package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new zza();
    private IBinder zzcy;

    public BinderWrapper() {
        this.zzcy = null;
    }

    public final int describeContents() {
        return 0;
    }

    public BinderWrapper(IBinder iBinder) {
        this.zzcy = null;
        this.zzcy = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.zzcy = null;
        this.zzcy = parcel.readStrongBinder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.zzcy);
    }

    /* synthetic */ BinderWrapper(Parcel parcel, zza zza) {
        this(parcel);
    }
}
