package com.google.firebase.iid;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.firebase_messaging.zza;

final class zzau extends zza {
    private final /* synthetic */ zzat zzcw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzau(zzat zzat, Looper looper) {
        super(looper);
        this.zzcw = zzat;
    }

    public final void handleMessage(Message message) {
        this.zzcw.zzb(message);
    }
}
