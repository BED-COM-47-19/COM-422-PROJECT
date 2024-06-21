package com.example.teachandlearn.Student.Form1.Categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Documents.Bible_Knowledge.Form1StudentViewContentBible_Knowledge;
import com.example.teachandlearn.Student.Form1.Documents.Geography.Form1StudentViewContentGeography;
import com.example.teachandlearn.Student.Form1.Documents.Life_Skills.Form1StudentViewContentLife_Skills;
import com.example.teachandlearn.Student.Form1.Documents.Social_Studies.Form1StudentViewContentSocial_Studies;
import com.example.teachandlearn.Student.Form1.Documents.History.Form1StudentViewContentHistory;

public class Form1HumanitiesStudent extends AppCompatActivity {
    private Button buttonBack;

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

        // Find small info buttons
        Button buttonSmallSocialStudies = findViewById(R.id.buttonSmallSocialStudies);
        Button buttonSmallLifeSkills = findViewById(R.id.buttonSmallLifeSkills);
        Button buttonSmallHistory = findViewById(R.id.buttonSmallHistory);
        Button buttonSmallBibleKnowledge = findViewById(R.id.buttonSmallBibleKnowledge);
        Button buttonSmallGeography = findViewById(R.id.buttonSmallGeography);

        buttonBack = findViewById(R.id.back_button);
        buttonBack.setOnClickListener(view -> onBackPressed());

        // Set click listeners for each button to open respective activities
        buttonSocialStudies.setOnClickListener(v -> startActivityForContent(Form1StudentViewContentSocial_Studies.class));
        buttonLifeSkills.setOnClickListener(v -> startActivityForContent(Form1StudentViewContentLife_Skills.class));
        buttonHistory.setOnClickListener(v -> startActivityForContent(Form1StudentViewContentHistory.class));
        buttonBibleKnowledge.setOnClickListener(v -> startActivityForContent(Form1StudentViewContentBible_Knowledge.class));
        buttonGeography.setOnClickListener(v -> startActivityForContent(Form1StudentViewContentGeography.class));

        // Set click listeners for small info buttons to show popups instead of toasts
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
        popupWindow.showAtLocation(anchor, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        // Optionally adjust the position of the popup
        popupWindow.update(anchor, 0, 100, -1, -1);  // Shift a bit downwards
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(Form1HumanitiesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity based on selected subject
    private void startActivityForContent(Class<?> cls) {
        Intent intent = new Intent(Form1HumanitiesStudent.this, cls);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
}
