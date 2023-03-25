package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity
{
    TextView txtUserInfo;
    TextView txtActivityTitle;
    Button btnFileStorage;
    Button btnQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        User loggedInUser = (User) intent.getSerializableExtra("loggedInUser");

        txtUserInfo = findViewById(R.id.txtUserInfo);
        txtActivityTitle = findViewById(R.id.txtActivityTitle);
        btnFileStorage = findViewById(R.id.btnFileStorage);
        btnQuiz = findViewById(R.id.btnQuiz);
    }
}