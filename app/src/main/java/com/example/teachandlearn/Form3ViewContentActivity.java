package com.example.teachandlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Form3ViewContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3_view_content);

        // Find buttons for PDF, Audio, Videos, and Questions
        Button buttonPDF = findViewById(R.id.buttonPDF);
        Button buttonAudio = findViewById(R.id.buttonAudio);
        Button buttonVideos = findViewById(R.id.buttonVideos);
        Button buttonQuestions = findViewById(R.id.buttonQuestions);

        // Set click listeners for each button
        buttonPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToForm3ScienceActivity();
            }
        });

        buttonAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToForm3ScienceActivity();
            }
        });

        buttonVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToForm3ScienceActivity();
            }
        });

        buttonQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFor3ScienceActivity();
            }
        });
    }

    // Helper method to navigate to Form1ScienceActivity
    private void navigateToForm3ScienceActivity() {
        Intent intent = new Intent(Form3ViewContentActivity.this, Form3ScienceActivity.class);
        startActivity(intent);
    }
}
