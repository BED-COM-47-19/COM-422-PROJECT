package com.example.teachandlearn.Student.Form1.Documents.Life_Skills;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.CHATGPT.ChatGPTService;
import com.example.teachandlearn.R;

public class Form1StudentViewContentLife_Skills extends AppCompatActivity {

    private Button buttonBack;
    private Button buttonChat;
    private Button buttonReadNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_lifeskills_view_content);

        // Find buttons for PDF, Audio, Videos, and Questions
        Button buttonPDF = findViewById(R.id.button_pdf);
        Button buttonAudio = findViewById(R.id.button_audio);
        Button buttonVideos = findViewById(R.id.button_videos);
        Button buttonQuestions = findViewById(R.id.button_tests_quizzes);
        buttonBack = findViewById(R.id.back_button);
        buttonReadNotes = findViewById(R.id.button_read_notes); // Initialize Read Notes button

        // Set click listeners for PDF, Audio, Videos, and Questions buttons
        buttonPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1StudentViewContentLife_Skills.this, Form1PDFLife_Skills.class);
                startActivity(intent);
            }
        });

        buttonAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1StudentViewContentLife_Skills.this, Form1AudioLife_Skills.class);
                startActivity(intent);
            }
        });

        buttonVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1StudentViewContentLife_Skills.this, Form1VideoLife_Skills.class);
                startActivity(intent);
            }
        });

        buttonQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1StudentViewContentLife_Skills.this, Form1QuizzesAndQuestionsLife_Skills.class);
                startActivity(intent);
            }
        });

        // Set click listener for Read Notes button
        buttonReadNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1StudentViewContentLife_Skills.this, Form1Life_SkillsReadNotesActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Back button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    // Method to handle opening the chat interface (if needed)
    private void openChatInterface() {
        Intent intent = new Intent(this, ChatGPTService.class);
        startService(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Add custom logic here if needed
    }
}
