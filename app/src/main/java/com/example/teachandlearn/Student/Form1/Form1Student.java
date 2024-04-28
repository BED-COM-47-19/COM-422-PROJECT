
package com.example.teachandlearn.Student.Form1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Form1HumanitiesStudent;
import com.example.teachandlearn.Student.Form1.Form1LanguagesSudent;
import com.example.teachandlearn.Student.Form1.Form1SciencesStudent;

public class Form1Student extends AppCompatActivity {

    private ImageButton buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);

        // Find the buttons for SCIENCE, HUMANITIES, and LANGUAGES
        Button buttonScience = findViewById(R.id.activity_form1_science);
        Button buttonHumanities = findViewById(R.id.activity_form1_humanities);
        Button buttonLanguages = findViewById(R.id.activity_form1_languages);
        buttonBack = findViewById(R.id.button_back);

        // Set onClickListener for SCIENCE button
        buttonScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SelectScienceActivity when SCIENCE button is clicked
                Intent intent = new Intent(Form1Student.this, Form1SciencesStudent.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for HUMANITIES button
        buttonHumanities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1Student.this, Form1HumanitiesStudent.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for LANGUAGES button
        buttonLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form1Student.this, Form1LanguagesSudent.class);
                startActivity(intent);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });

    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(Form1Student.this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }

}
