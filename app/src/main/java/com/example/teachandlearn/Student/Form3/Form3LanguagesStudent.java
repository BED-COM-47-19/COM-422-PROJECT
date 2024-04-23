package com.example.teachandlearn.Student.Form3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;

public class Form3LanguagesStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3_languages);

        // Find buttons for each humanities subject
        Button buttonEnglish = findViewById(R.id.buttonEnglish);
        Button buttonChichewa = findViewById(R.id.buttonChichewa);


        // Set click listeners for each button
        buttonEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("English Selected");
                startActivityForContent();
            }
        });

        buttonChichewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Chichewa Selected");
                startActivityForContent();
            }
        });


    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(Form3LanguagesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity
    private void startActivityForContent() {
        Intent intent = new Intent(Form3LanguagesStudent.this, Form3StudentViewContent.class);
        startActivity(intent);
    }
}
