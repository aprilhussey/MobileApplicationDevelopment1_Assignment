package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class FileStorageActivity extends AppCompatActivity
{
    TextView txtUserInfo;
    TextView txtActivityTitle;
    Button btnNewFile;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);

        User loggedInUser = LoginSystem.loggedInUser;

        txtUserInfo = findViewById(R.id.txtUserInfo);
        txtActivityTitle = findViewById(R.id.txtActivityTitle);
        btnNewFile = findViewById(R.id.btnNewFile);
        recyclerView = findViewById(R.id.recyclerView);

        txtUserInfo.setText(loggedInUser.getUserID() + " - " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

        String currentDate = getCurrentDate();
        String newFileTitle = loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + " - " + currentDate;

        setupRecyclerView();

        btnNewFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                boolean fileExists = doesFileExist(newFileTitle);
                writeFile(newFileTitle, "", fileExists);
                setupRecyclerView();
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        setupRecyclerView();
    }

    public void setupRecyclerView()
    {
        File dir = getFilesDir();
        File[] files = dir.listFiles();
        ArrayList<FileModel> fileModelArrayList = new ArrayList<>();    // Create new array list and add data to it

        for (File file : files)
        {
            String fileTitle = file.getName();
            String fileBlock = readFile(file);
            fileModelArrayList.add(new FileModel(fileTitle, fileBlock));
        }
        FileAdapter fileAdapter = new FileAdapter(this, fileModelArrayList);    // Initialise adapter class and pass array list to it
        recyclerView.setAdapter(fileAdapter);   // Set adapter to recycler view
    }

    public String readFile(File file)
    {
        StringBuilder block = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null)
            {
                block.append(line);
                block.append("\n");
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return block.toString();
    }

    public boolean doesFileExist(String fileTitle)
    {
        File file = new File(getFilesDir(), fileTitle);
        return file.exists();
    }

    public void writeFile(String fileTitle, String fileBlock, boolean fileExists)
    {
        if (fileExists)
        {
            int counter = 1;
            String newFileTitle = fileTitle + " (" + counter + ")";
            while (doesFileExist(newFileTitle))
            {
                counter++;
                newFileTitle = fileTitle + " (" + counter + ")";
            }
            fileTitle = newFileTitle;
        }
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

    public static String getCurrentDate()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = String.format("%02d-%02d-%d", day, month, year);
        return date;
    }
}