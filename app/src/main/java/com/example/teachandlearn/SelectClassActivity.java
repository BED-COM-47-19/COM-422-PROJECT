package com.example.teachandlearn;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SelectClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);

        // Find buttons for each form
        Button buttonForm1 = findViewById(R.id.buttonForm1);
        Button buttonForm2 = findViewById(R.id.buttonForm2);
        Button buttonForm3 = findViewById(R.id.buttonForm3);
        Button buttonForm4 = findViewById(R.id.buttonForm4);

        // Set click listeners for each button
        buttonForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Form 1 Selected");
            }
        });

        buttonForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Form 2 Selected");
            }
        });

        buttonForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Form 3 Selected");
            }
        });

        buttonForm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Form 4 Selected");
            }
        });
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(SelectClassActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
