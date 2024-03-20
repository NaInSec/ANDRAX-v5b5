package org.apache.commons.lang3.concurrent;

public abstract class LazyInitializer<T> implements ConcurrentInitializer<T> {
    private static final Object NO_INIT = new Object();
    private volatile T object = NO_INIT;

    /* access modifiers changed from: protected */
    public abstract T initialize() throws ConcurrentException;

    public T get() throws ConcurrentException {
        T t = this.object;
        if (t == NO_INIT) {
            synchronized (this) {
                t = this.object;
                if (t == NO_INIT) {
                    t = initialize();
                    this.object = t;
                }
            }
        }
        return t;
    }
}
