package org.acra.collections;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public final class ImmutableSet<E> implements Set<E>, Serializable {
    private static final ImmutableSet<Object> EMPTY = new ImmutableSet<>();
    private final Set<E> mSet;

    public static <T> ImmutableSet<T> empty() {
        return EMPTY;
    }

    private ImmutableSet() {
        this.mSet = Collections.emptySet();
    }

    @SafeVarargs
    public ImmutableSet(E... eArr) {
        this(Arrays.asList(eArr));
    }

    public ImmutableSet(Collection<E> collection) {
        this.mSet = new LinkedHashSet(collection);
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        return this.mSet.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.mSet.containsAll(collection);
    }

    public boolean isEmpty() {
        return this.mSet.isEmpty();
    }

    public Iterator<E> iterator() {
        return new UnmodifiableIteratorWrapper(this.mSet.iterator());
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.mSet.size();
    }

    public Object[] toArray() {
        return this.mSet.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.mSet.toArray(tArr);
    }

    public static final class Builder<E> {
        private final Set<E> mSet = new LinkedHashSet();

        public void add(E e) {
            this.mSet.add(e);
        }

        public ImmutableSet<E> build() {
            return new ImmutableSet<>(this.mSet);
        }
    }
}
