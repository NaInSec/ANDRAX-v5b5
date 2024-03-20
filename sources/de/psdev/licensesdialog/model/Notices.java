package de.psdev.licensesdialog.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Notices implements Parcelable {
    public static Parcelable.Creator<Notices> CREATOR = new Parcelable.Creator<Notices>() {
        public Notices createFromParcel(Parcel parcel) {
            return new Notices(parcel);
        }

        public Notices[] newArray(int i) {
            return new Notices[i];
        }
    };
    private final List<Notice> mNotices = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public Notices() {
    }

    public void addNotice(Notice notice) {
        this.mNotices.add(notice);
    }

    public List<Notice> getNotices() {
        return this.mNotices;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mNotices);
    }

    protected Notices(Parcel parcel) {
        parcel.readList(this.mNotices, Notice.class.getClassLoader());
    }
}
