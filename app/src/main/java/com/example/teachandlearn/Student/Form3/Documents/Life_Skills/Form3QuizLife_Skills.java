

package com.example.teachandlearn.Student.Form3.Documents.Life_Skills;

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

public class Form3QuizLife_Skills extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form3QuestionLife_Skills> questions;
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

    private List<Form3QuestionLife_Skills> loadQuestions() {


        List<Form3QuestionLife_Skills> questions = new ArrayList<>();
        questions.add(new Form3QuestionLife_Skills("What are the key components of effective communication?",
                "Key components of effective communication include clarity, active listening, empathy, non-verbal cues, and feedback.",
                "Key components of effective communication include speaking loudly, interrupting others, using complex vocabulary, and avoiding eye contact.",
                "Key components of effective communication include arguing, criticizing others, talking over others, and ignoring feedback.",
                "Key components of effective communication include speaking softly, avoiding eye contact, and using slang.",
                "Key components of effective communication include clarity, active listening, empathy, non-verbal cues, and feedback."));

        questions.add(new Form3QuestionLife_Skills("Discuss the importance of time management in achieving personal goals.",
                "Time management is crucial for prioritizing tasks, minimizing procrastination, and achieving personal goals efficiently. It helps individuals stay organized and productive.",
                "Time management is unnecessary for achieving personal goals.",
                "Time management leads to increased stress and anxiety.",
                "Time management focuses solely on relaxation.",
                "Time management is crucial for prioritizing tasks, minimizing procrastination, and achieving personal goals efficiently. It helps individuals stay organized and productive."));

        questions.add(new Form3QuestionLife_Skills("What are the benefits of teamwork in a school or work environment?",
                "Benefits of teamwork include improved problem-solving abilities, increased productivity, enhanced creativity, mutual support, and shared responsibilities.",
                "Benefits of teamwork include individual competition, lack of communication, and conflict.",
                "Benefits of teamwork include isolation and lack of collaboration.",
                "Benefits of teamwork include strict rules and regulations.",
                "Benefits of teamwork include improved problem-solving abilities, increased productivity, enhanced creativity, mutual support, and shared responsibilities."));

        questions.add(new Form3QuestionLife_Skills("Explain the concept of resilience and why it is important.",
                "Resilience is the ability to adapt and bounce back from challenges, adversity, and setbacks. It is important for maintaining mental well-being, achieving success despite obstacles, and developing coping skills.",
                "Resilience is the inability to adapt to challenges and setbacks.",
                "Resilience leads to increased stress and anxiety.",
                "Resilience focuses on avoiding challenges and setbacks.",
                "Resilience is the ability to adapt and bounce back from challenges, adversity, and setbacks. It is important for maintaining mental well-being, achieving success despite obstacles, and developing coping skills."));

        questions.add(new Form3QuestionLife_Skills("Discuss the importance of financial literacy for young adults.",
                "Financial literacy is essential for making informed financial decisions, managing money effectively, understanding investments, avoiding debt, and planning for the future.",
                "Financial literacy is unnecessary for young adults.",
                "Financial literacy leads to overspending and debt.",
                "Financial literacy focuses solely on earning money.",
                "Financial literacy is essential for making informed financial decisions, managing money effectively, understanding investments, avoiding debt, and planning for the future."));

        questions.add(new Form3QuestionLife_Skills("What are the characteristics of a healthy relationship?",
                "Characteristics of a healthy relationship include mutual respect, trust, effective communication, honesty, supportiveness, and shared interests.",
                "Characteristics of a healthy relationship include jealousy, lack of communication, disrespect, and dishonesty.",
                "Characteristics of a healthy relationship include competition and isolation.",
                "Characteristics of a healthy relationship include dependence on each other.",
                "Characteristics of a healthy relationship include mutual respect, trust, effective communication, honesty, supportiveness, and shared interests."));

        questions.add(new Form3QuestionLife_Skills("Discuss the importance of setting and achieving personal goals.",
                "Setting and achieving personal goals helps individuals stay focused, motivated, and accountable. It fosters personal growth, builds self-confidence, and provides direction in life.",
                "Setting and achieving personal goals is unnecessary.",
                "Setting and achieving personal goals leads to stress and anxiety.",
                "Setting and achieving personal goals focuses solely on external validation.",
                "Setting and achieving personal goals helps individuals stay focused, motivated, and accountable. It fosters personal growth, builds self-confidence, and provides direction in life."));

        questions.add(new Form3QuestionLife_Skills("What strategies can individuals use to manage stress effectively?",
                "Strategies for managing stress effectively include practicing relaxation techniques, exercising regularly, maintaining a balanced diet, seeking social support, and setting realistic goals.",
                "Strategies for managing stress effectively include avoiding responsibilities, isolation, and negative coping mechanisms.",
                "Strategies for managing stress effectively include increasing workload and ignoring self-care.",
                "Strategies for managing stress effectively include multitasking and overcommitting.",
                "Strategies for managing stress effectively include practicing relaxation techniques, exercising regularly, maintaining a balanced diet, seeking social support, and setting realistic goals."));

        questions.add(new Form3QuestionLife_Skills("Discuss the importance of empathy in building positive relationships.",
                "Empathy is essential for understanding others' perspectives, feelings, and emotions. It promotes compassion, cooperation, and mutual respect in relationships.",
                "Empathy is unnecessary in building positive relationships.",
                "Empathy leads to conflict and misunderstanding.",
                "Empathy focuses solely on personal emotions.",
                "Empathy is essential for understanding others' perspectives, feelings, and emotions. It promotes compassion, cooperation, and mutual respect in relationships."));

        questions.add(new Form3QuestionLife_Skills("What are the potential consequences of cyberbullying?",
                "Potential consequences of cyberbullying include emotional distress, low self-esteem, social isolation, depression, anxiety, academic problems, and in severe cases, self-harm or suicide.",
                "Potential consequences of cyberbullying include increased popularity and positive attention.",
                "Potential consequences of cyberbullying include improved mental health.",
                "Potential consequences of cyberbullying include strong social relationships.",
                "Potential consequences of cyberbullying include emotional distress, low self-esteem, social isolation, depression, anxiety, academic problems, and in severe cases, self-harm or suicide."));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form3QuestionLife_Skills currentQuestion = questions.get(currentQuestionIndex);
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
