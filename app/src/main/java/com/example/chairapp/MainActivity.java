package com.example.chairapp;

import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.chairapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    Button startTimeButton;
    Button endTimeButton;
    int start_hour, start_minute,end_hour, end_minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        startTimeButton = findViewById(R.id.button);
        endTimeButton = findViewById(R.id.button_endTime);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This button will change later", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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