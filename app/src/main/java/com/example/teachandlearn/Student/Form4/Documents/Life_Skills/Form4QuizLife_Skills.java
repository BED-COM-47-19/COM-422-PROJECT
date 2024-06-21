

package com.example.teachandlearn.Student.Form4.Documents.Life_Skills;

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

public class Form4QuizLife_Skills extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form4QuestionLife_Skills> questions;
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

    private List<Form4QuestionLife_Skills> loadQuestions() {


        List<Form4QuestionLife_Skills> questions = new ArrayList<>();
        questions.add(new Form4QuestionLife_Skills("Define financial literacy and explain its importance in modern society.",
                "Understanding how money works and managing finances effectively. It is crucial for making informed financial decisions and achieving financial stability.",
                "Knowledge of different currencies and their exchange rates. It helps in international business transactions.",
                "Knowing how to invest in stocks and bonds. It ensures long-term financial security.",
                "Understanding economic theories and policies. It helps in predicting future economic trends.",
                "Understanding how money works and managing finances effectively. It is crucial for making informed financial decisions and achieving financial stability."));

        questions.add(new Form4QuestionLife_Skills("Describe the steps involved in conflict resolution and give an example of each.",
                "Identifying the issue, discussing concerns openly, and seeking a mutually acceptable solution. Example: Resolving a disagreement between friends over conflicting schedules.",
                "Ignoring the problem and hoping it will go away. Example: Avoiding confrontation with a classmate who borrowed your book without asking.",
                "Demanding your way without considering the other person's perspective. Example: Insisting on a specific study group time without consulting group members.",
                "Seeking revenge for a perceived wrong. Example: Spreading rumors about a classmate who accidentally damaged your project.",
                "Identifying the issue, discussing concerns openly, and seeking a mutually acceptable solution. Example: Resolving a disagreement between friends over conflicting schedules."));

        questions.add(new Form4QuestionLife_Skills("Explain the concept of time management and discuss strategies for improving time management skills.",
                "Time management involves prioritizing tasks, setting goals, and planning activities to make efficient use of time. Strategies include creating a daily schedule, setting deadlines, and minimizing distractions.",
                "Time management means working as quickly as possible to complete tasks. Strategies include multi-tasking and skipping breaks.",
                "Time management is about letting things happen naturally without planning. Strategies include waiting until the last minute to start assignments.",
                "Time management is only necessary for people with busy schedules. Strategies include doing things whenever you feel like it.",
                "Time management involves prioritizing tasks, setting goals, and planning activities to make efficient use of time. Strategies include creating a daily schedule, setting deadlines, and minimizing distractions."));

        questions.add(new Form4QuestionLife_Skills("Discuss the benefits and challenges of teamwork in achieving common goals.",
                "Benefits include pooling skills and knowledge, fostering creativity, and achieving larger tasks efficiently. Challenges may include communication barriers, conflicting personalities, and unequal contributions.",
                "Benefits include individual recognition and competition. Challenges may include sharing credit for success and being held responsible for failures.",
                "Benefits include avoiding conflicts and working independently. Challenges may include increased workload and lack of support.",
                "Benefits include following instructions and minimizing responsibility. Challenges may include poor decision-making and lack of motivation.",
                "Benefits include pooling skills and knowledge, fostering creativity, and achieving larger tasks efficiently. Challenges may include communication barriers, conflicting personalities, and unequal contributions."));

        questions.add(new Form4QuestionLife_Skills("Explain the concept of environmental conservation and discuss its significance for sustainable development.",
                "Environmental conservation involves protecting natural resources, reducing pollution, and preserving biodiversity. It ensures the well-being of future generations and maintains ecological balance.",
                "Environmental conservation means using resources without limits. It promotes economic growth.",
                "Environmental conservation means preserving only endangered species. It focuses on aesthetics and tourism.",
                "Environmental conservation means maintaining existing technology. It supports industrial development.",
                "Environmental conservation involves protecting natural resources, reducing pollution, and preserving biodiversity. It ensures the well-being of future generations and maintains ecological balance."));

        questions.add(new Form4QuestionLife_Skills("Define assertiveness and explain how it differs from aggression and passivity.",
                "Assertiveness involves expressing one's own needs and opinions in a respectful and confident manner while considering the rights and feelings of others. It differs from aggression, which involves hostile behavior, and passivity, which involves avoiding conflict and neglecting one's own needs.",
                "Assertiveness means avoiding conflict. It involves pleasing others.",
                "Assertiveness means forcing others to agree with you. It involves dominating conversations.",
                "Assertiveness means ignoring others. It involves criticizing and blaming others.",
                "Assertiveness involves expressing one's own needs and opinions in a respectful and confident manner while considering the rights and feelings of others. It differs from aggression, which involves hostile behavior, and passivity, which involves avoiding conflict and neglecting one's own needs."));

        questions.add(new Form4QuestionLife_Skills("Discuss the importance of critical thinking in decision-making and problem-solving.",
                "Critical thinking involves analyzing information, evaluating options, and making reasoned judgments. It enhances decision-making by considering multiple perspectives and potential consequences. It also improves problem-solving by identifying root causes and generating effective solutions.",
                "Critical thinking means making decisions quickly without considering consequences. It involves following gut feelings.",
                "Critical thinking means following others' opinions without questioning. It involves conforming to group norms.",
                "Critical thinking means avoiding difficult decisions. It involves procrastinating.",
                "Critical thinking involves analyzing information, evaluating options, and making reasoned judgments. It enhances decision-making by considering multiple perspectives and potential consequences. It also improves problem-solving by identifying root causes and generating effective solutions."));

        questions.add(new Form4QuestionLife_Skills("Explain the concept of civic responsibility and discuss its role in promoting social change.",
                "Civic responsibility involves active participation in community affairs, respecting laws, and advocating for social justice. It plays a crucial role in promoting positive social change by fostering cooperation and addressing societal issues.",
                "Civic responsibility means avoiding involvement in community affairs. It involves following rules passively.",
                "Civic responsibility means ignoring laws. It involves breaking rules for personal gain.",
                "Civic responsibility means criticizing others' efforts. It involves blaming others for social issues.",
                "Civic responsibility involves active participation in community affairs, respecting laws, and advocating for social justice. It plays a crucial role in promoting positive social change by fostering cooperation and addressing societal issues."));

        questions.add(new Form4QuestionLife_Skills("Discuss the impact of globalization on cultural diversity and identity.",
                "Globalization facilitates cultural exchange and integration but may also lead to cultural homogenization and loss of traditional practices. It challenges cultural identities by promoting global norms and values.",
                "Globalization means protecting cultural heritage. It involves isolating cultures.",
                "Globalization means avoiding cultural exchange. It involves limiting interactions.",
                "Globalization means preserving cultural diversity. It involves promoting cultural exchange.",
                "Globalization facilitates cultural exchange and integration but may also lead to cultural homogenization and loss of traditional practices. It challenges cultural identities by promoting global norms and values."));

        questions.add(new Form4QuestionLife_Skills("Explain the concept of resilience and discuss its importance in overcoming adversity.",
                "Resilience is the ability to adapt and bounce back from difficult experiences. It involves maintaining a positive outlook, seeking support, and learning from setbacks. It plays a crucial role in building personal strength and coping with challenges effectively.",
                "Resilience means avoiding challenges. It involves fearing adversity.",
                "Resilience means succumbing to difficulties. It involves giving up easily.",
                "Resilience means criticizing oneself. It involves blaming others for setbacks.",
                "Resilience is the ability to adapt and bounce back from difficult experiences. It involves maintaining a positive outlook, seeking support, and learning from setbacks. It plays a crucial role in building personal strength and coping with challenges effectively."));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form4QuestionLife_Skills currentQuestion = questions.get(currentQuestionIndex);
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
