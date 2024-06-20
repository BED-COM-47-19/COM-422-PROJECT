package com.example.teachandlearn.Teacher.Form1.Categories;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class TeacherForm1 extends AppCompatActivity {

    private Button buttonBack;
    private Button progressTrackingButton; // New button for progress tracking
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private DatabaseReference teacherRef;
    private String teacherEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form1);

        // Initialize Firebase
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("Users");
        teacherRef = database.getReference("teachers");

        teacherEmail = getIntent().getStringExtra("teacher_form1_emails");

        // Find the buttons
        Button buttonScience = findViewById(R.id.activity_teacher_form1_science);
        Button buttonHumanities = findViewById(R.id.activity_teacher_form1_humanities);
        Button buttonLanguages = findViewById(R.id.activity_teacher_form1_languages);
        buttonBack = findViewById(R.id.back_button);

        // Find progress tracking button
        progressTrackingButton = findViewById(R.id.activity_teacher_form1_progress_tracking);
        progressTrackingButton.setOnClickListener(v -> {
            // Launch ProgressTrackingActivity
            Intent intent = new Intent(TeacherForm1.this, ProgressTrackingActivity.class);
            startActivity(intent);
        });

        // Set onClickListener for SCIENCE button
        buttonScience.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherForm1.this, TeacherForm1Science.class);
            startActivity(intent);
        });

        // Set onClickListener for HUMANITIES button
        buttonHumanities.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherForm1.this, TeacherForm1Humanities.class);
            startActivity(intent);
        });

        // Set onClickListener for LANGUAGES button
        buttonLanguages.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherForm1.this, TeacherForm1Languages.class);
            startActivity(intent);
        });

        // Set onClickListener for Back button
        buttonBack.setOnClickListener(view -> onBackPressed());
    }

    private void saveTeacherEmailToFirebase(String teacherEmail) {
        DatabaseReference teacherEmailsRef = FirebaseDatabase.getInstance().getReference().child("teacher_form1_emails");
        teacherEmailsRef.push().setValue(teacherEmail);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Get the current user's email or any other way to obtain the teacher's email
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            teacherEmail = currentUser.getEmail();

            // Store teacher email to Firebase when user logs in
            saveTeacherEmailToFirebase(teacherEmail);
        } else {
            // Handle the case where current user is null
            Log.e("TeacherForm1", "Current user is null");
        }
    }

    private void showToast(String message) {
        if (message != null && !message.isEmpty()) {
            Toast.makeText(TeacherForm1.this, message, Toast.LENGTH_SHORT).show();
        } else {
            Log.e("Toast Error", "Message is empty or null");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
