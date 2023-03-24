package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;

public class NewNote extends AppCompatActivity
{
    TextView txtUserInfo;

    EditText edtNoteTitle;
    EditText edtNoteBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        txtUserInfo = findViewById(R.id.txtUserInfo);

        edtNoteTitle = findViewById(R.id.edtNoteTitle);
        edtNoteBlock = findViewById(R.id.edtNoteBlock);

        //String noteTitle = edtNoteTitle.getText().toString();
        //String noteBlock = edtNoteBlock.getText().toString();

        //FileOutputStream fos = openFileOutput(noteTitle, Context.MODE_PRIVATE);
        //fos.write(noteBlock.getBytes());
        //fos.close();
    }
}