package com.chairnetwork.chairapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.chairnetwork.chairapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SecondFragment.SecondFragmentListener, Reserve.ReserveListener{

    private SecondFragment secondFragment;
    private Reserve reserve;

    private static final String TAG = "";
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public String chairNum;

    Button startTimeButton;
    Button endTimeButton;
    int start_hour, start_minute,end_hour, end_minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reserve = new Reserve();

        //setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //setContentView(R.layout.fragment_second);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Weight_data").child("1-setFloat"); //"Weight_data").child("1-setFloat");

        //myRef.setValue(1511);

        // Read from the database


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This button will change later", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onButtonPressed(String chair) {
        chairNum = chair;
    }

    @Override
    public void afterButtonPressed(String chair) {
        System.err.print(chair + "!!!");
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

// setting the time using the popTimePicker which will set a start and end time for on the reserve page
    public void popTimePicker(View view){
        startTimeButton = findViewById(R.id.button_startTime);
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
            start_hour = selectedHour;
            start_minute= selectedMinute;
            startTimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", start_hour, start_minute));
        };

        // setting style to digital on the time picker
        int style = AlertDialog.THEME_HOLO_DARK;

        // time select for the start time on reserve page
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener,start_hour, start_minute,false);
        // time select for the end time on reserve page
        TimePickerDialog endTimePickerDialog = new TimePickerDialog(this, style, onTimeSetListener,end_hour,end_minute,false);


        timePickerDialog.setTitle("Select Start Time");
        timePickerDialog.show();



    }

    // setting the time using the popTimePicker which will set a start and end time for on the reserve page
    public void popTimePicker2(View view){
        endTimeButton = findViewById(R.id.button_endTime);
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {

            end_hour = selectedHour;
            end_minute = selectedMinute;
            endTimeButton.setText(String.format(Locale.getDefault(),"%02d:%02d", end_hour, end_minute));
        };

        // setting style to digital on the time picker
        int style = AlertDialog.THEME_HOLO_DARK;

        // time select for the start time on reserve page
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener,start_hour, start_minute,false);
        // time select for the end time on reserve page
        TimePickerDialog endTimePickerDialog = new TimePickerDialog(this, style, onTimeSetListener,end_hour,end_minute,false);

        endTimePickerDialog.setTitle("Select End Time");
        endTimePickerDialog.show();

    }
}