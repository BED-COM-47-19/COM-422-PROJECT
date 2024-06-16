

package com.example.teachandlearn.Student.Form4.Documents.Physics;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachandlearn.R;

import java.util.ArrayList;
import java.util.List;

public class Form4QuizPhysics extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;

    private List<Form4QuestionPhysics> questions;
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

    private List<Form4QuestionPhysics> loadQuestions() {


        List<Form4QuestionPhysics> questions = new ArrayList<>();
        questions.add(new Form4QuestionPhysics("Explain the concept of electric field intensity and its unit of measurement.",
                "Electric field intensity refers to the force exerted per unit positive charge in an electric field. It is measured in newtons per coulomb (N/C).",
                "Electric field is the energy per unit charge at a point in an electric field.",
                "Electric field is the number of electric lines of force per unit area.",
                "Electric field is the region around a charged object where it exerts a force on another charged object.",
                "Electric field intensity refers to the force exerted per unit positive charge in an electric field. It is measured in newtons per coulomb (N/C)."));

        questions.add(new Form4QuestionPhysics("Describe the process of electromagnetic induction and its applications.",
                "Electromagnetic induction is the process by which a changing magnetic field induces an electromotive force (emf) or voltage in a conductor.",
                "Electromagnetic induction is the process of creating a magnetic field by passing an electric current through a conductor.",
                "Electromagnetic induction is the process of creating an electric field by moving a conductor in a magnetic field.",
                "Electromagnetic induction is the process of generating heat in a conductor due to the passage of an electric current.",
                "Electromagnetic induction is the process by which a changing magnetic field induces an electromotive force (emf) or voltage in a conductor."));

        questions.add(new Form4QuestionPhysics("Explain the principles of reflection and refraction of light.",
                "Reflection of light occurs when light rays bounce off a surface, obeying the law of reflection which states that the angle of incidence equals the angle of reflection. Refraction of light occurs when light travels from one medium to another, causing a change in direction due to a change in speed.",
                "Reflection of light occurs when light passes through a transparent medium.",
                "Reflection of light occurs when light changes direction after passing through a medium.",
                "Reflection of light occurs when light passes through a translucent medium.",
                "Reflection of light occurs when light rays bounce off a surface, obeying the law of reflection which states that the angle of incidence equals the angle of reflection."));

        questions.add(new Form4QuestionPhysics("Discuss the principles behind the functioning of a cathode-ray oscilloscope (CRO).",
                "A cathode-ray oscilloscope (CRO) is a device used to display and analyze the waveform of electrical signals. It operates by generating an electron beam (cathode rays) which is deflected by electric fields to trace the waveform on a fluorescent screen.",
                "A cathode-ray oscilloscope (CRO) is a device used to generate electrical signals.",
                "A cathode-ray oscilloscope (CRO) is a device used to measure temperature.",
                "A cathode-ray oscilloscope (CRO) is a device used to measure light intensity.",
                "A cathode-ray oscilloscope (CRO) is a device used to display and analyze the waveform of electrical signals."));

        questions.add(new Form4QuestionPhysics("Explain the concept of nuclear fission and its role in nuclear power generation.",
                "Nuclear fission is a nuclear reaction in which the nucleus of an atom splits into two or more smaller nuclei, releasing a large amount of energy. This energy is used to generate heat in nuclear reactors, which is then converted into electricity.",
                "Nuclear fission is a nuclear reaction in which two atomic nuclei combine to form a larger nucleus.",
                "Nuclear fission is a nuclear reaction in which energy is released by the combination of atomic nuclei.",
                "Nuclear fission is a nuclear reaction in which a nucleus captures a neutron.",
                "Nuclear fission is a nuclear reaction in which the nucleus of an atom splits into two or more smaller nuclei, releasing a large amount of energy."));

        questions.add(new Form4QuestionPhysics("Discuss the concept of interference in the context of wave phenomena.",
                "Interference is the phenomenon in which two or more waves superpose to form a resultant wave of greater, lower, or the same amplitude. It occurs when waves from different sources meet at the same point in space and either reinforce (constructive interference) or cancel out (destructive interference) each other.",
                "Interference is the phenomenon in which a wave passes through a medium.",
                "Interference is the phenomenon in which a wave reflects off a surface.",
                "Interference is the phenomenon in which a wave refracts through a medium.",
                "Interference is the phenomenon in which two or more waves superpose to form a resultant wave of greater, lower, or the same amplitude."));

        questions.add(new Form4QuestionPhysics("Explain the concept of electromagnetic waves and their properties.",
                "Electromagnetic waves are transverse waves consisting of oscillating electric and magnetic fields that propagate through space at the speed of light.",
                "Electromagnetic waves are longitudinal waves that require a medium for their propagation.",
                "Electromagnetic waves are mechanical waves that propagate at the speed of sound.",
                "Electromagnetic waves are transverse waves that propagate in a vacuum.",
                "Electromagnetic waves are transverse waves consisting of oscillating electric and magnetic fields that propagate through space at the speed of light."));

        questions.add(new Form4QuestionPhysics("Discuss the concept of thermal equilibrium and its importance in thermodynamics.",
                "Thermal equilibrium occurs when two objects or systems reach the same temperature and there is no net flow of heat between them. It is a fundamental principle in thermodynamics, governing heat transfer and the behavior of systems in thermal contact.",
                "Thermal equilibrium occurs when two objects have different temperatures and heat flows between them.",
                "Thermal equilibrium occurs when two objects or systems reach different temperatures and there is no heat transfer.",
                "Thermal equilibrium occurs when two objects or systems reach the same temperature and heat transfer is maximized.",
                "Thermal equilibrium occurs when two objects or systems reach the same temperature and there is no net flow of heat between them."));

        questions.add(new Form4QuestionPhysics("Explain the operation of a transformer and its significance in electrical power transmission.",
                "A transformer consists of two coils (primary and secondary) wound on a soft iron core. When an alternating current (AC) flows through the primary coil, it generates a varying magnetic field in the core, inducing an alternating emf in the secondary coil through mutual induction. Transformers are used in power transmission to step up voltage for efficient long-distance transmission and step down voltage for safe distribution to consumers.",
                "A transformer consists of two capacitors wound on a soft iron core.",
                "A transformer consists of two resistors wound on a soft iron core.",
                "A transformer consists of two coils wound on a steel core.",
                "A transformer consists of two coils (primary and secondary) wound on a soft iron core. When an alternating current (AC) flows through the primary coil, it generates a varying magnetic field in the core, inducing an alternating emf in the secondary coil through mutual induction."));

        questions.add(new Form4QuestionPhysics("Explain the concept of mechanical waves and give examples of longitudinal and transverse waves.",
                "Mechanical waves are waves that require a medium (solid, liquid, or gas) for their propagation. Longitudinal waves are waves in which the particles of the medium vibrate parallel to the direction of wave propagation, e.g., sound waves in air. Transverse waves are waves in which the particles of the medium vibrate perpendicular to the direction of wave propagation, e.g., light waves.",
                "Mechanical waves are waves that do not require a medium for their propagation.",
                "Mechanical waves are waves that propagate at the speed of light.",
                "Mechanical waves are waves that propagate in a vacuum.",
                "Mechanical waves are waves that require a medium (solid, liquid, or gas) for their propagation."));

        questions.add(new Form4QuestionPhysics("Explain the concept of electric current and its unit of measurement.",
                "Electric current is the rate of flow of electric charge through a conductor or a circuit. It is measured in amperes (A), where 1 ampere is equivalent to 1 coulomb of charge passing through a conductor per second.",
                "Electric current is the potential difference between two points in a conductor.",
                "Electric current is the work done per unit charge in a circuit.",
                "Electric current is the opposition of a material to the flow of electric current.",
                "Electric current is the rate of flow of electric charge through a conductor or a circuit. It is measured in amperes (A), where 1 ampere is equivalent to 1 coulomb of charge passing through a conductor per second."));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form4QuestionPhysics currentQuestion = questions.get(currentQuestionIndex);
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
