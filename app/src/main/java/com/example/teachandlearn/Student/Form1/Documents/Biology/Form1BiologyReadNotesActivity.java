package com.example.teachandlearn.Student.Form1.Documents.Biology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;

public class Form1BiologyReadNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_biology_read_notes);

        // Find the back button
        Button backButton = findViewById(R.id.button_back);

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBack();
            }
        });
    }

    private void navigateBack() {
        // Create an intent to navigate back to Form1StudentViewContentBible_Knowledge
        Intent intent = new Intent(this, Form1StudentViewContentBiology.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clear activity stack
        startActivity(intent);
        finish(); // Finish current activity
    }
}
