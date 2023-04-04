package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

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

    public void renameFile(String oldFileTitle, String newFileTitle)
    {
        newFileTitle = newFileTitle.replace("/", "-");

        File oldFile = new File(getFilesDir(), oldFileTitle);
        File newFile = new File(getFilesDir(), newFileTitle);

        boolean renameSuccess = oldFile.renameTo(newFile);
        if (renameSuccess)
        {
            Log.d("Files", "File renamed successfully");
        }
        else
        {
            Log.d("Files", "Failed to rename file");
        }
    }

    public boolean doesFileExist(String fileTitle)
    {
        File file = new File(getFilesDir(), fileTitle);
        return file.exists();
    }
}