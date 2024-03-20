package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;

final /* synthetic */ class o implements Runnable {
    private final SortedListItemManager.e a;

    private o(SortedListItemManager.e eVar) {
        this.a = eVar;
    }

    public static Runnable a(SortedListItemManager.e eVar) {
        return new o(eVar);
    }

    public final void run() {
        this.a.b();
    }
}
