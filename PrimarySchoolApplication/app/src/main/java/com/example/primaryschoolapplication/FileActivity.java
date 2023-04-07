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
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class FileActivity extends AppCompatActivity
{
    TextView txtUserInfo;
    Button btnSaveFile;
    EditText edtFileTitle;
    EditText edtFileBlock;

    String fileTitle;
    String fileBlock;
    String newFileTitle;
    String newFileBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        User loggedInUser = LoginSystem.loggedInUser;

        txtUserInfo = findViewById(R.id.txtUserInfo);
        btnSaveFile = findViewById(R.id.btnSaveFile);
        edtFileTitle = findViewById(R.id.edtFileTitle);
        edtFileBlock = findViewById(R.id.edtFileBlock);

        txtUserInfo.setText(loggedInUser.getUserID() + " - " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

        Intent intentFile = getIntent();
        fileTitle = intentFile.getStringExtra("fileTitle");
        fileBlock = intentFile.getStringExtra("fileBlock");

        edtFileTitle.setText(fileTitle);
        edtFileBlock.setText(fileBlock);

        btnSaveFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                newFileTitle = edtFileTitle.getText().toString();
                newFileBlock = edtFileBlock.getText().toString();

                boolean fileExists = doesFileExist(newFileTitle);
                renameFile(fileTitle, newFileTitle, fileExists);
                saveFile(newFileTitle, newFileBlock);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Context context = this;
        newFileTitle = edtFileTitle.getText().toString();
        newFileBlock = edtFileBlock.getText().toString();

        // Create an AlertDialog
        new AlertDialog.Builder(context)
                .setTitle("Save Unsaved Changes?")
                .setMessage("Would you like to save unsaved changes to this file?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        boolean fileExists = doesFileExist(newFileTitle);
                        renameFile(fileTitle, newFileTitle, fileExists);
                        saveFile(newFileTitle, newFileBlock);
                        FileActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        FileActivity.super.onBackPressed();
                    }
                })
                .setNeutralButton("Cancel", null)
                .show();
    }

    public boolean doesFileExist(String fileTitle)
    {
        File file = new File(getFilesDir(), fileTitle);
        return file.exists();
    }

    public void renameFile(String oldFileTitle, String newFileTitle, boolean fileExists)
    {
        if (oldFileTitle.equals(newFileTitle))
        {
            return;
        }
        else
        {
            File oldFile = new File(getFilesDir(), oldFileTitle);
            File newFile;

            if (fileExists)
            {
                int counter = 1;
                String counterFileTitle = newFileTitle + " (" + counter + ")";

                while (doesFileExist(counterFileTitle))
                {
                    counter++;
                    counterFileTitle = newFileTitle + " (" + counter + ")";
                }
                newFile = new File(getFilesDir(), counterFileTitle);
            }
            else
            {
                newFile = new File(getFilesDir(), newFileTitle);
            }

            boolean renameSuccess = oldFile.renameTo(newFile);

            if (renameSuccess)
            {
                Log.d("Files", "File renamed successfully");
            } else {
                Log.d("Files", "Failed to rename file");
            }
        }
    }

    public void saveFile(String fileTitle, String fileBlock)
    {
        FileOutputStream outputStream;
        try
        {
            outputStream = openFileOutput(fileTitle, Context.MODE_PRIVATE);
            outputStream.write(fileBlock.getBytes());
            outputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("Error", "Failed to write file: " + e.getMessage());
        }
    }
}