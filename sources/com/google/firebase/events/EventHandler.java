package com.google.firebase.events;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public interface EventHandler<T> {
    void handle(Event<T> event);
}
