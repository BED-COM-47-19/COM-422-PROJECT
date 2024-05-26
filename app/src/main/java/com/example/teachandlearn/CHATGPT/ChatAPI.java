


package com.example.teachandlearn.CHATGPT;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;



public interface ChatAPI {
    @POST("chat")
    Call<ChatResponse> sendMessage(@Body ChatRequest request);
}

class ChatRequest {
    private String message;

    public ChatRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

class ChatResponse {
    private String response;

    public String getResponse() {
        return response;
    }
}
