package com.bms.medicplus.home.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bms.medicplus.R;
import com.bms.medicplus.auth.Register;
import com.bms.medicplus.home.interfaces.HomeActivityFragmentCommunication;
import com.bms.medicplus.home.viewmodel.ProfileViewModel;
import com.bms.medicplus.interfaces.ActivityFragmentCommunication;
import com.bms.medicplus.viewmodels.ActivityFragmentViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private HomeActivityFragmentCommunication homeActivityFragmentCommunication;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        homeActivityFragmentCommunication = (HomeActivityFragmentCommunication) requireActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putString(ActivityFragmentViewModel.APP_BAR_TITLE, "Profile");
        homeActivityFragmentCommunication.communicate(bundle);
    }
}