package com.example.teachandlearn.Student.LogIn_And_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

        // Set click listeners for each button
        buttonForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Form 1 activity
                startActivity(new Intent(TeacherSelectClass.this, TeacherForm1.class));
            }
        });

        buttonForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Form 2 activity
                startActivity(new Intent(TeacherSelectClass.this, TeacherForm2.class));
            }
        });

        buttonForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Form 3 activity
                startActivity(new Intent(TeacherSelectClass.this, TeacherForm3.class));
            }
        });

        buttonForm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Form 4 activity
                startActivity(new Intent(TeacherSelectClass.this, TeacherForm4.class));
            }
        });
    }
}
