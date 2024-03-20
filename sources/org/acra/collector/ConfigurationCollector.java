package org.acra.collector;

import android.content.Context;
import android.content.res.Configuration;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.log.ACRALog;
import org.json.JSONException;
import org.json.JSONObject;

public final class ConfigurationCollector extends BaseReportFieldCollector implements ApplicationStartupCollector {
    private static final String FIELD_MCC = "mcc";
    private static final String FIELD_MNC = "mnc";
    private static final String FIELD_SCREENLAYOUT = "screenLayout";
    private static final String FIELD_UIMODE = "uiMode";
    private static final String PREFIX_HARDKEYBOARDHIDDEN = "HARDKEYBOARDHIDDEN_";
    private static final String PREFIX_KEYBOARD = "KEYBOARD_";
    private static final String PREFIX_KEYBOARDHIDDEN = "KEYBOARDHIDDEN_";
    private static final String PREFIX_NAVIGATION = "NAVIGATION_";
    private static final String PREFIX_NAVIGATIONHIDDEN = "NAVIGATIONHIDDEN_";
    private static final String PREFIX_ORIENTATION = "ORIENTATION_";
    private static final String PREFIX_SCREENLAYOUT = "SCREENLAYOUT_";
    private static final String PREFIX_TOUCHSCREEN = "TOUCHSCREEN_";
    private static final String PREFIX_UI_MODE = "UI_MODE_";
    private static final String SUFFIX_MASK = "_MASK";
    private JSONObject initialConfiguration;

    public ConfigurationCollector() {
        super(ReportField.INITIAL_CONFIGURATION, ReportField.CRASH_CONFIGURATION);
    }

