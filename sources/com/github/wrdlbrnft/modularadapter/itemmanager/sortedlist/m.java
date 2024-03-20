package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.ChangeSet;
import com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist.SortedListItemManager;
import java.util.List;

final /* synthetic */ class m implements ChangeSet {
    private final List a;

    private m(List list) {
        this.a = list;
    }

    public static ChangeSet a(List list) {
        return new m(list);
    }

    public final void applyTo(ChangeSet.MoveCallback moveCallback, ChangeSet.AddCallback addCallback, ChangeSet.RemoveCallback removeCallback, ChangeSet.ChangeCallback changeCallback) {
        SortedListItemManager.c.a(this.a, moveCallback, addCallback, removeCallback, changeCallback);
    }
}
