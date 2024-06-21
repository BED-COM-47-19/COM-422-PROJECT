

package com.example.teachandlearn.CHATGPT;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.teachandlearn.R;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONObject;



public class ChatGPTService extends AppCompatActivity {

    private TextView modelResponseTextView;
    private TextInputEditText queryEdt;
    private ProgressBar sendPromptProgressBar;
    private Button sendPromptButton;

    private final String url = "https://api.openai.com/v1/completions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_item_message);

        // Initialize views
        queryEdt = findViewById(R.id.queryEditText);
        sendPromptButton = findViewById(R.id.sendPromptButton);
        sendPromptProgressBar = findViewById(R.id.sendPromptProgressBar);
        modelResponseTextView = findViewById(R.id.modelResponseTextView);

        // Set click listener for sendPromptButton
        sendPromptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPrompt();
            }
        });
    }

    private void sendPrompt() {
        // Show progress bar
        sendPromptProgressBar.setVisibility(View.VISIBLE);

        // Get the query from edit text
        String query = queryEdt.getText().toString().trim();

        // Check if query is empty
        if (query.isEmpty()) {
            Toast.makeText(this, "Please enter a prompt", Toast.LENGTH_SHORT).show();
            sendPromptProgressBar.setVisibility(View.GONE);
            return;
        }

        // Set query to TextView
        modelResponseTextView.setText(queryEdt.getText().toString());

        // Create JSON object with parameters
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("model", "text-davinci-003");
            jsonObject.put("prompt", query);
            jsonObject.put("temperature", 0);
            jsonObject.put("max_tokens", 100);
            jsonObject.put("top_p", 1);
            jsonObject.put("frequency_penalty", 0.0);
            jsonObject.put("presence_penalty", 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize request queue
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        // Create JSON object request
        JsonObjectRequest postRequest = new JsonObjectRequest(
                JsonObjectRequest.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            String responseMsg = response.getJSONArray("choices").getJSONObject(0).getString("text");
                            modelResponseTextView.setText(responseMsg);
                            sendPromptProgressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                            modelResponseTextView.setText("Error parsing response.");
                            sendPromptProgressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Log.e("TAGAPI", "Error: " + error.getMessage(), error);
                        modelResponseTextView.setText("Error: " + error.getMessage());
                        sendPromptProgressBar.setVisibility(View.GONE);
                    }
                }
        ) {
            // Set headers for the request
            @Override
            public java.util.Map<String, String> getHeaders() {
                java.util.Map<String, String> params = new java.util.HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer YOUR_API_KEY_HERE"); // Replace with your OpenAI API key
                return params;
            }
        };

        // Set retry policy for the request
        postRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) {
                // Retry policy
            }
        });

        // Add request to queue
        queue.add(postRequest);
    }
}
