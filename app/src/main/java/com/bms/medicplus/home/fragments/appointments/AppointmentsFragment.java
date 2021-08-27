package com.bms.medicplus.home.fragments.appointments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bms.medicplus.databinding.AppointmentsFragmentBinding;
import com.bms.medicplus.home.interfaces.HomeActivityFragmentCommunication;
import com.bms.medicplus.home.viewmodel.AppointmentsViewModel;
import com.bms.medicplus.viewmodels.ActivityFragmentViewModel;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

public class AppointmentsFragment extends Fragment {

    private AppointmentsViewModel mViewModel;
    private AppointmentsFragmentBinding binding;
    private AppointmentsStateAdapter appointmentsStateAdapter;
    private HomeActivityFragmentCommunication homeActivityFragmentCommunication;

    public static AppointmentsFragment newInstance() {
        return new AppointmentsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AppointmentsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AppointmentsViewModel.class);
        // TODO: Use the ViewModel
        homeActivityFragmentCommunication = (HomeActivityFragmentCommunication) requireActivity();

    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putString(ActivityFragmentViewModel.APP_BAR_TITLE, "My Appointments");
        homeActivityFragmentCommunication.communicate(bundle);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appointmentsStateAdapter = new AppointmentsStateAdapter(this);
        binding.pager.setAdapter(appointmentsStateAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.pager,
                (tab, position) -> tab.setText(position == 0 ? "Pending" : "Completed")
        ).attach();
    }

    private static class AppointmentsStateAdapter extends FragmentStateAdapter {
        public AppointmentsStateAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = new AppointmentListFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

}