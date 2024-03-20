package com.thecrackertechnology.dragonterminal.framework.database;

import android.database.sqlite.SQLiteDatabase;

public interface OnDatabaseUpgradedListener {
    void onDatabaseUpgraded(SQLiteDatabase sQLiteDatabase, int i, int i2);
}
