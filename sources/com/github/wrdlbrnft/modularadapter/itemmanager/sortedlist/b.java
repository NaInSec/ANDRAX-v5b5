package com.github.wrdlbrnft.modularadapter.itemmanager.sortedlist;

import com.github.wrdlbrnft.modularadapter.itemmanager.ChangeSet;

final class b implements a {
    private final ChangeSet.MoveCallback a;
    private final ChangeSet.AddCallback b;
    private final ChangeSet.RemoveCallback c;
    private final ChangeSet.ChangeCallback d;

    b(ChangeSet.MoveCallback moveCallback, ChangeSet.AddCallback addCallback, ChangeSet.RemoveCallback removeCallback, ChangeSet.ChangeCallback changeCallback) {
        this.a = moveCallback;
        this.b = addCallback;
        this.c = removeCallback;
        this.d = changeCallback;
    }

    public final void add(int i, int i2) {
        this.b.add(i, i2);
    }

    public final void change(int i, int i2) {
        this.d.change(i, i2);
    }

    public final void move(int i, int i2) {
        this.a.move(i, i2);
    }

    public final void remove(int i, int i2) {
        this.c.remove(i, i2);
    }
}
