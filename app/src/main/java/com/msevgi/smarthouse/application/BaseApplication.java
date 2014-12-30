package com.msevgi.smarthouse.application;

import android.app.Application;

import com.msevgi.smarthouse.provider.BusProvider;

abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BusProvider.getInstance().register(this);
    }
}
