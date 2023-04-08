package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class QuizMarkActivity extends AppCompatActivity
{
    Context context = this;

    TextView txtUserInfo;
    TextView txtActivityTitle;
    TextView txtQuizMark;
    Button btnSaveScore;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_mark);

        User loggedInUser = LoginSystem.loggedInUser;

        txtUserInfo = findViewById(R.id.txtUserInfo);
        txtActivityTitle = findViewById(R.id.txtActivityTitle);
        txtQuizMark = findViewById(R.id.txtQuizMark);
        btnSaveScore = findViewById(R.id.btnSaveScore);
        btnExit = findViewById(R.id.btnExit);

        txtUserInfo.setText(loggedInUser.getUserID() + " - " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

        // Get data from Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);
        String quizName = intent.getStringExtra("quizName");

        txtActivityTitle.setText(quizName);

        // Update views
        txtQuizMark.setText("Final score: " + score + "/" + totalQuestions);

        btnSaveScore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Save file
                String currentDate = FileStorageActivity.getCurrentDate();
                String fileTitle = txtActivityTitle.getText() + " - " + currentDate;
                String fileBlock = loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + " - " + score + "/" + totalQuestions;

                boolean fileExists = FileActivity.doesFileExist(context, fileTitle);
                String newFileTitle = renameFile(context, fileTitle, fileExists);
                FileActivity.saveFile(context, newFileTitle, fileBlock);

                boolean checkFileExists = FileActivity.doesFileExist(context, newFileTitle);
                if (checkFileExists)
                {
                    Toast.makeText(context, "Score saved", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context, "Score could not be saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Add popup to save??

                Intent intentDashboard = new Intent(QuizMarkActivity.this, DashboardActivity.class);
                intentDashboard.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentDashboard);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        // Override back pressed so it does not go back to quiz questions
        Intent intentDashboard = new Intent(QuizMarkActivity.this, DashboardActivity.class);
        intentDashboard.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentDashboard);
    }

    // Different version of renameFile function to one existing in FileActivity.java
    public static String renameFile(Context context, String fileTitle, boolean fileExists)
    {
        File oldFile = new File(context.getFilesDir(), fileTitle);
        File newFile;

        if (fileExists)
        {
            int counter = 1;
            String counterFileTitle = fileTitle + " (" + counter + ")";

            while (FileActivity.doesFileExist(context, counterFileTitle))
            {
                counter++;
                counterFileTitle = fileTitle + " (" + counter + ")";
            }
            newFile = new File(context.getFilesDir(), counterFileTitle);
        }
        else
        {
            newFile = new File(context.getFilesDir(), fileTitle);
        }

        boolean renameSuccess = oldFile.renameTo(newFile);

        if (renameSuccess)
        {
            Log.d("Files", "File renamed successfully");
            return newFile.getName();
        }
        else
        {
            Log.d("Files", "Failed to rename file");
            return oldFile.getName();
        }
    }
}