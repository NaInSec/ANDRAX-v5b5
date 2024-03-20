package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import com.bumptech.glide.util.Util;
import java.util.Queue;

abstract class BaseKeyPool<T extends Poolable> {
    private static final int MAX_SIZE = 20;
    private final Queue<T> keyPool = Util.createQueue(20);

    /* access modifiers changed from: protected */
    public abstract T create();

    BaseKeyPool() {
    }

    /* access modifiers changed from: protected */
    public T get() {
        T t = (Poolable) this.keyPool.poll();
        return t == null ? create() : t;
    }

    public void offer(T t) {
        if (this.keyPool.size() < 20) {
            this.keyPool.offer(t);
        }
    }
}
