package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;
import java.util.Comparator;

final /* synthetic */ class c implements Comparator {
    private final ComparatorBuilder a;

    private c(ComparatorBuilder comparatorBuilder) {
        this.a = comparatorBuilder;
    }

    public static Comparator a(ComparatorBuilder comparatorBuilder) {
        return new c(comparatorBuilder);
    }

    public final int compare(Object obj, Object obj2) {
        return ComparatorBuilder.a(this.a, (SortedListItemManager.ViewModel) obj, (SortedListItemManager.ViewModel) obj2);
    }
}
