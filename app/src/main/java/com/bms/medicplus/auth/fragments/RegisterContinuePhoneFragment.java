package com.bms.medicplus.auth.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bms.medicplus.R;
import com.bms.medicplus.databinding.FragmentRegisterContinuePhoneBinding;
import com.bms.medicplus.interfaces.ActivityFragmentCommunication;
import com.bms.medicplus.viewmodels.ActivityFragmentViewModel;

import org.jetbrains.annotations.NotNull;

public class RegisterContinuePhoneFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ConfirmUserModalFragment confirmUserModalFragment;
    private FragmentRegisterContinuePhoneBinding binding;

    public RegisterContinuePhoneFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegisterContinuePhoneFragment newInstance(String param1, String param2) {
        RegisterContinuePhoneFragment fragment = new RegisterContinuePhoneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterContinuePhoneBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmUserModalFragment = new ConfirmUserModalFragment();

        binding.continueButton.setOnClickListener(v -> {
            confirmUserModalFragment.show(getParentFragmentManager(), confirmUserModalFragment.getTag());
        });
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActivityFragmentViewModel mViewModel = new ViewModelProvider(requireActivity()).get(ActivityFragmentViewModel.class);
        // CAST ACTIVITY
        try {
            ActivityFragmentCommunication activityFragmentCommunication = (ActivityFragmentCommunication) requireActivity();
            Bundle bundle = new Bundle();
            bundle.putString(ActivityFragmentViewModel.APP_BAR_TITLE, "Complete");
            bundle.putBoolean(ActivityFragmentViewModel.NAV_ENABLED, true);
            activityFragmentCommunication.communicate(bundle);
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Couldn't process further", Toast.LENGTH_SHORT).show();
        }

    }
}