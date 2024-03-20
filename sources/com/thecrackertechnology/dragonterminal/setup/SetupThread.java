package com.thecrackertechnology.dragonterminal.setup;

import android.app.Activity;
import android.app.ProgressDialog;
import java.io.File;
import java.io.IOException;

final class SetupThread extends Thread {
    private final Activity activity;
    private final File prefixPath;
    private final ProgressDialog progressDialog;
    private final ResultListener resultListener;
    private final SourceConnection sourceConnection;

    public SetupThread(Activity activity2, SourceConnection sourceConnection2, File file, ResultListener resultListener2, ProgressDialog progressDialog2) {
        this.activity = activity2;
        this.sourceConnection = sourceConnection2;
        this.prefixPath = file;
        this.resultListener = resultListener2;
        this.progressDialog = progressDialog2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0106, code lost:
        if (r11.startsWith("bin/") != false) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x010e, code lost:
        if (r11.startsWith("libexec") != false) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0116, code lost:
        if (r11.startsWith("lib/apt/methods") == false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0118, code lost:
        android.system.Os.chmod(r12.getAbsolutePath(), 448);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0125, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x012a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x012e, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r7.close();
        r15.sourceConnection.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x013b, code lost:
        if (r6.isEmpty() != false) goto L_0x01a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x013d, code lost:
        r3 = r6.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0145, code lost:
        if (r3.hasNext() == false) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0147, code lost:
        r5 = (android.util.Pair) r3.next();
        com.thecrackertechnology.dragonterminal.frontend.logging.NLog.INSTANCE.e("Setup", "Linking " + ((java.lang.String) r5.first) + " to " + ((java.lang.String) r5.second));
        android.system.Os.symlink((java.lang.String) r5.first, (java.lang.String) r5.second);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x018b, code lost:
        if (r4.renameTo(r15.prefixPath) == false) goto L_0x019f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x018d, code lost:
        r15.activity.runOnUiThread(new com.thecrackertechnology.dragonterminal.setup.$$Lambda$SetupThread$d0RhoFgTJPEPqyEh4Ya6DneABtI(r15));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0197, code lost:
        r0 = r15.activity;
        r1 = new com.thecrackertechnology.dragonterminal.setup.$$Lambda$SetupThread$nrE1ZTYbFq00A125Q1aOC1n5dy4(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01a6, code lost:
        throw new java.lang.RuntimeException("Unable to rename staging folder");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01ae, code lost:
        throw new java.lang.RuntimeException("No SYMLINKS.txt encountered");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01b1, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01ba, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r15 = this;
            r0 = 2
            r1 = 1
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01bd }
            r3.<init>()     // Catch:{ Exception -> 0x01bd }
            java.lang.String r4 = com.thecrackertechnology.dragonterminal.frontend.config.NeoTermPath.ROOT_PATH     // Catch:{ Exception -> 0x01bd }
            r3.append(r4)     // Catch:{ Exception -> 0x01bd }
            java.lang.String r4 = "/usr-staging"
            r3.append(r4)     // Catch:{ Exception -> 0x01bd }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01bd }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x01bd }
            r4.<init>(r3)     // Catch:{ Exception -> 0x01bd }
            boolean r5 = r4.exists()     // Catch:{ Exception -> 0x01bd }
            if (r5 == 0) goto L_0x0024
            deleteFolder(r4)     // Catch:{ Exception -> 0x01bd }
        L_0x0024:
            r5 = 8096(0x1fa0, float:1.1345E-41)
            byte[] r5 = new byte[r5]     // Catch:{ Exception -> 0x01bd }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Exception -> 0x01bd }
            r7 = 50
            r6.<init>(r7)     // Catch:{ Exception -> 0x01bd }
            java.util.zip.ZipInputStream r7 = new java.util.zip.ZipInputStream     // Catch:{ Exception -> 0x01bd }
            com.thecrackertechnology.dragonterminal.setup.SourceConnection r8 = r15.sourceConnection     // Catch:{ Exception -> 0x01bd }
            java.io.InputStream r8 = r8.getInputStream()     // Catch:{ Exception -> 0x01bd }
            r7.<init>(r8)     // Catch:{ Exception -> 0x01bd }
            com.thecrackertechnology.dragonterminal.setup.SourceConnection r8 = r15.sourceConnection     // Catch:{ all -> 0x01af }
            int r8 = r8.getSize()     // Catch:{ all -> 0x01af }
            r9 = 0
        L_0x0041:
            java.util.zip.ZipEntry r10 = r7.getNextEntry()     // Catch:{ all -> 0x01af }
            if (r10 == 0) goto L_0x012f
            long r11 = (long) r9     // Catch:{ all -> 0x01af }
            long r13 = r10.getCompressedSize()     // Catch:{ all -> 0x01af }
            long r11 = r11 + r13
            int r9 = (int) r11     // Catch:{ all -> 0x01af }
            android.app.Activity r11 = r15.activity     // Catch:{ all -> 0x01af }
            com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$qBMXnOFSWFLO-J3PaA1nWgfAVYg r12 = new com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$qBMXnOFSWFLO-J3PaA1nWgfAVYg     // Catch:{ all -> 0x01af }
            r12.<init>(r9, r8)     // Catch:{ all -> 0x01af }
            r11.runOnUiThread(r12)     // Catch:{ all -> 0x01af }
            java.lang.String r11 = r10.getName()     // Catch:{ all -> 0x01af }
            java.lang.String r12 = "SYMLINKS.txt"
            boolean r11 = r11.contains(r12)     // Catch:{ all -> 0x01af }
            if (r11 == 0) goto L_0x00bb
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch:{ all -> 0x01af }
            java.io.InputStreamReader r11 = new java.io.InputStreamReader     // Catch:{ all -> 0x01af }
            r11.<init>(r7)     // Catch:{ all -> 0x01af }
            r10.<init>(r11)     // Catch:{ all -> 0x01af }
        L_0x006e:
            java.lang.String r11 = r10.readLine()     // Catch:{ all -> 0x01af }
            if (r11 == 0) goto L_0x0041
            boolean r12 = r11.isEmpty()     // Catch:{ all -> 0x01af }
            if (r12 == 0) goto L_0x007b
            goto L_0x006e
        L_0x007b:
            java.lang.String r12 = "←"
            java.lang.String[] r12 = r11.split(r12)     // Catch:{ all -> 0x01af }
            int r13 = r12.length     // Catch:{ all -> 0x01af }
            if (r13 != r0) goto L_0x00a4
            r11 = r12[r2]     // Catch:{ all -> 0x01af }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x01af }
            r13.<init>()     // Catch:{ all -> 0x01af }
            r13.append(r3)     // Catch:{ all -> 0x01af }
            java.lang.String r14 = "/"
            r13.append(r14)     // Catch:{ all -> 0x01af }
            r12 = r12[r1]     // Catch:{ all -> 0x01af }
            r13.append(r12)     // Catch:{ all -> 0x01af }
            java.lang.String r12 = r13.toString()     // Catch:{ all -> 0x01af }
            android.util.Pair r11 = android.util.Pair.create(r11, r12)     // Catch:{ all -> 0x01af }
            r6.add(r11)     // Catch:{ all -> 0x01af }
            goto L_0x006e
        L_0x00a4:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ all -> 0x01af }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x01af }
            r4.<init>()     // Catch:{ all -> 0x01af }
            java.lang.String r5 = "Malformed symlink line: "
            r4.append(r5)     // Catch:{ all -> 0x01af }
            r4.append(r11)     // Catch:{ all -> 0x01af }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01af }
            r3.<init>(r4)     // Catch:{ all -> 0x01af }
            throw r3     // Catch:{ all -> 0x01af }
        L_0x00bb:
            java.lang.String r11 = r10.getName()     // Catch:{ all -> 0x01af }
            java.io.File r12 = new java.io.File     // Catch:{ all -> 0x01af }
            r12.<init>(r3, r11)     // Catch:{ all -> 0x01af }
            boolean r10 = r10.isDirectory()     // Catch:{ all -> 0x01af }
            if (r10 == 0) goto L_0x00ed
            boolean r10 = r12.mkdirs()     // Catch:{ all -> 0x01af }
            if (r10 == 0) goto L_0x00d2
            goto L_0x0041
        L_0x00d2:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ all -> 0x01af }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x01af }
            r4.<init>()     // Catch:{ all -> 0x01af }
            java.lang.String r5 = "Failed to create directory: "
            r4.append(r5)     // Catch:{ all -> 0x01af }
            java.lang.String r5 = r12.getAbsolutePath()     // Catch:{ all -> 0x01af }
            r4.append(r5)     // Catch:{ all -> 0x01af }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01af }
            r3.<init>(r4)     // Catch:{ all -> 0x01af }
            throw r3     // Catch:{ all -> 0x01af }
        L_0x00ed:
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ all -> 0x01af }
            r10.<init>(r12)     // Catch:{ all -> 0x01af }
        L_0x00f2:
            int r13 = r7.read(r5)     // Catch:{ all -> 0x0123 }
            r14 = -1
            if (r13 == r14) goto L_0x00fd
            r10.write(r5, r2, r13)     // Catch:{ all -> 0x0123 }
            goto L_0x00f2
        L_0x00fd:
            r10.close()     // Catch:{ all -> 0x01af }
            java.lang.String r10 = "bin/"
            boolean r10 = r11.startsWith(r10)     // Catch:{ all -> 0x01af }
            if (r10 != 0) goto L_0x0118
            java.lang.String r10 = "libexec"
            boolean r10 = r11.startsWith(r10)     // Catch:{ all -> 0x01af }
            if (r10 != 0) goto L_0x0118
            java.lang.String r10 = "lib/apt/methods"
            boolean r10 = r11.startsWith(r10)     // Catch:{ all -> 0x01af }
            if (r10 == 0) goto L_0x0041
        L_0x0118:
            java.lang.String r10 = r12.getAbsolutePath()     // Catch:{ all -> 0x01af }
            r11 = 448(0x1c0, float:6.28E-43)
            android.system.Os.chmod(r10, r11)     // Catch:{ all -> 0x01af }
            goto L_0x0041
        L_0x0123:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0125 }
        L_0x0125:
            r4 = move-exception
            r10.close()     // Catch:{ all -> 0x012a }
            goto L_0x012e
        L_0x012a:
            r5 = move-exception
            r3.addSuppressed(r5)     // Catch:{ all -> 0x01af }
        L_0x012e:
            throw r4     // Catch:{ all -> 0x01af }
        L_0x012f:
            r7.close()     // Catch:{ Exception -> 0x01bd }
            com.thecrackertechnology.dragonterminal.setup.SourceConnection r3 = r15.sourceConnection     // Catch:{ Exception -> 0x01bd }
            r3.close()     // Catch:{ Exception -> 0x01bd }
            boolean r3 = r6.isEmpty()     // Catch:{ Exception -> 0x01bd }
            if (r3 != 0) goto L_0x01a7
            java.util.Iterator r3 = r6.iterator()     // Catch:{ Exception -> 0x01bd }
        L_0x0141:
            boolean r5 = r3.hasNext()     // Catch:{ Exception -> 0x01bd }
            if (r5 == 0) goto L_0x0185
            java.lang.Object r5 = r3.next()     // Catch:{ Exception -> 0x01bd }
            android.util.Pair r5 = (android.util.Pair) r5     // Catch:{ Exception -> 0x01bd }
            com.thecrackertechnology.dragonterminal.frontend.logging.NLog r6 = com.thecrackertechnology.dragonterminal.frontend.logging.NLog.INSTANCE     // Catch:{ Exception -> 0x01bd }
            java.lang.String r7 = "Setup"
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x01bd }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01bd }
            r9.<init>()     // Catch:{ Exception -> 0x01bd }
            java.lang.String r10 = "Linking "
            r9.append(r10)     // Catch:{ Exception -> 0x01bd }
            java.lang.Object r10 = r5.first     // Catch:{ Exception -> 0x01bd }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x01bd }
            r9.append(r10)     // Catch:{ Exception -> 0x01bd }
            java.lang.String r10 = " to "
            r9.append(r10)     // Catch:{ Exception -> 0x01bd }
            java.lang.Object r10 = r5.second     // Catch:{ Exception -> 0x01bd }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x01bd }
            r9.append(r10)     // Catch:{ Exception -> 0x01bd }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x01bd }
            r8[r2] = r9     // Catch:{ Exception -> 0x01bd }
            r6.e(r7, r8)     // Catch:{ Exception -> 0x01bd }
            java.lang.Object r6 = r5.first     // Catch:{ Exception -> 0x01bd }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x01bd }
            java.lang.Object r5 = r5.second     // Catch:{ Exception -> 0x01bd }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x01bd }
            android.system.Os.symlink(r6, r5)     // Catch:{ Exception -> 0x01bd }
            goto L_0x0141
        L_0x0185:
            java.io.File r3 = r15.prefixPath     // Catch:{ Exception -> 0x01bd }
            boolean r3 = r4.renameTo(r3)     // Catch:{ Exception -> 0x01bd }
            if (r3 == 0) goto L_0x019f
            android.app.Activity r3 = r15.activity     // Catch:{ Exception -> 0x01bd }
            com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$d0RhoFgTJPEPqyEh4Ya6DneABtI r4 = new com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$d0RhoFgTJPEPqyEh4Ya6DneABtI     // Catch:{ Exception -> 0x01bd }
            r4.<init>()     // Catch:{ Exception -> 0x01bd }
            r3.runOnUiThread(r4)     // Catch:{ Exception -> 0x01bd }
            android.app.Activity r0 = r15.activity
            com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$nrE1ZTYbFq00A125Q1aOC1n5dy4 r1 = new com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$nrE1ZTYbFq00A125Q1aOC1n5dy4
            r1.<init>()
            goto L_0x01de
        L_0x019f:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x01bd }
            java.lang.String r4 = "Unable to rename staging folder"
            r3.<init>(r4)     // Catch:{ Exception -> 0x01bd }
            throw r3     // Catch:{ Exception -> 0x01bd }
        L_0x01a7:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x01bd }
            java.lang.String r4 = "No SYMLINKS.txt encountered"
            r3.<init>(r4)     // Catch:{ Exception -> 0x01bd }
            throw r3     // Catch:{ Exception -> 0x01bd }
        L_0x01af:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x01b1 }
        L_0x01b1:
            r4 = move-exception
            r7.close()     // Catch:{ all -> 0x01b6 }
            goto L_0x01ba
        L_0x01b6:
            r5 = move-exception
            r3.addSuppressed(r5)     // Catch:{ Exception -> 0x01bd }
        L_0x01ba:
            throw r4     // Catch:{ Exception -> 0x01bd }
        L_0x01bb:
            r0 = move-exception
            goto L_0x01e2
        L_0x01bd:
            r3 = move-exception
            com.thecrackertechnology.dragonterminal.frontend.logging.NLog r4 = com.thecrackertechnology.dragonterminal.frontend.logging.NLog.INSTANCE     // Catch:{ all -> 0x01bb }
            java.lang.String r5 = "NeoTerm-Emulator"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x01bb }
            java.lang.String r6 = "Bootstrap error"
            r0[r2] = r6     // Catch:{ all -> 0x01bb }
            r0[r1] = r3     // Catch:{ all -> 0x01bb }
            r4.e(r5, r0)     // Catch:{ all -> 0x01bb }
            android.app.Activity r0 = r15.activity     // Catch:{ all -> 0x01bb }
            com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$lcgWKCKXgkKhnq9BSEw6vaaxziM r1 = new com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$lcgWKCKXgkKhnq9BSEw6vaaxziM     // Catch:{ all -> 0x01bb }
            r1.<init>(r3)     // Catch:{ all -> 0x01bb }
            r0.runOnUiThread(r1)     // Catch:{ all -> 0x01bb }
            android.app.Activity r0 = r15.activity
            com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$nrE1ZTYbFq00A125Q1aOC1n5dy4 r1 = new com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$nrE1ZTYbFq00A125Q1aOC1n5dy4
            r1.<init>()
        L_0x01de:
            r0.runOnUiThread(r1)
            return
        L_0x01e2:
            android.app.Activity r1 = r15.activity
            com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$nrE1ZTYbFq00A125Q1aOC1n5dy4 r2 = new com.thecrackertechnology.dragonterminal.setup.-$$Lambda$SetupThread$nrE1ZTYbFq00A125Q1aOC1n5dy4
            r2.<init>()
            r1.runOnUiThread(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.setup.SetupThread.run():void");
    }

    public /* synthetic */ void lambda$run$0$SetupThread(int i, int i2) {
        try {
            this.progressDialog.setProgress((int) ((((double) i) / ((double) i2)) * 100.0d));
        } catch (RuntimeException unused) {
        }
    }

    public /* synthetic */ void lambda$run$1$SetupThread() {
        this.resultListener.onResult((Exception) null);
    }

    public /* synthetic */ void lambda$run$2$SetupThread(Exception exc) {
        try {
            this.resultListener.onResult(exc);
        } catch (RuntimeException unused) {
        }
    }

    public /* synthetic */ void lambda$run$3$SetupThread() {
        try {
            this.progressDialog.dismiss();
        } catch (RuntimeException unused) {
        }
    }

    private static void deleteFolder(File file) throws IOException {
        File[] listFiles;
        if (file.getCanonicalPath().equals(file.getAbsolutePath()) && file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File deleteFolder : listFiles) {
                deleteFolder(deleteFolder);
            }
        }
        if (!file.delete()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to delete ");
            sb.append(file.isDirectory() ? "directory " : "file ");
            sb.append(file.getAbsolutePath());
            throw new RuntimeException(sb.toString());
        }
    }
}
