package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;
import java.util.Collection;

final /* synthetic */ class q implements SortedListItemManager.a {
    private final SortedListItemManager.e a;
    private final Collection b;

    q(SortedListItemManager.e eVar, Collection collection) {
        this.a = eVar;
        this.b = collection;
    }

    public final void a() {
        SortedListItemManager.this.i.addAll(this.b);
    }
}
