package com.thecrackertechnology.dragonterminal.framework.database;

import java.lang.reflect.Field;

public class ValueHelper {

    /* renamed from: com.thecrackertechnology.dragonterminal.framework.database.ValueHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType = new int[DatabaseDataType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType[] r0 = com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType = r0
                int[] r0 = $SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r1 = com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType.INTEGER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r1 = com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType.TEXT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r1 = com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType.FLOAT     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r1 = com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType.BIGINT     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r1 = com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.database.ValueHelper.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:15|16|17|18|(1:20)(1:21)|22|24) */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0056 A[Catch:{ IllegalAccessException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0057 A[Catch:{ IllegalAccessException -> 0x005f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setKeyValue(android.database.Cursor r1, java.lang.Object r2, java.lang.reflect.Field r3, com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r4, int r5) {
        /*
            int[] r0 = com.thecrackertechnology.dragonterminal.framework.database.ValueHelper.AnonymousClass1.$SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType
            int r4 = r4.ordinal()
            r4 = r0[r4]
            r0 = 1
            if (r4 == r0) goto L_0x0044
            r0 = 2
            if (r4 == r0) goto L_0x003c
            r0 = 3
            if (r4 == r0) goto L_0x0030
            r0 = 4
            if (r4 == r0) goto L_0x0024
            r0 = 5
            if (r4 == r0) goto L_0x0018
            goto L_0x005f
        L_0x0018:
            double r4 = r1.getDouble(r5)     // Catch:{ IllegalAccessException -> 0x005f }
            java.lang.Double r1 = java.lang.Double.valueOf(r4)     // Catch:{ IllegalAccessException -> 0x005f }
            r3.set(r2, r1)     // Catch:{ IllegalAccessException -> 0x005f }
            goto L_0x005f
        L_0x0024:
            long r4 = r1.getLong(r5)     // Catch:{ IllegalAccessException -> 0x005f }
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ IllegalAccessException -> 0x005f }
            r3.set(r2, r1)     // Catch:{ IllegalAccessException -> 0x005f }
            goto L_0x005f
        L_0x0030:
            float r1 = r1.getFloat(r5)     // Catch:{ IllegalAccessException -> 0x005f }
            java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch:{ IllegalAccessException -> 0x005f }
            r3.set(r2, r1)     // Catch:{ IllegalAccessException -> 0x005f }
            goto L_0x005f
        L_0x003c:
            java.lang.String r1 = r1.getString(r5)     // Catch:{ IllegalAccessException -> 0x005f }
            r3.set(r2, r1)     // Catch:{ IllegalAccessException -> 0x005f }
            goto L_0x005f
        L_0x0044:
            int r4 = r1.getInt(r5)     // Catch:{ all -> 0x0050 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0050 }
            r3.set(r2, r4)     // Catch:{ all -> 0x0050 }
            goto L_0x005f
        L_0x0050:
            int r1 = r1.getInt(r5)     // Catch:{ IllegalAccessException -> 0x005f }
            if (r1 == 0) goto L_0x0057
            goto L_0x0058
        L_0x0057:
            r0 = 0
        L_0x0058:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)     // Catch:{ IllegalAccessException -> 0x005f }
            r3.set(r2, r1)     // Catch:{ IllegalAccessException -> 0x005f }
        L_0x005f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.database.ValueHelper.setKeyValue(android.database.Cursor, java.lang.Object, java.lang.reflect.Field, com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType, int):void");
    }

    public static String valueToString(DatabaseDataType databaseDataType, Field field, Object obj) throws IllegalAccessException {
        int i = AnonymousClass1.$SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType[databaseDataType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return "\"" + field.get(obj) + "\"";
            } else if (i == 3) {
                return String.valueOf(((Float) field.get(obj)).floatValue());
            } else {
                if (i == 4) {
                    return String.valueOf(((Long) field.get(obj)).longValue());
                }
                if (i != 5) {
                    return null;
                }
                return String.valueOf(((Double) field.get(obj)).doubleValue());
            }
        } else if (field.get(obj) instanceof Boolean) {
            return String.valueOf(((Boolean) field.get(obj)).booleanValue() ? 1 : 0);
        } else {
            return String.valueOf(((Integer) field.get(obj)).intValue());
        }
    }

    public static String valueToString(DatabaseDataType databaseDataType, Object obj) {
        int i = AnonymousClass1.$SwitchMap$com$thecrackertechnology$dragonterminal$framework$database$DatabaseDataType[databaseDataType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return "\"" + obj + "\"";
            } else if (i == 3) {
                return String.valueOf(((Float) obj).floatValue());
            } else {
                if (i == 4) {
                    return String.valueOf(((Long) obj).longValue());
                }
                if (i != 5) {
                    return null;
                }
                return String.valueOf(((Double) obj).doubleValue());
            }
        } else if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? "1" : "0";
        } else {
            return String.valueOf(((Integer) obj).intValue());
        }
    }
}
