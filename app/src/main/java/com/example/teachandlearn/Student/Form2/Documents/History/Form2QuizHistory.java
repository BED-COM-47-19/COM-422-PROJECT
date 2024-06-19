

package com.example.teachandlearn.Student.Form2.Documents.History;

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

public class Form2QuizHistory extends AppCompatActivity {

    private TextView questionTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private ProgressBar questionProgressBar;
    private List<Form2QuestionHistory> questions;
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

    private List<Form2QuestionHistory> loadQuestions() {


        List<Form2QuestionHistory> questions = new ArrayList<>();

        questions.add(new Form2QuestionHistory("Which European countries were heavily involved in the transatlantic slave trade in Central Africa?",
                "Portugal and Spain",
                "England and France",
                "Netherlands and Denmark",
                "Germany and Italy",
                "Portugal and Spain"));

        questions.add(new Form2QuestionHistory("Who were the main victims of the transatlantic slave trade from Central Africa?",
                "Arab traders",
                "European explorers",
                "Local chiefs",
                "Africans captured inland",
                "Africans captured inland"));

        questions.add(new Form2QuestionHistory("Which famous missionary explored Central Africa and opposed the slave trade?",
                "David Livingstone",
                "Mary Slessor",
                "Robert Moffat",
                "Albert Schweitzer",
                "David Livingstone"));

        questions.add(new Form2QuestionHistory("What was the impact of European missionaries in Central Africa during the colonial period?",
                "They promoted traditional African religions",
                "They encouraged local languages",
                "They built schools and hospitals",
                "They trained local soldiers",
                "They built schools and hospitals"));

        questions.add(new Form2QuestionHistory("Which Central African kingdom resisted European colonization the longest?",
                "Ashanti Empire",
                "Kingdom of Kongo",
                "Oyo Empire",
                "Buganda Kingdom",
                "Ashanti Empire"));

        questions.add(new Form2QuestionHistory("What was the Berlin Conference of 1884-1885 about in relation to Central Africa?",
                "Negotiating terms of slave trade",
                "Dividing African territories among European powers",
                "Establishing African independence",
                "Forming African trade unions",
                "Dividing African territories among European powers"));

        questions.add(new Form2QuestionHistory("Who was the leader of the Maji Maji Rebellion in German East Africa (Tanzania) against colonial rule?",
                "Jomo Kenyatta",
                "Julius Nyerere",
                "Sekou Toure",
                "Kinjikitile Ngwale",
                "Kinjikitile Ngwale"));

        questions.add(new Form2QuestionHistory("Which Central African country was the first to gain independence from European colonial rule?",
                "Ghana",
                "Kenya",
                "Nigeria",
                "Democratic Republic of Congo",
                "Ghana"));

        questions.add(new Form2QuestionHistory("What was the main economic activity in Central Africa during the colonial period?",
                "Cotton farming",
                "Gold mining",
                "Diamond mining",
                "Rubber production",
                "Rubber production"));

        questions.add(new Form2QuestionHistory("Which European explorer first reached the Congo River and explored Central Africa extensively?",
                "Henry Morton Stanley",
                "David Livingstone",
                "Richard Burton",
                "Mungo Park",
                "Henry Morton Stanley"));

        return questions;
    }

    private void displayQuestion() {

        if (currentQuestionIndex < questions.size()) {

            Form2QuestionHistory currentQuestion = questions.get(currentQuestionIndex);
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
