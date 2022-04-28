package com.chairnetwork.chairapp;

import androidx.annotation.NonNull;

import android.content.Context;
import android.os.Bundle;

import com.chairnetwork.chairapp.databinding.FragmentFirstBinding;
import com.chairnetwork.chairapp.databinding.ReserveBinding;

import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import android.view.ViewGroup;
//import com.example.chairapp.databinding.FragmentReserveBinding;

public class Reserve extends Fragment {

    private ReserveBinding binding;
    private ReserveListener listener;

    public interface ReserveListener {
        void afterButtonPressed(String chair);
    }

    public void whichChair(String chairNum){
        System.err.println(chairNum);
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

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Reserve.this)
                        .navigate(R.id.action_Reserve_to_SecondFragment);
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
