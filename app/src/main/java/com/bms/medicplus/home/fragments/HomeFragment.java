package com.bms.medicplus.home.fragments;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bms.medicplus.R;
import com.bms.medicplus.adapters.CategoriesAdapter;
import com.bms.medicplus.databinding.HomeFragmentBinding;
import com.bms.medicplus.home.interfaces.HomeActivityFragmentCommunication;
import com.bms.medicplus.home.viewmodel.HomeViewModel;
import com.bms.medicplus.interfaces.ActivityFragmentCommunication;
import com.bms.medicplus.viewmodels.ActivityFragmentViewModel;

import java.util.ArrayList;
import java.util.Objects;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class HomeFragment extends Fragment {

    private static final String TAG = "HOME_FRAGMENT";
    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;
    private HomeActivityFragmentCommunication parentActivityFragmentCommunication;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            parentActivityFragmentCommunication = (HomeActivityFragmentCommunication) requireActivity();
            mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
            // TODO: Use the ViewModel

            //SET CATEGORIES
            setCategories(binding.categories);


        } catch (ClassCastException e) {
            Toast.makeText(requireContext(), "Couldn't continue,please contact admin.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onActivityCreated: " + e.getLocalizedMessage());
            requireActivity().finish();
        }
    }

    private void setCategories(RecyclerView recyclerView) {
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(requireContext(), new ArrayList<>());
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        mViewModel.getCategories().observe(getViewLifecycleOwner(), categoriesAdapter::changed);
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putString(ActivityFragmentViewModel.APP_BAR_TITLE, "");
        parentActivityFragmentCommunication.communicate(bundle);
    }

    private Drawable createHomeActionDrawable() {
        return new BitmapDrawable();
    }
}