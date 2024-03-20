package com.onesignal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

/* compiled from: BundleCompat */
class BundleCompatBundle implements BundleCompat<Bundle> {
    private Bundle mBundle;

    BundleCompatBundle() {
        this.mBundle = new Bundle();
    }

    BundleCompatBundle(Bundle bundle) {
        this.mBundle = bundle;
    }

    BundleCompatBundle(Intent intent) {
        this.mBundle = intent.getExtras();
    }

    public void putString(String str, String str2) {
        this.mBundle.putString(str, str2);
    }

    public void putInt(String str, Integer num) {
        this.mBundle.putInt(str, num.intValue());
    }

    public void putLong(String str, Long l) {
        this.mBundle.putLong(str, l.longValue());
    }

    public void putBoolean(String str, Boolean bool) {
        this.mBundle.putBoolean(str, bool.booleanValue());
    }

    public String getString(String str) {
        return this.mBundle.getString(str);
    }

    public Integer getInt(String str) {
        return Integer.valueOf(this.mBundle.getInt(str));
    }

    public Long getLong(String str) {
        return Long.valueOf(this.mBundle.getLong(str));
    }

    public boolean getBoolean(String str) {
        return this.mBundle.getBoolean(str);
    }

    public boolean containsKey(String str) {
        return this.mBundle.containsKey(str);
    }

    public Bundle getBundle() {
        return this.mBundle;
    }

    public void setBundle(Parcelable parcelable) {
        this.mBundle = (Bundle) parcelable;
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mBundle.getBoolean(str, z);
    }
}
