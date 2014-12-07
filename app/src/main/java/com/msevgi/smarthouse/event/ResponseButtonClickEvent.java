package com.msevgi.smarthouse.event;

import android.database.Cursor;

public class ResponseButtonClickEvent {
    private Cursor mCursor;

    public ResponseButtonClickEvent(Cursor mCursor) {
        this.mCursor = mCursor;
    }

    public ResponseButtonClickEvent() {
    }

    public Cursor getCursor() {
        return mCursor;
    }

    public void setCursor(Cursor cursor) {
        this.mCursor = cursor;
    }
}
