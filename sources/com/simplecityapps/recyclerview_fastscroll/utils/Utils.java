package com.simplecityapps.recyclerview_fastscroll.utils;

import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;

public class Utils {
    public static int toPixels(Resources resources, float f) {
        return (int) (f * resources.getDisplayMetrics().density);
    }

    public static int toScreenPixels(Resources resources, float f) {
        return (int) TypedValue.applyDimension(2, f, resources.getDisplayMetrics());
    }

    public static boolean isRtl(Resources resources) {
        if (Build.VERSION.SDK_INT < 17 || resources.getConfiguration().getLayoutDirection() != 1) {
            return false;
        }
        return true;
    }
}
