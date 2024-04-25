
package com.example.teachandlearn.Teacher.Form2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Teacher.Form2.TeacherForm2Languages;
import com.example.teachandlearn.Teacher.Form2.TeacherForm2Humanities;
import com.example.teachandlearn.Teacher.Form2.TeacherForm2Science;

public class TeacherForm2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

        // Find the buttons for SCIENCE, HUMANITIES, and LANGUAGES
        Button buttonScience = findViewById(R.id.buttonScience);
        Button buttonHumanities = findViewById(R.id.buttonHumanities);
        Button buttonLanguages = findViewById(R.id.buttonLanguages);

        // Set onClickListener for SCIENCE button
        buttonScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SelectScienceActivity when SCIENCE button is clicked
                Intent intent = new Intent(TeacherForm2.this, TeacherForm2Science.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for HUMANITIES button
        buttonHumanities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherForm2.this, TeacherForm2Humanities.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for LANGUAGES button
        buttonLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherForm2.this, TeacherForm2Languages.class);
                startActivity(intent);
            }
        });
    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(TeacherForm2.this, message, Toast.LENGTH_SHORT).show();
    }
}
