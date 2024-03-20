package com.thecrackertechnology.dragonterminal.framework.database;

import com.thecrackertechnology.dragonterminal.framework.NeoTermDatabase;
import com.thecrackertechnology.dragonterminal.framework.database.annotation.ID;
import com.thecrackertechnology.dragonterminal.framework.database.annotation.Table;
import com.thecrackertechnology.dragonterminal.framework.database.bean.TableInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TableHelper {
    private static final Map<Class<?>, TableInfo> classToTableInfoMap = new HashMap();

    public static TableInfo from(Class<?> cls) {
        TableInfo tableInfo = classToTableInfoMap.get(cls);
        if (tableInfo != null) {
            return tableInfo;
        }
        TableInfo tableInfo2 = new TableInfo();
        Table table = (Table) cls.getAnnotation(Table.class);
        String afterTableCreate = table != null ? table.afterTableCreate() : null;
        if (afterTableCreate != null && afterTableCreate.trim().length() > 0) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(afterTableCreate, new Class[]{NeoTermDatabase.class});
                if (declaredMethod != null && Modifier.isStatic(declaredMethod.getModifiers())) {
                    declaredMethod.setAccessible(true);
                    tableInfo2.afterTableCreateMethod = declaredMethod;
                }
            } catch (Throwable unused) {
            }
        }
        if (table == null || table.name().trim().length() == 0) {
            tableInfo2.tableName = cls.getName().replace(".", "_");
        } else {
            tableInfo2.tableName = table.name();
        }
        HashMap hashMap = new HashMap();
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            if (!SQLTypeParser.isIgnore(field)) {
                DatabaseDataType dataType = SQLTypeParser.getDataType(field);
                if (dataType != null) {
                    hashMap.put(field, dataType);
                } else {
                    throw new IllegalArgumentException("The type of " + field.getName() + " is not supported in database.");
                }
            }
        }
        tableInfo2.fieldToDataTypeMap = hashMap;
        buildPrimaryIDForTableInfo(tableInfo2);
        tableInfo2.createTableStatement = SQLStatementHelper.createTable(tableInfo2);
        synchronized (classToTableInfoMap) {
            classToTableInfoMap.put(cls, tableInfo2);
        }
        return tableInfo2;
    }

    private static TableInfo buildPrimaryIDForTableInfo(TableInfo tableInfo) {
        Field field;
        Iterator<Field> it = tableInfo.fieldToDataTypeMap.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                field = null;
                break;
            }
            field = it.next();
            if (((ID) field.getAnnotation(ID.class)) != null) {
                break;
            }
        }
        if (field != null) {
            tableInfo.fieldToDataTypeMap.remove(field);
            tableInfo.containID = true;
            tableInfo.primaryField = field;
        } else {
            tableInfo.containID = false;
            tableInfo.primaryField = null;
        }
        return tableInfo;
    }

    public static TableInfo findTableInfoByName(String str) {
        for (TableInfo next : classToTableInfoMap.values()) {
            if (next.tableName.equals(str)) {
                return next;
            }
        }
        return null;
    }

    public static void clearCache() {
        classToTableInfoMap.clear();
    }
}
