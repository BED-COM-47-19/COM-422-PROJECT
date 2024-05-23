


package com.example.teachandlearn.CHATGPT;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class ChatBot {

    private static final String CHATBOT_API_URL = "https://tsogolo.com"; // Replace this with your chatbot API endpoint

    public interface ChatbotResponseListener {
        void onChatbotResponse(String response);
    }

    public static void sendMessage(String message, ChatbotResponseListener listener) {
        new SendMessageTask(listener).execute(message);
    }

    private static class SendMessageTask extends AsyncTask<String, Void, String> {
        private final ChatbotResponseListener listener;

        SendMessageTask(ChatbotResponseListener listener) {
            this.listener = listener;
        }

        @Override
        protected String doInBackground(String... params) {
            String message = params[0];
            try {
                URL url = new URL(CHATBOT_API_URL + "?message=" + message);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            if (listener != null) {
                listener.onChatbotResponse(response);
            }
        }
    }
}
