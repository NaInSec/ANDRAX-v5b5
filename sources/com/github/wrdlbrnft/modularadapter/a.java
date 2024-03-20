package com.github.wrdlbrnft.modularadapter;

import com.github.wrdlbrnft.modularadapter.itemmanager.ChangeSet;
import com.github.wrdlbrnft.modularadapter.itemmanager.ItemManager;

final /* synthetic */ class a implements ItemManager.ChangeSetCallback {
    private final ModularAdapter a;

    private a(ModularAdapter modularAdapter) {
        this.a = modularAdapter;
    }

    public static ItemManager.ChangeSetCallback a(ModularAdapter modularAdapter) {
        return new a(modularAdapter);
    }

    public final void onChangeSetAvailable(ChangeSet changeSet) {
        changeSet.applyTo(b.a(this.a), c.a(this.a), d.a(this.a), e.a(this.a));
    }
}
