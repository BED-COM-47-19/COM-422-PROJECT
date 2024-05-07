package com.example.teachandlearn.Student.Form1;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;

public class Form1SciencesStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_science);

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

        // Set click listeners for each button
        buttonMathematics.setOnClickListener(v -> {
            showToast("Mathematics Selected");
            startActivityForContent();
        });

        buttonBiology.setOnClickListener(v -> {
            showToast("Biology Selected");
            startActivityForContent();
        });

        buttonChemistry.setOnClickListener(v -> {
            showToast("Chemistry Selected");
            startActivityForContent();
        });

        buttonPhysics.setOnClickListener(v -> {
            showToast("Physics Selected");
            startActivityForContent();
        });

        buttonAgriculture.setOnClickListener(v -> {
            showToast("Agriculture Selected");
            startActivityForContent();
        });

        // Set click listeners for small info buttons to show popups instead of toasts
        buttonSmallMathematics.setOnClickListener(v -> showPopup(v, "Info: Learn more about Mathematics."));
        buttonSmallBiology.setOnClickListener(v -> showPopup(v, "Info: Learn more about Biology."));
        buttonSmallPhysics.setOnClickListener(v -> showPopup(v, "Info: Learn more about Physics."));
        buttonSmallChemistry.setOnClickListener(v -> showPopup(v, "Info: Learn more about Chemistry."));
        buttonSmallAgriculture.setOnClickListener(v -> showPopup(v, "Info: Learn more about Agriculture."));
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
        Toast.makeText(Form1SciencesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity
    private void startActivityForContent() {
        Intent intent = new Intent(Form1SciencesStudent.this, Form1StudentViewContent.class);
        startActivity(intent);
    }

}
