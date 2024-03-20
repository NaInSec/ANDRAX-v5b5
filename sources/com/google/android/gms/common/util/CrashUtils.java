package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.thecrackertechnology.dragonterminal.backend.KeyHandler;

public final class CrashUtils {
    private static final String[] zzge = {"android.", "com.android.", "dalvik.", "java.", "javax."};
    private static DropBoxManager zzgf = null;
    private static boolean zzgg = false;
    private static int zzgh = -1;
    private static int zzgi = 0;
    private static int zzgj = 0;

    public static boolean addDynamiteErrorToDropBox(Context context, Throwable th) {
        return zza(context, th, KeyHandler.KEYMOD_SHIFT);
    }

    private static boolean zza(Context context, Throwable th, int i) {
        try {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(th);
            return false;
        } catch (Exception e) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", e);
            return false;
        }
    }
}
