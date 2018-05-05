package com.example.antonio.greenhouse_testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button humidityButtonToActivity;
    Button temperatureButtonToActivity;
    Button co2ButtonToActivity;
    Button phButtonToActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        humidityButtonToActivity=(Button)findViewById(R.id.humidity_button);
        humidityButtonToActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),activity_humidity.class);
                startActivity(i);
            }
        });

        temperatureButtonToActivity=(Button)findViewById(R.id.temperature_button);
        temperatureButtonToActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),activity_temperature.class);
                startActivity(i);
            }
        });

        co2ButtonToActivity=(Button)findViewById(R.id.co2_button);
        co2ButtonToActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),activity_co2.class);
                startActivity(i);
            }
        });

        phButtonToActivity=(Button)findViewById(R.id.pH_button);
        phButtonToActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),activity_ph.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.action_share:
                Intent i = new Intent(getApplicationContext(),activity_share_data.class);
                startActivity(i);
                return true;

            case R.id.emergency_shutdown:
                Intent j = new Intent(getApplicationContext(),activity_emergency_shutdown.class);
                startActivity(j);
                return true;

            case R.id.exit_app:
                AppExit();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }

    public void AppExit()
    {

        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }
}
