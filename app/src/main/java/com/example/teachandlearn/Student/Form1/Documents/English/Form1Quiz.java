

package com.example.teachandlearn.Student.Form1.Documents.English;

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

public class Form1Quiz extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form1Question> form1Questions;
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
        questionProgressBar = findViewById(R.id.questionProgressBar);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.back_button);
        optionsGroup = findViewById(R.id.optionsGroup);

        // Initialize questions
        form1Questions = loadQuestions();

        if (form1Questions == null || form1Questions.isEmpty()) {
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

    private List<Form1Question> loadQuestions() {


        List<Form1Question> form1Questions = new ArrayList<>();
        form1Questions.add(new Form1Question("What is the capital of France?", "Lilongwe", "Paris", "Mzuzu", "Dowa", "Paris"));
        form1Questions.add(new Form1Question("What is 2 + 2?", "1", "5", "3", "4", "4"));
        form1Questions.add(new Form1Question("What is the color of the sky?", "Sky Black", "Sky Green", "Sky White", "Sky Blue", "Sky Blue"));
        form1Questions.add(new Form1Question("What is the capital of Spain?", "Barcelona", "London", "Madrid", "Espana", "Madrid"));

        return form1Questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < form1Questions.size()) {

            Form1Question currentForm1Question = form1Questions.get(currentQuestionIndex);
            questionTextView.setText(currentForm1Question.getQuestionText());
            optionARadioButton.setText(currentForm1Question.getOptionA());
            optionBRadioButton.setText(currentForm1Question.getOptionB());
            optionCRadioButton.setText(currentForm1Question.getOptionC());
            optionDRadioButton.setText(currentForm1Question.getOptionD());
            questionProgressBar.setProgress((int) (((float) currentQuestionIndex / form1Questions.size()) * 100));
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
        return form1Questions.get(currentQuestionIndex).getCorrectAnswer();
    }

    private void finishQuiz() {
        Toast.makeText(this, "Quiz finished! Correct answers: " + correctAnswers, Toast.LENGTH_LONG).show();
        // Redirect to another activity or close the current one
        finish();
    }
}
