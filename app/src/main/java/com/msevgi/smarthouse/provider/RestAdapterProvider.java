package com.msevgi.smarthouse.provider;

import com.msevgi.smarthouse.constant.ApplicationConstants;

import retrofit.RestAdapter;

public final class RestAdapterProvider {
    private static RestAdapter mAdapter;

    public static RestAdapter getInstance() {
        if (mAdapter == null)
            mAdapter = new RestAdapter
                    .Builder()
                    .setEndpoint(ApplicationConstants.API_URL)
                    .build();

        return mAdapter;
    }
}
