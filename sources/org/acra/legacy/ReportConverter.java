package org.acra.legacy;

import android.content.Context;

class ReportConverter {
    private static final int CONTINUE = 3;
    private static final int IGNORE = 5;
    private static final int KEY_DONE = 4;
    private static final int NONE = 0;
    private static final int SLASH = 1;
    private static final int UNICODE = 2;
    private final Context context;

    ReportConverter(Context context2) {
        this.context = context2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        org.acra.ACRA.log.w(org.acra.ACRA.LOG_TAG, "Unable to read report file " + r3.getPath() + ". Deleting", r4);
        org.acra.util.IOUtils.deleteFile(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00cb, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00cc, code lost:
        org.acra.util.IOUtils.safeClose(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00cf, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x00a2 */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0080 A[Catch:{ all -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c6 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void convert() {
        /*
            r11 = this;
            org.acra.log.ACRALog r0 = org.acra.ACRA.log
            java.lang.String r1 = org.acra.ACRA.LOG_TAG
            java.lang.String r2 = "Converting unsent ACRA reports to json"
            r0.i(r1, r2)
            org.acra.file.ReportLocator r0 = new org.acra.file.ReportLocator
            android.content.Context r1 = r11.context
            r0.<init>(r1)
            org.acra.file.CrashReportPersister r1 = new org.acra.file.CrashReportPersister
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.io.File[] r3 = r0.getUnapprovedReports()
            java.util.List r3 = java.util.Arrays.asList(r3)
            r2.addAll(r3)
            java.io.File[] r0 = r0.getApprovedReports()
            java.util.List r0 = java.util.Arrays.asList(r0)
            r2.addAll(r0)
            java.util.Iterator r0 = r2.iterator()
            r2 = 0
        L_0x0035:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00d0
            java.lang.Object r3 = r0.next()
            java.io.File r3 = (java.io.File) r3
            r4 = 0
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0075 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ all -> 0x0075 }
            r6.<init>(r3)     // Catch:{ all -> 0x0075 }
            r7 = 8192(0x2000, float:1.14794E-41)
            r5.<init>(r6, r7)     // Catch:{ all -> 0x0075 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ all -> 0x0073 }
            java.lang.String r6 = "ISO8859-1"
            r4.<init>(r5, r6)     // Catch:{ all -> 0x0073 }
            org.acra.data.CrashReportData r4 = r11.legacyLoad(r4)     // Catch:{ all -> 0x0073 }
            org.acra.ReportField r6 = org.acra.ReportField.REPORT_ID     // Catch:{ all -> 0x0073 }
            boolean r6 = r4.containsKey((org.acra.ReportField) r6)     // Catch:{ all -> 0x0073 }
            if (r6 == 0) goto L_0x006f
            org.acra.ReportField r6 = org.acra.ReportField.USER_CRASH_DATE     // Catch:{ all -> 0x0073 }
            boolean r6 = r4.containsKey((org.acra.ReportField) r6)     // Catch:{ all -> 0x0073 }
            if (r6 == 0) goto L_0x006f
            r1.store(r4, r3)     // Catch:{ all -> 0x0073 }
            int r2 = r2 + 1
            goto L_0x00c6
        L_0x006f:
            org.acra.util.IOUtils.deleteFile(r3)     // Catch:{ all -> 0x0073 }
            goto L_0x00c6
        L_0x0073:
            r4 = move-exception
            goto L_0x0079
        L_0x0075:
            r5 = move-exception
            r10 = r5
            r5 = r4
            r4 = r10
        L_0x0079:
            r1.load(r3)     // Catch:{ all -> 0x00a2 }
            boolean r6 = org.acra.ACRA.DEV_LOGGING     // Catch:{ all -> 0x00a2 }
            if (r6 == 0) goto L_0x00c6
            org.acra.log.ACRALog r6 = org.acra.ACRA.log     // Catch:{ all -> 0x00a2 }
            java.lang.String r7 = org.acra.ACRA.LOG_TAG     // Catch:{ all -> 0x00a2 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a2 }
            r8.<init>()     // Catch:{ all -> 0x00a2 }
            java.lang.String r9 = "Tried to convert already converted report file "
            r8.append(r9)     // Catch:{ all -> 0x00a2 }
            java.lang.String r9 = r3.getPath()     // Catch:{ all -> 0x00a2 }
            r8.append(r9)     // Catch:{ all -> 0x00a2 }
            java.lang.String r9 = ". Ignoring"
            r8.append(r9)     // Catch:{ all -> 0x00a2 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x00a2 }
            r6.d(r7, r8)     // Catch:{ all -> 0x00a2 }
            goto L_0x00c6
        L_0x00a2:
            org.acra.log.ACRALog r6 = org.acra.ACRA.log     // Catch:{ all -> 0x00cb }
            java.lang.String r7 = org.acra.ACRA.LOG_TAG     // Catch:{ all -> 0x00cb }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cb }
            r8.<init>()     // Catch:{ all -> 0x00cb }
            java.lang.String r9 = "Unable to read report file "
            r8.append(r9)     // Catch:{ all -> 0x00cb }
            java.lang.String r9 = r3.getPath()     // Catch:{ all -> 0x00cb }
            r8.append(r9)     // Catch:{ all -> 0x00cb }
            java.lang.String r9 = ". Deleting"
            r8.append(r9)     // Catch:{ all -> 0x00cb }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x00cb }
            r6.w(r7, r8, r4)     // Catch:{ all -> 0x00cb }
            org.acra.util.IOUtils.deleteFile(r3)     // Catch:{ all -> 0x00cb }
        L_0x00c6:
            org.acra.util.IOUtils.safeClose(r5)
            goto L_0x0035
        L_0x00cb:
            r0 = move-exception
            org.acra.util.IOUtils.safeClose(r5)
            throw r0
        L_0x00d0:
            org.acra.log.ACRALog r0 = org.acra.ACRA.log
            java.lang.String r1 = org.acra.ACRA.LOG_TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Converted "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = " unsent reports"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r0.i(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.legacy.ReportConverter.convert():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        if (r13 != r6) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
        if (r14 <= 4) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        throw new java.lang.IllegalArgumentException("luni.08");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
        if (r15 != r10) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
        if (r0 <= 0) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        r15 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        if (r15 < 0) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        r4 = new java.lang.String(r12, r11, r0);
        r0 = r4.substring(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        if (r13 != 1) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        r0 = r0 + "\u0000";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        putKeyValue(r2, r4.substring(r11, r15), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0060, code lost:
        org.acra.util.IOUtils.safeClose(r19);
     */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0167 A[Catch:{ all -> 0x01a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x016a A[Catch:{ all -> 0x01a5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized org.acra.data.CrashReportData legacyLoad(java.io.Reader r19) throws java.io.IOException {
        /*
            r18 = this;
            r1 = r18
            monitor-enter(r18)
            r0 = 40
            char[] r0 = new char[r0]     // Catch:{ all -> 0x01aa }
            org.acra.data.CrashReportData r2 = new org.acra.data.CrashReportData     // Catch:{ all -> 0x01aa }
            r2.<init>()     // Catch:{ all -> 0x01aa }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x01aa }
            r4 = 8192(0x2000, float:1.14794E-41)
            r5 = r19
            r3.<init>(r5, r4)     // Catch:{ all -> 0x01aa }
            r6 = 2
            r8 = 4
            r9 = 1
            r10 = -1
            r11 = 0
            r12 = r0
            r0 = 0
            r13 = 0
            r14 = 0
            r15 = -1
            r16 = 0
        L_0x0021:
            r17 = 1
        L_0x0023:
            int r7 = r3.read()     // Catch:{ all -> 0x01a5 }
            if (r7 != r10) goto L_0x0068
            if (r13 != r6) goto L_0x0036
            if (r14 <= r8) goto L_0x002e
            goto L_0x0036
        L_0x002e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x01a5 }
            java.lang.String r2 = "luni.08"
            r0.<init>(r2)     // Catch:{ all -> 0x01a5 }
            throw r0     // Catch:{ all -> 0x01a5 }
        L_0x0036:
            if (r15 != r10) goto L_0x003b
            if (r0 <= 0) goto L_0x003b
            r15 = r0
        L_0x003b:
            if (r15 < 0) goto L_0x0060
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x01a5 }
            r4.<init>(r12, r11, r0)     // Catch:{ all -> 0x01a5 }
            java.lang.String r0 = r4.substring(r15)     // Catch:{ all -> 0x01a5 }
            if (r13 != r9) goto L_0x0059
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a5 }
            r6.<init>()     // Catch:{ all -> 0x01a5 }
            r6.append(r0)     // Catch:{ all -> 0x01a5 }
            java.lang.String r0 = "\u0000"
            r6.append(r0)     // Catch:{ all -> 0x01a5 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x01a5 }
        L_0x0059:
            java.lang.String r4 = r4.substring(r11, r15)     // Catch:{ all -> 0x01a5 }
            r1.putKeyValue(r2, r4, r0)     // Catch:{ all -> 0x01a5 }
        L_0x0060:
            org.acra.util.IOUtils.safeClose(r19)     // Catch:{ all -> 0x01a5 }
            org.acra.util.IOUtils.safeClose(r3)     // Catch:{ all -> 0x01aa }
            monitor-exit(r18)
            return r2
        L_0x0068:
            char r7 = (char) r7
            int r4 = r12.length     // Catch:{ all -> 0x01a5 }
            if (r0 != r4) goto L_0x0075
            int r4 = r12.length     // Catch:{ all -> 0x01a5 }
            int r4 = r4 * 2
            char[] r4 = new char[r4]     // Catch:{ all -> 0x01a5 }
            java.lang.System.arraycopy(r12, r11, r4, r11, r0)     // Catch:{ all -> 0x01a5 }
            r12 = r4
        L_0x0075:
            r4 = 133(0x85, float:1.86E-43)
            r11 = 10
            if (r13 != r6) goto L_0x00af
            r6 = 16
            int r6 = java.lang.Character.digit(r7, r6)     // Catch:{ all -> 0x01a5 }
            if (r6 < 0) goto L_0x008e
            int r16 = r16 << 4
            int r16 = r16 + r6
            int r14 = r14 + 1
            if (r14 >= r8) goto L_0x0090
            r6 = 2
        L_0x008c:
            r11 = 0
            goto L_0x0023
        L_0x008e:
            if (r14 <= r8) goto L_0x00a7
        L_0x0090:
            r6 = r16
            int r13 = r0 + 1
            char r10 = (char) r6     // Catch:{ all -> 0x01a5 }
            r12[r0] = r10     // Catch:{ all -> 0x01a5 }
            if (r7 == r11) goto L_0x00a2
            if (r7 == r4) goto L_0x00a2
            r16 = r6
            r0 = r13
            r6 = 2
            r10 = -1
            goto L_0x0125
        L_0x00a2:
            r16 = r6
            r0 = r13
            r13 = 0
            goto L_0x00af
        L_0x00a7:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x01a5 }
            java.lang.String r2 = "luni.09"
            r0.<init>(r2)     // Catch:{ all -> 0x01a5 }
            throw r0     // Catch:{ all -> 0x01a5 }
        L_0x00af:
            r6 = 13
            if (r13 != r9) goto L_0x00f9
            if (r7 == r11) goto L_0x00f3
            if (r7 == r6) goto L_0x00ed
            r10 = 98
            if (r7 == r10) goto L_0x00e7
            r10 = 102(0x66, float:1.43E-43)
            if (r7 == r10) goto L_0x00e4
            r10 = 110(0x6e, float:1.54E-43)
            if (r7 == r10) goto L_0x00e1
            r10 = 114(0x72, float:1.6E-43)
            if (r7 == r10) goto L_0x00de
            if (r7 == r4) goto L_0x00f3
            r4 = 116(0x74, float:1.63E-43)
            if (r7 == r4) goto L_0x00db
            r4 = 117(0x75, float:1.64E-43)
            if (r7 == r4) goto L_0x00d2
            goto L_0x00e9
        L_0x00d2:
            r6 = 2
            r10 = -1
            r11 = 0
            r13 = 2
            r14 = 0
            r16 = 0
            goto L_0x0023
        L_0x00db:
            r7 = 9
            goto L_0x00e9
        L_0x00de:
            r7 = 13
            goto L_0x00e9
        L_0x00e1:
            r7 = 10
            goto L_0x00e9
        L_0x00e4:
            r7 = 12
            goto L_0x00e9
        L_0x00e7:
            r7 = 8
        L_0x00e9:
            r4 = 5
        L_0x00ea:
            r11 = 0
            goto L_0x0165
        L_0x00ed:
            r6 = 2
            r10 = -1
            r11 = 0
            r13 = 3
            goto L_0x0023
        L_0x00f3:
            r6 = 2
            r10 = -1
            r11 = 0
            r13 = 5
            goto L_0x0023
        L_0x00f9:
            if (r7 == r11) goto L_0x0179
            if (r7 == r6) goto L_0x0114
            r10 = 33
            if (r7 == r10) goto L_0x0129
            r10 = 35
            if (r7 == r10) goto L_0x0129
            r6 = 58
            if (r7 == r6) goto L_0x0120
            r6 = 61
            if (r7 == r6) goto L_0x0120
            r6 = 92
            if (r7 == r6) goto L_0x0117
            if (r7 == r4) goto L_0x0114
            goto L_0x013d
        L_0x0114:
            r4 = 5
            goto L_0x0177
        L_0x0117:
            if (r13 != r8) goto L_0x011a
            r15 = r0
        L_0x011a:
            r6 = 2
            r10 = -1
            r11 = 0
            r13 = 1
            goto L_0x0023
        L_0x0120:
            r10 = -1
            if (r15 != r10) goto L_0x013d
            r15 = r0
            r6 = 2
        L_0x0125:
            r11 = 0
            r13 = 0
            goto L_0x0023
        L_0x0129:
            r10 = -1
            if (r17 == 0) goto L_0x013d
        L_0x012c:
            int r7 = r3.read()     // Catch:{ all -> 0x01a5 }
            if (r7 != r10) goto L_0x0133
            goto L_0x0159
        L_0x0133:
            char r7 = (char) r7     // Catch:{ all -> 0x01a5 }
            if (r7 == r6) goto L_0x0159
            if (r7 == r11) goto L_0x0159
            if (r7 != r4) goto L_0x013b
            goto L_0x0159
        L_0x013b:
            r10 = -1
            goto L_0x012c
        L_0x013d:
            boolean r4 = java.lang.Character.isWhitespace(r7)     // Catch:{ all -> 0x01a5 }
            if (r4 == 0) goto L_0x015d
            r4 = 3
            if (r13 != r4) goto L_0x0147
            r13 = 5
        L_0x0147:
            if (r0 == 0) goto L_0x0158
            if (r0 == r15) goto L_0x0158
            r4 = 5
            if (r13 != r4) goto L_0x014f
            goto L_0x0159
        L_0x014f:
            r6 = -1
            if (r15 != r6) goto L_0x015e
            r6 = 2
            r10 = -1
            r11 = 0
            r13 = 4
            goto L_0x0023
        L_0x0158:
            r4 = 5
        L_0x0159:
            r6 = 2
            r10 = -1
            goto L_0x008c
        L_0x015d:
            r4 = 5
        L_0x015e:
            r11 = r13
            if (r11 == r4) goto L_0x00ea
            r6 = 3
            if (r11 != r6) goto L_0x0165
            goto L_0x00ea
        L_0x0165:
            if (r11 != r8) goto L_0x016a
            r15 = r0
            r13 = 0
            goto L_0x016b
        L_0x016a:
            r13 = r11
        L_0x016b:
            int r6 = r0 + 1
            r12[r0] = r7     // Catch:{ all -> 0x01a5 }
            r0 = r6
            r6 = 2
            r10 = -1
            r11 = 0
            r17 = 0
            goto L_0x0023
        L_0x0177:
            r6 = 3
            goto L_0x017f
        L_0x0179:
            r4 = 5
            r6 = 3
            if (r13 != r6) goto L_0x017f
            goto L_0x00f3
        L_0x017f:
            if (r0 > 0) goto L_0x0189
            if (r0 != 0) goto L_0x0186
            if (r15 != 0) goto L_0x0186
            goto L_0x0189
        L_0x0186:
            r7 = -1
            r11 = 0
            goto L_0x019e
        L_0x0189:
            r7 = -1
            if (r15 != r7) goto L_0x018d
            r15 = r0
        L_0x018d:
            java.lang.String r10 = new java.lang.String     // Catch:{ all -> 0x01a5 }
            r11 = 0
            r10.<init>(r12, r11, r0)     // Catch:{ all -> 0x01a5 }
            java.lang.String r0 = r10.substring(r11, r15)     // Catch:{ all -> 0x01a5 }
            java.lang.String r10 = r10.substring(r15)     // Catch:{ all -> 0x01a5 }
            r1.putKeyValue(r2, r0, r10)     // Catch:{ all -> 0x01a5 }
        L_0x019e:
            r0 = 0
            r6 = 2
            r10 = -1
            r13 = 0
            r15 = -1
            goto L_0x0021
        L_0x01a5:
            r0 = move-exception
            org.acra.util.IOUtils.safeClose(r3)     // Catch:{ all -> 0x01aa }
            throw r0     // Catch:{ all -> 0x01aa }
        L_0x01aa:
            r0 = move-exception
            monitor-exit(r18)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.legacy.ReportConverter.legacyLoad(java.io.Reader):org.acra.data.CrashReportData");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void putKeyValue(org.acra.data.CrashReportData r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0009 }
            r0.<init>(r8)     // Catch:{ JSONException -> 0x0009 }
            r6.put((java.lang.String) r7, (org.json.JSONObject) r0)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0049
        L_0x0009:
            java.lang.Double r0 = java.lang.Double.valueOf(r8)     // Catch:{ NumberFormatException -> 0x0015 }
            double r0 = r0.doubleValue()     // Catch:{ NumberFormatException -> 0x0015 }
            r6.put((java.lang.String) r7, (double) r0)     // Catch:{ NumberFormatException -> 0x0015 }
            goto L_0x0049
        L_0x0015:
            r0 = -1
            int r1 = r8.hashCode()
            r2 = 3569038(0x36758e, float:5.001287E-39)
            r3 = 0
            r4 = 1
            if (r1 == r2) goto L_0x0031
            r2 = 97196323(0x5cb1923, float:1.9099262E-35)
            if (r1 == r2) goto L_0x0027
            goto L_0x003a
        L_0x0027:
            java.lang.String r1 = "false"
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x003a
            r0 = 1
            goto L_0x003a
        L_0x0031:
            java.lang.String r1 = "true"
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x003a
            r0 = 0
        L_0x003a:
            if (r0 == 0) goto L_0x0046
            if (r0 == r4) goto L_0x0042
            r6.put((java.lang.String) r7, (java.lang.String) r8)
            goto L_0x0049
        L_0x0042:
            r6.put((java.lang.String) r7, (boolean) r3)
            goto L_0x0049
        L_0x0046:
            r6.put((java.lang.String) r7, (boolean) r4)
        L_0x0049:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.legacy.ReportConverter.putKeyValue(org.acra.data.CrashReportData, java.lang.String, java.lang.String):void");
    }
}
