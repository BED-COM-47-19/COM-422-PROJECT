package com.example.teachandlearn.Student.Form1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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


        // Set click listeners for small info buttons
        buttonSmallMathematics.setOnClickListener(v -> showToast("Info: More about Mathematics"));
        buttonSmallBiology.setOnClickListener(v -> showToast("Info: More about Biology"));
        buttonSmallPhysics.setOnClickListener(v -> showToast("Info: More about Physics"));
        buttonSmallChemistry.setOnClickListener(v -> showToast("Info: More about Chemistry"));
        buttonSmallAgriculture.setOnClickListener(v -> showToast("Info: More about Agriculture"))

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
