
package com.example.teachandlearn.Teacher.LogIn_And_SignUp;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.tasks.Task;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class TeacherLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        mAuth = FirebaseAuth.getInstance();
        configureGoogleSignIn();


        EditText emailField = findViewById(R.id.editTextEmail);
        EditText passwordField = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogIn);
        Button signUpButton = findViewById(R.id.buttonSignIn);
        TextView forgotPasswordTextView = findViewById(R.id.textViewForgotPassword);
        TextView googleSignInTextView = findViewById(R.id.textViewContinueWithGoogle);

        loginButton.setOnClickListener(v -> performLogin(emailField.getText().toString(), passwordField.getText().toString()));
        signUpButton.setOnClickListener(v -> startActivity(new Intent(this, TeacherSignUp.class)));
        forgotPasswordTextView.setOnClickListener(v -> startActivity(new Intent(this, TeacherPasswordReset.class)));
        googleSignInTextView.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
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
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(this, "Google sign-in failed: " + e.getStatusCode(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(TeacherLogin.this, TeacherSelection.class));
                    } else {
                        Toast.makeText(TeacherLogin.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
