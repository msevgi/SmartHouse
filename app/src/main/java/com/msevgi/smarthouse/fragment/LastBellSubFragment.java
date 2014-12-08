package com.msevgi.smarthouse.fragment;

import android.support.annotation.NonNull;

import com.msevgi.smarthouse.R;

public final class LastBellSubFragment extends SubBaseFragment {

    public static final int SUB_POSITION = 1;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.sub_fragment_last_bell;
    }

    @NonNull
    @Override
    public int getTitleResource() {
        return R.string.title_last_bell;
    }
}
