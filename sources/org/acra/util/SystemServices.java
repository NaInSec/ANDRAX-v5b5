package org.acra.util;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.os.DropBoxManager;
import android.telephony.TelephonyManager;
import com.onesignal.OneSignalDbContract;

public final class SystemServices {
    private SystemServices() {
    }

    public static TelephonyManager getTelephonyManager(Context context) throws ServiceNotReachedException {
        return (TelephonyManager) getService(context, "phone");
    }

    public static DropBoxManager getDropBoxManager(Context context) throws ServiceNotReachedException {
        return (DropBoxManager) getService(context, "dropbox");
    }

    public static NotificationManager getNotificationManager(Context context) throws ServiceNotReachedException {
        return (NotificationManager) getService(context, OneSignalDbContract.NotificationTable.TABLE_NAME);
    }

    public static ActivityManager getActivityManager(Context context) throws ServiceNotReachedException {
        return (ActivityManager) getService(context, "activity");
    }

    private static Object getService(Context context, String str) throws ServiceNotReachedException {
        Object systemService = context.getSystemService(str);
        if (systemService != null) {
            return systemService;
        }
        throw new ServiceNotReachedException("Unable to load SystemService " + str);
    }

    static class ServiceNotReachedException extends Exception {
        ServiceNotReachedException(String str) {
            super(str);
        }
    }
}
