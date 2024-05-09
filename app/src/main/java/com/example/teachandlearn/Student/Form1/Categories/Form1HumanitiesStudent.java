package com.example.teachandlearn.Student.Form1.Categories;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;

public class Form1HumanitiesStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_humanities);

        // Find buttons for each humanities subject
        Button buttonSocialStudies = findViewById(R.id.buttonSocialStudies);
        Button buttonLifeSkills = findViewById(R.id.buttonLifeSkills);
        Button buttonHistory = findViewById(R.id.buttonHistory);
        Button buttonBibleKnowledge = findViewById(R.id.buttonBibleKnowledge);
        Button buttonGeography = findViewById(R.id.buttonGeography);

        Button buttonSmallSocialStudies = findViewById(R.id.buttonSmallSocialStudies);
        Button buttonSmallLifeSkills = findViewById(R.id.buttonSmallLifeSkills);
        Button buttonSmallHistory = findViewById(R.id.buttonSmallHistory);
        Button buttonSmallBibleKnowledge = findViewById(R.id.buttonSmallBibleKnowledge);
        Button buttonSmallGeography = findViewById(R.id.buttonSmallGeography);

        // Set click listeners for each button
        buttonSocialStudies.setOnClickListener(v -> {
            showToast("Social Studies Selected");
            startActivityForContent();
        });

        buttonLifeSkills.setOnClickListener(v -> {
            showToast("Life Skills Selected");
            startActivityForContent();
        });

        buttonHistory.setOnClickListener(v -> {
            showToast("History Selected");
            startActivityForContent();
        });

        buttonBibleKnowledge.setOnClickListener(v -> {
            showToast("Bible Knowledge Selected");
            startActivityForContent();
        });

        buttonGeography.setOnClickListener(v -> {
            showToast("Geography Selected");
            startActivityForContent();
        });

        // Set click listeners for small info buttons
        buttonSmallSocialStudies.setOnClickListener(v -> showPopup(v, getString(R.string.info_social_studies)));
        buttonSmallLifeSkills.setOnClickListener(v -> showPopup(v, getString(R.string.info_life_skills)));
        buttonSmallHistory.setOnClickListener(v -> showPopup(v, getString(R.string.info_history)));
        buttonSmallBibleKnowledge.setOnClickListener(v -> showPopup(v, getString(R.string.info_bible_knowledge)));
        buttonSmallGeography.setOnClickListener(v -> showPopup(v, getString(R.string.info_geography)));
    }

    private void showPopup(View anchor, String text) {
        // Inflate the popup layout
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_info, null);
        TextView textView = popupView.findViewById(R.id.textViewPopupInfo);
        textView.setText(text);

        // Create the popup window
        PopupWindow popupWindow = new PopupWindow(popupView,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                true);  // True means the popup is focusable

        // Show the popup at the top center of the anchor view
        popupWindow.showAtLocation(anchor, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
        // Optionally adjust the position of the popup
        popupWindow.update(anchor, 0, 100, -1, -1);  // Shift a bit downwards
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(Form1HumanitiesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity
    private void startActivityForContent() {
        Intent intent = new Intent(Form1HumanitiesStudent.this, Form1StudentViewContent.class);
        startActivity(intent);
    }
}