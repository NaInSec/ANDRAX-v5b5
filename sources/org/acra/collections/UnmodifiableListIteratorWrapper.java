package org.acra.collections;

import java.util.ListIterator;

class UnmodifiableListIteratorWrapper<E> implements ListIterator<E> {
    private final ListIterator<E> mIterator;

    UnmodifiableListIteratorWrapper(ListIterator<E> listIterator) {
        this.mIterator = listIterator;
    }

    public void add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean hasNext() {
        return this.mIterator.hasNext();
    }

    public boolean hasPrevious() {
        return this.mIterator.hasPrevious();
    }

    public E next() {
        return this.mIterator.next();
    }

    public int nextIndex() {
        return this.mIterator.nextIndex();
    }

    public E previous() {
        return this.mIterator.previous();
    }

    public int previousIndex() {
        return this.mIterator.previousIndex();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void set(E e) {
        throw new UnsupportedOperationException();
    }
}
