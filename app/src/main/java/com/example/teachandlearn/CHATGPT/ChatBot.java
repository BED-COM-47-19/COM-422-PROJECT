
package com.example.teachandlearn.CHATGPT;
import androidx.appcompat.app.AppCompatActivity;


public class ChatBot {

    public interface ChatbotResponseListener {
        void onChatbotResponse(String response);
    }

    public static void sendMessage(String message, ChatbotResponseListener listener) {
        ChatGPTService service = new ChatGPTService();
        service.sendCommentToAI(message, new ChatGPTService.ChatGPTCallback() {
            @Override
            public void onSuccess(String response) {
                if (listener != null) {
                    listener.onChatbotResponse(response);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                if (listener != null) {
                    listener.onChatbotResponse(null);
                }
            }
        });


    }

    
}