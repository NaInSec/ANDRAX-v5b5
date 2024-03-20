package com.google.firebase.components;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
final /* synthetic */ class zzc implements ComponentFactory {
    private final Object zza;

    private zzc(Object obj) {
        this.zza = obj;
    }

    public static ComponentFactory zza(Object obj) {
        return new zzc(obj);
    }

    public final Object create(ComponentContainer componentContainer) {
        return Component.zza(this.zza);
    }
}
