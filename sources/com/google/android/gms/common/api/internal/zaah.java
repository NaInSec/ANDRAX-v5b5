package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;

public final class zaah implements zabd {
    /* access modifiers changed from: private */
    public final zabe zafs;
    private boolean zaft = false;

    public zaah(zabe zabe) {
        this.zafs = zabe;
    }

    public final void begin() {
    }

    public final void onConnected(Bundle bundle) {
    }

    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        return execute(t);
    }

    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        try {
            this.zafs.zaed.zahe.zab(t);
            zaaw zaaw = this.zafs.zaed;
            Api.Client client = zaaw.zagy.get(t.getClientKey());
            Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
            if (client.isConnected() || !this.zafs.zaho.containsKey(t.getClientKey())) {
                boolean z = client instanceof SimpleClientAdapter;
                Api.AnyClient anyClient = client;
                if (z) {
                    anyClient = ((SimpleClientAdapter) client).getClient();
                }
                t.run(anyClient);
                return t;
            }
            t.setFailedResult(new Status(17));
            return t;
        } catch (DeadObjectException unused) {
            this.zafs.zaa((zabf) new zaai(this, this));
        }
    }

    public final boolean disconnect() {
        if (this.zaft) {
            return false;
        }
        if (this.zafs.zaed.zaax()) {
            this.zaft = true;
            for (zacm zabv : this.zafs.zaed.zahd) {
                zabv.zabv();
            }
            return false;
        }
        this.zafs.zaf((ConnectionResult) null);
        return true;
    }

    public final void connect() {
        if (this.zaft) {
            this.zaft = false;
            this.zafs.zaa((zabf) new zaaj(this, this));
        }
    }

    public final void onConnectionSuspended(int i) {
        this.zafs.zaf((ConnectionResult) null);
        this.zafs.zahs.zab(i, this.zaft);
    }

    /* access modifiers changed from: package-private */
    public final void zaam() {
        if (this.zaft) {
            this.zaft = false;
            this.zafs.zaed.zahe.release();
            disconnect();
        }
    }
}
