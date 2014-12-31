package com.msevgi.smarthouse.application;

import android.app.Application;
import android.content.Context;

import com.msevgi.smarthouse.provider.BusProvider;

abstract class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        BusProvider.getInstance().register(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
