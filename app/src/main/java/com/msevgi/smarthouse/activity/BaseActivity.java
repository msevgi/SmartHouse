package com.msevgi.smarthouse.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;

import com.msevgi.smarthouse.provider.BusProvider;
import com.squareup.otto.Bus;

import butterknife.ButterKnife;

abstract class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        ButterKnife.inject(this);
        getBus().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.reset(this);
        getBus().unregister(this);
    }


    protected Bus getBus() {
        return BusProvider.getInstance();
    }

    protected abstract
    @NonNull
    @LayoutRes
    int getLayoutResource();
}
