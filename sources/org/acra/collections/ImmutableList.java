package org.acra.collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class ImmutableList<E> implements List<E>, Serializable {
    private final List<E> mList;

    @SafeVarargs
    public ImmutableList(E... eArr) {
        this(Arrays.asList(eArr));
    }

    public ImmutableList(Collection<E> collection) {
        this.mList = new ArrayList(collection);
    }

    public void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        return this.mList.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.mList.containsAll(collection);
    }

    public E get(int i) {
        return this.mList.get(i);
    }

    public int indexOf(Object obj) {
        return this.mList.indexOf(obj);
    }

    public boolean isEmpty() {
        return this.mList.isEmpty();
    }

    public Iterator<E> iterator() {
        return new UnmodifiableIteratorWrapper(this.mList.iterator());
    }

    public int lastIndexOf(Object obj) {
        return this.mList.lastIndexOf(obj);
    }

    public ListIterator<E> listIterator() {
        return new UnmodifiableListIteratorWrapper(this.mList.listIterator());
    }

    public ListIterator<E> listIterator(int i) {
        return new UnmodifiableListIteratorWrapper(this.mList.listIterator(i));
    }

    public E remove(int i) {
        throw new UnsupportedOperationException();
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

    public E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.mList.size();
    }

    public List<E> subList(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray() {
        return this.mList.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.mList.toArray(tArr);
    }
}
