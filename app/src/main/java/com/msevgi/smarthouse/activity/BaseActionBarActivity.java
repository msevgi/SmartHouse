package com.msevgi.smarthouse.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.msevgi.smarthouse.provider.BusProvider;
import com.squareup.otto.Bus;

import butterknife.ButterKnife;


public abstract class BaseActionBarActivity extends ActionBarActivity {
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
        getBus().unregister(this);
    }


    protected Bus getBus() {
        return BusProvider.getInstance();
    }

    protected abstract int getLayoutResource();
}
