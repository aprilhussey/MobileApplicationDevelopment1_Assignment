package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    EditText edtUserID;
    EditText edtPassword;
    Button btnLogin;
    TextView txtDisplay;

    //String strFixedUsername = "Username";
    //String strFixedPassword = "Password";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates instance of LoginSystem class
        LoginSystem loginSystem = new LoginSystem();
        // Creates the fixed logins from FixedLogins class
        FixedLogins.CreateFixedLogins(loginSystem);

        edtUserID = findViewById(R.id.edtUserID);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtDisplay = findViewById(R.id.txtDisplay);

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String strUserID = edtUserID.getText().toString();
                String strPassword = edtPassword.getText().toString();

                if (strUserID.isEmpty() || strPassword.isEmpty())
                {
                    txtDisplay.setText("Username or Password is incorrect");
                }
                else
                {
                    boolean loginSuccessful = loginSystem.login(strUserID, strPassword);
                    if (loginSuccessful)
                    {
                        User loggedInUser = loginSystem.getLoggedInUser();
                        String firstName = loggedInUser.getFirstName();
                        String lastName = loggedInUser.getLastName();
                        txtDisplay.setText("Welcome " + firstName + " " + lastName);
                        //Intent intentDashboard = new Intent(MainActivity.this, Dashboard.class);
                        //startActivity(intentDashboard);
                    }
                    else
                    {
                        txtDisplay.setText("Username or Password is incorrect");
                    }

                    /*if (strUserID.equals(strFixedUsername) && strPassword.equals(strFixedPassword))
                    {
                        txtDisplay.setText("Welcome");
                        //Intent intentDashboard = new Intent(MainActivity.this, Dashboard.class);
                        //startActivity(intentDashboard);
                    }
                    else
                    {
                        txtDisplay.setText("Username or Password is incorrect");
                    }*/
                }
            }
        });
    }
}