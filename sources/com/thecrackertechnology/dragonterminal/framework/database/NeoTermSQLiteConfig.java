package com.thecrackertechnology.dragonterminal.framework.database;

import java.io.Serializable;

public class NeoTermSQLiteConfig implements Serializable {
    public static NeoTermSQLiteConfig DEFAULT_CONFIG = new NeoTermSQLiteConfig();
    public static String DEFAULT_DB_NAME = "we_like.db";
    private static final long serialVersionUID = -4069725570156436316L;
    private String dbName = DEFAULT_DB_NAME;
    private int dbVersion = 1;
    public boolean debugMode = false;
    private boolean defaultDropAllTables = false;
    private OnDatabaseUpgradedListener onDatabaseUpgradedListener;
    private String saveDir;

    public String getDatabaseName() {
        return this.dbName;
    }

    public void setDatabaseName(String str) {
        this.dbName = str;
    }

    public OnDatabaseUpgradedListener getOnDatabaseUpgradedListener() {
        return this.onDatabaseUpgradedListener;
    }

    public void setOnDatabaseUpgradedListener(OnDatabaseUpgradedListener onDatabaseUpgradedListener2) {
        this.onDatabaseUpgradedListener = onDatabaseUpgradedListener2;
    }

    public String getSaveDir() {
        return this.saveDir;
    }

    public void setSaveDir(String str) {
        this.saveDir = str;
    }

    public int getDatabaseVersion() {
        return this.dbVersion;
    }

    public void setDatabaseVersion(int i) {
        this.dbVersion = i;
    }

    public boolean isDefaultDropAllTables() {
        return this.defaultDropAllTables;
    }

    public void setDefaultDropAllTables(boolean z) {
        this.defaultDropAllTables = z;
    }
}
