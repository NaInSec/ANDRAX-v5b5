package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zah extends zad<Boolean> {
    private final ListenerHolder.ListenerKey<?> zacs;

    public zah(ListenerHolder.ListenerKey<?> listenerKey, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zacs = listenerKey;
    }

    public final /* bridge */ /* synthetic */ void zaa(zaab zaab, boolean z) {
    }

    public final void zad(GoogleApiManager.zaa<?> zaa) throws RemoteException {
        zabw remove = zaa.zabk().remove(this.zacs);
        if (remove != null) {
            remove.zajx.unregisterListener(zaa.zaab(), this.zacm);
            remove.zajw.clearListener();
            return;
        }
        this.zacm.trySetResult(false);
    }

    public final Feature[] zab(GoogleApiManager.zaa<?> zaa) {
        zabw zabw = zaa.zabk().get(this.zacs);
        if (zabw == null) {
            return null;
        }
        return zabw.zajw.getRequiredFeatures();
    }

    public final boolean zac(GoogleApiManager.zaa<?> zaa) {
        zabw zabw = zaa.zabk().get(this.zacs);
        return zabw != null && zabw.zajw.shouldAutoResolveMissingFeatures();
    }

    public final /* bridge */ /* synthetic */ void zaa(RuntimeException runtimeException) {
        super.zaa(runtimeException);
    }

    public final /* bridge */ /* synthetic */ void zaa(Status status) {
        super.zaa(status);
    }
}
