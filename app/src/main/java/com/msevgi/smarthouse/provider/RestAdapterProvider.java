package com.msevgi.smarthouse.provider;

import android.content.Context;

import com.msevgi.smarthouse.application.SmartHouseApplication;
import com.msevgi.smarthouse.constant.ApplicationConstants;

import retrofit.RestAdapter;

public final class RestAdapterProvider {
    private static RestAdapter sAdapter;

    public static RestAdapter getInstance() {
        if (sAdapter == null) {
            Context context = SmartHouseApplication.getContext();
            String ipAddress = ConfiguratorProvider.getInstance(context).IpAddress().getOr(ApplicationConstants.API_URL);

            sAdapter = new RestAdapter
                    .Builder()
                    .setEndpoint(ipAddress)
                    .build();
        }

        return sAdapter;
    }

    public static void init() {
        Context context = SmartHouseApplication.getContext();
        String ipAddress = ConfiguratorProvider.getInstance(context).IpAddress().getOr(ApplicationConstants.API_URL);

        sAdapter = new RestAdapter
                .Builder()
                .setEndpoint(ipAddress)
                .build();
    }
}
