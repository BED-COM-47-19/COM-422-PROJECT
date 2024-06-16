

package com.example.teachandlearn.Student.Form4.Documents.Bible_Knowledge;

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

public class Form4QuizBible_Knowledge extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form4QuestionBible_Knowledge> questions;
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

    private List<Form4QuestionBible_Knowledge> loadQuestions() {


        List<Form4QuestionBible_Knowledge> questions = new ArrayList<>();
        questions.add(new Form4QuestionBible_Knowledge("Who was the first king of Israel?",
                "David",
                "Solomon",
                "Saul",
                "Samuel",
                "Saul"));

        questions.add(new Form4QuestionBible_Knowledge("What is the significance of the Sermon on the Mount?",
                "It marks the birth of Jesus.",
                "It contains the core teachings of Jesus about the Kingdom of God.",
                "It describes the resurrection of Jesus.",
                "It details the miracles performed by Jesus.",
                "It contains the core teachings of Jesus about the Kingdom of God."));

        questions.add(new Form4QuestionBible_Knowledge("In which book of the Bible is the story of the Exodus found?",
                "Genesis",
                "Leviticus",
                "Numbers",
                "Exodus",
                "Exodus"));

        questions.add(new Form4QuestionBible_Knowledge("What is the main theme of the book of Proverbs?",
                "Prophecies about the Messiah",
                "Wisdom and how to live a righteous life",
                "The history of Israel",
                "The life of Jesus",
                "Wisdom and how to live a righteous life"));

        questions.add(new Form4QuestionBible_Knowledge("Who led the Israelites into the Promised Land after Moses' death?",
                "Aaron",
                "Joshua",
                "Caleb",
                "Gideon",
                "Joshua"));

        questions.add(new Form4QuestionBible_Knowledge("What is the significance of the New Covenant in Christianity?",
                "It promises land and prosperity to Israel.",
                "It establishes a new relationship between God and humanity through Jesus Christ.",
                "It describes the laws given to Moses.",
                "It details the genealogies of the tribes of Israel.",
                "It establishes a new relationship between God and humanity through Jesus Christ."));

        questions.add(new Form4QuestionBible_Knowledge("Which apostle is known for his missionary journeys in the book of Acts?",
                "Peter",
                "James",
                "John",
                "Paul",
                "Paul"));

        questions.add(new Form4QuestionBible_Knowledge("In the Bible, what does the term 'Gospel' mean?",
                "Good News",
                "Covenant",
                "Prophecy",
                "Law",
                "Good News"));

        questions.add(new Form4QuestionBible_Knowledge("What was the main message of John the Baptist?",
                "Prepare the way for the Lord and repent for the Kingdom of Heaven is near.",
                "Love your neighbor as yourself.",
                "Judge not, lest you be judged.",
                "Render unto Caesar what is Caesar's.",
                "Prepare the way for the Lord and repent for the Kingdom of Heaven is near."));

        questions.add(new Form4QuestionBible_Knowledge("Who wrote the majority of the New Testament letters?",
                "Peter",
                "Paul",
                "James",
                "John",
                "Paul"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form4QuestionBible_Knowledge currentQuestion = questions.get(currentQuestionIndex);
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
