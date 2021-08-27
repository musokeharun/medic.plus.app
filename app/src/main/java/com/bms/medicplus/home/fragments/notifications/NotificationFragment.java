package com.bms.medicplus.home.fragments.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bms.medicplus.databinding.NotificationFragmentBinding;
import com.bms.medicplus.home.interfaces.HomeActivityFragmentCommunication;
import com.bms.medicplus.home.viewmodel.NotificationViewModel;
import com.bms.medicplus.viewmodels.ActivityFragmentViewModel;

import org.jetbrains.annotations.NotNull;

public class NotificationFragment extends Fragment {

    private NotificationViewModel mViewModel;
    private HomeActivityFragmentCommunication homeActivityFragmentCommunication;
    private NotificationFragmentBinding binding;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = NotificationFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        homeActivityFragmentCommunication = (HomeActivityFragmentCommunication) requireActivity();

    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putString(ActivityFragmentViewModel.APP_BAR_TITLE, "Notifications Center");
        homeActivityFragmentCommunication.communicate(bundle);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}