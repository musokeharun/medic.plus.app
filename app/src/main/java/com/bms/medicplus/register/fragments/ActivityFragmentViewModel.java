package com.bms.medicplus.register.fragments;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActivityFragmentViewModel extends ViewModel {

    public static final String APP_BAR_TITLE = "APP_BAR_TITLE";
    public static final String NAV_ENABLED = "NAV_ENABLED";
    public static final String AM_LAST_FRAGMENT = "AM_LAST_FRAGMENT";
    public static final String EXTRA = "EXTRA";
    public static final String FRAGMENT_CLASS = "FRAGMENT_CLASS";

    private final MutableLiveData<Bundle> fragmentNotice = new MutableLiveData<Bundle>();

    public LiveData<Bundle> fragmentNotice() {
        return fragmentNotice;
    }
}