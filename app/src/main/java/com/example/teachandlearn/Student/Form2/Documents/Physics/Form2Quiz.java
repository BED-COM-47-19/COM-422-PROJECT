

package com.example.teachandlearn.Student.Form2.Documents.Physics;

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

public class Form2Quiz extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form2Question> questions;
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

    private List<Form2Question> loadQuestions() {


        List<Form2Question> questions = new ArrayList<>();


        questions.add(new Form2Question("What is the SI unit of force?",
                "Newton",
                "Joule",
                "Watt",
                "Volt",
                "Newton"));

        questions.add(new Form2Question("Define acceleration.",
                "Rate of change of velocity",
                "Rate of change of displacement",
                "Rate of change of speed",
                "Rate of change of time",
                "Rate of change of velocity"));

        questions.add(new Form2Question("A car travels at 20 m/s for 30 seconds. What is the distance traveled by the car?",
                "600 meters",
                "400 meters",
                "800 meters",
                "1000 meters",
                "600 meters"));

        questions.add(new Form2Question("What is the formula for calculating kinetic energy?",
                "KE = 1/2 mv^2",
                "KE = mv",
                "KE = m^2v",
                "KE = 2mv",
                "KE = 1/2 mv^2"));

        questions.add(new Form2Question("State the law of conservation of energy.",
                "Energy cannot be created or destroyed, only transformed from one form to another",
                "Energy can be created or destroyed, depending on the situation",
                "Energy remains constant in any system",
                "Energy is lost during transformations",
                "Energy cannot be created or destroyed, only transformed from one form to another"));

        questions.add(new Form2Question("What is the principle of moments?",
                "When a body is in equilibrium, the sum of clockwise moments about any point equals the sum of anticlockwise moments about the same point",
                "When a body is in motion, the sum of clockwise moments about any point equals the sum of anticlockwise moments about the same point",
                "When a body is in equilibrium, the sum of clockwise forces equals the sum of anticlockwise forces",
                "When a body is in motion, the sum of clockwise forces equals the sum of anticlockwise forces",
                "When a body is in equilibrium, the sum of clockwise moments about any point equals the sum of anticlockwise moments about the same point"));

        questions.add(new Form2Question("What is the relationship between frequency (f), wavelength (λ), and wave speed (v) of a wave?",
                "v = fλ",
                "v = f + λ",
                "v = f/λ",
                "v = f - λ",
                "v = fλ"));

        questions.add(new Form2Question("Define pressure.",
                "Force per unit area",
                "Force times acceleration",
                "Mass times acceleration",
                "Work done per unit volume",
                "Force per unit area"));

        questions.add(new Form2Question("What is the unit of electric current?",
                "Ampere (A)",
                "Volt (V)",
                "Ohm (Ω)",
                "Coulomb (C)",
                "Ampere (A)"));

        questions.add(new Form2Question("Explain the term 'refraction' of light.",
                "The bending of light as it passes from one medium to another",
                "The reflection of light from a smooth surface",
                "The absorption of light by a material",
                "The dispersion of light into its constituent colors",
                "The bending of light as it passes from one medium to another"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form2Question currentQuestion = questions.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestionText());
            optionARadioButton.setText(currentQuestion.getOptionA());
            optionBRadioButton.setText(currentQuestion.getOptionB());
            optionCRadioButton.setText(currentQuestion.getOptionC());
            optionDRadioButton.setText(currentQuestion.getOptionD());
            questionProgressBar.setProgress((int) (((float) currentQuestionIndex / questions.size()) * 100));
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
