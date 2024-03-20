package com.google.android.gms.common.api.internal;

final class zzb implements Runnable {
    private final /* synthetic */ LifecycleCallback zzbh;
    private final /* synthetic */ String zzbi;
    private final /* synthetic */ zza zzbj;

    zzb(zza zza, LifecycleCallback lifecycleCallback, String str) {
        this.zzbj = zza;
        this.zzbh = lifecycleCallback;
        this.zzbi = str;
    }

    public final void run() {
        if (this.zzbj.zzbf > 0) {
            this.zzbh.onCreate(this.zzbj.zzbg != null ? this.zzbj.zzbg.getBundle(this.zzbi) : null);
        }
        if (this.zzbj.zzbf >= 2) {
            this.zzbh.onStart();
        }
        if (this.zzbj.zzbf >= 3) {
            this.zzbh.onResume();
        }
        if (this.zzbj.zzbf >= 4) {
            this.zzbh.onStop();
        }
        if (this.zzbj.zzbf >= 5) {
            this.zzbh.onDestroy();
        }
    }
}
