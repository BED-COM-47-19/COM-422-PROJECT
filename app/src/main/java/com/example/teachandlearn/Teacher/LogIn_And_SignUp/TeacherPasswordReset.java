
package com.example.teachandlearn.Teacher.LogIn_And_SignUp;
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


public class TeacherPasswordReset extends AppCompatActivity {

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

        // Create TextView for "Reset Password"
        TextView resetPasswordTextView = new TextView(this);
        resetPasswordTextView.setText("Reset Password");
        resetPasswordTextView.setTextColor(0xFFFFFFFF); // White text color
        resetPasswordTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams resetPasswordParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        resetPasswordParams.topMargin = 100; // Adjust top margin
        resetPasswordTextView.setLayoutParams(resetPasswordParams);
        rootLayout.addView(resetPasswordTextView);

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

        // Create Button for "Send Code"
        Button sendCodeButton = new Button(this);
        sendCodeButton.setText("Send Code");
        sendCodeButton.setTextColor(0xFFFFFFFF); // White text color
        sendCodeButton.setBackgroundColor(0xFF6200EE); // Purple background color
        LinearLayout.LayoutParams sendCodeParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        sendCodeParams.gravity = Gravity.CENTER_HORIZONTAL;
        sendCodeParams.topMargin = 20; // Adjust top margin
        sendCodeButton.setLayoutParams(sendCodeParams);
        rootLayout.addView(sendCodeButton);

        // Set OnClickListener for "Send Code" Button
        sendCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email entered by the user
                String email = editTextEmail.getText().toString();

                // Generate a random 6-digit code
                int min = 100000; // Minimum 6-digit number
                int max = 999999; // Maximum 6-digit number
                int code = min + (int) (Math.random() * ((max - min) + 1));

                // Display the code as a toast message
                Toast.makeText(TeacherPasswordReset.this, "Code sent to your email: " + code, Toast.LENGTH_SHORT).show();
            }

        });

        // Set Content View to the root layout
        setContentView(rootLayout);
    }
}
