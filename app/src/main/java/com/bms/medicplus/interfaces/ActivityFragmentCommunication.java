package com.bms.medicplus.interfaces;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public interface ActivityFragmentCommunication {
    public void communicate(Bundle bundle);

    public void transact(Fragment fragment);
}
