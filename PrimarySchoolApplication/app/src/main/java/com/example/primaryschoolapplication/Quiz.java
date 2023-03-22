package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Quiz extends AppCompatActivity
{
    TextView txtUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        txtUserInfo = findViewById(R.id.txtUserInfo);
    }
}