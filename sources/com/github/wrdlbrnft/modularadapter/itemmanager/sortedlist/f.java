package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.ComparatorBuilder;
import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;
import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager.ViewModel;
import java.util.Comparator;

final class f<M extends SortedListItemManager.ViewModel> implements ComparatorBuilder.a {
    private final Class<M> a;
    private final Comparator<M> b;

    f(Class<M> cls, Comparator<M> comparator) {
        this.a = cls;
        this.b = comparator;
    }

    public final boolean a(SortedListItemManager.ViewModel viewModel, SortedListItemManager.ViewModel viewModel2) {
        return this.a.isAssignableFrom(viewModel.getClass()) && this.a.isAssignableFrom(viewModel2.getClass());
    }

    public final int b(SortedListItemManager.ViewModel viewModel, SortedListItemManager.ViewModel viewModel2) {
        return this.b.compare(viewModel, viewModel2);
    }
}
