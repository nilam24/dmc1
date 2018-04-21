package in.cdac.day19fcmdemo056;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Dell1 on 14/01/2018.
 */

public class BookInfo implements Parcelable {

    private String title;
    private String authors;
    private String publisher;
    private Bitmap smallThumbnails;


    //default constructor-- no need to initialise the class
    public BookInfo() {
    }


    public BookInfo(String title, String authors, String publisher, Bitmap smallThumbnails) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.smallThumbnails = smallThumbnails;
    }


    protected BookInfo(Parcel in) {
        title = in.readString();
        authors = in.readString();
        publisher = in.readString();
        smallThumbnails = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(authors);
        dest.writeString(publisher);
        dest.writeParcelable(smallThumbnails, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookInfo> CREATOR = new Creator<BookInfo>() {
        @Override
        public BookInfo createFromParcel(Parcel in) {
            return new BookInfo(in);
        }

        @Override
        public BookInfo[] newArray(int size) {
            return new BookInfo[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Bitmap getSmallThumbnails() {
        return smallThumbnails;
    }

    public void setSmallThumbnails(Bitmap smallThumbnails) {
        this.smallThumbnails = smallThumbnails;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", smallThumbnails='" + smallThumbnails +
                '}';

    }


}
