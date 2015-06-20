package com.msevgi.smarthouse.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.msevgi.smarthouse.event.SnapshotFailureResponseEvent;
import com.msevgi.smarthouse.event.SnapshotRequestEvent;
import com.msevgi.smarthouse.event.SnapshotSuccessResponseEvent;
import com.msevgi.smarthouse.interfaces.SnapshotRestInterface;
import com.msevgi.smarthouse.provider.BusProvider;
import com.msevgi.smarthouse.provider.RestAdapterProvider;

import java.io.InputStream;

import retrofit.client.Response;

public final class SnapshotAsyncTask extends AsyncTask<Void, Void, Boolean> {
    Bitmap mBitmap = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        SnapshotRequestEvent mEvent = new SnapshotRequestEvent();
        BusProvider.getInstance().post(mEvent);
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            SnapshotRestInterface snapshotRestInterface = RestAdapterProvider.getInstance().create(SnapshotRestInterface.class);
            Response response = snapshotRestInterface.getByteArray();
            InputStream inputStream = response.getBody().in();
            mBitmap = BitmapFactory.decodeStream(inputStream);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HATA", e.getMessage());
        }
        return false;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (result) {
            SnapshotSuccessResponseEvent mSuccessEvent = new SnapshotSuccessResponseEvent();
            mSuccessEvent.setBitmap(mBitmap);
            BusProvider.getInstance().post(mSuccessEvent);
        } else {
            SnapshotFailureResponseEvent mFailureEvent = new SnapshotFailureResponseEvent();
            BusProvider.getInstance().post(mFailureEvent);
        }
    }
}
