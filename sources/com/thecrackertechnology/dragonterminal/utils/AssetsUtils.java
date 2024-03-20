package com.thecrackertechnology.dragonterminal.utils;

import android.content.Context;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\b¨\u0006\r"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/AssetsUtils;", "", "()V", "extractAssetsDir", "", "context", "Landroid/content/Context;", "dirName", "", "extractDir", "openAssetsFile", "Ljava/io/InputStream;", "fileName", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AssetsUtils.kt */
public final class AssetsUtils {
    public static final AssetsUtils INSTANCE = new AssetsUtils();

    private AssetsUtils() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a4, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a5, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a8, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void extractAssetsDir(android.content.Context r7, java.lang.String r8, java.lang.String r9) {
        /*
            r6 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            java.lang.String r0 = "dirName"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.lang.String r0 = "extractDir"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            android.content.res.AssetManager r7 = r7.getAssets()
            java.lang.String[] r0 = r7.list(r8)
            java.lang.String r1 = "assets.list(dirName)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.length
            r1.<init>(r2)
            java.util.Collection r1 = (java.util.Collection) r1
            int r2 = r0.length
            r3 = 0
        L_0x0026:
            if (r3 >= r2) goto L_0x0035
            r4 = r0[r3]
            java.io.File r5 = new java.io.File
            r5.<init>(r9, r4)
            r1.add(r5)
            int r3 = r3 + 1
            goto L_0x0026
        L_0x0035:
            java.util.List r1 = (java.util.List) r1
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Iterator r0 = r1.iterator()
        L_0x0042:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005c
            java.lang.Object r1 = r0.next()
            r2 = r1
            java.io.File r2 = (java.io.File) r2
            boolean r2 = r2.exists()
            r2 = r2 ^ 1
            if (r2 != 0) goto L_0x0058
            goto L_0x005c
        L_0x0058:
            r9.add(r1)
            goto L_0x0042
        L_0x005c:
            java.util.List r9 = (java.util.List) r9
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Iterator r9 = r9.iterator()
        L_0x0064:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x00a9
            java.lang.Object r0 = r9.next()
            java.io.File r0 = (java.io.File) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r8)
            r2 = 47
            r1.append(r2)
            java.lang.String r2 = r0.getName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.io.InputStream r1 = r7.open(r1)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = 0
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = r1
            java.io.InputStream r3 = (java.io.InputStream) r3     // Catch:{ all -> 0x00a2 }
            com.thecrackertechnology.dragonterminal.utils.FileUtils r4 = com.thecrackertechnology.dragonterminal.utils.FileUtils.INSTANCE     // Catch:{ all -> 0x00a2 }
            java.lang.String r5 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5)     // Catch:{ all -> 0x00a2 }
            r4.writeFile((java.io.File) r0, (java.io.InputStream) r3)     // Catch:{ all -> 0x00a2 }
            kotlin.io.CloseableKt.closeFinally(r1, r2)
            goto L_0x0064
        L_0x00a2:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00a4 }
        L_0x00a4:
            r8 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r7)
            throw r8
        L_0x00a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.utils.AssetsUtils.extractAssetsDir(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public final InputStream openAssetsFile(Context context, String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(str, "fileName");
        InputStream open = context.getAssets().open(str);
        Intrinsics.checkExpressionValueIsNotNull(open, "assets.open(fileName)");
        return open;
    }
}
