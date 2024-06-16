

package com.example.teachandlearn.Student.Form4.Documents.Biology;

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

public class Form4QuizBiology extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form4QuestionBiology> questions;
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

    private List<Form4QuestionBiology> loadQuestions() {


        List<Form4QuestionBiology> questions = new ArrayList<>();
        questions.add(new Form4QuestionBiology("What is the role of ribosomes in a cell?",
                "Synthesize proteins from amino acids.",
                "Store genetic information.",
                "Produce energy in the form of ATP.",
                "Control the cell's activities.",
                "Synthesize proteins from amino acids."));

        questions.add(new Form4QuestionBiology("Explain the process of photosynthesis.",
                "The conversion of light energy into chemical energy, producing glucose and oxygen from carbon dioxide and water.",
                "The breakdown of glucose to release energy in the form of ATP.",
                "The transfer of genetic information from DNA to RNA.",
                "The movement of water and nutrients from roots to leaves.",
                "The conversion of light energy into chemical energy, producing glucose and oxygen from carbon dioxide and water."));

        questions.add(new Form4QuestionBiology("What is the function of the mitochondria in a cell?",
                "Generate ATP through cellular respiration.",
                "Store and transmit genetic information.",
                "Produce ribosomes for protein synthesis.",
                "Regulate the cell's osmotic balance.",
                "Generate ATP through cellular respiration."));

        questions.add(new Form4QuestionBiology("Describe the structure and function of the human heart.",
                "A muscular organ with four chambers that pumps blood throughout the body, supplying oxygen and nutrients while removing waste products.",
                "An organ that filters blood, removing toxins and waste products.",
                "A gland that secretes hormones regulating metabolism.",
                "A vessel that carries oxygenated blood from the lungs to the heart.",
                "A muscular organ with four chambers that pumps blood throughout the body, supplying oxygen and nutrients while removing waste products."));

        questions.add(new Form4QuestionBiology("What is the role of enzymes in biological reactions?",
                "Act as catalysts, speeding up the rate of biochemical reactions without being consumed.",
                "Provide structural support to cells.",
                "Store genetic information in the form of DNA.",
                "Transport oxygen in the blood.",
                "Act as catalysts, speeding up the rate of biochemical reactions without being consumed."));

        questions.add(new Form4QuestionBiology("Explain the process of mitosis.",
                "A type of cell division resulting in two identical daughter cells with the same number of chromosomes as the parent cell.",
                "The process by which cells produce gametes with half the number of chromosomes.",
                "The division of the cytoplasm in a cell.",
                "The replication of DNA in preparation for cell division.",
                "A type of cell division resulting in two identical daughter cells with the same number of chromosomes as the parent cell."));

        questions.add(new Form4QuestionBiology("What are the main stages of the cell cycle?",
                "Interphase, mitosis, and cytokinesis.",
                "Glycolysis, Krebs cycle, and electron transport chain.",
                "Prophase, metaphase, anaphase, and telophase.",
                "Transcription, translation, and replication.",
                "Interphase, mitosis, and cytokinesis."));

        questions.add(new Form4QuestionBiology("Describe the structure and function of DNA.",
                "A double-helix molecule composed of nucleotides that carries genetic information necessary for the growth, development, and reproduction of organisms.",
                "A single-stranded molecule that carries the instructions for protein synthesis.",
                "A protein structure that forms the framework of the cell.",
                "A lipid molecule that stores energy in the form of fat.",
                "A double-helix molecule composed of nucleotides that carries genetic information necessary for the growth, development, and reproduction of organisms."));

        questions.add(new Form4QuestionBiology("What is the role of the circulatory system?",
                "Transport blood, nutrients, gases, and waste products throughout the body.",
                "Provide structural support to the body.",
                "Regulate the body's temperature and hormone levels.",
                "Facilitate the exchange of gases in the lungs.",
                "Transport blood, nutrients, gases, and waste products throughout the body."));

        questions.add(new Form4QuestionBiology("Explain the concept of natural selection.",
                "The process by which organisms better adapted to their environment tend to survive and produce more offspring, leading to evolution.",
                "The artificial breeding of plants and animals for desired traits.",
                "The random changes in the genetic makeup of a population over time.",
                "The movement of organisms from one environment to another.",
                "The process by which organisms better adapted to their environment tend to survive and produce more offspring, leading to evolution."));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form4QuestionBiology currentQuestion = questions.get(currentQuestionIndex);
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
