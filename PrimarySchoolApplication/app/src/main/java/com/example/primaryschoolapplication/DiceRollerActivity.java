package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
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

        // Load animations from anim folder
        Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_down);
        Animation scaleUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
        Animation rotate360 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate360);


        // Create animation sets
        AnimationSet animDownSet = new AnimationSet(true);
        animDownSet.addAnimation(scaleDown);
        animDownSet.addAnimation(rotate360);

        AnimationSet animUpSet = new AnimationSet(true);
        animUpSet.addAnimation(scaleUp);
        animUpSet.addAnimation(rotate360);

        btnRollDie.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                imgDie.startAnimation(animDownSet);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        rollDie();
                        imgDie.startAnimation(animUpSet);
                    }
                }, 1000);
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