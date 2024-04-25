
package com.example.teachandlearn.Student.Form1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;

public class Form1StudentViewContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_view_content);

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


    private void navigateToTeacherUploads(String contentType) {
        Intent intent = new Intent(Form1StudentViewContent.this, TeacherForm1Uploads.class);
        intent.putExtra("content_type", contentType);
        intent.putExtra("action", "view");  // This specifies that the student wants to view content
        startActivity(intent);
    }

}