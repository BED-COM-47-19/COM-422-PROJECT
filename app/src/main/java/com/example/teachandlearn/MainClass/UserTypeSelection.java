
package com.example.teachandlearn.MainClass;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.teachandlearn.R;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;
import com.example.teachandlearn.Student.LogIn_And_SignUp.StudentLogIn;
import com.example.teachandlearn.Teacher.LogIn_And_SignUp.TeacherLogIn;

public class UserTypeSelection extends AppCompatActivity {

    private Button buttonStudent;
    private Button buttonTeacher;
    private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the XML layout for this activity
        setContentView(R.layout.activity_user_type_selection);

        // Initialize buttons from layout
        buttonStudent = findViewById(R.id.button_student);
        buttonTeacher = findViewById(R.id.button_teacher);
        buttonBack = findViewById(R.id.back_button);

        // Set onClick listeners for buttons
        buttonStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the 'STUDENT' button is pressed
                Intent intent = new Intent(UserTypeSelection.this, StudentLogIn.class);
                startActivity(intent);
            }
        });

        buttonTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the 'TEACHER' button is pressed
                Intent intent = new Intent(UserTypeSelection.this, TeacherLogIn.class);
                startActivity(intent);
            }
        });

        // Set onClick listener for the back button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
}
