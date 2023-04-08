package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class FileActivity extends AppCompatActivity
{
    Context context = this;

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

                    boolean fileExists = doesFileExist(context, newFileTitle);
                    String newNewFileTitle = renameFile(context, fileTitle, newFileTitle, fileExists);
                    saveFile(context, newNewFileTitle, newFileBlock);

                    boolean checkFileExists = doesFileExist(context, newNewFileTitle);
                    if (checkFileExists)
                    {
                        Toast.makeText(context, "File saved", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(context, "File could not be saved", Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        newFileTitle = edtFileTitle.getText().toString();
        newFileBlock = edtFileBlock.getText().toString();

        Log.d("File Title: ", fileTitle);
        Log.d("New File Title: ", newFileTitle);
        Log.d("File Block: ", fileBlock);
        Log.d("New File Block: ", newFileBlock);

        if (!fileTitle.equals(newFileTitle) || !fileBlock.equals(newFileBlock))
        {
            // Create an AlertDialog
            new AlertDialog.Builder(context)
                    .setTitle("Save Unsaved Changes?")
                    .setMessage("Would you like to save unsaved changes to this file?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            boolean fileExists = doesFileExist(context, newFileTitle);
                            String newNewFileTitle = renameFile(context, fileTitle, newFileTitle, fileExists);
                            saveFile(context, newNewFileTitle, newFileBlock);

                            boolean checkFileExists = doesFileExist(context, newNewFileTitle);
                            if (checkFileExists)
                            {
                                Toast.makeText(context, "File saved", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(context, "File could not be saved", Toast.LENGTH_SHORT).show();
                            }

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
        else
        {
            FileActivity.super.onBackPressed();
        }
    }

    public static boolean doesFileExist(Context context, String fileTitle)
    {
        File file = new File(context.getFilesDir(), fileTitle);
        return file.exists();
    }

    public static String renameFile(Context context, String oldFileTitle, String newFileTitle, boolean fileExists)
    {
        if (oldFileTitle.equals(newFileTitle))
        {
            return newFileTitle;
        }
        else
        {
            File oldFile = new File(context.getFilesDir(), oldFileTitle);
            File newFile;

            if (fileExists)
            {
                int counter = 1;
                String counterFileTitle = newFileTitle + " (" + counter + ")";

                while (doesFileExist(context, counterFileTitle))
                {
                    counter++;
                    counterFileTitle = newFileTitle + " (" + counter + ")";
                }
                newFile = new File(context.getFilesDir(), counterFileTitle);
            }
            else
            {
                newFile = new File(context.getFilesDir(), newFileTitle);
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

    public static void saveFile(Context context, String fileTitle, String fileBlock)    // Is static so that it can be used in different activities
    {
        FileOutputStream outputStream;
        try
        {
            outputStream = context.openFileOutput(fileTitle, Context.MODE_PRIVATE);
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