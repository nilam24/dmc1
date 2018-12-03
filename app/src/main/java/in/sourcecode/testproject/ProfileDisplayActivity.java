package in.sourcecode.testproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ProfileDisplayActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    String emailid;
    ImageView img;
    TextView nmtext, contacttext, emtext, ocuptext;
    String name1, contact1, email1, occupation;
    byte[] bytes;
    private static final int LOADERID = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        nmtext = (TextView) findViewById(R.id.textViewN);
        contacttext = (TextView) findViewById(R.id.textViewC);
        emtext = (TextView) findViewById(R.id.textViewE);
        ocuptext = (TextView) findViewById(R.id.textViewO);
        img = (ImageView) findViewById(R.id.imageView1);

        Intent intent = getIntent();
        if (intent != null) {
            emailid = intent.getStringExtra("email");
            Toast.makeText(this, " .." + emailid, Toast.LENGTH_LONG).show();
        }

        getSupportLoaderManager().initLoader(LOADERID, null, this);

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {

        Uri uri = HelperContract.HelperEntry.COTENT_URI_TAB;
        String projection[] = {HelperContract.HelperEntry.COLUMN_ID, HelperContract.HelperEntry.COLUMN_NAME, HelperContract.HelperEntry.COLUMN_Contact, HelperContract.HelperEntry.COLUMN_EmailId, HelperContract.HelperEntry.COLUMN_PASS, HelperContract.HelperEntry.COLUMN_OCCUPATION, HelperContract.HelperEntry.COLUMN_IMAGE};
        String seleArg[] = {emailid};
        String selection = HelperContract.HelperEntry.COLUMN_EmailId + " =?";
        CursorLoader cursorLoader = new CursorLoader(this, uri, projection, selection, seleArg, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

        if (cursor != null) {
            cursor.moveToFirst();

           for(int i=0;i<cursor.getCount();i++) {
               email1 = cursor.getString(cursor.getColumnIndex(HelperContract.HelperEntry.COLUMN_EmailId));


               if (email1.equals(emailid)) {
                   name1 = cursor.getString(cursor.getColumnIndex(HelperContract.HelperEntry.COLUMN_NAME));
                   nmtext.setText(name1);
                   contact1 = cursor.getString(cursor.getColumnIndex(HelperContract.HelperEntry.COLUMN_Contact));
                   contacttext.setText(contact1);
                   emtext.setText(email1);
                   occupation = cursor.getString(cursor.getColumnIndex(HelperContract.HelperEntry.COLUMN_OCCUPATION));
                   ocuptext.setText(occupation);
                   bytes = cursor.getBlob(cursor.getColumnIndex(HelperContract.HelperEntry.COLUMN_IMAGE));

                   if (bytes != null) {
                       InputStream inputStream = new ByteArrayInputStream(bytes);
                       Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                       img.setImageBitmap(bitmap);
                   }
                   break;
               }
           }
            //img.setImageBitmap();

            Log.e(".*.", "" + name1 + contact1 + email1 + occupation + bytes);


            if (cursor == null) {
                Log.e("null", "null");
            }
        }


    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {


    }
}
