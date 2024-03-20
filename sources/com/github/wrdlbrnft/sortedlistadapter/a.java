package com.github.wrdlbrnft.sortedlistadapter;

import com.github.wrdlbrnft.modularadapter.itemmanager.ModifiableItemManager;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter.ViewModel;
import java.util.Collection;

final class a<T extends SortedListAdapter.ViewModel> implements SortedListAdapter.Editor<T> {
    private final ModifiableItemManager.Transaction<T> a;

    a(ModifiableItemManager.Transaction<T> transaction) {
        this.a = transaction;
    }

    public final SortedListAdapter.Editor<T> add(T t) {
        this.a.add(t);
        return this;
    }

    public final SortedListAdapter.Editor<T> add(Collection<T> collection) {
        this.a.add(collection);
        return this;
    }

    public final void commit() {
        this.a.commit();
    }

    public final SortedListAdapter.Editor<T> remove(T t) {
        this.a.remove(t);
        return this;
    }

    public final SortedListAdapter.Editor<T> remove(Collection<T> collection) {
        this.a.remove(collection);
        return this;
    }

    public final SortedListAdapter.Editor<T> removeAll() {
        this.a.removeAll();
        return this;
    }

    public final SortedListAdapter.Editor<T> replaceAll(Collection<T> collection) {
        this.a.replaceAll(collection);
        return this;
    }
}
