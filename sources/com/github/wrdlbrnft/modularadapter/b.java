package com.github.wrdlbrnft.modularadapter;

import com.github.wrdlbrnft.modularadapter.itemmanager.ChangeSet;

final /* synthetic */ class b implements ChangeSet.MoveCallback {
    private final ModularAdapter a;

    private b(ModularAdapter modularAdapter) {
        this.a = modularAdapter;
    }

    public static ChangeSet.MoveCallback a(ModularAdapter modularAdapter) {
        return new b(modularAdapter);
    }

    public final void move(int i, int i2) {
        this.a.notifyItemMoved(i, i2);
    }
}
