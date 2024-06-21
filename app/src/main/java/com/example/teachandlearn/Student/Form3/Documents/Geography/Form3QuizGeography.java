

package com.example.teachandlearn.Student.Form3.Documents.Geography;

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

public class Form3QuizGeography extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form3QuestionGeography> questions;
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

    private List<Form3QuestionGeography> loadQuestions() {


        List<Form3QuestionGeography> questions = new ArrayList<>();
        questions.add(new Form3QuestionGeography("What is the difference between weather and climate?",
                "Weather refers to the short-term atmospheric conditions in a specific place at a specific time (e.g., daily temperature and precipitation). Climate, on the other hand, refers to the long-term average weather conditions of a region over a period of time (e.g., average temperature and precipitation patterns over decades).",
                "Weather refers to the long-term atmospheric conditions in a specific place at a specific time. Climate refers to the short-term average weather conditions of a region over a period of time.",
                "Weather refers to the daily variations in temperature and climate. Climate refers to the seasonal changes in weather patterns.",
                "Weather refers to the atmospheric conditions in urban areas. Climate refers to the atmospheric conditions in rural areas.",
                "Weather refers to the short-term atmospheric conditions in a specific place at a specific time (e.g., daily temperature and precipitation). Climate, on the other hand, refers to the long-term average weather conditions of a region over a period of time (e.g., average temperature and precipitation patterns over decades)."));

        questions.add(new Form3QuestionGeography("Explain the concept of population density.",
                "Population density is the average number of people living per square kilometer in a specific area. It is calculated by dividing the total population of an area by its total land area.",
                "Population density is the total number of people living in a specific area. It is calculated by dividing the total land area by the total population of an area.",
                "Population density is the average number of cities in a specific area. It is calculated by dividing the total population of an area by its total land area.",
                "Population density is the average number of animals in a specific area. It is calculated by dividing the total population of an area by its total land area.",
                "Population density is the average number of people living per square kilometer in a specific area. It is calculated by dividing the total population of an area by its total land area."));

        questions.add(new Form3QuestionGeography("What are the factors influencing population distribution?",
                "Factors influencing population distribution include physical factors (e.g., climate, terrain), economic factors (e.g., resources, job opportunities), social factors (e.g., culture, amenities), and political factors (e.g., government policies, stability).",
                "Factors influencing population distribution include economic factors only.",
                "Factors influencing population distribution include social factors only.",
                "Factors influencing population distribution include physical factors only.",
                "Factors influencing population distribution include physical factors (e.g., climate, terrain), economic factors (e.g., resources, job opportunities), social factors (e.g., culture, amenities), and political factors (e.g., government policies, stability)."));

        questions.add(new Form3QuestionGeography("Describe the process of urbanization.",
                "Urbanization is the increase in the proportion of people living in urban areas compared to rural areas. It involves the growth of cities due to factors such as rural-urban migration and natural population growth.",
                "Urbanization is the decrease in the proportion of people living in urban areas compared to rural areas.",
                "Urbanization is the migration of people from urban areas to rural areas.",
                "Urbanization is the stabilization of population in urban areas.",
                "Urbanization is the increase in the proportion of people living in urban areas compared to rural areas. It involves the growth of cities due to factors such as rural-urban migration and natural population growth."));

        questions.add(new Form3QuestionGeography("Explain the concept of sustainable development.",
                "Sustainable development is development that meets the needs of the present without compromising the ability of future generations to meet their own needs. It involves balancing economic growth, social development, and environmental protection.",
                "Sustainable development is development that focuses solely on economic growth.",
                "Sustainable development is development that focuses solely on social development.",
                "Sustainable development is development that focuses solely on environmental protection.",
                "Sustainable development is development that meets the needs of the present without compromising the ability of future generations to meet their own needs. It involves balancing economic growth, social development, and environmental protection."));

        questions.add(new Form3QuestionGeography("What are the factors influencing climate?",
                "Factors influencing climate include latitude, altitude, proximity to water bodies, ocean currents, wind patterns, and topography.",
                "Factors influencing climate include latitude only.",
                "Factors influencing climate include proximity to water bodies only.",
                "Factors influencing climate include altitude only.",
                "Factors influencing climate include latitude, altitude, proximity to water bodies, ocean currents, wind patterns, and topography."));

        questions.add(new Form3QuestionGeography("Discuss the causes and impacts of deforestation.",
                "Causes of deforestation include agricultural expansion, logging, infrastructure development, and mining. Impacts of deforestation include loss of biodiversity, soil erosion, disrupted water cycles, and climate change.",
                "Causes of deforestation include natural disasters only. Impacts of deforestation include increased biodiversity.",
                "Causes of deforestation include urbanization only. Impacts of deforestation include decreased soil erosion.",
                "Causes of deforestation include political factors only. Impacts of deforestation include climate change.",
                "Causes of deforestation include agricultural expansion, logging, infrastructure development, and mining. Impacts of deforestation include loss of biodiversity, soil erosion, disrupted water cycles, and climate change."));

        questions.add(new Form3QuestionGeography("Explain the concept of globalization.",
                "Globalization is the process of increased interconnectedness and interdependence among countries, economies, cultures, and societies due to advancements in technology, communication, and trade.",
                "Globalization is the process of isolating countries and economies from one another.",
                "Globalization is the process of reducing cultural diversity.",
                "Globalization is the process of decreasing trade between countries.",
                "Globalization is the process of increased interconnectedness and interdependence among countries, economies, cultures, and societies due to advancements in technology, communication, and trade."));

        questions.add(new Form3QuestionGeography("What are the major types of economic activities?",
                "The major types of economic activities include primary activities (e.g., agriculture, mining), secondary activities (e.g., manufacturing, industry), tertiary activities (e.g., services, retail), and quaternary activities (e.g., research, development).",
                "The major types of economic activities include urban activities only.",
                "The major types of economic activities include rural activities only.",
                "The major types of economic activities include industrial activities only.",
                "The major types of economic activities include primary activities (e.g., agriculture, mining), secondary activities (e.g., manufacturing, industry), tertiary activities (e.g., services, retail), and quaternary activities (e.g., research, development)."));

        questions.add(new Form3QuestionGeography("Explain the concept of a watershed.",
                "A watershed is an area of land that drains all the streams and rainfall to a common outlet such as a reservoir, lake, or ocean. It is also known as a drainage basin.",
                "A watershed is a type of river.",
                "A watershed is an area of land that only drains to a lake.",
                "A watershed is an area of land that only drains to a mountain.",
                "A watershed is an area of land that drains all the streams and rainfall to a common outlet such as a reservoir, lake, or ocean. It is also known as a drainage basin."));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form3QuestionGeography currentQuestion = questions.get(currentQuestionIndex);
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
