

package com.example.teachandlearn.Teacher.Uploads;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Teacher.Form3.TeacherForm3Science;
import com.example.teachandlearn.Teacher.Form3.TeacherForm3Languages;
import com.example.teachandlearn.Teacher.Form3.TeacherForm3Humanities;
public class TeacherForm3Uploads extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form3_uploads); // Layout with a button

        Button uploadButton = findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(v -> {
            // Navigate to TeacherUploads Activity
            Intent intent = new Intent(this, TeacherUploads.class);
            startActivity(intent);
        });
    }
}
