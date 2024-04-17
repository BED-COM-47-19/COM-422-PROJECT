package com.example.teachandlearn;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class  SignUpActivity extends AppCompatActivity {

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

        // Create EditText for First Name
        EditText editTextFirstName = new EditText(this);
        editTextFirstName.setId(View.generateViewId());
        editTextFirstName.setHint("First name");
        editTextFirstName.setTextColor(0xFF000000); // Black text color
        editTextFirstName.setBackgroundColor(0xFFFFFFFF); // White background color
        LinearLayout.LayoutParams firstNameParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        firstNameParams.gravity = Gravity.CENTER_HORIZONTAL;
        firstNameParams.topMargin = 100; // Adjust top margin
        editTextFirstName.setLayoutParams(firstNameParams);
        rootLayout.addView(editTextFirstName);

        // Create EditText for Last Name
        EditText editTextLastName = new EditText(this);
        editTextLastName.setId(View.generateViewId());
        editTextLastName.setHint("Last name");
        editTextLastName.setTextColor(0xFF000000); // Black text color
        editTextLastName.setBackgroundColor(0xFFFFFFFF); // White background color
        LinearLayout.LayoutParams lastNameParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lastNameParams.gravity = Gravity.CENTER_HORIZONTAL;
        editTextLastName.setLayoutParams(lastNameParams);
        rootLayout.addView(editTextLastName);

        // Create EditText for Email
        EditText editTextEmail = new EditText(this);
        editTextEmail.setId(View.generateViewId());
        editTextEmail.setHint("Email");
        editTextEmail.setTextColor(0xFF000000); // Black text color
        editTextEmail.setBackgroundColor(0xFFFFFFFF); // White background color
        LinearLayout.LayoutParams emailParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        emailParams.gravity = Gravity.CENTER_HORIZONTAL;
        editTextEmail.setLayoutParams(emailParams);
        rootLayout.addView(editTextEmail);

        // Create EditText for Password
        EditText editTextPassword = new EditText(this);
        editTextPassword.setId(View.generateViewId());
        editTextPassword.setHint("Password");
        editTextPassword.setTextColor(0xFF000000); // Black text color
        editTextPassword.setBackgroundColor(0xFFFFFFFF); // White background color
        LinearLayout.LayoutParams passwordParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        passwordParams.gravity = Gravity.CENTER_HORIZONTAL;
        editTextPassword.setLayoutParams(passwordParams);
        rootLayout.addView(editTextPassword);

        // Create EditText for Confirm Password
        EditText editTextConfirmPassword = new EditText(this);
        editTextConfirmPassword.setId(View.generateViewId());
        editTextConfirmPassword.setHint("Confirm Password");
        editTextConfirmPassword.setTextColor(0xFF000000); // Black text color
        editTextConfirmPassword.setBackgroundColor(0xFFFFFFFF); // White background color
        LinearLayout.LayoutParams confirmPasswordParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        confirmPasswordParams.gravity = Gravity.CENTER_HORIZONTAL;
        editTextConfirmPassword.setLayoutParams(confirmPasswordParams);
        rootLayout.addView(editTextConfirmPassword);

        // Create Continue Button
        Button buttonContinue = new Button(this);
        buttonContinue.setText("CONTINUE");
        buttonContinue.setTextColor(0xFF000000); // Black text color
        buttonContinue.setBackgroundColor(0xFF6200EE); // Purple background color
        LinearLayout.LayoutParams continueParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        continueParams.gravity = Gravity.CENTER_HORIZONTAL;
        continueParams.topMargin = 50; // Adjust top margin
        buttonContinue.setLayoutParams(continueParams);
        rootLayout.addView(buttonContinue);

        // Set Content View to the root layout
        setContentView(rootLayout);

        // Set OnClickListener for Continue button
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String firstName = editTextFirstName.getText().toString().trim();
                String lastName = editTextLastName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                // Validate input
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }


                Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();


            }
        });

    }

}
