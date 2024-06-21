

package com.example.teachandlearn.Student.Form2.Documents.Biology;

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

public class Form2QuizBiology extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form2QuestionBiology> questions;
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

    private List<Form2QuestionBiology> loadQuestions() {


        List<Form2QuestionBiology> questions = new ArrayList<>();


        questions.add(new Form2QuestionBiology("Which part of the cell is responsible for energy production?",
                "Nucleus",
                "Mitochondria",
                "Cell membrane",
                "Endoplasmic reticulum",
                "Mitochondria"));

        questions.add(new Form2QuestionBiology("What is the function of the heart in the human body?",
                "To pump blood",
                "To digest food",
                "To filter waste products",
                "To produce hormones",
                "To pump blood"));

        questions.add(new Form2QuestionBiology("Which gas do plants absorb during photosynthesis?",
                "Carbon dioxide",
                "Oxygen",
                "Nitrogen",
                "Hydrogen",
                "Carbon dioxide"));

        questions.add(new Form2QuestionBiology("What is the purpose of the skeleton in vertebrate animals?",
                "To produce red blood cells",
                "To provide structure and support",
                "To store excess water",
                "To produce hormones",
                "To provide structure and support"));

        questions.add(new Form2QuestionBiology("Which of the following is a function of the liver?",
                "To pump blood",
                "To filter waste products",
                "To produce insulin",
                "To digest food",
                "To filter waste products"));

        questions.add(new Form2QuestionBiology("What is the process by which plants make their own food using sunlight?",
                "Respiration",
                "Transpiration",
                "Photosynthesis",
                "Pollination",
                "Photosynthesis"));

        questions.add(new Form2QuestionBiology("Which part of the flower produces pollen?",
                "Stigma",
                "Petal",
                "Sepal",
                "Anther",
                "Anther"));

        questions.add(new Form2QuestionBiology("What is the function of the nervous system?",
                "To transport oxygen",
                "To regulate body temperature",
                "To control and coordinate body activities",
                "To produce antibodies",
                "To control and coordinate body activities"));

        questions.add(new Form2QuestionBiology("Which of these is an example of a renewable resource?",
                "Coal",
                "Natural gas",
                "Solar energy",
                "Petroleum",
                "Solar energy"));

        questions.add(new Form2QuestionBiology("What is the process by which organisms produce offspring similar to themselves?",
                "Fermentation",
                "Fertilization",
                "Mutation",
                "Excretion",
                "Fertilization"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form2QuestionBiology currentQuestion = questions.get(currentQuestionIndex);
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
