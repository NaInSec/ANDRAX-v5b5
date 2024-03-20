package com.github.wrdlbrnft.modularadapter;

import com.github.wrdlbrnft.modularadapter.itemmanager.ChangeSet;

final /* synthetic */ class c implements ChangeSet.AddCallback {
    private final ModularAdapter a;

    private c(ModularAdapter modularAdapter) {
        this.a = modularAdapter;
    }

    public static ChangeSet.AddCallback a(ModularAdapter modularAdapter) {
        return new c(modularAdapter);
    }

    public final void add(int i, int i2) {
        this.a.notifyItemRangeInserted(i, i2);
    }
}
