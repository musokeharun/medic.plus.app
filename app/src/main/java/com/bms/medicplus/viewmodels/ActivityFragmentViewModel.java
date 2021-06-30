package com.bms.medicplus.viewmodels;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActivityFragmentViewModel extends ViewModel {

    public static final String APP_BAR_TITLE = "APP_BAR_TITLE";
    public static final String NAV_ENABLED = "NAV_ENABLED";
    public static final String AM_LAST_FRAGMENT = "AM_LAST_FRAGMENT";
    public static final String EXTRA = "EXTRA";
    public static final String FRAGMENT_CLASS = "FRAGMENT_CLASS";

    private final MutableLiveData<Bundle> data = new MutableLiveData<>();

    public void setData(Bundle notice) {
        data.setValue(notice);
    }

    public MutableLiveData<Bundle> getData() {
        return data;
    }

}