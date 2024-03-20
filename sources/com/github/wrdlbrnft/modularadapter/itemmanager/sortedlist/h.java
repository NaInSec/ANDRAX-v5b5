package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;
import java.util.List;

final /* synthetic */ class h implements Runnable {
    private final SortedListItemManager.c a;
    private final List b;
    private final List c;

    private h(SortedListItemManager.c cVar, List list, List list2) {
        this.a = cVar;
        this.b = list;
        this.c = list2;
    }

    public static Runnable a(SortedListItemManager.c cVar, List list, List list2) {
        return new h(cVar, list, list2);
    }

    public final void run() {
        SortedListItemManager.c.a(this.a, this.b, this.c);
    }
}
