package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;

final /* synthetic */ class p implements Runnable {
    private final SortedListItemManager.e a;

    private p(SortedListItemManager.e eVar) {
        this.a = eVar;
    }

    public static Runnable a(SortedListItemManager.e eVar) {
        return new p(eVar);
    }

    public final void run() {
        this.a.b();
    }
}
