package com.google.android.gms.common.api.internal;

import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zaag extends GoogleApiClient {
    private final String zafr;

    public zaag(String str) {
        this.zafr = str;
    }

    public boolean hasConnectedApi(Api<?> api) {
        throw new UnsupportedOperationException(this.zafr);
    }

    public ConnectionResult getConnectionResult(Api<?> api) {
        throw new UnsupportedOperationException(this.zafr);
    }

    public void connect() {
        throw new UnsupportedOperationException(this.zafr);
    }

    public ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException(this.zafr);
    }

    public ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException(this.zafr);
    }

    public void disconnect() {
        throw new UnsupportedOperationException(this.zafr);
    }

    public void reconnect() {
        throw new UnsupportedOperationException(this.zafr);
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        throw new UnsupportedOperationException(this.zafr);
    }

    public void stopAutoManage(FragmentActivity fragmentActivity) {
        throw new UnsupportedOperationException(this.zafr);
    }

    public boolean isConnected() {
        throw new UnsupportedOperationException(this.zafr);
    }

    public boolean isConnecting() {
        throw new UnsupportedOperationException(this.zafr);
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        throw new UnsupportedOperationException(this.zafr);
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        throw new UnsupportedOperationException(this.zafr);
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        throw new UnsupportedOperationException(this.zafr);
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        throw new UnsupportedOperationException(this.zafr);
    }

    public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        throw new UnsupportedOperationException(this.zafr);
    }

    public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        throw new UnsupportedOperationException(this.zafr);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        throw new UnsupportedOperationException(this.zafr);
    }
}
