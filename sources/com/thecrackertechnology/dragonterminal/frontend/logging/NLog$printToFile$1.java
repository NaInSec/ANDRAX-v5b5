package com.thecrackertechnology.dragonterminal.frontend.logging;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: NLog.kt */
final class NLog$printToFile$1 implements Runnable {
    final /* synthetic */ String $content;
    final /* synthetic */ String $fullPath;
    final /* synthetic */ String $tag;

    NLog$printToFile$1(String str, String str2, String str3) {
        this.$fullPath = str;
        this.$content = str2;
        this.$tag = str3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0066 A[SYNTHETIC, Splitter:B:17:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006d A[SYNTHETIC, Splitter:B:21:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r7 = this;
            java.lang.String r0 = "log to "
            r1 = 0
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            java.io.FileWriter r3 = new java.io.FileWriter     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            java.lang.String r4 = r7.$fullPath     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            r5 = 1
            r3.<init>(r4, r5)     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            java.io.Writer r3 = (java.io.Writer) r3     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            java.lang.String r1 = r7.$content     // Catch:{ IOException -> 0x003d }
            r2.write(r1)     // Catch:{ IOException -> 0x003d }
            java.lang.String r1 = r7.$tag     // Catch:{ IOException -> 0x003d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x003d }
            r3.<init>()     // Catch:{ IOException -> 0x003d }
            r3.append(r0)     // Catch:{ IOException -> 0x003d }
            java.lang.String r4 = r7.$fullPath     // Catch:{ IOException -> 0x003d }
            r3.append(r4)     // Catch:{ IOException -> 0x003d }
            java.lang.String r4 = " success!"
            r3.append(r4)     // Catch:{ IOException -> 0x003d }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x003d }
            android.util.Log.d(r1, r3)     // Catch:{ IOException -> 0x003d }
            r2.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0069
        L_0x0038:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0069
        L_0x003d:
            r1 = move-exception
            goto L_0x0046
        L_0x003f:
            r0 = move-exception
            r2 = r1
            goto L_0x006b
        L_0x0042:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L_0x0046:
            r1.printStackTrace()     // Catch:{ all -> 0x006a }
            java.lang.String r1 = r7.$tag     // Catch:{ all -> 0x006a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006a }
            r3.<init>()     // Catch:{ all -> 0x006a }
            r3.append(r0)     // Catch:{ all -> 0x006a }
            java.lang.String r0 = r7.$fullPath     // Catch:{ all -> 0x006a }
            r3.append(r0)     // Catch:{ all -> 0x006a }
            java.lang.String r0 = " failed!"
            r3.append(r0)     // Catch:{ all -> 0x006a }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x006a }
            android.util.Log.e(r1, r0)     // Catch:{ all -> 0x006a }
            if (r2 == 0) goto L_0x0069
            r2.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0069:
            return
        L_0x006a:
            r0 = move-exception
        L_0x006b:
            if (r2 == 0) goto L_0x0075
            r2.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0075:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.frontend.logging.NLog$printToFile$1.run():void");
    }
}
