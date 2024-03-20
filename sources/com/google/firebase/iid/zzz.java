package com.google.firebase.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import java.security.KeyPair;

final class zzz {
    private final KeyPair zzbr;
    /* access modifiers changed from: private */
    public final long zzbs;

    zzz(KeyPair keyPair, long j) {
        this.zzbr = keyPair;
        this.zzbs = j;
    }

    /* access modifiers changed from: package-private */
    public final KeyPair getKeyPair() {
        return this.zzbr;
    }

    /* access modifiers changed from: package-private */
    public final long getCreationTime() {
        return this.zzbs;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzz)) {
            return false;
        }
        zzz zzz = (zzz) obj;
        if (this.zzbs != zzz.zzbs || !this.zzbr.getPublic().equals(zzz.zzbr.getPublic()) || !this.zzbr.getPrivate().equals(zzz.zzbr.getPrivate())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbr.getPublic(), this.zzbr.getPrivate(), Long.valueOf(this.zzbs));
    }

    /* access modifiers changed from: private */
    public final String zzv() {
        return Base64.encodeToString(this.zzbr.getPublic().getEncoded(), 11);
    }

    /* access modifiers changed from: private */
    public final String zzw() {
        return Base64.encodeToString(this.zzbr.getPrivate().getEncoded(), 11);
    }
}
