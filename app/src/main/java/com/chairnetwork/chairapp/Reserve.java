package com.chairnetwork.chairapp;

import java.time.LocalDateTime;

import androidx.annotation.NonNull;

import android.content.Context;
import android.os.Bundle;

import com.chairnetwork.chairapp.databinding.FragmentFirstBinding;
import com.chairnetwork.chairapp.databinding.ReserveBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
//import com.example.chairapp.databinding.FragmentReserveBinding;

public class Reserve extends Fragment {

    private ReserveBinding binding;
    private ReserveListener listener;
    private DatabaseReference myRef;
    private String chairNumber;

    public interface ReserveListener {
        void afterButtonPressed(String chair);
    }

    public void whichChair(String chairNum){

        chairNumber = chairNum;
        System.err.println("CHANGING CHAIR NUM TO " + chairNumber);
    }

    public static String convert24HourToAmPm(String time) {

        if (time == null)
            return time;

        // Convert time where time is like: 0100, 0200, 0300....2300...
        if (time.length() == 4) {
            String hour = time.substring(0,2);
            String minutes = time.substring(2,4);
            String meridian = "am";

            if (hour.substring(0,2).equals("00")) {
                hour = "12";
            } else if (hour.substring(0,1).equals("1") || hour.substring(0,1).equals("2")) {
                meridian = "pm";
                Integer militaryHour = Integer.parseInt(hour);
                Integer convertedHour = null;

                if (militaryHour > 12) {
                    convertedHour = (militaryHour - 12);

                    if (convertedHour < 10)
                        hour = "0" + String.valueOf(convertedHour);
                    else
                        hour = String.valueOf(convertedHour);
                }
            }

            time = hour + ":" + minutes + " " + meridian;
        }

        if(time.charAt(0) == '0'){
            time = time.substring(1);
        }

        return time;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = ReserveBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity main = (MainActivity) getActivity();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        System.err.println("CHAIR NUM IS::: " + main.chairNum);
        myRef = database.getReference("reservations").child(main.chairNum).child("2");

        ValueEventListener valueEventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView view2 = ((TextView)getView().findViewById(R.id.textView));
                String reservations = dataSnapshot.getValue(String.class);
                String[] reservationList = reservations.split(",");
                for(String timeSlot : reservationList) {
                    if(timeSlot.length() > 0) {
                        String[] startAndEndTime= timeSlot.split("-");
                        String startString = startAndEndTime[0];
                        String endString = startAndEndTime[1];
                        view2.setText(String.valueOf(view2.getText() + convert24HourToAmPm(startString) + " - " + convert24HourToAmPm(endString) +  "\n"));
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.print("error");
            }
        };
        myRef.addListenerForSingleValueEvent(valueEventListener2);


        binding.buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Reserve.this)
                        .navigate(R.id.action_Reserve_to_SecondFragment);
            }
        });

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Button startTime = getView().findViewById(R.id.button_startTime);
                Button endTime = getView().findViewById(R.id.button_endTime);
                CharSequence startNum = startTime.getText();
                CharSequence endNum = endTime.getText();
                String start = "" + startNum.charAt(0) + startNum.charAt(1) + startNum.charAt(3) + startNum.charAt(4);
                String end = "" + endNum.charAt(0) + endNum.charAt(1) + endNum.charAt(3) + endNum.charAt(4);

                if(!startNum.toString().equals("Select Start Time") && !endNum.toString().equals("Select End Time")){
                    int startInt = Integer.parseInt(start);
                    int endInt = Integer.parseInt(end);


                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String reservations = dataSnapshot.getValue(String.class);
                            System.out.println("RESERVATIONS" + reservations);

                            Boolean isConflicting = false;

                            String[] reservationList = reservations.split(",");
                            for(String timeSlot : reservationList){
                                if(timeSlot.length() > 0){
                                    String[] startAndEndTime= timeSlot.split("-");
                                    String startString = startAndEndTime[0];
                                    String endString = startAndEndTime[1];
                                    int startInt2 = Integer.parseInt(startString);
                                    int endInt2 = Integer.parseInt(endString);
                                    if(startInt >= startInt2 && startInt < endInt2){
                                        System.err.println("OVERLAP with" + startString + "-" + endString);
                                        isConflicting = true;
                                    }
                                    else if(endInt > startInt2 && endInt <= endInt2){
                                        System.err.println("OVERLAP with" + startString + "-" + endString);
                                        isConflicting = true;
                                    }
                                }
                            }
                            TextView view2 = ((TextView)getView().findViewById(R.id.textView));
                            if(startInt < endInt && !isConflicting){
                                if(endInt-startInt < 400){
                                    System.err.println("setting db");
                                    String store = start + "-" + end;
//                        System.err.println(store);
                                    myRef.setValue(reservations + "," + store);

                                    view2.setText(String.valueOf(view2.getText() + convert24HourToAmPm(start) + " - " + convert24HourToAmPm(end) +  "\n"));


                                }
                            }
                            else{
                                view2.setText(String.valueOf(view2.getText()+ "Conflict" + "\n"));
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            System.out.print("error");
                        }
                    };
                    myRef.addListenerForSingleValueEvent(valueEventListener);
                }



            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ReserveListener) {
            listener = (ReserveListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ReserveListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
