package com.example.antonio.greenhouse_testapp1;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Switch;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class activity_share_data extends AppCompatActivity {

    private Button shareButton;
    private Switch allTimeSwitch;
    private Switch humiditySwitch;
    private Switch temperatureSwitch;
    private Switch co2Switch;
    private Switch phSwitch;
    private Switch lightingSwitch;
    private Switch graphsSwitch;
    private Switch tableSwitch;

    private Button startDateButton;
    private Button endDateButton;

    protected static TextView from_textView_date, to_textView_date, from_textView_time, to_textView_time;
    protected static int startDay = 0, startMonth = 0, startYear = 0;
    protected static int endDay = 0, endMonth = 0, endYear = 0;

    private LinearLayout showDatesLayout;
    protected static int dateSel = 0;
    protected static boolean startSet = false, endSet = false;
    protected static Context shareContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_data);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        allTimeSwitch = (Switch) findViewById(R.id.all_time);
        humiditySwitch = (Switch) findViewById(R.id.include_humidity);
        temperatureSwitch = (Switch) findViewById(R.id.include_temperature);
        co2Switch = (Switch) findViewById(R.id.include_CO2);
        phSwitch = (Switch) findViewById(R.id.include_pH);
        lightingSwitch = (Switch) findViewById(R.id.include_lighting);
        graphsSwitch = (Switch) findViewById(R.id.include_graphs);
        tableSwitch = (Switch) findViewById(R.id.include_tables);
        shareButton=(Button)findViewById(R.id.share_button);

        startDateButton = (Button) findViewById(R.id.startDateButton);
        endDateButton = (Button) findViewById(R.id.endDateButton);

        showDatesLayout = (LinearLayout) findViewById(R.id.time_set_user);
        from_textView_date = (TextView) findViewById(R.id.from_date);
        to_textView_date = (TextView) findViewById(R.id.to_date);
        from_textView_time = (TextView) findViewById(R.id.from_time);
        to_textView_time = (TextView) findViewById(R.id.to_time);

        activity_share_data.shareContext = getApplicationContext();

        actionPerformed();
    }

    private void actionPerformed()
    {
        shareButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (allTimeSwitch.isChecked() || humiditySwitch.isChecked() || temperatureSwitch.isChecked() || co2Switch.isChecked() || lightingSwitch.isChecked() || phSwitch.isChecked())
                {
                    if(graphsSwitch.isChecked() || tableSwitch.isChecked())
                    {
                        if (from_textView_date.getText().equals("") || to_textView_date.getText().equals("") || from_textView_time.getText().equals("") || to_textView_time.getText().equals(""))
                        {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.need_time),Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.share_successful),Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.need_data_format),Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.unable_to_share),Toast.LENGTH_LONG).show();
                }

            }
        });

        allTimeSwitch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (allTimeSwitch.isChecked()) {
                    humiditySwitch.setChecked(true);
                    temperatureSwitch.setChecked(true);
                    co2Switch.setChecked(true);
                    lightingSwitch.setChecked(true);
                    graphsSwitch.setChecked(true);
                    tableSwitch.setChecked(true);

                    // Set start and end time
                }
                else
                {
                    humiditySwitch.setChecked(false);
                    temperatureSwitch.setChecked(false);
                    co2Switch.setChecked(false);
                    lightingSwitch.setChecked(false);
                    graphsSwitch.setChecked(false);
                    tableSwitch.setChecked(false);

                    // Erase start and end time
                }

            }


        });

        startDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dateSel = 0;
                DatePickerFragment mDatePicker = new DatePickerFragment();
                mDatePicker.show(getFragmentManager(), "Select date");
                startSet = true;

                if(startSet && endSet)
                {
                    showDatesLayout.setVisibility(View.VISIBLE);
                }
            }


        });

        endDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dateSel = 1;
                DatePickerFragment mDatePicker = new DatePickerFragment();
                mDatePicker.show(getFragmentManager(), "Select date");
                endSet = true;

                if(startSet && endSet)
                {
                    showDatesLayout.setVisibility(View.VISIBLE);
                }
            }


        });


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {

        int year, month, day;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH) + 1;
            day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    this,year,month,day);

            dpd.getDatePicker().setMaxDate(c.getTimeInMillis());
            c.add(Calendar.DATE, -7);
            //dpd.getDatePicker().setMinDate(c.getTimeInMillis());

            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            boolean readyForTime = false;

            if (dateSel == 0)
            {
                if ((year > endYear && endYear != 0))
                {
                    // Year is higher than end Year
                    // Error
                    // Throw error message
                    Toast.makeText(shareContext,getResources().getString(R.string.bad_year),Toast.LENGTH_LONG).show();

                }
                else // Start year is ok
                {
                    // if end month has not been set
                    if (endMonth != 0)
                    {
                        if (year == endYear && month > endMonth)
                        {
                            // Error month selected is higher than end month on the same year
                            Toast.makeText(shareContext,getResources().getString(R.string.bad_month),Toast.LENGTH_LONG).show();
                        }
                        else // Start Month is ok
                        {
                            if (year == endYear && month == endMonth && day > endDay)
                            {
                                // Error day selected is higher than end day on the same month and year
                                Toast.makeText(shareContext,getResources().getString(R.string.bad_day),Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                // Date selceted is ok
                                startYear = year;
                                startMonth = month;
                                startDay = day;
                                from_textView_date.setText(String.valueOf(startMonth) + "/" + String.valueOf(startDay) + "/" + String.valueOf(startYear));
                                readyForTime = true;
                            }
                        }
                    }
                    else
                    {
                        // If end month is 0 then end date has not been set yet
                        startYear = year;
                        startMonth = month;
                        startDay = day;
                        from_textView_date.setText(String.valueOf(startMonth) + "/" + String.valueOf(startDay) + "/" + String.valueOf(startYear));
                        readyForTime = true;
                    }
                }
            }
            else if (dateSel == 1)
            {

                if ((year < startYear && startYear != 0))
                {
                    // Year is higher than end Year
                    // Error
                    // Throw error message
                    Toast.makeText(shareContext,getResources().getString(R.string.bad_year),Toast.LENGTH_LONG).show();
                }
                else // Start year is ok
                {
                    // if end month has not been set
                    if (startMonth != 0)
                    {
                        if (year == startYear && month < startMonth)
                        {
                            // Error month selected is higher than end month on the same year
                            Toast.makeText(shareContext,getResources().getString(R.string.bad_month),Toast.LENGTH_LONG).show();
                        }
                        else // Start Month is ok
                        {
                            if (year == startYear && month == startMonth && day < startDay)
                            {
                                // Error day selected is higher than end day on the same month and year
                                Toast.makeText(shareContext,getResources().getString(R.string.bad_day),Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                // Date selceted is ok
                                endYear = year;
                                endMonth = month;
                                endDay = day;
                                to_textView_date.setText(String.valueOf(endMonth) + "/" + String.valueOf(endDay) + "/" + String.valueOf(endYear));
                                readyForTime = true;

                            }
                        }
                    }
                    else
                    {
                        // If end month is 0 then end date has not been set yet
                        endYear = year;
                        endMonth = month;
                        endDay = day;
                        to_textView_date.setText(String.valueOf(endMonth) + "/" + String.valueOf(endDay) + "/" + String.valueOf(endYear));
                        readyForTime = true;

                    }
                }

            }

            if (readyForTime)
            {
                TimePicker mTimePicker = new TimePicker();
                mTimePicker.show(getFragmentManager(), "Select time");
            }

        }
    }

    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute)
        {

            if (dateSel == 0)
            {
                from_textView_time.setText(String.valueOf(hourOfDay) + " : " + String.valueOf(0) + String.valueOf(0));
            }
            else if (dateSel == 1)
            {
                to_textView_time.setText(String.valueOf(hourOfDay) + " : " + String.valueOf(0) + String.valueOf(0));
            }

        }
    }

}
