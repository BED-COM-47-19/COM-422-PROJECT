package com.example.teachandlearn.Student.Form4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;

public class Form4ScienceStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form4_science);

        // Find buttons for each science field
        Button buttonMathematics = findViewById(R.id.buttonMathematics);
        Button buttonBiology = findViewById(R.id.buttonBiology);
        Button buttonPhysics = findViewById(R.id.buttonPhysics);
        Button buttonChemistry = findViewById(R.id.buttonChemistry);
        Button buttonAgriculture = findViewById(R.id.buttonAgriculture);

        // Set click listeners for each button
        buttonMathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message
                showToast("Mathematics Selected");
                // Start the Form1ViewContentActivity
                startActivityForContent();
            }
        });

        buttonBiology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message
                showToast("Biology Selected");
                // Start the Form1ViewContentActivity
                startActivityForContent();
            }
        });

        buttonChemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message
                showToast("Chemistry Selected");
                // Start the Form1ViewContentActivity
                startActivityForContent();
            }
        });

        buttonPhysics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message
                showToast("Physics Selected");
                // Start the Form1ViewContentActivity
                startActivityForContent();
            }
        });

        buttonAgriculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message
                showToast("Agriculture Selected");
                // Start the Form1ViewContentActivity
                startActivityForContent();
            }
        });
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(Form4ScienceStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity
    private void startActivityForContent() {
        Intent intent = new Intent(Form4ScienceStudent.this, Form4StudentViewContent.class);
        startActivity(intent);
    }
}
