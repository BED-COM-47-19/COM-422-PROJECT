
package com.example.teachandlearn.Teacher.Uploads;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Teacher.Form1.TeacherForm1Languages;
import com.example.teachandlearn.Teacher.Form1.TeacherForm1Humanities;
import com.example.teachandlearn.Teacher.Form1.TeacherForm1Science;

public class TeacherForm1Uploads extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form1_uploads); // Layout with a button

        Button uploadButton = findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(v -> {
            // Navigate to TeacherUploads Activity
            Intent intent = new Intent(this, TeacherUploads.class);
            startActivity(intent);
        });
    }
}
