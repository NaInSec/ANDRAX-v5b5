package com.thecrackertechnology.dragonterminal.backend;

final class ByteQueue {
    private final byte[] mBuffer;
    private int mHead;
    private boolean mOpen = true;
    private int mStoredBytes;

    public ByteQueue(int i) {
        this.mBuffer = new byte[i];
    }

    public synchronized void close() {
        this.mOpen = false;
        notify();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005b, code lost:
        return r3;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:37:0x0001, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(byte[] r9, boolean r10) {
        /*
            r8 = this;
            monitor-enter(r8)
        L_0x0001:
            int r0 = r8.mStoredBytes     // Catch:{ all -> 0x005c }
            r1 = 0
            if (r0 != 0) goto L_0x0012
            boolean r0 = r8.mOpen     // Catch:{ all -> 0x005c }
            if (r0 == 0) goto L_0x0012
            if (r10 == 0) goto L_0x0010
            r8.wait()     // Catch:{ InterruptedException -> 0x0001 }
            goto L_0x0001
        L_0x0010:
            monitor-exit(r8)
            return r1
        L_0x0012:
            boolean r10 = r8.mOpen     // Catch:{ all -> 0x005c }
            if (r10 != 0) goto L_0x0019
            r9 = -1
            monitor-exit(r8)
            return r9
        L_0x0019:
            byte[] r10 = r8.mBuffer     // Catch:{ all -> 0x005c }
            int r10 = r10.length     // Catch:{ all -> 0x005c }
            int r0 = r8.mStoredBytes     // Catch:{ all -> 0x005c }
            if (r10 != r0) goto L_0x0022
            r0 = 1
            goto L_0x0023
        L_0x0022:
            r0 = 0
        L_0x0023:
            int r2 = r9.length     // Catch:{ all -> 0x005c }
            r3 = 0
            r4 = 0
        L_0x0026:
            if (r2 <= 0) goto L_0x0055
            int r5 = r8.mStoredBytes     // Catch:{ all -> 0x005c }
            if (r5 <= 0) goto L_0x0055
            int r5 = r8.mHead     // Catch:{ all -> 0x005c }
            int r5 = r10 - r5
            int r6 = r8.mStoredBytes     // Catch:{ all -> 0x005c }
            int r5 = java.lang.Math.min(r5, r6)     // Catch:{ all -> 0x005c }
            int r5 = java.lang.Math.min(r2, r5)     // Catch:{ all -> 0x005c }
            byte[] r6 = r8.mBuffer     // Catch:{ all -> 0x005c }
            int r7 = r8.mHead     // Catch:{ all -> 0x005c }
            java.lang.System.arraycopy(r6, r7, r9, r4, r5)     // Catch:{ all -> 0x005c }
            int r6 = r8.mHead     // Catch:{ all -> 0x005c }
            int r6 = r6 + r5
            r8.mHead = r6     // Catch:{ all -> 0x005c }
            int r6 = r8.mHead     // Catch:{ all -> 0x005c }
            if (r6 < r10) goto L_0x004c
            r8.mHead = r1     // Catch:{ all -> 0x005c }
        L_0x004c:
            int r6 = r8.mStoredBytes     // Catch:{ all -> 0x005c }
            int r6 = r6 - r5
            r8.mStoredBytes = r6     // Catch:{ all -> 0x005c }
            int r2 = r2 - r5
            int r4 = r4 + r5
            int r3 = r3 + r5
            goto L_0x0026
        L_0x0055:
            if (r0 == 0) goto L_0x005a
            r8.notify()     // Catch:{ all -> 0x005c }
        L_0x005a:
            monitor-exit(r8)
            return r3
        L_0x005c:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.ByteQueue.read(byte[], boolean):int");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000e */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x000e A[LOOP:1: B:7:0x000e->B:47:0x000e, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean write(byte[] r7, int r8, int r9) {
        /*
            r6 = this;
            int r0 = r9 + r8
            int r1 = r7.length
            if (r0 > r1) goto L_0x0065
            if (r9 <= 0) goto L_0x005d
            byte[] r0 = r6.mBuffer
            int r0 = r0.length
            monitor-enter(r6)
        L_0x000b:
            r1 = 1
            if (r9 <= 0) goto L_0x0059
        L_0x000e:
            int r2 = r6.mStoredBytes     // Catch:{ all -> 0x0057 }
            if (r0 != r2) goto L_0x001a
            boolean r2 = r6.mOpen     // Catch:{ all -> 0x0057 }
            if (r2 == 0) goto L_0x001a
            r6.wait()     // Catch:{ InterruptedException -> 0x000e }
            goto L_0x000e
        L_0x001a:
            boolean r2 = r6.mOpen     // Catch:{ all -> 0x0057 }
            r3 = 0
            if (r2 != 0) goto L_0x0021
            monitor-exit(r6)     // Catch:{ all -> 0x0057 }
            return r3
        L_0x0021:
            int r2 = r6.mStoredBytes     // Catch:{ all -> 0x0057 }
            if (r2 != 0) goto L_0x0026
            goto L_0x0027
        L_0x0026:
            r1 = 0
        L_0x0027:
            int r2 = r6.mStoredBytes     // Catch:{ all -> 0x0057 }
            int r2 = r0 - r2
            int r2 = java.lang.Math.min(r9, r2)     // Catch:{ all -> 0x0057 }
            int r9 = r9 - r2
        L_0x0030:
            if (r2 <= 0) goto L_0x0051
            int r3 = r6.mHead     // Catch:{ all -> 0x0057 }
            int r4 = r6.mStoredBytes     // Catch:{ all -> 0x0057 }
            int r3 = r3 + r4
            if (r3 < r0) goto L_0x003e
            int r3 = r3 - r0
            int r4 = r6.mHead     // Catch:{ all -> 0x0057 }
            int r4 = r4 - r3
            goto L_0x0040
        L_0x003e:
            int r4 = r0 - r3
        L_0x0040:
            int r4 = java.lang.Math.min(r4, r2)     // Catch:{ all -> 0x0057 }
            byte[] r5 = r6.mBuffer     // Catch:{ all -> 0x0057 }
            java.lang.System.arraycopy(r7, r8, r5, r3, r4)     // Catch:{ all -> 0x0057 }
            int r8 = r8 + r4
            int r2 = r2 - r4
            int r3 = r6.mStoredBytes     // Catch:{ all -> 0x0057 }
            int r3 = r3 + r4
            r6.mStoredBytes = r3     // Catch:{ all -> 0x0057 }
            goto L_0x0030
        L_0x0051:
            if (r1 == 0) goto L_0x000b
            r6.notify()     // Catch:{ all -> 0x0057 }
            goto L_0x000b
        L_0x0057:
            r7 = move-exception
            goto L_0x005b
        L_0x0059:
            monitor-exit(r6)     // Catch:{ all -> 0x0057 }
            return r1
        L_0x005b:
            monitor-exit(r6)     // Catch:{ all -> 0x0057 }
            throw r7
        L_0x005d:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "length <= 0"
            r7.<init>(r8)
            throw r7
        L_0x0065:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "length + offset > buffer.length"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.ByteQueue.write(byte[], int, int):boolean");
    }
}
