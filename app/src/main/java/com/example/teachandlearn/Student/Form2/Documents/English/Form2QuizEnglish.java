

package com.example.teachandlearn.Student.Form2.Documents.English;

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

public class Form2QuizEnglish extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form2QuestionEnglish> questions;
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

    private List<Form2QuestionEnglish> loadQuestions() {


        List<Form2QuestionEnglish> questions = new ArrayList<>();

        questions.add(new Form2QuestionEnglish("Read the following passage:\n\n"
                + "The sun was setting behind the hills, casting a warm orange glow over the valley. "
                + "Birds were returning to their nests, chirping happily as they settled for the night. "
                + "What is the mood of this passage?",
                "Excitement",
                "Sadness",
                "Calmness",
                "Anger",
                "Calmness"));

        questions.add(new Form2QuestionEnglish("What is the meaning of the word 'perplexed'?",
                "Confused",
                "Happy",
                "Angry",
                "Excited",
                "Confused"));

        questions.add(new Form2QuestionEnglish("Choose the correct sentence:\n\n"
                + "a) She don't like cheese.\n"
                + "b) He doesn't like cheese.",
                "a",
                "b",
                "Both are correct",
                "Neither is correct",
                "b"));

        questions.add(new Form2QuestionEnglish("Who is the protagonist in the novel 'To Kill a Mockingbird'?",
                "Atticus Finch",
                "Scout Finch",
                "Boo Radley",
                "Mayella Ewell",
                "Scout Finch"));

        questions.add(new Form2QuestionEnglish("Write a synonym for 'happy'.",
                "Sad",
                "Joyful",
                "Angry",
                "Tired",
                "Joyful"));

        questions.add(new Form2QuestionEnglish("What is the central theme of the poem 'The Road Not Taken' by Robert Frost?",
                "Regret",
                "Celebration",
                "Adventure",
                "Hope",
                "Regret"));

        questions.add(new Form2QuestionEnglish("Which literary device is used in the phrase 'time flies'?",
                "Simile",
                "Metaphor",
                "Personification",
                "Hyperbole",
                "Metaphor"));

        questions.add(new Form2QuestionEnglish("What are the traits of Sherlock Holmes?",
                "Kind and shy",
                "Brave and honest",
                "Intelligent and observant",
                "Cruel and mean",
                "Intelligent and observant"));

        questions.add(new Form2QuestionEnglish("Do you agree or disagree with the statement: 'Reading fiction books is more beneficial than reading non-fiction books'?",
                "Agree",
                "Disagree",
                "Partially agree",
                "Not sure",
                "Depends on personal preference"));

        questions.add(new Form2QuestionEnglish("Which sentence uses correct punctuation?",
                "I went to the store and bought milk apples and bread",
                "I went to the store, and bought milk, apples, and bread",
                "I went to the store and bought milk, apples, and bread.",
                "I went to the store and bought milk, apples and bread",
                "I went to the store, and bought milk, apples, and bread"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form2QuestionEnglish currentQuestion = questions.get(currentQuestionIndex);
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
