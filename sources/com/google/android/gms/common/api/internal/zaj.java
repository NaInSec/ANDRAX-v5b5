package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import org.apache.commons.lang3.StringUtils;

public class zaj extends zal {
    private final SparseArray<zaa> zacv = new SparseArray<>();

    public static zaj zaa(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = getFragment(lifecycleActivity);
        zaj zaj = (zaj) fragment.getCallbackOrNull("AutoManageHelper", zaj.class);
        if (zaj != null) {
            return zaj;
        }
        return new zaj(fragment);
    }

    private class zaa implements GoogleApiClient.OnConnectionFailedListener {
        public final int zacw;
        public final GoogleApiClient zacx;
        public final GoogleApiClient.OnConnectionFailedListener zacy;

        public zaa(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zacw = i;
            this.zacx = googleApiClient;
            this.zacy = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public final void onConnectionFailed(ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("beginFailureResolution for ");
            sb.append(valueOf);
            Log.d("AutoManageHelper", sb.toString());
            zaj.this.zab(connectionResult, this.zacw);
        }
    }

    private zaj(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public final void zaa(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        boolean z = this.zacv.indexOfKey(i) < 0;
        StringBuilder sb = new StringBuilder(54);
        sb.append("Already managing a GoogleApiClient with id ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zam zam = (zam) this.zade.get();
        boolean z2 = this.mStarted;
        String valueOf = String.valueOf(zam);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 49);
        sb2.append("starting AutoManage for client ");
        sb2.append(i);
        sb2.append(StringUtils.SPACE);
        sb2.append(z2);
        sb2.append(StringUtils.SPACE);
        sb2.append(valueOf);
        Log.d("AutoManageHelper", sb2.toString());
        this.zacv.put(i, new zaa(i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && zam == null) {
            String valueOf2 = String.valueOf(googleApiClient);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 11);
            sb3.append("connecting ");
            sb3.append(valueOf2);
            Log.d("AutoManageHelper", sb3.toString());
            googleApiClient.connect();
        }
    }

    public final void zaa(int i) {
        zaa zaa2 = this.zacv.get(i);
        this.zacv.remove(i);
        if (zaa2 != null) {
            zaa2.zacx.unregisterConnectionFailedListener(zaa2);
            zaa2.zacx.disconnect();
        }
    }

    public void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.zacv);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("onStart ");
        sb.append(z);
        sb.append(StringUtils.SPACE);
        sb.append(valueOf);
        Log.d("AutoManageHelper", sb.toString());
        if (this.zade.get() == null) {
            for (int i = 0; i < this.zacv.size(); i++) {
                zaa zab = zab(i);
                if (zab != null) {
                    zab.zacx.connect();
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        for (int i = 0; i < this.zacv.size(); i++) {
            zaa zab = zab(i);
            if (zab != null) {
                zab.zacx.disconnect();
            }
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zacv.size(); i++) {
            zaa zab = zab(i);
            if (zab != null) {
                printWriter.append(str).append("GoogleApiClient #").print(zab.zacw);
                printWriter.println(":");
                zab.zacx.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zaa(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaa zaa2 = this.zacv.get(i);
        if (zaa2 != null) {
            zaa(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zaa2.zacy;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zao() {
        for (int i = 0; i < this.zacv.size(); i++) {
            zaa zab = zab(i);
            if (zab != null) {
                zab.zacx.connect();
            }
        }
    }

    private final zaa zab(int i) {
        if (this.zacv.size() <= i) {
            return null;
        }
        SparseArray<zaa> sparseArray = this.zacv;
        return sparseArray.get(sparseArray.keyAt(i));
    }
}
