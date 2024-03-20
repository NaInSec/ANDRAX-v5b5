package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zze implements DynamiteModule.VersionPolicy {
    zze() {
    }

    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zza) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.zzb zzb = new DynamiteModule.VersionPolicy.zzb();
        zzb.zziq = zza.getLocalVersion(context, str);
        if (zzb.zziq != 0) {
            zzb.zzir = zza.zza(context, str, false);
        } else {
            zzb.zzir = zza.zza(context, str, true);
        }
        if (zzb.zziq == 0 && zzb.zzir == 0) {
            zzb.zzis = 0;
        } else if (zzb.zziq >= zzb.zzir) {
            zzb.zzis = -1;
        } else {
            zzb.zzis = 1;
        }
        return zzb;
    }
}
