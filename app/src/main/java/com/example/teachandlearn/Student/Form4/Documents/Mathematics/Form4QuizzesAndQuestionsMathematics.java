
package com.example.teachandlearn.Student.Form4.Documents.Mathematics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.CHATGPT.ChatGPTService;
import com.example.teachandlearn.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;


public class Form4QuizzesAndQuestionsMathematics extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> pdfNames;
    private ArrayList<String> pdfUrls; // To store URLs of the PDFs
    private ChatGPTService chatGPTService;


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
                Intent intent = new Intent(Form4QuizzesAndQuestionsMathematics.this, Form4PDFViewerMathematics.class);
                intent.putExtra("PDF_URL", url);
                startActivity(intent);
            }
        });



        // Quiz button setup
        Button buttonStartQuiz = findViewById(R.id.buttonStartQuiz);
        buttonStartQuiz.setOnClickListener(v -> {
            ArrayList<HashMap<String, String>> questions = new ArrayList<>();
            HashMap<String, String> question1 = new HashMap<>();
            question1.put("question", "Question 1?");
            question1.put("optionA", "Option A1");
            question1.put("optionB", "Option B1");
            question1.put("optionC", "Option C1");
            question1.put("optionD", "Option D1");
            question1.put("correctAnswer", "Option A1");
            questions.add(question1);

            HashMap<String, String> question2 = new HashMap<>();
            question2.put("question", "Question 2?");
            question2.put("optionA", "Option A2");
            question2.put("optionB", "Option B2");
            question2.put("optionC", "Option C2");
            question2.put("optionD", "Option D2");
            question2.put("correctAnswer", "Option B2");
            questions.add(question2);



            HashMap<String, String> question3 = new HashMap<>();
            question2.put("question", "Question 3?");
            question2.put("optionA", "hello, hey");
            question2.put("optionB", "hello, who are you");
            question2.put("optionC", "hello hie");
            question2.put("optionD", "hello..");
            question2.put("correctAnswer", "hello hie");
            questions.add(question3);

            HashMap<String, String> question4 = new HashMap<>();
            question2.put("question", "Question 2?");
            question2.put("optionA", "Option A2");
            question2.put("optionB", "Option B2");
            question2.put("optionC", "Option C2");
            question2.put("optionD", "Option D2");
            question2.put("correctAnswer", "Option B2");
            questions.add(question4);

            HashMap<String, String> question5 = new HashMap<>();
            question2.put("question", "Question 2?");
            question2.put("optionA", "Option A2");
            question2.put("optionB", "Option B2");
            question2.put("optionC", "Option C2");
            question2.put("optionD", "Option D2");
            question2.put("correctAnswer", "Option B2");
            questions.add(question5);

            HashMap<String, String> question6 = new HashMap<>();
            question2.put("question", "Question 2?");
            question2.put("optionA", "Option A2");
            question2.put("optionB", "Option B2");
            question2.put("optionC", "Option C2");
            question2.put("optionD", "Option D2");
            question2.put("correctAnswer", "Option B2");
            questions.add(question6);

            HashMap<String, String> question7 = new HashMap<>();
            question2.put("question", "Question 2?");
            question2.put("optionA", "Option A2");
            question2.put("optionB", "Option B2");
            question2.put("optionC", "Option C2");
            question2.put("optionD", "Option D2");
            question2.put("correctAnswer", "Option B2");
            questions.add(question7);

            HashMap<String, String> question8 = new HashMap<>();
            question2.put("question", "Question 2?");
            question2.put("optionA", "Option A2");
            question2.put("optionB", "Option B2");
            question2.put("optionC", "Option C2");
            question2.put("optionD", "Option D2");
            question2.put("correctAnswer", "Option B2");
            questions.add(question8);

            Intent intent = new Intent(Form4QuizzesAndQuestionsMathematics.this, Form4QuizMathematics.class);
            intent.putExtra("QUESTIONS", questions);
            startActivity(intent);
        });

        loadPDFsFromStorage();
    }

    private void loadPDFsFromStorage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef;

        // Initialize the storage reference for the desired path
        storageRef = storage.getReference().child("/form4/sciences/mathematics/quizzes_and_questions/");

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
            Toast.makeText(Form4QuizzesAndQuestionsMathematics.this, "Failed to load past papers", Toast.LENGTH_SHORT).show();
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