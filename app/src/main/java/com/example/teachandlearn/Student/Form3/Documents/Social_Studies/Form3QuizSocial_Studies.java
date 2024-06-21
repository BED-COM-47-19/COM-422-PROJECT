

package com.example.teachandlearn.Student.Form3.Documents.Social_Studies;

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

public class Form3QuizSocial_Studies extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form3QuestionSocial_Studies> questions;
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

    private List<Form3QuestionSocial_Studies> loadQuestions() {


        List<Form3QuestionSocial_Studies> questions = new ArrayList<>();
        questions.add(new Form3QuestionSocial_Studies("What is globalization?",
                "The process of increased interconnectedness among countries.",
                "The domination of one country over another.",
                "The policy of isolationism.",
                "The spread of a disease worldwide.",
                "The process of increased interconnectedness among countries."));

        questions.add(new Form3QuestionSocial_Studies("Explain the concept of human rights.",
                "Rights inherent to all human beings, regardless of nationality, ethnicity, religion, or any other status.",
                "Rights granted by governments to their citizens.",
                "Rights that are only applicable in times of peace.",
                "Rights that can be revoked depending on social status.",
                "Rights inherent to all human beings, regardless of nationality, ethnicity, religion, or any other status."));

        questions.add(new Form3QuestionSocial_Studies("What is the purpose of the United Nations (UN)?",
                "To maintain international peace and security.",
                "To promote the interests of the most powerful nations.",
                "To control global economic policies.",
                "To enforce military interventions worldwide.",
                "To maintain international peace and security."));

        questions.add(new Form3QuestionSocial_Studies("Define democracy.",
                "A system of government by the people, typically through elected representatives.",
                "A system of government controlled by a single political party.",
                "A system of government led by military generals.",
                "A system of government based on hereditary rule.",
                "A system of government by the people, typically through elected representatives."));

        questions.add(new Form3QuestionSocial_Studies("What is cultural diffusion?",
                "The spread of cultural beliefs and social activities from one group to another.",
                "The domination of one culture over others.",
                "The isolation of cultures from outside influences.",
                "The deliberate suppression of cultural practices.",
                "The spread of cultural beliefs and social activities from one group to another."));

        questions.add(new Form3QuestionSocial_Studies("Explain the term 'human geography'.",
                "The branch of geography that focuses on the human aspects of the world, including population, culture, economics, and urbanization.",
                "The study of the physical features of the Earth's surface.",
                "The exploration of space beyond Earth's atmosphere.",
                "The study of ancient human civilizations.",
                "The branch of geography that focuses on the human aspects of the world, including population, culture, economics, and urbanization."));

        questions.add(new Form3QuestionSocial_Studies("What is sustainable development?",
                "Development that meets the needs of the present without compromising the ability of future generations to meet their own needs.",
                "Development that focuses exclusively on economic growth.",
                "Development that prioritizes military expansion.",
                "Development that promotes immediate resource extraction.",
                "Development that meets the needs of the present without compromising the ability of future generations to meet their own needs."));

        questions.add(new Form3QuestionSocial_Studies("Define apartheid.",
                "A policy of racial segregation and discrimination enforced by the South African government from 1948 to 1994.",
                "A system of governance based on democratic principles.",
                "A term used to describe cultural diversity.",
                "A political movement advocating for equal rights globally.",
                "A policy of racial segregation and discrimination enforced by the South African government from 1948 to 1994."));

        questions.add(new Form3QuestionSocial_Studies("What is the role of NGOs (Non-Governmental Organizations) in society?",
                "To address social issues and advocate for specific causes, often in collaboration with governments and international organizations.",
                "To enforce laws and regulations at the local level.",
                "To provide military support during conflicts.",
                "To control global economic policies.",
                "To address social issues and advocate for specific causes, often in collaboration with governments and international organizations."));

        questions.add(new Form3QuestionSocial_Studies("Explain the concept of economic globalization.",
                "The increasing integration and interdependence of national economies across the world through trade, investment, and technology.",
                "The decline in economic activities due to regional conflicts.",
                "The policy of restricting foreign investments.",
                "The promotion of local industries over international competition.",
                "The increasing integration and interdependence of national economies across the world through trade, investment, and technology."));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form3QuestionSocial_Studies currentQuestion = questions.get(currentQuestionIndex);
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
