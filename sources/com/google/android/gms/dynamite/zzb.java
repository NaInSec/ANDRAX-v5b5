package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzb implements DynamiteModule.VersionPolicy {
    zzb() {
    }

    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zza) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.zzb zzb = new DynamiteModule.VersionPolicy.zzb();
        zzb.zzir = zza.zza(context, str, true);
        if (zzb.zzir != 0) {
            zzb.zzis = 1;
        } else {
            zzb.zziq = zza.getLocalVersion(context, str);
            if (zzb.zziq != 0) {
                zzb.zzis = -1;
            }
        }
        return zzb;
    }
}
