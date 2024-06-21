

package com.example.teachandlearn.Student.Form2.Documents.Mathematics;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;

import java.util.ArrayList;
import java.util.List;

public class Form2QuizMathematics extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form2QuestionMathematics> questions;
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

    private List<Form2QuestionMathematics> loadQuestions() {


        List<Form2QuestionMathematics> questions = new ArrayList<>();
        questions.add(new Form2QuestionMathematics("Solve for x: 3x + 5 = 17",
                "x = 4",
                "x = 6",
                "x = 7",
                "x = 12",
                "x = 4"));

        questions.add(new Form2QuestionMathematics("Simplify the expression: 3a^2b + 2ab^2 - ab",
                "3a^2b + 2ab^2 - ab",
                "3a^2b + ab^2",
                "3a^2b - ab^2",
                "3a^2b + 3ab^2",
                "3a^2b + 2ab^2 - ab"));

        questions.add(new Form2QuestionMathematics("Find the value of y if y/5 = 12",
                "y = 60",
                "y = 10",
                "y = 15",
                "y = 7",
                "y = 60"));

        questions.add(new Form2QuestionMathematics("Calculate the area of a triangle with base 6 cm and height 8 cm",
                "24 sq cm",
                "20 sq cm",
                "30 sq cm",
                "18 sq cm",
                "24 sq cm"));

        questions.add(new Form2QuestionMathematics("Simplify: (2x + 5y)^2",
                "4x^2 + 20xy + 25y^2",
                "4x^2 + 10xy + 25y^2",
                "4x^2 + 10xy + 20y^2",
                "4x^2 + 20xy + 20y^2",
                "4x^2 + 20xy + 25y^2"));

        questions.add(new Form2QuestionMathematics("Solve for x: 2(x - 3) = 10",
                "x = 8",
                "x = 7",
                "x = 6",
                "x = 5",
                "x = 8"));

        questions.add(new Form2QuestionMathematics("Simplify: (4x^2y^3)^3",
                "64x^6y^9",
                "16x^5y^6",
                "12x^6y^9",
                "16x^6y^9",
                "64x^6y^9"));

        questions.add(new Form2QuestionMathematics("Calculate the perimeter of a rectangle with length 12 cm and width 8 cm",
                "40 cm",
                "32 cm",
                "24 cm",
                "20 cm",
                "40 cm"));

        questions.add(new Form2QuestionMathematics("Solve for x: 5(x + 2) = 35",
                "x = 5",
                "x = 6",
                "x = 7",
                "x = 8",
                "x = 5"));

        questions.add(new Form2QuestionMathematics("Simplify: 2a^3b^2 * 3a^2b^3",
                "6a^5b^5",
                "5a^5b^5",
                "6a^6b^5",
                "5a^6b^5",
                "6a^5b^5"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form2QuestionMathematics currentQuestion = questions.get(currentQuestionIndex);
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
