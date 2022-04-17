package com.chairnetwork.chairapp;

import androidx.annotation.NonNull;

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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }






}
