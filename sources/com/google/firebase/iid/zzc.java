package com.google.firebase.iid;

import android.content.Intent;

final class zzc implements Runnable {
    private final /* synthetic */ Intent zzm;
    private final /* synthetic */ Intent zzn;
    private final /* synthetic */ zzb zzo;

    zzc(zzb zzb, Intent intent, Intent intent2) {
        this.zzo = zzb;
        this.zzm = intent;
        this.zzn = intent2;
    }

    public final void run() {
        this.zzo.zzd(this.zzm);
        this.zzo.zza(this.zzn);
    }
}
