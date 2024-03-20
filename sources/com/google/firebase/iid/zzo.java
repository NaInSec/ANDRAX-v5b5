package com.google.firebase.iid;

import com.google.android.gms.tasks.Task;

final /* synthetic */ class zzo implements zzas {
    private final FirebaseInstanceId zzat;
    private final String zzau;
    private final String zzav;
    private final String zzax;
    private final String zzay;

    zzo(FirebaseInstanceId firebaseInstanceId, String str, String str2, String str3, String str4) {
        this.zzat = firebaseInstanceId;
        this.zzau = str;
        this.zzav = str2;
        this.zzay = str3;
        this.zzax = str4;
    }

    public final Task zzs() {
        return this.zzat.zza(this.zzau, this.zzav, this.zzay, this.zzax);
    }
}
