package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity
{
    // Declare variables
    TextView txtUserInfo;
    TextView txtActivityTitle;
    ImageButton btnFileStorage;
    ImageButton btnQuiz;

    ImageButton btnDiceRoller;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialise variables
        User loggedInUser = LoginSystem.loggedInUser;

        txtUserInfo = findViewById(R.id.txtUserInfo);
        txtActivityTitle = findViewById(R.id.txtActivityTitle);
        btnFileStorage = findViewById(R.id.btnFileStorage);
        btnQuiz = findViewById(R.id.btnQuiz);
        btnDiceRoller = findViewById(R.id.btnDiceRoller);

        // Set user info
        txtUserInfo.setText(loggedInUser.getUserID() + " - " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

        btnFileStorage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intentFileStorage = new Intent(DashboardActivity.this, FileStorageActivity.class);
                startActivity(intentFileStorage);
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intentQuiz = new Intent(DashboardActivity.this, QuizActivity.class);
                startActivity(intentQuiz);
            }
        });

        btnDiceRoller.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intentDiceRoller = new Intent(DashboardActivity.this, DiceRollerActivity.class);
                startActivity(intentDiceRoller);
            }
        });
    }
}