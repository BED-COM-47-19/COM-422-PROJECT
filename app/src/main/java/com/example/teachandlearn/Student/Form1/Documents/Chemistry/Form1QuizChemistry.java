package com.example.teachandlearn.Student.Form1.Documents.Chemistry;

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

public class Form1QuizChemistry extends AppCompatActivity {

    private TextView questionTextView, questionNumberTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form1QuestionChemistry> form1Questions;
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

    private List<Form1QuestionChemistry> loadQuestions() {
        List<Form1QuestionChemistry> form1Questions = new ArrayList<>();
        form1Questions.add(new Form1QuestionChemistry("Which gas is commonly used in balloons for buoyancy?", "Oxygen", "Carbon dioxide", "Helium", "Nitrogen", "Helium"));
        form1Questions.add(new Form1QuestionChemistry("What is the chemical formula for table salt?", "NaCl", "H2O", "CO2", "O2", "NaCl"));
        form1Questions.add(new Form1QuestionChemistry("What is the process of converting a solid directly into a gas called?", "Condensation", "Evaporation", "Sublimation", "Freezing", "Sublimation"));
        form1Questions.add(new Form1QuestionChemistry("Which metal is commonly used in electrical wires due to its high conductivity?", "Aluminum", "Copper", "Iron", "Lead", "Copper"));
        form1Questions.add(new Form1QuestionChemistry("Which substance is produced by combining hydrogen and oxygen in a chemical reaction?", "Carbon dioxide", "Water", "Nitrogen", "Hydrogen peroxide", "Water"));
        form1Questions.add(new Form1QuestionChemistry("What is the chemical symbol for silver?", "Si", "Ag", "Au", "Fe", "Ag"));
        form1Questions.add(new Form1QuestionChemistry("Which type of chemical bond involves the sharing of electrons between atoms?", "Ionic bond", "Covalent bond", "Metallic bond", "Hydrogen bond", "Covalent bond"));
        form1Questions.add(new Form1QuestionChemistry("What is the state of matter of water at room temperature?", "Solid", "Liquid", "Gas", "Plasma", "Liquid"));
        form1Questions.add(new Form1QuestionChemistry("Which gas is essential for respiration in living organisms?", "Carbon dioxide", "Oxygen", "Nitrogen", "Helium", "Oxygen"));
        form1Questions.add(new Form1QuestionChemistry("What is the pH value of a neutral substance?", "7", "0", "14", "1", "7"));
        return form1Questions;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < form1Questions.size()) {
            Form1QuestionChemistry currentForm1Question = form1Questions.get(currentQuestionIndex);
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
        Form1QuestionChemistry currentForm1Question = form1Questions.get(currentQuestionIndex);
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
            Form1QuestionChemistry question = form1Questions.get(i);

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
