

package com.example.teachandlearn.Student.SelectClass;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.teachandlearn.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";

    RecyclerView recyclerView;
    EditText messageEditText;
    ImageButton sendButton;

    List<Message> messageList;
    MessageAdapter messageAdapter;

    OkHttpClient client;
    Call currentCall;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private int currentRetries = 0;
    private final long INITIAL_RETRY_DELAY = 1000;
    private long retryDelay = INITIAL_RETRY_DELAY;

    private boolean isRateLimited = false;
    private long rateLimitResetTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        recyclerView = findViewById(R.id.recycler_view);
        messageEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_btn);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sendButton.setOnClickListener(v -> {
            String question = messageEditText.getText().toString().trim();
            if (!question.isEmpty()) {
                addToChat(question, Message.SENT_BY_ME);
                messageEditText.setText("");
                callAPI(question);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel ongoing requests and clear resources
        if (currentCall != null) {
            currentCall.cancel();
        }
    }

    void addToChat(String message, String sentBy) {
        runOnUiThread(() -> {
            messageList.add(new Message(message, sentBy));
            messageAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
        });
    }

    void addResponse(String response) {
        runOnUiThread(() -> {
            messageList.add(new Message(response, Message.SENT_BY_BOT));
            messageAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
        });
    }

    void callAPI(String question) {
        addToChat("Typing...", Message.SENT_BY_BOT);

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "gpt-4-turbo");

            // Create the messages array
            JSONArray messagesArray = new JSONArray();

            // Add system message
            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", "You are a helpful assistant.");
            messagesArray.put(systemMessage);

            // Add user message
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", question); // User question
            messagesArray.put(userMessage);

            jsonBody.put("messages", messagesArray);

            Log.d("INFO",jsonBody.toString());


        } catch (JSONException e) {
            Log.e(TAG, "JSON exception", e);
        }

        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        String apiKey = getString(R.string.api_key);  // Retrieve API key from strings.xml
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey) // Ensure there is a space after "Bearer"
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        currentCall = client.newCall(request);
        currentCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handleApiCallFailure(e, question);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    handleApiResponse(response);
                } else {
                    handleApiErrorResponse(response);
                }
            }
        });
    }

    private void handleApiCallFailure(IOException e, String question) {
        String errorMessage = "API call failed: " + e.getMessage();
        Log.e(TAG, errorMessage);
        addToChat(errorMessage, Message.SENT_BY_BOT);

        int MAX_RETRY_ATTEMPTS = 3;
        if (currentRetries < MAX_RETRY_ATTEMPTS) {
            currentRetries++;
            Log.d(TAG, errorMessage + ". Retrying in " + retryDelay + "ms.");
            addToChat("Retrying...", Message.SENT_BY_BOT);

            retryDelay *= 2;
            long MAX_RETRY_DELAY = 16000;
            if (retryDelay > MAX_RETRY_DELAY) {
                retryDelay = MAX_RETRY_DELAY;
            }

            new Handler().postDelayed(() -> {
                if (currentCall != null && !currentCall.isCanceled()) {
                    currentCall.cancel();
                }
                callAPI(question);
            }, retryDelay);
        } else {
            currentRetries = 0;
            retryDelay = INITIAL_RETRY_DELAY;
            addToChat("Network error: " + e.getMessage(), Message.SENT_BY_BOT);
        }
    }

    private void handleApiResponse(Response response) throws IOException {
        if (response.body() != null) {
            String responseBody = response.body().string();
            Log.d(TAG, "API Response: " + responseBody);
            try {
                JSONObject jsonObject = new JSONObject(responseBody);
                JSONArray choicesArray = jsonObject.getJSONArray("choices");
                if (choicesArray.length() > 0) {
                    JSONObject choiceObject = choicesArray.getJSONObject(0);
                    JSONObject messageObject = choiceObject.getJSONObject("message");
                    String content = messageObject.getString("content");
                    addResponse(content.trim());
                } else {
                    String errorMessage = "Empty response from API";
                    Log.e(TAG, errorMessage);
                    addResponse(errorMessage);
                }
            } catch (JSONException e) {
                String errorMessage = "Failed to parse response: " + e.getMessage();
                Log.e(TAG, errorMessage);
                addResponse(errorMessage);
            }
            currentRetries = 0;
            retryDelay = INITIAL_RETRY_DELAY;
        }
    }

    private void handleApiErrorResponse(Response response) {
        String errorMessage;
        if (response.code() == 401) {
            errorMessage = "Unauthorized access. Please check your API key.";
        } else if (response.code() == 429) {
            isRateLimited = true;
            String retryAfter = response.header("Retry-After");
            if (retryAfter != null) {
                rateLimitResetTime = System.currentTimeMillis() / 1000 + Long.parseLong(retryAfter);
            } else {
                rateLimitResetTime = System.currentTimeMillis() / 1000 + 5; // Default retry after 5 seconds
            }
            errorMessage = "API rate limit exceeded. Please try again later.";
        } else {
            errorMessage = "Failed to load response: " + response.code();
        }
        Log.e(TAG, errorMessage);
        addResponse(errorMessage);
    }
}
