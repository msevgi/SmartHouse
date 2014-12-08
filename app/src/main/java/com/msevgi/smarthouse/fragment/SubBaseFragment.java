package com.msevgi.smarthouse.fragment;


import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

abstract class SubBaseFragment extends BaseFragment {

    public abstract
    @NonNull
    @StringRes
    int getTitleResource();

}
