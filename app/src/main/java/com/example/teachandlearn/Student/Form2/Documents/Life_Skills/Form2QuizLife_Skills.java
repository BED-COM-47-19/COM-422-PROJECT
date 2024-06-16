

package com.example.teachandlearn.Student.Form2.Documents.Life_Skills;

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

public class Form2QuizLife_Skills extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form2QuestionLife_Skills> questions;
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

    private List<Form2QuestionLife_Skills> loadQuestions() {


        List<Form2QuestionLife_Skills> questions = new ArrayList<>();

        questions.add(new Form2QuestionLife_Skills("Why is it important to set goals in life?",
                "To keep track of achievements",
                "To make others happy",
                "To avoid challenges",
                "To procrastinate",
                "To keep track of achievements"));

        questions.add(new Form2QuestionLife_Skills("What are the benefits of teamwork?",
                "Increased competition",
                "Improved communication",
                "Decreased productivity",
                "Isolation",
                "Improved communication"));

        questions.add(new Form2QuestionLife_Skills("How can you effectively manage your time?",
                "By procrastinating tasks",
                "By prioritizing tasks",
                "By avoiding planning",
                "By multitasking",
                "By prioritizing tasks"));

        questions.add(new Form2QuestionLife_Skills("Why is it important to develop good communication skills?",
                "To isolate oneself",
                "To improve relationships",
                "To create misunderstandings",
                "To avoid sharing ideas",
                "To improve relationships"));

        questions.add(new Form2QuestionLife_Skills("What are the consequences of bullying?",
                "Improved self-esteem",
                "Decreased anxiety",
                "Increased trust",
                "Decreased self-esteem",
                "Decreased self-esteem"));

        questions.add(new Form2QuestionLife_Skills("How can you show respect to others?",
                "By gossiping about them",
                "By ignoring their feelings",
                "By listening to their opinions",
                "By criticizing their actions",
                "By listening to their opinions"));

        questions.add(new Form2QuestionLife_Skills("What are the benefits of staying physically active?",
                "Decreased energy levels",
                "Increased risk of diseases",
                "Improved physical health",
                "Decreased motivation",
                "Improved physical health"));

        questions.add(new Form2QuestionLife_Skills("Why is it important to manage money wisely?",
                "To accumulate debt",
                "To achieve financial goals",
                "To spend impulsively",
                "To avoid saving",
                "To achieve financial goals"));

        questions.add(new Form2QuestionLife_Skills("How can you resolve conflicts peacefully?",
                "By being aggressive",
                "By avoiding communication",
                "By listening actively",
                "By blaming others",
                "By listening actively"));

        questions.add(new Form2QuestionLife_Skills("What is the importance of being resilient?",
                "To give up easily",
                "To overcome challenges",
                "To avoid setbacks",
                "To ignore strengths",
                "To overcome challenges"));
        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form2QuestionLife_Skills currentQuestion = questions.get(currentQuestionIndex);
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
