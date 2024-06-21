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
import com.example.teachandlearn.Student.Form1.Documents.Chichewa.Form1StudentViewContentChichewa;
import com.example.teachandlearn.Student.Form1.Documents.English.Form1StudentViewContentEnglish;

public class Form1LanguagesStudent extends AppCompatActivity {

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_languages);

        // Initialize language subject buttons
        Button buttonEnglish = findViewById(R.id.buttonEnglish);
        Button buttonChichewa = findViewById(R.id.buttonChichewa);
        Button buttonSmallEnglish = findViewById(R.id.buttonSmallEnglish);
        Button buttonSmallChichewa = findViewById(R.id.buttonSmallChichewa);

        buttonBack = findViewById(R.id.back_button);
        buttonBack.setOnClickListener(view -> onBackPressed());

        // Setup listeners for main subject buttons
        buttonEnglish.setOnClickListener(v -> {
            showToast("English Selected");
            startActivityForContent(Form1StudentViewContentEnglish.class);
        });

        buttonChichewa.setOnClickListener(v -> {
            showToast("Chichewa Selected");
            startActivityForContent(Form1StudentViewContentChichewa.class);
        });

        // Setup listeners for small info buttons to show popups
        buttonSmallEnglish.setOnClickListener(v -> showPopup(v, getString(R.string.info_english)));
        buttonSmallChichewa.setOnClickListener(v -> showPopup(v, getString(R.string.info_chichewa)));
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
        Toast.makeText(Form1LanguagesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    // Starts an activity to view content for the selected language subject
    private void startActivityForContent(Class<?> cls) {
        Intent intent = new Intent(Form1LanguagesStudent.this, cls);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
}
