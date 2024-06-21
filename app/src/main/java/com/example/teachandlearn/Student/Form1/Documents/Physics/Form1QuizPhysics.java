

package com.example.teachandlearn.Student.Form1.Documents.Physics;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Documents.Mathematics.Form1QuestionsMathematics;

import java.util.ArrayList;
import java.util.List;

public class Form1QuizPhysics extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form1QuestionsPhysics> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private Button nextButton, backButton;
    private RadioGroup optionsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_quizzes);

        questionTextView = findViewById(R.id.questionTextView);
        optionARadioButton = findViewById(R.id.optionARadioButton);
        optionBRadioButton = findViewById(R.id.optionBRadioButton);
        optionCRadioButton = findViewById(R.id.optionCRadioButton);
        optionDRadioButton = findViewById(R.id.optionDRadioButton);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.back_button);
        optionsGroup = findViewById(R.id.optionsGroup);

        // Initialize questions
        questions = loadQuestions();

        if (questions == null || questions.isEmpty()) {
            Toast.makeText(this, "No questions available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        displayQuestion();

        nextButton.setOnClickListener(v -> {
            displayNextQuestion();
        });

        backButton.setOnClickListener(v -> {
            displayPreviousQuestion();
        });
    }

    private List<Form1QuestionsPhysics> loadQuestions() {


        List<Form1QuestionsPhysics> questions = new ArrayList<>();


        questions.add(new Form1QuestionsPhysics("What is the SI unit of force?",
                "Newton (N)",
                "Meter (m)",
                "Gram (g)",
                "Second (s)",
                "Newton (N)"));

        questions.add(new Form1QuestionsPhysics("What is the formula for calculating speed?",
                "Speed = Distance × Time",
                "Speed = Time / Distance",
                "Speed = Distance / Time",
                "Speed = Time - Distance",
                "Speed = Distance / Time"));

        questions.add(new Form1QuestionsPhysics("What is the acceleration of an object if it moves with a constant speed?",
                "Zero",
                "Depends on the direction",
                "Positive",
                "Negative",
                "Zero"));

        questions.add(new Form1QuestionsPhysics("State the law of conservation of energy.",
                "Energy cannot be created or destroyed, only transformed from one form to another",
                "Energy can be created but not destroyed",
                "Energy can be destroyed but not created",
                "Energy can disappear",
                "Energy cannot be created or destroyed, only transformed from one form to another"));

        questions.add(new Form1QuestionsPhysics("What is the unit of electric current?",
                "Volt (V)",
                "Ohm (Ω)",
                "Watt (W)",
                "Ampere (A)",
                "Ampere (A)"));

        questions.add(new Form1QuestionsPhysics("Define 'pressure' in physics.",
                "Force per unit area",
                "Mass per unit volume",
                "Distance traveled per unit time",
                "Energy per unit charge",
                "Force per unit area"));

        questions.add(new Form1QuestionsPhysics("What is the principle of moments?",
                "The sum of clockwise moments is equal to the sum of anticlockwise moments about a pivot",
                "The sum of clockwise moments is greater than the sum of anticlockwise moments",
                "The sum of clockwise moments is less than the sum of anticlockwise moments",
                "The sum of moments is always zero",
                "The sum of clockwise moments is equal to the sum of anticlockwise moments about a pivot"));

        questions.add(new Form1QuestionsPhysics("What is the unit of power?",
                "Joule (J)",
                "Newton (N)",
                "Watt (W)",
                "Meter per second (m/s)",
                "Watt (W)"));

        questions.add(new Form1QuestionsPhysics("State Newton's first law of motion.",
                "An object at rest will remain at rest, and an object in motion will continue to move at a constant velocity unless acted upon by a net external force",
                "Force is equal to mass times acceleration (F = ma)",
                "For every action, there is an equal and opposite reaction",
                "The force exerted by an object is equal to the object's mass times acceleration",
                "An object at rest will remain at rest, and an object in motion will continue to move at a constant velocity unless acted upon by a net external force"));

        questions.add(new Form1QuestionsPhysics("What is the relationship between wavelength and frequency of a wave?",
                "Wavelength × Frequency = Speed of the wave",
                "Wavelength / Frequency = Speed of the wave",
                "Wavelength + Frequency = Speed of the wave",
                "Wavelength - Frequency = Speed of the wave",
                "Wavelength × Frequency = Speed of the wave"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form1QuestionsPhysics currentQuestion = questions.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestionText());
            optionARadioButton.setText(currentQuestion.getOptionA());
            optionBRadioButton.setText(currentQuestion.getOptionB());
            optionCRadioButton.setText(currentQuestion.getOptionC());
            optionDRadioButton.setText(currentQuestion.getOptionD());

        }
        else {
            finishQuiz();
        }
    }

    private void displayNextQuestion() {
        if (optionsGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(optionsGroup.getCheckedRadioButtonId());
        checkAnswer(selectedRadioButton.getText().toString());

        currentQuestionIndex++;
        optionsGroup.clearCheck();
        displayQuestion();
    }

    private void displayPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            displayQuestion();
        } else {
            Toast.makeText(this, "You are already at the first question", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkAnswer(String selectedAnswer) {
        String correctAnswer = getCorrectAnswerForCurrentQuestion();
        if (selectedAnswer.equals(correctAnswer)) {
            correctAnswers++;
        }
    }

    private String getCorrectAnswerForCurrentQuestion() {
        return questions.get(currentQuestionIndex).getCorrectAnswer();
    }

    private void finishQuiz() {
        // Hide question and options
        questionTextView.setVisibility(View.GONE);
        optionsGroup.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);



        // Create a ScrollView
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams scrollViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        scrollView.setLayoutParams(scrollViewParams);

        // Create a LinearLayout to hold all content (including grade, questions, and options)
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Display grade
        double grade = (double) correctAnswers / questions.size() * 100;
        TextView gradeTextView = new TextView(this);
        gradeTextView.setText("Grade: " + String.format("%.2f", grade) + "%");
        gradeTextView.setTextColor(Color.BLACK);
        gradeTextView.setTextSize(18);
        contentLayout.addView(gradeTextView);

        // Display solutions
        for (int i = 0; i < questions.size(); i++) {
            Form1QuestionsPhysics question = questions.get(i);

            TextView questionTextView = new TextView(this);
            questionTextView.setText("Question " + (i + 1) + ": " + question.getQuestionText());
            questionTextView.setTextSize(16);
            questionTextView.setTextColor(Color.BLACK);
            contentLayout.addView(questionTextView);

            String[] options = {question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD()};
            for (String option : options) {
                TextView optionTextView = new TextView(this);
                optionTextView.setText(option);
                optionTextView.setTextSize(16);

                if (option.equals(question.getCorrectAnswer())) {
                    optionTextView.setTextColor(Color.BLUE); // Correct answer in blue
                } else if (option.equals(question.getUserAnswer())) {
                    optionTextView.setTextColor(Color.RED); // User's answer in red
                } else {
                    optionTextView.setTextColor(Color.BLACK); // Default color for other options
                }

                contentLayout.addView(optionTextView);
            }

            // Add some space between questions
            TextView spaceTextView = new TextView(this);
            spaceTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    16 // Adjust spacing as needed
            ));
            contentLayout.addView(spaceTextView);
        }

        // Add the contentLayout to the scrollView
        scrollView.addView(contentLayout);

        // Add the scrollView to the rootLayout
        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        rootLayout.addView(scrollView);
    }

}
