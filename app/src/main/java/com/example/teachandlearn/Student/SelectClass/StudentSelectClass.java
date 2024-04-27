
package com.example.teachandlearn.Student.LogIn_And_SignUp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Form1Student;
import com.example.teachandlearn.Student.Form2.Form2Student;
import com.example.teachandlearn.Student.Form3.Form3Student;
import com.example.teachandlearn.Student.Form4.Form4Student;

import androidx.appcompat.app.AppCompatActivity;

public class StudentSelectClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_forms.xml layout file
        setContentView(R.layout.activity_student_select_class);

        // Initialize buttons
        Button buttonForm1 = findViewById(R.id.button_form1);
        Button buttonForm2 = findViewById(R.id.button_form2);
        Button buttonForm3 = findViewById(R.id.button_form3);
        Button buttonForm4 = findViewById(R.id.button_form4);
        Button buttonSignIn = findViewById(R.id.buttonSignIn);
        ImageButton logOutButton = findViewById(R.id.log_out_button);

        // Set click listeners for each button
        buttonForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Form 1 button click
                openForm1();
            }
        });

        buttonForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Form 2 button click
                openForm2();
            }
        });

        buttonForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Form 3 button click
                openForm3();
            }
        });

        buttonForm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Form 4 button click
                openForm4();
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Sign In button click
                logoutUser();
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Logout button click
                logoutUser();
            }
        });
    }

    private void openForm1() {
        // Intent to start an activity for Form 1
        Intent intent = new Intent(this, Form1Student.class);
        startActivity(intent);
    }

    private void openForm2() {
        // Intent to start an activity for Form 2
        Intent intent = new Intent(this, Form2Student.class);
        startActivity(intent);
    }

    private void openForm3() {
        // Intent to start an activity for Form 3
        Intent intent = new Intent(this, Form3Student.class);
        startActivity(intent);
    }

    private void openForm4() {
        // Intent to start an activity for Form 4
        Intent intent = new Intent(this, Form4Student.class);
        startActivity(intent);
    }

    private void logoutUser() {
        // Logic to handle user logout
        finish();  // For example, closes the activity
    }
}
