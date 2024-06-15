

package com.example.teachandlearn.Student.Form3.Documents.Biology;

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

public class Form3QuizCHE extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form3QuestionCHE> questions;
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

    private List<Form3QuestionCHE> loadQuestions() {


        List<Form3QuestionCHE> questions = new ArrayList<>();
        questions.add(new Form3QuestionCHE("What is the function of the mitochondria in a cell?",
                "To generate energy (ATP) through cellular respiration",
                "To store genetic information",
                "To synthesize proteins",
                "To package and transport molecules",
                "To generate energy (ATP) through cellular respiration"));

        questions.add(new Form3QuestionCHE("Explain the process of photosynthesis.",
                "Photosynthesis is the process by which plants, algae, and some bacteria convert light energy, carbon dioxide, and water into glucose and oxygen.",
                "Photosynthesis is the process by which animals break down glucose to release energy.",
                "Photosynthesis is the process by which plants absorb oxygen from the atmosphere.",
                "Photosynthesis is the process by which plants convert glucose into starch.",
                "Photosynthesis is the process by which plants, algae, and some bacteria convert light energy, carbon dioxide, and water into glucose and oxygen."));

        questions.add(new Form3QuestionCHE("What is homeostasis and why is it important in living organisms?",
                "Homeostasis is the ability of an organism to maintain internal stability and regulate its internal environment despite external changes. It is important for maintaining optimal conditions for biochemical reactions.",
                "Homeostasis is the breakdown of cellular components in aging organisms.",
                "Homeostasis is the process by which organisms reproduce and pass on their genetic information.",
                "Homeostasis is the synthesis of proteins in cells.",
                "Homeostasis is the ability of an organism to maintain internal stability and regulate its internal environment despite external changes. It is important for maintaining optimal conditions for biochemical reactions."));

        questions.add(new Form3QuestionCHE("Describe the structure and function of DNA.",
                "DNA (deoxyribonucleic acid) is a double-stranded molecule that carries genetic information. It encodes the instructions for the development, growth, functioning, and reproduction of all known organisms.",
                "DNA is a single-stranded molecule found only in animal cells.",
                "DNA is a type of protein found in plant cells.",
                "DNA is a lipid molecule found in the cell membrane.",
                "DNA (deoxyribonucleic acid) is a double-stranded molecule that carries genetic information. It encodes the instructions for the development, growth, functioning, and reproduction of all known organisms."));

        questions.add(new Form3QuestionCHE("What is natural selection and how does it drive evolution?",
                "Natural selection is the process by which organisms with favorable traits are more likely to survive and reproduce, passing on their genes to the next generation. Over time, this process leads to changes in species, known as evolution.",
                "Natural selection is the process by which organisms compete for resources.",
                "Natural selection is the process by which organisms exchange genetic material.",
                "Natural selection is the process by which organisms produce offspring with identical traits.",
                "Natural selection is the process by which organisms with favorable traits are more likely to survive and reproduce, passing on their genes to the next generation. Over time, this process leads to changes in species, known as evolution."));

        questions.add(new Form3QuestionCHE("What are enzymes and what role do they play in biochemical reactions?",
                "Enzymes are biological catalysts that speed up biochemical reactions by lowering the activation energy required for reactions to occur. They are essential for metabolism and other cellular processes.",
                "Enzymes are structural proteins that provide support to cells.",
                "Enzymes are genetic materials that encode hereditary information.",
                "Enzymes are energy molecules that store ATP in cells.",
                "Enzymes are biological catalysts that speed up biochemical reactions by lowering the activation energy required for reactions to occur. They are essential for metabolism and other cellular processes."));

        questions.add(new Form3QuestionCHE("Explain the process of cellular respiration and its importance in living organisms.",
                "Cellular respiration is the process by which cells break down glucose and other organic molecules to produce ATP (energy) through a series of biochemical reactions. It is important for providing energy that cells need to function.",
                "Cellular respiration is the process by which cells divide and multiply.",
                "Cellular respiration is the process by which cells absorb oxygen from the environment.",
                "Cellular respiration is the process by which cells convert sunlight into energy.",
                "Cellular respiration is the process by which cells break down glucose and other organic molecules to produce ATP (energy) through a series of biochemical reactions. It is important for providing energy that cells need to function."));

        questions.add(new Form3QuestionCHE("Describe the difference between mitosis and meiosis.",
                "Mitosis is a type of cell division that produces two identical daughter cells with the same number of chromosomes as the parent cell. Meiosis is a type of cell division that produces four genetically diverse daughter cells with half the number of chromosomes as the parent cell, important for sexual reproduction.",
                "Mitosis and meiosis are the same processes occurring in different types of cells.",
                "Mitosis is a type of cell division that occurs only in animal cells, while meiosis occurs only in plant cells.",
                "Mitosis is a type of cell division that produces four genetically identical daughter cells.",
                "Mitosis is a type of cell division that produces two identical daughter cells with the same number of chromosomes as the parent cell. Meiosis is a type of cell division that produces four genetically diverse daughter cells with half the number of chromosomes as the parent cell, important for sexual reproduction."));

        questions.add(new Form3QuestionCHE("What is the function of the circulatory system in the human body?",
                "The circulatory system transports oxygen, nutrients, hormones, and waste products throughout the body. It includes the heart, blood vessels, and blood.",
                "The circulatory system produces energy for the body.",
                "The circulatory system regulates body temperature.",
                "The circulatory system synthesizes proteins in cells.",
                "The circulatory system transports oxygen, nutrients, hormones, and waste products throughout the body. It includes the heart, blood vessels, and blood."));

        questions.add(new Form3QuestionCHE("Discuss the role of hormones in the human body.",
                "Hormones are chemical messengers produced by endocrine glands that regulate various physiological processes such as growth, metabolism, and reproduction.",
                "Hormones are structural proteins that provide support to cells.",
                "Hormones are genetic materials that encode hereditary information.",
                "Hormones are energy molecules that store ATP in cells.",
                "Hormones are chemical messengers produced by endocrine glands that regulate various physiological processes such as growth, metabolism, and reproduction."));

        questions.add(new Form3QuestionCHE("Explain the process of evolution by natural selection.",
                "Evolution by natural selection is the process by which organisms with advantageous traits for survival and reproduction in a specific environment tend to leave more offspring than less advantageous individuals, leading to changes in populations over time.",
                "Evolution by natural selection is the process by which organisms compete for resources.",
                "Evolution by natural selection is the process by which organisms produce offspring with identical traits.",
                "Evolution by natural selection is the process by which organisms adapt to their environment through learned behaviors.",
                "Evolution by natural selection is the process by which organisms with advantageous traits for survival and reproduction in a specific environment tend to leave more offspring than less advantageous individuals, leading to changes in populations over time."));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form3QuestionCHE currentQuestion = questions.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestionText());
            optionARadioButton.setText(currentQuestion.getOptionA());
            optionBRadioButton.setText(currentQuestion.getOptionB());
            optionCRadioButton.setText(currentQuestion.getOptionC());
            optionDRadioButton.setText(currentQuestion.getOptionD());
            questionProgressBar.setProgress((int) (((float) currentQuestionIndex / questions.size()) * 100));
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
