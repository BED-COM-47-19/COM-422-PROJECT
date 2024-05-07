
package com.example.teachandlearn.Teacher.Form1;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;



public class TeacherForm1Humanities extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form1_humanities);

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
        buttonSocialStudies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Social Studies Selected");
                startActivityForContent();
            }
        });

        buttonLifeSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Life Skills Selected");
                startActivityForContent();
            }
        });

        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("History Selected");
                startActivityForContent();
            }
        });

        buttonBibleKnowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Bible Knowledge Selected");
                startActivityForContent();
            }
        });

        buttonGeography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Geography Selected");
                startActivityForContent();
            }
        });

        buttonSmallSocialStudies.setOnClickListener(v -> showPopup(v, "Info: Social Studies"));
        buttonSmallLifeSkills.setOnClickListener(v -> showPopup(v, "Info: Life Skills"));
        buttonSmallHistory.setOnClickListener(v -> showPopup(v, "Info: History"));
        buttonSmallBibleKnowledge.setOnClickListener(v -> showPopup(v, "Info: Bible Knowledge"));
        buttonSmallGeography.setOnClickListener(v -> showPopup(v, "Info: Geography"));
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
        Toast.makeText(TeacherForm1Humanities.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity
    private void startActivityForContent() {
        Intent intent = new Intent(TeacherForm1Humanities.this, TeacherForm1Uploads.class);
        startActivity(intent);
    }
//
//    @Override
//    public void onBackPressed() {
//        // Handle the back button action
//        super.onBackPressed();
//        // You can also add custom logic here if needed
//    }
//
}
