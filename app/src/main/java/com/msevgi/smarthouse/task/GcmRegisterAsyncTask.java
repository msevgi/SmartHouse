package com.msevgi.smarthouse.task;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.msevgi.smarthouse.constant.ApplicationConstants;

import java.io.IOException;

public final class GcmRegisterAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private Context mContext;
    private GoogleCloudMessaging mGoogleCloudMessaging;

    public GcmRegisterAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            mGoogleCloudMessaging = GoogleCloudMessaging.getInstance(mContext);
            mGoogleCloudMessaging.register(ApplicationConstants.PROJECT_NUMBER);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }
}