    /* renamed from: org.acra.collector.ConfigurationCollector$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$acra$ReportField = new int[ReportField.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                org.acra.ReportField[] r0 = org.acra.ReportField.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$acra$ReportField = r0
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.acra.ReportField r1 = org.acra.ReportField.INITIAL_CONFIGURATION     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x001f }
                org.acra.ReportField r1 = org.acra.ReportField.CRASH_CONFIGURATION     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.ConfigurationCollector.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) {
        int i = AnonymousClass1.$SwitchMap$org$acra$ReportField[reportField.ordinal()];
        if (i == 1) {
            crashReportData.put(ReportField.INITIAL_CONFIGURATION, this.initialConfiguration);
        } else if (i == 2) {
            crashReportData.put(ReportField.CRASH_CONFIGURATION, collectConfiguration(context));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void collectApplicationStartUp(Context context, CoreConfiguration coreConfiguration) {
        if (coreConfiguration.reportContent().contains(ReportField.INITIAL_CONFIGURATION)) {
            this.initialConfiguration = collectConfiguration(context);
        }
    }

    private JSONObject configToJson(Configuration configuration) {
        JSONObject jSONObject = new JSONObject();
        Map<String, SparseArray<String>> valueArrays = getValueArrays();
        for (Field field : configuration.getClass().getFields()) {
            try {
                if (!Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName();
                    try {
                        if (field.getType().equals(Integer.TYPE)) {
                            jSONObject.put(name, getFieldValueName(valueArrays, configuration, field));
                        } else if (field.get(configuration) != null) {
                            jSONObject.put(name, field.get(configuration));
                        }
                    } catch (JSONException e) {
                        ACRA.log.w(ACRA.LOG_TAG, "Could not collect configuration field " + name, e);
                    }
                }
            } catch (IllegalArgumentException e2) {
                ACRA.log.e(ACRA.LOG_TAG, "Error while inspecting device configuration: ", e2);
            } catch (IllegalAccessException e3) {
                ACRA.log.e(ACRA.LOG_TAG, "Error while inspecting device configuration: ", e3);
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0095, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x014e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0150, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0151, code lost:
        r13 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0154, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0156, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0157, code lost:
        r13 = r18;
        r2 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x015c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x015d, code lost:
        r13 = r18;
        r2 = r20;
        r1 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0163, code lost:
        r12 = r17;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0095 A[ExcHandler: IllegalAccessException (e java.lang.IllegalAccessException), Splitter:B:12:0x0086] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x014e A[ExcHandler: IllegalAccessException (e java.lang.IllegalAccessException), PHI: r2 
      PHI: (r2v14 android.util.SparseArray) = (r2v16 android.util.SparseArray), (r2v21 android.util.SparseArray), (r2v21 android.util.SparseArray) binds: [B:75:0x0135, B:65:0x011b, B:66:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:65:0x011b] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0154 A[ExcHandler: IllegalAccessException (e java.lang.IllegalAccessException), PHI: r1 
      PHI: (r1v15 android.util.SparseArray) = (r1v16 android.util.SparseArray), (r1v21 android.util.SparseArray), (r1v21 android.util.SparseArray) binds: [B:59:0x010f, B:48:0x00ed, B:49:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:48:0x00ed] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<java.lang.String, android.util.SparseArray<java.lang.String>> getValueArrays() {
        /*
            r27 = this;
            java.lang.String r1 = "Error while inspecting device configuration: "
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            android.util.SparseArray r3 = new android.util.SparseArray
            r3.<init>()
            android.util.SparseArray r4 = new android.util.SparseArray
            r4.<init>()
            android.util.SparseArray r5 = new android.util.SparseArray
            r5.<init>()
            android.util.SparseArray r6 = new android.util.SparseArray
            r6.<init>()
            android.util.SparseArray r7 = new android.util.SparseArray
            r7.<init>()
            android.util.SparseArray r8 = new android.util.SparseArray
            r8.<init>()
            android.util.SparseArray r9 = new android.util.SparseArray
            r9.<init>()
            android.util.SparseArray r10 = new android.util.SparseArray
            r10.<init>()
            android.util.SparseArray r11 = new android.util.SparseArray
            r11.<init>()
            java.lang.Class<android.content.res.Configuration> r0 = android.content.res.Configuration.class
            java.lang.reflect.Field[] r12 = r0.getFields()
            int r13 = r12.length
            r0 = 0
            r14 = 0
        L_0x003d:
            java.lang.String r0 = "UI_MODE_"
            java.lang.String r15 = "TOUCHSCREEN_"
            r16 = r2
            java.lang.String r2 = "SCREENLAYOUT_"
            r17 = r1
            java.lang.String r1 = "ORIENTATION_"
            r18 = r11
            java.lang.String r11 = "NAVIGATIONHIDDEN_"
            r19 = r0
            java.lang.String r0 = "NAVIGATION_"
            r20 = r10
            java.lang.String r10 = "KEYBOARDHIDDEN_"
            r21 = r15
            java.lang.String r15 = "KEYBOARD_"
            r22 = r9
            java.lang.String r9 = "HARDKEYBOARDHIDDEN_"
            if (r14 >= r13) goto L_0x01a8
            r23 = r13
            r13 = r12[r14]
            int r24 = r13.getModifiers()
            boolean r24 = java.lang.reflect.Modifier.isStatic(r24)
            if (r24 == 0) goto L_0x018e
            int r24 = r13.getModifiers()
            boolean r24 = java.lang.reflect.Modifier.isFinal(r24)
            if (r24 == 0) goto L_0x018e
            r24 = r12
            java.lang.String r12 = r13.getName()
            boolean r9 = r12.startsWith(r9)     // Catch:{ IllegalArgumentException -> 0x0170, IllegalAccessException -> 0x0166 }
            r25 = r14
            r14 = 0
            if (r9 == 0) goto L_0x009b
            int r0 = r13.getInt(r14)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            r3.put(r0, r12)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
        L_0x008d:
            r13 = r18
            r2 = r20
            r1 = r22
            goto L_0x014b
        L_0x0095:
            r0 = move-exception
            goto L_0x0169
        L_0x0098:
            r0 = move-exception
            goto L_0x0173
        L_0x009b:
            boolean r9 = r12.startsWith(r15)     // Catch:{ IllegalArgumentException -> 0x015c, IllegalAccessException -> 0x0095 }
            if (r9 == 0) goto L_0x00a9
            int r0 = r13.getInt(r14)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            r4.put(r0, r12)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            goto L_0x008d
        L_0x00a9:
            boolean r9 = r12.startsWith(r10)     // Catch:{ IllegalArgumentException -> 0x015c, IllegalAccessException -> 0x0095 }
            if (r9 == 0) goto L_0x00b7
            int r0 = r13.getInt(r14)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            r5.put(r0, r12)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            goto L_0x008d
        L_0x00b7:
            boolean r0 = r12.startsWith(r0)     // Catch:{ IllegalArgumentException -> 0x015c, IllegalAccessException -> 0x0095 }
            if (r0 == 0) goto L_0x00c5
            int r0 = r13.getInt(r14)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            r6.put(r0, r12)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            goto L_0x008d
        L_0x00c5:
            boolean r0 = r12.startsWith(r11)     // Catch:{ IllegalArgumentException -> 0x015c, IllegalAccessException -> 0x0095 }
            if (r0 == 0) goto L_0x00d3
            int r0 = r13.getInt(r14)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            r7.put(r0, r12)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            goto L_0x008d
        L_0x00d3:
            boolean r0 = r12.startsWith(r1)     // Catch:{ IllegalArgumentException -> 0x015c, IllegalAccessException -> 0x0095 }
            if (r0 == 0) goto L_0x00e1
            int r0 = r13.getInt(r14)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            r8.put(r0, r12)     // Catch:{ IllegalArgumentException -> 0x0098, IllegalAccessException -> 0x0095 }
            goto L_0x008d
        L_0x00e1:
            boolean r0 = r12.startsWith(r2)     // Catch:{ IllegalArgumentException -> 0x015c, IllegalAccessException -> 0x0095 }
            if (r0 == 0) goto L_0x010b
            int r0 = r13.getInt(r14)     // Catch:{ IllegalArgumentException -> 0x0100, IllegalAccessException -> 0x00f7 }
            r1 = r22
            r1.put(r0, r12)     // Catch:{ IllegalArgumentException -> 0x00f5, IllegalAccessException -> 0x0154 }
            r13 = r18
            r2 = r20
            goto L_0x014b
        L_0x00f5:
            r0 = move-exception
            goto L_0x0103
        L_0x00f7:
            r0 = move-exception
            r1 = r22
        L_0x00fa:
            r13 = r18
            r2 = r20
            goto L_0x017c
        L_0x0100:
            r0 = move-exception
            r1 = r22
        L_0x0103:
            r12 = r17
            r13 = r18
            r2 = r20
            goto L_0x0186
        L_0x010b:
            r1 = r22
            r0 = r21
            boolean r0 = r12.startsWith(r0)     // Catch:{ IllegalArgumentException -> 0x0156, IllegalAccessException -> 0x0154 }
            if (r0 == 0) goto L_0x0131
            int r0 = r13.getInt(r14)     // Catch:{ IllegalArgumentException -> 0x0128, IllegalAccessException -> 0x0121 }
            r2 = r20
            r2.put(r0, r12)     // Catch:{ IllegalArgumentException -> 0x011f, IllegalAccessException -> 0x014e }
            goto L_0x0149
        L_0x011f:
            r0 = move-exception
            goto L_0x012b
        L_0x0121:
            r0 = move-exception
            r2 = r20
        L_0x0124:
            r13 = r18
            goto L_0x017c
        L_0x0128:
            r0 = move-exception
            r2 = r20
        L_0x012b:
            r12 = r17
            r13 = r18
            goto L_0x0186
        L_0x0131:
            r2 = r20
            r0 = r19
            boolean r0 = r12.startsWith(r0)     // Catch:{ IllegalArgumentException -> 0x0150, IllegalAccessException -> 0x014e }
            if (r0 == 0) goto L_0x0149
            int r0 = r13.getInt(r14)     // Catch:{ IllegalArgumentException -> 0x0150, IllegalAccessException -> 0x014e }
            r13 = r18
            r13.put(r0, r12)     // Catch:{ IllegalArgumentException -> 0x0147, IllegalAccessException -> 0x0145 }
            goto L_0x014b
        L_0x0145:
            r0 = move-exception
            goto L_0x017c
        L_0x0147:
            r0 = move-exception
            goto L_0x0163
        L_0x0149:
            r13 = r18
        L_0x014b:
            r12 = r17
            goto L_0x019a
        L_0x014e:
            r0 = move-exception
            goto L_0x0124
        L_0x0150:
            r0 = move-exception
            r13 = r18
            goto L_0x0163
        L_0x0154:
            r0 = move-exception
            goto L_0x00fa
        L_0x0156:
            r0 = move-exception
            r13 = r18
            r2 = r20
            goto L_0x0163
        L_0x015c:
            r0 = move-exception
            r13 = r18
            r2 = r20
            r1 = r22
        L_0x0163:
            r12 = r17
            goto L_0x0186
        L_0x0166:
            r0 = move-exception
            r25 = r14
        L_0x0169:
            r13 = r18
            r2 = r20
            r1 = r22
            goto L_0x017c
        L_0x0170:
            r0 = move-exception
            r25 = r14
        L_0x0173:
            r12 = r17
            r13 = r18
            r2 = r20
            r1 = r22
            goto L_0x0186
        L_0x017c:
            org.acra.log.ACRALog r9 = org.acra.ACRA.log
            java.lang.String r10 = org.acra.ACRA.LOG_TAG
            r12 = r17
            r9.w(r10, r12, r0)
            goto L_0x019a
        L_0x0186:
            org.acra.log.ACRALog r9 = org.acra.ACRA.log
            java.lang.String r10 = org.acra.ACRA.LOG_TAG
            r9.w(r10, r12, r0)
            goto L_0x019a
        L_0x018e:
            r24 = r12
            r25 = r14
            r12 = r17
            r13 = r18
            r2 = r20
            r1 = r22
        L_0x019a:
            int r14 = r25 + 1
            r9 = r1
            r10 = r2
            r1 = r12
            r11 = r13
            r2 = r16
            r13 = r23
            r12 = r24
            goto L_0x003d
        L_0x01a8:
            r14 = r16
            r26 = r19
            r13 = r21
            r12 = r22
            r14.put(r9, r3)
            r14.put(r15, r4)
            r14.put(r10, r5)
            r14.put(r0, r6)
            r14.put(r11, r7)
            r14.put(r1, r8)
            r14.put(r2, r12)
            r1 = r20
            r14.put(r13, r1)
            r1 = r18
            r0 = r26
            r14.put(r0, r1)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.ConfigurationCollector.getValueArrays():java.util.Map");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object getFieldValueName(java.util.Map<java.lang.String, android.util.SparseArray<java.lang.String>> r6, android.content.res.Configuration r7, java.lang.reflect.Field r8) throws java.lang.IllegalAccessException {
        /*
            r5 = this;
            java.lang.String r0 = r8.getName()
            int r1 = r0.hashCode()
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r1) {
                case -1896438090: goto L_0x002d;
                case -845983145: goto L_0x0023;
                case 107917: goto L_0x0019;
                case 108258: goto L_0x000f;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0037
        L_0x000f:
            java.lang.String r1 = "mnc"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0037
            r1 = 1
            goto L_0x0038
        L_0x0019:
            java.lang.String r1 = "mcc"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0037
            r1 = 0
            goto L_0x0038
        L_0x0023:
            java.lang.String r1 = "uiMode"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0037
            r1 = 2
            goto L_0x0038
        L_0x002d:
            java.lang.String r1 = "screenLayout"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0037
            r1 = 3
            goto L_0x0038
        L_0x0037:
            r1 = -1
        L_0x0038:
            if (r1 == 0) goto L_0x009d
            if (r1 == r4) goto L_0x009d
            if (r1 == r3) goto L_0x008c
            if (r1 == r2) goto L_0x007b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r0 = r0.toUpperCase()
            r1.append(r0)
            r0 = 95
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.Object r6 = r6.get(r0)
            android.util.SparseArray r6 = (android.util.SparseArray) r6
            if (r6 != 0) goto L_0x0066
            int r6 = r8.getInt(r7)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            return r6
        L_0x0066:
            int r0 = r8.getInt(r7)
            java.lang.Object r6 = r6.get(r0)
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L_0x007a
            int r6 = r8.getInt(r7)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
        L_0x007a:
            return r6
        L_0x007b:
            java.lang.String r0 = "SCREENLAYOUT_"
            java.lang.Object r6 = r6.get(r0)
            android.util.SparseArray r6 = (android.util.SparseArray) r6
            int r7 = r8.getInt(r7)
            java.lang.String r6 = r5.activeFlags(r6, r7)
            return r6
        L_0x008c:
            java.lang.String r0 = "UI_MODE_"
            java.lang.Object r6 = r6.get(r0)
            android.util.SparseArray r6 = (android.util.SparseArray) r6
            int r7 = r8.getInt(r7)
            java.lang.String r6 = r5.activeFlags(r6, r7)
            return r6
        L_0x009d:
            int r6 = r8.getInt(r7)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.ConfigurationCollector.getFieldValueName(java.util.Map, android.content.res.Configuration, java.lang.reflect.Field):java.lang.Object");
    }

    private String activeFlags(SparseArray<String> sparseArray, int i) {
        int i2;
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            int keyAt = sparseArray.keyAt(i3);
            if (sparseArray.get(keyAt).endsWith(SUFFIX_MASK) && (i2 = keyAt & i) > 0) {
                if (sb.length() > 0) {
                    sb.append('+');
                }
                sb.append(sparseArray.get(i2));
            }
        }
        return sb.toString();
    }

    private JSONObject collectConfiguration(Context context) {
        try {
            return configToJson(context.getResources().getConfiguration());
        } catch (RuntimeException e) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.w(str, "Couldn't retrieve CrashConfiguration for : " + context.getPackageName(), e);
            return null;
        }
    }
}
