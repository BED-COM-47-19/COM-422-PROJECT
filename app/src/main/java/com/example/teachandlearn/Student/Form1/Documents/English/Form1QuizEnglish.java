package com.example.teachandlearn.Student.Form1.Documents.English;

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

import java.util.ArrayList;
import java.util.List;

public class Form1QuizEnglish extends AppCompatActivity {

    private TextView questionTextView, questionNumberTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form1QuestionEnglish> form1Questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private Button nextButton, backButton;
    private RadioGroup optionsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_quizzes);

        questionTextView = findViewById(R.id.questionTextView);
        questionNumberTextView = findViewById(R.id.questionNumberTextView);
        optionARadioButton = findViewById(R.id.optionARadioButton);
        optionBRadioButton = findViewById(R.id.optionBRadioButton);
        optionCRadioButton = findViewById(R.id.optionCRadioButton);
        optionDRadioButton = findViewById(R.id.optionDRadioButton);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.back_button);
        optionsGroup = findViewById(R.id.optionsGroup);


        // Initialize questions
        form1Questions = loadQuestions();

        if (form1Questions == null || form1Questions.isEmpty()) {
            Toast.makeText(this, "No questions available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        displayQuestion();

        nextButton.setOnClickListener(v -> displayNextQuestion());

        backButton.setOnClickListener(v -> displayPreviousQuestion());
    }

    private List<Form1QuestionEnglish> loadQuestions() {
        List<Form1QuestionEnglish> form1Questions = new ArrayList<>();
        form1Questions.add(new Form1QuestionEnglish(
                "Identify the part of speech of the underlined word: The cat quickly ran away.",
                "Noun",
                "Verb",
                "Adjective",
                "Adverb",
                "Adverb"
        ));

        form1Questions.add(new Form1QuestionEnglish(
                "Which sentence has the correct question tag? She likes to dance, ____________?",
                "doesn't she?",
                "isn't she?",
                "hasn't she?",
                "do she?",
                "doesn't she?"
        ));

        form1Questions.add(new Form1QuestionEnglish(
                "Identify the type of phrase in the sentence: The boy with the red hat is my friend.",
                "Prepositional phrase",
                "Verb phrase",
                "Noun phrase",
                "Adjective phrase",
                "Prepositional phrase"
        ));

        form1Questions.add(new Form1QuestionEnglish(
                "Choose the correct form of the verb: They ____________ a new car last week.",
                "buy",
                "buys",
                "buying",
                "bought",
                "bought"
        ));

        form1Questions.add(new Form1QuestionEnglish(
                "Identify the type of clause in the sentence: Although it was raining, we went for a walk.",
                "Independent clause",
                "Dependent clause",
                "Subordinate clause",
                "Relative clause",
                "Subordinate clause"
        ));

        form1Questions.add(new Form1QuestionEnglish(
                "Choose the correct form of the adjective: This is the ____________ book I have ever read.",
                "good",
                "better",
                "best",
                "goodest",
                "best"
        ));

        form1Questions.add(new Form1QuestionEnglish(
                "Which sentence has the correct punctuation? My friend is coming over for dinner ____________",
                "My friend is coming over for dinner?",
                "My friend is coming over for dinner.",
                "My friend is coming over for dinner!",
                "My friend is coming over for dinner,",
                "My friend is coming over for dinner."
        ));

        form1Questions.add(new Form1QuestionEnglish(
                "Identify the conjunction in the sentence: She will go shopping if it stops raining.",
                "She",
                "will",
                "if",
                "stops",
                "if"
        ));

        form1Questions.add(new Form1QuestionEnglish(
                "Choose the correct sentence with a preposition: He jumped ____________ the fence.",
                "He jumped happy.",
                "He jumped quickly.",
                "He jumped over the fence.",
                "He jumped to the fence.",
                "He jumped over the fence."
        ));

        form1Questions.add(new Form1QuestionEnglish(
                "Which sentence is in the present perfect tense?",
                "She will go to the store.",
                "She went to the store yesterday.",
                "She goes to the store every day.",
                "She has gone to the store.",
                "She has gone to the store."
        ));

        return form1Questions;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < form1Questions.size()) {
            Form1QuestionEnglish currentForm1Question = form1Questions.get(currentQuestionIndex);
            questionNumberTextView.setText("Question " + (currentQuestionIndex + 1) + " of " + form1Questions.size());
            questionTextView.setText(currentForm1Question.getQuestionText());
            optionARadioButton.setText(currentForm1Question.getOptionA());
            optionBRadioButton.setText(currentForm1Question.getOptionB());
            optionCRadioButton.setText(currentForm1Question.getOptionC());
            optionDRadioButton.setText(currentForm1Question.getOptionD());
            questionProgressBar.setProgress((int) (((float) currentQuestionIndex / form1Questions.size()) * 100));

            // Restore user's previous answer if available
            String userAnswer = currentForm1Question.getUserAnswer();
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
        Form1QuestionEnglish currentForm1Question = form1Questions.get(currentQuestionIndex);
        currentForm1Question.setUserAnswer(selectedAnswer);  // Save the user's answer
        if (selectedAnswer.equals(currentForm1Question.getCorrectAnswer())) {
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
        double grade = (double) correctAnswers / form1Questions.size() * 100;
        TextView gradeTextView = new TextView(this);
        gradeTextView.setText("Grade: " + String.format("%.2f", grade) + "%");
        gradeTextView.setTextSize(18);
        ((LinearLayout) findViewById(R.id.rootLayout)).addView(gradeTextView);

        // Display solutions
        for (int i = 0; i < form1Questions.size(); i++) {
            Form1QuestionEnglish question = form1Questions.get(i);

            TextView questionTextView = new TextView(this);
            questionTextView.setText("Question " + (i + 1) + ": " + question.getQuestionText());
            questionTextView.setTextSize(16);
            ((LinearLayout) findViewById(R.id.rootLayout)).addView(questionTextView);

            String[] options = {question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD()};
            for (String option : options) {
                TextView optionTextView = new TextView(this);
                optionTextView.setText(option);
                optionTextView.setPadding(20, 0, 0, 0);

                if (option.equals(question.getCorrectAnswer())) {
                    optionTextView.setTextColor(Color.GREEN);
                } else if (option.equals(question.getUserAnswer())) {
                    optionTextView.setTextColor(Color.RED);
                }

                ((LinearLayout) findViewById(R.id.rootLayout)).addView(optionTextView);
            }

            ((LinearLayout) findViewById(R.id.rootLayout)).addView(new TextView(this));
        }
    }
}

