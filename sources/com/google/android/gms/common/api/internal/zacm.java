package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

public final class zacm<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    /* access modifiers changed from: private */
    public final Object zadn = new Object();
    /* access modifiers changed from: private */
    public final WeakReference<GoogleApiClient> zadp;
    /* access modifiers changed from: private */
    public ResultTransform<? super R, ? extends Result> zakn = null;
    /* access modifiers changed from: private */
    public zacm<? extends Result> zako = null;
    private volatile ResultCallbacks<? super R> zakp = null;
    private PendingResult<R> zakq = null;
    private Status zakr = null;
    /* access modifiers changed from: private */
    public final zaco zaks;
    private boolean zakt = false;

    public zacm(WeakReference<GoogleApiClient> weakReference) {
        Preconditions.checkNotNull(weakReference, "GoogleApiClient reference must not be null");
        this.zadp = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.zadp.get();
        this.zaks = new zaco(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    public final <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        zacm<? extends Result> zacm;
        synchronized (this.zadn) {
            boolean z = true;
            Preconditions.checkState(this.zakn == null, "Cannot call then() twice.");
            if (this.zakp != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zakn = resultTransform;
            zacm = new zacm<>(this.zadp);
            this.zako = zacm;
            zabu();
        }
        return zacm;
    }

    public final void andFinally(ResultCallbacks<? super R> resultCallbacks) {
        synchronized (this.zadn) {
            boolean z = true;
            Preconditions.checkState(this.zakp == null, "Cannot call andFinally() twice.");
            if (this.zakn != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zakp = resultCallbacks;
            zabu();
        }
    }

    public final void onResult(R r) {
        synchronized (this.zadn) {
            if (!r.getStatus().isSuccess()) {
                zad(r.getStatus());
                zab(r);
            } else if (this.zakn != null) {
                zacc.zabb().submit(new zacn(this, r));
            } else if (zabw()) {
                this.zakp.onSuccess(r);
            }
        }
    }

    public final void zaa(PendingResult<?> pendingResult) {
        synchronized (this.zadn) {
            this.zakq = pendingResult;
            zabu();
        }
    }

    private final void zabu() {
        if (this.zakn != null || this.zakp != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zadp.get();
            if (!(this.zakt || this.zakn == null || googleApiClient == null)) {
                googleApiClient.zaa(this);
                this.zakt = true;
            }
            Status status = this.zakr;
            if (status != null) {
                zae(status);
                return;
            }
            PendingResult<R> pendingResult = this.zakq;
            if (pendingResult != null) {
                pendingResult.setResultCallback(this);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zad(Status status) {
        synchronized (this.zadn) {
            this.zakr = status;
            zae(this.zakr);
        }
    }

    private final void zae(Status status) {
        synchronized (this.zadn) {
            if (this.zakn != null) {
                Status onFailure = this.zakn.onFailure(status);
                Preconditions.checkNotNull(onFailure, "onFailure must not return null");
                this.zako.zad(onFailure);
            } else if (zabw()) {
                this.zakp.onFailure(status);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zabv() {
        this.zakp = null;
    }

    private final boolean zabw() {
        return (this.zakp == null || ((GoogleApiClient) this.zadp.get()) == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public static void zab(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                sb.append("Unable to release ");
                sb.append(valueOf);
                Log.w("TransformedResultImpl", sb.toString(), e);
            }
        }
    }
}
