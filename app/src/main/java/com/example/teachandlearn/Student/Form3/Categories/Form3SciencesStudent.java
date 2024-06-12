
package com.example.teachandlearn.Student.Form3.Categories;
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
import com.example.teachandlearn.Student.Form3.Documents.Agriculture.Form3StudentViewContentAgriculture;
import com.example.teachandlearn.Student.Form3.Documents.Biology.Form3StudentViewContentBiology;
import com.example.teachandlearn.Student.Form3.Documents.Chemistry.Form3StudentViewContentChemistry;
import com.example.teachandlearn.Student.Form3.Documents.Mathematics.Form3StudentViewContentMathematics;
import com.example.teachandlearn.Student.Form3.Documents.Physics.Form3StudentViewContentPhysics;

public class Form3SciencesStudent extends AppCompatActivity {

    private Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3_science);

        // Find buttons for each science field
        Button buttonMathematics = findViewById(R.id.buttonMathematics);
        Button buttonBiology = findViewById(R.id.buttonBiology);
        Button buttonPhysics = findViewById(R.id.buttonPhysics);
        Button buttonChemistry = findViewById(R.id.buttonChemistry);
        Button buttonAgriculture = findViewById(R.id.buttonAgriculture);

        // Find small info buttons
        Button buttonSmallMathematics = findViewById(R.id.buttonSmallMathematics);
        Button buttonSmallBiology = findViewById(R.id.buttonSmallBiology);
        Button buttonSmallPhysics = findViewById(R.id.buttonSmallPhysics);
        Button buttonSmallChemistry = findViewById(R.id.buttonSmallChemistry);
        Button buttonSmallAgriculture = findViewById(R.id.buttonSmallAgriculture);
        buttonBack = findViewById(R.id.back_button);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });
        // Set click listeners for each button
        buttonMathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message
                showToast("Mathematics Selected");
                // Start the Form1ViewContentActivity
                startActivityForContent();
            }
        });

        buttonBiology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message
                showToast("Biology Selected");
                // Start the Form1ViewContentActivity
                startActivityForContent();
            }
        });

        buttonChemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message
                showToast("Chemistry Selected");
                // Start the Form1ViewContentActivity
                startActivityForContent();
            }
        });

        buttonPhysics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message
                showToast("Physics Selected");
                // Start the Form1ViewContentActivity
                startActivityForContent();
            }
        });

        buttonAgriculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message
                showToast("Agriculture Selected");
                // Start the Form1ViewContentActivity
                startActivityForContent();
            }
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

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(Form3SciencesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity
    private void startActivityForContent() {


        Intent intent1 = new Intent(Form3SciencesStudent.this, Form3StudentViewContentAgriculture.class);
        Intent intent2 = new Intent(Form3SciencesStudent.this, Form3StudentViewContentBiology.class);
        Intent intent3 = new Intent(Form3SciencesStudent.this, Form3StudentViewContentChemistry.class);
        Intent intent4 = new Intent(Form3SciencesStudent.this, Form3StudentViewContentMathematics.class);
        Intent intent5 = new Intent(Form3SciencesStudent.this, Form3StudentViewContentPhysics.class);

        startActivity(intent1);
        startActivity(intent2);
        startActivity(intent3);
        startActivity(intent4);
        startActivity(intent5);
    }

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }

}