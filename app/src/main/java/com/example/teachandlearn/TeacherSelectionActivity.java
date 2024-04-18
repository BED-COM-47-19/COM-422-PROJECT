package com.example.teachandlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(0xFF6200EE);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        Button selectClassButton = new Button(this);
        selectClassButton.setText("SELECT A CLASS");
        selectClassButton.setTextColor(0xFF000000);
        selectClassButton.setBackgroundColor(0xFF6200EE);
        LinearLayout.LayoutParams selectClassParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        selectClassParams.gravity = Gravity.CENTER_HORIZONTAL;
        selectClassParams.topMargin = 100;
        selectClassButton.setLayoutParams(selectClassParams);
        rootLayout.addView(selectClassButton);

        Button selectFieldsButton = new Button(this);
        selectFieldsButton.setText("SELECT THE FIELDS YOU TEACH");
        selectFieldsButton.setTextColor(0xFF000000);
        selectFieldsButton.setBackgroundColor(0xFF6200EE);
        LinearLayout.LayoutParams selectFieldsParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        selectFieldsParams.gravity = Gravity.CENTER_HORIZONTAL;
        selectFieldsParams.topMargin = 20;
        selectFieldsButton.setLayoutParams(selectFieldsParams);
        rootLayout.addView(selectFieldsButton);

        setContentView(rootLayout);

        selectClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSelectionActivity.this, SelectClassActivity.class));
            }
        });

        selectFieldsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSelectionActivity.this, SelectFieldsActivity.class));
            }
        });
    }
}
