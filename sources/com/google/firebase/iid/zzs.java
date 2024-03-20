package com.google.firebase.iid;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzs implements Runnable {
    private final zzr zzbk;
    private final Bundle zzbl;
    private final TaskCompletionSource zzbm;

    zzs(zzr zzr, Bundle bundle, TaskCompletionSource taskCompletionSource) {
        this.zzbk = zzr;
        this.zzbl = bundle;
        this.zzbm = taskCompletionSource;
    }

    public final void run() {
        this.zzbk.zza(this.zzbl, this.zzbm);
    }
}
