package org.acra.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public final class BoundedLinkedList<E> extends LinkedList<E> {
    private final int maxSize;

    public BoundedLinkedList(int i) {
        this.maxSize = i;
    }

    public boolean add(E e) {
        if (size() == this.maxSize) {
            removeFirst();
        }
        return super.add(e);
    }

    public void add(int i, E e) {
        if (size() == this.maxSize) {
            removeFirst();
        }
        super.add(i, e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size();
        if (size > this.maxSize) {
            collection = new ArrayList(collection).subList(size - this.maxSize, size);
        }
        int size2 = (size() + collection.size()) - this.maxSize;
        if (size2 > 0) {
            removeRange(0, size2);
        }
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        if (i == size()) {
            return super.addAll(i, collection);
        }
        throw new UnsupportedOperationException();
    }

    public void addFirst(E e) {
        throw new UnsupportedOperationException();
    }

    public void addLast(E e) {
        add(e);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    public boolean offer(E e) {
        return add(e);
    }

    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    public boolean offerLast(E e) {
        return add(e);
    }

    public void push(E e) {
        add(e);
    }
}
