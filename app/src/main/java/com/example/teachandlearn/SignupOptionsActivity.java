

package com.example.teachandlearn;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class SignupOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_options);

        Button studentButton = findViewById(R.id.buttonStudent);
        Button teacherButton = findViewById(R.id.buttonTeacher);

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle student button click
                // For example, navigate to student signup activity
                startActivity(new Intent(SignupOptionsActivity.this, StudentSignupActivity.class));
            }
        });

        teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle teacher button click
                // For example, navigate to teacher signup activity
                startActivity(new Intent(SignupOptionsActivity.this, TeacherSignupActivity.class));
            }
        });
    }
}
