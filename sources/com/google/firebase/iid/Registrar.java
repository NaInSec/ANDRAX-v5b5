package com.google.firebase.iid;

import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import java.util.Arrays;
import java.util.List;

public final class Registrar implements ComponentRegistrar {
    public final List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebaseInstanceId.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.required(Subscriber.class)).factory(zzao.zzcm).alwaysEager().build(), Component.builder(FirebaseInstanceIdInternal.class).add(Dependency.required(FirebaseInstanceId.class)).factory(zzap.zzcm).build()});
    }

    private static class zza implements FirebaseInstanceIdInternal {
        private final FirebaseInstanceId zzcn;

        public zza(FirebaseInstanceId firebaseInstanceId) {
            this.zzcn = firebaseInstanceId;
        }

        public final String getId() {
            return this.zzcn.getId();
        }

        public final String getToken() {
            return this.zzcn.getToken();
        }
    }
}
