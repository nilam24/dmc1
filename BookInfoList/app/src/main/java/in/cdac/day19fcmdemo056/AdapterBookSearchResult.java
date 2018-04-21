package in.cdac.day19fcmdemo056;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


/**
 * Created by Dell1 on 14/01/2018.
 */


public class AdapterBookSearchResult extends ArrayAdapter<BookInfo> {

    Context context;
    List<BookInfo> arrayList;

    public AdapterBookSearchResult(@NonNull Context context, @NonNull List<BookInfo> arrayList) {
        super(context, 0, arrayList);

        this.context = context;
        this.arrayList = arrayList;

    }

    @Nullable
    @Override
    public BookInfo getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemview = convertView;
        context = parent.getContext();
        if (itemview == null) {
            itemview = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);

        }
        BookInfo bookInfo = getItem(position);
        TextView titeltext, authortext, publishertext;
        ImageView img;

        titeltext = itemview.findViewById(R.id.textViewTitle);
        String title = bookInfo.getTitle();
        titeltext.setText(title);

        authortext = itemview.findViewById(R.id.textViewAuthor);
        String author = bookInfo.getAuthors();
        authortext.setText(author);

        publishertext = itemview.findViewById(R.id.textViewPublisher);
        String publisher = bookInfo.getPublisher();
        publishertext.setText(publisher);


        img = itemview.findViewById(R.id.imageView);
        Bitmap bitmap = bookInfo.getSmallThumbnails();

        if (bitmap != null) {
            img.setImageBitmap(bitmap);
        }


        Log.e("TAG-Adapter", "" + bitmap + "," + title + "," + publisher);

        return itemview;
    }
}
