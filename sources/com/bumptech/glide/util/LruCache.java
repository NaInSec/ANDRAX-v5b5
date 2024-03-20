package com.bumptech.glide.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<T, Y> {
    private final LinkedHashMap<T, Y> cache = new LinkedHashMap<>(100, 0.75f, true);
    private int currentSize = 0;
    private final int initialMaxSize;
    private int maxSize;

    /* access modifiers changed from: protected */
    public int getSize(Y y) {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void onItemEvicted(T t, Y y) {
    }

    public LruCache(int i) {
        this.initialMaxSize = i;
        this.maxSize = i;
    }

    public void setSizeMultiplier(float f) {
        if (f >= 0.0f) {
            this.maxSize = Math.round(((float) this.initialMaxSize) * f);
            evict();
            return;
        }
        throw new IllegalArgumentException("Multiplier must be >= 0");
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public int getCurrentSize() {
        return this.currentSize;
    }

    public boolean contains(T t) {
        return this.cache.containsKey(t);
    }

    public Y get(T t) {
        return this.cache.get(t);
    }

    public Y put(T t, Y y) {
        if (getSize(y) >= this.maxSize) {
            onItemEvicted(t, y);
            return null;
        }
        Y put = this.cache.put(t, y);
        if (y != null) {
            this.currentSize += getSize(y);
        }
        if (put != null) {
            this.currentSize -= getSize(put);
        }
        evict();
        return put;
    }

    public Y remove(T t) {
        Y remove = this.cache.remove(t);
        if (remove != null) {
            this.currentSize -= getSize(remove);
        }
        return remove;
    }

    public void clearMemory() {
        trimToSize(0);
    }

    /* access modifiers changed from: protected */
    public void trimToSize(int i) {
        while (this.currentSize > i) {
            Map.Entry next = this.cache.entrySet().iterator().next();
            Object value = next.getValue();
            this.currentSize -= getSize(value);
            Object key = next.getKey();
            this.cache.remove(key);
            onItemEvicted(key, value);
        }
    }

    private void evict() {
        trimToSize(this.maxSize);
    }
}
