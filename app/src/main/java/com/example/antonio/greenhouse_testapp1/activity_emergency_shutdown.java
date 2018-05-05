package com.example.antonio.greenhouse_testapp1;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;


public class activity_emergency_shutdown extends AppCompatActivity {

    Button shutdown_button;
    Switch switch_allow_shutdown;
    Snackbar shutdown_SnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_shutdown);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        shutdown_button  = (Button) findViewById(R.id.shutdown_button);
        switch_allow_shutdown = (Switch) findViewById(R.id.switch_shutdown);
        shutdown_SnackBar = Snackbar.make(findViewById(android.R.id.content),getResources().getString(R.string.system_feedback), Snackbar.LENGTH_SHORT);

        checkSwitch();

        checkButton();



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void checkSwitch()
    {
        switch_allow_shutdown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (switch_allow_shutdown.isChecked()) {
                    shutdown_button.setEnabled(true);
                } else {
                    shutdown_button.setEnabled(false);
                }

            }


        });


    }

    private void checkButton()
    {

        shutdown_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (shutdown_button.isEnabled())
                {
                    shutdown_SnackBar.show();
                }

            }
        });



    }

}
