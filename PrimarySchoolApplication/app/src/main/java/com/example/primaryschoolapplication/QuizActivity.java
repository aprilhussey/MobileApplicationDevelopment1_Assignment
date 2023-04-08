package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity
{
    // Declare variables
    TextView txtUserInfo;
    TextView txtActivityTitle;
    TextView txtQuestion;
    RadioGroup radioGroup;
    RadioButton rbtnOption1;
    RadioButton rbtnOption2;
    RadioButton rbtnOption3;
    RadioButton rbtnOption4;
    Button btnSubmitAnswer;

    Question currentQuestion;
    int currentQuestionIndex;
    int score;

    // Create questions
    Question[] questions = new Question[] {
            new Question("Question 1", new String[] {"Option 1", "Option 2", "Option 3", "Option 4"}, 0),
            new Question("Question 2", new String[] {"Option 1", "Option 2", "Option 3", "Option 4"}, 0),
            new Question("Question 3", new String[] {"Option 1", "Option 2", "Option 3", "Option 4"}, 0),
            new Question("Question 4", new String[] {"Option 1", "Option 2", "Option 3", "Option 4"}, 0),
            new Question("Question 5", new String[] {"Option 1", "Option 2", "Option 3", "Option 4"}, 0),
            new Question("Question 6", new String[] {"Option 1", "Option 2", "Option 3", "Option 4"}, 0),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialise variables
        User loggedInUser = LoginSystem.loggedInUser;

        txtUserInfo = findViewById(R.id.txtUserInfo);
        txtActivityTitle = findViewById(R.id.txtActivityTitle);
        txtQuestion = findViewById(R.id.txtQuestion);
        radioGroup = findViewById(R.id.radioGroup);
        rbtnOption1 = findViewById(R.id.rbtnOption1);
        rbtnOption2 = findViewById(R.id.rbtnOption2);
        rbtnOption3 = findViewById(R.id.rbtnOption3);
        rbtnOption4 = findViewById(R.id.rbtnOption4);
        btnSubmitAnswer = findViewById(R.id.btnSubmitAnswer);

        // Set user info
        txtUserInfo.setText(loggedInUser.getUserID() + " - " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

        // Set up current question index
        currentQuestionIndex = 0;

        // Set up score
        score = 0;

        // Update views for current question
        UpdateQuestionViews(currentQuestionIndex);

        // Set up submit answer button onClickListener
        btnSubmitAnswer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Check if selected option is correct
                int selectedOptionId = radioGroup.getCheckedRadioButtonId();
                int correctOptionIndex = currentQuestion.getCorrectAnswerIndex();
                RadioButton correctOptionRButton = (RadioButton) radioGroup.getChildAt(correctOptionIndex);
                int correctOptionId = correctOptionRButton.getId();

                if (selectedOptionId == correctOptionId)
                {
                    score++;
                }
                // Move to next question
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length)
                {
                    // Update views for next question
                    UpdateQuestionViews(currentQuestionIndex);
                }
                else
                {
                    Intent intentQuizMark = new Intent(QuizActivity.this, QuizMarkActivity.class);
                    intentQuizMark.putExtra("score", score);
                    intentQuizMark.putExtra("totalQuestions", questions.length);
                    intentQuizMark.putExtra("quizName", txtActivityTitle.getText());
                    startActivity(intentQuizMark);
                }
            }
        });
    }

    public void UpdateQuestionViews(int currentQuestionIndex)
    {
        // Update views for current question
        currentQuestion = questions[currentQuestionIndex];
        txtQuestion.setText(currentQuestion.getQuestionText());
        String[] answerOptions = currentQuestion.getAnswerOptions();
        rbtnOption1.setText(answerOptions[0]);
        rbtnOption2.setText(answerOptions[1]);
        rbtnOption3.setText(answerOptions[2]);
        rbtnOption4.setText(answerOptions[3]);
    }
}