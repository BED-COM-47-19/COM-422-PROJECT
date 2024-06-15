

package com.example.teachandlearn.Student.Form2.Documents.Chemistry;

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


        questions.add(new Form2Question("What is the chemical symbol for water?",
                "H2O",
                "CO2",
                "O2",
                "NaCl",
                "H2O"));

        questions.add(new Form2Question("Which gas is most abundant in the Earth's atmosphere?",
                "Oxygen",
                "Carbon dioxide",
                "Nitrogen",
                "Hydrogen",
                "Nitrogen"));

        questions.add(new Form2Question("What happens to water when it boils?",
                "It freezes",
                "It condenses",
                "It evaporates",
                "It solidifies",
                "It evaporates"));

        questions.add(new Form2Question("Which of these is a non-metallic element?",
                "Iron",
                "Sodium",
                "Oxygen",
                "Calcium",
                "Oxygen"));

        questions.add(new Form2Question("What is the pH value of a neutral substance?",
                "0",
                "7",
                "14",
                "10",
                "7"));

        questions.add(new Form2Question("Which gas is produced during photosynthesis?",
                "Carbon dioxide",
                "Oxygen",
                "Nitrogen",
                "Hydrogen",
                "Oxygen"));

        questions.add(new Form2Question("What type of reaction occurs when acids react with bases?",
                "Oxidation reaction",
                "Neutralization reaction",
                "Combustion reaction",
                "Decomposition reaction",
                "Neutralization reaction"));

        questions.add(new Form2Question("What is the chemical formula for methane?",
                "CH3OH",
                "CO2",
                "CH4",
                "H2O",
                "CH4"));

        questions.add(new Form2Question("Which of these is an example of a chemical change?",
                "Melting ice",
                "Boiling water",
                "Mixing salt in water",
                "Burning wood",
                "Burning wood"));

        questions.add(new Form2Question("What is the process by which a solid changes directly into a gas?",
                "Condensation",
                "Sublimation",
                "Evaporation",
                "Filtration",
                "Sublimation"));
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
