package com.example.teachandlearn.Student.Form1.Documents.Agriculture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;

public class Form1AgricultureReadNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_agriculture_read_notes);

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
        // Create an intent to navigate back to Form1StudentViewContentAgriculture
        Intent intent = new Intent(this, Form1StudentViewContentAgriculture.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Optional: Clear activity stack
        startActivity(intent);
        finish(); // Finish current activity
    }
}
