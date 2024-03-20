package com.google.android.gms.common.util;

import android.os.SystemClock;

public class DefaultClock implements Clock {
    private static final DefaultClock zzgk = new DefaultClock();

    public static Clock getInstance() {
        return zzgk;
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public long nanoTime() {
        return System.nanoTime();
    }

    public long currentThreadTimeMillis() {
        return SystemClock.currentThreadTimeMillis();
    }

    private DefaultClock() {
    }
}
