package com.thecrackertechnology.dragonterminal.utils;

import com.thecrackertechnology.dragonterminal.backend.KeyHandler;
import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\b¨\u0006\u0010"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/FileUtils;", "", "()V", "formatSizeInKB", "", "size", "", "readFile", "", "path", "Ljava/io/File;", "writeFile", "", "inputStream", "Ljava/io/InputStream;", "bytes", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: FileUtils.kt */
public final class FileUtils {
    public static final FileUtils INSTANCE = new FileUtils();

    private FileUtils() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean writeFile(java.io.File r5, byte[] r6) {
        /*
            r4 = this;
            java.lang.String r0 = "path"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "bytes"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            boolean r0 = r5.exists()
            if (r0 != 0) goto L_0x0013
            r5.createNewFile()
        L_0x0013:
            r0 = 0
            r1 = 1
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0034 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x0034 }
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ Exception -> 0x0034 }
            r5 = 0
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch:{ Exception -> 0x0034 }
            r3 = r2
            java.io.FileOutputStream r3 = (java.io.FileOutputStream) r3     // Catch:{ all -> 0x002d }
            r3.write(r6)     // Catch:{ all -> 0x002d }
            r3.flush()     // Catch:{ all -> 0x002d }
            kotlin.io.CloseableKt.closeFinally(r2, r5)     // Catch:{ Exception -> 0x0034 }
            r0 = 1
            goto L_0x0055
        L_0x002d:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x002f }
        L_0x002f:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r5)     // Catch:{ Exception -> 0x0034 }
            throw r6     // Catch:{ Exception -> 0x0034 }
        L_0x0034:
            r5 = move-exception
            com.thecrackertechnology.dragonterminal.frontend.logging.NLog r6 = com.thecrackertechnology.dragonterminal.frontend.logging.NLog.INSTANCE
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to write file: "
            r2.append(r3)
            java.lang.String r5 = r5.getLocalizedMessage()
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            r1[r0] = r5
            java.lang.String r5 = "FileUtils"
            r6.e(r5, r1)
        L_0x0055:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.utils.FileUtils.writeFile(java.io.File, byte[]):boolean");
    }

    public final boolean writeFile(File file, InputStream inputStream) {
        Intrinsics.checkParameterIsNotNull(file, "path");
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        byte[] bArr = new byte[inputStream.available()];
        inputStream.read(bArr);
        return writeFile(file, bArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] readFile(java.io.File r4) {
        /*
            r3 = this;
            java.lang.String r0 = "path"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            boolean r0 = r4.canRead()
            r1 = 0
            if (r0 != 0) goto L_0x000d
            return r1
        L_0x000d:
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r4)
            java.io.Closeable r0 = (java.io.Closeable) r0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r4 = r0
            java.io.FileInputStream r4 = (java.io.FileInputStream) r4     // Catch:{ all -> 0x0026 }
            int r2 = r4.available()     // Catch:{ all -> 0x0026 }
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x0026 }
            r4.read(r2)     // Catch:{ all -> 0x0026 }
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            return r2
        L_0x0026:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.utils.FileUtils.readFile(java.io.File):byte[]");
    }

    public final String formatSizeInKB(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("####.00");
        if (j < ((long) 1024)) {
            return j + " KB";
        } else if (j < ((long) 1048576)) {
            return decimalFormat.format(Float.valueOf(((float) j) / 1024.0f)) + " MB";
        } else if (j < ((long) KeyHandler.KEYMOD_CTRL)) {
            return decimalFormat.format(Float.valueOf((((float) j) / 1024.0f) / 1024.0f)) + " GB";
        } else if (j < 1099511627776L) {
            return decimalFormat.format(Float.valueOf(((((float) j) / 1024.0f) / 1024.0f) / 1024.0f)) + " TB";
        } else {
            return j + " KB";
        }
    }
}
