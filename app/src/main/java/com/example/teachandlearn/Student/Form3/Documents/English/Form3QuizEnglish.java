

package com.example.teachandlearn.Student.Form3.Documents.English;

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

public class Form3QuizEnglish extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form3QuestionEnglish> questions;
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

    private List<Form3QuestionEnglish> loadQuestions() {


        List<Form3QuestionEnglish> questions = new ArrayList<>();

        questions.add(new Form3QuestionEnglish("Identify the part of speech of the word 'quickly' in the sentence: She ran quickly to catch the bus.",
                "Adverb",
                "Verb",
                "Adjective",
                "Noun",
                "Adverb"));

        questions.add(new Form3QuestionEnglish("In the sentence 'The cat sat on the mat.', what is the prepositional phrase?",
                "On the mat",
                "The cat sat",
                "The cat",
                "The mat",
                "On the mat"));

        questions.add(new Form3QuestionEnglish("Identify the type of clause in the sentence: 'Although she studied hard, she failed the exam.'",
                "Adverbial clause",
                "Noun clause",
                "Relative clause",
                "Independent clause",
                "Adverbial clause"));

        questions.add(new Form3QuestionEnglish("What is the function of the gerund phrase in the sentence: 'Swimming in the lake is his favorite hobby.'",
                "Subject",
                "Object",
                "Predicate",
                "Adverb",
                "Subject"));

        questions.add(new Form3QuestionEnglish("Which word is a conjunction in the sentence: 'He wanted to go, but he had to stay.'",
                "But",
                "He",
                "Go",
                "Wanted",
                "But"));

        questions.add(new Form3QuestionEnglish("Identify the type of sentence: 'Will you go to the party?'",
                "Interrogative",
                "Declarative",
                "Imperative",
                "Exclamatory",
                "Interrogative"));

        questions.add(new Form3QuestionEnglish("What is the correct question tag for the sentence: 'She is coming, _____?'",
                "Isn't she?",
                "Is she?",
                "Doesn't she?",
                "Does she?",
                "Isn't she?"));

        questions.add(new Form3QuestionEnglish("In the sentence 'The book that I borrowed is on the table.', what type of clause is 'that I borrowed'?",
                "Relative clause",
                "Adverbial clause",
                "Noun clause",
                "Independent clause",
                "Relative clause"));

        questions.add(new Form3QuestionEnglish("Identify the part of speech of the word 'beautiful' in the sentence: 'She has a beautiful voice.'",
                "Adjective",
                "Noun",
                "Verb",
                "Adverb",
                "Adjective"));

        questions.add(new Form3QuestionEnglish("What is the function of the infinitive phrase in the sentence: 'To swim in the ocean is his dream.'",
                "Subject",
                "Object",
                "Predicate",
                "Adverb",
                "Subject"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form3QuestionEnglish currentQuestion = questions.get(currentQuestionIndex);
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
