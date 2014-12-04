package com.msevgi.smarthouse.content;

import android.net.Uri;

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
        public static final String KEY_TITLE = "date";

        @Column(Column.FieldType.BLOB)
        public static final String KEY_CONTENT = "photo";
    }

}
