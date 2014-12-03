package com.msevgi.smarthouse.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.msevgi.smarthouse.constant.ApplicationConstants;

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
            return "";
        }
    }

    @Override
    protected void onPostExecute(String registrationId) {
        Log.i("GCM", registrationId);
        super.onPostExecute(registrationId);
    }
}
