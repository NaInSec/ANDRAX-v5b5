package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public interface ComponentContainer {
    <T> T get(Class<T> cls);

    <T> Provider<T> getProvider(Class<T> cls);
}
