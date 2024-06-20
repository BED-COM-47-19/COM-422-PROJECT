package com.example.teachandlearn.Student.Form1.Categories;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.google.ai.client.generativeai.GenerativeAI;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;

public class ChatAIActivity extends AppCompatActivity {

    private Button sendButton;
    private GenerativeAI model; // Declare the GenerativeAI object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ai);

        // Initialize views
        sendButton = findViewById(R.id.sendButton);

        // Initialize GenerativeAI model
        model = GenerativeAI.initialize(this); // Replace with actual initialization logic

        // Set click listener for sendButton
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(); // Call your method to handle send action
            }
        });
    }

    private void sendMessage() {
        // Replace with your logic for sending the message
        // For example, you might want to send the text from an EditText to a server or process it locally
        // This is a placeholder method for demonstration purposes
        // Modify this method according to your actual application logic
        // For now, let's just show a toast message
        Toast.makeText(ChatAIActivity.this, "Message Sent!", Toast.LENGTH_SHORT).show();

        // Call your model method to generate content
        modelCall();
    }

    private void modelCall() {
        Content content = new Content.Builder()
                .addText("Write a story about AI and magic")
                .build();

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String resultText = result.getText();
                Toast.makeText(ChatAIActivity.this, resultText, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(ChatAIActivity.this, "Model call failed", Toast.LENGTH_SHORT).show();
            }
        }, this.getMainExecutor());
    }
}
