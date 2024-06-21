package com.example.teachandlearn.Student.Form1.Documents.Agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.CHATGPT.ChatGPTService;
import com.example.teachandlearn.R;

public class Form1StudentViewContentAgriculture extends AppCompatActivity {

    private Button buttonBack;
    private Button buttonChat;
    private Button buttonReadNotes; // New button for reading notes


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_agriculture_view_content); // Ensure this layout file name is correct

        // Find buttons for PDF, Audio, Videos, and Questions
        Button buttonPDF = findViewById(R.id.button_pdf);
        Button buttonAudio = findViewById(R.id.button_audio);
        Button buttonVideos = findViewById(R.id.button_videos);
        Button buttonQuestions = findViewById(R.id.button_tests_quizzes);
        buttonBack = findViewById(R.id.back_button);
        buttonChat = findViewById(R.id.button_chat);
        buttonReadNotes = findViewById(R.id.button_read_notes); // Find the new button


        // Set click listeners for each button
        buttonPDF.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentAgriculture.this, Form1PDFAgriculture.class);
            startActivity(intent);
        });

        buttonAudio.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentAgriculture.this, Form1AudioAgriculture.class);
            startActivity(intent);
        });

        buttonVideos.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentAgriculture.this, Form1VideosAgriculture.class);
            startActivity(intent);
        });

        buttonQuestions.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentAgriculture.this, Form1QuizzesAndQuestionsAgriculture.class);
            startActivity(intent);
        });

        buttonBack.setOnClickListener(view -> {
            // Logic for when the back button is pressed
            onBackPressed();
        });

        buttonChat.setOnClickListener(v -> {
            // Open chat interface
            openChatInterface();
        });

        buttonReadNotes.setOnClickListener(v -> {
            // Logic for when the read notes button is pressed
            Intent intent = new Intent(Form1StudentViewContentAgriculture.this, Form1AgricultureReadNotesActivity.class);
            startActivity(intent);
        });



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
