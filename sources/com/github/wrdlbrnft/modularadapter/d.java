package com.github.wrdlbrnft.modularadapter;

import com.github.wrdlbrnft.modularadapter.itemmanager.ChangeSet;

final /* synthetic */ class d implements ChangeSet.RemoveCallback {
    private final ModularAdapter a;

    private d(ModularAdapter modularAdapter) {
        this.a = modularAdapter;
    }

    public static ChangeSet.RemoveCallback a(ModularAdapter modularAdapter) {
        return new d(modularAdapter);
    }

    public final void remove(int i, int i2) {
        this.a.notifyItemRangeRemoved(i, i2);
    }
}
