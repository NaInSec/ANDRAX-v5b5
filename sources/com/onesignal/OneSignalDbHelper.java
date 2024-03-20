package com.onesignal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import com.onesignal.outcomes.OSOutcomeTableProvider;
import java.util.ArrayList;

class OneSignalDbHelper extends SQLiteOpenHelper implements OneSignalDb {
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_NAME = "OneSignal.db";
    static final int DATABASE_VERSION = 8;
    private static final int DB_OPEN_RETRY_BACKOFF = 400;
    private static final int DB_OPEN_RETRY_MAX = 5;
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String INTEGER_PRIMARY_KEY_TYPE = " INTEGER PRIMARY KEY";
    private static final String INT_TYPE = " INTEGER";
    private static final Object LOCK = new Object();
    protected static final String SQL_CREATE_ENTRIES = "CREATE TABLE notification (_id INTEGER PRIMARY KEY,notification_id TEXT,android_notification_id INTEGER,group_id TEXT,collapse_id TEXT,is_summary INTEGER DEFAULT 0,opened INTEGER DEFAULT 0,dismissed INTEGER DEFAULT 0,title TEXT,message TEXT,full_data TEXT,created_time TIMESTAMP DEFAULT (strftime('%s', 'now')),expire_time TIMESTAMP);";
    private static final String SQL_CREATE_IN_APP_MESSAGE_ENTRIES = "CREATE TABLE in_app_message (_id INTEGER PRIMARY KEY,display_quantity INTEGER,last_display INTEGER,message_id TEXT,displayed_in_session INTEGER,click_ids TEXT);";
    protected static final String[] SQL_INDEX_ENTRIES = {OneSignalDbContract.NotificationTable.INDEX_CREATE_NOTIFICATION_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_ANDROID_NOTIFICATION_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_GROUP_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_COLLAPSE_ID, OneSignalDbContract.NotificationTable.INDEX_CREATE_CREATED_TIME, OneSignalDbContract.NotificationTable.INDEX_CREATE_EXPIRE_TIME};
    private static final String TEXT_TYPE = " TEXT";
    private static final String TIMESTAMP_TYPE = " TIMESTAMP";
    private static OSLogger logger = new OSLogWrapper();
    private static OSOutcomeTableProvider outcomeTableProvider = new OSOutcomeTableProvider();
    private static OneSignalDbHelper sInstance;

    private static int getDbVersion() {
        return 8;
    }

    /* access modifiers changed from: package-private */
    public void setOutcomeTableProvider(OSOutcomeTableProvider oSOutcomeTableProvider) {
        outcomeTableProvider = oSOutcomeTableProvider;
    }

    OneSignalDbHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, getDbVersion());
    }

    public static OneSignalDbHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new OneSignalDbHelper(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private SQLiteDatabase getSQLiteDatabase() {
        SQLiteDatabase writableDatabase;
        synchronized (LOCK) {
            try {
                writableDatabase = getWritableDatabase();
            } catch (SQLiteCantOpenDatabaseException e) {
                e = e;
                throw e;
            } catch (SQLiteDatabaseLockedException e2) {
                e = e2;
                throw e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return writableDatabase;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0014 A[Catch:{ SQLiteCantOpenDatabaseException -> 0x000e, SQLiteDatabaseLockedException -> 0x000c, all -> 0x000a }, LOOP:0: B:3:0x0004->B:15:0x0014, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x001b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.database.sqlite.SQLiteDatabase getSQLiteDatabaseWithRetries() {
        /*
            r4 = this;
            java.lang.Object r0 = LOCK
            monitor-enter(r0)
            r1 = 0
        L_0x0004:
            android.database.sqlite.SQLiteDatabase r1 = r4.getSQLiteDatabase()     // Catch:{ SQLiteCantOpenDatabaseException -> 0x000e, SQLiteDatabaseLockedException -> 0x000c }
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return r1
        L_0x000a:
            r1 = move-exception
            goto L_0x001c
        L_0x000c:
            r2 = move-exception
            goto L_0x000f
        L_0x000e:
            r2 = move-exception
        L_0x000f:
            int r1 = r1 + 1
            r3 = 5
            if (r1 >= r3) goto L_0x001b
            int r2 = r1 * 400
            long r2 = (long) r2     // Catch:{ all -> 0x000a }
            android.os.SystemClock.sleep(r2)     // Catch:{ all -> 0x000a }
            goto L_0x0004
        L_0x001b:
            throw r2     // Catch:{ all -> 0x000a }
        L_0x001c:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OneSignalDbHelper.getSQLiteDatabaseWithRetries():android.database.sqlite.SQLiteDatabase");
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        Cursor query;
        synchronized (LOCK) {
            query = getSQLiteDatabaseWithRetries().query(str, strArr, str2, strArr2, str3, str4, str5);
        }
        return query;
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        Cursor query;
        synchronized (LOCK) {
            query = getSQLiteDatabaseWithRetries().query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        }
        return query;
    }

    public void insert(String str, String str2, ContentValues contentValues) {
        synchronized (LOCK) {
            SQLiteDatabase sQLiteDatabaseWithRetries = getSQLiteDatabaseWithRetries();
            try {
                sQLiteDatabaseWithRetries.beginTransaction();
                sQLiteDatabaseWithRetries.insert(str, str2, contentValues);
                sQLiteDatabaseWithRetries.setTransactionSuccessful();
                sQLiteDatabaseWithRetries.endTransaction();
            } catch (SQLException e) {
                logger.error("Error closing transaction! ", e);
            } catch (Throwable th) {
                try {
                    sQLiteDatabaseWithRetries.endTransaction();
                } catch (SQLException e2) {
                    logger.error("Error closing transaction! ", e2);
                }
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r6 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;
        r7 = "Error closing transaction! ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004b, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        com.onesignal.OneSignal.Log(com.onesignal.OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r5 = th;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:7:0x0012, B:26:0x0047] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void insertOrThrow(java.lang.String r5, java.lang.String r6, android.content.ContentValues r7) throws android.database.SQLException {
        /*
            r4 = this;
            java.lang.Object r0 = LOCK
            monitor-enter(r0)
            android.database.sqlite.SQLiteDatabase r1 = r4.getSQLiteDatabaseWithRetries()     // Catch:{ all -> 0x0054 }
            r1.beginTransaction()     // Catch:{ all -> 0x001f }
            r1.insertOrThrow(r5, r6, r7)     // Catch:{ all -> 0x001f }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0042
            r1.endTransaction()     // Catch:{ all -> 0x0016 }
            goto L_0x0042
        L_0x0016:
            r5 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r6 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0054 }
            java.lang.String r7 = "Error closing transaction! "
        L_0x001b:
            com.onesignal.OneSignal.Log(r6, r7, r5)     // Catch:{ all -> 0x0054 }
            goto L_0x0042
        L_0x001f:
            r6 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r7 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0044 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r2.<init>()     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = "Error inserting under table: "
            r2.append(r3)     // Catch:{ all -> 0x0044 }
            r2.append(r5)     // Catch:{ all -> 0x0044 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0044 }
            com.onesignal.OneSignal.Log(r7, r5, r6)     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0042
            r1.endTransaction()     // Catch:{ all -> 0x003c }
            goto L_0x0042
        L_0x003c:
            r5 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r6 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0054 }
            java.lang.String r7 = "Error closing transaction! "
            goto L_0x001b
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            return
        L_0x0044:
            r5 = move-exception
            if (r1 == 0) goto L_0x0053
            r1.endTransaction()     // Catch:{ all -> 0x004b }
            goto L_0x0053
        L_0x004b:
            r6 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r7 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0054 }
            java.lang.String r1 = "Error closing transaction! "
            com.onesignal.OneSignal.Log(r7, r1, r6)     // Catch:{ all -> 0x0054 }
        L_0x0053:
            throw r5     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OneSignalDbHelper.insertOrThrow(java.lang.String, java.lang.String, android.content.ContentValues):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r6 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;
        r7 = "Error closing transaction! ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004d, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        com.onesignal.OneSignal.Log(com.onesignal.OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", r6);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:8:0x0014, B:27:0x0049] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int update(java.lang.String r5, android.content.ContentValues r6, java.lang.String r7, java.lang.String[] r8) {
        /*
            r4 = this;
            java.lang.Object r0 = LOCK
            monitor-enter(r0)
            android.database.sqlite.SQLiteDatabase r1 = r4.getSQLiteDatabaseWithRetries()     // Catch:{ all -> 0x0056 }
            r2 = 0
            r1.beginTransaction()     // Catch:{ all -> 0x0021 }
            int r2 = r1.update(r5, r6, r7, r8)     // Catch:{ all -> 0x0021 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0021 }
            if (r1 == 0) goto L_0x0044
            r1.endTransaction()     // Catch:{ all -> 0x0018 }
            goto L_0x0044
        L_0x0018:
            r5 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r6 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0056 }
            java.lang.String r7 = "Error closing transaction! "
        L_0x001d:
            com.onesignal.OneSignal.Log(r6, r7, r5)     // Catch:{ all -> 0x0056 }
            goto L_0x0044
        L_0x0021:
            r6 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r7 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0046 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0046 }
            r8.<init>()     // Catch:{ all -> 0x0046 }
            java.lang.String r3 = "Error updating table: "
            r8.append(r3)     // Catch:{ all -> 0x0046 }
            r8.append(r5)     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = r8.toString()     // Catch:{ all -> 0x0046 }
            com.onesignal.OneSignal.Log(r7, r5, r6)     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0044
            r1.endTransaction()     // Catch:{ all -> 0x003e }
            goto L_0x0044
        L_0x003e:
            r5 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r6 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0056 }
            java.lang.String r7 = "Error closing transaction! "
            goto L_0x001d
        L_0x0044:
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            return r2
        L_0x0046:
            r5 = move-exception
            if (r1 == 0) goto L_0x0055
            r1.endTransaction()     // Catch:{ all -> 0x004d }
            goto L_0x0055
        L_0x004d:
            r6 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r7 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0056 }
            java.lang.String r8 = "Error closing transaction! "
            com.onesignal.OneSignal.Log(r7, r8, r6)     // Catch:{ all -> 0x0056 }
        L_0x0055:
            throw r5     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OneSignalDbHelper.update(java.lang.String, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r6 = logger;
        r7 = "Error closing transaction! ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0046, code lost:
        if (r1 != null) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004c, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        logger.error("Error closing transaction! ", r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0054, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r5 = e;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:7:0x0012, B:15:0x0022] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void delete(java.lang.String r5, java.lang.String r6, java.lang.String[] r7) {
        /*
            r4 = this;
            java.lang.Object r0 = LOCK
            monitor-enter(r0)
            android.database.sqlite.SQLiteDatabase r1 = r4.getSQLiteDatabaseWithRetries()     // Catch:{ all -> 0x0055 }
            r1.beginTransaction()     // Catch:{ SQLiteException -> 0x0021 }
            r1.delete(r5, r6, r7)     // Catch:{ SQLiteException -> 0x0021 }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x0021 }
            if (r1 == 0) goto L_0x0044
            r1.endTransaction()     // Catch:{ SQLiteException -> 0x0016 }
            goto L_0x0044
        L_0x0016:
            r5 = move-exception
            com.onesignal.OSLogger r6 = logger     // Catch:{ all -> 0x0055 }
            java.lang.String r7 = "Error closing transaction! "
        L_0x001b:
            r6.error(r7, r5)     // Catch:{ all -> 0x0055 }
            goto L_0x0044
        L_0x001f:
            r5 = move-exception
            goto L_0x0046
        L_0x0021:
            r6 = move-exception
            com.onesignal.OSLogger r7 = logger     // Catch:{ all -> 0x001f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x001f }
            r2.<init>()     // Catch:{ all -> 0x001f }
            java.lang.String r3 = "Error deleting on table: "
            r2.append(r3)     // Catch:{ all -> 0x001f }
            r2.append(r5)     // Catch:{ all -> 0x001f }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x001f }
            r7.error(r5, r6)     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0044
            r1.endTransaction()     // Catch:{ SQLiteException -> 0x003e }
            goto L_0x0044
        L_0x003e:
            r5 = move-exception
            com.onesignal.OSLogger r6 = logger     // Catch:{ all -> 0x0055 }
            java.lang.String r7 = "Error closing transaction! "
            goto L_0x001b
        L_0x0044:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return
        L_0x0046:
            if (r1 == 0) goto L_0x0054
            r1.endTransaction()     // Catch:{ SQLiteException -> 0x004c }
            goto L_0x0054
        L_0x004c:
            r6 = move-exception
            com.onesignal.OSLogger r7 = logger     // Catch:{ all -> 0x0055 }
            java.lang.String r1 = "Error closing transaction! "
            r7.error(r1, r6)     // Catch:{ all -> 0x0055 }
        L_0x0054:
            throw r5     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OneSignalDbHelper.delete(java.lang.String, java.lang.String, java.lang.String[]):void");
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        sQLiteDatabase.execSQL(OSOutcomeTableProvider.SQL_CREATE_OUTCOME_ENTRIES_V3);
        sQLiteDatabase.execSQL(OSOutcomeTableProvider.SQL_CREATE_UNIQUE_OUTCOME_ENTRIES_V2);
        sQLiteDatabase.execSQL(SQL_CREATE_IN_APP_MESSAGE_ENTRIES);
        for (String execSQL : SQL_INDEX_ENTRIES) {
            sQLiteDatabase.execSQL(execSQL);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "OneSignal Database onUpgrade from: " + i + " to: " + i2);
        try {
            internalOnUpgrade(sQLiteDatabase, i);
        } catch (SQLiteException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error in upgrade, migration may have already run! Skipping!", e);
        }
    }

    private synchronized void internalOnUpgrade(SQLiteDatabase sQLiteDatabase, int i) {
        if (i < 2) {
            try {
                upgradeToV2(sQLiteDatabase);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (i < 3) {
            upgradeToV3(sQLiteDatabase);
        }
        if (i < 4) {
            upgradeToV4(sQLiteDatabase);
        }
        if (i < 5) {
            upgradeToV5(sQLiteDatabase);
        }
        if (i == 5) {
            upgradeFromV5ToV6(sQLiteDatabase);
        }
        if (i < 7) {
            upgradeToV7(sQLiteDatabase);
        }
        if (i < 8) {
            upgradeToV8(sQLiteDatabase);
        }
    }

    private static void upgradeToV2(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, "ALTER TABLE notification ADD COLUMN collapse_id TEXT;");
        safeExecSQL(sQLiteDatabase, OneSignalDbContract.NotificationTable.INDEX_CREATE_GROUP_ID);
    }

    private static void upgradeToV3(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, "ALTER TABLE notification ADD COLUMN expire_time TIMESTAMP;");
        safeExecSQL(sQLiteDatabase, "UPDATE notification SET expire_time = created_time + 259200;");
        safeExecSQL(sQLiteDatabase, OneSignalDbContract.NotificationTable.INDEX_CREATE_EXPIRE_TIME);
    }

    private static void upgradeToV4(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, OSOutcomeTableProvider.SQL_CREATE_OUTCOME_ENTRIES_V1);
    }

    private static void upgradeToV5(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, OSOutcomeTableProvider.SQL_CREATE_UNIQUE_OUTCOME_ENTRIES_V1);
        upgradeFromV5ToV6(sQLiteDatabase);
    }

    private static void upgradeFromV5ToV6(SQLiteDatabase sQLiteDatabase) {
        outcomeTableProvider.upgradeOutcomeTableRevision1To2(sQLiteDatabase);
    }

    private static void upgradeToV7(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, SQL_CREATE_IN_APP_MESSAGE_ENTRIES);
    }

    private synchronized void upgradeToV8(SQLiteDatabase sQLiteDatabase) {
        outcomeTableProvider.upgradeOutcomeTableRevision2To3(sQLiteDatabase);
        outcomeTableProvider.upgradeCacheOutcomeTableRevision1To2(sQLiteDatabase);
    }

    private static void safeExecSQL(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL(str);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: finally extract failed */
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "SDK version rolled back! Clearing OneSignal.db as it could be in an unexpected state.");
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", (String[]) null);
        try {
            ArrayList<String> arrayList = new ArrayList<>(rawQuery.getCount());
            while (rawQuery.moveToNext()) {
                arrayList.add(rawQuery.getString(0));
            }
            for (String str : arrayList) {
                if (!str.startsWith("sqlite_")) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
            }
            rawQuery.close();
            onCreate(sQLiteDatabase);
        } catch (Throwable th) {
            rawQuery.close();
            throw th;
        }
    }

    static StringBuilder recentUninteractedWithNotificationsWhere() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        StringBuilder sb = new StringBuilder("created_time > " + (currentTimeMillis - 604800) + " AND " + OneSignalDbContract.NotificationTable.COLUMN_NAME_DISMISSED + " = 0 AND " + OneSignalDbContract.NotificationTable.COLUMN_NAME_OPENED + " = 0 AND " + OneSignalDbContract.NotificationTable.COLUMN_NAME_IS_SUMMARY + " = 0");
        if (OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_RESTORE_TTL_FILTER, true)) {
            sb.append(" AND expire_time > " + currentTimeMillis);
        }
        return sb;
    }

    static void cleanOutcomeDatabaseTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete(OSOutcomeTableProvider.OUTCOME_EVENT_TABLE, (String) null, (String[]) null);
    }
}
