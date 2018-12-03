package in.sourcecode.testproject;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class HelperContract {

    protected static String BASE_URI="content://";
    protected static String CONTENT_AUTHORITY="in.sourcecode.testproject";
    protected static Uri BASE_CONTENT_URI=Uri.parse(BASE_URI + CONTENT_AUTHORITY);


    public HelperContract() {
    }

    protected static class HelperEntry implements BaseColumns {

        static String TABLE1="profiletab";
        static String COLUMN_ID=BaseColumns._ID;
        static String COLUMN_NAME="name";
        static String COLUMN_Contact="contact";
        static String COLUMN_EmailId="emailId";
        static String COLUMN_PASS="pass";
        static String COLUMN_IMAGE="image";
        static String COLUMN_OCCUPATION="occupation";


        protected static String PATH2=TABLE1;

        protected static Uri COTENT_URI_TAB = Uri.withAppendedPath(BASE_CONTENT_URI, PATH2);

        protected static String CONTENT_LIST_TYPE2 = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH2;


    }

}
