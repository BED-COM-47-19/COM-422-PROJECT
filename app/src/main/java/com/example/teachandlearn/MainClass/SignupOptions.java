package com.example.teachandlearn.MainClass;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.LogIn_And_SignUp.StudentSignUp;
import com.example.teachandlearn.Teacher.LogIn_And_SignUp.TeacherSignUp;

public class SignupOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_password_reset);

        Button studentButton = findViewById(R.id.buttonStudent);
        Button teacherButton = findViewById(R.id.buttonTeacher);

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle student button click
                // For example, navigate to student signup activity
                startActivity(new Intent(SignupOptions.this, StudentSignUp.class));
            }
        });

        teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle teacher button click
                // For example, navigate to teacher signup activity
                startActivity(new Intent(SignupOptions.this, TeacherSignUp.class));
            }
        });
    }
}
