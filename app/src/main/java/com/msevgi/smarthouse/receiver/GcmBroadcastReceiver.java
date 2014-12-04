package com.msevgi.smarthouse.receiver;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.msevgi.smarthouse.handler.GcmMessageHandler;

public final class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Explicitly specify that GcmMessageHandler will handle the intent.
        ComponentName mComponentName = new ComponentName(context.getPackageName(),GcmMessageHandler.class.getName());

        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, (intent.setComponent(mComponentName)));
        setResultCode(Activity.RESULT_OK);
    }
}