package com.bms.medicplus.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bms.medicplus.R;
import com.bms.medicplus.interfaces.ActivityFragmentCommunication;
import com.bms.medicplus.viewmodels.ActivityFragmentViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;

public class Register extends AppCompatActivity implements ActivityFragmentCommunication {

    private ActivityFragmentViewModel viewModel;
    private final String TAG = "REGISTER_LOG_TAG";
    private AppBarLayout appBarLayout;
    private MaterialToolbar topAppBar;
    private ProgressBar progressBar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        progressBar = findViewById(R.id.progress);
        topAppBar = findViewById(R.id.topAppBar);
        appBarLayout = findViewById(R.id.topAppBarLayout);

        fragmentManager = getSupportFragmentManager();
        viewModel = new ViewModelProvider(this).get(ActivityFragmentViewModel.class);
        getSupportFragmentManager().addOnBackStackChangedListener(() -> Toast.makeText(this, "Stack Changed", Toast.LENGTH_SHORT).show());
    }

    private void customiseToolBar(String app_title, Boolean is_nav_enabled) {
        topAppBar.setTitle(app_title);
        if (is_nav_enabled) {
            if (topAppBar.getNavigationIcon() == null) {
                topAppBar.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.back, getTheme()));
                topAppBar.setNavigationOnClickListener(v -> handleBackNavigation());
            }
        } else {
            topAppBar.setNavigationIcon(null);
        }
    }

    private void handleBackNavigation() {
        onBackPressed();
    }

    private void setProgressBarVisibility(int mode) {
        progressBar.setVisibility(mode);
    }

    @Override
    public void communicate(Bundle bundle) {
        if (bundle != null) {
            setProgressBarVisibility(View.VISIBLE);
            String app_title = bundle.getString(ActivityFragmentViewModel.APP_BAR_TITLE, "");
            Boolean is_nav_enabled = bundle.getBoolean(ActivityFragmentViewModel.NAV_ENABLED, false);
            customiseToolBar(app_title, is_nav_enabled);
            setProgressBarVisibility(View.GONE);
        }
    }

    @Override
    public void transact(Fragment fragment) {
        setProgressBarVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .setCustomAnimations(
                        R.anim.slide_in,
                        R.animator.fade_out,
                        R.anim.slide_left,
                        R.animator.fade_out
                ).replace(R.id.registerFragmentContainer, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
        setProgressBarVisibility(View.GONE);

    }
}