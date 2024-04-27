
package com.example.teachandlearn.Student.LogIn_And_SignUp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Form1Student;
import com.example.teachandlearn.Student.Form2.Form2Student;
import com.example.teachandlearn.Student.Form3.Form3Student;
import com.example.teachandlearn.Student.Form4.Form4Student;

public class StudentSelectClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_select_class);

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
                Intent intent = new Intent(StudentSelectClass.this, Form1Student.class);
                startActivity(intent);
            }
        });

        buttonForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentSelectClass.this, Form2Student.class);
                startActivity(intent);
            }
        });

        buttonForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentSelectClass.this, Form3Student.class);
                startActivity(intent);
            }
        });

        buttonForm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentSelectClass.this, Form4Student.class);
                startActivity(intent);
            }
        });
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(StudentSelectClass.this, message, Toast.LENGTH_SHORT).show();
    }
}
