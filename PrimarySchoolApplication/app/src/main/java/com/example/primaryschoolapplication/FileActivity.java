package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class FileActivity extends AppCompatActivity
{
    TextView txtUserInfo;
    EditText edtFileTitle;
    EditText edtFileBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        User loggedInUser = LoginSystem.loggedInUser;

        txtUserInfo = findViewById(R.id.txtUserInfo);
        edtFileTitle = findViewById(R.id.edtFileTitle);
        edtFileBlock = findViewById(R.id.edtFileBlock);

        txtUserInfo.setText(loggedInUser.getUserID() + " - " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

        Intent intentFile = getIntent();
        String fileTitle = intentFile.getStringExtra("fileTitle");
        String fileBlock = intentFile.getStringExtra("fileBlock");

        edtFileTitle.setText(fileTitle);
        edtFileBlock.setText(fileBlock);
    }
}