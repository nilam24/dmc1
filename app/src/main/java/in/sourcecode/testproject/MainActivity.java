package in.sourcecode.testproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    EditText editTextunm, editTextupass;
    Button loginbtn, register;
    String useremail;
    String upas;
    String e, em;
    String password, ps;
    private static int LOADERID = 1;
    User user;
    List<User> userList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        editTextunm = (EditText) findViewById(R.id.editTextU);
        editTextupass = (EditText) findViewById(R.id.editTextP);
        loginbtn = (Button) findViewById(R.id.btnlogin);
        register = (Button) findViewById(R.id.buttonReg);

        getSupportLoaderManager().initLoader(LOADERID,null,this);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //   if (savedInstanceState != null)
                //     e = savedInstanceState.getString("email");
                //  p = savedInstanceState.getString("pass");


                useremail = editTextunm.getText().toString().trim();
                password = editTextupass.getText().toString().trim();

                Toast.makeText(MainActivity.this, "login ", Toast.LENGTH_LONG).show();

                login(useremail, password);
//                if ((useremail.equals(e)) && (password.equals(p))) {
//                    Intent intent = new Intent(MainActivity.this, ProfileDisplayActivity.class);
//                    intent.putExtra("email", em);
//
//                    startActivity(intent);
//                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });


    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {

        Uri uri = HelperContract.HelperEntry.COTENT_URI_TAB;
        String projection[] = {HelperContract.HelperEntry.COLUMN_ID, HelperContract.HelperEntry.COLUMN_NAME, HelperContract.HelperEntry.COLUMN_Contact, HelperContract.HelperEntry.COLUMN_EmailId, HelperContract.HelperEntry.COLUMN_PASS, HelperContract.HelperEntry.COLUMN_OCCUPATION, HelperContract.HelperEntry.COLUMN_IMAGE};
        String seleArg[] = {};
        String selection = HelperContract.HelperEntry.COLUMN_EmailId + " =? ";
        CursorLoader cursorLoader = new CursorLoader(MainActivity.this, uri, projection, selection, seleArg, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor){

        if(cursor==null)
        {
            editTextunm.setHint("null");
            editTextupass.setHint("null");
            return;
        }

        if(cursor.getCount()<=0)
        {
            editTextunm.setHint("null");
            editTextupass.setHint("null");
            return;
        }

        if(cursor!=null){
            cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();) {

                em = cursor.getString(cursor.getColumnIndex(HelperContract.HelperEntry.COLUMN_EmailId));
                ps = cursor.getString(cursor.getColumnIndex(HelperContract.HelperEntry.COLUMN_PASS));

                cursor.move(i);
                i++;
                Log.e("loadfinishh", "" + em+ps);
            }

        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", em);
        outState.putString("pass", ps);
    }

    protected void login(String useremail, String password) {

        Toast.makeText(MainActivity.this, "login method ", Toast.LENGTH_LONG).show();
        if (useremail.equals(em) && password.equals(ps)) {

            Toast.makeText(MainActivity.this, "login databaste ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, ProfileDisplayActivity.class);
            intent.putExtra("email", em);

            startActivity(intent);
        }
    }
}
