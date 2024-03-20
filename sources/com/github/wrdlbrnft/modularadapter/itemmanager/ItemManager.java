package com.github.wrdlbrnft.modularadapter.itemmanager;

public interface ItemManager<T> {

    public interface ChangeSetCallback {
        void onChangeSetAvailable(ChangeSet changeSet);
    }

    public interface StateCallback {
        void onChangesFinished();

        void onChangesInProgress();
    }

    void addChangeSetCallback(ChangeSetCallback changeSetCallback);

    void addStateCallback(StateCallback stateCallback);

    T getItem(int i);

    int getItemCount();

    void removeChangeSetCallback(ChangeSetCallback changeSetCallback);

    void removeStateCallback(StateCallback stateCallback);
}
