package com.github.wrdlbrnft.modularadapter;

import com.github.wrdlbrnft.modularadapter.itemmanager.ChangeSet;

final /* synthetic */ class e implements ChangeSet.ChangeCallback {
    private final ModularAdapter a;

    private e(ModularAdapter modularAdapter) {
        this.a = modularAdapter;
    }

    public static ChangeSet.ChangeCallback a(ModularAdapter modularAdapter) {
        return new e(modularAdapter);
    }

    public final void change(int i, int i2) {
        this.a.notifyItemRangeChanged(i, i2);
    }
}
