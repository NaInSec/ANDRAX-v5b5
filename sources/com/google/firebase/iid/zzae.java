package com.google.firebase.iid;

import android.os.Handler;
import android.os.Message;

final /* synthetic */ class zzae implements Handler.Callback {
    private final zzad zzcc;

    zzae(zzad zzad) {
        this.zzcc = zzad;
    }

    public final boolean handleMessage(Message message) {
        return this.zzcc.zza(message);
    }
}
