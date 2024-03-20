package com.thecrackertechnology.dragonterminal.framework.database;

import com.thecrackertechnology.dragonterminal.framework.database.annotation.Ignore;
import com.thecrackertechnology.dragonterminal.framework.database.annotation.NotNull;
import java.lang.reflect.Field;

public class SQLTypeParser {
    public static DatabaseDataType getDataType(Field field) {
        Class<?> type = field.getType();
        boolean z = true;
        if (type == String.class) {
            DatabaseDataType databaseDataType = DatabaseDataType.TEXT;
            if (field.getAnnotation(NotNull.class) != null) {
                z = false;
            }
            return databaseDataType.nullable(z);
        } else if (type == Integer.TYPE || type == Integer.class) {
            DatabaseDataType databaseDataType2 = DatabaseDataType.INTEGER;
            if (field.getAnnotation(NotNull.class) != null) {
                z = false;
            }
            return databaseDataType2.nullable(z);
        } else if (type == Float.TYPE || type == Float.class) {
            DatabaseDataType databaseDataType3 = DatabaseDataType.FLOAT;
            if (field.getAnnotation(NotNull.class) != null) {
                z = false;
            }
            return databaseDataType3.nullable(z);
        } else if (type == Long.TYPE || type == Long.class) {
            DatabaseDataType databaseDataType4 = DatabaseDataType.BIGINT;
            if (field.getAnnotation(NotNull.class) != null) {
                z = false;
            }
            return databaseDataType4.nullable(z);
        } else if (type == Double.TYPE || type == Double.class) {
            DatabaseDataType databaseDataType5 = DatabaseDataType.DOUBLE;
            if (field.getAnnotation(NotNull.class) != null) {
                z = false;
            }
            return databaseDataType5.nullable(z);
        } else if (type != Boolean.TYPE && type != Boolean.class) {
            return null;
        } else {
            DatabaseDataType databaseDataType6 = DatabaseDataType.INTEGER;
            if (field.getAnnotation(NotNull.class) != null) {
                z = false;
            }
            return databaseDataType6.nullable(z);
        }
    }

    public static DatabaseDataType getDataType(Class<?> cls) {
        if (cls == String.class) {
            return DatabaseDataType.TEXT;
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            return DatabaseDataType.INTEGER;
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return DatabaseDataType.FLOAT;
        }
        if (cls == Long.TYPE || cls == Long.class) {
            return DatabaseDataType.BIGINT;
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return DatabaseDataType.DOUBLE;
        }
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return DatabaseDataType.INTEGER;
        }
        return null;
    }

    public static boolean matchType(Field field, DatabaseDataType databaseDataType) {
        return databaseDataType != null && getDataType(field.getType()) == databaseDataType;
    }

    public static boolean isIgnore(Field field) {
        return field.getAnnotation(Ignore.class) != null;
    }
}
