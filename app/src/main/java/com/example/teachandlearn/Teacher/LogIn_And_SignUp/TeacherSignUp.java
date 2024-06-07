

package com.example.teachandlearn.Teacher.LogIn_And_SignUp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import androidx.annotation.NonNull;
import com.example.teachandlearn.Teacher.SelectClass.TeacherSelectClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class TeacherSignUp extends AppCompatActivity {

    private DatabaseReference databaseReference; // Firebase database reference
    private FirebaseAuth mAuth; // Firebase database reference
    private Button buttonBack;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signup);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonContinue = findViewById(R.id.buttonContinue);

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

        class User {
            public String firstName, lastName, email, password;

            public User() {
                // Default constructor required for calls to DataSnapshot.getValue(User.class)
            }

            public User(String firstName, String lastName, String email, String password) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.password = password;
            }

        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registration successful, save user data in the Realtime Database
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String userId = firebaseUser.getUid();
                        User user = new User(firstName, lastName, email, password);

                        databaseReference.child(userId).setValue(user).addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "User registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(TeacherSignUp.this, TeacherSelectClass.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(),   task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
