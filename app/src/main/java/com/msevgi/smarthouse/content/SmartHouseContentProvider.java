package com.msevgi.smarthouse.content;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.ByteArrayOutputStream;

import de.triplet.simpleprovider.AbstractProvider;
import de.triplet.simpleprovider.Column;
import de.triplet.simpleprovider.Table;

public final class SmartHouseContentProvider extends AbstractProvider {

    private static final String AUTHORITY = "com.msevgi.smarthouse";
    private static final String URI_BELL_STRING = "content://com.msevgi.smarthouse/bells";
    private static final String URI_SPEECH_STRING = "content://com.msevgi.smarthouse/speechs";
    private static Uri mBellUri;
    private static Uri mSpeechUri;

    @Override
    protected String getAuthority() {
        return AUTHORITY;
    }

    public static Uri getBellUri() {
        if (mBellUri == null)
            mBellUri = Uri.parse(URI_BELL_STRING);

        return mBellUri;
    }

    public static Uri getSpeechUri() {
        if (mSpeechUri == null)
            mSpeechUri = Uri.parse(URI_SPEECH_STRING);

        return mSpeechUri;
    }

    @Table
    public static class Bell {

        @Column(value = Column.FieldType.INTEGER, primaryKey = true)
        public static final String KEY_ID = "_id";

        @Column(value = Column.FieldType.INTEGER)
        public static final String PHOTO_ID = "photo_id";

        @Column(Column.FieldType.TEXT)
        public static final String KEY_TIME = "time";

        @Column(Column.FieldType.BLOB)
        public static final String KEY_BITMAP = "bitmap";

        private String mPhotoId;
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

        public String getPhotoId() {
            return mPhotoId;
        }

        public void setPhotoId(String photoId) {
            this.mPhotoId = photoId;
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

    @Table
    public static class Speech {

        @Column(value = Column.FieldType.INTEGER, primaryKey = true)
        public static final String KEY_ID = "_id";

        @Column(value = Column.FieldType.TEXT)
        public static final String KEY_CONTENT = "content";

        private String mContent;

        public String getContent() {
            return mContent;
        }

        public void setContent(String content) {
            mContent = content;
        }

        public ContentValues toContentValues() {
            ContentValues mContentValues = new ContentValues();
            mContentValues.put(KEY_CONTENT, mContent);
            return mContentValues;
        }

    }

}
