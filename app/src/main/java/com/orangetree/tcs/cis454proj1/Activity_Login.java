package com.orangetree.tcs.cis454proj1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.PatternSyntaxException;

public class Activity_Login extends AppCompatActivity {

    private String name, password, message;
    private EditText etUserName, etPassword;
    private Button btnLogin, btnRegister;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper helper = new DatabaseHelper(getApplicationContext());



                name = etUserName.getText().toString();
                password = etPassword.getText().toString();

                if (validateEmpty()) {
                    Boolean checkUserName = helper.databaseContains(name);
                    String checkPassword = helper.getPassword(name);
                    if ((helper.databaseContains(name)) && (checkPassword.equals(password))) {
                        Intent loginIntent = new Intent(Activity_Login.this, MainActivity.class);
                        startActivity(loginIntent);
                    } else {
                        tvMessage.setText("Your username or password were entered incorrectly");
                    }
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Activity_Login.this, Activity_Register.class);
                startActivity(registerIntent);
            }
        });
    }

    public boolean validateEmpty() {

        boolean valid = true;

        if (name.isEmpty()) {
            etUserName.setError("Please enter a valid username");
            valid = false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Please enter a password");
            valid = false;
        }

        return valid;
    }
}
