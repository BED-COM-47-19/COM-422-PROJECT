package com.example.teachandlearn.CHATGPT;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class ChatGPTService {

    private ChatGPTServiceInterface service;

    public ChatGPTService() {
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ChatGPT service interface
        service = retrofit.create(ChatGPTServiceInterface.class);
    }

    public void sendCommentToAI(String comment, ChatGPTCallback callback) {
        // Make an API request to get a response from ChatGPT
        Call<ChatGPTResponse> call = service.getChatResponse(comment, 50); // Adjust max_tokens as needed

        call.enqueue(new Callback<ChatGPTResponse>() {
            @Override
            public void onResponse(Call<ChatGPTResponse> call, Response<ChatGPTResponse> response) {
                if (response.isSuccessful()) {
                    ChatGPTResponse responseBody = response.body();
                    if (responseBody != null && !responseBody.getChoices().isEmpty()) {
                        String generatedResponse = responseBody.getChoices().get(0).getText();
                        callback.onSuccess(generatedResponse);
                    } else {
                        callback.onFailure(new Exception("Empty response from AI"));
                    }
                } else {
                    callback.onFailure(new Exception("Failed to process comment"));
                }
            }

            @Override
            public void onFailure(Call<ChatGPTResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public interface ChatGPTCallback {
        void onSuccess(String response);
        void onFailure(Throwable t);
    }

    interface ChatGPTServiceInterface {
        @POST("completion/generic-chat")
        @FormUrlEncoded
        Call<ChatGPTResponse> getChatResponse(
                @Field("prompt") String prompt,
                @Field("max_tokens") int maxTokens
        );
    }

    public static class ChatGPTResponse {
        private List<ChatGPTChoice> choices;

        public List<ChatGPTChoice> getChoices() {
            return choices;
        }

        public void setChoices(List<ChatGPTChoice> choices) {
            this.choices = choices;
        }
    }

    public static class ChatGPTChoice {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
