package in.sourcecode.testproject;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import in.sourcecode.testproject.HelperContract.*;

import static in.sourcecode.testproject.HelperContract.CONTENT_AUTHORITY;

public class HelperProvider extends ContentProvider {

    SQLiteDatabase read;
    SQLiteDatabase write;
    ContentResolver resolver;
    Cursor cursor;
// uri matcher
    private static final int test_match_1=100;
    private static final int test_match_2=101;
    private static final int test_match_3=102;
    private final String []projection={HelperEntry.COLUMN_ID,HelperEntry.COLUMN_NAME,HelperEntry.COLUMN_Contact,HelperEntry.COLUMN_EmailId,HelperEntry.COLUMN_PASS,HelperEntry.COLUMN_OCCUPATION,HelperEntry.COLUMN_IMAGE};
    private final String []projectio1={HelperEntry.COLUMN_EmailId,HelperEntry.COLUMN_IMAGE};
    private final String []projection2={HelperEntry.COLUMN_EmailId,HelperEntry.COLUMN_OCCUPATION};
    private final String[]projection3={HelperEntry.COLUMN_EmailId,HelperEntry.COLUMN_Contact};
    private final String[]projection4={HelperEntry.COLUMN_ID,HelperEntry.COLUMN_EmailId};
    private static final UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    static {

        uriMatcher.addURI(CONTENT_AUTHORITY,HelperEntry.PATH2,test_match_1);

    }

    @Override
    public boolean onCreate() {

        Helperdb helperdb=new Helperdb(getContext());
        read=helperdb.getReadableDatabase();
        write=helperdb.getWritableDatabase();
        resolver=getContext().getContentResolver();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        cursor=read.query(HelperEntry.TABLE1,projection,null,null,null,null,null);

        cursor.setNotificationUri(resolver,uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        int match=uriMatcher.match(uri);
        switch (match) {
            case test_match_1:
                insertProfile(uri,values);
                return uri;
            default:
                throw new IllegalArgumentException("exception ");

        }

    }
    private Uri insertProfile(Uri uri,ContentValues values)
    {
        long id=0;
//      int match=uriMatcher.match(uri);
//        switch (match) {
//
//            case test_match_1:
               id = write.insert(HelperEntry.TABLE1, null, values);
            if (id == 1) {
                return null;
            }
            if (id != 1) {
                return uri;
            }
//            default:
//                throw new IllegalArgumentException("exception ");
//        }

        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
