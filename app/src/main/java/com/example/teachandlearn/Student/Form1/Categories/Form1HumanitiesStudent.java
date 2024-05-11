package com.example.teachandlearn.Student.Form1.Categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;

public class Form1HumanitiesStudent extends AppCompatActivity {

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
        // Implement your popup window logic here
    }

    private void showToast(String message) {
        Toast.makeText(Form1HumanitiesStudent.this, message, Toast.LENGTH_SHORT).show();
    }

    private void startActivityForContent() {
        Intent intent = new Intent(Form1HumanitiesStudent.this, Form1StudentViewContent.class);
        startActivity(intent);
    }
}
