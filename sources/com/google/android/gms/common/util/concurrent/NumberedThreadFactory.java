package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberedThreadFactory implements ThreadFactory {
    private final int priority;
    private final ThreadFactory zzhp;
    private final String zzhq;
    private final AtomicInteger zzhr;

    public NumberedThreadFactory(String str) {
        this(str, 0);
    }

    private NumberedThreadFactory(String str, int i) {
        this.zzhr = new AtomicInteger();
        this.zzhp = Executors.defaultThreadFactory();
        this.zzhq = (String) Preconditions.checkNotNull(str, "Name must not be null");
        this.priority = 0;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.zzhp.newThread(new zza(runnable, 0));
        String str = this.zzhq;
        int andIncrement = this.zzhr.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13);
        sb.append(str);
        sb.append("[");
        sb.append(andIncrement);
        sb.append("]");
        newThread.setName(sb.toString());
        return newThread;
    }
}
