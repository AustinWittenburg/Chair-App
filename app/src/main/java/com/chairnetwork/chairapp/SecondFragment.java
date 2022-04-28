package com.chairnetwork.chairapp;

import android.content.Context;
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

    private SecondFragmentListener listener;
    private FragmentSecondBinding binding;
    private DatabaseReference myRef;
    private DatabaseReference myRef2;
    private DatabaseReference myRef3;
    private DatabaseReference myRef4;
    private DatabaseReference myRef5;
    private ValueEventListener listener1;
    private ValueEventListener listener2;
    private ValueEventListener listener3;
    private ValueEventListener listener4;
    private ValueEventListener listener5;

    public interface SecondFragmentListener {
        void onButtonPressed(String chair);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Weight_data").child("1");
        myRef2 = database.getReference("Weight_data").child("2");
        myRef3 = database.getReference("Weight_data").child("3");
        myRef4 = database.getReference("Weight_data").child("4");
        myRef5 = database.getReference("Weight_data").child("5");

        //myRef.setValue(1511);

        // Read from the database
        listener1 = new ValueEventListener() {
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
        };
        myRef.addValueEventListener(listener1);

        // Read from the database
        listener2 = new ValueEventListener() {
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
        };
        myRef2.addValueEventListener(listener2);

        // Read from the database
        listener3 = new ValueEventListener() {
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
                    Button chair1 = getView().findViewById(R.id.button5);
                    chair1.setTextColor(getResources().getColor(R.color.hawkeye_gold));
                    chair1.setBackgroundColor(getResources().getColor(R.color.black));
                }
                else{
                    Button chair1 = getView().findViewById(R.id.button5);
                    chair1.setTextColor(getResources().getColor(R.color.black));
                    chair1.setBackgroundColor(getResources().getColor(R.color.hawkeye_gold));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.print("error");
            }
        };
        myRef3.addValueEventListener(listener3);

        // Read from the database
        listener4 = new ValueEventListener() {
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
                    Button chair1 = getView().findViewById(R.id.button6);
                    chair1.setTextColor(getResources().getColor(R.color.hawkeye_gold));
                    chair1.setBackgroundColor(getResources().getColor(R.color.black));
                }
                else{
                    Button chair1 = getView().findViewById(R.id.button6);
                    chair1.setTextColor(getResources().getColor(R.color.black));
                    chair1.setBackgroundColor(getResources().getColor(R.color.hawkeye_gold));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.print("error");
            }
        };
        myRef4.addValueEventListener(listener4);

        // Read from the database
        listener5 = new ValueEventListener() {
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
                    Button chair1 = getView().findViewById(R.id.button7);
                    chair1.setTextColor(getResources().getColor(R.color.hawkeye_gold));
                    chair1.setBackgroundColor(getResources().getColor(R.color.black));
                }
                else{
                    Button chair1 = getView().findViewById(R.id.button7);
                    chair1.setTextColor(getResources().getColor(R.color.black));
                    chair1.setBackgroundColor(getResources().getColor(R.color.hawkeye_gold));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.print("error");
            }
        };
        myRef5.addValueEventListener(listener5);

        
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
                listener.onButtonPressed("Chair 3");
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Reserve);
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonPressed("Chair 5");
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Reserve);
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonPressed("Chair 4");
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Reserve);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonPressed("Chair 1");
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Reserve);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonPressed("Chair 2");
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Reserve);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SecondFragmentListener) {
            listener = (SecondFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SecondFragmentListener");
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
        System.err.print("destroying!");
        myRef.removeEventListener(listener1);
        myRef2.removeEventListener(listener2);
        myRef3.removeEventListener(listener3);
        myRef4.removeEventListener(listener4);
        myRef5.removeEventListener(listener5);
        myRef = null;
        myRef2 = null;
        myRef3 = null;
        myRef4 = null;
        myRef5 = null;
    }

}