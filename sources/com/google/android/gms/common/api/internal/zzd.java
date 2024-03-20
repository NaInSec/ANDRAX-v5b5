package com.google.android.gms.common.api.internal;

final class zzd implements Runnable {
    private final /* synthetic */ LifecycleCallback zzbh;
    private final /* synthetic */ String zzbi;
    private final /* synthetic */ zzc zzbk;

    zzd(zzc zzc, LifecycleCallback lifecycleCallback, String str) {
        this.zzbk = zzc;
        this.zzbh = lifecycleCallback;
        this.zzbi = str;
    }

    public final void run() {
        if (this.zzbk.zzbf > 0) {
            this.zzbh.onCreate(this.zzbk.zzbg != null ? this.zzbk.zzbg.getBundle(this.zzbi) : null);
        }
        if (this.zzbk.zzbf >= 2) {
            this.zzbh.onStart();
        }
        if (this.zzbk.zzbf >= 3) {
            this.zzbh.onResume();
        }
        if (this.zzbk.zzbf >= 4) {
            this.zzbh.onStop();
        }
        if (this.zzbk.zzbf >= 5) {
            this.zzbh.onDestroy();
        }
    }
}
