package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    ImageView imgQuestion;
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
            new Question("1. What is the value of 7 in the number 475.6?", new String[] {"A) 7", "B) 70", "C) 0.7", "D) 7/100"}, 1),
            new Question("2. What is the name of a quadrilateral that has one pair of parallel sides?", new String[] {"A) Square", "B) Rectangle", "C) Trapezium", "D) Rhombus"}, 2),
            new Question("3. What is the answer to this calculation: (4 x 5) + (6 x 3) - (2 x 4)?", new String[] {"A) 14", "B) 24", "C) 34", "D) 44"}, 2),
            new Question("4. What is the equivalent fraction to 3/4?", new String[] {"A) 6/8", "B) 9/12", "C) Both A and B", "D) Neither A nor B"}, 2),
            new Question(R.drawable.question5, "5. What is the percentage of a circle that is shaded in this diagram?", new String[] {"A) 10%", "B) 25%", "C) 50%", "D) 75%"}, 1),
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
        imgQuestion = findViewById(R.id.imgQuestion);
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
        if (currentQuestion.hasImage())
        {
            imgQuestion.setVisibility(View.VISIBLE);
            imgQuestion.setImageResource(currentQuestion.getQuestionImage());
        }
        else
        {
            imgQuestion.setVisibility(View.GONE);
        }
        txtQuestion.setText(currentQuestion.getQuestionText());
        String[] answerOptions = currentQuestion.getAnswerOptions();
        rbtnOption1.setText(answerOptions[0]);
        rbtnOption2.setText(answerOptions[1]);
        rbtnOption3.setText(answerOptions[2]);
        rbtnOption4.setText(answerOptions[3]);
    }
}