package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR = new zaa();
    private final int zale;
    private final String zapi;
    private final int zapj;

    public FavaDiagnosticsEntity(int i, String str, int i2) {
        this.zale = i;
        this.zapi = str;
        this.zapj = i2;
    }

    public FavaDiagnosticsEntity(String str, int i) {
        this.zale = 1;
        this.zapi = str;
        this.zapj = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeString(parcel, 2, this.zapi, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zapj);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
