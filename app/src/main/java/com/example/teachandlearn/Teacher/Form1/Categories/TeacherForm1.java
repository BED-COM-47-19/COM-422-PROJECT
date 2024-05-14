<<<<<<< HEAD
package com.example.teachandlearn.Teacher.Form1.Categories;

=======
<<<<<<< HEAD
package com.example.teachandlearn.Teacher.Form1.Categories;

=======

package com.example.teachandlearn.Teacher.Form1.Categories;
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;

<<<<<<< HEAD
public class TeacherForm1 extends AppCompatActivity {

    private Button buttonBack;

=======
<<<<<<< HEAD
public class TeacherForm1 extends AppCompatActivity {

    private Button buttonBack;

=======

public class TeacherForm1 extends AppCompatActivity {

//    private ImageButton buttonBack;
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form1);

        // Find the buttons for SCIENCE, HUMANITIES, and LANGUAGES
        Button buttonScience = findViewById(R.id.activity_teacher_form1_science);
        Button buttonHumanities = findViewById(R.id.activity_teacher_form1_humanities);
        Button buttonLanguages = findViewById(R.id.activity_teacher_form1_languages);
<<<<<<< HEAD
        buttonBack = findViewById(R.id.back_button);
=======
<<<<<<< HEAD
        buttonBack = findViewById(R.id.back_button);
=======
//        buttonBack = findViewById(R.id.back_button);
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

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
                Intent intent = new Intent(TeacherForm1.this, TeacherForm1Humanities.class);
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
//        buttonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Logic for when the back button is pressed
//                onBackPressed();
//            }
//        });
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

    }

    // Helper method to show toast message
    private void showToast(String message) {
        Toast.makeText(TeacherForm1.this, message, Toast.LENGTH_SHORT).show();
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }
<<<<<<< HEAD
=======
=======
//    @Override
//    public void onBackPressed() {
//        // Handle the back button action
//        super.onBackPressed();
//        // You can also add custom logic here if needed
//    }
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

}
