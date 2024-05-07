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

public class Form1LanguagesStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_languages);

        // Find buttons for each language subject
        Button buttonEnglish = findViewById(R.id.buttonEnglish);
        Button buttonChichewa = findViewById(R.id.buttonChichewa);
        Button buttonSmallEnglish = findViewById(R.id.buttonSmallEnglish);
        Button buttonSmallChichewa = findViewById(R.id.buttonSmallChichewa);

        // Set click listeners for main subject buttons
        buttonEnglish.setOnClickListener(v -> {
            showToast("English Selected");
            startActivityForContent();
        });

        buttonChichewa.setOnClickListener(v -> {
            showToast("Chichewa Selected");
            startActivityForContent();
        });

        // Set click listeners for small info buttons with popups
        buttonSmallEnglish.setOnClickListener(v -> showPopup(v, "Info: Learn more about English."));
        buttonSmallChichewa.setOnClickListener(v -> showPopup(v, "Info: Learn more about Chichewa."));
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(Form1LanguagesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity
    private void startActivityForContent() {
        Intent intent = new Intent(Form1LanguagesStudent.this, Form1StudentViewContent.class);
        startActivity(intent);
    }

    // Method to show a popup
    private void showPopup(View anchor, String text) {
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_info, null);
        TextView textView = popupView.findViewById(R.id.textViewPopupInfo);
        textView.setText(text);

        PopupWindow popupWindow = new PopupWindow(popupView,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                true); // True means the popup is focusable

        // Show the popup at the specified location
        popupWindow.showAtLocation(anchor, Gravity.CENTER, 0, 0);
        popupWindow.update(); // Update the popup for proper layout handling
    }
}
