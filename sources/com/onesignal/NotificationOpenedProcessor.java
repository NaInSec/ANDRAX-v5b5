package com.onesignal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationOpenedProcessor {
    private static final String TAG = NotificationOpenedProcessor.class.getCanonicalName();

    NotificationOpenedProcessor() {
    }

    static void processFromContext(Context context, Intent intent) {
        if (isOneSignalIntent(intent)) {
            OneSignal.setAppContext(context);
            handleDismissFromActionButtonPress(context, intent);
            processIntent(context, intent);
        }
    }

    private static boolean isOneSignalIntent(Intent intent) {
        return intent.hasExtra(GenerateNotification.BUNDLE_KEY_ONESIGNAL_DATA) || intent.hasExtra("summary") || intent.hasExtra(GenerateNotification.BUNDLE_KEY_ANDROID_NOTIFICATION_ID);
    }

    private static void handleDismissFromActionButtonPress(Context context, Intent intent) {
        if (intent.getBooleanExtra("action_button", false)) {
            NotificationManagerCompat.from(context).cancel(intent.getIntExtra(GenerateNotification.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, 0));
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void processIntent(android.content.Context r8, android.content.Intent r9) {
        /*
            java.lang.String r0 = "androidNotificationId"
            java.lang.String r1 = "onesignalData"
            java.lang.String r2 = "summary"
            java.lang.String r2 = r9.getStringExtra(r2)
            r3 = 0
            java.lang.String r4 = "dismissed"
            boolean r4 = r9.getBooleanExtra(r4, r3)
            r5 = 0
            if (r4 != 0) goto L_0x0048
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0042 }
            java.lang.String r7 = r9.getStringExtra(r1)     // Catch:{ JSONException -> 0x0042 }
            r6.<init>(r7)     // Catch:{ JSONException -> 0x0042 }
            boolean r7 = handleIAMPreviewOpen(r8, r6)     // Catch:{ JSONException -> 0x0040 }
            if (r7 == 0) goto L_0x0024
            return
        L_0x0024:
            int r7 = r9.getIntExtra(r0, r3)     // Catch:{ JSONException -> 0x0040 }
            r6.put(r0, r7)     // Catch:{ JSONException -> 0x0040 }
            java.lang.String r0 = r6.toString()     // Catch:{ JSONException -> 0x0040 }
            r9.putExtra(r1, r0)     // Catch:{ JSONException -> 0x0040 }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0040 }
            java.lang.String r1 = r9.getStringExtra(r1)     // Catch:{ JSONException -> 0x0040 }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0040 }
            org.json.JSONArray r5 = com.onesignal.NotificationBundleProcessor.newJsonArray(r0)     // Catch:{ JSONException -> 0x0040 }
            goto L_0x0049
        L_0x0040:
            r0 = move-exception
            goto L_0x0044
        L_0x0042:
            r0 = move-exception
            r6 = r5
        L_0x0044:
            r0.printStackTrace()
            goto L_0x0049
        L_0x0048:
            r6 = r5
        L_0x0049:
            com.onesignal.OneSignalDbHelper r0 = com.onesignal.OneSignalDbHelper.getInstance(r8)
            if (r4 != 0) goto L_0x0054
            if (r2 == 0) goto L_0x0054
            addChildNotifications(r5, r2, r0)
        L_0x0054:
            markNotificationsConsumed(r8, r9, r0, r4)
            if (r2 != 0) goto L_0x0064
            java.lang.String r1 = "grp"
            java.lang.String r1 = r9.getStringExtra(r1)
            if (r1 == 0) goto L_0x0064
            com.onesignal.NotificationSummaryManager.updateSummaryNotificationAfterChildRemoved(r8, r0, r1, r4)
        L_0x0064:
            if (r4 != 0) goto L_0x0073
            java.lang.String r0 = "from_alert"
            boolean r9 = r9.getBooleanExtra(r0, r3)
            java.lang.String r0 = com.onesignal.OSNotificationFormatHelper.getOSNotificationIdFromJson(r6)
            com.onesignal.OneSignal.handleNotificationOpen(r8, r5, r9, r0)
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationOpenedProcessor.processIntent(android.content.Context, android.content.Intent):void");
    }

    static boolean handleIAMPreviewOpen(Context context, JSONObject jSONObject) {
        String inAppPreviewPushUUID = NotificationBundleProcessor.inAppPreviewPushUUID(jSONObject);
        if (inAppPreviewPushUUID == null) {
            return false;
        }
        OneSignal.startOrResumeApp(context);
        OSInAppMessageController.getController().displayPreviewMessage(inAppPreviewPushUUID);
        return true;
    }

    private static void addChildNotifications(JSONArray jSONArray, String str, OneSignalDbHelper oneSignalDbHelper) {
        Cursor query = oneSignalDbHelper.query(OneSignalDbContract.NotificationTable.TABLE_NAME, new String[]{OneSignalDbContract.NotificationTable.COLUMN_NAME_FULL_DATA}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{str}, (String) null, (String) null, (String) null);
        if (query.getCount() > 1) {
            query.moveToFirst();
            do {
                try {
                    jSONArray.put(new JSONObject(query.getString(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_FULL_DATA))));
                } catch (JSONException unused) {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level, "Could not parse JSON of sub notification in group: " + str);
                }
            } while (query.moveToNext());
        }
        query.close();
    }

    private static void markNotificationsConsumed(Context context, Intent intent, OneSignalDbHelper oneSignalDbHelper, boolean z) {
        String str;
        String stringExtra = intent.getStringExtra("summary");
        String[] strArr = null;
        if (stringExtra != null) {
            boolean equals = stringExtra.equals(OneSignalNotificationManager.getGrouplessSummaryKey());
            if (equals) {
                str = "group_id IS NULL";
            } else {
                strArr = new String[]{stringExtra};
                str = "group_id = ?";
            }
            if (!z && !OneSignal.getClearGroupSummaryClick()) {
                String valueOf = String.valueOf(OneSignalNotificationManager.getMostRecentNotifIdFromGroup(oneSignalDbHelper, stringExtra, equals));
                str = str + " AND android_notification_id = ?";
                strArr = equals ? new String[]{valueOf} : new String[]{stringExtra, valueOf};
            }
        } else {
            str = "android_notification_id = " + intent.getIntExtra(GenerateNotification.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, 0);
        }
        clearStatusBarNotifications(context, oneSignalDbHelper, stringExtra);
        oneSignalDbHelper.update(OneSignalDbContract.NotificationTable.TABLE_NAME, newContentValuesWithConsumed(intent), str, strArr);
        BadgeCountUpdater.update(oneSignalDbHelper, context);
    }

    private static void clearStatusBarNotifications(Context context, OneSignalDbHelper oneSignalDbHelper, String str) {
        if (str != null) {
            NotificationSummaryManager.clearNotificationOnSummaryClick(context, oneSignalDbHelper, str);
        } else if (Build.VERSION.SDK_INT >= 23 && OneSignalNotificationManager.getGrouplessNotifsCount(context).intValue() < 1) {
            OneSignalNotificationManager.getNotificationManager(context).cancel(OneSignalNotificationManager.getGrouplessSummaryId());
        }
    }

    private static ContentValues newContentValuesWithConsumed(Intent intent) {
        ContentValues contentValues = new ContentValues();
        if (intent.getBooleanExtra(OneSignalDbContract.NotificationTable.COLUMN_NAME_DISMISSED, false)) {
            contentValues.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_DISMISSED, 1);
        } else {
            contentValues.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_OPENED, 1);
        }
        return contentValues;
    }
}
