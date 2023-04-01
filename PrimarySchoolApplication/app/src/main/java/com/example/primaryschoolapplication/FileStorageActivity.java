package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class FileStorageActivity extends AppCompatActivity
{
    TextView txtUserInfo;
    TextView txtActivityTitle;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);

        User loggedInUser = LoginSystem.loggedInUser;

        txtUserInfo = findViewById(R.id.txtUserInfo);
        txtActivityTitle = findViewById(R.id.txtActivityTitle);
        recyclerView = findViewById(R.id.recyclerView);

        txtUserInfo.setText(loggedInUser.getUserID() + " - " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

        // Create new array list and add data to it
        ArrayList<FileModel> fileModelArrayList = new ArrayList<FileModel>();
        fileModelArrayList.add(new FileModel("titleTest1", "blockTest1"));
        fileModelArrayList.add(new FileModel("titleTest2", "blockTest2"));
        fileModelArrayList.add(new FileModel("titleTest3", "blockTest3"));
        fileModelArrayList.add(new FileModel("titleTest4", "blockTest4"));
        fileModelArrayList.add(new FileModel("titleTest5", "blockTest5"));
        fileModelArrayList.add(new FileModel("titleTest6", "blockTest6"));
        fileModelArrayList.add(new FileModel("titleTest7", "blockTest7"));
        fileModelArrayList.add(new FileModel("titleTest8", "blockTest8"));
        fileModelArrayList.add(new FileModel("titleTest9", "blockTest9"));
        fileModelArrayList.add(new FileModel("titleTest10", "blockTest10"));

        // Initialise adapter class and pass array list to it
        FileAdapter fileAdapter = new FileAdapter(this, fileModelArrayList);

        // Set adapter to recycler view
        recyclerView.setAdapter(fileAdapter);
    }
    public void onCardViewClick(View view)
    {
        TextView txtFileTitleView = (TextView) view.findViewById(R.id.txtFileTitle);
        TextView txtFileBlockView = (TextView) view.findViewById(R.id.txtFileBlock);

        Intent intentFile = new Intent(this, FileActivity.class);
        intentFile.putExtra("fileTitle", txtFileTitleView.getText().toString());
        intentFile.putExtra("fileBlock", txtFileBlockView.getText().toString());
        startActivity(intentFile);
    }
}