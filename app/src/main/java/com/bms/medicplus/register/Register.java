package com.bms.medicplus.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bms.medicplus.R;
import com.bms.medicplus.register.fragments.ActivityFragmentViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class Register extends AppCompatActivity {

    private ActivityFragmentViewModel viewModel;
    private final String TAG = "REGISTER_LOG_TAG";
    private MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }

        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setTitle("Get Started");

        viewModel = new ViewModelProvider(this).get(ActivityFragmentViewModel.class);
        viewModel.fragmentNotice().observe(this, item -> {
            if (item != null) {
                String app_title = item.getString(ActivityFragmentViewModel.APP_BAR_TITLE, "");
                Boolean is_nav_enabled = item.getBoolean(ActivityFragmentViewModel.NAV_ENABLED, false);

                if (app_title.isEmpty()) {
                    topAppBar.setTitle("Get Started");
                }

            }
        });

        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, UserType.newInstance())
//                    .commitNow();
        }
    }
}