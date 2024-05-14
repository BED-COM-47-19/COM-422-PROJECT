<<<<<<< HEAD
=======
<<<<<<< HEAD
package com.example.teachandlearn.Student.Form1.Categories;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
=======

>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
package com.example.teachandlearn.Student.Form1.Categories;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ImageButton;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
=======
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;

public class Form1HumanitiesStudent extends AppCompatActivity {
<<<<<<< HEAD
    private Button buttonBack;
=======
<<<<<<< HEAD
    private Button buttonBack;
=======
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

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

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
        buttonBack = findViewById(R.id.back_button);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });


<<<<<<< HEAD
=======
=======
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
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

        // Set click listeners for small info buttons
        buttonSmallSocialStudies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v, getString(R.string.info_social_studies));
            }
        });
        buttonSmallLifeSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v, getString(R.string.info_life_skills));
            }
        });
        buttonSmallHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v, getString(R.string.info_history));
            }
        });
        buttonSmallBibleKnowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v, getString(R.string.info_bible_knowledge));
            }
        });
        buttonSmallGeography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v, getString(R.string.info_geography));
            }
        });
    }

    private void showPopup(View anchor, String text) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
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
<<<<<<< HEAD
    }

    // Helper method to show toast message
=======
    }

    // Helper method to show toast message
=======
        // Implement your popup window logic here
    }

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
    private void showToast(String message) {
        Toast.makeText(Form1HumanitiesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

<<<<<<< HEAD
    // Method to start Form1ViewContentActivity
=======
<<<<<<< HEAD
    // Method to start Form1ViewContentActivity
=======
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
    private void startActivityForContent() {
        Intent intent = new Intent(Form1HumanitiesStudent.this, Form1StudentViewContent.class);
        startActivity(intent);
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }

<<<<<<< HEAD
=======
=======
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
}
