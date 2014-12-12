package com.msevgi.smarthouse.event;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public final class SnapshotSuccessResponseEvent {
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
