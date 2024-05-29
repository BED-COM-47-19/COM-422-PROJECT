package com.example.teachandlearn;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherActivity extends AppCompatActivity {

    private EditText editTextQuizTitle, editTextQuizQuestion, editTextQuizAnswer;
    private Button buttonAddQuiz;
    private FirebaseDatabase database;
    private DatabaseReference quizzesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        editTextQuizTitle = findViewById(R.id.editTextQuizTitle);
        editTextQuizQuestion = findViewById(R.id.editTextQuizQuestion);
        editTextQuizAnswer = findViewById(R.id.editTextQuizAnswer);
        buttonAddQuiz = findViewById(R.id.buttonAddQuiz);

        database = FirebaseDatabase.getInstance();
        quizzesRef = database.getReference("quizzes");

        buttonAddQuiz.setOnClickListener(v -> addQuiz());
    }

    private void addQuiz() {
        String title = editTextQuizTitle.getText().toString().trim();
        String question = editTextQuizQuestion.getText().toString().trim();
        String answer = editTextQuizAnswer.getText().toString().trim();

        if (!title.isEmpty() && !question.isEmpty() && !answer.isEmpty()) {
            String quizId = quizzesRef.push().getKey();
            Quiz quiz = new Quiz(quizId, title, question, answer);
            quizzesRef.child(quizId).setValue(quiz)
                    .addOnSuccessListener(aVoid -> Toast.makeText(this, "Quiz added successfully", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Failed to add quiz", Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    public static class Quiz {
        public String id;
        public String title;
        public String question;
        public String answer;

        public Quiz() { }

        public Quiz(String id, String title, String question, String answer) {
            this.id = id;
            this.title = title;
            this.question = question;
            this.answer = answer;
        }
    }
}
