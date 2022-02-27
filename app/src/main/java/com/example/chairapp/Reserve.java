package com.example.chairapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Locale;

public class Reserve extends AppCompatActivity {
    Button startTimeButton;
    Button endTimeButton;
    int start_hour, start_minute,end_hour, end_minute;


    //@Override
    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve);
        startTimeButton = findViewById(R.id.button);
        endTimeButton = findViewById(R.id.button_endTime);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void popTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                start_hour = selectedHour;
                start_minute= selectedMinute;
                end_hour = selectedHour;
                end_minute = selectedMinute;
                startTimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", start_hour, start_minute));
                endTimeButton.setText(String.format(Locale.getDefault(),"%02d:%02d", end_hour, end_minute));
            }
        };



        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener,start_hour, start_minute,false);
        TimePickerDialog endTimePickerDialog = new TimePickerDialog(this, onTimeSetListener,end_hour,end_minute,false);
        endTimePickerDialog.setTitle("Select End Time");
        endTimePickerDialog.show();
        timePickerDialog.setTitle("Select Start Time");
        timePickerDialog.show();
    }

}
