package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;
import java.util.List;

final /* synthetic */ class v implements Runnable {
    private final SortedListItemManager.e a;
    private final List b;

    private v(SortedListItemManager.e eVar, List list) {
        this.a = eVar;
        this.b = list;
    }

    public static Runnable a(SortedListItemManager.e eVar, List list) {
        return new v(eVar, list);
    }

    public final void run() {
        SortedListItemManager.e.a(this.a, this.b);
    }
}
