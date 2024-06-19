package com.example.teachandlearn.Student.Form1.Documents.Bible_Knowledge;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;
import com.example.teachandlearn.Student.Form1.Documents.Agriculture.Form1QuestionAgriculture;

import java.util.ArrayList;
import java.util.List;

public class Form1QuizBible_Knowledge extends AppCompatActivity {

    private TextView questionTextView, questionNumberTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form1QuestionBible_Knowledge> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private Button nextButton, backButton;
    private RadioGroup optionsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_bk_quizz);

        questionTextView = findViewById(R.id.questionTextViewBK);
        questionNumberTextView = findViewById(R.id.questionNumberTextViewBK);
        optionARadioButton = findViewById(R.id.optionARadioButtonBK);
        optionBRadioButton = findViewById(R.id.optionBRadioButtonBK);
        optionCRadioButton = findViewById(R.id.optionCRadioButtonBK);
        optionDRadioButton = findViewById(R.id.optionDRadioButtonBK);
        nextButton = findViewById(R.id.nextButtonBK);
        backButton = findViewById(R.id.back_buttonBK);
        optionsGroup = findViewById(R.id.optionsGroupBK);


        // Initialize questions
        questions = loadQuestions();

        if (questions == null || questions.isEmpty()) {
            Toast.makeText(this, "No questions available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        displayQuestion();

        nextButton.setOnClickListener(v -> displayNextQuestion());

        backButton.setOnClickListener(v -> displayPreviousQuestion());
    }

    private List<Form1QuestionBible_Knowledge> loadQuestions() {
        List<Form1QuestionBible_Knowledge> questions = new ArrayList<>();
        questions.add(new Form1QuestionBible_Knowledge("Who was swallowed by a great fish?", "Elijah", "Daniel", "Joseph", "Jonah", "Jonah"));
        questions.add(new Form1QuestionBible_Knowledge("What is the first book of the Bible?", "Exodus", "Leviticus", "Genesis", "Numbers", "Genesis"));
        questions.add(new Form1QuestionBible_Knowledge("Who was the first man created by God?", "Noah", "Abraham", "Isaac", "Adam", "Adam"));
        questions.add(new Form1QuestionBible_Knowledge("Who built an ark to survive the great flood?", "Moses", "David", "Solomon", "Noah", "Noah"));
        questions.add(new Form1QuestionBible_Knowledge("What is the name of the sea that Moses parted?", "Dead Sea", "Sea of Galilee", "Mediterranean Sea", "Red Sea", "Red Sea"));
        questions.add(new Form1QuestionBible_Knowledge("Who is known as the father of many nations?", "Isaac", "Jacob", "Joseph", "Abraham", "Abraham"));
        questions.add(new Form1QuestionBible_Knowledge("Who was thrown into the lions' den?", "Shadrach", "Meshach", "Daniel", "Abednego", "Daniel"));
        questions.add(new Form1QuestionBible_Knowledge("What food did God provide the Israelites in the desert?", "Quail", "Bread", "Fish", "Manna", "Manna"));
        questions.add(new Form1QuestionBible_Knowledge("Who defeated Goliath with a sling and a stone?", "Saul", "Samson", "David", "Solomon", "David"));
        questions.add(new Form1QuestionBible_Knowledge("What was the name of the place where Jesus was crucified?", "Bethlehem", "Nazareth", "Golgotha", "Jerusalem", "Golgotha"));
        return questions;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Form1QuestionBible_Knowledge currentQuestion = questions.get(currentQuestionIndex);
            questionNumberTextView.setText("Question " + (currentQuestionIndex + 1) + " of " + questions.size());
            questionTextView.setText(currentQuestion.getQuestionText());
            optionARadioButton.setText(currentQuestion.getOptionA());
            optionBRadioButton.setText(currentQuestion.getOptionB());
            optionCRadioButton.setText(currentQuestion.getOptionC());
            optionDRadioButton.setText(currentQuestion.getOptionD());
            questionProgressBar.setProgress((int) (((float) currentQuestionIndex / questions.size()) * 100));

            // Restore user's previous answer if available
            String userAnswer = currentQuestion.getUserAnswer();
            if (userAnswer != null) {
                if (userAnswer.equals(optionARadioButton.getText().toString())) {
                    optionARadioButton.setChecked(true);
                } else if (userAnswer.equals(optionBRadioButton.getText().toString())) {
                    optionBRadioButton.setChecked(true);
                } else if (userAnswer.equals(optionCRadioButton.getText().toString())) {
                    optionCRadioButton.setChecked(true);
                } else if (userAnswer.equals(optionDRadioButton.getText().toString())) {
                    optionDRadioButton.setChecked(true);
                }
            } else {
                optionsGroup.clearCheck();
            }
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
        Form1QuestionBible_Knowledge currentQuestion = questions.get(currentQuestionIndex);
        currentQuestion.setUserAnswer(selectedAnswer);  // Save the user's answer
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            correctAnswers++;
        }
    }

    private void finishQuiz() {
        // Hide question and options
        questionTextView.setVisibility(View.GONE);
        optionsGroup.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);

        // Display grade
        double grade = (double) correctAnswers / questions.size() * 100;
        TextView gradeTextView = new TextView(this);
        System.out.println();
        gradeTextView.setText("Grade: " + String.format("%.2f", grade) + "%");
        System.out.println();
        gradeTextView.setTextSize(18);
        ((LinearLayout) findViewById(R.id.rootLayout)).addView(gradeTextView);

        // Display solutions
        for (int i = 0; i < questions.size(); i++) {
            Form1QuestionBible_Knowledge question = questions.get(i);

            TextView questionTextView = new TextView(this);
            questionTextView.setText("Question " + (i + 1) + ": " + question.getQuestionText());
            questionTextView.setTextSize(16);
            ((LinearLayout) findViewById(R.id.rootLayout)).addView(questionTextView);

            String[] options = {question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD()};
            for (String option : options) {
                TextView optionTextView = new TextView(this);
                optionTextView.setText(option);
                optionTextView.setTextSize(16);

                if (option.equals(question.getCorrectAnswer())) {
                    optionTextView.setTextColor(Color.GREEN);
                } else if (option.equals(question.getUserAnswer())) {
                    optionTextView.setTextColor(Color.RED);
                }

                ((LinearLayout) findViewById(R.id.rootLayout)).addView(optionTextView);
            }

            ((LinearLayout) findViewById(R.id.rootLayout)).addView(new TextView(this)); // Add some space between questions
        }
    }
}
