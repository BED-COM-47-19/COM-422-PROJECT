

package com.example.teachandlearn.Student.Form2.Documents.Bible_Knowledge;

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

public class Form2QuizBible_Knowledge extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form2QuestionBible_Knowledge> questions;
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

    private List<Form2QuestionBible_Knowledge> loadQuestions() {


        List<Form2QuestionBible_Knowledge> questions = new ArrayList<>();


        questions.add(new Form2QuestionBible_Knowledge("Who was the brother of Moses?",
                "Aaron",
                "Elijah",
                "Joshua",
                "David",
                "Aaron"));

        questions.add(new Form2QuestionBible_Knowledge("Which prophet was taken up to heaven in a whirlwind?",
                "Isaiah",
                "Elijah",
                "Jeremiah",
                "Daniel",
                "Elijah"));

        questions.add(new Form2QuestionBible_Knowledge("What is the first book of the New Testament?",
                "Genesis",
                "Exodus",
                "Matthew",
                "Psalms",
                "Matthew"));

        questions.add(new Form2QuestionBible_Knowledge("Who betrayed Jesus to the religious authorities?",
                "Peter",
                "John",
                "Judas Iscariot",
                "Thomas",
                "Judas Iscariot"));

        questions.add(new Form2QuestionBible_Knowledge("Who was known for his wisdom and wrote many proverbs in the Bible?",
                "David",
                "Solomon",
                "Samuel",
                "Ezekiel",
                "Solomon"));

        questions.add(new Form2QuestionBible_Knowledge("Which of these is not one of the Ten Commandments?",
                "You shall not bear false witness",
                "You shall not covet your neighbor's wife",
                "You shall not make for yourself an idol",
                "You shall not eat pork",
                "You shall not eat pork"));

        questions.add(new Form2QuestionBible_Knowledge("Who was the first king of Israel?",
                "David",
                "Saul",
                "Solomon",
                "Samuel",
                "Saul"));

        questions.add(new Form2QuestionBible_Knowledge("Which disciple of Jesus doubted His resurrection until he saw Jesus for himself?",
                "Peter",
                "John",
                "Thomas",
                "James",
                "Thomas"));

        questions.add(new Form2QuestionBible_Knowledge("Who was thrown into a den of lions but saved by God?",
                "Daniel",
                "Joseph",
                "Moses",
                "Samson",
                "Daniel"));

        questions.add(new Form2QuestionBible_Knowledge("Who was sold into slavery by his brothers and later became a ruler in Egypt?",
                "Isaac",
                "Jacob",
                "Joseph",
                "Abraham",
                "Joseph"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form2QuestionBible_Knowledge currentQuestion = questions.get(currentQuestionIndex);
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
