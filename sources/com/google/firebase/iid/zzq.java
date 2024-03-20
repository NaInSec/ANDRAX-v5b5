package com.google.firebase.iid;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.iid.FirebaseInstanceId;

final /* synthetic */ class zzq implements EventHandler {
    private final FirebaseInstanceId.zza zzbe;

    zzq(FirebaseInstanceId.zza zza) {
        this.zzbe = zza;
    }

    public final void handle(Event event) {
        FirebaseInstanceId.zza zza = this.zzbe;
        synchronized (zza) {
            if (zza.isEnabled()) {
                FirebaseInstanceId.this.zzg();
            }
        }
    }
}
