package com.github.wrdlbrnft.modularadapter.itemmanager;

import java.util.Collection;

public interface ModifiableItemManager<T> extends ItemManager<T> {

    public interface Transaction<T> {
        Transaction<T> add(T t);

        Transaction<T> add(Collection<T> collection);

        void commit();

        Transaction<T> remove(T t);

        Transaction<T> remove(Collection<T> collection);

        Transaction<T> removeAll();

        Transaction<T> replaceAll(Collection<T> collection);
    }

    Transaction<T> newTransaction();
}
