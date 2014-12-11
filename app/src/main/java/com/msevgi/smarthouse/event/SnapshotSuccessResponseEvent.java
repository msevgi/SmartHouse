package com.msevgi.smarthouse.event;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * Created by akturke on 12/11/2014.
 */
public class SnapshotSuccessResponseEvent {
    @NonNull
    Bitmap mBitmap;

    public SnapshotSuccessResponseEvent() {
    }

    public SnapshotSuccessResponseEvent(@NonNull Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(@NonNull Bitmap bitmap) {
        this.mBitmap = bitmap;
    }
}
