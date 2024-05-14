package com.example.teachandlearn.Student.Form4.Categories;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Documents.Form1Audio;
import com.example.teachandlearn.Student.Form1.Documents.Form1PDF;
import com.example.teachandlearn.Student.Form1.Documents.Form1QuizzesAndQuestions;
import com.example.teachandlearn.Student.Form1.Documents.Form1Videos;


public class Form4StudentViewContent extends AppCompatActivity {

<<<<<<< HEAD

    private Button buttonBack;
=======
<<<<<<< HEAD

    private Button buttonBack;
=======
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form4_view_content);

        // Find buttons for PDF, Audio, Videos, and Questions
        Button buttonPDF = findViewById(R.id.button_pdf);
        Button buttonAudio = findViewById(R.id.button_audio);
        Button buttonVideos = findViewById(R.id.button_videos);
        Button buttonQuestions = findViewById(R.id.button_tests_quizzes);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945

        buttonBack = findViewById(R.id.back_button);

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
        Button buttonBack = findViewById(R.id.button_back); // Find the back button
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945


        // Set click listeners for each button
        buttonPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form4StudentViewContent.this, Form1PDF.class);
                startActivity(intent);
            }
        });

        buttonAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form4StudentViewContent.this, Form1Audio.class);
                startActivity(intent);
            }
        });

        buttonVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form4StudentViewContent.this, Form1Videos.class);
                startActivity(intent);
            }
        });

        buttonQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Form4StudentViewContent.this, Form1QuizzesAndQuestions.class);
                startActivity(intent);
            }
        });

<<<<<<< HEAD

=======
<<<<<<< HEAD

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

=======
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for when the back button is pressed
                onBackPressed();
            }
        });

    }
<<<<<<< HEAD

    @Override
    public void onBackPressed() {
        // Handle the back button action
        super.onBackPressed();
        // You can also add custom logic here if needed
    }

=======
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
}
