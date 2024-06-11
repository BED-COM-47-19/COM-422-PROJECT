

package com.example.teachandlearn.CHATGPT;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;



public class ChatGPTService extends Service {

    private OkHttpClient client;

    public IBinder onBind(Intent intent) {
        // Return null because your service doesn't support binding
        return null;
    }

    public ChatGPTService() {
        // Create an instance of OkHttpClient
        client = new OkHttpClient();
    }

    public void sendCommentToAI(String comment, ChatGPTCallback callback) {
        // Build the request body
        RequestBody requestBody = new FormBody.Builder()
                .add("prompt", comment)
                .add("max_tokens", "50")
                .build();

        // Build the request
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completion/generic-chat")
                .addHeader("Authorization", "Bearer sk-proj-3vQstH301YqSAjfmfrMkT3BlbkFJHyityVAfLQuKgyTuax4d") // Replace YOUR_API_KEY_HERE with your actual API key
                .post(requestBody)
                .build();

        // Asynchronously execute the request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected response code: " + response);
                    }

                    String responseBody = response.body().string();
                    callback.onSuccess(responseBody);
                } catch (IOException e) {
                    callback.onFailure(e);
                }
            }
        });
    }

    public interface ChatGPTCallback {
        void onSuccess(String response);
        void onFailure(Throwable t);
    }

}