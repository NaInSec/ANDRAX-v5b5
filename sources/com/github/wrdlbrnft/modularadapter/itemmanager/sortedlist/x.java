package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;

final /* synthetic */ class x implements Runnable {
    private final SortedListItemManager.e a;

    private x(SortedListItemManager.e eVar) {
        this.a = eVar;
    }

    public static Runnable a(SortedListItemManager.e eVar) {
        return new x(eVar);
    }

    public final void run() {
        this.a.b();
    }
}
