package com.msevgi.smarthouse.handler;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.badoo.mobile.util.WeakHandler;
import com.msevgi.smarthouse.R;
import com.msevgi.smarthouse.constant.ApplicationConstants;
import com.msevgi.smarthouse.content.BellContentProvider;
import com.msevgi.smarthouse.helper.NotificationFacade;
import com.msevgi.smarthouse.interfaces.PhotoRestInterface;
import com.msevgi.smarthouse.receiver.GcmBroadcastReceiver;

import java.io.IOException;
import java.io.InputStream;

import retrofit.RestAdapter;
import retrofit.client.Response;

public final class GcmMessageHandler extends IntentService {

    private String mId;
    private WeakHandler mHandler;

    public GcmMessageHandler() {
        super("GcmMessageHandler");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new WeakHandler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle mExtras = intent.getExtras();

        mId = mExtras.getString("id");
        NotificationFacade mNotificationFacade = new NotificationFacade(this);
        mNotificationFacade
                .getBuilder()
                .setContentTitle("Door has ring!")
                .setContentText("The id of photo is " + mId)
                .setSmallIcon(R.drawable.ic_launcher);
        mNotificationFacade.show();

        RestAdapter mRestAdapter = new RestAdapter
                .Builder()
                .setEndpoint(ApplicationConstants.API_URL)
                .build();

        // Create an instance of our GitHub API interface.
        Bitmap mBitmap = null;
        try {
            PhotoRestInterface mRestInterface = mRestAdapter.create(PhotoRestInterface.class);
            Response mResponse = mRestInterface.getByteArray(mId);
            InputStream mInputStream = mResponse.getBody().in();
            mBitmap = BitmapFactory.decodeStream(mInputStream);
        } catch (IOException e) {
            Log.i("GCM", "Received : Corrupted Image Data");
        }

        BellContentProvider.Bell mBell = new BellContentProvider.Bell();
        mBell.setTime(SystemClock.currentThreadTimeMillis() + "");
        mBell.setPhotoId(mId);
        mBell.setBitmap(mBitmap);

        Uri mBellUri = BellContentProvider.getUri();
        getContentResolver().insert(mBellUri, mBell.toContentValues());

        Log.i("GCM", "Received : " + mExtras.getString("id"));
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

}