


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
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.SelectClass.StudentSelectClass;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;


public class StudentLogIn extends AppCompatActivity {

    private Button buttonBack;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private DatabaseReference mDatabase;
    private static final int RC_SIGN_IN = 9001;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sharedPreferences = getSharedPreferences("user_credentials", MODE_PRIVATE);
        mDatabase.keepSynced(true);

        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogIn);
        Button signUpButton = findViewById(R.id.buttonSignIn);
        TextView forgotPasswordTextView = findViewById(R.id.textViewForgotPassword);
        TextView googleSignInTextView = findViewById(R.id.textViewContinueWithGoogle);

        buttonBack = findViewById(R.id.back_button);

        buttonBack.setOnClickListener(view -> onBackPressed());
        loginButton.setOnClickListener(v -> loginUser(editTextEmail.getText().toString(), editTextPassword.getText().toString()));
        signUpButton.setOnClickListener(v -> startActivity(new Intent(StudentLogIn.this, StudentSignUp.class)));
        forgotPasswordTextView.setOnClickListener(v -> sendPasswordResetEmail(editTextEmail.getText().toString()));
        googleSignInTextView.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        buttonBack.setOnClickListener(view -> onBackPressed());

        loginButton.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            loginUser(email, password);
            logAttemptToDatabase(email);
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
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(StudentLogIn.this, "Email and password cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isNetworkAvailable()) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            saveCredentials(email, password);
                            startActivity(new Intent(StudentLogIn.this, StudentSelectClass.class));
                        } else {
                            Toast.makeText(StudentLogIn.this, "Email or Password Incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            if (checkCredentials(email, password)) {
                startActivity(new Intent(StudentLogIn.this, StudentSelectClass.class));
            } else {
                Toast.makeText(StudentLogIn.this, "Invalid credentials or no network available.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void saveCredentials(String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    private boolean checkCredentials(String email, String password) {
        String savedEmail = sharedPreferences.getString("email", null);
        String savedPassword = sharedPreferences.getString("password", null);
        return email.equals(savedEmail) && password.equals(savedPassword);
    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w("GoogleSignIn", "Google sign in failed", e);
                Toast.makeText(StudentLogIn.this, "Google sign-in failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        // Proceed with your app logic, for example, navigate to a new activity
                        startActivity(new Intent(StudentLogIn.this, StudentSelectClass.class));
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("FirebaseAuth", "signInWithCredential:failure", task.getException());
                        Toast.makeText(StudentLogIn.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
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











