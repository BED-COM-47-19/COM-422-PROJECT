package com.example.teachandlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class UserOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);

        // Find login and signup buttons
        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = findViewById(R.id.signUpButton);

        // Set OnClickListener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity
                startActivity(new Intent(UserOptionsActivity.this, LoginActivity.class));
            }
        });

        // Set OnClickListener for signup button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SignUpActivity
                startActivity(new Intent(UserOptionsActivity.this, SignUpActivity.class));
            }

        });

        // Set background color to blue
        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        rootLayout.setBackgroundColor(0xFF6200EE); // Blue color

        // Set gravity for root layout to center the login button
        rootLayout.setGravity(Gravity.CENTER);

        // Set layout params for signup button to align top right
        LinearLayout.LayoutParams signUpParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        signUpParams.gravity = Gravity.TOP | Gravity.END; // Align to top-right corner
        signUpButton.setLayoutParams(signUpParams);
        
    }

}
