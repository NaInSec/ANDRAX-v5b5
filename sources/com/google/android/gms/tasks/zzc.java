package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzc<TResult, TContinuationResult> implements zzq<TResult> {
    private final Executor zzd;
    /* access modifiers changed from: private */
    public final Continuation<TResult, TContinuationResult> zze;
    /* access modifiers changed from: private */
    public final zzu<TContinuationResult> zzf;

    public zzc(Executor executor, Continuation<TResult, TContinuationResult> continuation, zzu<TContinuationResult> zzu) {
        this.zzd = executor;
        this.zze = continuation;
        this.zzf = zzu;
    }

    public final void onComplete(Task<TResult> task) {
        this.zzd.execute(new zzd(this, task));
    }

    public final void cancel() {
        throw new UnsupportedOperationException();
    }
}
