
package com.example.teachandlearn.Student.LogIn_And_SignUp;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class StudentLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login); // Consider updating this layout name if it's incorrect for this context

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Build the UI dynamically
        LinearLayout rootLayout = buildDynamicUI();

        // Add listeners for login, sign up, password reset, and Google sign-in
        setupListeners(rootLayout);

        // Set Content View to the root layout
        setContentView(rootLayout);
    }

    private LinearLayout buildDynamicUI() {
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(0xFF6200EE);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // Add UI components
        rootLayout.addView(createTextView("STUDENT", 100));
        rootLayout.addView(createEditText("Email", 20));
        rootLayout.addView(createEditText("Password", 20));
        rootLayout.addView(createButton("LOG IN", 20));
        rootLayout.addView(createButton("SIGN UP", 30));
        rootLayout.addView(createTextView("Forgot Password?", 20));
        rootLayout.addView(createTextView("Continue with Google", 30));

        return rootLayout;
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
        signUpButton.setOnClickListener(v -> startActivity(new Intent(StudentLogin.this, StudentSignup.class)));
        forgotPasswordTextView.setOnClickListener(v -> startActivity(new Intent(StudentLogin.this, StudentPasswordReset.class)));
        googleSignInTextView.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }


    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(StudentLogin.this, StudentSelectClass.class));
                    } else {
                        Toast.makeText(StudentLogin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
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


}
