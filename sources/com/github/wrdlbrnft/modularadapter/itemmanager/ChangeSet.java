package com.github.wrdlbrnft.modularadapter.itemmanager;

public interface ChangeSet {

    public interface AddCallback {
        void add(int i, int i2);
    }

    public interface ChangeCallback {
        void change(int i, int i2);
    }

    public interface MoveCallback {
        void move(int i, int i2);
    }

    public interface RemoveCallback {
        void remove(int i, int i2);
    }

    void applyTo(MoveCallback moveCallback, AddCallback addCallback, RemoveCallback removeCallback, ChangeCallback changeCallback);
}
