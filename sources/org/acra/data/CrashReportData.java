package org.acra.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.collections.ImmutableSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class CrashReportData {
    private final JSONObject content;

    public CrashReportData() {
        this.content = new JSONObject();
    }

    public CrashReportData(String str) throws JSONException {
        this.content = new JSONObject(str);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r4, boolean r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            org.json.JSONObject r0 = r3.content     // Catch:{ JSONException -> 0x0009 }
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0025
        L_0x0007:
            r4 = move-exception
            goto L_0x0027
        L_0x0009:
            org.acra.log.ACRALog r4 = org.acra.ACRA.log     // Catch:{ all -> 0x0007 }
            java.lang.String r0 = org.acra.ACRA.LOG_TAG     // Catch:{ all -> 0x0007 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0007 }
            r1.<init>()     // Catch:{ all -> 0x0007 }
            java.lang.String r2 = "Failed to put value into CrashReportData: "
            r1.append(r2)     // Catch:{ all -> 0x0007 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0007 }
            r1.append(r5)     // Catch:{ all -> 0x0007 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0007 }
            r4.w((java.lang.String) r0, (java.lang.String) r5)     // Catch:{ all -> 0x0007 }
        L_0x0025:
            monitor-exit(r3)
            return
        L_0x0027:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.data.CrashReportData.put(java.lang.String, boolean):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r4, double r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            org.json.JSONObject r0 = r3.content     // Catch:{ JSONException -> 0x0009 }
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0025
        L_0x0007:
            r4 = move-exception
            goto L_0x0027
        L_0x0009:
            org.acra.log.ACRALog r4 = org.acra.ACRA.log     // Catch:{ all -> 0x0007 }
            java.lang.String r0 = org.acra.ACRA.LOG_TAG     // Catch:{ all -> 0x0007 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0007 }
            r1.<init>()     // Catch:{ all -> 0x0007 }
            java.lang.String r2 = "Failed to put value into CrashReportData: "
            r1.append(r2)     // Catch:{ all -> 0x0007 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0007 }
            r1.append(r5)     // Catch:{ all -> 0x0007 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0007 }
            r4.w((java.lang.String) r0, (java.lang.String) r5)     // Catch:{ all -> 0x0007 }
        L_0x0025:
            monitor-exit(r3)
            return
        L_0x0027:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.data.CrashReportData.put(java.lang.String, double):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r4, int r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            org.json.JSONObject r0 = r3.content     // Catch:{ JSONException -> 0x0009 }
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0025
        L_0x0007:
            r4 = move-exception
            goto L_0x0027
        L_0x0009:
            org.acra.log.ACRALog r4 = org.acra.ACRA.log     // Catch:{ all -> 0x0007 }
            java.lang.String r0 = org.acra.ACRA.LOG_TAG     // Catch:{ all -> 0x0007 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0007 }
            r1.<init>()     // Catch:{ all -> 0x0007 }
            java.lang.String r2 = "Failed to put value into CrashReportData: "
            r1.append(r2)     // Catch:{ all -> 0x0007 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0007 }
            r1.append(r5)     // Catch:{ all -> 0x0007 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0007 }
            r4.w((java.lang.String) r0, (java.lang.String) r5)     // Catch:{ all -> 0x0007 }
        L_0x0025:
            monitor-exit(r3)
            return
        L_0x0027:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.data.CrashReportData.put(java.lang.String, int):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r4, long r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            org.json.JSONObject r0 = r3.content     // Catch:{ JSONException -> 0x0009 }
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0025
        L_0x0007:
            r4 = move-exception
            goto L_0x0027
        L_0x0009:
            org.acra.log.ACRALog r4 = org.acra.ACRA.log     // Catch:{ all -> 0x0007 }
            java.lang.String r0 = org.acra.ACRA.LOG_TAG     // Catch:{ all -> 0x0007 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0007 }
            r1.<init>()     // Catch:{ all -> 0x0007 }
            java.lang.String r2 = "Failed to put value into CrashReportData: "
            r1.append(r2)     // Catch:{ all -> 0x0007 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0007 }
            r1.append(r5)     // Catch:{ all -> 0x0007 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0007 }
            r4.w((java.lang.String) r0, (java.lang.String) r5)     // Catch:{ all -> 0x0007 }
        L_0x0025:
            monitor-exit(r3)
            return
        L_0x0027:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.data.CrashReportData.put(java.lang.String, long):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r5 != 0) goto L_0x0008
            r3.putNA(r4)     // Catch:{ all -> 0x000e }
            monitor-exit(r3)
            return
        L_0x0008:
            org.json.JSONObject r0 = r3.content     // Catch:{ JSONException -> 0x0010 }
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x0010 }
            goto L_0x0028
        L_0x000e:
            r4 = move-exception
            goto L_0x002a
        L_0x0010:
            org.acra.log.ACRALog r4 = org.acra.ACRA.log     // Catch:{ all -> 0x000e }
            java.lang.String r0 = org.acra.ACRA.LOG_TAG     // Catch:{ all -> 0x000e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x000e }
            r1.<init>()     // Catch:{ all -> 0x000e }
            java.lang.String r2 = "Failed to put value into CrashReportData: "
            r1.append(r2)     // Catch:{ all -> 0x000e }
            r1.append(r5)     // Catch:{ all -> 0x000e }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x000e }
            r4.w((java.lang.String) r0, (java.lang.String) r5)     // Catch:{ all -> 0x000e }
        L_0x0028:
            monitor-exit(r3)
            return
        L_0x002a:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.data.CrashReportData.put(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r4, org.json.JSONObject r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r5 != 0) goto L_0x0008
            r3.putNA(r4)     // Catch:{ all -> 0x000e }
            monitor-exit(r3)
            return
        L_0x0008:
            org.json.JSONObject r0 = r3.content     // Catch:{ JSONException -> 0x0010 }
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x0010 }
            goto L_0x002c
        L_0x000e:
            r4 = move-exception
            goto L_0x002e
        L_0x0010:
            org.acra.log.ACRALog r4 = org.acra.ACRA.log     // Catch:{ all -> 0x000e }
            java.lang.String r0 = org.acra.ACRA.LOG_TAG     // Catch:{ all -> 0x000e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x000e }
            r1.<init>()     // Catch:{ all -> 0x000e }
            java.lang.String r2 = "Failed to put value into CrashReportData: "
            r1.append(r2)     // Catch:{ all -> 0x000e }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x000e }
            r1.append(r5)     // Catch:{ all -> 0x000e }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x000e }
            r4.w((java.lang.String) r0, (java.lang.String) r5)     // Catch:{ all -> 0x000e }
        L_0x002c:
            monitor-exit(r3)
            return
        L_0x002e:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.data.CrashReportData.put(java.lang.String, org.json.JSONObject):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(java.lang.String r4, org.json.JSONArray r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r5 != 0) goto L_0x0008
            r3.putNA(r4)     // Catch:{ all -> 0x000e }
            monitor-exit(r3)
            return
        L_0x0008:
            org.json.JSONObject r0 = r3.content     // Catch:{ JSONException -> 0x0010 }
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x0010 }
            goto L_0x002c
        L_0x000e:
            r4 = move-exception
            goto L_0x002e
        L_0x0010:
            org.acra.log.ACRALog r4 = org.acra.ACRA.log     // Catch:{ all -> 0x000e }
            java.lang.String r0 = org.acra.ACRA.LOG_TAG     // Catch:{ all -> 0x000e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x000e }
            r1.<init>()     // Catch:{ all -> 0x000e }
            java.lang.String r2 = "Failed to put value into CrashReportData: "
            r1.append(r2)     // Catch:{ all -> 0x000e }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x000e }
            r1.append(r5)     // Catch:{ all -> 0x000e }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x000e }
            r4.w((java.lang.String) r0, (java.lang.String) r5)     // Catch:{ all -> 0x000e }
        L_0x002c:
            monitor-exit(r3)
            return
        L_0x002e:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.data.CrashReportData.put(java.lang.String, org.json.JSONArray):void");
    }

    public synchronized void put(ReportField reportField, boolean z) {
        put(reportField.toString(), z);
    }

    public synchronized void put(ReportField reportField, double d) {
        put(reportField.toString(), d);
    }

    public synchronized void put(ReportField reportField, int i) {
        put(reportField.toString(), i);
    }

    public synchronized void put(ReportField reportField, long j) {
        put(reportField.toString(), j);
    }

    public synchronized void put(ReportField reportField, String str) {
        put(reportField.toString(), str);
    }

    public synchronized void put(ReportField reportField, JSONObject jSONObject) {
        put(reportField.toString(), jSONObject);
    }

    public synchronized void put(ReportField reportField, JSONArray jSONArray) {
        put(reportField.toString(), jSONArray);
    }

    private void putNA(String str) {
        try {
            this.content.put(str, ACRAConstants.NOT_AVAILABLE);
        } catch (JSONException unused) {
        }
    }

    public String getString(ReportField reportField) {
        return this.content.optString(reportField.toString());
    }

    public Object get(String str) {
        return this.content.opt(str);
    }

    public boolean containsKey(String str) {
        return this.content.has(str);
    }

    public boolean containsKey(ReportField reportField) {
        return containsKey(reportField.toString());
    }

    public String toJSON() throws JSONException {
        try {
            return StringFormat.JSON.toFormattedString(this, ImmutableSet.empty(), "", "", false);
        } catch (JSONException e) {
            throw e;
        } catch (Exception e2) {
            throw new JSONException(e2.getMessage());
        }
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap(this.content.length());
        Iterator<String> keys = this.content.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, get(next));
        }
        return hashMap;
    }
}
