package in.cdac.day19fcmdemo056;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dell1 on 08/02/2018.
 */


public class BookListLoader extends AsyncTaskLoader<List<BookInfo>> {

    Context context;
    String requestQuery;
    List<BookInfo> bookInfoList = new ArrayList<BookInfo>();
    Loader<List<BookInfo>>loader;

    private static String TAG=BookListLoader.class.getName();

    public BookListLoader(Context context) {
        super(context);
        this.context = context;

    }



    public BookListLoader(Context context, String requestQuery, Loader<List<BookInfo>> loader) {
        super(context);

        this.context = context;
        this.requestQuery = requestQuery;
        this.loader=loader;

    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();

    }

    @Override
    public List<BookInfo> loadInBackground() {


        try {

            if (requestQuery != null) {
                bookInfoList = QueryUtileBookInfo.fetchData(requestQuery);

            }



        } catch (IOException e) {

            Log.e(TAG,"BookListLoader:::::"+e.getMessage());
        } catch (JSONException j) {
            Log.e(TAG,"BookListLoader"+j.getMessage());

        }

        return bookInfoList;

    }


    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();

    }

    @Override
    public void onCanceled(List<BookInfo> data) {
        super.onCanceled(data);

    }

    @Override
    public boolean isLoadInBackgroundCanceled() {


        return super.isLoadInBackgroundCanceled();
    }

    @Override
    protected void onReset() {
        super.onReset();

        stopLoading();
    }
}

