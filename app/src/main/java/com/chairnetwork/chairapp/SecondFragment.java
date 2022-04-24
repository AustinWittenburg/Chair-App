package com.chairnetwork.chairapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.chairnetwork.chairapp.databinding.FragmentSecondBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
//        View view =  inflater.inflate(R.layout.fragment_second, container, false);
//        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Weight_data").child("1-setFloat"); //"Weight_data").child("1-setFloat");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                Integer value = dataSnapshot.getValue(Integer.class);
//                System.err.println("Database Value:");
//                System.err.println(value);
//                System.err.println("End Database Value");
//                ((TextView)view.findViewById(R.id.db_output)).setText(String.valueOf(value));
//
//                if(value > 1000){
//                    Button chair1 = view.findViewById(R.id.button2);
//                    chair1.setTextColor(getResources().getColor(R.color.hawkeye_gold));
//                    chair1.setBackgroundColor(getResources().getColor(R.color.black));
//                }
//                else{
//                    Button chair1 = view.findViewById(R.id.button2);
//                    chair1.setTextColor(getResources().getColor(R.color.black));
//                    chair1.setBackgroundColor(getResources().getColor(R.color.hawkeye_gold));
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                System.out.print("error");
//            }
//        });
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Weight_data").child("1-setFloat");
        DatabaseReference myRef2 = database.getReference("Weight_data").child("2-setFloat");

        //myRef.setValue(1511);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value = dataSnapshot.getValue(Integer.class);
                System.err.println("Database Value:");
                System.err.println(value);
                System.err.println("End Database Value");
                ((TextView)getView().findViewById(R.id.db_output)).setText(String.valueOf(value));

                if(value > 1000){
                    Button chair1 = getView().findViewById(R.id.button2);
                    chair1.setTextColor(getResources().getColor(R.color.hawkeye_gold));
                    chair1.setBackgroundColor(getResources().getColor(R.color.black));
                }
                else{
                    Button chair1 = getView().findViewById(R.id.button2);
                    chair1.setTextColor(getResources().getColor(R.color.black));
                    chair1.setBackgroundColor(getResources().getColor(R.color.hawkeye_gold));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.print("error");
            }
        });

        // Read from the database
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value = dataSnapshot.getValue(Integer.class);
                System.err.println("Database Value:");
                System.err.println(value);
                System.err.println("End Database Value");
                ((TextView)getView().findViewById(R.id.db_output)).setText(String.valueOf(value));

                if(value > 1000){
                    Button chair1 = getView().findViewById(R.id.button4);
                    chair1.setTextColor(getResources().getColor(R.color.hawkeye_gold));
                    chair1.setBackgroundColor(getResources().getColor(R.color.black));
                }
                else{
                    Button chair1 = getView().findViewById(R.id.button4);
                    chair1.setTextColor(getResources().getColor(R.color.black));
                    chair1.setBackgroundColor(getResources().getColor(R.color.hawkeye_gold));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.print("error");
            }
        });

        
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Reserve);
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Reserve);
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Reserve);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Reserve);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Reserve);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}