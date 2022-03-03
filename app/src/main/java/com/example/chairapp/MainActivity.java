package com.example.chairapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.chairapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "";
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    Button startTimeButton;
    Button endTimeButton;
    int start_hour, start_minute,end_hour, end_minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Weight_data").child("1-setFloat");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(Integer.class);
                Log.d(TAG, "\n\n\n\nValue is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "\n\n\n\nFailed to read value.", error.toException());
            }
        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        startTimeButton = findViewById(R.id.button_startTime);
        endTimeButton = findViewById(R.id.button_endTime);

        /*startTimeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TimePickerDialog startTimePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                             @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
                                start_hour = hourOfDay;
                                start_minute = minuteOfDay;
                                Calendar calendar = Calendar.getInstance();

                                calendar.set
                                startTimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", start_hour, start_minute));

                    }
                }
                )

        });*/
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

        // setting style to digital on the time picker
        int style = AlertDialog.THEME_HOLO_DARK;

        // time select for the start time on reserve page
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener,start_hour, start_minute,false);
        // time select for the end time on reserve page
        TimePickerDialog endTimePickerDialog = new TimePickerDialog(this, style, onTimeSetListener,end_hour,end_minute,false);

        endTimePickerDialog.setTitle("Select End Time");
        endTimePickerDialog.show();

        timePickerDialog.setTitle("Select Start Time");
        timePickerDialog.show();
    }
}