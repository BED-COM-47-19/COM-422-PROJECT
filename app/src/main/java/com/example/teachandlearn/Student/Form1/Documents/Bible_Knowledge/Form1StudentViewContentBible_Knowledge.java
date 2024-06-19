package com.example.teachandlearn.Student.Form1.Documents.Bible_Knowledge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.CHATGPT.ChatGPTService;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Documents.Bible_Knowledge.Form1BibleKnowledgeReadNotesActivity;

public class Form1StudentViewContentBible_Knowledge extends AppCompatActivity {

    private Button buttonBack;
    private Button buttonChat;
    private Button buttonReadNotes;
    // Declare other buttons here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_bible_knowledge_view_content);

        // Find buttons
        Button buttonPDF = findViewById(R.id.button_pdf);
        Button buttonAudio = findViewById(R.id.button_audio);
        Button buttonVideos = findViewById(R.id.button_videos);
        Button buttonQuestions = findViewById(R.id.button_tests_quizzes);
        buttonBack = findViewById(R.id.back_button);
        buttonChat = findViewById(R.id.button_chat);
        buttonReadNotes = findViewById(R.id.button_read_notes);

        // Set click listeners
        buttonPDF.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentBible_Knowledge.this, Form1PDFBible_Knowledge.class);
            startActivity(intent);
        });

        buttonAudio.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentBible_Knowledge.this, Form1AudioBible_Knowledge.class);
            startActivity(intent);
        });

        buttonVideos.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentBible_Knowledge.this, Form1VideoBible_Knowledge.class);
            startActivity(intent);
        });

        buttonQuestions.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentBible_Knowledge.this, Form1QuizzesAndQuestionsBible_Knowledge.class);
            startActivity(intent);
        });

        buttonBack.setOnClickListener(view -> {
            onBackPressed();
        });

        buttonChat.setOnClickListener(v -> {
            openChatInterface();
        });

        buttonReadNotes.setOnClickListener(v -> {
            Intent intent = new Intent(Form1StudentViewContentBible_Knowledge.this, Form1BibleKnowledgeReadNotesActivity.class);
            startActivity(intent);
        });
    }

    private void openChatInterface() {
        Intent intent = new Intent(this, ChatGPTService.class);
        startService(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Additional custom logic if needed
    }
}
