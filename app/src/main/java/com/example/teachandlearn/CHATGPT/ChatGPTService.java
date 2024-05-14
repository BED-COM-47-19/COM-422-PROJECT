

package com.example.teachandlearn.CHATGPT;
import android.widget.Toast;
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

    public void sendCommentToAI(String comment) {
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ChatGPT service interface
        ChatGPTServiceInterface service = retrofit.create(ChatGPTServiceInterface.class);

        // Make an API request to get a response from ChatGPT
        Call<ChatGPTResponse> call = service.getChatResponse(comment, 50); // Adjust max_tokens as needed

        call.enqueue(new Callback<ChatGPTResponse>() {
            @Override
            public void onResponse(Call<ChatGPTResponse> call, Response<ChatGPTResponse> response) {
                if (response.isSuccessful()) {
                    // Step 5: Display Response to the Student
                    ChatGPTResponse responseBody = response.body();
                    String generatedResponse = responseBody.getChoices().get(0).getText();

                    // Update the UI to display the generated response
                    // Assuming you have a TextView named textViewResponse in your activity
                    // textViewResponse.setText(generatedResponse);
                } else {
                    // Handle unsuccessful response
                    // Assuming this code is within an Activity
                    // Toast.makeText(MainActivity.this, "Failed to process comment", Toast.LENGTH_SHORT).show();
                    // Handle failure to make API request
                    // Assuming this code is within an Activity
                    // Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ChatGPTResponse> call, Throwable t) {
                // Handle failure to make API request
                // Assuming this code is within an Activity
                //Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    interface ChatGPTServiceInterface {
        @POST("completion/generic-chat")
        @FormUrlEncoded
        Call<ChatGPTResponse> getChatResponse(
                @Field("prompt") String prompt,
                @Field("max_tokens") int maxTokens
        );
    }

    class ChatGPTResponse {
        private List<ChatGPTChoice> choices;

        public List<ChatGPTChoice> getChoices() {
            return choices;
        }
    }

    class ChatGPTChoice {
        private String text;

        public String getText() {
            return text;
        }
    }
}
