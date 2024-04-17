
package com.example.teachandlearn;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.TeacherLoginActivity;
import com.example.teachandlearn.TeacherPasswordResetActivity;

public class TeacherLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a LinearLayout as the root layout
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(0xFF6200EE); // Purple background color
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        // Create TextView for "TEACHER"
        TextView teacherTextView = new TextView(this);
        teacherTextView.setText("TEACHER");
        teacherTextView.setTextColor(0xFFFFFFFF); // White text color
        teacherTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams teacherParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        teacherParams.topMargin = 100; // Adjust top margin
        teacherTextView.setLayoutParams(teacherParams);
        rootLayout.addView(teacherTextView);

        // Create EditText for Email
        EditText editTextEmail = new EditText(this);
        editTextEmail.setHint("Email");
        editTextEmail.setTextColor(0xFF000000); // Black text color
        editTextEmail.setBackgroundColor(0xFFFFFFFF); // White background color
        LinearLayout.LayoutParams emailParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        emailParams.topMargin = 20; // Adjust top margin
        editTextEmail.setLayoutParams(emailParams);
        rootLayout.addView(editTextEmail);

        // Create EditText for Password
        EditText editTextPassword = new EditText(this);
        editTextPassword.setHint("Password");
        editTextPassword.setTextColor(0xFF000000); // Black text color
        editTextPassword.setBackgroundColor(0xFFFFFFFF); // White background color
        LinearLayout.LayoutParams passwordParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        passwordParams.topMargin = 20; // Adjust top margin
        editTextPassword.setLayoutParams(passwordParams);
        rootLayout.addView(editTextPassword);

        // Create "Log In" Button
        Button loginButton = new Button(this);
        loginButton.setText("Log In");
        loginButton.setTextColor(0xFFFFFFFF); // White text color
        loginButton.setBackgroundColor(0xFF6200EE); // Purple background color
        LinearLayout.LayoutParams loginParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        loginParams.gravity = Gravity.CENTER_HORIZONTAL;
        loginParams.topMargin = 20; // Adjust top margin
        loginButton.setLayoutParams(loginParams);
        rootLayout.addView(loginButton);

        // Create "Forgot Password?" TextView
        TextView forgotPasswordTextView = new TextView(this);
        forgotPasswordTextView.setText("Forgot Password?");
        forgotPasswordTextView.setTextColor(0xFFFFFFFF); // White text color
        forgotPasswordTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams forgotPasswordParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        forgotPasswordParams.topMargin = 20; // Adjust top margin
        forgotPasswordTextView.setLayoutParams(forgotPasswordParams);
        rootLayout.addView(forgotPasswordTextView);

        // Set OnClickListener for "Log In" Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login operation
                // Here, you can add your logic for authenticating the user

                // For example, you can display a toast message
                Toast.makeText(TeacherLoginActivity.this, "Logged in as Teacher", Toast.LENGTH_SHORT).show();
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the PasswordResetActivity to initiate the password reset process
                startActivity(new Intent(TeacherLoginActivity.this, TeacherPasswordResetActivity.class));
            }
        });

        // Set Content View to the root layout
        setContentView(rootLayout);

        // Set Content View to the root layout
        setContentView(rootLayout);
    }
}
