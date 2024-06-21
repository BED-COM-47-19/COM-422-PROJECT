

package com.example.teachandlearn.Student.Form1.Documents.Agriculture;
import static com.google.firebase.appcheck.internal.util.Logger.TAG;
import android.widget.ScrollView;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.teachandlearn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Form1QuizAgriculture extends AppCompatActivity {


    private TextView questionTextView, questionNumberTextView;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    private List<Form1QuestionAgriculture> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private Button nextButton, backButton, startQuizButton;
    private RadioGroup optionsGroup;

    private double grade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1_quizzes);

        questionTextView = findViewById(R.id.questionTextView);
        questionNumberTextView = findViewById(R.id.questionNumberTextView);
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

    private List<Form1QuestionAgriculture> loadQuestions() {


        List<Form1QuestionAgriculture> questions = new ArrayList<>();
        questions.add(new Form1QuestionAgriculture("What is the main purpose of crop rotation?", "To increase soil acidity", "To improve soil fertility", "To decrease soil nutrients", "To increase pest population", "To improve soil fertility"));
        questions.add(new Form1QuestionAgriculture("Which of the following is a leguminous plant?", "Maize", "Beans", "Wheat", "Rice", "Beans"));
        questions.add(new Form1QuestionAgriculture("What is the process of water movement from the soil into plant roots called?", "Transpiration", "Evaporation", "Osmosis", "Condensation", "Osmosis"));
        questions.add(new Form1QuestionAgriculture("What is the main advantage of organic farming?", "High use of synthetic chemicals", "Increased pollution", "Improved soil health", "Lower biodiversity", "Improved soil health"));
        questions.add(new Form1QuestionAgriculture("Which of these is a common method of irrigation?", "Flooding", "Rainwater harvesting", "Mulching", "Crop rotation", "Flooding"));
        questions.add(new Form1QuestionAgriculture("What is a common pest that affects maize crops?", "Aphids", "Boll weevil", "Armyworm", "Cabbage worm", "Armyworm"));
        questions.add(new Form1QuestionAgriculture("What is the term for growing crops without soil?", "Hydroponics", "Agroforestry", "Aquaponics", "Permaculture", "Hydroponics"));
        questions.add(new Form1QuestionAgriculture("Which soil type is best for growing crops?", "Clayey soil", "Sandy soil", "Loamy soil", "Rocky soil", "Loamy soil"));
        questions.add(new Form1QuestionAgriculture("What is the primary nutrient provided by nitrogen fertilizers?", "Phosphorus", "Potassium", "Nitrogen", "Calcium", "Nitrogen"));
        questions.add(new Form1QuestionAgriculture("Which of the following is a method of soil conservation?", "Deforestation", "Overgrazing", "Terracing", "Slash-and-burn", "Terracing"));
        return questions;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Form1QuestionAgriculture currentQuestion = questions.get(currentQuestionIndex);
            questionNumberTextView.setText("Question " + (currentQuestionIndex + 1) + " of " + questions.size());
            questionTextView.setText(currentQuestion.getQuestionText());
            optionARadioButton.setText(currentQuestion.getOptionA());
            optionBRadioButton.setText(currentQuestion.getOptionB());
            optionCRadioButton.setText(currentQuestion.getOptionC());
            optionDRadioButton.setText(currentQuestion.getOptionD());

            // Restore user's previous answer if available
            String userAnswer = currentQuestion.getUserAnswer();
            if (userAnswer != null) {
                if (userAnswer.equals(optionARadioButton.getText().toString())) {
                    optionARadioButton.setChecked(true);
                } else if (userAnswer.equals(optionBRadioButton.getText().toString())) {
                    optionBRadioButton.setChecked(true);
                } else if (userAnswer.equals(optionCRadioButton.getText().toString())) {
                    optionCRadioButton.setChecked(true);
                } else if (userAnswer.equals(optionDRadioButton.getText().toString())) {
                    optionDRadioButton.setChecked(true);
                }
            } else {
                optionsGroup.clearCheck();
            }
        } else {
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
        Form1QuestionAgriculture currentQuestion = questions.get(currentQuestionIndex);
        currentQuestion.setUserAnswer(selectedAnswer);  // Save the user's answer
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            correctAnswers++;
        }
    }

    private String getCorrectAnswerForCurrentQuestion() {
        return questions.get(currentQuestionIndex).getCorrectAnswer();
    }

    private void finishQuiz() {
        // Hide question and options
        questionTextView.setVisibility(View.GONE);
        optionsGroup.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);

        storeQuizResultToFirestore();

        // Create a ScrollView
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams scrollViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        scrollView.setLayoutParams(scrollViewParams);

        // Create a LinearLayout to hold all content (including grade, questions, and options)
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Display grade
        double grade = (double) correctAnswers / questions.size() * 100;
        TextView gradeTextView = new TextView(this);
        gradeTextView.setText("Grade: " + String.format("%.2f", grade) + "%");
        gradeTextView.setTextSize(18);
        gradeTextView.setTextColor(Color.BLACK);
        contentLayout.addView(gradeTextView);

        // Display solutions
        for (int i = 0; i < questions.size(); i++) {
            Form1QuestionAgriculture question = questions.get(i);

            TextView questionTextView = new TextView(this);
            questionTextView.setText("Question " + (i + 1) + ": " + question.getQuestionText());
            questionTextView.setTextSize(16);
            questionTextView.setTextColor(Color.BLACK);
            contentLayout.addView(questionTextView);

            String[] options = {question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD()};
            for (String option : options) {
                TextView optionTextView = new TextView(this);
                optionTextView.setText(option);
                optionTextView.setTextSize(16);

                if (option.equals(question.getCorrectAnswer())) {
                    optionTextView.setTextColor(Color.BLUE); // Correct answer in blue
                } else if (option.equals(question.getUserAnswer())) {
                    optionTextView.setTextColor(Color.RED); // User's answer in red
                } else {
                    optionTextView.setTextColor(Color.BLACK); // Default color for other options
                }

                contentLayout.addView(optionTextView);
            }

            // Add some space between questions
            TextView spaceTextView = new TextView(this);
            spaceTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    16 // Adjust spacing as needed
            ));
            contentLayout.addView(spaceTextView);
        }

        // Add the contentLayout to the scrollView
        scrollView.addView(contentLayout);

        // Add the scrollView to the rootLayout
        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        rootLayout.addView(scrollView);
    }



    private void storeQuizResultToFirestore() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String studentEmail = user.getEmail();

            Map<String, Object> quizResult = new HashMap<>();
            quizResult.put("studentEmail", studentEmail);
            quizResult.put("grade", String.format("%.2f", grade) + "%");
            quizResult.put("timestamp", FieldValue.serverTimestamp());

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("quiz_results_agriculture")
                    .add(quizResult)
                    .addOnSuccessListener(documentReference -> {
                        Log.d(TAG, "Quiz result added for student: " + studentEmail);
                        // Notify teacher of new quiz result (if needed)
                    })
                    .addOnFailureListener(e -> {
                        Log.w(TAG, "Error adding quiz result", e);
                    });
        }
    }

    private void notifyTeacher(String studentEmail, double grade) {
        // Example: Logging the notification
        Log.d(TAG, "Notification sent to teacher for new quiz result by student: " + studentEmail +
                ". Grade: " + String.format("%.2f", grade) + "%");
    }




}
