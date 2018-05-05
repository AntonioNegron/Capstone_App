package com.example.antonio.greenhouse_testapp1;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText inputEmail;
    EditText inputPass;
    String email;
    String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email_input);
        inputPass = (EditText) findViewById(R.id.pass_input);

        loginButton=(Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(verifyCredentials())
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.welcome),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    finish();
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_invalid_credentials),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean verifyCredentials()
    {
        boolean valid = false;

        String checkEmail = inputEmail.getText().toString();
        String checkPass = inputPass.getText().toString();

        // Logic where database is compared to current data on fields

        if (checkEmail.equals(getDBEmail()) && checkPass.equals(getDBPassword()))
        {
            valid = true;
        }
        else
        {
            valid = false;
        }

        return valid;
    }

    private String getDBEmail()
    {
        email = ""; //"test@email.com";

        return email;
    }

    private String getDBPassword()
    {
        password = ""; //"password";

        return password;
    }

}
