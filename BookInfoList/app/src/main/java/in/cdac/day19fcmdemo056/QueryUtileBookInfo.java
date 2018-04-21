package in.cdac.day19fcmdemo056;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Dell1 on 08/02/2018.
 */


public class QueryUtileBookInfo {


    private static final String TAG = QueryUtileBookInfo.class.getName();
    SharedPreferences preferences;
    public static final String mypreferences = "in.cdac.day19fcmdemo056.MyPreferences";
    private static String baseQuery = "https://www.googleapis.com/books/v1/volumes?q=";
    private String query;
    private static int CONNECTION_TIME_OUT = 15000;
    private static int READ_TIME_OUT = 12000;
    private static Bitmap bitmap = null;
    private static int RESPONSE_CODE = 200;
    private static HttpsURLConnection connection = null;
    BookInfo bookInfo;
    private static InputStream inputStream = null;


    // default constructor --no need to initialise the class
    private QueryUtileBookInfo() {
    }


    public static List<BookInfo> fetchData(String murl) throws IOException, JSONException {

        Uri uri = urlEncode(murl);
        URL url = createUrl(uri);
        String res = makeConnection(url);
        List<BookInfo> list = extractJsonBookInfo(res);

        return list;

    }

    private static Uri urlEncode(String qq) throws UnsupportedEncodingException {

        String querySearchCriteria = URLEncoder.encode(qq, "UTF-8");
        String newGoogleBookApiqueryUrl = baseQuery + querySearchCriteria;
        Uri builtUri = Uri.parse(newGoogleBookApiqueryUrl)
                .buildUpon()
                .appendQueryParameter("maxResult", "3")
                .build();

        return builtUri;

    }

    private static URL createUrl(Uri uri) {

        URL url = null;

        try {

            url = new URL(uri.toString());

            return url;

        } catch (MalformedURLException e) {
            e.printStackTrace();

        }

        return url;

    }


    private static String makeConnection(URL url) throws UnknownHostException {

        String jsonResult = "";


        if (url == null) {

            return jsonResult;

        }

        try {
            connection = (HttpsURLConnection) url.openConnection();

            connection.setConnectTimeout(CONNECTION_TIME_OUT);
            connection.setReadTimeout(READ_TIME_OUT);
            connection.setRequestMethod("GET");
            connection.connect();


            if (connection.getResponseCode() == RESPONSE_CODE) {

                inputStream = connection.getInputStream();
                jsonResult = readFromStream(inputStream);
                readFromStream(inputStream);


            } else if (connection.getResponseCode() != RESPONSE_CODE) {

                connection.disconnect();

                Log.e(TAG, "" + connection.getResponseCode());

            }


        } catch (SocketTimeoutException io) {

            Log.e(TAG, "connection timeout" + connection);

        } catch (IOException e) {

            Log.e(TAG, "" + e.getMessage());

        } finally {
            if (connection != null) {

                connection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }

        }

        Log.e(TAG, "httpConnection " + jsonResult);


        return jsonResult;

    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder builder = new StringBuilder();


        if (inputStream != null) {

            InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();

            while (line != null) {

                builder.append(line);
                line = bufferedReader.readLine();

            }

        }

        Log.e(TAG, " " + builder);

        return builder.toString();
    }

    private static List<BookInfo> extractJsonBookInfo(String jsonString) {


        BookInfo info = null;
        String booktitle = null;
        String authorname = null;
        String publishername = null;
        String imglink=null;
        HttpURLConnection con = null;


        List<BookInfo> bookInfoList = new ArrayList<BookInfo>();

        if (TextUtils.isEmpty(jsonString)) {

            return null;
        }

        try {
            JSONObject rootJson = new JSONObject(jsonString);
            JSONArray itemarray = rootJson.getJSONArray("items");
            int i = 0;
            for (i = 0; i < itemarray.length(); i++) {
                JSONObject itemroot = itemarray.getJSONObject(i);

                JSONObject volumeobj = itemroot.getJSONObject("volumeInfo");

                booktitle = volumeobj.getString("title");

                if (volumeobj.has("authors")) {
                    JSONArray authorarray = volumeobj.getJSONArray("authors");

                    for (int j = 0; j < authorarray.length(); j++) {
                        authorname = authorarray.optString(j);

                    }

                }

                if (volumeobj.has("publisher")) {

                    publishername = volumeobj.getString("publisher");

                }

                if(volumeobj.has("imageLinks")) {
                    JSONObject imagelinkobj = volumeobj.getJSONObject("imageLinks");

                    imglink = (String) imagelinkobj.get("smallThumbnail");

                }


                if (imglink != null) {
                    URL imageUrl = new URL(imglink);

                    con = (HttpURLConnection) imageUrl.openConnection();
                    con.setConnectTimeout(CONNECTION_TIME_OUT);
                    con.setReadTimeout(READ_TIME_OUT);
                    con.setRequestMethod("GET");
                    con.connect();

                    try {
                        if (con.getResponseCode() == RESPONSE_CODE) {
                            inputStream = con.getInputStream();
                            bitmap = BitmapFactory.decodeStream(inputStream);

                        } else if (con.getResponseCode() != RESPONSE_CODE) {
                            inputStream = null;
                            bitmap = null;
                        }
                    } catch (SocketTimeoutException s) {
                        Log.e(TAG, "" + s.getLocalizedMessage());
                    }


                }

                info = new BookInfo(booktitle, authorname, publishername, bitmap);
                bookInfoList.add(info);

                Log.e(TAG, ": list data ==" + bookInfoList);

            }

        } catch (JSONException j) {
            Log.e(TAG, "" + j.getMessage());
        } catch (MalformedURLException e) {
            Log.e(TAG, "malformed Url" + e.getMessage());
        } catch (IOException io) {
            Log.e(TAG, "QueryutilExtractJson" + io.getMessage());
        } finally {
            Log.e(TAG, "exception ");
        }

        return bookInfoList;
    }

}


