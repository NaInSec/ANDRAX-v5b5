package com.thecrackertechnology.dragonterminal.framework.database;

import com.thecrackertechnology.dragonterminal.framework.database.annotation.ID;
import com.thecrackertechnology.dragonterminal.framework.database.bean.TableInfo;
import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;

public class SQLStatementHelper {
    public static String createTable(TableInfo tableInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append("'");
        sb.append(tableInfo.tableName);
        sb.append("'");
        sb.append(" (");
        if (tableInfo.containID) {
            DatabaseDataType dataType = SQLTypeParser.getDataType(tableInfo.primaryField);
            if (dataType != null) {
                sb.append("'");
                sb.append(tableInfo.primaryField.getName());
                sb.append("'");
                if (AnonymousClass1.$SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType[dataType.ordinal()] != 1) {
                    sb.append("  ");
                    sb.append(dataType.name());
                    sb.append(" PRIMARY KEY");
                } else {
                    sb.append(" INTEGER PRIMARY KEY ");
                    ID id = (ID) tableInfo.primaryField.getAnnotation(ID.class);
                    if (id != null && id.autoIncrement()) {
                        sb.append("AUTOINCREMENT");
                    }
                }
                sb.append(",");
            } else {
                throw new IllegalArgumentException("Type of " + tableInfo.primaryField.getType().getName() + " is not support in WelikeDB.");
            }
        } else {
            sb.append("'_id' INTEGER PRIMARY KEY AUTOINCREMENT,");
        }
        for (Field next : tableInfo.fieldToDataTypeMap.keySet()) {
            DatabaseDataType databaseDataType = tableInfo.fieldToDataTypeMap.get(next);
            sb.append("'");
            sb.append(next.getName());
            sb.append("'");
            sb.append(StringUtils.SPACE);
            sb.append(databaseDataType.name());
            if (!databaseDataType.nullable) {
                sb.append(" NOT NULL");
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: com.thecrackertechnology.dragonterminal.framework.database.SQLStatementHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType = new int[DatabaseDataType.values().length];

        static {
            try {
                $SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType[DatabaseDataType.INTEGER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static String insertIntoTable(Object obj) {
        TableInfo from = TableHelper.from(obj.getClass());
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(from.tableName);
        sb.append(StringUtils.SPACE);
        sb.append("VALUES(");
        if (from.containID) {
            DatabaseDataType dataType = SQLTypeParser.getDataType(from.primaryField);
            if (AnonymousClass1.$SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType[dataType.ordinal()] != 1) {
                try {
                    sb.append(ValueHelper.valueToString(dataType, from.primaryField, obj));
                    sb.append(",");
                } catch (IllegalAccessException unused) {
                }
            } else {
                sb.append("NULL,");
            }
        } else {
            sb.append("NULL,");
        }
        for (Field next : from.fieldToDataTypeMap.keySet()) {
            try {
                sb.append(ValueHelper.valueToString(from.fieldToDataTypeMap.get(next), next, obj));
                sb.append(",");
            } catch (IllegalAccessException unused2) {
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    public static String findByWhere(TableInfo tableInfo, String str) {
        return "SELECT * FROM " + tableInfo.tableName + StringUtils.SPACE + "WHERE " + str;
    }

    public static String deleteByWhere(TableInfo tableInfo, String str) {
        return "DELETE FROM " + tableInfo.tableName + StringUtils.SPACE + "WHERE " + str;
    }

    public static String updateByWhere(TableInfo tableInfo, Object obj, String str) {
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(tableInfo.tableName);
        sb.append(" SET ");
        for (Field next : tableInfo.fieldToDataTypeMap.keySet()) {
            try {
                sb.append(next.getName());
                sb.append(" = ");
                sb.append(ValueHelper.valueToString(SQLTypeParser.getDataType(next.getType()), next.get(obj)));
                sb.append(",");
            } catch (Throwable unused) {
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE ");
        sb.append(str);
        return sb.toString();
    }

    public static String selectTable(String str) {
        return "SELECT * FROM " + str;
    }
}
