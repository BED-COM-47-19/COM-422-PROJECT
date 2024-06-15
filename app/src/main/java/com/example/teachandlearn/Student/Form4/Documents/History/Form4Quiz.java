

package com.example.teachandlearn.Student.Form4.Documents.History;

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

public class Form4Quiz extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form4Question> questions;
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

    private List<Form4Question> loadQuestions() {


        List<Form4Question> questions = new ArrayList<>();

        questions.add(new Form4Question("Who was the Malawian nationalist leader who led the country to independence?",
                "Hastings Banda",
                "John Chilembwe",
                "Orton Chirwa",
                "Kamuzu Chibambo",
                "Hastings Banda"));

        questions.add(new Form4Question("What event triggered the beginning of World War I in 1914?",
                "Assassination of Archduke Franz Ferdinand",
                "Battle of the Somme",
                "Treaty of Versailles",
                "Russian Revolution",
                "Assassination of Archduke Franz Ferdinand"));

        questions.add(new Form4Question("Which treaty formally ended World War I in 1919?",
                "Treaty of Versailles",
                "Treaty of Trianon",
                "Treaty of Saint-Germain",
                "Treaty of Neuilly",
                "Treaty of Versailles"));

        questions.add(new Form4Question("Who was the leader of Nazi Germany during World War II?",
                "Adolf Hitler",
                "Benito Mussolini",
                "Joseph Stalin",
                "Winston Churchill",
                "Adolf Hitler"));

        questions.add(new Form4Question("Which battle is often considered the turning point for the Allies in World War II?",
                "Battle of Stalingrad",
                "Battle of Midway",
                "D-Day Invasion",
                "Battle of El Alamein",
                "Battle of Stalingrad"));

        questions.add(new Form4Question("What was the name of the ship that carried the pilgrims to America in 1620?",
                "Mayflower",
                "Santa Maria",
                "Nina",
                "Endeavour",
                "Mayflower"));

        questions.add(new Form4Question("Who wrote the Communist Manifesto in 1848?",
                "Karl Marx and Friedrich Engels",
                "Vladimir Lenin",
                "Joseph Stalin",
                "Mao Zedong",
                "Karl Marx and Friedrich Engels"));

        questions.add(new Form4Question("What was the name of the last Russian Tsar who abdicated in 1917?",
                "Nicholas II",
                "Alexander III",
                "Peter the Great",
                "Ivan the Terrible",
                "Nicholas II"));

        questions.add(new Form4Question("Which country was ruled by the dictator Francisco Franco from 1939 to 1975?",
                "Spain",
                "Italy",
                "Germany",
                "Portugal",
                "Spain"));

        questions.add(new Form4Question("What was the name of the peace treaty that ended the Korean War in 1953?",
                "Armistice Agreement",
                "Geneva Accords",
                "Potsdam Agreement",
                "Yalta Conference",
                "Armistice Agreement"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form4Question currentQuestion = questions.get(currentQuestionIndex);
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
