package com.example.teachandlearn.Teacher.LogIn_And_SignUp;

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

public class TeacherLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        // Initialize Firebase Auth and configure Google Sign-In
        mAuth = FirebaseAuth.getInstance();
        configureGoogleSignIn();

        EditText emailField = findViewById(R.id.editTextEmail);
        EditText passwordField = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.login_button);
        Button signUpButton = findViewById(R.id.sign_up_button);
        TextView forgotPasswordTextView = findViewById(R.id.forgotPassword);
        TextView googleSignInTextView = findViewById(R.id.google_sign_in);

        loginButton.setOnClickListener(v -> performLogin(emailField.getText().toString(), passwordField.getText().toString()));
        googleSignInTextView.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });

        // Implement other listeners for signUpButton and forgotPasswordTextView...
    }

    private void configureGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void performLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(TeacherLogin.this, TeacherSelection.class));
                    } else {
                        Toast.makeText(TeacherLogin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                // Google Sign-In was successful, authenticate with Firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign-In failed, update UI appropriately
                Log.w("Google Sign-In", "Google sign-in failed", e);
                Toast.makeText(this, "Google sign-in failed: " + e.getStatusCode(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("Google Sign-In", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Google Sign-In", "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(TeacherLogin.this, TeacherSelection.class));
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Google Sign-In", "signInWithCredential:failure", task.getException());
                        Toast.makeText(TeacherLogin.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

