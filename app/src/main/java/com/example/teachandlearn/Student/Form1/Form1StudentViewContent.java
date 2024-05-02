

package com.example.teachandlearn.Student.Form1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Teacher.Form1.TeacherForm1Uploads;

public class Form1StudentViewContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_view_content);

        // Assume classId is fetched from student's profile or login session
        String classId = getClassIdForStudent();

        // Find buttons and set up click listeners for PDF, Audio, Videos, and Questions

        Button buttonPDF = findViewById(R.id.button_pdf);
        Button buttonAudio = findViewById(R.id.button_audio);
        Button buttonVideos = findViewById(R.id.button_videos);
        Button buttonQuestions = findViewById(R.id.button_tests_quizzes);

        setupButton(R.id.button_pdf, "pdf", classId);
        setupButton(R.id.button_audio, "audio", classId);
        setupButton(R.id.button_videos, "video", classId);
        setupButton(R.id.button_tests_quizzes, "question", classId);
    }

    private String getClassIdForStudent() {
        // This method would actually fetch from a secure source like SharedPreferences or a database
        // Here, it's hardcoded for demonstration purposes
        return "form1";
    }

    private void setupButton(int buttonId, String contentType, String classId) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> navigateToTeacherUploads(contentType, classId));
    }

    // Helper method to navigate to TeacherUploadsActivity with specific content type and class
    private void navigateToTeacherUploads(String contentType, String classId) {
        Intent intent = new Intent(Form1StudentViewContent.this, TeacherForm1Uploads.class);
        intent.putExtra("content_type", contentType);
        intent.putExtra("classId", classId);
        intent.putExtra("action", "view");
        startActivity(intent);
    }
}
