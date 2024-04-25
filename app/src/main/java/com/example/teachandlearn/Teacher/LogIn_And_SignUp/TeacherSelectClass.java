package com.example.teachandlearn.Teacher.LogIn_And_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.example.teachandlearn.Teacher.Form1.TeacherForm1;
import com.example.teachandlearn.Teacher.Form2.TeacherForm2;
import com.example.teachandlearn.Teacher.Form3.TeacherForm3;
import com.example.teachandlearn.Teacher.Form4.TeacherForm4;

public class TeacherSelectClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_select_class);

        // Find buttons for each form
        Button buttonForm1 = findViewById(R.id.button_form1);
        Button buttonForm2 = findViewById(R.id.button_form2);
        Button buttonForm3 = findViewById(R.id.button_form3);
        Button buttonForm4 = findViewById(R.id.button_form4);

        // Set click listeners for each button
        buttonForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SelectCategoryActivity when Form 1 is selected
                Intent intent = new Intent(TeacherSelectClass.this, TeacherForm1.class);
                startActivity(intent);
            }
        });

        buttonForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherSelectClass.this, TeacherForm2.class);
                startActivity(intent);
            }
        });

        buttonForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherSelectClass.this, TeacherForm3.class);
                startActivity(intent);
            }
        });

        buttonForm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherSelectClass.this, TeacherForm4.class);
                startActivity(intent);
            }
        });
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(TeacherSelectClass.this, message, Toast.LENGTH_SHORT).show();
    }
}
