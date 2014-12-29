package com.msevgi.smarthouse.handler;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.content.SmartHouseContentProvider;
import com.msevgi.smarthouse.event.NewBellEvent;
import com.msevgi.smarthouse.helper.NotificationFacade;
import com.msevgi.smarthouse.interfaces.PhotoRestInterface;
import com.msevgi.smarthouse.provider.BusProvider;
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

        Uri mBellUri = SmartHouseContentProvider.getBellUri();
        getContentResolver().insert(mBellUri, bell.toContentValues());

        NotificationFacade notificationFacade = new NotificationFacade(this);
        notificationFacade
                .getBuilder()
                .setContentTitle("Door has ring!")
                .setContentText("The id of photo is " + id)
                .setSmallIcon(R.drawable.ic_bell)
                .setLargeIcon(thumbnail);
        notificationFacade.show();

        NewBellEvent event = new NewBellEvent();
        BusProvider.getInstance().post(event);

        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

}