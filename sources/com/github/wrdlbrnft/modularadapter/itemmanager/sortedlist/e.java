package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.ComparatorBuilder;
import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;

final class e implements ComparatorBuilder.a {
    private final Class<? extends SortedListItemManager.ViewModel>[] a;

    e(Class<? extends SortedListItemManager.ViewModel>[] clsArr) {
        this.a = clsArr;
    }

    public final boolean a(SortedListItemManager.ViewModel viewModel, SortedListItemManager.ViewModel viewModel2) {
        Class<?> cls = viewModel.getClass();
        Class<?> cls2 = viewModel2.getClass();
        return !cls.equals(cls2) && g.b(this.a, cls) && g.b(this.a, cls2);
    }

    public final int b(SortedListItemManager.ViewModel viewModel, SortedListItemManager.ViewModel viewModel2) {
        return Integer.signum(g.a(this.a, viewModel.getClass()) - g.a(this.a, viewModel2.getClass()));
    }
}
