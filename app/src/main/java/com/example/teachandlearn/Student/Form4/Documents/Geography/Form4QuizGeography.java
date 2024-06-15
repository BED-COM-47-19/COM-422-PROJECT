

package com.example.teachandlearn.Student.Form4.Documents.Geography;

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

public class Form4QuizGeography extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form4QuestionGeography> questions;
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

    private List<Form4QuestionGeography> loadQuestions() {


        List<Form4QuestionGeography> questions = new ArrayList<>();
        questions.add(new Form4QuestionGeography("What is the process by which water vapor turns into liquid water?",
                "Evaporation",
                "Condensation",
                "Precipitation",
                "Transpiration",
                "Condensation"));

        questions.add(new Form4QuestionGeography("Identify the type of rock formed from molten lava.",
                "Sedimentary rock",
                "Igneous rock",
                "Metamorphic rock",
                "Organic rock",
                "Igneous rock"));

        questions.add(new Form4QuestionGeography("What is the name of the line that separates the Earth into the Northern and Southern Hemispheres?",
                "Prime Meridian",
                "Equator",
                "Tropic of Cancer",
                "Tropic of Capricorn",
                "Equator"));

        questions.add(new Form4QuestionGeography("Which map feature explains the symbols used on a map?",
                "Scale",
                "Compass rose",
                "Legend",
                "Grid",
                "Legend"));

        questions.add(new Form4QuestionGeography("What term describes the height of land above sea level?",
                "Latitude",
                "Longitude",
                "Altitude",
                "Relief",
                "Altitude"));

        questions.add(new Form4QuestionGeography("What is the main cause of seasons on Earth?",
                "Earth's distance from the Sun",
                "The tilt of Earth's axis",
                "Earth's rotation",
                "The shape of Earth's orbit",
                "The tilt of Earth's axis"));

        questions.add(new Form4QuestionGeography("Which type of rainfall is common in tropical rainforests?",
                "Convectional rainfall",
                "Orographic rainfall",
                "Frontal rainfall",
                "Cyclonic rainfall",
                "Convectional rainfall"));

        questions.add(new Form4QuestionGeography("What is the main purpose of topographic maps?",
                "To show population distribution",
                "To depict physical and human features",
                "To illustrate climate patterns",
                "To highlight economic activities",
                "To depict physical and human features"));

        questions.add(new Form4QuestionGeography("What is a common characteristic of desert climates?",
                "High annual rainfall",
                "Low temperature variation",
                "Sparse vegetation",
                "High humidity",
                "Sparse vegetation"));

        questions.add(new Form4QuestionGeography("Which river is the longest in the world?",
                "Amazon River",
                "Yangtze River",
                "Nile River",
                "Mississippi River",
                "Nile River"));

        questions.add(new Form4QuestionGeography("Which human activity is the primary cause of deforestation?",
                "Agriculture",
                "Urbanization",
                "Industrialization",
                "Transportation",
                "Agriculture"));

        questions.add(new Form4QuestionGeography("What is urbanization?",
                "The movement of people from urban areas to rural areas",
                "The increase in the proportion of people living in rural areas",
                "The development of rural areas into urban areas",
                "The growth in the proportion of people living in urban areas",
                "The growth in the proportion of people living in urban areas"));

        questions.add(new Form4QuestionGeography("Which factor is most likely to influence the location of industries?",
                "Climate",
                "Availability of raw materials",
                "Population size",
                "Cultural heritage",
                "Availability of raw materials"));

        questions.add(new Form4QuestionGeography("What is a population pyramid?",
                "A graphical representation of population distribution by age and sex",
                "A chart showing population growth over time",
                "A diagram illustrating migration patterns",
                "A graph depicting birth and death rates",
                "A graphical representation of population distribution by age and sex"));

        questions.add(new Form4QuestionGeography("What is the main focus of human geography?",
                "Study of landforms and physical features",
                "Analysis of weather and climate",
                "Examination of human activities and their impact on the environment",
                "Observation of natural disasters",
                "Examination of human activities and their impact on the environment"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form4QuestionGeography currentQuestion = questions.get(currentQuestionIndex);
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
