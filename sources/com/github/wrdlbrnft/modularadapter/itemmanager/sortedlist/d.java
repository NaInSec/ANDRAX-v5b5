package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;
import java.util.List;
import java.util.NoSuchElementException;

final class d<T> implements SortedListItemManager.d<T> {
    private List<T> a = null;

    d() {
    }

    public final synchronized int a() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public final synchronized T a(int i) {
        if (this.a != null) {
        } else {
            throw new NoSuchElementException();
        }
        return this.a.get(i);
    }

    public final void a(List<T> list) {
        this.a = list;
    }
}
