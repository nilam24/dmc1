package in.cdac.day19fcmdemo056;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Dell1 on 14/01/2018.
 */


public class MainActivity extends AppCompatActivity {


    EditText editText;
    Button click;
    String searchtext, userQuery;
    String API_KEY = "AIzaSyAKnS_-JxZo9sPyNnwIDX-jSGyrJvIYSTs";
    SharedPreferences preferences;
    public static final String mypreferences = "in.cdac.day19fcmdemo056.MyPreferences";
    BookInfo bookInfo;
    private final String TAG = MainActivity.this.getClass().getName();
    String error;
    String title_key = null;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextSearch);
        click = findViewById(R.id.buttonSearch);

        bookInfo = new BookInfo();


        preferences = getApplicationContext().getSharedPreferences(mypreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("userQuery", searchtext);
        editor.apply();


        preferences = getSharedPreferences(mypreferences, Context.MODE_PRIVATE);
        if (preferences != null) {
            Log.e("preference has values", "size" + preferences.getString("title", ""));

            title_key = preferences.getString("userQuery", "");

        }


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchtext = editText.getText().toString().trim();

                try {
                    if (searchtext == null) {

                        return;

                    } else {
                        userQuery = searchtext.replaceAll(" ", "+");

                        Log.e(TAG, ":=: " + userQuery);

                        Intent intent = new Intent(MainActivity.this, DisplaySearchResultActivity.class);

                        intent.putExtra("userQuery", userQuery);
                        startActivity(intent);

                    }
                } catch (NullPointerException n) {
                    error = n.getMessage();
                    Log.e(TAG, "error==" + error);
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        bookInfo = new BookInfo();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        searchtext = editText.getText().toString().trim();
        outState.putString("userQuery", searchtext);
        click.setVisibility(View.VISIBLE);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);

        String text = savedInstanceState.getString("userQuery");

    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {

        if (bookInfo != null) {
            return bookInfo;
        }

        return super.onRetainCustomNonConfigurationInstance();
    }


}