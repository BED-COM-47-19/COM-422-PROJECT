

package com.example.teachandlearn.Student.Form3.Documents.History;

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

public class Form3QuizHistory extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form3QuestionHistory> questions;
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

    private List<Form3QuestionHistory> loadQuestions() {


        List<Form3QuestionHistory> questions = new ArrayList<>();
        questions.add(new Form3QuestionHistory("Who was the leader of the Angolan independence movement against Portuguese colonial rule?",
                "Agostinho Neto",
                "Samora Machel",
                "Patrice Lumumba",
                "Jonas Savimbi",
                "Agostinho Neto"));

        questions.add(new Form3QuestionHistory("What was the name of the liberation movement in Mozambique that fought against Portuguese rule?",
                "FRELIMO (Mozambique Liberation Front)",
                "ANC (African National Congress)",
                "SWAPO (South West Africa People's Organization)",
                "ZANU (Zimbabwe African National Union)",
                "FRELIMO (Mozambique Liberation Front)"));

        questions.add(new Form3QuestionHistory("Which country did Zimbabwe gain independence from in 1980?",
                "United Kingdom",
                "Portugal",
                "Belgium",
                "South Africa",
                "United Kingdom"));

        questions.add(new Form3QuestionHistory("Who was the first President of Zimbabwe after independence?",
                "Robert Mugabe",
                "Nelson Mandela",
                "Ian Smith",
                "Joshua Nkomo",
                "Robert Mugabe"));

        questions.add(new Form3QuestionHistory("What was the name of the pro-independence movement in Namibia?",
                "SWAPO (South West Africa People's Organization)",
                "ANC (African National Congress)",
                "ZANU (Zimbabwe African National Union)",
                "FRELIMO (Mozambique Liberation Front)",
                "SWAPO (South West Africa People's Organization)"));

        questions.add(new Form3QuestionHistory("Which country did Namibia gain independence from in 1990?",
                "South Africa",
                "Portugal",
                "Belgium",
                "United Kingdom",
                "South Africa"));

        questions.add(new Form3QuestionHistory("Who was the first President of Namibia after independence?",
                "Sam Nujoma",
                "Hifikepunye Pohamba",
                "Andimba Toivo ya Toivo",
                "Hage Geingob",
                "Sam Nujoma"));

        questions.add(new Form3QuestionHistory("Who was the first President of Zambia after independence?",
                "Kenneth Kaunda",
                "Julius Nyerere",
                "Jomo Kenyatta",
                "Hastings Banda",
                "Kenneth Kaunda"));

        questions.add(new Form3QuestionHistory("Which country did Malawi gain independence from in 1964?",
                "United Kingdom",
                "Portugal",
                "Belgium",
                "South Africa",
                "United Kingdom"));

        questions.add(new Form3QuestionHistory("Who was the first President of Malawi after independence?",
                "Hastings Banda",
                "Kamuzu Chibambo",
                "Joyce Banda",
                "Bakili Muluzi",
                "Hastings Banda"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form3QuestionHistory currentQuestion = questions.get(currentQuestionIndex);
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
