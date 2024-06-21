

package com.example.teachandlearn.Student.Form3.Documents.Chemistry;

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

public class Form3QuizChemistry extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form3QuestionChemistry> questions;
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

    private List<Form3QuestionChemistry> loadQuestions() {


        List<Form3QuestionChemistry> questions = new ArrayList<>();
        questions.add(new Form3QuestionChemistry("What is the difference between an element and a compound?",
                "An element is a substance made up of atoms with the same number of protons. A compound is a substance made up of atoms of two or more different elements chemically bonded together.",
                "An element is a substance that cannot be broken down into simpler substances. A compound is a substance with a fixed composition.",
                "An element is a substance with a fixed composition. A compound is a substance that cannot be broken down into simpler substances.",
                "An element is a substance made up of atoms with different numbers of protons. A compound is a substance made up of atoms of the same element.",
                "An element is a substance made up of atoms with the same number of protons. A compound is a substance made up of atoms of two or more different elements chemically bonded together."));

        questions.add(new Form3QuestionChemistry("Explain the concept of a chemical reaction.",
                "A chemical reaction is a process in which one or more substances (reactants) are converted into different substances (products) with new chemical properties.",
                "A chemical reaction is a process in which physical changes occur in substances.",
                "A chemical reaction is a process in which substances are mixed together without any change occurring.",
                "A chemical reaction is a process in which substances are converted into energy.",
                "A chemical reaction is a process in which one or more substances (reactants) are converted into different substances (products) with new chemical properties."));

        questions.add(new Form3QuestionChemistry("What is the law of conservation of mass?",
                "The law of conservation of mass states that mass is neither created nor destroyed in a chemical reaction. The total mass of reactants equals the total mass of products.",
                "The law of conservation of mass states that mass is created in a chemical reaction. The total mass of reactants is greater than the total mass of products.",
                "The law of conservation of mass states that mass is destroyed in a chemical reaction. The total mass of reactants is less than the total mass of products.",
                "The law of conservation of mass states that mass changes form in a chemical reaction. The total mass of reactants is different from the total mass of products.",
                "The law of conservation of mass states that mass is neither created nor destroyed in a chemical reaction. The total mass of reactants equals the total mass of products."));

        questions.add(new Form3QuestionChemistry("What are isotopes?",
                "Isotopes are atoms of the same element with the same number of protons but different numbers of neutrons.",
                "Isotopes are atoms of different elements with the same number of protons and neutrons.",
                "Isotopes are atoms of different elements with different numbers of protons and neutrons.",
                "Isotopes are atoms of the same element with different numbers of protons but the same number of neutrons.",
                "Isotopes are atoms of the same element with the same number of protons but different numbers of neutrons."));

        questions.add(new Form3QuestionChemistry("Describe the properties of acids.",
                "Acids are substances that ionize in water to produce hydrogen ions (H⁺). They taste sour, turn blue litmus paper red, and have a pH less than 7.",
                "Acids are substances that ionize in water to produce hydroxide ions (OH⁻). They taste bitter, turn red litmus paper blue, and have a pH greater than 7.",
                "Acids are substances that do not ionize in water. They have a neutral pH of 7.",
                "Acids are substances that do not ionize in water. They taste sweet and are not reactive with metals.",
                "Acids are substances that ionize in water to produce hydrogen ions (H⁺). They taste sour, turn blue litmus paper red, and have a pH less than 7."));

        questions.add(new Form3QuestionChemistry("Explain the difference between exothermic and endothermic reactions.",
                "Exothermic reactions release energy to the surroundings in the form of heat. Endothermic reactions absorb energy from the surroundings.",
                "Exothermic reactions absorb energy from the surroundings. Endothermic reactions release energy to the surroundings.",
                "Exothermic reactions involve the melting of substances. Endothermic reactions involve the freezing of substances.",
                "Exothermic reactions involve the vaporization of substances. Endothermic reactions involve the condensation of substances.",
                "Exothermic reactions release energy to the surroundings in the form of heat. Endothermic reactions absorb energy from the surroundings."));

        questions.add(new Form3QuestionChemistry("What is electrolysis?",
                "Electrolysis is the process of using electricity to decompose a compound into its elements or simpler compounds.",
                "Electrolysis is the process of using heat to decompose a compound into its elements or simpler compounds.",
                "Electrolysis is the process of using chemical reactions to decompose a compound into its elements or simpler compounds.",
                "Electrolysis is the process of using light to decompose a compound into its elements or simpler compounds.",
                "Electrolysis is the process of using electricity to decompose a compound into its elements or simpler compounds."));

        questions.add(new Form3QuestionChemistry("Discuss the role of catalysts in chemical reactions.",
                "Catalysts are substances that speed up chemical reactions by lowering the activation energy required for the reaction to occur. They remain unchanged at the end of the reaction.",
                "Catalysts are substances that slow down chemical reactions by increasing the activation energy required for the reaction to occur. They are consumed during the reaction.",
                "Catalysts are substances that do not affect the rate of chemical reactions. They remain unchanged at the end of the reaction.",
                "Catalysts are substances that inhibit chemical reactions by preventing the formation of products. They are consumed during the reaction.",
                "Catalysts are substances that speed up chemical reactions by lowering the activation energy required for the reaction to occur. They remain unchanged at the end of the reaction."));

        questions.add(new Form3QuestionChemistry("Explain the concept of a chemical bond.",
                "A chemical bond is a force of attraction between two atoms that holds them together in a compound. Types of chemical bonds include ionic bonds, covalent bonds, and metallic bonds.",
                "A chemical bond is a physical barrier between two atoms that prevents them from reacting. Types of chemical bonds include solid bonds, liquid bonds, and gas bonds.",
                "A chemical bond is a magnetic field between two atoms that repels them from each other. Types of chemical bonds include positive bonds, negative bonds, and neutral bonds.",
                "A chemical bond is a heat exchange between two atoms that absorbs energy. Types of chemical bonds include hot bonds, cold bonds, and warm bonds.",
                "A chemical bond is a force of attraction between two atoms that holds them together in a compound. Types of chemical bonds include ionic bonds, covalent bonds, and metallic bonds."));

        questions.add(new Form3QuestionChemistry("Describe the structure and properties of diamond and graphite.",
                "Diamond and graphite are both forms of carbon. Diamond has a tetrahedral crystal structure with strong covalent bonds, making it hard and transparent. Graphite has a layered structure with weak intermolecular forces between layers, making it soft and slippery.",
                "Diamond and graphite are both forms of oxygen. Diamond has a layered crystal structure with weak intermolecular forces between layers, making it soft and slippery. Graphite has a tetrahedral structure with strong covalent bonds, making it hard and transparent.",
                "Diamond and graphite are both forms of nitrogen. Diamond has a cubic crystal structure with weak intermolecular forces between layers, making it soft and slippery. Graphite has a layered structure with strong covalent bonds, making it hard and transparent.",
                "Diamond and graphite are both forms of hydrogen. Diamond has a cubic crystal structure with strong covalent bonds, making it hard and transparent. Graphite has a layered structure with weak intermolecular forces between layers, making it soft and slippery.",
                "Diamond and graphite are both forms of carbon. Diamond has a tetrahedral crystal structure with strong covalent bonds, making it hard and transparent. Graphite has a layered structure with weak intermolecular forces between layers, making it soft and slippery."));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form3QuestionChemistry currentQuestion = questions.get(currentQuestionIndex);
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
