package com.example.teachandlearn.Student.Form2.Categories;

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
import com.example.teachandlearn.Student.Form2.Documents.Agriculture.Form2StudentViewContentAgriculture;
import com.example.teachandlearn.Student.Form2.Documents.Biology.Form2StudentViewContentBiology;
import com.example.teachandlearn.Student.Form2.Documents.Chemistry.Form2StudentViewContentChemistry;
import com.example.teachandlearn.Student.Form2.Documents.Mathematics.Form2StudentViewContentMathematics;
import com.example.teachandlearn.Student.Form2.Documents.Physics.Form2StudentViewContentPhysics;

public class Form2SciencesStudent extends AppCompatActivity {

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2_science);

        // Initialize buttons for each science field
        Button buttonMathematics = findViewById(R.id.buttonMathematics);
        Button buttonBiology = findViewById(R.id.buttonBiology);
        Button buttonPhysics = findViewById(R.id.buttonPhysics);
        Button buttonChemistry = findViewById(R.id.buttonChemistry);
        Button buttonAgriculture = findViewById(R.id.buttonAgriculture);

        // Initialize small info buttons
        Button buttonSmallMathematics = findViewById(R.id.buttonSmallMathematics);
        Button buttonSmallBiology = findViewById(R.id.buttonSmallBiology);
        Button buttonSmallPhysics = findViewById(R.id.buttonSmallPhysics);
        Button buttonSmallChemistry = findViewById(R.id.buttonSmallChemistry);
        Button buttonSmallAgriculture = findViewById(R.id.buttonSmallAgriculture);

        // Back button setup
        buttonBack = findViewById(R.id.back_button);
        buttonBack.setOnClickListener(view -> onBackPressed());

        // Set click listeners for each science field button
        buttonMathematics.setOnClickListener(v -> {
            showToast("Mathematics Selected");
            startActivityForContent(Form2StudentViewContentMathematics.class);
        });

        buttonBiology.setOnClickListener(v -> {
            showToast("Biology Selected");
            startActivityForContent(Form2StudentViewContentBiology.class);
        });

        buttonPhysics.setOnClickListener(v -> {
            showToast("Physics Selected");
            startActivityForContent(Form2StudentViewContentPhysics.class);
        });

        buttonChemistry.setOnClickListener(v -> {
            showToast("Chemistry Selected");
            startActivityForContent(Form2StudentViewContentChemistry.class);
        });

        buttonAgriculture.setOnClickListener(v -> {
            showToast("Agriculture Selected");
            startActivityForContent(Form2StudentViewContentAgriculture.class);
        });

        // Set click listeners for small info buttons to show popups instead of toasts
        buttonSmallMathematics.setOnClickListener(v -> showPopup(v, getString(R.string.info_mathematics)));
        buttonSmallBiology.setOnClickListener(v -> showPopup(v, getString(R.string.info_biology)));
        buttonSmallPhysics.setOnClickListener(v -> showPopup(v, getString(R.string.info_physics)));
        buttonSmallChemistry.setOnClickListener(v -> showPopup(v, getString(R.string.info_chemistry)));
        buttonSmallAgriculture.setOnClickListener(v -> showPopup(v, getString(R.string.info_agriculture)));
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

    // Helper method to show a toast message
    private void showToast(String message) {
        Toast.makeText(Form2SciencesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start the respective content activity based on the selected science field
    private void startActivityForContent(Class<?> cls) {
        Intent intent = new Intent(Form2SciencesStudent.this, cls);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
}
