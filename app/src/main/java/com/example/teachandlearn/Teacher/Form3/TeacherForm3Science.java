
package com.example.teachandlearn.Teacher.Form3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;


public class TeacherForm3Science extends AppCompatActivity {

//    private ImageButton buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form3_science);

        // Find buttons for each science field
        Button buttonMathematics = findViewById(R.id.buttonMathematics);
        Button buttonBiology = findViewById(R.id.buttonBiology);
        Button buttonPhysics = findViewById(R.id.buttonPhysics);
        Button buttonChemistry = findViewById(R.id.buttonChemistry);
        Button buttonAgriculture = findViewById(R.id.buttonAgriculture);
//        buttonBack = findViewById(R.id.back_button);

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

//        buttonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Logic for when the back button is pressed
//                onBackPressed();
//            }
//        });
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(TeacherForm3Science.this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to start Form1ViewContentActivity
    private void startActivityForContent() {
        Intent intent = new Intent(TeacherForm3Science.this, TeacherForm3Uploads.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
}
