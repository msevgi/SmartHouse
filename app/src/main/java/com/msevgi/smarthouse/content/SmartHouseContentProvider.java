package com.msevgi.smarthouse.content;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;

import com.msevgi.smarthouse.constant.ApplicationConstants;
import com.msevgi.smarthouse.model.Language;

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

        @Column(value = Column.FieldType.INTEGER, primaryKey = true, unique = true)
        public static final String KEY_ID = "_id";

        @Column(Column.FieldType.INTEGER)
        public static final String KEY_TIME_STAMP = "time_stamp";

        @Column(Column.FieldType.BLOB)
        public static final String KEY_BITMAP = "bitmap";

        private long mTimestamp;
        private Bitmap mBitmap;

        public long getTimestamp() {
            return mTimestamp;
        }

        public void setTimestamp(long time) {
            mTimestamp = time;
        }

        public Bitmap getBitmap() {
            return mBitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            mBitmap = bitmap;
        }

        private byte[] getByteArray() {
            ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, mOutputStream);
            return mOutputStream.toByteArray();
        }

        public ContentValues toContentValues() {
            ContentValues mContentValues = new ContentValues();
            mContentValues.put(KEY_TIME_STAMP, mTimestamp);
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

        @Column(value = Column.FieldType.TEXT)
        public static final String KEY_LANGUAGE = "language";

        private String mContent;
        private String mLanguage;

        public String getContent() {
            return mContent;
        }

        public void setContent(String content) {
            mContent = content;
        }

        public void setLanguage(Language language) {
            mLanguage = language.toString();
        }

        public ContentValues toContentValues() {
            ContentValues mContentValues = new ContentValues();
            mContentValues.put(KEY_CONTENT, mContent);
            mContentValues.put(KEY_LANGUAGE, mLanguage);
            return mContentValues;
        }
    }
}
