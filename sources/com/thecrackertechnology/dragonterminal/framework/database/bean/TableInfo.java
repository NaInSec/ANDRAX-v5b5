package com.thecrackertechnology.dragonterminal.framework.database.bean;

import com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class TableInfo {
    public Method afterTableCreateMethod;
    public boolean containID;
    public String createTableStatement;
    public Map<Field, DatabaseDataType> fieldToDataTypeMap;
    public boolean isCreate = false;
    public Field primaryField;
    public String tableName;
}
