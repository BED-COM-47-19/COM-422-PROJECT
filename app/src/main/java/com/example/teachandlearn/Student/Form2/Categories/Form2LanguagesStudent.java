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
import com.example.teachandlearn.Student.Form2.Documents.English.Form2StudentViewContentEnglish;
import com.example.teachandlearn.Student.Form2.Documents.Chichewa.Form2StudentViewContentChichewa;

public class Form2LanguagesStudent extends AppCompatActivity {

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2_languages);

        // Initialize buttons for each language subject
        Button buttonEnglish = findViewById(R.id.buttonEnglish);
        Button buttonChichewa = findViewById(R.id.buttonChichewa);

        // Initialize small info buttons
        Button buttonSmallEnglish = findViewById(R.id.buttonSmallEnglish);
        Button buttonSmallChichewa = findViewById(R.id.buttonSmallChichewa);

        // Back button setup
        buttonBack = findViewById(R.id.back_button);
        buttonBack.setOnClickListener(view -> onBackPressed());

        // Click listeners for main language subject buttons
        buttonEnglish.setOnClickListener(v -> {
            showToast("English Selected");
            startActivityForContent(Form2StudentViewContentEnglish.class);
        });

        buttonChichewa.setOnClickListener(v -> {
            showToast("Chichewa Selected");
            startActivityForContent(Form2StudentViewContentChichewa.class);
        });

        // Click listeners for small info buttons
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
        Toast.makeText(Form2LanguagesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    // Starts an activity to view content for the selected language subject
    private void startActivityForContent(Class<?> cls) {
        Intent intent = new Intent(Form2LanguagesStudent.this, cls);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
}
