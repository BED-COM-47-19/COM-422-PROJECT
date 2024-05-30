

package com.example.teachandlearn.Student.Form1.Categories;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Form1Student extends AppCompatActivity {

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);

        // Find the buttons for SCIENCE, HUMANITIES, and LANGUAGES
        Button buttonScience = findViewById(R.id.activity_form1_science);
        Button buttonHumanities = findViewById(R.id.activity_form1_humanities);
        Button buttonLanguages = findViewById(R.id.activity_form1_languages);
        buttonBack = findViewById(R.id.back_button);

        // Set onClickListener for SCIENCE button
        buttonScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Form1SciencesStudent when SCIENCE button is clicked
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
                Intent intent = new Intent(Form1Student.this, Form1LanguagesStudent.class);
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

    @Override
    protected void onStart() {
        super.onStart();

        // Get the current user's email or any other way to obtain the student's email
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String studentEmail = currentUser.getEmail();

            // Store student email to Firebase when user logs in
            saveStudentEmailToFirebase(studentEmail);
        }

    }

    // Method to save a single student's email to Firebase Realtime Database
    private void saveStudentEmailToFirebase(String studentEmail) {

        DatabaseReference studentEmailsRef = FirebaseDatabase.getInstance().getReference().child("student_form1_emails");
        studentEmailsRef.push().setValue(studentEmail);

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
