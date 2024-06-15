

package com.example.teachandlearn.Student.Form2.Documents.Geography;

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

public class Form2QuizGeography extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form2QuestionGeography> questions;
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

    private List<Form2QuestionGeography> loadQuestions() {


        List<Form2QuestionGeography> questions = new ArrayList<>();

        questions.add(new Form2QuestionGeography("Which type of plate boundary is responsible for the formation of the Himalayas?",
                "Convergent boundary",
                "Divergent boundary",
                "Transform boundary",
                "Subduction boundary",
                "Convergent boundary"));

        questions.add(new Form2QuestionGeography("On a map, what does a contour line represent?",
                "Rivers",
                "Roads",
                "Elevation",
                "Political boundaries",
                "Elevation"));

        questions.add(new Form2QuestionGeography("What is the name of the largest hot desert in the world?",
                "Sahara Desert",
                "Arabian Desert",
                "Gobi Desert",
                "Kalahari Desert",
                "Sahara Desert"));

        questions.add(new Form2QuestionGeography("Which ocean contains the Mariana Trench, the deepest part of the world's oceans?",
                "Atlantic Ocean",
                "Indian Ocean",
                "Arctic Ocean",
                "Pacific Ocean",
                "Pacific Ocean"));

        questions.add(new Form2QuestionGeography("Which river is known as the 'Father of Waters'?",
                "Amazon River",
                "Nile River",
                "Mississippi River",
                "Yangtze River",
                "Mississippi River"));

        questions.add(new Form2QuestionGeography("What is the capital city of Australia?",
                "Sydney",
                "Melbourne",
                "Canberra",
                "Perth",
                "Canberra"));

        questions.add(new Form2QuestionGeography("Which mountain range runs along the west coast of South America?",
                "Rocky Mountains",
                "Andes Mountains",
                "Himalayas",
                "Alps",
                "Andes Mountains"));

        questions.add(new Form2QuestionGeography("What is the term for the area drained by a river and its tributaries?",
                "Watershed",
                "Estuary",
                "Delta",
                "Reservoir",
                "Watershed"));

        questions.add(new Form2QuestionGeography("Which country is known for having the most active volcanoes?",
                "Italy",
                "Indonesia",
                "United States",
                "Iceland",
                "Indonesia"));

        questions.add(new Form2QuestionGeography("What is the latitude and longitude of the Prime Meridian?",
                "0 degrees latitude, 0 degrees longitude",
                "0 degrees latitude, 180 degrees longitude",
                "90 degrees latitude, 0 degrees longitude",
                "180 degrees latitude, 0 degrees longitude",
                "0 degrees latitude, 0 degrees longitude"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form2QuestionGeography currentQuestion = questions.get(currentQuestionIndex);
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
