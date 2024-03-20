package com.google.firebase.components;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
final /* synthetic */ class zzb implements ComponentFactory {
    private final Object zza;

    private zzb(Object obj) {
        this.zza = obj;
    }

    public static ComponentFactory zza(Object obj) {
        return new zzb(obj);
    }

    public final Object create(ComponentContainer componentContainer) {
        return Component.zzb(this.zza);
    }
}
