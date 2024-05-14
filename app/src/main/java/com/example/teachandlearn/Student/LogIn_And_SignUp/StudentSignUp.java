

package com.example.teachandlearn.Student.LogIn_And_SignUp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.SelectClass.StudentSelectClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class StudentSignUp extends AppCompatActivity {

    private DatabaseReference databaseReference; // Firebase database reference
<<<<<<< HEAD
    private Button buttonBack;
=======
<<<<<<< HEAD
    private Button buttonBack;
=======
    private ImageButton buttonBack;
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        // Initialize views
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonContinue = findViewById(R.id.buttonContinue);
<<<<<<< HEAD

=======
<<<<<<< HEAD

=======
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
        buttonBack = findViewById(R.id.back_button);

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupStudent();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void signupStudent() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        boolean isValid = true;

        if (firstName.isEmpty()) {
            editTextFirstName.setError("First name is required");
            isValid = false;
        } else if (!firstName.matches("[a-zA-Z]+")) {
            editTextFirstName.setError("First name must contain only letters");
            isValid = false;
        }

        if (lastName.isEmpty()) {
            editTextLastName.setError("Last name is required");
            isValid = false;
        } else if (!lastName.matches("[a-zA-Z]+")) {
            editTextLastName.setError("Last name must contain only letters");
            isValid = false;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            isValid = false;
        } else if (!email.contains("@")) {
            editTextEmail.setError("Email must contain an '@' symbol");
            isValid = false;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            isValid = false;
        } else if (password.length() < 7) {
            editTextPassword.setError("Password must be at least 6 characters long");
            isValid = false;
        }

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError("Confirm Password is required");
            isValid = false;
        } else if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Passwords do not match");
            isValid = false;
        }

        if (!isValid) {
            return;
        }

        String userId = databaseReference.push().getKey();
        User user = new User(firstName, lastName, email, password, confirmPassword);

        // Attempt to save user and navigate
        databaseReference.child(userId).setValue(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "User registration successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StudentSignUp.this, StudentSelectClass.class));
            } else {
                Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
