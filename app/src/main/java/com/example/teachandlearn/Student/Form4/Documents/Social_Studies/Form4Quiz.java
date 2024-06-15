

package com.example.teachandlearn.Student.Form4.Documents.Social_Studies;
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


        questions.add(new Form4Question("Explain the factors that led to the outbreak of World War II.",
                "Factors include the Treaty of Versailles, rise of totalitarian regimes, militarism, nationalism, and economic instability.",
                "Factors include the Cold War, spread of communism, and territorial disputes.",
                "Factors include decolonization, economic cooperation, and global trade.",
                "Factors include the Renaissance, religious conflicts, and cultural exchange.",
                "Factors include the Treaty of Versailles, rise of totalitarian regimes, militarism, nationalism, and economic instability."));

        questions.add(new Form4Question("Discuss the impact of globalization on cultural diversity.",
                "Globalization has led to the spread of cultural practices, languages, and traditions, resulting in cultural homogenization in some areas.",
                "Globalization has led to the preservation of cultural identities and diversity across the globe.",
                "Globalization has had no impact on cultural diversity.",
                "Globalization has led to the dominance of one culture over others.",
                "Globalization has led to the spread of cultural practices, languages, and traditions, resulting in cultural homogenization in some areas."));

        questions.add(new Form4Question("Explain the concept of human rights and their significance in global governance.",
                "Human rights are rights inherent to all human beings, regardless of race, nationality, or religion. They are essential for promoting dignity, equality, and justice worldwide.",
                "Human rights are rights granted to governments by international organizations.",
                "Human rights are rights limited to certain groups based on ethnicity or religion.",
                "Human rights are rights granted to corporations and businesses.",
                "Human rights are rights inherent to all human beings, regardless of race, nationality, or religion. They are essential for promoting dignity, equality, and justice worldwide."));

        questions.add(new Form4Question("Discuss the causes and consequences of climate change.",
                "Causes include greenhouse gas emissions from human activities, deforestation, and industrialization. Consequences include rising global temperatures, sea level rise, and extreme weather events.",
                "Causes include volcanic eruptions and solar radiation. Consequences include increased biodiversity and agricultural productivity.",
                "Causes include ocean currents and tectonic plate movements. Consequences include reduced rainfall and desertification.",
                "Causes include political conflicts and economic instability. Consequences include urbanization and industrial growth.",
                "Causes include greenhouse gas emissions from human activities, deforestation, and industrialization. Consequences include rising global temperatures, sea level rise, and extreme weather events."));

        questions.add(new Form4Question("Explain the role of the United Nations in promoting global peace and security.",
                "The United Nations aims to maintain international peace and security through collective security arrangements, peacekeeping operations, and conflict prevention initiatives.",
                "The United Nations aims to promote economic development through global trade agreements.",
                "The United Nations aims to enforce military interventions in sovereign states.",
                "The United Nations aims to promote cultural exchanges and tourism.",
                "The United Nations aims to maintain international peace and security through collective security arrangements, peacekeeping operations, and conflict prevention initiatives."));

        questions.add(new Form4Question("Discuss the impact of colonialism on African societies.",
                "Colonialism led to the exploitation of African resources, cultural assimilation, political instability, and economic dependency.",
                "Colonialism led to industrialization and modernization of African economies.",
                "Colonialism led to the preservation of African cultural heritage and traditions.",
                "Colonialism led to the establishment of democratic governance in African countries.",
                "Colonialism led to the exploitation of African resources, cultural assimilation, political instability, and economic dependency."));

        questions.add(new Form4Question("Explain the concept of sustainable development and its importance.",
                "Sustainable development aims to meet the needs of the present without compromising the ability of future generations to meet their own needs. It promotes economic growth, social inclusion, and environmental protection.",
                "Sustainable development aims to exploit natural resources for short-term economic gains.",
                "Sustainable development aims to achieve rapid industrialization at the expense of environmental degradation.",
                "Sustainable development aims to promote inequality and social injustice.",
                "Sustainable development aims to meet the needs of the present without compromising the ability of future generations to meet their own needs. It promotes economic growth, social inclusion, and environmental protection."));

        questions.add(new Form4Question("Discuss the impact of globalization on economic development in developing countries.",
                "Globalization has led to increased trade, investment, and technological transfer, but it has also exacerbated income inequality and economic dependency in many developing countries.",
                "Globalization has had no impact on economic development in developing countries.",
                "Globalization has led to the isolation of developing countries from the global economy.",
                "Globalization has led to the complete industrialization of developing countries.",
                "Globalization has led to increased trade, investment, and technological transfer, but it has also exacerbated income inequality and economic dependency in many developing countries."));

        questions.add(new Form4Question("Explain the concept of democracy and its significance in governance.",
                "Democracy is a system of government in which power is vested in the people, who exercise it directly or through elected representatives. It promotes political participation, accountability, and protection of human rights.",
                "Democracy is a system of government controlled by a single political party.",
                "Democracy is a system of government that excludes certain groups based on ethnicity or religion.",
                "Democracy is a system of government that promotes dictatorship and authoritarian rule.",
                "Democracy is a system of government in which power is vested in the people, who exercise it directly or through elected representatives. It promotes political participation, accountability, and protection of human rights."));

        questions.add(new Form4Question("Discuss the role of non-governmental organizations (NGOs) in promoting social change.",
                "NGOs play a crucial role in advocating for human rights, providing humanitarian aid, promoting environmental conservation, and empowering marginalized communities.",
                "NGOs play no role in promoting social change.",
                "NGOs play a role only in economic development.",
                "NGOs play a role only in political advocacy.",
                "NGOs play a crucial role in advocating for human rights, providing humanitarian aid, promoting environmental conservation, and empowering marginalized communities."));

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
