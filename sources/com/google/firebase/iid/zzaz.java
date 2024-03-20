package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import javax.annotation.Nullable;

final class zzaz extends BroadcastReceiver {
    @Nullable
    private zzay zzdk;

    public zzaz(zzay zzay) {
        this.zzdk = zzay;
    }

    public final void zzap() {
        if (FirebaseInstanceId.zzl()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }
        this.zzdk.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public final void onReceive(Context context, Intent intent) {
        zzay zzay = this.zzdk;
        if (zzay != null && zzay.zzao()) {
            if (FirebaseInstanceId.zzl()) {
                Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
            }
            FirebaseInstanceId.zza((Runnable) this.zzdk, 0);
            this.zzdk.getContext().unregisterReceiver(this);
            this.zzdk = null;
        }
    }
}
