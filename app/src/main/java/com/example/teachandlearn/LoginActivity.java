package com.example.teachandlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

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

        // Create a Button for "Log In"
        Button loginButton = new Button(this);
        loginButton.setText("Log In");
        loginButton.setTextColor(0xFF000000); // Black text color
        loginButton.setBackgroundColor(0xFF6200EE); // Purple background color
        LinearLayout.LayoutParams loginParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        loginParams.gravity = Gravity.CENTER;
        loginParams.topMargin = 100; // Adjust top margin
        loginButton.setLayoutParams(loginParams);
        rootLayout.addView(loginButton);

        // Set OnClickListener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start UserTypeSelectionActivity when login button is clicked
                startActivity(new Intent(LoginActivity.this, UserTypeSelectionActivity.class));
            }
        });

        setContentView(rootLayout);
    }

}
