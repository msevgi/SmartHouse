package com.msevgi.smarthouse.handler;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.activity.HomeActivity;
import com.msevgi.smarthouse.content.SmartHouseContentProvider;
import com.msevgi.smarthouse.event.NewBellEvent;
import com.msevgi.smarthouse.helper.NotificationFacade;
import com.msevgi.smarthouse.interfaces.PhotoRestInterface;
import com.msevgi.smarthouse.provider.BusProvider;
import com.msevgi.smarthouse.provider.ConfiguratorProvider;
import com.msevgi.smarthouse.provider.RestAdapterProvider;
import com.msevgi.smarthouse.receiver.GcmBroadcastReceiver;

import java.io.IOException;
import java.io.InputStream;

import retrofit.client.Response;

public final class GcmMessageHandler extends IntentService {

    public GcmMessageHandler() {
        super("GcmMessageHandler");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle mExtras = intent.getExtras();

        String id = mExtras.getString("id");
        String title = mExtras.getString("title");

        Bitmap bitmap = null;
        Bitmap thumbnail = null;
        try {
            PhotoRestInterface restInterface = RestAdapterProvider.getInstance().create(PhotoRestInterface.class);
            Response response = restInterface.getByteArray(id);
            InputStream inputStream = response.getBody().in();

            bitmap = BitmapFactory.decodeStream(inputStream);
            thumbnail = ThumbnailUtils.extractThumbnail(bitmap, 256, 256);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SmartHouseContentProvider.Bell bell = new SmartHouseContentProvider.Bell();
        bell.setTimestamp(System.currentTimeMillis());
        bell.setBitmap(bitmap);

        Uri bellUri = SmartHouseContentProvider.getBellUri();
        getContentResolver().insert(bellUri, bell.toContentValues());

        boolean doNotDisturb = ConfiguratorProvider.getInstance(getApplicationContext()).doNotDisturb().getOr(false);
        if (doNotDisturb)
            return;

        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri soundUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.doorbell);

        NotificationFacade notificationFacade = new NotificationFacade(this);
        notificationFacade
                .getBuilder()
                .setContentTitle("Door has ring!")
                .setContentText("The id of photo is " + id)
                .setSmallIcon(R.drawable.ic_bell)
                .setLargeIcon(thumbnail)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(soundUri);
        notificationFacade.show();

        NewBellEvent event = new NewBellEvent();
        BusProvider.getInstance().post(event);

        bitmap.recycle();
        thumbnail.recycle();

        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

}