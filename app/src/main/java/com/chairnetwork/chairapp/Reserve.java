package com.chairnetwork.chairapp;

import androidx.annotation.NonNull;

import android.content.Context;
import android.os.Bundle;

import com.chairnetwork.chairapp.databinding.FragmentFirstBinding;
import com.chairnetwork.chairapp.databinding.ReserveBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                MainActivity main = (MainActivity) getActivity();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                System.err.println("CHAIR NUM IS::: " + chairNumber);
                myRef = database.getReference("reservations").child(main.chairNum).child("1");
                System.err.println(myRef.orderByValue());
                Button startTime = getView().findViewById(R.id.button_startTime);
                Button endTime = getView().findViewById(R.id.button_endTime);
                CharSequence startNum = startTime.getText();
                CharSequence endNum = endTime.getText();
                String start = "" + startNum.charAt(0) + startNum.charAt(1) + startNum.charAt(3) + startNum.charAt(4);
                String end = "" + endNum.charAt(0) + endNum.charAt(1) + endNum.charAt(3) + endNum.charAt(4);

                int startInt = Integer.parseInt(start);
                int endInt = Integer.parseInt(end);
                if(startInt < endInt){
                    if(endInt-startInt < 400){
                        String store = start + "-" + end;
                        System.err.println(store);
                        myRef.setValue(store);
                    }
                }
                TextView view2 = ((TextView)getView().findViewById(R.id.textView)); //
                view2.setText(String.valueOf(view2.getText()+ "\n"+startNum.charAt(1)+startNum.charAt(2)+startNum.charAt(3)+startNum.charAt(4)+"AM-"+endNum.charAt(1)+endNum.charAt(2)+endNum.charAt(3)+endNum.charAt(4) + "AM"));
                System.err.println(startInt);
                System.err.println(endInt);
                System.err.println(startTime.getText());
                System.err.println(endTime.getText());
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
