package com.example.teachandlearn.Teacher.LogIn_And_SignUp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherPasswordReset extends AppCompatActivity {

    private EditText editTextEmail;
    private Button sendCodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_password_reset);

        // Initialize views
        editTextEmail = findViewById(R.id.editTextEmail);
        sendCodeButton = findViewById(R.id.buttonSendCode);

        // Set OnClickListener for "Send Code" Button
        sendCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email entered by the user
                String email = editTextEmail.getText().toString();

                // Generate a random 6-digit code
                int min = 100000; // Minimum 6-digit number
                int max = 999999; // Maximum 6-digit number
                int code = min + (int) (Math.random() * ((max - min) + 1));

                // Display the code as a toast message
                Toast.makeText(TeacherPasswordReset.this, "Code sent to your email: " + code, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
