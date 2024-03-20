package com.google.firebase.components;

import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> zza;

    public DependencyCycleException(List<Component<?>> list) {
        super("Dependency cycle detected: " + Arrays.toString(list.toArray()));
        this.zza = list;
    }

    public List<Component<?>> getComponentsInCycle() {
        return this.zza;
    }
}
