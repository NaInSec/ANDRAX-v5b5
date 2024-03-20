package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zabe implements zabs, zar {
    private final Context mContext;
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zacd;
    final zaaw zaed;
    /* access modifiers changed from: private */
    public final Lock zaen;
    private final ClientSettings zaes;
    private final Map<Api<?>, Boolean> zaev;
    private final GoogleApiAvailabilityLight zaex;
    final Map<Api.AnyClientKey<?>, Api.Client> zagy;
    private final Condition zahm;
    private final zabg zahn;
    final Map<Api.AnyClientKey<?>, ConnectionResult> zaho = new HashMap();
    /* access modifiers changed from: private */
    public volatile zabd zahp;
    private ConnectionResult zahq = null;
    int zahr;
    final zabt zahs;

    public zabe(Context context, zaaw zaaw, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList, zabt zabt) {
        this.mContext = context;
        this.zaen = lock;
        this.zaex = googleApiAvailabilityLight;
        this.zagy = map;
        this.zaes = clientSettings;
        this.zaev = map2;
        this.zacd = abstractClientBuilder;
        this.zaed = zaaw;
        this.zahs = zabt;
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            ((zaq) obj).zaa(this);
        }
        this.zahn = new zabg(this, looper);
        this.zahm = lock.newCondition();
        this.zahp = new zaav(this);
    }

    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        return false;
    }

    public final void maybeSignOut() {
    }

    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        t.zau();
        return this.zahp.enqueue(t);
    }

    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        t.zau();
        return this.zahp.execute(t);
    }

    public final void connect() {
        this.zahp.connect();
    }

    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zahm.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, (PendingIntent) null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zahq;
        if (connectionResult != null) {
            return connectionResult;
        }
        return new ConnectionResult(13, (PendingIntent) null);
    }

    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, (PendingIntent) null);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, (PendingIntent) null);
                }
            } else {
                nanos = this.zahm.awaitNanos(nanos);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zahq;
        if (connectionResult != null) {
            return connectionResult;
        }
        return new ConnectionResult(13, (PendingIntent) null);
    }

    public final void disconnect() {
        if (this.zahp.disconnect()) {
            this.zaho.clear();
        }
    }

    public final ConnectionResult getConnectionResult(Api<?> api) {
        Api.AnyClientKey<?> clientKey = api.getClientKey();
        if (!this.zagy.containsKey(clientKey)) {
            return null;
        }
        if (this.zagy.get(clientKey).isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.zaho.containsKey(clientKey)) {
            return this.zaho.get(clientKey);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void zaaz() {
        this.zaen.lock();
        try {
            this.zahp = new zaak(this, this.zaes, this.zaev, this.zaex, this.zacd, this.zaen, this.mContext);
            this.zahp.begin();
            this.zahm.signalAll();
        } finally {
            this.zaen.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zaba() {
        this.zaen.lock();
        try {
            this.zaed.zaaw();
            this.zahp = new zaah(this);
            this.zahp.begin();
            this.zahm.signalAll();
        } finally {
            this.zaen.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zaf(ConnectionResult connectionResult) {
        this.zaen.lock();
        try {
            this.zahq = connectionResult;
            this.zahp = new zaav(this);
            this.zahp.begin();
            this.zahm.signalAll();
        } finally {
            this.zaen.unlock();
        }
    }

    public final boolean isConnected() {
        return this.zahp instanceof zaah;
    }

    public final boolean isConnecting() {
        return this.zahp instanceof zaak;
    }

    public final void zaw() {
        if (isConnected()) {
            ((zaah) this.zahp).zaam();
        }
    }

    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
        this.zaen.lock();
        try {
            this.zahp.zaa(connectionResult, api, z);
        } finally {
            this.zaen.unlock();
        }
    }

    public final void onConnected(Bundle bundle) {
        this.zaen.lock();
        try {
            this.zahp.onConnected(bundle);
        } finally {
            this.zaen.unlock();
        }
    }

    public final void onConnectionSuspended(int i) {
        this.zaen.lock();
        try {
            this.zahp.onConnectionSuspended(i);
        } finally {
            this.zaen.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zaa(zabf zabf) {
        this.zahn.sendMessage(this.zahn.obtainMessage(1, zabf));
    }

    /* access modifiers changed from: package-private */
    public final void zab(RuntimeException runtimeException) {
        this.zahn.sendMessage(this.zahn.obtainMessage(2, runtimeException));
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append(str).append("mState=").println(this.zahp);
        for (Api next : this.zaev.keySet()) {
            printWriter.append(str).append(next.getName()).println(":");
            this.zagy.get(next.getClientKey()).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }
}
