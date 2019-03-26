package com.ngocminhtran.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.content.UriMatcher;
import com.ngocminhtran.sqlitedemoapplication.DataHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;
public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY
            ="com.ngocminhtran.database.provider.MyContentProvider";
    private static final String STUDENTS_TABLE = "Students";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + STUDENTS_TABLE);
    public static final int STUDENTS = 1;
    public static final int STUDENTS_ID = 2;
    private static final UriMatcher sURIMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, STUDENTS_TABLE, STUDENTS);
        sURIMatcher.addURI(AUTHORITY, STUDENTS_TABLE + "/#",
                STUDENTS_ID);
    }
    private DataHandler myHandler;
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myHandler.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case STUDENTS:
                rowsDeleted = sqlDB.delete(DataHandler.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case STUDENTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted =
                            sqlDB.delete(DataHandler.TABLE_NAME,
                                    myHandler.COLUMN_ID + "=" + id,
                                    null);
                } else {
                    rowsDeleted =
                            sqlDB.delete(DataHandler.TABLE_NAME,
                                    myHandler.COLUMN_ID + "=" + id
                                            + " and " + selection,
                                    selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " +
                        uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myHandler.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case STUDENTS:
                id = sqlDB.insert(DataHandler.TABLE_NAME,
                        null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "
                        + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(STUDENTS_TABLE + "/" + id);
    }


    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        myHandler = new DataHandler(getContext(), null, null, 1);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DataHandler.TABLE_NAME);
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case STUDENTS_ID:
                queryBuilder.appendWhere(DataHandler.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;
            case STUDENTS:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }
        Cursor cursor = queryBuilder.query(myHandler.getReadableDatabase(),projection,
                selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myHandler.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case STUDENTS:
                rowsUpdated =
                        sqlDB.update(DataHandler.TABLE_NAME,
                                values,
                                selection,
                                selectionArgs);
                break;
            case STUDENTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =
                            sqlDB.update(DataHandler.TABLE_NAME,
                                    values,
                                    DataHandler.COLUMN_ID + "=" + id,
                                    null);
                } else {
                    rowsUpdated =
                            sqlDB.update(DataHandler.TABLE_NAME,
                                    values,
                                    DataHandler.COLUMN_ID + "=" + id
                                            + " and "
                                            + selection,
                                    selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "
                        + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
