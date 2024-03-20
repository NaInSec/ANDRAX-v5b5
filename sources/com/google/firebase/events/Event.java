package com.google.firebase.events;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class Event<T> {
    private final Class<T> zza;
    private final T zzb;

    public Event(Class<T> cls, T t) {
        this.zza = (Class) Preconditions.checkNotNull(cls);
        this.zzb = Preconditions.checkNotNull(t);
    }

    public Class<T> getType() {
        return this.zza;
    }

    public T getPayload() {
        return this.zzb;
    }

    public String toString() {
        return String.format("Event{type: %s, payload: %s}", new Object[]{this.zza, this.zzb});
    }
}
