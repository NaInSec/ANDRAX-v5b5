package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;

final /* synthetic */ class r implements SortedListItemManager.a {
    private final SortedListItemManager.e a;
    private final SortedListItemManager.ViewModel b;

    r(SortedListItemManager.e eVar, SortedListItemManager.ViewModel viewModel) {
        this.a = eVar;
        this.b = viewModel;
    }

    public final void a() {
        SortedListItemManager.this.i.remove(this.b);
    }
}