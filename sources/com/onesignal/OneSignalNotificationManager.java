package com.onesignal;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.onesignal.OneSignalDbContract;
import java.util.ArrayList;
import java.util.Iterator;

public class OneSignalNotificationManager {
    private static final int GROUPLESS_SUMMARY_ID = -718463522;
    private static final String GROUPLESS_SUMMARY_KEY = "os_group_undefined";

    static int getGrouplessSummaryId() {
        return GROUPLESS_SUMMARY_ID;
    }

    static String getGrouplessSummaryKey() {
        return GROUPLESS_SUMMARY_KEY;
    }

    static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME);
    }

    static Integer getGrouplessNotifsCount(Context context) {
        int i = 0;
        for (StatusBarNotification statusBarNotification : getActiveNotifications(context)) {
            if (!NotificationCompat.isGroupSummary(statusBarNotification.getNotification()) && GROUPLESS_SUMMARY_KEY.equals(statusBarNotification.getNotification().getGroup())) {
                i++;
            }
        }
        return Integer.valueOf(i);
    }

    static StatusBarNotification[] getActiveNotifications(Context context) {
        try {
            return getNotificationManager(context).getActiveNotifications();
        } catch (Throwable unused) {
            return new StatusBarNotification[0];
        }
    }

    static ArrayList<StatusBarNotification> getActiveGrouplessNotifications(Context context) {
        ArrayList<StatusBarNotification> arrayList = new ArrayList<>();
        for (StatusBarNotification statusBarNotification : getActiveNotifications(context)) {
            Notification notification = statusBarNotification.getNotification();
            boolean isGroupSummary = NotificationLimitManager.isGroupSummary(statusBarNotification);
            boolean z = notification.getGroup() == null || notification.getGroup().equals(getGrouplessSummaryKey());
            if (!isGroupSummary && z) {
                arrayList.add(statusBarNotification);
            }
        }
        return arrayList;
    }

    static void assignGrouplessNotifications(Context context, ArrayList<StatusBarNotification> arrayList) {
        Notification.Builder builder;
        Iterator<StatusBarNotification> it = arrayList.iterator();
        while (it.hasNext()) {
            StatusBarNotification next = it.next();
            if (Build.VERSION.SDK_INT >= 24) {
                builder = Notification.Builder.recoverBuilder(context, next.getNotification());
            } else {
                builder = new Notification.Builder(context);
            }
            NotificationManagerCompat.from(context).notify(next.getId(), builder.setGroup(GROUPLESS_SUMMARY_KEY).build());
        }
    }

    static Integer getMostRecentNotifIdFromGroup(OneSignalDbHelper oneSignalDbHelper, String str, boolean z) {
        String str2 = z ? "group_id IS NULL" : "group_id = ?";
        Cursor query = oneSignalDbHelper.query(OneSignalDbContract.NotificationTable.TABLE_NAME, (String[]) null, str2 + " AND dismissed = 0 AND opened = 0 AND is_summary = 0", z ? null : new String[]{str}, (String) null, (String) null, "created_time DESC", "1");
        if (!query.moveToFirst()) {
            query.close();
            return null;
        }
        Integer valueOf = Integer.valueOf(query.getInt(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID)));
        query.close();
        return valueOf;
    }
}
