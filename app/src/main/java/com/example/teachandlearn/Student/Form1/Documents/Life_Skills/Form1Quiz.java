package com.example.teachandlearn.Student.Form1.Documents.Life_Skills;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;

import java.util.ArrayList;
import java.util.List;

public class Form1Quiz extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private List<Form1Question> questions;
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

    private List<Form1Question> loadQuestions() {
        List<Form1Question> questions = new ArrayList<>();

        questions.add(new Form1Question("What is the importance of good hygiene?",
                "It keeps you healthy and prevents diseases",
                "It saves money",
                "It helps you make more friends",
                "It is not important",
                "It keeps you healthy and prevents diseases"));

        questions.add(new Form1Question("How can you manage your time effectively?",
                "Procrastinate and do tasks at the last minute",
                "Create a schedule and prioritize tasks",
                "Do only one task at a time",
                "Avoid setting deadlines",
                "Create a schedule and prioritize tasks"));

        questions.add(new Form1Question("Why is it important to respect others?",
                "It is not important",
                "Respect helps build positive relationships",
                "It makes you appear weak",
                "Respect makes you look superior",
                "Respect helps build positive relationships"));

        questions.add(new Form1Question("What are the benefits of regular exercise?",
                "It wastes time",
                "It increases the risk of diseases",
                "It improves physical and mental health",
                "It is boring",
                "It improves physical and mental health"));

        questions.add(new Form1Question("How can you handle peer pressure effectively?",
                "Give in to peer pressure to fit in",
                "Stay true to your values and beliefs",
                "Ignore your friends' opinions",
                "Never interact with peers",
                "Stay true to your values and beliefs"));

        questions.add(new Form1Question("Why is it important to have a positive attitude?",
                "It doesn't matter",
                "It attracts negative people",
                "It improves your mood and outlook on life",
                "It makes you unpopular",
                "It improves your mood and outlook on life"));

        questions.add(new Form1Question("How can you handle conflicts peacefully?",
                "Use physical force",
                "Yell and shout",
                "Listen actively and discuss calmly",
                "Avoid the person forever",
                "Listen actively and discuss calmly"));

        questions.add(new Form1Question("What are the advantages of setting goals?",
                "Goals limit your potential",
                "Goals help you focus and achieve success",
                "Goals are unnecessary",
                "Goals make you lazy",
                "Goals help you focus and achieve success"));

        questions.add(new Form1Question("How can you improve your communication skills?",
                "Avoid talking to others",
                "Listen actively and speak clearly",
                "Interrupt others while they are speaking",
                "Use complicated words",
                "Listen actively and speak clearly"));

        questions.add(new Form1Question("Why is it important to develop problem-solving skills?",
                "To avoid challenges",
                "To become more resilient and adaptable",
                "To ignore problems",
                "To blame others",
                "To become more resilient and adaptable"));

        return questions;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Form1Question currentQuestion = questions.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestionText());
            optionARadioButton.setText(currentQuestion.getOptionA());
            optionBRadioButton.setText(currentQuestion.getOptionB());
            optionCRadioButton.setText(currentQuestion.getOptionC());
            optionDRadioButton.setText(currentQuestion.getOptionD());
        } else {
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
