package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zzdb = new Object();
    private static ClassLoader zzdc = null;
    private static Integer zzdd = null;
    private boolean zzde = false;

    /* access modifiers changed from: protected */
    public abstract boolean prepareForClientVersion(int i);

    private static ClassLoader zzp() {
        synchronized (zzdb) {
        }
        return null;
    }

    protected static Integer getUnparcelClientVersion() {
        synchronized (zzdb) {
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean shouldDowngrade() {
        return this.zzde;
    }

    public void setShouldDowngrade(boolean z) {
        this.zzde = z;
    }

    protected static boolean canUnparcelSafely(String str) {
        zzp();
        return true;
    }
}
