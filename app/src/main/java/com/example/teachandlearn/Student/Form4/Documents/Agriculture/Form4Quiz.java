

package com.example.teachandlearn.Student.Form4.Documents.Agriculture;

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

public class Form4Quiz extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form4Question> questions;
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

    private List<Form4Question> loadQuestions() {


        List<Form4Question> questions = new ArrayList<>();
        questions.add(new Form4Question("What is crop rotation?",
                "The practice of growing different types of crops in the same area in sequenced seasons.",
                "The method of planting crops in circular patterns.",
                "The technique of growing the same crop continuously in the same field.",
                "The process of rotating crops between different fields within a farm.",
                "The practice of growing different types of crops in the same area in sequenced seasons."));

        questions.add(new Form4Question("What are the benefits of using organic fertilizers over chemical fertilizers?",
                "Improves soil structure and fertility without causing harm to the environment.",
                "Increases crop yield rapidly but may harm the environment.",
                "Is cheaper to produce and has no environmental impact.",
                "Requires less labor and is more cost-effective.",
                "Improves soil structure and fertility without causing harm to the environment."));

        questions.add(new Form4Question("Explain integrated pest management (IPM).",
                "A sustainable approach to managing pests by combining biological, cultural, physical, and chemical tools.",
                "The use of only chemical pesticides to control pests.",
                "The complete exclusion of any pest control measures.",
                "A traditional method of pest control relying solely on natural predators.",
                "A sustainable approach to managing pests by combining biological, cultural, physical, and chemical tools."));

        questions.add(new Form4Question("What is the importance of soil pH in agriculture?",
                "It affects the availability of nutrients to plants and the activity of soil microorganisms.",
                "It determines the soil's temperature and moisture content.",
                "It influences the soil's physical structure and water-holding capacity.",
                "It impacts the amount of sunlight the soil can absorb.",
                "It affects the availability of nutrients to plants and the activity of soil microorganisms."));

        questions.add(new Form4Question("Describe the process of selective breeding in livestock.",
                "Choosing animals with desirable traits to reproduce, enhancing those traits in future generations.",
                "Randomly mating animals without considering their traits.",
                "Breeding animals in controlled environments only.",
                "Using genetic engineering to alter animal traits directly.",
                "Choosing animals with desirable traits to reproduce, enhancing those traits in future generations."));

        questions.add(new Form4Question("What are leguminous crops, and why are they important in agriculture?",
                "Crops that can fix atmospheric nitrogen, improving soil fertility.",
                "Crops that grow in waterlogged conditions and enhance soil drainage.",
                "Crops that require minimal sunlight and improve crop diversity.",
                "Crops that are resistant to pests and diseases, reducing the need for pesticides.",
                "Crops that can fix atmospheric nitrogen, improving soil fertility."));

        questions.add(new Form4Question("What is the role of irrigation in crop production?",
                "To provide water to crops, ensuring their growth and development, especially in areas with insufficient rainfall.",
                "To enhance the flavor and nutritional value of crops.",
                "To prevent soil erosion by maintaining soil moisture levels.",
                "To reduce the growth of weeds by flooding the fields.",
                "To provide water to crops, ensuring their growth and development, especially in areas with insufficient rainfall."));

        questions.add(new Form4Question("Explain the concept of sustainable agriculture.",
                "Farming that meets current food needs without compromising the ability of future generations to meet their own needs.",
                "Agriculture that focuses solely on increasing crop yield using any available method.",
                "The practice of farming only high-value crops for export.",
                "The use of technology and machinery to maximize production efficiency.",
                "Farming that meets current food needs without compromising the ability of future generations to meet their own needs."));

        questions.add(new Form4Question("What are cover crops, and how do they benefit soil health?",
                "Crops grown to protect and enrich the soil, preventing erosion and improving soil structure.",
                "Crops grown specifically for human consumption.",
                "Crops that are planted to attract pollinators.",
                "Crops that require no additional nutrients or water.",
                "Crops grown to protect and enrich the soil, preventing erosion and improving soil structure."));

        questions.add(new Form4Question("What is agroforestry, and what are its benefits?",
                "The integration of trees and shrubs into agricultural landscapes, enhancing biodiversity and sustainability.",
                "The exclusive cultivation of tree crops for timber production.",
                "The practice of rotating forestry and agriculture in the same land area.",
                "The use of forests for recreational and tourism purposes.",
                "The integration of trees and shrubs into agricultural landscapes, enhancing biodiversity and sustainability."));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form4Question currentQuestion = questions.get(currentQuestionIndex);
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
