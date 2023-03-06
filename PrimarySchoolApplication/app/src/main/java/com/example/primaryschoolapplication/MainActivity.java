package com.example.primaryschoolapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String strFixedUsername = "Username";
    String strFixedPassword = "Password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtUsername = findViewByID(R.id.edtUsername);
        EditText edtPassword = findViewByID(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView txtDisplay = findViewById(R.id.txtDisplay);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = edtUsername.getText().toString();
                String strPassword = edtPassword.getText().toString();

                User u1 = new User(strUsername, strPassword);

                if (strUsername.isEmpty() || strPassword.isEmpty()) {
                    txtDisplay.setText("Username or Password is empty");
                } else {
                    if (strUsername.equals(strFixedUsername) && strPassword.equals(strFixedPassword)) {
                        txtDisplay.setText("Welcome");
                    } else {
                        txtDisplay.setText("Username or Password is incorrect");
                    }
                }
            }
        });
    }
        private EditText findViewByID(int edtUsername) { return null; }
}