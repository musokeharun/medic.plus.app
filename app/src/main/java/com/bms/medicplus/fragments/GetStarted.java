package com.bms.medicplus.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bms.medicplus.Constants;
import com.bms.medicplus.R;
import com.bms.medicplus.auth.Login;
import com.bms.medicplus.interfaces.ActivityFragmentCommunication;
import com.bms.medicplus.viewmodels.ActivityFragmentViewModel;
import com.bms.medicplus.auth.fragments.RegisterFormFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class GetStarted extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ActivityFragmentViewModel mViewModel;
    private String TAG = "GET_STARTED_TAG";
    private ActivityFragmentCommunication activityFragmentCommunication;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GetStarted.
     */
    // TODO: Rename and change types and number of parameters
    public static GetStarted newInstance(String param1, String param2) {
        GetStarted fragment = new GetStarted();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GetStarted() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_started, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button loginButton;
        Button registerButton;

        loginButton = view.findViewById(R.id.login);
        registerButton = view.findViewById(R.id.register);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Login.class);
            intent.putExtra(Constants.IS_NAV_ENABLED.name(), true);
            intent.putExtra(Constants.AM_STILL_HERE.name(), true);
            startActivity(intent);
        });

        registerButton.setOnClickListener(v -> {
            activityFragmentCommunication.transact(new RegisterFormFragment());
        });

    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(ActivityFragmentViewModel.class);
        // CAST ACTIVITY
        try {
            activityFragmentCommunication = (ActivityFragmentCommunication) requireActivity();
            Bundle bundle = new Bundle();
            bundle.putString(ActivityFragmentViewModel.APP_BAR_TITLE, "Get Started");
            bundle.putBoolean(ActivityFragmentViewModel.NAV_ENABLED, false);
            activityFragmentCommunication.communicate(bundle);
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Couldn't process further", Toast.LENGTH_SHORT).show();
        }

    }
}