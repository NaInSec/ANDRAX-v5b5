package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class RegisterListenerMethod<A extends Api.AnyClient, L> {
    private final ListenerHolder<L> zajt;
    private final Feature[] zaju;
    private final boolean zajv;

    protected RegisterListenerMethod(ListenerHolder<L> listenerHolder) {
        this.zajt = listenerHolder;
        this.zaju = null;
        this.zajv = false;
    }

    /* access modifiers changed from: protected */
    public abstract void registerListener(A a, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException;

    protected RegisterListenerMethod(ListenerHolder<L> listenerHolder, Feature[] featureArr, boolean z) {
        this.zajt = listenerHolder;
        this.zaju = featureArr;
        this.zajv = z;
    }

    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zajt.getListenerKey();
    }

    public void clearListener() {
        this.zajt.clear();
    }

    public Feature[] getRequiredFeatures() {
        return this.zaju;
    }

    public final boolean shouldAutoResolveMissingFeatures() {
        return this.zajv;
    }
}
