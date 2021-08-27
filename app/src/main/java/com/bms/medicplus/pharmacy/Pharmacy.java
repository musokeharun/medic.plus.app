package com.bms.medicplus.pharmacy;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bms.medicplus.R;
import com.bms.medicplus.databinding.ActivityPharmacyBinding;

public class Pharmacy extends AppCompatActivity {

    private ActivityPharmacyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPharmacyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        View navUp = binding.getRoot().findViewById(R.id.nav_or_up);
        navUp.setOnClickListener(v -> finish());
    }
}