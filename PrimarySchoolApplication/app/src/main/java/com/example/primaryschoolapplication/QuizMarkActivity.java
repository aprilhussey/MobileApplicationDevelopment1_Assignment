package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuizMarkActivity extends AppCompatActivity
{
    // Declare variables
    Context context = this;
    User loggedInUser;

    TextView txtUserInfo;
    TextView txtActivityTitle;
    TextView txtQuizMark;
    Button btnSaveScore;
    Button btnExit;

    int score;
    int totalQuestions;
    boolean fileExists;
    String fileTitle;
    String fileBlock;
    boolean fileSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_mark);

        // Initialise variables
        loggedInUser = LoginSystem.loggedInUser;

        txtUserInfo = findViewById(R.id.txtUserInfo);
        txtActivityTitle = findViewById(R.id.txtActivityTitle);
        txtQuizMark = findViewById(R.id.txtQuizMark);
        btnSaveScore = findViewById(R.id.btnSaveScore);
        btnExit = findViewById(R.id.btnExit);

        fileSaved = false;

        // Set user info
        txtUserInfo.setText(loggedInUser.getUserID() + " - " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

        // Get data from intent
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        totalQuestions = intent.getIntExtra("totalQuestions", 0);
        String quizName = intent.getStringExtra("quizName");

        txtActivityTitle.setText(quizName);

        // Update views
        txtQuizMark.setText("SCORE\n" + score + "/" + totalQuestions);

        btnSaveScore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String currentDate = FileStorageActivity.getCurrentDate();
                fileTitle = txtActivityTitle.getText() + " - " + currentDate;
                fileBlock = loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + " - " + score + "/" + totalQuestions;

                checkForFile(false);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String currentDate = FileStorageActivity.getCurrentDate();
                fileTitle = txtActivityTitle.getText() + " - " + currentDate;
                fileBlock = loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + " - " + score + "/" + totalQuestions;

                checkForFile(true);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        String currentDate = FileStorageActivity.getCurrentDate();
        fileTitle = txtActivityTitle.getText() + " - " + currentDate;
        fileBlock = loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + " - " + score + "/" + totalQuestions;

        checkForFile(true);
    }

    public void checkForFile(boolean savePopup)
    {
        fileExists = FileActivity.doesFileExist(context, fileTitle);

        if (fileExists)
        {
            if (savePopup)
            {
                saveDialogPopup();
            }
            else
            {
                createNewFile();
            }
        }
        else // File does not exist
        {
            if (savePopup)
            {
                saveDialogPopup();
            }
            else
            {
                createNewFile();
            }
        }
    }

    public void saveDialogPopup()
    {
        // Create AlertDialog
        new AlertDialog.Builder(context)
                .setTitle("Save your Score?")
                .setMessage("Would you like to save your score to a file?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        createNewFile();

                        Intent intentDashboard = new Intent(QuizMarkActivity.this, DashboardActivity.class);
                        intentDashboard.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentDashboard);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Intent intentDashboard = new Intent(QuizMarkActivity.this, DashboardActivity.class);
                        intentDashboard.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentDashboard);
                    }
                })
                .setNeutralButton("Cancel", null)
                .show();
    }

    public void createNewFile()
    {
        int counter = 0;
        String counterFileTitle = fileTitle;
        while (FileActivity.doesFileExist(context, counterFileTitle))
        {
            counter++;
            counterFileTitle = fileTitle + " (" + counter + ")";
        }
        if (!fileSaved)
        {
            FileActivity.saveFile(context, counterFileTitle, fileBlock);

            boolean checkFileExists = FileActivity.doesFileExist(context, counterFileTitle);
            if (checkFileExists)
            {
                Toast.makeText(context, "Score saved", Toast.LENGTH_SHORT).show();
                fileSaved = true;
            }
            else
            {
                Toast.makeText(context, "Score could not be saved", Toast.LENGTH_SHORT).show();
            }
        }
    }
}