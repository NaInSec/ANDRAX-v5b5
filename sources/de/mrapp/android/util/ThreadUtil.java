package de.mrapp.android.util;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtil {
    private ThreadUtil() {
    }

    public static void runOnUiThread(Runnable runnable) {
        Condition.ensureNotNull(runnable, "The runnable may not be null");
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
