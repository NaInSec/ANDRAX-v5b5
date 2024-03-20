package com.onesignal;

import android.content.ContentValues;
import com.onesignal.OneSignalDbContract;
import java.util.Set;

class OSInAppMessageRepository {
    static final long IAM_CACHE_DATA_LIFETIME = 15552000;
    private final OneSignalDbHelper dbHelper;

    OSInAppMessageRepository(OneSignalDbHelper oneSignalDbHelper) {
        this.dbHelper = oneSignalDbHelper;
    }

    /* access modifiers changed from: package-private */
    public synchronized void saveInAppMessage(OSInAppMessage oSInAppMessage) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(OneSignalDbContract.InAppMessageTable.COLUMN_NAME_MESSAGE_ID, oSInAppMessage.messageId);
        contentValues.put(OneSignalDbContract.InAppMessageTable.COLUMN_NAME_DISPLAY_QUANTITY, Integer.valueOf(oSInAppMessage.getRedisplayStats().getDisplayQuantity()));
        contentValues.put(OneSignalDbContract.InAppMessageTable.COLUMN_NAME_LAST_DISPLAY, Long.valueOf(oSInAppMessage.getRedisplayStats().getLastDisplayTime()));
        contentValues.put(OneSignalDbContract.InAppMessageTable.COLUMN_CLICK_IDS, oSInAppMessage.getClickedClickIds().toString());
        contentValues.put(OneSignalDbContract.InAppMessageTable.COLUMN_DISPLAYED_IN_SESSION, Boolean.valueOf(oSInAppMessage.isDisplayedInSession()));
        if (this.dbHelper.update(OneSignalDbContract.InAppMessageTable.TABLE_NAME, contentValues, "message_id = ?", new String[]{oSInAppMessage.messageId}) == 0) {
            this.dbHelper.insert(OneSignalDbContract.InAppMessageTable.TABLE_NAME, (String) null, contentValues);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0074, code lost:
        if (r1.isClosed() == false) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0076, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008a, code lost:
        if (r1.isClosed() == false) goto L_0x0076;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<com.onesignal.OSInAppMessage> getCachedInAppMessages() {
        /*
            r10 = this;
            monitor-enter(r10)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x009b }
            r0.<init>()     // Catch:{ all -> 0x009b }
            r1 = 0
            com.onesignal.OneSignalDbHelper r2 = r10.dbHelper     // Catch:{ JSONException -> 0x007c }
            java.lang.String r3 = "in_app_message"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ JSONException -> 0x007c }
            boolean r2 = r1.moveToFirst()     // Catch:{ JSONException -> 0x007c }
            if (r2 == 0) goto L_0x006e
        L_0x001b:
            java.lang.String r2 = "message_id"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ JSONException -> 0x007c }
            java.lang.String r2 = r1.getString(r2)     // Catch:{ JSONException -> 0x007c }
            java.lang.String r3 = "click_ids"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ JSONException -> 0x007c }
            java.lang.String r3 = r1.getString(r3)     // Catch:{ JSONException -> 0x007c }
            java.lang.String r4 = "display_quantity"
            int r4 = r1.getColumnIndex(r4)     // Catch:{ JSONException -> 0x007c }
            int r4 = r1.getInt(r4)     // Catch:{ JSONException -> 0x007c }
            java.lang.String r5 = "last_display"
            int r5 = r1.getColumnIndex(r5)     // Catch:{ JSONException -> 0x007c }
            long r5 = r1.getLong(r5)     // Catch:{ JSONException -> 0x007c }
            java.lang.String r7 = "displayed_in_session"
            int r7 = r1.getColumnIndex(r7)     // Catch:{ JSONException -> 0x007c }
            int r7 = r1.getInt(r7)     // Catch:{ JSONException -> 0x007c }
            r8 = 1
            if (r7 != r8) goto L_0x0051
            goto L_0x0052
        L_0x0051:
            r8 = 0
        L_0x0052:
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch:{ JSONException -> 0x007c }
            r7.<init>(r3)     // Catch:{ JSONException -> 0x007c }
            java.util.Set r3 = com.onesignal.OSUtils.newStringSetFromJSONArray(r7)     // Catch:{ JSONException -> 0x007c }
            com.onesignal.OSInAppMessage r7 = new com.onesignal.OSInAppMessage     // Catch:{ JSONException -> 0x007c }
            com.onesignal.OSInAppMessageRedisplayStats r9 = new com.onesignal.OSInAppMessageRedisplayStats     // Catch:{ JSONException -> 0x007c }
            r9.<init>(r4, r5)     // Catch:{ JSONException -> 0x007c }
            r7.<init>(r2, r3, r8, r9)     // Catch:{ JSONException -> 0x007c }
            r0.add(r7)     // Catch:{ JSONException -> 0x007c }
            boolean r2 = r1.moveToNext()     // Catch:{ JSONException -> 0x007c }
            if (r2 != 0) goto L_0x001b
        L_0x006e:
            if (r1 == 0) goto L_0x008d
            boolean r2 = r1.isClosed()     // Catch:{ all -> 0x009b }
            if (r2 != 0) goto L_0x008d
        L_0x0076:
            r1.close()     // Catch:{ all -> 0x009b }
            goto L_0x008d
        L_0x007a:
            r0 = move-exception
            goto L_0x008f
        L_0x007c:
            r2 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x007a }
            java.lang.String r4 = "Generating JSONArray from iam click ids:JSON Failed."
            com.onesignal.OneSignal.Log(r3, r4, r2)     // Catch:{ all -> 0x007a }
            if (r1 == 0) goto L_0x008d
            boolean r2 = r1.isClosed()     // Catch:{ all -> 0x009b }
            if (r2 != 0) goto L_0x008d
            goto L_0x0076
        L_0x008d:
            monitor-exit(r10)
            return r0
        L_0x008f:
            if (r1 == 0) goto L_0x009a
            boolean r2 = r1.isClosed()     // Catch:{ all -> 0x009b }
            if (r2 != 0) goto L_0x009a
            r1.close()     // Catch:{ all -> 0x009b }
        L_0x009a:
            throw r0     // Catch:{ all -> 0x009b }
        L_0x009b:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSInAppMessageRepository.getCachedInAppMessages():java.util.List");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0074, code lost:
        if (r12.isClosed() == false) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void cleanCachedInAppMessages() {
        /*
            r13 = this;
            monitor-enter(r13)
            java.lang.String r0 = "message_id"
            java.lang.String r1 = "click_ids"
            java.lang.String[] r4 = new java.lang.String[]{r0, r1}     // Catch:{ all -> 0x00b8 }
            java.lang.String r0 = "last_display < ?"
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00b8 }
            r5 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r5
            r5 = 15552000(0xed4e00, double:7.683709E-317)
            long r1 = r1 - r5
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00b8 }
            r2 = 1
            java.lang.String[] r10 = new java.lang.String[r2]     // Catch:{ all -> 0x00b8 }
            r2 = 0
            r10[r2] = r1     // Catch:{ all -> 0x00b8 }
            java.util.Set r1 = com.onesignal.OSUtils.newConcurrentSet()     // Catch:{ all -> 0x00b8 }
            java.util.Set r11 = com.onesignal.OSUtils.newConcurrentSet()     // Catch:{ all -> 0x00b8 }
            r12 = 0
            com.onesignal.OneSignalDbHelper r2 = r13.dbHelper     // Catch:{ JSONException -> 0x0090 }
            java.lang.String r3 = "in_app_message"
            r7 = 0
            r8 = 0
            r9 = 0
            r5 = r0
            r6 = r10
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ JSONException -> 0x0090 }
            if (r12 == 0) goto L_0x007a
            int r2 = r12.getCount()     // Catch:{ JSONException -> 0x0090 }
            if (r2 != 0) goto L_0x003f
            goto L_0x007a
        L_0x003f:
            boolean r2 = r12.moveToFirst()     // Catch:{ JSONException -> 0x0090 }
            if (r2 == 0) goto L_0x006e
        L_0x0045:
            java.lang.String r2 = "message_id"
            int r2 = r12.getColumnIndex(r2)     // Catch:{ JSONException -> 0x0090 }
            java.lang.String r2 = r12.getString(r2)     // Catch:{ JSONException -> 0x0090 }
            java.lang.String r3 = "click_ids"
            int r3 = r12.getColumnIndex(r3)     // Catch:{ JSONException -> 0x0090 }
            java.lang.String r3 = r12.getString(r3)     // Catch:{ JSONException -> 0x0090 }
            r1.add(r2)     // Catch:{ JSONException -> 0x0090 }
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0090 }
            r2.<init>(r3)     // Catch:{ JSONException -> 0x0090 }
            java.util.Set r2 = com.onesignal.OSUtils.newStringSetFromJSONArray(r2)     // Catch:{ JSONException -> 0x0090 }
            r11.addAll(r2)     // Catch:{ JSONException -> 0x0090 }
            boolean r2 = r12.moveToNext()     // Catch:{ JSONException -> 0x0090 }
            if (r2 != 0) goto L_0x0045
        L_0x006e:
            if (r12 == 0) goto L_0x009d
            boolean r2 = r12.isClosed()     // Catch:{ all -> 0x00b8 }
            if (r2 != 0) goto L_0x009d
        L_0x0076:
            r12.close()     // Catch:{ all -> 0x00b8 }
            goto L_0x009d
        L_0x007a:
            com.onesignal.OneSignal$LOG_LEVEL r2 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ JSONException -> 0x0090 }
            java.lang.String r3 = "Attempted to clean 6 month old IAM data, but none exists!"
            com.onesignal.OneSignal.onesignalLog(r2, r3)     // Catch:{ JSONException -> 0x0090 }
            if (r12 == 0) goto L_0x008c
            boolean r0 = r12.isClosed()     // Catch:{ all -> 0x00b8 }
            if (r0 != 0) goto L_0x008c
            r12.close()     // Catch:{ all -> 0x00b8 }
        L_0x008c:
            monitor-exit(r13)
            return
        L_0x008e:
            r0 = move-exception
            goto L_0x00ac
        L_0x0090:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ all -> 0x008e }
            if (r12 == 0) goto L_0x009d
            boolean r2 = r12.isClosed()     // Catch:{ all -> 0x00b8 }
            if (r2 != 0) goto L_0x009d
            goto L_0x0076
        L_0x009d:
            com.onesignal.OneSignalDbHelper r2 = r13.dbHelper     // Catch:{ all -> 0x00b8 }
            java.lang.String r3 = "in_app_message"
            r2.delete(r3, r0, r10)     // Catch:{ all -> 0x00b8 }
            r13.cleanInAppMessageIds(r1)     // Catch:{ all -> 0x00b8 }
            r13.cleanInAppMessageClickedClickIds(r11)     // Catch:{ all -> 0x00b8 }
            monitor-exit(r13)
            return
        L_0x00ac:
            if (r12 == 0) goto L_0x00b7
            boolean r1 = r12.isClosed()     // Catch:{ all -> 0x00b8 }
            if (r1 != 0) goto L_0x00b7
            r12.close()     // Catch:{ all -> 0x00b8 }
        L_0x00b7:
            throw r0     // Catch:{ all -> 0x00b8 }
        L_0x00b8:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSInAppMessageRepository.cleanCachedInAppMessages():void");
    }

    private void cleanInAppMessageIds(Set<String> set) {
        if (set != null && set.size() > 0) {
            Set<String> stringSet = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_DISMISSED_IAMS, (Set<String>) null);
            Set<String> stringSet2 = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_IMPRESSIONED_IAMS, (Set<String>) null);
            if (stringSet != null && stringSet.size() > 0) {
                stringSet.removeAll(set);
                OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_DISMISSED_IAMS, stringSet);
            }
            if (stringSet2 != null && stringSet2.size() > 0) {
                stringSet2.removeAll(set);
                OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_IMPRESSIONED_IAMS, stringSet2);
            }
        }
    }

    private void cleanInAppMessageClickedClickIds(Set<String> set) {
        Set<String> stringSet;
        if (set != null && set.size() > 0 && (stringSet = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_CLICKED_CLICK_IDS_IAMS, (Set<String>) null)) != null && stringSet.size() > 0) {
            stringSet.removeAll(set);
            OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_CLICKED_CLICK_IDS_IAMS, stringSet);
        }
    }
}
