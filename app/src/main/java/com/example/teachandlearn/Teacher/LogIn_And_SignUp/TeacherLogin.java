
package com.example.teachandlearn.Teacher.LogIn_And_SignUp;
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
import com.example.teachandlearn.Teacher.SelectClass.TeacherSelectClass;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;


public class TeacherLogin extends AppCompatActivity {

    private ImageButton buttonBack;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login); // Consider updating this layout name if it's incorrect for this context


        ImageButton buttonBack = findViewById(R.id.back_button);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogIn);
        Button signUpButton = findViewById(R.id.buttonSignIn);
        TextView forgotPasswordTextView = findViewById(R.id.textViewForgotPassword);
        TextView googleSignInTextView = findViewById(R.id.textViewContinueWithGoogle);

        // Set listeners
        buttonBack.setOnClickListener(view -> onBackPressed());
        loginButton.setOnClickListener(v -> loginUser(editTextEmail.getText().toString(), editTextPassword.getText().toString()));
        signUpButton.setOnClickListener(v -> startActivity(new Intent(TeacherLogin.this, TeacherSignUp.class)));
        forgotPasswordTextView.setOnClickListener(v -> sendPasswordResetEmail(editTextEmail.getText().toString()));
        googleSignInTextView.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Build the UI dynamically


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
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
        signUpButton.setOnClickListener(v -> startActivity(new Intent(TeacherLogin.this, TeacherSignUp.class)));
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
                            Toast.makeText(TeacherLogin.this, "Reset link sent to your email", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(TeacherLogin.this, "Failed to send reset email", Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(TeacherLogin.this, "Please enter your email", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginUser(String email, String password) {
        // Check if email or password is empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(TeacherLogin.this, "Email and password cannot be empty.", Toast.LENGTH_SHORT).show();
            return; // Stop the login process if fields are empty
        }

        // Proceed with Firebase authentication
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // If login is successful, navigate to the next screen
                        startActivity(new Intent(TeacherLogin.this, TeacherSelectClass.class));
                    } else {
                        // If login fails, display a failure message
                        Toast.makeText(TeacherLogin.this, "Invalid account", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }



}
