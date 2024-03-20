package com.thecrackertechnology.dragonterminal.framework;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType;
import com.thecrackertechnology.dragonterminal.framework.database.NeoTermSQLiteConfig;
import com.thecrackertechnology.dragonterminal.framework.database.OnDatabaseUpgradedListener;
import com.thecrackertechnology.dragonterminal.framework.database.SQLStatementHelper;
import com.thecrackertechnology.dragonterminal.framework.database.SQLTypeParser;
import com.thecrackertechnology.dragonterminal.framework.database.TableHelper;
import com.thecrackertechnology.dragonterminal.framework.database.ValueHelper;
import com.thecrackertechnology.dragonterminal.framework.database.bean.TableInfo;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeoTermDatabase {
    private static final Map<String, NeoTermDatabase> DAO_MAP = new HashMap();
    private SQLiteDatabase db;
    private NeoTermSQLiteConfig neoTermSQLiteConfig;

    private NeoTermDatabase(NeoTermSQLiteConfig neoTermSQLiteConfig2) {
        this.neoTermSQLiteConfig = neoTermSQLiteConfig2;
        String saveDir = neoTermSQLiteConfig2.getSaveDir();
        if (saveDir == null || saveDir.trim().length() <= 0) {
            this.db = new SQLiteDataBaseHelper(AndraxApp.Companion.get().getApplicationContext().getApplicationContext(), neoTermSQLiteConfig2).getWritableDatabase();
        } else {
            this.db = createDataBaseFileOnSDCard(saveDir, neoTermSQLiteConfig2.getDatabaseName());
        }
    }

    public static NeoTermDatabase instance(NeoTermSQLiteConfig neoTermSQLiteConfig2) {
        if (neoTermSQLiteConfig2.getDatabaseName() != null) {
            NeoTermDatabase neoTermDatabase = DAO_MAP.get(neoTermSQLiteConfig2.getDatabaseName());
            if (neoTermDatabase == null) {
                neoTermDatabase = new NeoTermDatabase(neoTermSQLiteConfig2);
                synchronized (DAO_MAP) {
                    DAO_MAP.put(neoTermSQLiteConfig2.getDatabaseName(), neoTermDatabase);
                }
            } else {
                neoTermDatabase.applyConfig(neoTermSQLiteConfig2);
            }
            return neoTermDatabase;
        }
        throw new IllegalArgumentException("DBName is null in SqLiteConfig.");
    }

    public static NeoTermDatabase instance() {
        return instance(NeoTermSQLiteConfig.DEFAULT_CONFIG);
    }

    public static NeoTermDatabase instance(String str) {
        NeoTermSQLiteConfig neoTermSQLiteConfig2 = new NeoTermSQLiteConfig();
        neoTermSQLiteConfig2.setDatabaseName(str);
        return instance(neoTermSQLiteConfig2);
    }

    public static NeoTermDatabase instance(int i) {
        NeoTermSQLiteConfig neoTermSQLiteConfig2 = new NeoTermSQLiteConfig();
        neoTermSQLiteConfig2.setDatabaseVersion(i);
        return instance(neoTermSQLiteConfig2);
    }

    public static NeoTermDatabase instance(OnDatabaseUpgradedListener onDatabaseUpgradedListener) {
        NeoTermSQLiteConfig neoTermSQLiteConfig2 = new NeoTermSQLiteConfig();
        neoTermSQLiteConfig2.setOnDatabaseUpgradedListener(onDatabaseUpgradedListener);
        return instance(neoTermSQLiteConfig2);
    }

    public static NeoTermDatabase instance(String str, int i) {
        NeoTermSQLiteConfig neoTermSQLiteConfig2 = new NeoTermSQLiteConfig();
        neoTermSQLiteConfig2.setDatabaseName(str);
        neoTermSQLiteConfig2.setDatabaseVersion(i);
        return instance(neoTermSQLiteConfig2);
    }

    public static NeoTermDatabase instance(String str, int i, OnDatabaseUpgradedListener onDatabaseUpgradedListener) {
        NeoTermSQLiteConfig neoTermSQLiteConfig2 = new NeoTermSQLiteConfig();
        neoTermSQLiteConfig2.setDatabaseName(str);
        neoTermSQLiteConfig2.setDatabaseVersion(i);
        neoTermSQLiteConfig2.setOnDatabaseUpgradedListener(onDatabaseUpgradedListener);
        return instance(neoTermSQLiteConfig2);
    }

    private void applyConfig(NeoTermSQLiteConfig neoTermSQLiteConfig2) {
        this.neoTermSQLiteConfig.debugMode = neoTermSQLiteConfig2.debugMode;
        this.neoTermSQLiteConfig.setOnDatabaseUpgradedListener(neoTermSQLiteConfig2.getOnDatabaseUpgradedListener());
    }

    public void release() {
        DAO_MAP.clear();
        if (this.neoTermSQLiteConfig.debugMode) {
            NLog.INSTANCE.d("缓存的DAO已经全部清除,将不占用内存.");
        }
    }

    private SQLiteDatabase createDataBaseFileOnSDCard(String str, String str2) {
        File file = new File(str, str2);
        if (file.exists()) {
            return SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
        }
        try {
            if (file.createNewFile()) {
                return SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
            }
            return null;
        } catch (IOException unused) {
            throw new RuntimeException("无法在 " + file.getAbsolutePath() + "创建DB文件.");
        }
    }

    private void createTableIfNeed(Class<?> cls) {
        TableInfo from = TableHelper.from(cls);
        if (!from.isCreate && !isTableExist(from)) {
            String createTable = SQLStatementHelper.createTable(from);
            if (this.neoTermSQLiteConfig.debugMode) {
                NLog.INSTANCE.w(createTable);
            }
            this.db.execSQL(createTable);
            Method method = from.afterTableCreateMethod;
            if (method != null) {
                try {
                    method.invoke((Object) null, new Object[]{this});
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0038, code lost:
        if (r4 != null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isTableExist(com.thecrackertechnology.dragonterminal.framework.database.bean.TableInfo r4) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='"
            r0.append(r1)
            java.lang.String r4 = r4.tableName
            r0.append(r4)
            java.lang.String r4 = "' "
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r3.db     // Catch:{ all -> 0x0049 }
            r2 = 0
            android.database.Cursor r4 = r1.rawQuery(r4, r2)     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x0043
            boolean r1 = r4.moveToNext()     // Catch:{ all -> 0x0035 }
            if (r1 == 0) goto L_0x0043
            int r1 = r4.getInt(r0)     // Catch:{ all -> 0x0035 }
            if (r1 <= 0) goto L_0x0043
            r1 = 1
            if (r4 == 0) goto L_0x0034
            r4.close()     // Catch:{ all -> 0x0049 }
        L_0x0034:
            return r1
        L_0x0035:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r2 = move-exception
            if (r4 == 0) goto L_0x0042
            r4.close()     // Catch:{ all -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r4 = move-exception
            r1.addSuppressed(r4)     // Catch:{ all -> 0x0049 }
        L_0x0042:
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x0043:
            if (r4 == 0) goto L_0x004d
            r4.close()     // Catch:{ all -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.NeoTermDatabase.isTableExist(com.thecrackertechnology.dragonterminal.framework.database.bean.TableInfo):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        if (r0 != null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002c, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002f, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dropAllTable() {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r0 = r3.db
            java.lang.String r1 = "SELECT name FROM sqlite_master WHERE type ='table'"
            r2 = 0
            android.database.Cursor r0 = r0.rawQuery(r1, r2)
            if (r0 == 0) goto L_0x0030
            r0.moveToFirst()     // Catch:{ all -> 0x0022 }
        L_0x000e:
            boolean r1 = r0.moveToNext()     // Catch:{ all -> 0x0022 }
            if (r1 == 0) goto L_0x0030
            r1 = 0
            java.lang.String r1 = r0.getString(r1)     // Catch:{ SQLException -> 0x001d }
            r3.dropTable((java.lang.String) r1)     // Catch:{ SQLException -> 0x001d }
            goto L_0x000e
        L_0x001d:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0022 }
            goto L_0x000e
        L_0x0022:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r2 = move-exception
            if (r0 == 0) goto L_0x002f
            r0.close()     // Catch:{ all -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x002f:
            throw r2
        L_0x0030:
            if (r0 == 0) goto L_0x0035
            r0.close()
        L_0x0035:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.NeoTermDatabase.dropAllTable():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (r0 != null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0021, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int tableCount() {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r0 = r3.db
            java.lang.String r1 = "SELECT name FROM sqlite_master WHERE type ='table'"
            r2 = 0
            android.database.Cursor r0 = r0.rawQuery(r1, r2)
            if (r0 != 0) goto L_0x000d
            r1 = 0
            goto L_0x0011
        L_0x000d:
            int r1 = r0.getCount()     // Catch:{ all -> 0x0017 }
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            r0.close()
        L_0x0016:
            return r1
        L_0x0017:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0019 }
        L_0x0019:
            r2 = move-exception
            if (r0 == 0) goto L_0x0024
            r0.close()     // Catch:{ all -> 0x0020 }
            goto L_0x0024
        L_0x0020:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0024:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.NeoTermDatabase.tableCount():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r0 != null) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> getTableList() {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r0 = r3.db
            java.lang.String r1 = "SELECT name FROM sqlite_master WHERE type ='table'"
            r2 = 0
            android.database.Cursor r0 = r0.rawQuery(r1, r2)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0028 }
            r1.<init>()     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0022
            r0.moveToFirst()     // Catch:{ all -> 0x0028 }
        L_0x0013:
            boolean r2 = r0.moveToNext()     // Catch:{ all -> 0x0028 }
            if (r2 == 0) goto L_0x0022
            r2 = 0
            java.lang.String r2 = r0.getString(r2)     // Catch:{ all -> 0x0028 }
            r1.add(r2)     // Catch:{ all -> 0x0028 }
            goto L_0x0013
        L_0x0022:
            if (r0 == 0) goto L_0x0027
            r0.close()
        L_0x0027:
            return r1
        L_0x0028:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x002a }
        L_0x002a:
            r2 = move-exception
            if (r0 == 0) goto L_0x0035
            r0.close()     // Catch:{ all -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0035:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.NeoTermDatabase.getTableList():java.util.List");
    }

    public void dropTable(Class<?> cls) {
        TableInfo from = TableHelper.from(cls);
        dropTable(from.tableName);
        from.isCreate = false;
    }

    public void dropTable(String str) {
        String str2 = "DROP TABLE IF EXISTS " + str;
        if (this.neoTermSQLiteConfig.debugMode) {
            NLog.INSTANCE.w(str2);
        }
        this.db.execSQL(str2);
        TableInfo findTableInfoByName = TableHelper.findTableInfoByName(str);
        if (findTableInfoByName != null) {
            findTableInfoByName.isCreate = false;
        }
    }

    public <T> NeoTermDatabase saveBean(T t) {
        createTableIfNeed(t.getClass());
        String insertIntoTable = SQLStatementHelper.insertIntoTable(t);
        if (this.neoTermSQLiteConfig.debugMode) {
            NLog.INSTANCE.w(insertIntoTable);
        }
        this.db.execSQL(insertIntoTable);
        return this;
    }

    public NeoTermDatabase saveBeans(Object[] objArr) {
        for (Object saveBean : objArr) {
            saveBean(saveBean);
        }
        return this;
    }

    public <T> NeoTermDatabase saveBeans(List<T> list) {
        for (T saveBean : list) {
            saveBean(saveBean);
        }
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0091, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0092, code lost:
        if (r1 != null) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0098, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0099, code lost:
        r9.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009c, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> java.util.List<T> findAll(java.lang.Class<?> r9) {
        /*
            r8 = this;
            r8.createTableIfNeed(r9)
            com.thecrackertechnology.dragonterminal.framework.database.bean.TableInfo r0 = com.thecrackertechnology.dragonterminal.framework.database.TableHelper.from(r9)
            java.lang.String r1 = r0.tableName
            java.lang.String r1 = com.thecrackertechnology.dragonterminal.framework.database.SQLStatementHelper.selectTable(r1)
            com.thecrackertechnology.dragonterminal.framework.database.NeoTermSQLiteConfig r2 = r8.neoTermSQLiteConfig
            boolean r2 = r2.debugMode
            if (r2 == 0) goto L_0x0018
            com.thecrackertechnology.dragonterminal.frontend.logging.NLog r2 = com.thecrackertechnology.dragonterminal.frontend.logging.NLog.INSTANCE
            r2.w(r1)
        L_0x0018:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            android.database.sqlite.SQLiteDatabase r3 = r8.db
            r4 = 0
            android.database.Cursor r1 = r3.rawQuery(r1, r4)
            if (r1 != 0) goto L_0x0030
            java.util.List r9 = java.util.Collections.emptyList()     // Catch:{ all -> 0x008f }
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            return r9
        L_0x0030:
            boolean r3 = r1.moveToNext()     // Catch:{ all -> 0x008f }
            if (r3 == 0) goto L_0x0089
            com.thecrackertechnology.dragonterminal.framework.reflection.Reflect r3 = com.thecrackertechnology.dragonterminal.framework.reflection.Reflect.on((java.lang.Class<?>) r9)     // Catch:{ all -> 0x008f }
            com.thecrackertechnology.dragonterminal.framework.reflection.Reflect r3 = r3.create()     // Catch:{ all -> 0x008f }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x008f }
            boolean r4 = r0.containID     // Catch:{ all -> 0x008f }
            if (r4 == 0) goto L_0x005b
            java.lang.reflect.Field r4 = r0.primaryField     // Catch:{ all -> 0x008f }
            com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r4 = com.thecrackertechnology.dragonterminal.framework.database.SQLTypeParser.getDataType((java.lang.reflect.Field) r4)     // Catch:{ all -> 0x008f }
            java.lang.reflect.Field r5 = r0.primaryField     // Catch:{ all -> 0x008f }
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x008f }
            java.lang.reflect.Field r6 = r0.primaryField     // Catch:{ all -> 0x008f }
            int r5 = r1.getColumnIndex(r5)     // Catch:{ all -> 0x008f }
            com.thecrackertechnology.dragonterminal.framework.database.ValueHelper.setKeyValue(r1, r3, r6, r4, r5)     // Catch:{ all -> 0x008f }
        L_0x005b:
            java.util.Map<java.lang.reflect.Field, com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType> r4 = r0.fieldToDataTypeMap     // Catch:{ all -> 0x008f }
            java.util.Set r4 = r4.keySet()     // Catch:{ all -> 0x008f }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x008f }
        L_0x0065:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x008f }
            if (r5 == 0) goto L_0x0085
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x008f }
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5     // Catch:{ all -> 0x008f }
            java.util.Map<java.lang.reflect.Field, com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType> r6 = r0.fieldToDataTypeMap     // Catch:{ all -> 0x008f }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ all -> 0x008f }
            com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r6 = (com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType) r6     // Catch:{ all -> 0x008f }
            java.lang.String r7 = r5.getName()     // Catch:{ all -> 0x008f }
            int r7 = r1.getColumnIndex(r7)     // Catch:{ all -> 0x008f }
            com.thecrackertechnology.dragonterminal.framework.database.ValueHelper.setKeyValue(r1, r3, r5, r6, r7)     // Catch:{ all -> 0x008f }
            goto L_0x0065
        L_0x0085:
            r2.add(r3)     // Catch:{ all -> 0x008f }
            goto L_0x0030
        L_0x0089:
            if (r1 == 0) goto L_0x008e
            r1.close()
        L_0x008e:
            return r2
        L_0x008f:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0091 }
        L_0x0091:
            r0 = move-exception
            if (r1 == 0) goto L_0x009c
            r1.close()     // Catch:{ all -> 0x0098 }
            goto L_0x009c
        L_0x0098:
            r1 = move-exception
            r9.addSuppressed(r1)
        L_0x009c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.NeoTermDatabase.findAll(java.lang.Class):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0090, code lost:
        if (r9 != null) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0096, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0097, code lost:
        r8.addSuppressed(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009a, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> java.util.List<T> findBeanByWhere(java.lang.Class<?> r8, java.lang.String r9) {
        /*
            r7 = this;
            r7.createTableIfNeed(r8)
            com.thecrackertechnology.dragonterminal.framework.database.bean.TableInfo r0 = com.thecrackertechnology.dragonterminal.framework.database.TableHelper.from(r8)
            java.lang.String r9 = com.thecrackertechnology.dragonterminal.framework.database.SQLStatementHelper.findByWhere(r0, r9)
            com.thecrackertechnology.dragonterminal.framework.database.NeoTermSQLiteConfig r1 = r7.neoTermSQLiteConfig
            boolean r1 = r1.debugMode
            if (r1 == 0) goto L_0x0016
            com.thecrackertechnology.dragonterminal.frontend.logging.NLog r1 = com.thecrackertechnology.dragonterminal.frontend.logging.NLog.INSTANCE
            r1.w(r9)
        L_0x0016:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.database.sqlite.SQLiteDatabase r2 = r7.db
            r3 = 0
            android.database.Cursor r9 = r2.rawQuery(r9, r3)
            if (r9 != 0) goto L_0x002e
            java.util.List r8 = java.util.Collections.emptyList()     // Catch:{ all -> 0x008d }
            if (r9 == 0) goto L_0x002d
            r9.close()
        L_0x002d:
            return r8
        L_0x002e:
            boolean r2 = r9.moveToNext()     // Catch:{ all -> 0x008d }
            if (r2 == 0) goto L_0x0087
            com.thecrackertechnology.dragonterminal.framework.reflection.Reflect r2 = com.thecrackertechnology.dragonterminal.framework.reflection.Reflect.on((java.lang.Class<?>) r8)     // Catch:{ all -> 0x008d }
            com.thecrackertechnology.dragonterminal.framework.reflection.Reflect r2 = r2.create()     // Catch:{ all -> 0x008d }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x008d }
            boolean r3 = r0.containID     // Catch:{ all -> 0x008d }
            if (r3 == 0) goto L_0x0059
            java.lang.reflect.Field r3 = r0.primaryField     // Catch:{ all -> 0x008d }
            com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r3 = com.thecrackertechnology.dragonterminal.framework.database.SQLTypeParser.getDataType((java.lang.reflect.Field) r3)     // Catch:{ all -> 0x008d }
            java.lang.reflect.Field r4 = r0.primaryField     // Catch:{ all -> 0x008d }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x008d }
            java.lang.reflect.Field r5 = r0.primaryField     // Catch:{ all -> 0x008d }
            int r4 = r9.getColumnIndex(r4)     // Catch:{ all -> 0x008d }
            com.thecrackertechnology.dragonterminal.framework.database.ValueHelper.setKeyValue(r9, r2, r5, r3, r4)     // Catch:{ all -> 0x008d }
        L_0x0059:
            java.util.Map<java.lang.reflect.Field, com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType> r3 = r0.fieldToDataTypeMap     // Catch:{ all -> 0x008d }
            java.util.Set r3 = r3.keySet()     // Catch:{ all -> 0x008d }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x008d }
        L_0x0063:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x008d }
            if (r4 == 0) goto L_0x0083
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x008d }
            java.lang.reflect.Field r4 = (java.lang.reflect.Field) r4     // Catch:{ all -> 0x008d }
            java.util.Map<java.lang.reflect.Field, com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType> r5 = r0.fieldToDataTypeMap     // Catch:{ all -> 0x008d }
            java.lang.Object r5 = r5.get(r4)     // Catch:{ all -> 0x008d }
            com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r5 = (com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType) r5     // Catch:{ all -> 0x008d }
            java.lang.String r6 = r4.getName()     // Catch:{ all -> 0x008d }
            int r6 = r9.getColumnIndex(r6)     // Catch:{ all -> 0x008d }
            com.thecrackertechnology.dragonterminal.framework.database.ValueHelper.setKeyValue(r9, r2, r4, r5, r6)     // Catch:{ all -> 0x008d }
            goto L_0x0063
        L_0x0083:
            r1.add(r2)     // Catch:{ all -> 0x008d }
            goto L_0x002e
        L_0x0087:
            if (r9 == 0) goto L_0x008c
            r9.close()
        L_0x008c:
            return r1
        L_0x008d:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x008f }
        L_0x008f:
            r0 = move-exception
            if (r9 == 0) goto L_0x009a
            r9.close()     // Catch:{ all -> 0x0096 }
            goto L_0x009a
        L_0x0096:
            r9 = move-exception
            r8.addSuppressed(r9)
        L_0x009a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.NeoTermDatabase.findBeanByWhere(java.lang.Class, java.lang.String):java.util.List");
    }

    public <T> T findOneBeanByWhere(Class<?> cls, String str) {
        List<?> findBeanByWhere = findBeanByWhere(cls, str);
        if (!findBeanByWhere.isEmpty()) {
            return findBeanByWhere.get(0);
        }
        return null;
    }

    public NeoTermDatabase deleteBeanByWhere(Class<?> cls, String str) {
        createTableIfNeed(cls);
        String deleteByWhere = SQLStatementHelper.deleteByWhere(TableHelper.from(cls), str);
        if (this.neoTermSQLiteConfig.debugMode) {
            NLog.INSTANCE.w(deleteByWhere);
        }
        try {
            this.db.execSQL(deleteByWhere);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public NeoTermDatabase deleteBeanByID(Class<?> cls, Object obj) {
        String str;
        createTableIfNeed(cls);
        TableInfo from = TableHelper.from(cls);
        DatabaseDataType dataType = SQLTypeParser.getDataType(obj.getClass());
        if (dataType == null || from.primaryField == null || SQLTypeParser.matchType(from.primaryField, dataType)) {
            String valueToString = ValueHelper.valueToString(dataType, obj);
            if (from.primaryField == null) {
                str = "_id";
            } else {
                str = from.primaryField.getName() + " = " + valueToString;
            }
            String deleteByWhere = SQLStatementHelper.deleteByWhere(from, str);
            if (this.neoTermSQLiteConfig.debugMode) {
                NLog.INSTANCE.w(deleteByWhere);
            }
            try {
                this.db.execSQL(deleteByWhere);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return this;
        }
        throw new IllegalArgumentException("类型 " + obj.getClass().getName() + " 不是主键的类型,主键的类型应该为 " + from.primaryField.getType().getName());
    }

    public NeoTermDatabase updateByWhere(Class<?> cls, String str, Object obj) {
        createTableIfNeed(cls);
        String updateByWhere = SQLStatementHelper.updateByWhere(TableHelper.from(cls), obj, str);
        if (this.neoTermSQLiteConfig.debugMode) {
            NLog.INSTANCE.d(updateByWhere);
        }
        this.db.execSQL(updateByWhere);
        return this;
    }

    public NeoTermDatabase updateByID(Class<?> cls, Object obj, Object obj2) {
        createTableIfNeed(cls);
        TableInfo from = TableHelper.from(cls);
        StringBuilder sb = new StringBuilder();
        if (from.containID) {
            sb.append(from.primaryField.getName());
            sb.append(" = ");
            sb.append(ValueHelper.valueToString(SQLTypeParser.getDataType(from.primaryField), obj));
        } else {
            sb.append("_id = ");
            sb.append(((Integer) obj).intValue());
        }
        updateByWhere(cls, sb.toString(), obj2);
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00bc, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00bd, code lost:
        if (r1 != null) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c4, code lost:
        r8.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c7, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T findBeanByID(java.lang.Class<?> r8, java.lang.Object r9) {
        /*
            r7 = this;
            r7.createTableIfNeed(r8)
            com.thecrackertechnology.dragonterminal.framework.database.bean.TableInfo r0 = com.thecrackertechnology.dragonterminal.framework.database.TableHelper.from(r8)
            java.lang.Class r1 = r9.getClass()
            com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r1 = com.thecrackertechnology.dragonterminal.framework.database.SQLTypeParser.getDataType((java.lang.Class<?>) r1)
            r2 = 0
            if (r1 != 0) goto L_0x0013
            return r2
        L_0x0013:
            java.lang.reflect.Field r3 = r0.primaryField
            boolean r3 = com.thecrackertechnology.dragonterminal.framework.database.SQLTypeParser.matchType(r3, r1)
            if (r3 != 0) goto L_0x0022
            java.lang.reflect.Field r3 = r0.primaryField
            if (r3 != 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r3 = 0
            goto L_0x0023
        L_0x0022:
            r3 = 1
        L_0x0023:
            if (r3 == 0) goto L_0x00ce
            java.lang.String r1 = com.thecrackertechnology.dragonterminal.framework.database.ValueHelper.valueToString(r1, r9)
            java.lang.reflect.Field r3 = r0.primaryField
            java.lang.String r4 = "_id"
            if (r3 != 0) goto L_0x0031
            r1 = r4
            goto L_0x004b
        L_0x0031:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.reflect.Field r5 = r0.primaryField
            java.lang.String r5 = r5.getName()
            r3.append(r5)
            java.lang.String r5 = " = "
            r3.append(r5)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
        L_0x004b:
            java.lang.String r1 = com.thecrackertechnology.dragonterminal.framework.database.SQLStatementHelper.findByWhere(r0, r1)
            com.thecrackertechnology.dragonterminal.framework.database.NeoTermSQLiteConfig r3 = r7.neoTermSQLiteConfig
            boolean r3 = r3.debugMode
            if (r3 == 0) goto L_0x005a
            com.thecrackertechnology.dragonterminal.frontend.logging.NLog r3 = com.thecrackertechnology.dragonterminal.frontend.logging.NLog.INSTANCE
            r3.w(r1)
        L_0x005a:
            android.database.sqlite.SQLiteDatabase r3 = r7.db
            android.database.Cursor r1 = r3.rawQuery(r1, r2)
            if (r1 == 0) goto L_0x00c8
            int r3 = r1.getCount()     // Catch:{ all -> 0x00ba }
            if (r3 <= 0) goto L_0x00c8
            r1.moveToFirst()     // Catch:{ all -> 0x00ba }
            com.thecrackertechnology.dragonterminal.framework.reflection.Reflect r8 = com.thecrackertechnology.dragonterminal.framework.reflection.Reflect.on((java.lang.Class<?>) r8)     // Catch:{ all -> 0x00ba }
            com.thecrackertechnology.dragonterminal.framework.reflection.Reflect r8 = r8.create()     // Catch:{ all -> 0x00ba }
            java.lang.Object r8 = r8.get()     // Catch:{ all -> 0x00ba }
            java.util.Map<java.lang.reflect.Field, com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType> r2 = r0.fieldToDataTypeMap     // Catch:{ all -> 0x00ba }
            java.util.Set r2 = r2.keySet()     // Catch:{ all -> 0x00ba }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00ba }
        L_0x0081:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x00a1
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00ba }
            java.lang.reflect.Field r3 = (java.lang.reflect.Field) r3     // Catch:{ all -> 0x00ba }
            java.util.Map<java.lang.reflect.Field, com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType> r5 = r0.fieldToDataTypeMap     // Catch:{ all -> 0x00ba }
            java.lang.Object r5 = r5.get(r3)     // Catch:{ all -> 0x00ba }
            com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType r5 = (com.thecrackertechnology.dragonterminal.framework.database.DatabaseDataType) r5     // Catch:{ all -> 0x00ba }
            java.lang.String r6 = r3.getName()     // Catch:{ all -> 0x00ba }
            int r6 = r1.getColumnIndex(r6)     // Catch:{ all -> 0x00ba }
            com.thecrackertechnology.dragonterminal.framework.database.ValueHelper.setKeyValue(r1, r8, r3, r5, r6)     // Catch:{ all -> 0x00ba }
            goto L_0x0081
        L_0x00a1:
            com.thecrackertechnology.dragonterminal.framework.reflection.Reflect r2 = com.thecrackertechnology.dragonterminal.framework.reflection.Reflect.on((java.lang.Object) r8)     // Catch:{ all -> 0x00b3 }
            boolean r3 = r0.containID     // Catch:{ all -> 0x00b3 }
            if (r3 == 0) goto L_0x00af
            java.lang.reflect.Field r0 = r0.primaryField     // Catch:{ all -> 0x00b3 }
            java.lang.String r4 = r0.getName()     // Catch:{ all -> 0x00b3 }
        L_0x00af:
            r2.set(r4, r9)     // Catch:{ all -> 0x00b3 }
            goto L_0x00b4
        L_0x00b3:
        L_0x00b4:
            if (r1 == 0) goto L_0x00b9
            r1.close()
        L_0x00b9:
            return r8
        L_0x00ba:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x00bc }
        L_0x00bc:
            r9 = move-exception
            if (r1 == 0) goto L_0x00c7
            r1.close()     // Catch:{ all -> 0x00c3 }
            goto L_0x00c7
        L_0x00c3:
            r0 = move-exception
            r8.addSuppressed(r0)
        L_0x00c7:
            throw r9
        L_0x00c8:
            if (r1 == 0) goto L_0x00cd
            r1.close()
        L_0x00cd:
            return r2
        L_0x00ce:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Type "
            r1.append(r2)
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getName()
            r1.append(r9)
            java.lang.String r9 = " is not the primary key, expecting "
            r1.append(r9)
            java.lang.reflect.Field r9 = r0.primaryField
            java.lang.Class r9 = r9.getType()
            java.lang.String r9 = r9.getName()
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.framework.NeoTermDatabase.findBeanByID(java.lang.Class, java.lang.Object):java.lang.Object");
    }

    public void vacuum() {
        this.db.execSQL("VACUUM");
    }

    public void destroy() {
        DAO_MAP.remove(this);
        this.neoTermSQLiteConfig = null;
        this.db = null;
    }

    public SQLiteDatabase getDatabase() {
        return this.db;
    }

    private class SQLiteDataBaseHelper extends SQLiteOpenHelper {
        private final boolean defaultDropAllTables;
        private final OnDatabaseUpgradedListener onDatabaseUpgradedListener;

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
        }

        public SQLiteDataBaseHelper(Context context, NeoTermSQLiteConfig neoTermSQLiteConfig) {
            super(context, neoTermSQLiteConfig.getDatabaseName(), (SQLiteDatabase.CursorFactory) null, neoTermSQLiteConfig.getDatabaseVersion());
            this.onDatabaseUpgradedListener = neoTermSQLiteConfig.getOnDatabaseUpgradedListener();
            this.defaultDropAllTables = neoTermSQLiteConfig.isDefaultDropAllTables();
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            OnDatabaseUpgradedListener onDatabaseUpgradedListener2 = this.onDatabaseUpgradedListener;
            if (onDatabaseUpgradedListener2 != null) {
                onDatabaseUpgradedListener2.onDatabaseUpgraded(sQLiteDatabase, i, i2);
            } else if (this.defaultDropAllTables) {
                NeoTermDatabase.this.dropAllTable();
            }
        }
    }
}
