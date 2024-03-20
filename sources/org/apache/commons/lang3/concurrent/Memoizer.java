package org.apache.commons.lang3.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;

public class Memoizer<I, O> implements Computable<I, O> {
    private final ConcurrentMap<I, Future<O>> cache;
    /* access modifiers changed from: private */
    public final Computable<I, O> computable;
    private final boolean recalculate;

    public Memoizer(Computable<I, O> computable2) {
        this(computable2, false);
    }

    public Memoizer(Computable<I, O> computable2, boolean z) {
        this.cache = new ConcurrentHashMap();
        this.computable = computable2;
        this.recalculate = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r1 = new java.util.concurrent.FutureTask(new org.apache.commons.lang3.concurrent.Memoizer.AnonymousClass1(r3));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public O compute(final I r4) throws java.lang.InterruptedException {
        /*
            r3 = this;
        L_0x0000:
            java.util.concurrent.ConcurrentMap<I, java.util.concurrent.Future<O>> r0 = r3.cache
            java.lang.Object r0 = r0.get(r4)
            java.util.concurrent.Future r0 = (java.util.concurrent.Future) r0
            if (r0 != 0) goto L_0x0022
            org.apache.commons.lang3.concurrent.Memoizer$1 r0 = new org.apache.commons.lang3.concurrent.Memoizer$1
            r0.<init>(r4)
            java.util.concurrent.FutureTask r1 = new java.util.concurrent.FutureTask
            r1.<init>(r0)
            java.util.concurrent.ConcurrentMap<I, java.util.concurrent.Future<O>> r0 = r3.cache
            java.lang.Object r0 = r0.putIfAbsent(r4, r1)
            java.util.concurrent.Future r0 = (java.util.concurrent.Future) r0
            if (r0 != 0) goto L_0x0022
            r1.run()
            r0 = r1
        L_0x0022:
            java.lang.Object r4 = r0.get()     // Catch:{ CancellationException -> 0x003a, ExecutionException -> 0x0027 }
            return r4
        L_0x0027:
            r1 = move-exception
            boolean r2 = r3.recalculate
            if (r2 == 0) goto L_0x0031
            java.util.concurrent.ConcurrentMap<I, java.util.concurrent.Future<O>> r2 = r3.cache
            r2.remove(r4, r0)
        L_0x0031:
            java.lang.Throwable r4 = r1.getCause()
            java.lang.RuntimeException r4 = r3.launderException(r4)
            throw r4
        L_0x003a:
            java.util.concurrent.ConcurrentMap<I, java.util.concurrent.Future<O>> r1 = r3.cache
            r1.remove(r4, r0)
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.concurrent.Memoizer.compute(java.lang.Object):java.lang.Object");
    }

    private RuntimeException launderException(Throwable th) {
        if (th instanceof RuntimeException) {
            return (RuntimeException) th;
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw new IllegalStateException("Unchecked exception", th);
    }
}
