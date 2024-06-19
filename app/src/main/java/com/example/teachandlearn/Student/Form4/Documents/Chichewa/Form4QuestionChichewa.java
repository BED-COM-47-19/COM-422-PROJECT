package com.example.teachandlearn.Student.Form4.Documents.Chichewa;

import androidx.appcompat.app.AppCompatActivity;

public class Form4QuestionChichewa extends AppCompatActivity {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    public Form4QuestionChichewa(String questionText, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
