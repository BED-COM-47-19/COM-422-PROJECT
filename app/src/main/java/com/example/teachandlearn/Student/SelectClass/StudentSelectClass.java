
package com.example.teachandlearn.Student.SelectClass;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import android.widget.ImageButton;

import com.example.teachandlearn.MainClass.UserAccountSettings;
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
import com.example.teachandlearn.MainClass.UserTypeSelection;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Categories.Form1Student;
import com.example.teachandlearn.Student.Form2.Categories.Form2Student;
import com.example.teachandlearn.Student.Form3.Categories.Form3Student;
import com.example.teachandlearn.Student.Form4.Categories.Form4Student;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD


public class StudentSelectClass extends AppCompatActivity {

    private Button buttonBack;
=======
<<<<<<< HEAD


public class StudentSelectClass extends AppCompatActivity {

    private Button buttonBack;
=======
import android.widget.Switch;

public class StudentSelectClass extends AppCompatActivity {

    private ImageButton buttonBack;
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_select_class);

        // Initialize buttons

        Button buttonForm1 = findViewById(R.id.button_form1);
        Button buttonForm2 = findViewById(R.id.button_form2);
        Button buttonForm3 = findViewById(R.id.button_form3);
        Button buttonForm4 = findViewById(R.id.button_form4);
        Button logOutButton = findViewById(R.id.log_out_button);  // Initialize the log out button
        buttonBack = findViewById(R.id.back_button);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

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
         // Initialize the menu button

        // Set click listeners for each button
        buttonForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentSelectClass.this, Form1Student.class));
            }
        });

        buttonForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentSelectClass.this, Form2Student.class));
            }
        });

        buttonForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentSelectClass.this, Form3Student.class));
            }
        });

        buttonForm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentSelectClass.this, Form4Student.class));
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
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

    private void logoutUser() {
        // Here you might handle clearing any cached user data or logged-in session
        // Redirect user to UserTypeSelection activity
        Intent intent = new Intent(StudentSelectClass.this, UserTypeSelection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clears the activity stack
        startActivity(intent);
        finish(); // Close the current activity
    }

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
}
