package com.example.teachandlearn.Student.Form2.Documents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.CHATGPT.ChatGPTService;
import com.example.teachandlearn.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

public class Form2QuizzesAndQuestions extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> pdfNames;
    private ArrayList<String> pdfUrls; // To store URLs of the PDFs
    private ChatGPTService chatGPTService;
    private EditText editTextComment;
    private Button buttonSubmitComment;
    private ArrayList<String> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2_quizzes_and_questions);

        editTextComment = findViewById(R.id.editTextComment);
        buttonSubmitComment = findViewById(R.id.buttonSubmitComment);
        listView = findViewById(R.id.list_view);
        pdfNames = new ArrayList<>();
        pdfUrls = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pdfNames);
        listView.setAdapter(adapter);

        chatGPTService = new ChatGPTService();

        comments = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String comment = comments.get(position);
                String url = pdfUrls.get(position);
                Intent intent = new Intent(Form2QuizzesAndQuestions.this, Form2PDFViewer.class);
                intent.putExtra("PDF_URL", url);
                intent.putExtra("COMMENT", comment);
                startActivity(intent);
            }
        });

        buttonSubmitComment.setOnClickListener(v -> {
            String comment = editTextComment.getText().toString().trim();
            if (!comment.isEmpty()) {
                comments.add(comment);
                editTextComment.setText(""); // Clear the comment field after submission
                adapter.notifyDataSetChanged(); // Notify adapter of data change
                chatGPTService.sendCommentToAI(comment, new ChatGPTService.ChatGPTCallback() {
                    @Override
                    public void onSuccess(String response) {
                        // Handle the successful AI response
                        Toast.makeText(Form2QuizzesAndQuestions.this, "AI Response: " + response, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        // Handle the failure of the AI response
                        Toast.makeText(Form2QuizzesAndQuestions.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        loadPDFsFromStorage();
    }

    private void loadPDFsFromStorage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef;

        // Initialize the storage reference for the desired path
        storageRef = storage.getReference().child("/form1/humanities/bible_knowledge/quizzes_and_questions/");

        // Now you can use this reference to list the items in the directory
        storageRef.listAll().addOnSuccessListener(listResult -> {
            if (listResult.getItems().isEmpty()) {
                // If no documents are found, show "No file Uploaded" message
                showNoFilesUploaded();
            } else {
                for (StorageReference item : listResult.getItems()) {
                    // Get the name and download URL for each item
                    String name = item.getName();
                    item.getDownloadUrl().addOnSuccessListener(uri -> {
                        // Convert the URI to a string URL
                        String url = uri.toString();
                        // Add the name and URL to the respective lists
                        pdfNames.add(name);
                        pdfUrls.add(url);
                        // Add an empty comment for each item
                        comments.add("");
                        // Notify the adapter of the data change
                        adapter.notifyDataSetChanged();
                    }).addOnFailureListener(e -> {
                        // Handle failure to get download URL
                        Log.e("Form1QuizzesAndQuestions", "Failed to get download URL", e);
                    });
                }
            }
        }).addOnFailureListener(exception -> {
            // Handle the error
            Toast.makeText(Form2QuizzesAndQuestions.this, "Failed to load past papers", Toast.LENGTH_SHORT).show();
        });
    }

    private void showNoFilesUploaded() {
        // Clear the existing list of PDFs
        pdfNames.clear();
        pdfUrls.clear();
        adapter.notifyDataSetChanged();
        comments.clear();
        // Display "No file Uploaded" message
        Toast.makeText(this, "No file Uploaded", Toast.LENGTH_SHORT).show();
    }
}
