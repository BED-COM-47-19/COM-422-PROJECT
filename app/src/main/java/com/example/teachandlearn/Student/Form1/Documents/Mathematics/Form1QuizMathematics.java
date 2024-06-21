

package com.example.teachandlearn.Student.Form1.Documents.Mathematics;
import android.widget.ScrollView;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Documents.Agriculture.Form1QuestionAgriculture;

import java.util.ArrayList;
import java.util.List;

public class Form1QuizMathematics extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form1QuestionsMathematics> questions;
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

    private List<Form1QuestionsMathematics> loadQuestions() {


        List<Form1QuestionsMathematics> questions = new ArrayList<>();

        questions.add(new Form1QuestionsMathematics("What is the value of π (pi)?",
                "3.14",
                "2.71",
                "1.62",
                "4.56",
                "3.14"));

        questions.add(new Form1QuestionsMathematics("Simplify the expression: 3x + 2x - 5",
                "5x - 5",
                "6x - 5",
                "5x + 3",
                "2x - 5",
                "5x - 5"));

        questions.add(new Form1QuestionsMathematics("Calculate the area of a rectangle with length 8 cm and width 5 cm.",
                "13 cm²",
                "40 cm²",
                "45 cm²",
                "20 cm²",
                "40 cm²"));

        questions.add(new Form1QuestionsMathematics("Solve for x: 2x + 5 = 15",
                "x = 10",
                "x = 5",
                "x = 20",
                "x = 15",
                "x = 5"));

        questions.add(new Form1QuestionsMathematics("If a triangle has angles measuring 30°, 60°, and 90°, what is the measure of the third angle?",
                "120°",
                "90°",
                "45°",
                "60°",
                "90°"));

        questions.add(new Form1QuestionsMathematics("Calculate the perimeter of a square with each side measuring 7 cm.",
                "21 cm",
                "28 cm",
                "35 cm",
                "42 cm",
                "28 cm"));

        questions.add(new Form1QuestionsMathematics("What is the value of √25?",
                "2",
                "5",
                "6",
                "25",
                "5"));

        questions.add(new Form1QuestionsMathematics("If 3x = 24, what is the value of x?",
                "8",
                "6",
                "10",
                "12",
                "8"));

        questions.add(new Form1QuestionsMathematics("Simplify the expression: 4a - 2b + 3a - b",
                "7a - 3b",
                "5a - 3b",
                "7a - 2b",
                "5a - b",
                "7a - 3b"));

        questions.add(new Form1QuestionsMathematics("Calculate the volume of a cube with side length 4 cm.",
                "16 cm³",
                "32 cm³",
                "64 cm³",
                "12 cm³",
                "64 cm³"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form1QuestionsMathematics currentQuestion = questions.get(currentQuestionIndex);
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
        gradeTextView.setTextSize(18);
        gradeTextView.setTextColor(Color.BLACK);
        contentLayout.addView(gradeTextView);

        // Display solutions
        for (int i = 0; i < questions.size(); i++) {
            Form1QuestionsMathematics question = questions.get(i);

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
