package com.example.teachandlearn.Student.Form1.Documents.Chemistry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.CHATGPT.ChatGPTService;
import com.example.teachandlearn.R;

public class Form1StudentViewContentChemistry extends AppCompatActivity {

    private Button buttonBack;

    private Button buttonChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_chemistry_view_content);

        // Find buttons for PDF, Audio, Videos, and Questions
        Button buttonPDF = findViewById(R.id.button_pdf);
        Button buttonAudio = findViewById(R.id.button_audio);
        Button buttonVideos = findViewById(R.id.button_videos);
        Button buttonQuestions = findViewById(R.id.button_tests_quizzes);
        Button buttonReadNotes = findViewById(R.id.button_read_notes);
        buttonBack = findViewById(R.id.back_button);

        // Set click listeners for each button
        buttonPDF.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentChemistry.this, Form1PDFChemistry.class);
            startActivity(intent);
        });

        buttonAudio.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentChemistry.this, Form1AudioChemistry.class);
            startActivity(intent);
        });

        buttonVideos.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentChemistry.this, Form1VideoChemistry.class);
            startActivity(intent);
        });

        buttonQuestions.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentChemistry.this, Form1QuizzesAndQuestionsChemistry.class);
            startActivity(intent);
        });

        // Set click listener for Read Notes button
        buttonReadNotes.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentChemistry.this, Form1ChemistryReadNotesActivity.class);
            startActivity(intent);
        });

        buttonBack.setOnClickListener(v -> onBackPressed());
    }

    private void openChatInterface() {
        // Handle logic for opening the chat interface
        // For example, start the ChatGPTService
        Intent intent = new Intent(this, ChatGPTService.class);
        startService(intent);
    }

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
}
