package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Notes extends AppCompatActivity
{
    TextView txtUserInfo;
    Button btnNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        txtUserInfo = findViewById(R.id.txtUserInfo);
        btnNewNote = findViewById(R.id.btnNewNote);

        btnNewNote.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intentNewNote = new Intent(Notes.this, NewNote.class);
                startActivity(intentNewNote);
            }
        });
    }
}