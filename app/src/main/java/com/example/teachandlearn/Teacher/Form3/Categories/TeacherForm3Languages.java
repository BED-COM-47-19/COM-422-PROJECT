
package com.example.teachandlearn.Teacher.Form3.Categories;
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
import com.example.teachandlearn.Teacher.Form3.Uploads.Chichewa.TeacherForm3ChichewaUploads;


public class TeacherForm3Languages extends AppCompatActivity {

   private Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form3_languages);

        // Find buttons for each humanities subject
        Button buttonEnglish = findViewById(R.id.buttonEnglish);
        Button buttonChichewa = findViewById(R.id.buttonChichewa);


        Button buttonSmallEnglish = findViewById(R.id.buttonSmallEnglish);
        Button buttonSmallChichewa = findViewById(R.id.buttonSmallChichewa);

        buttonBack = findViewById(R.id.back_button);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });
        // Set click listeners for each button
        buttonEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("English Selected");
                startActivityForContent();
            }
        });

        buttonChichewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Chichewa Selected");
                startActivityForContent();
            }
        });

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

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(TeacherForm3Languages.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity
    private void startActivityForContent() {


        Intent intent1 = new Intent(TeacherForm3Languages.this, TeacherForm3ChichewaUploads.class);
        startActivity(intent1);

        Intent intent2 = new Intent(TeacherForm3Languages.this, TeacherForm3ChichewaUploads.class);
        startActivity(intent2);

    }

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
}
