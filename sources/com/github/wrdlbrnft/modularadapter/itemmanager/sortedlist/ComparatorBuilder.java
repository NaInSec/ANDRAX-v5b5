package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;
import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager.ViewModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorBuilder<T extends SortedListItemManager.ViewModel> {
    private final List<a> a = new ArrayList();

    interface a {
        boolean a(SortedListItemManager.ViewModel viewModel, SortedListItemManager.ViewModel viewModel2);

        int b(SortedListItemManager.ViewModel viewModel, SortedListItemManager.ViewModel viewModel2);
    }

    static /* synthetic */ int a(ComparatorBuilder comparatorBuilder, SortedListItemManager.ViewModel viewModel, SortedListItemManager.ViewModel viewModel2) {
        for (a next : comparatorBuilder.a) {
            if (next.a(viewModel, viewModel2)) {
                return next.b(viewModel, viewModel2);
            }
        }
        return 0;
    }

    public final Comparator<T> build() {
        return c.a(this);
    }

    @SafeVarargs
    public final ComparatorBuilder<T> setGeneralOrder(Class<? extends T>... clsArr) {
        if (clsArr.length > 1) {
            this.a.add(new e(clsArr));
        }
        return this;
    }

    public final <M extends T> ComparatorBuilder<T> setOrderForModel(Class<M> cls, Comparator<M> comparator) {
        this.a.add(new f(cls, comparator));
        return this;
    }
}
