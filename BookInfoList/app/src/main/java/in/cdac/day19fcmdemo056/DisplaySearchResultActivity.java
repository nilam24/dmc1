package in.cdac.day19fcmdemo056;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dell1 on 14/01/2018.
 */

public class DisplaySearchResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookInfo>> {

    TextView emptyViewText;
    ListView listViewResult;
    AdapterBookSearchResult adapterBookSearchResult;
    List<BookInfo> arraylist = null;
    BookInfo bookInfo;
    private final String TAG = DisplaySearchResultActivity.this.getClass().getName();
    private static final int BookListLoader_ID = 1;
    String query = null;
    Parcelable state;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_search_info_layout);

        Intent in = getIntent();
        Bundle b = in.getExtras();

        bookInfo = new BookInfo();

        if (b != null) {

            query = b.getString("userQuery");

            Log.e(TAG, "bundle =" + b + "," + query);

        }

        emptyViewText = findViewById(R.id.textViewSearchBy);
        listViewResult = findViewById(R.id.listviewId);
        listViewResult.setEmptyView(emptyViewText);

        adapterBookSearchResult = new AdapterBookSearchResult(DisplaySearchResultActivity.this, new ArrayList<BookInfo>());
        listViewResult.setAdapter(adapterBookSearchResult);
        adapterBookSearchResult.notifyDataSetChanged();

        Log.e(TAG, "in arraylist ==" + arraylist + " ");


        try {
            ConnectivityManager conManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = conManager.getActiveNetworkInfo();


            if ((networkInfo != null) && networkInfo.isConnected()) {


                LoaderManager loaderManager = getSupportLoaderManager();
                loaderManager.initLoader(BookListLoader_ID, null, this);
                Toast.makeText(DisplaySearchResultActivity.this, " List is populating....." + query, Toast.LENGTH_LONG).show();


            } else {
                emptyViewText.setText(R.string.textview_noNetwork);

                LoaderManager.enableDebugLogging(true);

            }

            Log.e(TAG, "conManager" + networkInfo);
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());

        }

    }


    @Override
    public Loader<List<BookInfo>> onCreateLoader(int id, Bundle args) {


        BookListLoader bookListLoader = new BookListLoader(this, query, new Loader<List<BookInfo>>(this));


        return bookListLoader;

    }

    @Override
    public void onLoadFinished(Loader<List<BookInfo>> loader, List<BookInfo> bookInfos) {

        if (bookInfos == null) {
            emptyViewText.setText(R.string.textview1_searchCriteria);
        }

        adapterBookSearchResult.clear();

        if ((bookInfos != null) && (!bookInfos.isEmpty())) {
            adapterBookSearchResult.addAll(bookInfos);

        }
    }

    @Override
    public void onLoaderReset(Loader<List<BookInfo>> loader) {

        adapterBookSearchResult.clear();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("BookInfo", bookInfo);
        position = adapterBookSearchResult.getPosition(bookInfo);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getParcelable("BookInfo");

    }

    @Override
    protected void onResume() {
        super.onResume();

        bookInfo = new BookInfo();

    }

    @Override
    protected void onPause() {
        super.onPause();

        position = adapterBookSearchResult.getPosition(bookInfo);

        state = listViewResult.onSaveInstanceState();

    }

}
