package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzk implements Parcelable.Creator<zzj> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 50;
        long j2 = Long.MAX_VALUE;
        boolean z = true;
        float f = 0.0f;
        int i = Integer.MAX_VALUE;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                z = SafeParcelReader.readBoolean(parcel2, readHeader);
            } else if (fieldId == 2) {
                j = SafeParcelReader.readLong(parcel2, readHeader);
            } else if (fieldId == 3) {
                f = SafeParcelReader.readFloat(parcel2, readHeader);
            } else if (fieldId == 4) {
                j2 = SafeParcelReader.readLong(parcel2, readHeader);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel2, readHeader);
            } else {
                i = SafeParcelReader.readInt(parcel2, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzj(z, j, f, j2, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzj[i];
    }
}
