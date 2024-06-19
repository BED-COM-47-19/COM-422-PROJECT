

package com.example.teachandlearn.Student.Form1.Documents.Social_Studies;

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

public class Form1QuizSocial_Studies extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form1QuestionsSocial_Studies> questions;
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

    private List<Form1QuestionsSocial_Studies> loadQuestions() {


        List<Form1QuestionsSocial_Studies> questions = new ArrayList<>();


        questions.add(new Form1QuestionsSocial_Studies("What is the capital city of Malawi?",
                "Lilongwe",
                "Blantyre",
                "Mzuzu",
                "Zomba",
                "Lilongwe"));

        questions.add(new Form1QuestionsSocial_Studies("When did Malawi gain independence from British rule?",
                "1960",
                "1961",
                "1962",
                "1963",
                "1964"));

        questions.add(new Form1QuestionsSocial_Studies("Who was the first President of Malawi?",
                "Kamuzu Chibambo",
                "Joyce Banda",
                "Bakili Muluzi",
                "Hastings Banda",
                "Hastings Banda"));

        questions.add(new Form1QuestionsSocial_Studies("What is the main economic activity in rural Malawi?",
                "Fishing",
                "Mining",
                "Agriculture",
                "Manufacturing",
                "Agriculture"));

        questions.add(new Form1QuestionsSocial_Studies("Which lake forms part of Malawi's eastern border?",
                "Lake Victoria",
                "Lake Tanganyika",
                "Lake Malawi",
                "Lake Kariba",
                "Lake Malawi"));

        questions.add(new Form1QuestionsSocial_Studies("Who are the Yao people in Malawi?",
                "Nomadic pastoralists",
                "Fishermen",
                "Farmers",
                "Traders",
                "Traders"));

        questions.add(new Form1QuestionsSocial_Studies("Which countries border Malawi?",
                "Zambia, Tanzania, Mozambique",
                "Zimbabwe, Botswana, South Africa",
                "Kenya, Uganda, Rwanda",
                "Angola, Namibia, Congo",
                "Zambia, Tanzania, Mozambique"));

        questions.add(new Form1QuestionsSocial_Studies("What is the official language of Malawi?",
                "English",
                "Chichewa",
                "French",
                "Portuguese",
                "English"));

        questions.add(new Form1QuestionsSocial_Studies("Who was David Livingstone and what was his role in Malawi's history?",
                "A missionary and explorer who contributed to the exploration of Africa, including Lake Malawi",
                "A military leader who fought for Malawi's independence",
                "A colonial governor who ruled Malawi",
                "A businessman who developed trade routes in Malawi",
                "A missionary and explorer who contributed to the exploration of Africa, including Lake Malawi"));

        questions.add(new Form1QuestionsSocial_Studies("What is the significance of Lake Malawi to Malawi's economy and culture?",
                "It is a major source of oil production",
                "It is a source of freshwater and supports fishing industry",
                "It is a major tourist attraction for mountain climbing",
                "It is used for commercial shipping and transportation",
                "It is a source of freshwater and supports fishing industry"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form1QuestionsSocial_Studies currentQuestion = questions.get(currentQuestionIndex);
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
