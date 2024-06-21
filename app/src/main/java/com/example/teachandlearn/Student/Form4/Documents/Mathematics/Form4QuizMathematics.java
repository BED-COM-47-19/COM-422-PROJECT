

package com.example.teachandlearn.Student.Form4.Documents.Mathematics;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;

import java.util.ArrayList;
import java.util.List;

public class Form4QuizMathematics extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;

    private List<Form4QuestionMathematics> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private Button nextButton, backButton;
    private RadioGroup optionsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_agri_quizzes);

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

    private List<Form4QuestionMathematics> loadQuestions() {


        List<Form4QuestionMathematics> questions = new ArrayList<>();

        // Add questions to the list
        questions.add(new Form4QuestionMathematics("Solve the quadratic equation: x^2 - 5x + 6 = 0",
                "x = 2, x = 3",
                "x = 1, x = 6",
                "x = 2, x = 4",
                "x = 3, x = 5",
                "x = 2, x = 3"));

        questions.add(new Form4QuestionMathematics("Find the value of sqrt(144)",
                "12",
                "10",
                "15",
                "14",
                "12"));

        questions.add(new Form4QuestionMathematics("Simplify the expression: (3x^2y^3) / (6x^-2y)",
                "x^4y^2 / 2",
                "x^3y^4 / 2",
                "x^2y^5 / 3",
                "x^5y^3 / 3",
                "x^4y^2 / 2"));

        questions.add(new Form4QuestionMathematics("Calculate the area of a circle with radius 5 cm (use pi = 3.14)",
                "78.5 cm^2",
                "75.0 cm^2",
                "79.0 cm^2",
                "80.0 cm^2",
                "78.5 cm^2"));

        questions.add(new Form4QuestionMathematics("Solve the equation: log2(x) = 3",
                "x = 8",
                "x = 6",
                "x = 4",
                "x = 2",
                "x = 8"));

        questions.add(new Form4QuestionMathematics("Find the value of (2/3) / (4/5)",
                "5/6",
                "6/5",
                "3/2",
                "4/3",
                "5/6"));

        questions.add(new Form4QuestionMathematics("Determine the solution set for the inequality: 2x - 5 > 7",
                "x > 6",
                "x > 4",
                "x > 3",
                "x > 5",
                "x > 6"));

        questions.add(new Form4QuestionMathematics("Factorize the expression: 2x^2 - 8x",
                "2x(x - 4)",
                "2x^2(x - 2)",
                "2x(x + 4)",
                "2x^2(x + 2)",
                "2x(x - 4)"));

        questions.add(new Form4QuestionMathematics("Calculate the value of sin(30 degrees)",
                "0.5",
                "0.707",
                "0.866",
                "1.0",
                "0.5"));

        questions.add(new Form4QuestionMathematics("Simplify the expression: (3x^2 + 2x - 1) / (x^2 - 1)",
                "3x + 1 / x + 1",
                "3x - 1 / x - 1",
                "3x^2 + 1 / x^2 + 1",
                "3x^2 - 1 / x^2 - 1",
                "3x + 1 / x + 1"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form4QuestionMathematics currentQuestion = questions.get(currentQuestionIndex);
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
        Toast.makeText(this, "Quiz finished! Correct answers: " + correctAnswers, Toast.LENGTH_LONG).show();
        // Redirect to another activity or close the current one
        finish();
    }
}
