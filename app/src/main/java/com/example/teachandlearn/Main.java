package com.example.teachandlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a LinearLayout as the root layout
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(0xFF116AFD); // Purple background color
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        rootLayout.setGravity(Gravity.CENTER); // Center its children horizontally and vertically

        // Create a TextView for the heading
        TextView headingTextView = new TextView(this);
        headingTextView.setText("SECONDARY EDUCATION");
        headingTextView.setTextSize(24);
        headingTextView.setTextColor(0xFFFFFFFF); // White text color
        headingTextView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams headingParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        headingParams.gravity = Gravity.CENTER; // Center horizontally within the LinearLayout
        headingParams.topMargin = 100; // Adjust top margin
        headingTextView.setLayoutParams(headingParams);
        rootLayout.addView(headingTextView);

        // Create a Button for "GET STARTED"
        Button getStartedButton = new Button(this);
        getStartedButton.setText("GET STARTED");
        getStartedButton.setTextColor(0xFF000000); // Black text color
        getStartedButton.setBackgroundColor(0xFF6200EE); // Purple background color
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        buttonParams.gravity = Gravity.CENTER; // Center horizontally within the LinearLayout
        buttonParams.topMargin = 100; // Adjust top margin
        getStartedButton.setLayoutParams(buttonParams);
        rootLayout.addView(getStartedButton);

        // Set OnClickListener to the "GET STARTED" button
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start UserTypeSelectionActivity
                startActivity(new Intent(Main.this, UserTypeSelection.class));
            }
        });


        setContentView(rootLayout);

    }

}
