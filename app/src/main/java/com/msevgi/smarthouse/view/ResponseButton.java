package com.msevgi.smarthouse.view;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.widget.Button;

public final class ResponseButton extends Button {
    private Cursor mCursor;

    public ResponseButton(Context context) {
        super(context);
    }

    public ResponseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResponseButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Cursor getCursor() {
        return mCursor;
    }

    public void setCursor(Cursor cursor) {
        this.mCursor = cursor;
    }
}
