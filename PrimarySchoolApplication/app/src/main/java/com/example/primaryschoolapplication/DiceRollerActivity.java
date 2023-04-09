package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class DiceRollerActivity extends AppCompatActivity
{
    // Declare variables
    TextView txtUserInfo;
    TextView txtActivityTitle;
    ImageView imgDie;
    Button btnRollDie;
    private Random rng;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller);

        // Initialise variables
        User loggedInUser = LoginSystem.loggedInUser;

        txtUserInfo = findViewById(R.id.txtUserInfo);
        txtActivityTitle = findViewById(R.id.txtActivityTitle);
        imgDie = findViewById(R.id.imgDie);
        btnRollDie = findViewById(R.id.btnRollDie);
        rng = new Random();

        // Set user info
        txtUserInfo.setText(loggedInUser.getUserID() + " - " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

        btnRollDie.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                rollDie();
            }
        });
    }

    private void rollDie()
    {
        int randomNumber = rng.nextInt(6) + 1;

        switch (randomNumber)
        {
            case 1:
                imgDie.setImageResource(R.drawable.die1);
                break;
            case 2:
                imgDie.setImageResource(R.drawable.die2);
                break;
            case 3:
                imgDie.setImageResource(R.drawable.die3);
                break;
            case 4:
                imgDie.setImageResource(R.drawable.die4);
                break;
            case 5:
                imgDie.setImageResource(R.drawable.die5);
                break;
            case 6:
                imgDie.setImageResource(R.drawable.die6);
                break;
        }
    }
}