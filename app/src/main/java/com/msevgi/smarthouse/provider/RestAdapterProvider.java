package com.msevgi.smarthouse.provider;

import com.msevgi.smarthouse.constant.ApplicationConstants;

import retrofit.RestAdapter;

public final class RestAdapterProvider {
    private static RestAdapter sAdapter;

    public static RestAdapter getInstance() {
        if (sAdapter == null)
            sAdapter = new RestAdapter
                    .Builder()
                    .setEndpoint(ApplicationConstants.API_URL)
                    .build();

        return sAdapter;
    }
}
