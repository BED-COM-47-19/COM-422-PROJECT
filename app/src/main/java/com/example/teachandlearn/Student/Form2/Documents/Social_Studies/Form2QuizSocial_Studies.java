

package com.example.teachandlearn.Student.Form2.Documents.Social_Studies;

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

public class Form2QuizSocial_Studies extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form2QuestionSocial_Studies> questions;
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

    private List<Form2QuestionSocial_Studies> loadQuestions() {


        List<Form2QuestionSocial_Studies> questions = new ArrayList<>();


        questions.add(new Form2QuestionSocial_Studies("What are the main functions of the government in a democratic society?",
                "To maintain law and order, provide public services, and protect citizens' rights",
                "To control the media and limit freedom of speech",
                "To promote inequality and restrict voting rights",
                "To encourage corruption and discourage public participation",
                "To maintain law and order, provide public services, and protect citizens' rights"));

        questions.add(new Form2QuestionSocial_Studies("Explain the concept of human rights.",
                "Rights inherent to all human beings, regardless of race, nationality, religion, or any other status",
                "Rights that can be granted or revoked by governments",
                "Rights that are only applicable to citizens of a country",
                "Rights that are only applicable during times of peace",
                "Rights inherent to all human beings, regardless of race, nationality, religion, or any other status"));

        questions.add(new Form2QuestionSocial_Studies("What is the role of non-governmental organizations (NGOs) in society?",
                "To advocate for specific social causes, provide humanitarian aid, and monitor government actions",
                "To control political parties and influence elections",
                "To create conflicts and disrupt peace",
                "To promote censorship and restrict freedom of expression",
                "To advocate for specific social causes, provide humanitarian aid, and monitor government actions"));

        questions.add(new Form2QuestionSocial_Studies("Describe the impact of globalization on cultural diversity.",
                "Globalization can lead to both the homogenization and diversification of cultures",
                "Globalization has no impact on cultural diversity",
                "Globalization decreases cultural interaction",
                "Globalization promotes cultural isolation",
                "Globalization can lead to both the homogenization and diversification of cultures"));

        questions.add(new Form2QuestionSocial_Studies("What are the causes and consequences of urbanization?",
                "Causes: Rural-urban migration; Consequences: Increased demand for housing and infrastructure, environmental degradation",
                "Causes: Decreased population; Consequences: Improved living standards",
                "Causes: Increased agricultural production; Consequences: Decreased unemployment",
                "Causes: Technological advancements; Consequences: Reduced pollution",
                "Causes: Rural-urban migration; Consequences: Increased demand for housing and infrastructure, environmental degradation"));

        questions.add(new Form2QuestionSocial_Studies("Explain the concept of sustainable development.",
                "Development that meets the needs of the present without compromising the ability of future generations to meet their own needs",
                "Development that focuses solely on economic growth",
                "Development that exploits natural resources without concern for the environment",
                "Development that promotes inequality among people",
                "Development that meets the needs of the present without compromising the ability of future generations to meet their own needs"));

        questions.add(new Form2QuestionSocial_Studies("What are the consequences of deforestation on the environment and society?",
                "Loss of biodiversity, soil erosion, climate change, and disruption of water cycles",
                "Increase in wildlife habitats and improvement in air quality",
                "Decrease in agricultural productivity and increase in carbon dioxide levels",
                "Reduction in urbanization and improvement in soil fertility",
                "Loss of biodiversity, soil erosion, climate change, and disruption of water cycles"));

        questions.add(new Form2QuestionSocial_Studies("Describe the significance of cultural heritage preservation.",
                "To maintain identity, promote diversity, and strengthen social cohesion",
                "To enforce cultural assimilation and eliminate diversity",
                "To promote cultural isolation and restrict interactions",
                "To create conflicts and promote inequality",
                "To maintain identity, promote diversity, and strengthen social cohesion"));

        questions.add(new Form2QuestionSocial_Studies("What are the effects of population growth on a country's development?",
                "Pressure on resources, increased demand for infrastructure, and challenges in providing social services",
                "Decreased economic activities and improved environmental sustainability",
                "Improved political stability and reduced poverty rates",
                "Decreased urbanization and increased rural-urban migration",
                "Pressure on resources, increased demand for infrastructure, and challenges in providing social services"));

        questions.add(new Form2QuestionSocial_Studies("Explain the concept of cultural diffusion.",
                "The spread of cultural beliefs, practices, and social activities from one society to another",
                "The isolation of cultural groups and avoidance of interactions",
                "The enforcement of cultural norms and restrictions",
                "The imposition of cultural values on others",
                "The spread of cultural beliefs, practices, and social activities from one society to another"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form2QuestionSocial_Studies currentQuestion = questions.get(currentQuestionIndex);
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
