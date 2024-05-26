

package com.example.teachandlearn.CHATGPT;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import com.example.teachandlearn.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;




public class Chat extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private List<Message> messageList;
    private EditText editTextMessage;
    private ImageButton buttonSendMessage;
    private ChatAPI chatApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Initialize Retrofit
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your-backend-server.com/") // Replace with your server URL
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        chatApi = retrofit.create(ChatAPI.class);

        // Initialize UI components
        recyclerView = findViewById(R.id.recyclerView);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSendMessage = findViewById(R.id.buttonSendMessage);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageList = new ArrayList<>();
        adapter = new ChatAdapter(messageList);
        recyclerView.setAdapter(adapter);

        // Send message button click listener
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    // Method to send message
    private void sendMessage() {
        String messageText = editTextMessage.getText().toString().trim();
        if (!messageText.isEmpty()) {
            // Add user's message
            messageList.add(new Message(messageText, true));
            adapter.notifyDataSetChanged();
            // Clear input field after sending message
            editTextMessage.setText("");
            // Scroll RecyclerView to the bottom
            recyclerView.scrollToPosition(messageList.size() - 1);

            // Send message to server and handle response
            sendToServer(messageText);
        } else {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendToServer(String userMessage) {
        ChatRequest request = new ChatRequest(userMessage);
        chatApi.sendMessage(request).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseText = response.body().getResponse();
                    messageList.add(new Message(responseText, false));
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(messageList.size() - 1);
                } else {
                    Toast.makeText(Chat.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Toast.makeText(Chat.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Message class
    public static class Message {
        private String message;
        private boolean sentByUser;

        public Message(String message, boolean sentByUser) {
            this.message = message;
            this.sentByUser = sentByUser;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSentByUser() {
            return sentByUser;
        }
    }

    // ChatAdapter class
    public static class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {

        private List<Message> messageList;

        public ChatAdapter(List<Message> messageList) {
            this.messageList = messageList;
        }

        @Override
        public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_item_message, parent, false);
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MessageViewHolder holder, int position) {
            Message message = messageList.get(position);
            holder.bind(message);
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }

        public static class MessageViewHolder extends RecyclerView.ViewHolder {

            TextView textViewMessage;

            public MessageViewHolder(View itemView) {
                super(itemView);
                textViewMessage = itemView.findViewById(R.id.textViewMessage);
            }

            public void bind(Message message) {
                textViewMessage.setText(message.getMessage());
                // Change background based on the sender
                if (message.isSentByUser()) {
                    textViewMessage.setBackgroundResource(R.drawable.message_background_user);
                } else {
                    textViewMessage.setBackgroundResource(R.drawable.message_background_bot);
                }
            }
        }
    }
}