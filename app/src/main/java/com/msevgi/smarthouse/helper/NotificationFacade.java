package com.msevgi.smarthouse.helper;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

public final class NotificationFacade {

    private static final int NOTIFICATION_ID = 001;

    private Context mContext;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;

    public NotificationFacade(Context context) {
        mContext = context;
        mBuilder = new NotificationCompat.Builder(context);
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public NotificationCompat.Builder getBuilder() {
        return mBuilder;
    }

    public void show() {
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
