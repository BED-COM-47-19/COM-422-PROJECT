

package com.example.teachandlearn.Student.Form2.Documents.Agriculture;

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

public class Form2QuizAgriculture extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form2QuestionAgriculture> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private Button nextButton, backButton;
    private RadioGroup optionsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2_agri_quiz);

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

    private List<Form2QuestionAgriculture> loadQuestions() {


        List<Form2QuestionAgriculture> questions = new ArrayList<>();

        questions.add(new Form2QuestionAgriculture("What is the primary economic activity in rural Malawi?",
                "Fishing",
                "Mining",
                "Agriculture",
                "Manufacturing",
                "Agriculture"));

        questions.add(new Form2QuestionAgriculture("Name two types of soil commonly found in Malawi.",
                "Red soil and sandy soil",
                "Loamy soil and clay soil",
                "Black soil and volcanic soil",
                "Alluvial soil and peaty soil",
                "Red soil and sandy soil"));

        questions.add(new Form2QuestionAgriculture("What is crop rotation?",
                "Growing the same crop year after year",
                "Growing different crops in the same field in a planned sequence",
                "Using chemical fertilizers for crop growth",
                "Harvesting crops at different times of the year",
                "Growing different crops in the same field in a planned sequence"));

        questions.add(new Form2QuestionAgriculture("What are the benefits of using organic fertilizers?",
                "They are cheaper than chemical fertilizers",
                "They improve soil fertility and structure",
                "They have a longer shelf life",
                "They require less application",
                "They improve soil fertility and structure"));

        questions.add(new Form2QuestionAgriculture("Name two examples of leguminous crops.",
                "Maize and rice",
                "Beans and groundnuts",
                "Wheat and barley",
                "Cotton and tobacco",
                "Beans and groundnuts"));

        questions.add(new Form2QuestionAgriculture("What is irrigation in agriculture?",
                "Draining excess water from fields",
                "Supplying water to crops by artificial means",
                "Fertilizing crops using chemical substances",
                "Harvesting crops mechanically",
                "Supplying water to crops by artificial means"));

        questions.add(new Form2QuestionAgriculture("What is pest control in agriculture?",
                "Using pests to control crop growth",
                "Using chemicals to kill pests",
                "Using biological methods to manage pests",
                "Removing crops affected by pests",
                "Using biological methods to manage pests"));

        questions.add(new Form2QuestionAgriculture("Name a common disease that affects maize plants in Malawi.",
                "Foot and mouth disease",
                "Maize streak virus",
                "Malaria",
                "Dengue fever",
                "Maize streak virus"));

        questions.add(new Form2QuestionAgriculture("Define agroforestry.",
                "Growing crops without using any fertilizers",
                "Growing trees together with crops or animals",
                "Fishing in agricultural fields",
                "Mining in agricultural areas",
                "Growing trees together with crops or animals"));

        questions.add(new Form2QuestionAgriculture("What is livestock farming?",
                "Growing crops for domestic consumption",
                "Rearing animals for commercial purposes",
                "Selling animal products in the market",
                "Keeping animals as pets",
                "Rearing animals for commercial purposes"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form2QuestionAgriculture currentQuestion = questions.get(currentQuestionIndex);
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
