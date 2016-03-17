package ua.com.kistudio.parcealable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Вiталя on 15.03.2016.
 */
public class MyMail implements Parcelable {

    private String mTitle;
    private String mFrom;
    private String mTo;
    private String mBody;

    public MyMail(String mTitle, String mFrom, String mTo, String mBody) {
        this.mTitle = mTitle;
        this.mFrom = mFrom;
        this.mTo = mTo;
        this.mBody = mBody;
    }

    protected MyMail(Parcel in) {

//TODO!!!!!!
        mTitle = in.readString();
        mFrom = in.readString();
        mTo = in.readString();
        mBody = in.readString();

    }


    public static final Creator<MyMail> CREATOR = new Creator<MyMail>() {
        @Override
        public MyMail createFromParcel(Parcel in) {
            return new MyMail(in);
        }

        @Override
        public MyMail[] newArray(int size) {
            return new MyMail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mFrom);
        dest.writeString(mTo);
        dest.writeString(mBody);
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmFrom() {
        return mFrom;
    }

    public String getmTo() {
        return mTo;
    }

    public String getmBody() {
        return mBody;
    }
}
