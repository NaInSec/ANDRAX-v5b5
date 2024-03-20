package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;

final /* synthetic */ class w implements Runnable {
    private final SortedListItemManager.e a;

    private w(SortedListItemManager.e eVar) {
        this.a = eVar;
    }

    public static Runnable a(SortedListItemManager.e eVar) {
        return new w(eVar);
    }

    public final void run() {
        SortedListItemManager.e.b(this.a);
    }
}
