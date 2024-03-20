package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.internal.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzc extends Fragment implements LifecycleFragment {
    private static WeakHashMap<FragmentActivity, WeakReference<zzc>> zzbd = new WeakHashMap<>();
    private Map<String, LifecycleCallback> zzbe = new ArrayMap();
    /* access modifiers changed from: private */
    public int zzbf = 0;
    /* access modifiers changed from: private */
    public Bundle zzbg;

    public static zzc zza(FragmentActivity fragmentActivity) {
        zzc zzc;
        WeakReference weakReference = zzbd.get(fragmentActivity);
        if (weakReference != null && (zzc = (zzc) weakReference.get()) != null) {
            return zzc;
        }
        try {
            zzc zzc2 = (zzc) fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
            if (zzc2 == null || zzc2.isRemoving()) {
                zzc2 = new zzc();
                fragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment) zzc2, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
            }
            zzbd.put(fragmentActivity, new WeakReference(zzc2));
            return zzc2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
        }
    }

    public final <T extends LifecycleCallback> T getCallbackOrNull(String str, Class<T> cls) {
        return (LifecycleCallback) cls.cast(this.zzbe.get(str));
    }

    public final void addCallback(String str, LifecycleCallback lifecycleCallback) {
        if (!this.zzbe.containsKey(str)) {
            this.zzbe.put(str, lifecycleCallback);
            if (this.zzbf > 0) {
                new zze(Looper.getMainLooper()).post(new zzd(this, lifecycleCallback, str));
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 59);
        sb.append("LifecycleCallback with tag ");
        sb.append(str);
        sb.append(" already added to this fragment.");
        throw new IllegalArgumentException(sb.toString());
    }

    public final boolean isCreated() {
        return this.zzbf > 0;
    }

    public final boolean isStarted() {
        return this.zzbf >= 2;
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbf = 1;
        this.zzbg = bundle;
        for (Map.Entry next : this.zzbe.entrySet()) {
            ((LifecycleCallback) next.getValue()).onCreate(bundle != null ? bundle.getBundle((String) next.getKey()) : null);
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzbf = 2;
        for (LifecycleCallback onStart : this.zzbe.values()) {
            onStart.onStart();
        }
    }

    public final void onResume() {
        super.onResume();
        this.zzbf = 3;
        for (LifecycleCallback onResume : this.zzbe.values()) {
            onResume.onResume();
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (LifecycleCallback onActivityResult : this.zzbe.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Map.Entry next : this.zzbe.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((LifecycleCallback) next.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) next.getKey(), bundle2);
            }
        }
    }

    public final void onStop() {
        super.onStop();
        this.zzbf = 4;
        for (LifecycleCallback onStop : this.zzbe.values()) {
            onStop.onStop();
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzbf = 5;
        for (LifecycleCallback onDestroy : this.zzbe.values()) {
            onDestroy.onDestroy();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback dump : this.zzbe.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public final /* synthetic */ Activity getLifecycleActivity() {
        return getActivity();
    }
}
