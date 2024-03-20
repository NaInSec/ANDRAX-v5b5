package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;

final /* synthetic */ class j implements SortedListItemManager.b {
    private final int a;
    private final int b;

    j(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final void a(a aVar) {
        aVar.remove(this.a, this.b);
    }
}
