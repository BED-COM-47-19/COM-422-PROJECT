

package com.example.teachandlearn.CHATGPT;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
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
import kotlin.jvm.Throws;


public class ChatGPTService extends AppCompatActivity {

    // creating variables on below line.
    private TextView responseTV;
    private TextView questionTV;
    private TextInputEditText queryEdt;

    private final String url = "https://api.openai.com/v1/completions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_item_message);
        // initializing variables on below line.
        responseTV = findViewById(R.id.idTVResponse);
        questionTV = findViewById(R.id.idTVQuestion);
        queryEdt = findViewById(R.id.idEdtQuery);




        // adding editor action listener for edit text on below line.
        queryEdt.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, android.view.KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    // setting response tv on below line.
                    responseTV.setText("Please wait..");
                    // validating text
                    if (queryEdt.getText().toString().length() > 0) {
                        // calling get response to get the response.
                        getResponse(queryEdt.getText().toString());
                    } else {
                        Toast.makeText(ChatGPTService.this, "Please enter your query..", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void getResponse(String query) {
        // setting text on for question on below line.
        questionTV.setText(query);
        queryEdt.setText("");
        // creating a queue for request queue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        // creating a json object on below line.
        JSONObject jsonObject = new JSONObject();
        try {
            // adding params to json object.
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

        // on below line making json object request.
        JsonObjectRequest postRequest = new JsonObjectRequest(
                JsonObjectRequest.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // on below line getting response message and setting it to text view.
                        try {
                            String responseMsg = response.getJSONArray("choices").getJSONObject(0).getString("text");
                            responseTV.setText(responseMsg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            responseTV.setText("Error parsing response.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAGAPI", "Error is : " + error.getMessage() + "\n" + error);
                    }
                }
        ) {
            @Override
            public java.util.Map<String, String> getHeaders() {
                java.util.Map<String, String> params = new java.util.HashMap<>();
                // adding headers on below line.
                params.put("Content-Type", "application/json");
                params.put("Authorization", "sk-proj-NefvkoCl16tQoYM2bex1T3BlbkFJJz5PcdElIEp6eDiOmMAh");
                return params;
            }
        };

        // on below line adding retry policy for our request.
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

            }
        });
        // on below line adding our request to queue.
        queue.add(postRequest);
    }
}
