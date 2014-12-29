package com.msevgi.smarthouse.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;

import com.github.machinarius.preferencefragment.PreferenceFragment;
import com.msevgi.smarthouse.R;

public class SettingsFragment extends PreferenceFragment {
    public static final int POSITION = 2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }

}
