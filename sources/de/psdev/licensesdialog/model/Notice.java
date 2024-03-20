package de.psdev.licensesdialog.model;

import android.os.Parcel;
import android.os.Parcelable;
import de.psdev.licensesdialog.licenses.License;

public class Notice implements Parcelable {
    public static Parcelable.Creator<Notice> CREATOR = new Parcelable.Creator<Notice>() {
        public Notice createFromParcel(Parcel parcel) {
            return new Notice(parcel);
        }

        public Notice[] newArray(int i) {
            return new Notice[i];
        }
    };
    private String mCopyright;
    private License mLicense;
    private String mName;
    private String mUrl;

    public int describeContents() {
        return 0;
    }

    public Notice() {
    }

    public Notice(String str, String str2, String str3, License license) {
        this.mName = str;
        this.mUrl = str2;
        this.mCopyright = str3;
        this.mLicense = license;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public void setCopyright(String str) {
        this.mCopyright = str;
    }

    public void setLicense(License license) {
        this.mLicense = license;
    }

    public String getName() {
        return this.mName;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getCopyright() {
        return this.mCopyright;
    }

    public License getLicense() {
        return this.mLicense;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mCopyright);
        parcel.writeSerializable(this.mLicense);
    }

    private Notice(Parcel parcel) {
        this.mName = parcel.readString();
        this.mUrl = parcel.readString();
        this.mCopyright = parcel.readString();
        this.mLicense = (License) parcel.readSerializable();
    }
}
