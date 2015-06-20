package com.msevgi.smarthouse.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.msevgi.smarthouse.constant.ApplicationConstants;
import com.msevgi.smarthouse.event.TokenSendEvent;
import com.msevgi.smarthouse.provider.BusProvider;

import java.io.IOException;

public final class GcmRegisterAsyncTask extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private GoogleCloudMessaging mGoogleCloudMessaging;

    public GcmRegisterAsyncTask(Context context) {
        mContext = context;
        mGoogleCloudMessaging = GoogleCloudMessaging.getInstance(mContext);
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return mGoogleCloudMessaging.register(ApplicationConstants.PROJECT_NUMBER);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String registrationId) {
        if (registrationId == null)
            return;

        TokenSendEvent event = new TokenSendEvent();
        event.setToken(registrationId);

        BusProvider.getInstance().post(event);

        Log.i("GCM", registrationId);
    }
}
