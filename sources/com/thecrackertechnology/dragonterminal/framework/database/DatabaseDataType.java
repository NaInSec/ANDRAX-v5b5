package com.thecrackertechnology.dragonterminal.framework.database;

public enum DatabaseDataType {
    INTEGER,
    TEXT,
    FLOAT,
    BIGINT,
    DOUBLE;
    
    boolean nullable;

    public DatabaseDataType nullable(boolean z) {
        this.nullable = z;
        return this;
    }
}
