package com.msevgi.smarthouse.content;

import de.triplet.simpleprovider.AbstractProvider;
import de.triplet.simpleprovider.Column;
import de.triplet.simpleprovider.Table;

public final class BellContentProvider extends AbstractProvider {

    public static final String AUTHORITY = "com.msevgi.smarthouse";
    public static final String URI = "content://com.msevgi.smarthouse/bells";

    @Override
    protected String getAuthority() {
        return AUTHORITY;
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
