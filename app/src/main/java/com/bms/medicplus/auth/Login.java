package com.bms.medicplus.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bms.medicplus.Constants;
import com.bms.medicplus.R;
import com.bms.medicplus.databinding.ActivityLoginBinding;
import com.bms.medicplus.home.Home;
import com.google.android.material.appbar.MaterialToolbar;

public class Login extends AppCompatActivity {

    MaterialToolbar toolbar;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        toolbar = findViewById(R.id.topAppBar);
        boolean isNav = getIntent().getBooleanExtra(Constants.IS_NAV_ENABLED.name(), false);
        boolean isStill = getIntent().getBooleanExtra(Constants.AM_STILL_HERE.name(), false);
        toolbar.setTitle(getResources().getString(R.string.login));
        if (isNav) {
            toolbar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.back, getTheme()));
            toolbar.setNavigationOnClickListener(v -> {
                finish();
            });
        }
        binding.register.setOnClickListener(v -> {
            if (!isStill) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
            finish();
        });
        binding.loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        });

    }
}