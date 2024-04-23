

package com.example.teachandlearn.Teacher.Uploads;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;

public class TeacherForm3Uploads extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form1_uploads); // Make sure to create this layout file

        Button uploadButton = findViewById(R.id.uploadButton); // Button ID in the layout file
        uploadButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TeacherUploads.class);
            startActivity(intent);
        });
    }
}
