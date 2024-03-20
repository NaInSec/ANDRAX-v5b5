package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zze<TResult, TContinuationResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TContinuationResult>, zzq<TResult> {
    private final Executor zzd;
    /* access modifiers changed from: private */
    public final Continuation<TResult, Task<TContinuationResult>> zze;
    /* access modifiers changed from: private */
    public final zzu<TContinuationResult> zzf;

    public zze(Executor executor, Continuation<TResult, Task<TContinuationResult>> continuation, zzu<TContinuationResult> zzu) {
        this.zzd = executor;
        this.zze = continuation;
        this.zzf = zzu;
    }

    public final void onComplete(Task<TResult> task) {
        this.zzd.execute(new zzf(this, task));
    }

    public final void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzf.setResult(tcontinuationresult);
    }

    public final void onFailure(Exception exc) {
        this.zzf.setException(exc);
    }

    public final void onCanceled() {
        this.zzf.zza();
    }

    public final void cancel() {
        throw new UnsupportedOperationException();
    }
}
