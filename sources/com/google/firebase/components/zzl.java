package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
final class zzl extends zza {
    private final Set<Class<?>> zza;
    private final Set<Class<?>> zzb;
    private final Set<Class<?>> zzc;
    private final ComponentContainer zzd;

    zzl(Component<?> component, ComponentContainer componentContainer) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (Dependency next : component.zzb()) {
            if (next.zzc()) {
                hashSet.add(next.zza());
            } else {
                hashSet2.add(next.zza());
            }
        }
        if (!component.zzd().isEmpty()) {
            hashSet.add(Publisher.class);
        }
        this.zza = Collections.unmodifiableSet(hashSet);
        this.zzb = Collections.unmodifiableSet(hashSet2);
        this.zzc = component.zzd();
        this.zzd = componentContainer;
    }

    public final <T> T get(Class<T> cls) {
        if (this.zza.contains(cls)) {
            T t = this.zzd.get(cls);
            if (!cls.equals(Publisher.class)) {
                return t;
            }
            return new zza(this.zzc, (Publisher) t);
        }
        throw new IllegalArgumentException(String.format("Requesting %s is not allowed.", new Object[]{cls}));
    }

    public final <T> Provider<T> getProvider(Class<T> cls) {
        if (this.zzb.contains(cls)) {
            return this.zzd.getProvider(cls);
        }
        throw new IllegalArgumentException(String.format("Requesting Provider<%s> is not allowed.", new Object[]{cls}));
    }

    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    static class zza implements Publisher {
        private final Set<Class<?>> zza;
        private final Publisher zzb;

        public zza(Set<Class<?>> set, Publisher publisher) {
            this.zza = set;
            this.zzb = publisher;
        }

        public final void publish(Event<?> event) {
            if (this.zza.contains(event.getType())) {
                this.zzb.publish(event);
            } else {
                throw new IllegalArgumentException(String.format("Attempting to publish an undeclared event %s.", new Object[]{event}));
            }
        }
    }
}
