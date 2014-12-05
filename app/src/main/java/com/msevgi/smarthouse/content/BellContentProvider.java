package com.msevgi.smarthouse.content;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.ByteArrayOutputStream;

import de.triplet.simpleprovider.AbstractProvider;
import de.triplet.simpleprovider.Column;
import de.triplet.simpleprovider.Table;

public final class BellContentProvider extends AbstractProvider {

    private static final String AUTHORITY = "com.msevgi.smarthouse";
    private static final String URI_STRING = "content://com.msevgi.smarthouse/bells";
    private static Uri mUri;

    @Override
    protected String getAuthority() {
        return AUTHORITY;
    }

    public static Uri getUri() {
        if (mUri == null)
            mUri = Uri.parse(URI_STRING);

        return mUri;
    }

    @Table
    public static class Bell {

        @Column(value = Column.FieldType.INTEGER, primaryKey = true)
        public static final String KEY_ID = "_id";

        @Column(Column.FieldType.TEXT)
        public static final String KEY_TIME = "time";

        @Column(Column.FieldType.BLOB)
        public static final String KEY_BITMAP = "bitmap";

        private String mTime;
        private Bitmap mBitmap;

        public String getTime() {
            return mTime;
        }

        public void setTime(String time) {
            mTime = time;
        }

        public Bitmap getBitmap() {
            return mBitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            mBitmap = bitmap;
        }

        public byte[] getByteArray() {
            ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, mOutputStream);
            return mOutputStream.toByteArray();
        }

        public ContentValues toContentValues() {
            ContentValues mContentValues = new ContentValues();
            mContentValues.put(KEY_TIME, mTime);
            mContentValues.put(KEY_BITMAP, getByteArray());
            return mContentValues;
        }

    }

}
