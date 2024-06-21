

package com.example.teachandlearn.Student.Form4.Documents.Chemistry;

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

public class Form4QuizChemistry extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form4QuestionChemistry> questions;
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

    private List<Form4QuestionChemistry> loadQuestions() {


        List<Form4QuestionChemistry> questions = new ArrayList<>();
        questions.add(new Form4QuestionChemistry("What is the molecular formula of glucose?",
                "C6H12O6",
                "C2H6O2",
                "CH3COOH",
                "C6H6",
                "C6H12O6"));

        questions.add(new Form4QuestionChemistry("What is the process of electrolysis?",
                "The chemical decomposition produced by passing an electric current through a liquid or solution containing ions.",
                "The combination of elements to form a compound.",
                "The process of filtering a solid from a liquid.",
                "The physical change from a solid to a liquid.",
                "The chemical decomposition produced by passing an electric current through a liquid or solution containing ions."));

        questions.add(new Form4QuestionChemistry("Which gas is produced when hydrochloric acid reacts with zinc?",
                "Oxygen",
                "Nitrogen",
                "Hydrogen",
                "Carbon dioxide",
                "Hydrogen"));

        questions.add(new Form4QuestionChemistry("What is the main component of natural gas?",
                "Ethane",
                "Propane",
                "Butane",
                "Methane",
                "Methane"));

        questions.add(new Form4QuestionChemistry("What is Avogadro's number?",
                "6.022 × 10^23",
                "3.14 × 10^23",
                "1.602 × 10^-19",
                "9.81 × 10^2",
                "6.022 × 10^23"));

        questions.add(new Form4QuestionChemistry("What is the pH value of a neutral solution at 25°C?",
                "7",
                "0",
                "14",
                "4",
                "7"));

        questions.add(new Form4QuestionChemistry("Which of the following is an example of a strong acid?",
                "Hydrochloric acid",
                "Acetic acid",
                "Formic acid",
                "Carbonic acid",
                "Hydrochloric acid"));

        questions.add(new Form4QuestionChemistry("What is the electron configuration of a sodium atom?",
                "1s^2 2s^2 2p^6 3s^1",
                "1s^2 2s^2 2p^5 3s^1",
                "1s^2 2s^2 2p^4 3s^2",
                "1s^2 2s^2 2p^3 3s^3",
                "1s^2 2s^2 2p^6 3s^1"));

        questions.add(new Form4QuestionChemistry("What type of bond is formed when two atoms share electrons?",
                "Ionic bond",
                "Hydrogen bond",
                "Metallic bond",
                "Covalent bond",
                "Covalent bond"));

        questions.add(new Form4QuestionChemistry("What is the empirical formula of a compound that contains 40% carbon, 6.7% hydrogen, and 53.3% oxygen by mass?",
                "CH2O",
                "C2H2O2",
                "C3H6O3",
                "CH4O",
                "CH2O"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form4QuestionChemistry currentQuestion = questions.get(currentQuestionIndex);
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
