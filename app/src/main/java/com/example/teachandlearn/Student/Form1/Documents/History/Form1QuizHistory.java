package com.example.teachandlearn.Student.Form1.Documents.History;

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

public class Form1QuizHistory extends AppCompatActivity {

    private TextView questionTextView, questionNumberTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form1QuestionHistory> questions;
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

    private List<Form1QuestionHistory> loadQuestions() {
        List<Form1QuestionHistory> questions = new ArrayList<>();
        questions.add(new Form1QuestionHistory("Who was the first President of Malawi?", "Hastings Banda", "Kamuzu Chibambo", "Joyce Banda", "Bakili Muluzi", "Hastings Banda"));
        questions.add(new Form1QuestionHistory("When did Malawi gain independence from British rule?", "1960", "1961", "1962", "1963", "1964"));
        questions.add(new Form1QuestionHistory("Which explorer reached Lake Malawi in the 19th century?", "David Livingstone", "Vasco da Gama", "Marco Polo", "Christopher Columbus", "David Livingstone"));
        questions.add(new Form1QuestionHistory("What was the name of the kingdom that existed in Malawi before British colonization?", "Chewa Kingdom", "Yao Kingdom", "Ngoni Kingdom", "Tumbuka Kingdom", "Chewa Kingdom"));
        questions.add(new Form1QuestionHistory("Who led the Ngoni people into Malawi during the 19th century?", "Zwangendaba", "Mzilikazi", "Shaka", "Dingane", "Zwangendaba"));
        questions.add(new Form1QuestionHistory("What was the capital city of Malawi before Lilongwe?", "Zomba", "Blantyre", "Mzuzu", "Mangochi", "Zomba"));
        questions.add(new Form1QuestionHistory("Which missionary played a significant role in the spread of Christianity in Malawi?", "Robert Laws", "Mary Slessor", "David Livingstone", "Albert Schweitzer", "Robert Laws"));
        questions.add(new Form1QuestionHistory("What was the primary economic activity in Malawi during the colonial period?", "Tobacco farming", "Cotton weaving", "Gold mining", "Ivory trading", "Tobacco farming"));
        questions.add(new Form1QuestionHistory("Which political party led Malawi to independence?", "Malawi Congress Party (MCP)", "United Democratic Front (UDF)", "Alliance for Democracy (AFORD)", "People's Party (PP)", "Malawi Congress Party (MCP)"));
        questions.add(new Form1QuestionHistory("What is the significance of July 6th in Malawi's history?", "Independence Day", "Martyrs' Day", "Republic Day", "Heroes' Day", "Independence Day"));

        return questions;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Form1QuestionHistory currentQuestion = questions.get(currentQuestionIndex);
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
        Form1QuestionHistory currentQuestion = questions.get(currentQuestionIndex);
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
        gradeTextView.setText("Grade: " + String.format("%.2f", grade) + "%");
        gradeTextView.setTextSize(18);
        ((LinearLayout) findViewById(R.id.rootLayout)).addView(gradeTextView);

        // Display solutions
        for (int i = 0; i < questions.size(); i++) {
            Form1QuestionHistory question = questions.get(i);

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

