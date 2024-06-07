

package com.example.teachandlearn.Teacher.Form1.Categories;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Teacher.SelectClass.TeacherSelectClass;
import com.example.teachandlearn.Student.Form1.Documents.Form1PDF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;



public class TeacherForm1 extends AppCompatActivity {

    private Button buttonBack;
    private Button buttonListEmail;
    private Button buttonProfile;

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
        buttonListEmail = findViewById(R.id.email_list_button);
        buttonProfile = findViewById(R.id.user_profile_button);

        // Set onClickListener for SCIENCE button
        buttonScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SelectScienceActivity when SCIENCE button is clicked
                Intent intent = new Intent(TeacherForm1.this, TeacherForm1Science.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for HUMANITIES button
        buttonHumanities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherForm1.this, TeacherSelectClass.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for LANGUAGES button
        buttonLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherForm1.this, TeacherForm1Languages.class);
                startActivity(intent);
            }
        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Profile button click
                // Retrieve and display user details based on the logged-in user's email
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser != null) {
                    String userEmail = currentUser.getEmail();

                    userRef.orderByChild("email").equalTo(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            if (dataSnapshot.exists()) {
                                // Assuming user details are stored under a node named "userDetails"
                                String userDetails = dataSnapshot.child("Users").getValue(String.class);
                                if (userDetails != null && !userDetails.isEmpty()) {
                                    showToast(userDetails);
                                }

                                else {
                                    showToast("User details not found.");
                                }
                            }

                            else {
                                showToast("User not found.");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            showToast("Failed to retrieve user details.");

                        }
                    });

                }

                else {
                    showToast("User not authenticated.");
                }
            }
        });



        buttonListEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Email button click
                // Retrieve and display emails of students who are in Form1
                final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser(); // Access currentUser here

                if (currentUser != null) {

                    DatabaseReference form1StudentsRef = database.getReference("student_form1_emails");
                    form1StudentsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            List<String> studentEmails = new ArrayList<>();


                            for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                                // Assuming student emails are stored under a node named "email" for each user
                                String email = studentSnapshot.child("email").getValue(String.class);
                                studentEmails.add(email);
                            }

                            if (!studentEmails.isEmpty()) {
                                // Create an Intent to start the Form1PDF activity
                                Intent intent = new Intent(TeacherForm1.this, Form1PDF.class);
                                // Pass the student emails as an extra with the Intent
                                intent.putStringArrayListExtra("student_form1_emails", (ArrayList<String>) studentEmails);
                                // Start the Form1PDF activity
                                startActivity(intent);
                            }

                            else {
                                showToast("No students found in Form1.");
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            showToast("Failed to retrieve student emails: " + databaseError.getMessage());
                        }
                    });

                }

                else {
                    showToast("User not authenticated.");
                }
            }

        });


        // Set onClickListener for Back button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }

        });

    }

    private void showToast(String message) {

        if (message != null && !message.isEmpty()) {

            Toast.makeText(TeacherForm1.this, message, Toast.LENGTH_SHORT).show();

        }

        else {
            // Handle the case where the message is empty or null
            Log.e("Toast Error", "Message is empty or null");
        }

    }

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }

    // New method to save a single teacher's email to Firebase Realtime Database
    private void saveTeacherEmailToFirebase(String teacherEmail) {

        DatabaseReference teacherEmailsRef = FirebaseDatabase.getInstance().getReference().child("teacher_form1_emails");
        teacherEmailsRef.push().setValue(teacherEmail);

    }

    @Override
    protected void onStart() {

        super.onStart();

        // Get the current user's email or any other way to obtain the teacher's email
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            teacherEmail = currentUser.getEmail();

            // Store teacher email to Firebase when user logs in
            saveTeacherEmailToFirebase(teacherEmail);
        }

        else {

        }
    }

}
