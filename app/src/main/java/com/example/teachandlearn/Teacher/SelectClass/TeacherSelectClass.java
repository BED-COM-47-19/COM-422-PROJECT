
package com.example.teachandlearn.Teacher.SelectClass;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.teachandlearn.MainClass.UserTypeSelection;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Teacher.Form1.TeacherForm1;
import com.example.teachandlearn.Teacher.Form2.TeacherForm2;
import com.example.teachandlearn.Teacher.Form3.TeacherForm3;
import com.example.teachandlearn.Teacher.Form4.TeacherForm4;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherSelectClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_select_class);

        // Initialize buttons
        Button buttonForm1 = findViewById(R.id.button_form1);
        Button buttonForm2 = findViewById(R.id.button_form2);
        Button buttonForm3 = findViewById(R.id.button_form3);
        Button buttonForm4 = findViewById(R.id.button_form4);
        ImageButton logOutButton = findViewById(R.id.log_out_button);  // Initialize the log out button

        // Set click listeners for each button
        buttonForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSelectClass.this, TeacherForm1.class));
            }
        });

        buttonForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSelectClass.this, TeacherForm2.class));
            }
        });

        buttonForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSelectClass.this, TeacherForm3.class));
            }
        });

        buttonForm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSelectClass.this, TeacherForm4.class));
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void logoutUser() {
        // Here you might handle clearing any cached user data or logged-in session
        // Redirect user to UserTypeSelection activity
        Intent intent = new Intent(TeacherSelectClass.this, UserTypeSelection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clears the activity stack
        startActivity(intent);
        finish(); // Close the current activity
    }
}
