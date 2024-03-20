package com.google.android.gms.common;

import android.util.Log;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import java.util.concurrent.Callable;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
class zzm {
    private static final zzm zzab = new zzm(true, (String) null, (Throwable) null);
    private final Throwable cause;
    final boolean zzac;
    private final String zzad;

    zzm(boolean z, @Nullable String str, @Nullable Throwable th) {
        this.zzac = z;
        this.zzad = str;
        this.cause = th;
    }

    static zzm zze() {
        return zzab;
    }

    static zzm zza(Callable<String> callable) {
        return new zzo(callable);
    }

    static zzm zzb(String str) {
        return new zzm(false, str, (Throwable) null);
    }

    static zzm zza(String str, Throwable th) {
        return new zzm(false, str, th);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public String getErrorMessage() {
        return this.zzad;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        if (!this.zzac && Log.isLoggable("GoogleCertificatesRslt", 3)) {
            if (this.cause != null) {
                Log.d("GoogleCertificatesRslt", getErrorMessage(), this.cause);
            } else {
                Log.d("GoogleCertificatesRslt", getErrorMessage());
            }
        }
    }

    static String zza(String str, zze zze, boolean z, boolean z2) {
        return String.format("%s: pkg=%s, sha1=%s, atk=%s, ver=%s", new Object[]{z2 ? "debug cert rejected" : "not whitelisted", str, Hex.zza(AndroidUtilsLight.zzi("SHA-1").digest(zze.getBytes())), Boolean.valueOf(z), "12451009.false"});
    }
}
