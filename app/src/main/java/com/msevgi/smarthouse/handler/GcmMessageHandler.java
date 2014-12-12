package com.msevgi.smarthouse.handler;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.constant.ApplicationConstants;
import com.msevgi.smarthouse.content.SmartHouseContentProvider;
import com.msevgi.smarthouse.helper.NotificationFacade;
import com.msevgi.smarthouse.interfaces.PhotoRestInterface;
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

        String mId = mExtras.getString("id");
        String mTitle = mExtras.getString("title");

        Bitmap mBitmap = null;
        try {
            PhotoRestInterface mRestInterface = RestAdapterProvider.getInstance().create(PhotoRestInterface.class);
            Response mResponse = mRestInterface.getByteArray(mId);
            InputStream mInputStream = mResponse.getBody().in();
            mBitmap = BitmapFactory.decodeStream(mInputStream);
        } catch (IOException e) {
            Log.i("GCM", "Received : Corrupted Image Data");
        }

        SmartHouseContentProvider.Bell mBell = new SmartHouseContentProvider.Bell();
        mBell.setTime(SystemClock.currentThreadTimeMillis() + "");
        mBell.setPhotoId(mId);
        mBell.setBitmap(mBitmap);

        Uri mBellUri = SmartHouseContentProvider.getBellUri();
        getContentResolver().insert(mBellUri, mBell.toContentValues());

        NotificationFacade mNotificationFacade = new NotificationFacade(this);
        mNotificationFacade
                .getBuilder()
                .setContentTitle("Door has ring!")
                .setContentText("The id of photo is " + mId)
                .setSmallIcon(R.drawable.ic_home)
                .setLargeIcon(mBitmap);
        mNotificationFacade.show();

        Log.i("GCM", "Received : " + mExtras.getString("id"));
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

}