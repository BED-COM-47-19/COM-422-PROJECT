

package com.example.teachandlearn.Student.Form3.Documents.Mathematics;

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

public class Form3QuizMathematics extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form3QuestionMathematics> questions;
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

    private List<Form3QuestionMathematics> loadQuestions() {


        List<Form3QuestionMathematics> questions = new ArrayList<>();

        questions.add(new Form3QuestionMathematics("Solve the equation: log3(x-1) = 2",
                "x = 10",
                "x = 8",
                "x = 6",
                "x = 4",
                "x = 10"));

        questions.add(new Form3QuestionMathematics("Simplify the expression: log2(16)",
                "4",
                "2",
                "8",
                "6",
                "4"));

        questions.add(new Form3QuestionMathematics("Find the determinant of the matrix:\n[ [ 3, 2 ], [ 5, 4 ] ]",
                "2",
                "4",
                "-2",
                "-4",
                "2"));

        questions.add(new Form3QuestionMathematics("Solve the system of equations:\n2x + y = 7\nx - y = 1",
                "x = 2, y = 3",
                "x = 3, y = 2",
                "x = 4, y = 1",
                "x = 1, y = 4",
                "x = 2, y = 3"));

        questions.add(new Form3QuestionMathematics("Calculate the value of 2^{3} \times 2^{-2}",
                "4",
                "2",
                "8",
                "16",
                "4"));

        questions.add(new Form3QuestionMathematics("Find the inverse of the matrix:\n[ [ 3, 1 ], [ 2, 4 ] ]",
                "[ [ 0.4, -0.1 ], [ -0.2, 0.3 ] ]",
                "[ [ 0.3, -0.2 ], [ -0.1, 0.4 ] ]",
                "[ [ 0.2, -0.3 ], [ -0.4, 0.1 ] ]",
                "[ [ -0.3, 0.2 ], [ 0.1, -0.4 ] ]",
                "[ [ 0.4, -0.1 ], [ -0.2, 0.3 ] ]"));

        questions.add(new Form3QuestionMathematics("Solve for x in the equation: 2^{x} = 16",
                "x = 4",
                "x = 3",
                "x = 2",
                "x = 1",
                "x = 4"));

        questions.add(new Form3QuestionMathematics("Find the value of \\sqrt{625}",
                "25",
                "20",
                "30",
                "35",
                "25"));

        questions.add(new Form3QuestionMathematics("Calculate the value of \\log_{3}(27)",
                "3",
                "2",
                "4",
                "5",
                "3"));

        questions.add(new Form3QuestionMathematics("Solve the equation: 3^{2x-1} = 27",
                "x = 2",
                "x = 1",
                "x = 3",
                "x = 4",
                "x = 2"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form3QuestionMathematics currentQuestion = questions.get(currentQuestionIndex);
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
