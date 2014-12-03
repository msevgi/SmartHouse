package com.msevgi.smarthouse.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.msevgi.smarthouse.activity.HomeActivity;

public class NotificationHelper {

    private NotificationCompat.Builder notificationCompatBuilder;
    private NotificationManager notificationManager;

    private Context context;

    private int requestCode = 0;
    private int notificationId = 001;

    public NotificationHelper(Context context) {
        notificationCompatBuilder = new NotificationCompat.Builder(context);
        this.context = context;
    }

    public NotificationCompat.Builder setContentTitle(String title) {
        return notificationCompatBuilder.setContentTitle(title);
    }

    public NotificationCompat.Builder setSmallIcon(int icon) {
        return notificationCompatBuilder.setSmallIcon(icon);
    }

    public NotificationCompat.Builder setContentText(String content) {
        return notificationCompatBuilder.setContentText(content);
    }

    public NotificationCompat.Builder setAutoCancel(boolean isCancel) {
        return notificationCompatBuilder.setAutoCancel(isCancel);
    }

    public void show() {

        Intent intent = new Intent(context, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationCompatBuilder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationCompatBuilder.build());
    }
}
