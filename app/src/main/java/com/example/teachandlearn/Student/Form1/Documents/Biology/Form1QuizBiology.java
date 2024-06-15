package com.example.teachandlearn.Student.Form1.Documents.Biology;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Documents.Agriculture.Form1QuestionAgriculture;

import java.util.ArrayList;
import java.util.List;

public class Form1QuizBiology extends AppCompatActivity {

    private TextView questionTextView, questionNumberTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form1QuestionBiology> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private Button nextButton, backButton;
    private RadioGroup optionsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_quizzes);

        questionTextView = findViewById(R.id.questionTextView);
        questionNumberTextView = findViewById(R.id.questionNumberTextView);
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

        nextButton.setOnClickListener(v -> displayNextQuestion());

        backButton.setOnClickListener(v -> displayPreviousQuestion());
    }

    private List<Form1QuestionBiology> loadQuestions() {
        List<Form1QuestionBiology> questions = new ArrayList<>();
        questions.add(new Form1QuestionBiology("Which of the following is not a mammal?", "Dog", "Snake", "Cat", "Elephant", "Snake"));
        questions.add(new Form1QuestionBiology("What is the powerhouse of the cell?", "Nucleus", "Mitochondria", "Ribosome", "Endoplasmic reticulum", "Mitochondria"));
        questions.add(new Form1QuestionBiology("Which gas do plants absorb during photosynthesis?", "Oxygen", "Carbon dioxide", "Nitrogen", "Hydrogen", "Carbon dioxide"));
        questions.add(new Form1QuestionBiology("What is the largest organ of the human body?", "Liver", "Skin", "Brain", "Heart", "Skin"));
        questions.add(new Form1QuestionBiology("Which organ is responsible for filtering blood in the human body?", "Liver", "Kidney", "Stomach", "Pancreas", "Kidney"));
        questions.add(new Form1QuestionBiology("Which part of the plant conducts photosynthesis?", "Root", "Leaf", "Stem", "Flower", "Leaf"));
        questions.add(new Form1QuestionBiology("What type of joints are the knees and elbows?", "Ball and socket", "Hinge", "Pivot", "Gliding", "Hinge"));
        questions.add(new Form1QuestionBiology("What is the main function of red blood cells?", "Transport oxygen", "Fight infections", "Produce energy", "Digest food", "Transport oxygen"));
        questions.add(new Form1QuestionBiology("Which gas is produced by plants during respiration?", "Oxygen", "Carbon dioxide", "Methane", "Hydrogen", "Carbon dioxide"));
        questions.add(new Form1QuestionBiology("Which organ is part of the central nervous system?", "Heart", "Brain", "Liver", "Lungs", "Brain"));
        return questions;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Form1QuestionBiology currentQuestion = questions.get(currentQuestionIndex);
            questionNumberTextView.setText("Question " + (currentQuestionIndex + 1) + " of " + questions.size());
            questionTextView.setText(currentQuestion.getQuestionText());
            optionARadioButton.setText(currentQuestion.getOptionA());
            optionBRadioButton.setText(currentQuestion.getOptionB());
            optionCRadioButton.setText(currentQuestion.getOptionC());
            optionDRadioButton.setText(currentQuestion.getOptionD());
            questionProgressBar.setProgress((int) (((float) currentQuestionIndex / questions.size()) * 100));

            // Restore user's previous answer if available
            String userAnswer = currentQuestion.getUserAnswer();
            if (userAnswer != null) {
                if (userAnswer.equals(optionARadioButton.getText().toString())) {
                    optionARadioButton.setChecked(true);
                } else if (userAnswer.equals(optionBRadioButton.getText().toString())) {
                    optionBRadioButton.setChecked(true);
                } else if (userAnswer.equals(optionCRadioButton.getText().toString())) {
                    optionCRadioButton.setChecked(true);
                } else if (userAnswer.equals(optionDRadioButton.getText().toString())) {
                    optionDRadioButton.setChecked(true);
                }
            } else {
                optionsGroup.clearCheck();
            }
        } else {
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
        Form1QuestionBiology currentQuestion = questions.get(currentQuestionIndex);
        currentQuestion.setUserAnswer(selectedAnswer);  // Save the user's answer
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            correctAnswers++;
        }
    }

    private void finishQuiz() {
        // Hide question and options
        questionTextView.setVisibility(View.GONE);
        optionsGroup.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);

        // Display grade
        double grade = (double) correctAnswers / questions.size() * 100;
        TextView gradeTextView = new TextView(this);
        System.out.println();
        gradeTextView.setText("Grade: " + String.format("%.2f", grade) + "%");
        System.out.println();
        gradeTextView.setTextSize(18);
        ((LinearLayout) findViewById(R.id.rootLayout)).addView(gradeTextView);

        // Display solutions
        for (int i = 0; i < questions.size(); i++) {
            Form1QuestionBiology question = questions.get(i);

            TextView questionTextView = new TextView(this);
            questionTextView.setText("Question " + (i + 1) + ": " + question.getQuestionText());
            questionTextView.setTextSize(16);
            ((LinearLayout) findViewById(R.id.rootLayout)).addView(questionTextView);

            String[] options = {question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD()};
            for (String option : options) {
                TextView optionTextView = new TextView(this);
                optionTextView.setText(option);
                optionTextView.setTextSize(16);

                if (option.equals(question.getCorrectAnswer())) {
                    optionTextView.setTextColor(Color.GREEN);
                } else if (option.equals(question.getUserAnswer())) {
                    optionTextView.setTextColor(Color.RED);
                }

                ((LinearLayout) findViewById(R.id.rootLayout)).addView(optionTextView);
            }

            ((LinearLayout) findViewById(R.id.rootLayout)).addView(new TextView(this)); // Add some space between questions
        }
    }
}
