package com.example.teachandlearn.Student.Form1.Documents.Social_Studies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.CHATGPT.ChatGPTService;
import com.example.teachandlearn.R;

public class Form1StudentViewContentSocial_Studies extends AppCompatActivity {

    private Button buttonBack;
    private Button buttonChat;
    private Button buttonReadNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_socialstudies_view_content);

        // Find buttons for PDF, Audio, Videos, Questions, and Read Notes
        Button buttonPDF = findViewById(R.id.button_pdf);
        Button buttonAudio = findViewById(R.id.button_audio);
        Button buttonVideos = findViewById(R.id.button_videos);
        Button buttonQuestions = findViewById(R.id.button_tests_quizzes);
        buttonBack = findViewById(R.id.back_button);
        buttonReadNotes = findViewById(R.id.button_read_notes);
        buttonChat = findViewById(R.id.button_chat); // Initialize buttonChat

        // Set click listeners for each button
        buttonPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1StudentViewContentSocial_Studies.this, Form1PDFSocial_Studies.class);
                startActivity(intent);
            }
        });

        buttonAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1StudentViewContentSocial_Studies.this, Form1AudioSocial_Studies.class);
                startActivity(intent);
            }
        });

        buttonVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1StudentViewContentSocial_Studies.this, Form1VideoSocial_Studies.class);
                startActivity(intent);
            }
        });

        buttonQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1StudentViewContentSocial_Studies.this, Form1QuizzesAndQuestionsSocial_Studies.class);
                startActivity(intent);
            }
        });

        // Set click listener for Read Notes button
        buttonReadNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1StudentViewContentSocial_Studies.this, Form1Social_StudiesReadNotesActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Back button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });

        // Set click listener for Chat button
        buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatInterface();
            }
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
