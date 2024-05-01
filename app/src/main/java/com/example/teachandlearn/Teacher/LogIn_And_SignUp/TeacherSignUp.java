

package com.example.teachandlearn.Teacher.LogIn_And_SignUp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Teacher.SelectClass.TeacherSelectClass;

public class TeacherSignUp extends AppCompatActivity {

    private ImageButton buttonBack;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signup);

        // Initialize views
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonContinue = findViewById(R.id.buttonContinue);
        buttonBack = findViewById(R.id.back_button);

        // Set click listener for continue button
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupTeacher();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });

    }

    private void signupTeacher() {
        // Retrieve input values
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        // Perform validation (e.g., check if fields are not empty)
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate firstname and lastname (should contain only alphabetic characters)
        if (!firstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
            Toast.makeText(getApplicationContext(), "First name and Last name must contain only letters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email (must contain '@')
        if (!email.contains("@")) {
            Toast.makeText(getApplicationContext(), "Email must contain @ symbol", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate password (must match confirmPassword, contain at least one uppercase letter, one special character, and be at least 6 characters long)
        if (!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.matches(".*[A-Z].*") || !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*") || password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must contain at least one uppercase letter, one special character, and be at least 6 characters long", Toast.LENGTH_LONG).show();
            return;
        }

        // All validations passed, navigate to the next activity
        startActivity(new Intent(TeacherSignUp.this, TeacherSelectClass.class));

    }


    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }

}
