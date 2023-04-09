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
    // Declare variables
    TextView txtActivityTitle;
    EditText edtUserID;
    EditText edtPassword;
    Button btnLogin;
    TextView txtDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates instance of LoginSystem class
        LoginSystem loginSystem = new LoginSystem();
        // Creates the fixed logins from FixedLogins class
        FixedLogins.CreateFixedLogins(loginSystem);

        // Initialise variables
        txtActivityTitle = findViewById(R.id.txtActivityTitle);
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
                    txtDisplay.setText("UserID or Password is empty");
                }
                else
                {
                    boolean loginSuccessful = loginSystem.login(strUserID, strPassword);
                    if (loginSuccessful)
                    {
                        User loggedInUser = LoginSystem.getLoggedInUser();
                        String firstName = loggedInUser.getFirstName();
                        String lastName = loggedInUser.getLastName();

                        txtDisplay.setText("Welcome\n" + firstName + " " + lastName);

                        // Create intent to DashboardActivity
                        Intent intentDashboard = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(intentDashboard);
                    }
                    else
                    {
                        txtDisplay.setText("UserID or Password is incorrect");
                    }
                }
            }
        });
    }
}