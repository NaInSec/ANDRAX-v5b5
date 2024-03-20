package de.mrapp.android.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.util.LinkedList;

public final class PermissionUtil {
    private PermissionUtil() {
    }

    public static boolean isPermissionGranted(Context context, String str) {
        Condition.ensureNotNull(context, "The context may not be null");
        Condition.ensureNotNull(str, "The permission may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The permission may not be empty");
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean areAllPermissionsGranted(Context context, String... strArr) {
        Condition.ensureNotNull(context, "The context may not be null");
        Condition.ensureNotNull(strArr, "The array may not be null");
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (String isPermissionGranted : strArr) {
            if (!isPermissionGranted(context, isPermissionGranted)) {
                return false;
            }
        }
        return true;
    }

    public static String[] getNotGrantedPermissions(Context context, String... strArr) {
        Condition.ensureNotNull(strArr, "The array may not be null");
        LinkedList linkedList = new LinkedList();
        for (String str : strArr) {
            if (!isPermissionGranted(context, str)) {
                linkedList.add(str);
            }
        }
        String[] strArr2 = new String[linkedList.size()];
        linkedList.toArray(strArr2);
        return strArr2;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... strArr) {
        Condition.ensureNotNull(activity, "The activity may not be null");
        Condition.ensureNotNull(strArr, "The array may not be null");
        if (Build.VERSION.SDK_INT >= 23) {
            for (String shouldShowRequestPermissionRationale : strArr) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, shouldShowRequestPermissionRationale)) {
                    return true;
                }
            }
        }
        return false;
    }
}
