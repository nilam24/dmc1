package in.sourcecode.testproject;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String user_name;
    private String contact;
    private String emailId;
    private String password;
    private String occupation;
    private String image;

    public User() {
    }

    public User(String user_name, String contact, String emailId, String password, String occupation, String image) {
        this.user_name = user_name;
        this.contact = contact;
        this.emailId = emailId;
        this.password = password;
        this.occupation = occupation;
        this.image = image;
    }


    protected User(Parcel in) {
        user_name = in.readString();
        contact = in.readString();
        emailId = in.readString();
        password = in.readString();
        occupation = in.readString();
        image = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_name);
        dest.writeString(contact);
        dest.writeString(emailId);
        dest.writeString(password);
        dest.writeString(occupation);
        dest.writeString(image);
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_name='" + user_name + '\'' +
                ", contact='" + contact + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", occupation='" + occupation + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
