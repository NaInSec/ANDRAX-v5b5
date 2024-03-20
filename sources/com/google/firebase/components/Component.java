package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public final class Component<T> {
    private final Set<Class<? super T>> zza;
    private final Set<Dependency> zzb;
    private final int zzc;
    private final ComponentFactory<T> zzd;
    private final Set<Class<?>> zze;

    static /* synthetic */ Object zza(Object obj) {
        return obj;
    }

    static /* synthetic */ Object zzb(Object obj) {
        return obj;
    }

    /* synthetic */ Component(Set set, Set set2, int i, ComponentFactory componentFactory, Set set3, byte b) {
        this(set, set2, i, componentFactory, set3);
    }

    private Component(Set<Class<? super T>> set, Set<Dependency> set2, int i, ComponentFactory<T> componentFactory, Set<Class<?>> set3) {
        this.zza = Collections.unmodifiableSet(set);
        this.zzb = Collections.unmodifiableSet(set2);
        this.zzc = i;
        this.zzd = componentFactory;
        this.zze = Collections.unmodifiableSet(set3);
    }

    public final Set<Class<? super T>> zza() {
        return this.zza;
    }

    public final Set<Dependency> zzb() {
        return this.zzb;
    }

    public final ComponentFactory<T> zzc() {
        return this.zzd;
    }

    public final Set<Class<?>> zzd() {
        return this.zze;
    }

    public final boolean zze() {
        return this.zzc == 1;
    }

    public final boolean zzf() {
        return this.zzc == 2;
    }

    public final String toString() {
        return "Component<" + Arrays.toString(this.zza.toArray()) + ">{" + this.zzc + ", deps=" + Arrays.toString(this.zzb.toArray()) + "}";
    }

    public static <T> Builder<T> builder(Class<T> cls) {
        return new Builder<>(cls, new Class[0], (byte) 0);
    }

    public static <T> Builder<T> builder(Class<T> cls, Class<? super T>... clsArr) {
        return new Builder<>(cls, clsArr, (byte) 0);
    }

    @Deprecated
    public static <T> Component<T> of(Class<T> cls, T t) {
        return builder(cls).factory(zzb.zza(t)).build();
    }

    @SafeVarargs
    public static <T> Component<T> of(T t, Class<T> cls, Class<? super T>... clsArr) {
        return builder(cls, clsArr).factory(zzc.zza(t)).build();
    }

    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    public static class Builder<T> {
        private final Set<Class<? super T>> zza;
        private final Set<Dependency> zzb;
        private int zzc;
        private ComponentFactory<T> zzd;
        private Set<Class<?>> zze;

        /* synthetic */ Builder(Class cls, Class[] clsArr, byte b) {
            this(cls, clsArr);
        }

        private Builder(Class<T> cls, Class<? super T>... clsArr) {
            this.zza = new HashSet();
            this.zzb = new HashSet();
            this.zzc = 0;
            this.zze = new HashSet();
            Preconditions.checkNotNull(cls, "Null interface");
            this.zza.add(cls);
            for (Class<? super T> checkNotNull : clsArr) {
                Preconditions.checkNotNull(checkNotNull, "Null interface");
            }
            Collections.addAll(this.zza, clsArr);
        }

        public Builder<T> add(Dependency dependency) {
            Preconditions.checkNotNull(dependency, "Null dependency");
            Preconditions.checkArgument(!this.zza.contains(dependency.zza()), "Components are not allowed to depend on interfaces they themselves provide.");
            this.zzb.add(dependency);
            return this;
        }

        public Builder<T> alwaysEager() {
            return zza(1);
        }

        public Builder<T> eagerInDefaultApp() {
            return zza(2);
        }

        public Builder<T> publishes(Class<?> cls) {
            this.zze.add(cls);
            return this;
        }

        private Builder<T> zza(int i) {
            Preconditions.checkState(this.zzc == 0, "Instantiation type has already been set.");
            this.zzc = i;
            return this;
        }

        public Builder<T> factory(ComponentFactory<T> componentFactory) {
            this.zzd = (ComponentFactory) Preconditions.checkNotNull(componentFactory, "Null factory");
            return this;
        }

        public Component<T> build() {
            Preconditions.checkState(this.zzd != null, "Missing required property: factory.");
            return new Component(new HashSet(this.zza), new HashSet(this.zzb), this.zzc, this.zzd, this.zze, (byte) 0);
        }
    }
}
