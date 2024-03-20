package com.onesignal;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationSummaryManager {
    NotificationSummaryManager() {
    }

    static void updatePossibleDependentSummaryOnDismiss(Context context, OneSignalDb oneSignalDb, int i) {
        String[] strArr = {OneSignalDbContract.NotificationTable.COLUMN_NAME_GROUP_ID};
        Cursor query = oneSignalDb.query(OneSignalDbContract.NotificationTable.TABLE_NAME, strArr, "android_notification_id = " + i, (String[]) null, (String) null, (String) null, (String) null);
        if (query.moveToFirst()) {
            String string = query.getString(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_GROUP_ID));
            query.close();
            if (string != null) {
                updateSummaryNotificationAfterChildRemoved(context, oneSignalDb, string, true);
                return;
            }
            return;
        }
        query.close();
    }

    static void updateSummaryNotificationAfterChildRemoved(Context context, OneSignalDb oneSignalDb, String str, boolean z) {
        try {
            Cursor internalUpdateSummaryNotificationAfterChildRemoved = internalUpdateSummaryNotificationAfterChildRemoved(context, oneSignalDb, str, z);
            if (internalUpdateSummaryNotificationAfterChildRemoved != null && !internalUpdateSummaryNotificationAfterChildRemoved.isClosed()) {
                internalUpdateSummaryNotificationAfterChildRemoved.close();
            }
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error running updateSummaryNotificationAfterChildRemoved!", th);
        }
    }

    private static Cursor internalUpdateSummaryNotificationAfterChildRemoved(Context context, OneSignalDb oneSignalDb, String str, boolean z) {
        Cursor query = oneSignalDb.query(OneSignalDbContract.NotificationTable.TABLE_NAME, new String[]{OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID, OneSignalDbContract.NotificationTable.COLUMN_NAME_CREATED_TIME}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{str}, (String) null, (String) null, "_id DESC");
        int count = query.getCount();
        if (count == 0) {
            query.close();
            Integer summaryNotificationId = getSummaryNotificationId(oneSignalDb, str);
            if (summaryNotificationId == null) {
                return query;
            }
            OneSignalNotificationManager.getNotificationManager(context).cancel(summaryNotificationId.intValue());
            ContentValues contentValues = new ContentValues();
            contentValues.put(z ? OneSignalDbContract.NotificationTable.COLUMN_NAME_DISMISSED : OneSignalDbContract.NotificationTable.COLUMN_NAME_OPENED, 1);
            oneSignalDb.update(OneSignalDbContract.NotificationTable.TABLE_NAME, contentValues, "android_notification_id = " + summaryNotificationId, (String[]) null);
            return query;
        } else if (count == 1) {
            query.close();
            if (getSummaryNotificationId(oneSignalDb, str) == null) {
                return query;
            }
            restoreSummary(context, str);
            return query;
        } else {
            try {
                query.moveToFirst();
                Long valueOf = Long.valueOf(query.getLong(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_CREATED_TIME)));
                query.close();
                if (getSummaryNotificationId(oneSignalDb, str) == null) {
                    return query;
                }
                NotificationGenerationJob notificationGenerationJob = new NotificationGenerationJob(context);
                notificationGenerationJob.restoring = true;
                notificationGenerationJob.shownTimeStamp = valueOf;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("grp", str);
                notificationGenerationJob.jsonPayload = jSONObject;
                GenerateNotification.updateSummaryNotification(notificationGenerationJob);
                return query;
            } catch (JSONException unused) {
            }
        }
    }

    private static void restoreSummary(Context context, String str) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            cursor = OneSignalDbHelper.getInstance(context).query(OneSignalDbContract.NotificationTable.TABLE_NAME, NotificationRestorer.COLUMNS_FOR_RESTORE, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", strArr, (String) null, (String) null, (String) null);
            NotificationRestorer.showNotificationsFromCursor(context, cursor, 0);
            if (cursor == null || cursor.isClosed()) {
                return;
            }
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
        cursor.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        if (r10.isClosed() == false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        if (r10.isClosed() == false) goto L_0x0045;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Integer getSummaryNotificationId(com.onesignal.OneSignalDb r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "android_notification_id"
            java.lang.String r4 = "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 1"
            r1 = 1
            java.lang.String[] r5 = new java.lang.String[r1]
            r1 = 0
            r5[r1] = r11
            r9 = 0
            java.lang.String r2 = "notification"
            java.lang.String[] r3 = new java.lang.String[]{r0}     // Catch:{ all -> 0x004b }
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r10
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x004b }
            boolean r1 = r10.moveToFirst()     // Catch:{ all -> 0x0049 }
            if (r1 != 0) goto L_0x002e
            r10.close()     // Catch:{ all -> 0x0049 }
            if (r10 == 0) goto L_0x002d
            boolean r11 = r10.isClosed()
            if (r11 != 0) goto L_0x002d
            r10.close()
        L_0x002d:
            return r9
        L_0x002e:
            int r0 = r10.getColumnIndex(r0)     // Catch:{ all -> 0x0049 }
            int r0 = r10.getInt(r0)     // Catch:{ all -> 0x0049 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0049 }
            r10.close()     // Catch:{ all -> 0x0049 }
            if (r10 == 0) goto L_0x006c
            boolean r11 = r10.isClosed()
            if (r11 != 0) goto L_0x006c
        L_0x0045:
            r10.close()
            goto L_0x006c
        L_0x0049:
            r0 = move-exception
            goto L_0x004d
        L_0x004b:
            r0 = move-exception
            r10 = r9
        L_0x004d:
            com.onesignal.OneSignal$LOG_LEVEL r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x006d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x006d }
            r2.<init>()     // Catch:{ all -> 0x006d }
            java.lang.String r3 = "Error getting android notification id for summary notification group: "
            r2.append(r3)     // Catch:{ all -> 0x006d }
            r2.append(r11)     // Catch:{ all -> 0x006d }
            java.lang.String r11 = r2.toString()     // Catch:{ all -> 0x006d }
            com.onesignal.OneSignal.Log(r1, r11, r0)     // Catch:{ all -> 0x006d }
            if (r10 == 0) goto L_0x006c
            boolean r11 = r10.isClosed()
            if (r11 != 0) goto L_0x006c
            goto L_0x0045
        L_0x006c:
            return r9
        L_0x006d:
            r11 = move-exception
            if (r10 == 0) goto L_0x0079
            boolean r0 = r10.isClosed()
            if (r0 != 0) goto L_0x0079
            r10.close()
        L_0x0079:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationSummaryManager.getSummaryNotificationId(com.onesignal.OneSignalDb, java.lang.String):java.lang.Integer");
    }

    static void clearNotificationOnSummaryClick(Context context, OneSignalDbHelper oneSignalDbHelper, String str) {
        Integer summaryNotificationId = getSummaryNotificationId(oneSignalDbHelper, str);
        boolean equals = str.equals(OneSignalNotificationManager.getGrouplessSummaryKey());
        NotificationManager notificationManager = OneSignalNotificationManager.getNotificationManager(context);
        Integer mostRecentNotifIdFromGroup = OneSignalNotificationManager.getMostRecentNotifIdFromGroup(oneSignalDbHelper, str, equals);
        if (mostRecentNotifIdFromGroup == null) {
            return;
        }
        if (OneSignal.getClearGroupSummaryClick()) {
            if (equals) {
                summaryNotificationId = Integer.valueOf(OneSignalNotificationManager.getGrouplessSummaryId());
            }
            if (summaryNotificationId != null) {
                notificationManager.cancel(summaryNotificationId.intValue());
                return;
            }
            return;
        }
        OneSignal.cancelNotification(mostRecentNotifIdFromGroup.intValue());
    }
}
