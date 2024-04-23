package com.example.teachandlearn.Teacher.Uploads;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;

public class TeacherForm2Uploads extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form2_uploads); // Layout with a button

        Button uploadButton = findViewById(R.id.uploadButton); // Ensure you have this ID in your XML
        uploadButton.setOnClickListener(v -> {
            // Navigate to TeacherUploads Activity
            Intent intent = new Intent(this, TeacherUploads.class);
            startActivity(intent);
        });
    }
}
