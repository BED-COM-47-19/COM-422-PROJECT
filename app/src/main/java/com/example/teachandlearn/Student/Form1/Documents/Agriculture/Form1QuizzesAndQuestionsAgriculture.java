
package com.example.teachandlearn.Student.Form1.Documents.Agriculture;

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
import java.util.HashMap;


public class Form1QuizzesAndQuestionsAgriculture extends AppCompatActivity {

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
        setContentView(R.layout.activity_form1_agr_quizzes_and_questions);

        listView = findViewById(R.id.list_view);
        pdfNames = new ArrayList<>();
        pdfUrls = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pdfNames);
        listView.setAdapter(adapter);

        chatGPTService = new ChatGPTService();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = pdfUrls.get(position);
                Intent intent = new Intent(Form1QuizzesAndQuestionsAgriculture.this, Form1PDFViewerAgriculture.class);
                intent.putExtra("PDF_URL", url);
                startActivity(intent);
            }
        });



        // Quiz button setup
        Button buttonStartQuiz = findViewById(R.id.buttonStartQuiz);
        buttonStartQuiz.setOnClickListener(v -> {
            ArrayList<HashMap<String, String>> questions = new ArrayList<>();

            Intent intent = new Intent(Form1QuizzesAndQuestionsAgriculture.this, Form1QuizAgriculture.class);
            intent.putExtra("QUESTIONS", questions);
            startActivity(intent);
        });

        loadPDFsFromStorage();
    }

    private void loadPDFsFromStorage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef;

        // Initialize the storage reference for the desired path
        storageRef = storage.getReference().child("/form1/sciences/agriculture/quizzes_and_questions/");

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
            Toast.makeText(Form1QuizzesAndQuestionsAgriculture.this, "Failed to load past papers", Toast.LENGTH_SHORT).show();
        });



    }

    private void showNoFilesUploaded() {
        // Clear the existing list of PDFs
        pdfNames.clear();
        pdfUrls.clear();
        adapter.notifyDataSetChanged();
        // Display "No file Uploaded" message
        Toast.makeText(this, "No file Uploaded", Toast.LENGTH_SHORT).show();
    }
}