
package com.example.teachandlearn.Teacher.Form2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Form1StudentViewContent;

public class TeacherForm2Languages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_languages);

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
        Toast.makeText(TeacherForm2Languages.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity
    private void startActivityForContent() {
        Intent intent = new Intent(TeacherForm2Languages.this, Form1StudentViewContent.class);
        startActivity(intent);
    }
}
