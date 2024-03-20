package org.acra.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.acra.collections.ImmutableSet;

public final class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    private final Map<K, V> mMap;

    public ImmutableMap(Map<K, V> map) {
        this.mMap = new HashMap(map);
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(Object obj) {
        return this.mMap.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.mMap.containsValue(obj);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = this.mMap.entrySet();
        ImmutableSet.Builder builder = new ImmutableSet.Builder();
        for (Map.Entry<K, V> immutableEntryWrapper : entrySet) {
            builder.add(new ImmutableEntryWrapper(immutableEntryWrapper));
        }
        return builder.build();
    }

    public V get(Object obj) {
        return this.mMap.get(obj);
    }

    public boolean isEmpty() {
        return this.mMap.isEmpty();
    }

    public Set<K> keySet() {
        return new ImmutableSet(this.mMap.keySet());
    }

    public V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    public V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.mMap.size();
    }

    public Collection<V> values() {
        return new ImmutableList(this.mMap.values());
    }

    private static class ImmutableEntryWrapper<K, V> implements Map.Entry<K, V> {
        private final Map.Entry<K, V> mEntry;

        ImmutableEntryWrapper(Map.Entry<K, V> entry) {
            this.mEntry = entry;
        }

        public K getKey() {
            return this.mEntry.getKey();
        }

        public V getValue() {
            return this.mEntry.getValue();
        }

        public V setValue(Object obj) {
            throw new UnsupportedOperationException();
        }
    }
}
