package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class QuizMarkActivity extends AppCompatActivity
{
    TextView txtUserInfo;
    TextView txtActivityTitle;
    TextView txtQuizMark;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_mark);

        User loggedInUser = LoginSystem.loggedInUser;

        txtUserInfo = findViewById(R.id.txtUserInfo);
        txtActivityTitle = findViewById(R.id.txtActivityTitle);
        txtQuizMark = findViewById(R.id.txtQuizMark);

        // Get data from Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);

        // Update views
        txtQuizMark.setText("Final score: " + score + "/" + totalQuestions);
    }
}