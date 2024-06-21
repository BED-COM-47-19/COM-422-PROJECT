

package com.example.teachandlearn.Student.Form4.Documents.English;

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

public class Form4QuizEnglish extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form4QuestionEnglish> questions;
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

    private List<Form4QuestionEnglish> loadQuestions() {


        List<Form4QuestionEnglish> questions = new ArrayList<>();
        questions.add(new Form4QuestionEnglish("Identify the noun clause in the sentence: 'I believe that honesty is the best policy.'",
                "I believe",
                "that honesty is the best policy",
                "honesty is the best policy",
                "the best policy",
                "that honesty is the best policy"));

        questions.add(new Form4QuestionEnglish("Determine the type and function of the clause: 'Because she was late, she missed the bus.'",
                "Noun clause, subject of 'was'",
                "Adjective clause, modifying 'late'",
                "Adverb clause, showing reason",
                "Main clause, expressing a complete thought",
                "Adverb clause, showing reason"));

        questions.add(new Form4QuestionEnglish("What is the function of a prepositional phrase in the sentence: 'She sat on the chair.'",
                "To modify the noun 'she'",
                "To modify the verb 'sat'",
                "To connect the subject and the verb",
                "To describe the subject",
                "To modify the verb 'sat'"));

        questions.add(new Form4QuestionEnglish("Identify the type of phrase: 'Running quickly, he caught the bus.'",
                "Noun phrase",
                "Adjective phrase",
                "Adverb phrase",
                "Prepositional phrase",
                "Adverb phrase"));

        questions.add(new Form4QuestionEnglish("Convert the following direct speech into indirect speech: 'He said, \"I will go to the market tomorrow.\"'",
                "He said that he will go to the market tomorrow.",
                "He said that he would go to the market the next day.",
                "He says he will go to the market tomorrow.",
                "He said he will go to the market the next day.",
                "He said that he would go to the market the next day."));

        questions.add(new Form4QuestionEnglish("Identify the phrasal verb in the sentence: 'She looked up the information online.'",
                "looked",
                "up",
                "looked up",
                "information",
                "looked up"));

        questions.add(new Form4QuestionEnglish("Choose the sentence that contains a gerund:",
                "She is swimming in the pool.",
                "He is reading a book.",
                "Swimming is my favorite hobby.",
                "They are playing football.",
                "Swimming is my favorite hobby."));

        questions.add(new Form4QuestionEnglish("Identify the main clause in the sentence: 'Although it was raining, we went for a walk.'",
                "Although it was raining",
                "it was raining",
                "we went for a walk",
                "Although",
                "we went for a walk"));

        questions.add(new Form4QuestionEnglish("Choose the correct question tag: 'You haven't finished your homework, ______?'",
                "isn't it",
                "have you",
                "didn't you",
                "won't you",
                "have you"));

        questions.add(new Form4QuestionEnglish("Identify the part of speech of the word 'quickly' in the sentence: 'She quickly ran to the store.'",
                "Noun",
                "Adjective",
                "Verb",
                "Adverb",
                "Adverb"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form4QuestionEnglish currentQuestion = questions.get(currentQuestionIndex);
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
