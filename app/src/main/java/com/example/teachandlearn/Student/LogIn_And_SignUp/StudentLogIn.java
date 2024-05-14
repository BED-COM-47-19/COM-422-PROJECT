
package com.example.teachandlearn.Student.LogIn_And_SignUp;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.SelectClass.StudentSelectClass;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Map;
import java.util.HashMap;
import com.google.firebase.database.ServerValue;
import android.util.Log;




public class StudentLogIn extends AppCompatActivity {

<<<<<<< HEAD
    private Button buttonBack;
=======
<<<<<<< HEAD
    private Button buttonBack;
=======
    private ImageButton buttonBack;
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private DatabaseReference mDatabase;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
        ImageButton buttonBack = findViewById(R.id.back_button);
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogIn);
        Button signUpButton = findViewById(R.id.buttonSignIn);
        TextView forgotPasswordTextView = findViewById(R.id.textViewForgotPassword);
        TextView googleSignInTextView = findViewById(R.id.textViewContinueWithGoogle);

<<<<<<< HEAD
        buttonBack = findViewById(R.id.back_button); // Initialize buttonBack

// Set listeners
=======
<<<<<<< HEAD
        buttonBack = findViewById(R.id.back_button); // Initialize buttonBack

// Set listeners
=======
        // Set listeners
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
        buttonBack.setOnClickListener(view -> onBackPressed());
        loginButton.setOnClickListener(v -> loginUser(editTextEmail.getText().toString(), editTextPassword.getText().toString()));
        signUpButton.setOnClickListener(v -> startActivity(new Intent(StudentLogIn.this, StudentSignUp.class)));
        forgotPasswordTextView.setOnClickListener(v -> sendPasswordResetEmail(editTextEmail.getText().toString()));
        googleSignInTextView.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
<<<<<<< HEAD
=======
<<<<<<< HEAD

        // Initialize Firebase Auth
        buttonBack = findViewById(R.id.back_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Your login button click logic here
            }
        });

        // In

=======
        // Initialize Firebase Auth
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

        // Initialize Firebase Auth
        buttonBack = findViewById(R.id.back_button);

<<<<<<< HEAD
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Your login button click logic here
            }
        });

        // In

=======
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });


        loginButton.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            loginUser(email, password);
            logAttemptToDatabase(email);  // Log the attempt to Firebase Database
        });

    }



    private TextView createTextView(String text, int topMargin) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextColor(0xFFFFFFFF);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = topMargin;
        textView.setLayoutParams(params);
        return textView;
    }

    private EditText createEditText(String hint, int topMargin) {
        EditText editText = new EditText(this);
        editText.setHint(hint);
        editText.setTextColor(0xFF000000);
        editText.setBackgroundColor(0xFFFFFFFF);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = topMargin;
        editText.setLayoutParams(params);
        return editText;
    }

    private Button createButton(String text, int topMargin) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextColor(0xFFFFFFFF);
        button.setBackgroundColor(0xFF6200EE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = topMargin;
        button.setLayoutParams(params);
        return button;
    }

    private void setupListeners(LinearLayout rootLayout) {
        EditText editTextEmail = (EditText) rootLayout.getChildAt(1);
        EditText editTextPassword = (EditText) rootLayout.getChildAt(2);
        Button loginButton = (Button) rootLayout.getChildAt(3);
        Button signUpButton = (Button) rootLayout.getChildAt(4);
        TextView forgotPasswordTextView = (TextView) rootLayout.getChildAt(5);
        TextView googleSignInTextView = (TextView) rootLayout.getChildAt(6);

        loginButton.setOnClickListener(v -> loginUser(editTextEmail.getText().toString(), editTextPassword.getText().toString()));
        signUpButton.setOnClickListener(v -> startActivity(new Intent(StudentLogIn.this, StudentSignUp.class)));
        forgotPasswordTextView.setOnClickListener(v -> sendPasswordResetEmail(editTextEmail.getText().toString()));
        googleSignInTextView.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    private void sendPasswordResetEmail(String email) {
        if (!email.isEmpty()) {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(StudentLogIn.this, "Reset link sent to your email", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(StudentLogIn.this, "Failed to send reset email", Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(StudentLogIn.this, "Please enter your email", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginUser(String email, String password) {
        // Check if email or password is empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(StudentLogIn.this, "Email and password cannot be empty.", Toast.LENGTH_SHORT).show();
            return; // Stop the login process if fields are empty
        }

        // Proceed with Firebase authentication
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // If login is successful, navigate to the next screen
                        startActivity(new Intent(StudentLogIn.this, StudentSelectClass.class));
                    } else {
                        // If login fails, display a failure message
                        Toast.makeText(StudentLogIn.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignIn.getSignedInAccountFromIntent(data)
                    .addOnSuccessListener(googleSignInAccount -> {
                        // Firebase Google Auth
                        // Use googleSignInAccount to authenticate with Firebase
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Google sign-in failed.", Toast.LENGTH_SHORT).show());
        }
    }



    private void logAttemptToDatabase(String email) {
        // Create a unique ID for each log entry
        String key = mDatabase.child("logins").push().getKey();
        Map<String, Object> log = new HashMap<>();
        log.put("email", email);
        log.put("timestamp", ServerValue.TIMESTAMP);  // Firebase server timestamp

        // Save the log entry to the database
        mDatabase.child("logins").child(key).setValue(log)
                .addOnSuccessListener(aVoid -> Log.d("Database", "Log saved successfully"))
                .addOnFailureListener(e -> Log.d("Database", "Error saving log", e));
    }



    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }


}
