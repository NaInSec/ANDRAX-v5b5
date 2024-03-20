package es.dmoral.coloromatic;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;

public class ColorOMaticUtil {
    public static String getFormattedColorString(int i, boolean z) {
        if (z) {
            return String.format("#%08X", new Object[]{Integer.valueOf(i)});
        }
        return String.format("#%06X", new Object[]{Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK)});
    }

    public static int getThemeAccentColor(Context context) {
        int i;
        if (Build.VERSION.SDK_INT >= 21) {
            i = 16843829;
        } else {
            i = context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.data;
    }
}
