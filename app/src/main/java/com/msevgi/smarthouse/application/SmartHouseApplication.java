package com.msevgi.smarthouse.application;

import android.app.Application;
import android.os.StrictMode;

public final class SmartHouseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.ThreadPolicy mPolicy = new StrictMode.ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(mPolicy);
    }
}
