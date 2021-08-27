package com.bms.medicplus.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bms.medicplus.R;
import com.bms.medicplus.databinding.ActivityHomeBinding;
import com.bms.medicplus.home.fragments.appointments.AppointmentsFragment;
import com.bms.medicplus.home.fragments.HomeFragment;
import com.bms.medicplus.home.fragments.notifications.NotificationFragment;
import com.bms.medicplus.home.fragments.ProfileFragment;
import com.bms.medicplus.home.interfaces.HomeActivityFragmentCommunication;
import com.bms.medicplus.pharmacy.Pharmacy;
import com.bms.medicplus.viewmodels.ActivityFragmentViewModel;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeActivityFragmentCommunication {

    private ActivityHomeBinding binding;
    private static final float END_SCALE = 0.7f;
    private static final int NUM_PAGES = 4;
    private FragmentStateAdapter pagerAdapter;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.bottomNavView.setBackgroundColor(ResourcesCompat.getColor(getResources(), android.R.color.transparent, getTheme()));
        binding.bottomNavView.getMenu().getItem(2).setEnabled(false);
        binding.bottomNavView.getOrCreateBadge(R.id.bottom_alert).setNumber(1);
        setSupportActionBar(binding.homeToolbar);

        pagerAdapter = new HomePagerAdapter(this);
        binding.homePager.setUserInputEnabled(false);
        binding.homePager.setAdapter(pagerAdapter);
        // LISTEN PAGER
        binding.bottomNavView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == binding.bottomNavView.getSelectedItemId()) return false;
            int index;
            switch (item.getItemId()) {

                case R.id.bottom_appoint:
                    index = 1;
                    break;
                case R.id.bottom_alert:
                    index = 2;
                    break;
                case R.id.bottom_profile:
                    index = 3;
                    break;
                default:
                    index = 0;
            }
            binding.homePager.setCurrentItem(index, true);
            return true;
        });
        binding.bottomNavView.setOnNavigationItemReselectedListener(item -> {
            // TODO U CAN SEND REFRESH
            Toast.makeText(this, "Stop Reselecting", Toast.LENGTH_SHORT).show();
        });

        binding.homePager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position > 1) {
                    binding.bottomNavView.setSelectedItemId(binding.bottomNavView.getMenu().getItem(position + 1).getItemId());
                } else {
                    binding.bottomNavView.setSelectedItemId(binding.bottomNavView.getMenu().getItem(position).getItemId());
                }

                if (position > 0) {
                    binding.homeToolbar.setNavigationIcon(R.drawable.left);
                } else {
                    binding.homeToolbar.setNavigationIcon(R.drawable.ham);
                }


            }
        });
        activateDrawer();

        binding.homeToolbar.setNavigationOnClickListener(v -> {
            if (binding.homePager.getCurrentItem() > 0) {
                onBackPressed();
            } else {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        binding.goPharmacyOrAppoint.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Pharmacy.class);
            startActivity(intent);
        });

    }

    private void activateDrawer() {
        binding.drawerView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.homeToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.drawerView.setNavigationItemSelectedListener(this);
        binding.drawerView.setCheckedItem(R.id.nav_home);
        binding.drawerLayout.setScrimColor(ResourcesCompat.getColor(getResources(), R.color.light, getTheme()));
        binding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                labelView.setVisibility(slideOffset > 0 ? View.VISIBLE : View.GONE);
               // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                binding.homeContent.setScaleX(offsetScale);
                binding.homeContent.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = binding.homeContent.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                binding.homeContent.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (binding.homePager.getCurrentItem() == 0) {
                super.onBackPressed();
            } else {
                binding.homePager.setCurrentItem(0);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void communicate(Bundle bundle) {
        String title = bundle.getString(ActivityFragmentViewModel.APP_BAR_TITLE, "");
        binding.actionBarTitle.setText(getString(R.string.app_name));
        binding.actionBarTitle.setText(title);
    }

    @Override
    public void transact(Fragment fragment) {

    }

    @Override
    public void addActionDrawable(Drawable drawable) {

    }

    private static class HomePagerAdapter extends FragmentStateAdapter {
        public HomePagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NotNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new AppointmentsFragment();
                case 2:
                    return new NotificationFragment();
                default:
                    return new ProfileFragment();
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}