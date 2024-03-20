package de.mrapp.android.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.KeyCharacterMap;
import android.view.ViewConfiguration;

public class DisplayUtil {
    protected static final float PIXEL_DP_RATIO = 160.0f;

    public enum Orientation {
        PORTRAIT,
        LANDSCAPE,
        SQUARE
    }

    public enum DeviceType {
        PHONE("phone"),
        PHABLET("phablet"),
        TABLET("tablet");
        
        private String value;

        private DeviceType(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }

        public static DeviceType fromValue(String str) {
            for (DeviceType deviceType : values()) {
                if (deviceType.getValue().equals(str)) {
                    return deviceType;
                }
            }
            throw new IllegalArgumentException("Invalid enum value: " + str);
        }
    }

    private DisplayUtil() {
    }

    public static int pixelsToDp(Context context, int i) {
        Condition.ensureNotNull(context, "The context may not be null");
        return Math.round(((float) i) / (((float) context.getResources().getDisplayMetrics().densityDpi) / PIXEL_DP_RATIO));
    }

    public static long pixelsToDp(Context context, long j) {
        Condition.ensureNotNull(context, "The context may not be null");
        return (long) Math.round(((float) j) / (((float) context.getResources().getDisplayMetrics().densityDpi) / PIXEL_DP_RATIO));
    }

    public static float pixelsToDp(Context context, float f) {
        Condition.ensureNotNull(context, "The context may not be null");
        return f / (((float) context.getResources().getDisplayMetrics().densityDpi) / PIXEL_DP_RATIO);
    }

    public static double pixelsToDp(Context context, double d) {
        Condition.ensureNotNull(context, "The context may not be null");
        return d / ((double) (((float) context.getResources().getDisplayMetrics().densityDpi) / PIXEL_DP_RATIO));
    }

    public static int dpToPixels(Context context, int i) {
        Condition.ensureNotNull(context, "The context may not be null");
        return Math.round(((float) i) * (((float) context.getResources().getDisplayMetrics().densityDpi) / PIXEL_DP_RATIO));
    }

    public static long dpToPixels(Context context, long j) {
        Condition.ensureNotNull(context, "The context may not be null");
        return (long) Math.round(((float) j) * (((float) context.getResources().getDisplayMetrics().densityDpi) / PIXEL_DP_RATIO));
    }

    public static float dpToPixels(Context context, float f) {
        Condition.ensureNotNull(context, "The context may not be null");
        return f * (((float) context.getResources().getDisplayMetrics().densityDpi) / PIXEL_DP_RATIO);
    }

    public static double dpToPixels(Context context, double d) {
        Condition.ensureNotNull(context, "The context may not be null");
        return d * ((double) (((float) context.getResources().getDisplayMetrics().densityDpi) / PIXEL_DP_RATIO));
    }

    public static Orientation getOrientation(Context context) {
        Condition.ensureNotNull(context, "The context may not be null");
        int i = context.getResources().getConfiguration().orientation;
        if (i == 0) {
            int displayWidth = getDisplayWidth(context);
            int displayHeight = getDisplayHeight(context);
            if (displayWidth > displayHeight) {
                return Orientation.LANDSCAPE;
            }
            if (displayWidth < displayHeight) {
                return Orientation.PORTRAIT;
            }
            return Orientation.SQUARE;
        } else if (i == 2) {
            return Orientation.LANDSCAPE;
        } else {
            if (i == 1) {
                return Orientation.PORTRAIT;
            }
            return Orientation.SQUARE;
        }
    }

    public static DeviceType getDeviceType(Context context) {
        Condition.ensureNotNull(context, "The context may not be null");
        return DeviceType.fromValue(context.getString(R.string.device_type));
    }

    public static int getDisplayWidth(Context context) {
        Condition.ensureNotNull(context, "The context may not be null");
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getDisplayHeight(Context context) {
        Condition.ensureNotNull(context, "The context may not be null");
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static float getDensity(Context context) {
        Condition.ensureNotNull(context, "The context may not be null");
        return context.getResources().getDisplayMetrics().density;
    }

    @Deprecated
    public static int getStatusBarHeight(Context context) {
        Condition.ensureNotNull(context, "The context may not be null");
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @Deprecated
    public static int getNavigationBarHeight(Context context) {
        int i;
        Condition.ensureNotNull(context, "The context may not be null");
        boolean hasPermanentMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            return 0;
        }
        Orientation orientation = getOrientation(context);
        String str = "navigation_bar_height";
        if (getDeviceType(context) == DeviceType.TABLET) {
            Resources resources = context.getResources();
            if (orientation != Orientation.PORTRAIT) {
                str = "navigation_bar_height_landscape";
            }
            i = resources.getIdentifier(str, "dimen", "android");
        } else {
            Resources resources2 = context.getResources();
            if (orientation != Orientation.PORTRAIT) {
                str = "navigation_bar_width";
            }
            i = resources2.getIdentifier(str, "dimen", "android");
        }
        if (i > 0) {
            return context.getResources().getDimensionPixelSize(i);
        }
        return 0;
    }
}
