package com.example.teachandlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class StudentSelectClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class_activity);

        // Find buttons for each form
        Button buttonForm1 = findViewById(R.id.buttonForm1);
        Button buttonForm2 = findViewById(R.id.buttonForm2);
        Button buttonForm3 = findViewById(R.id.buttonForm3);
        Button buttonForm4 = findViewById(R.id.buttonForm4);

        // Set click listeners for each button
        buttonForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SelectCategoryActivity when Form 1 is selected
                Intent intent = new Intent(StudentSelectClassActivity.this, Form1Activity.class);
                startActivity(intent);
            }
        });

        buttonForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentSelectClassActivity.this, Form2Activity.class);
                startActivity(intent);
            }
        });

        buttonForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentSelectClassActivity.this, Form3Activity.class);
                startActivity(intent);
            }
        });

        buttonForm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentSelectClassActivity.this, Form4Activity.class);
                startActivity(intent);
            }
        });
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(StudentSelectClassActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
