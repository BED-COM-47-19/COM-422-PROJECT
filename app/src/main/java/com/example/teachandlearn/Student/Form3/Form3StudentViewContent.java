
package com.example.teachandlearn.Student.Form3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Teacher.Form3.TeacherForm3Uploads;


public class Form3StudentViewContent extends AppCompatActivity {

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
                navigateToTeacherUploads("pdf");
            }
        });

        buttonAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToTeacherUploads("audio");
            }
        });

        buttonVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToTeacherUploads("video");
            }
        });

        buttonQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToTeacherUploads("question");
            }
        });
    }

    // Helper method to navigate to TeacherUploadsActivity
    private void navigateToTeacherUploads(String contentType) {
        Intent intent = new Intent(Form3StudentViewContent.this, TeacherForm3Uploads.class);
        intent.putExtra("content_type", contentType);
        intent.putExtra("action", "view");  // This specifies that the student wants to view content
        startActivity(intent);
    }

}