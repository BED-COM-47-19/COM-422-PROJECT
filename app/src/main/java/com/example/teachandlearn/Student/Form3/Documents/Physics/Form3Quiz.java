

package com.example.teachandlearn.Student.Form3.Documents.Physics;

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

public class Form3Quiz extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form3Question> questions;
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

    private List<Form3Question> loadQuestions() {


        List<Form3Question> questions = new ArrayList<>();

        questions.add(new Form3Question("Define the term 'momentum'.",
                "The product of an object's mass and velocity.",
                "The force acting on an object.",
                "The energy stored in an object.",
                "The distance an object travels per unit time.",
                "The product of an object's mass and velocity."));

        questions.add(new Form3Question("State Newton's first law of motion.",
                "An object at rest will remain at rest, and an object in motion will remain in motion unless acted upon by an external force.",
                "The rate of change of momentum of an object is directly proportional to the force applied, and this change in momentum takes place in the direction of the applied force.",
                "The force of attraction between two objects is directly proportional to the product of their masses and inversely proportional to the square of the distance between them.",
                "For every action, there is an equal and opposite reaction.",
                "An object at rest will remain at rest, and an object in motion will remain in motion unless acted upon by an external force."));

        questions.add(new Form3Question("What is the SI unit of electric current?",
                "Ampere (A)",
                "Volt (V)",
                "Ohm (Ω)",
                "Coulomb (C)",
                "Ampere (A)"));

        questions.add(new Form3Question("State the principle of conservation of energy.",
                "Energy cannot be created or destroyed, only transformed from one form to another.",
                "The total energy of an isolated system remains constant over time.",
                "Energy is directly proportional to mass and the square of the speed of light.",
                "The energy required to accelerate an object is equal to the force applied times the distance traveled.",
                "Energy cannot be created or destroyed, only transformed from one form to another."));

        questions.add(new Form3Question("Define refraction of light.",
                "The bending of light as it passes from one medium to another.",
                "The reflection of light off a smooth surface.",
                "The spreading out of light as it passes through a narrow slit.",
                "The interference pattern formed by light waves.",
                "The bending of light as it passes from one medium to another."));

        questions.add(new Form3Question("What is the relationship between voltage, current, and resistance in an electrical circuit?",
                "V = IR",
                "I = VR",
                "R = VI",
                "P = VI",
                "V = IR"));

        questions.add(new Form3Question("State Archimedes' principle.",
                "An object immersed in a fluid experiences an upward buoyant force equal to the weight of the fluid displaced by the object.",
                "The pressure exerted by a fluid at any point is transmitted undiminished in all directions throughout the fluid.",
                "The total electric flux through a closed surface is proportional to the net electric charge enclosed by the surface.",
                "The force of attraction between two point masses is directly proportional to the product of their masses and inversely proportional to the square of the distance between them.",
                "An object immersed in a fluid experiences an upward buoyant force equal to the weight of the fluid displaced by the object."));

        questions.add(new Form3Question("Explain the term 'wave-particle duality' in quantum physics.",
                "The concept that particles exhibit both wave-like and particle-like properties.",
                "The principle that the speed of a wave is directly proportional to the frequency and wavelength.",
                "The phenomenon where two waves overlap and form a new wave pattern.",
                "The mathematical relationship between the energy of a photon and its frequency.",
                "The concept that particles exhibit both wave-like and particle-like properties."));

        questions.add(new Form3Question("State the universal law of gravitation.",
                "Every mass attracts every other mass in the universe with a force that is directly proportional to the product of their masses and inversely proportional to the square of the distance between their centers.",
                "The force applied to an object is equal to the rate of change of its momentum over time.",
                "The total electric flux through a closed surface is proportional to the net electric charge enclosed by the surface.",
                "The pressure difference between two points in a fluid is equal to the product of the fluid's density, gravity, and height difference.",
                "Every mass attracts every other mass in the universe with a force that is directly proportional to the product of their masses and inversely proportional to the square of the distance between their centers."));

        questions.add(new Form3Question("Define nuclear fission.",
                "The splitting of a heavy atomic nucleus into two lighter nuclei, accompanied by the release of a large amount of energy.",
                "The combining of two light atomic nuclei to form a heavier nucleus, releasing energy.",
                "The process by which a radioactive nucleus emits particles and energy to become stable.",
                "The transformation of a substance into one or more new substances with different properties.",
                "The splitting of a heavy atomic nucleus into two lighter nuclei, accompanied by the release of a large amount of energy."));

        questions.add(new Form3Question("State the law of reflection of light.",
                "The angle of incidence is equal to the angle of reflection.",
                "The speed of light in a medium is directly proportional to the frequency of the light.",
                "The bending of light as it passes from one medium to another.",
                "The spreading out of light as it passes through a narrow slit.",
                "The angle of incidence is equal to the angle of reflection."));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form3Question currentQuestion = questions.get(currentQuestionIndex);
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
