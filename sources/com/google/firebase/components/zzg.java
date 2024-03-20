package com.google.firebase.components;

import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
final class zzg {
    private final Component<?> zza;
    private final Set<zzg> zzb = new HashSet();
    private final Set<zzg> zzc = new HashSet();

    zzg(Component<?> component) {
        this.zza = component;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzg zzg) {
        this.zzb.add(zzg);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzg zzg) {
        this.zzc.add(zzg);
    }

    /* access modifiers changed from: package-private */
    public final Set<zzg> zza() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzg zzg) {
        this.zzc.remove(zzg);
    }

    /* access modifiers changed from: package-private */
    public final Component<?> zzb() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc() {
        return this.zzc.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd() {
        return this.zzb.isEmpty();
    }
}
