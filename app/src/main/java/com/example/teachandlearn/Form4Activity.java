package com.example.teachandlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Form4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form4);

        // Find the buttons for SCIENCE, HUMANITIES, and LANGUAGES
        Button buttonScience = findViewById(R.id.buttonScience);
        Button buttonHumanities = findViewById(R.id.buttonHumanities);
        Button buttonLanguages = findViewById(R.id.buttonLanguages);

        // Set onClickListener for SCIENCE button
        buttonScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SelectScienceActivity when SCIENCE button is clicked
                Intent intent = new Intent(Form4Activity.this, Form4ScienceActivity.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for HUMANITIES button
        buttonHumanities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SelectScienceActivity when SCIENCE button is clicked
                Intent intent = new Intent(Form4Activity.this, Form4HumanitiesActivity.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for LANGUAGES button
        buttonLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SelectScienceActivity when SCIENCE button is clicked
                Intent intent = new Intent(Form4Activity.this, Form4LanguagesActivity.class);
                startActivity(intent);
            }
        });
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(Form4Activity.this, message, Toast.LENGTH_SHORT).show();
    }
}
