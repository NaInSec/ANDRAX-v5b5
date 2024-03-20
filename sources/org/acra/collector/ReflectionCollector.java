package org.acra.collector;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ReflectionCollector extends BaseReportFieldCollector {
    public ReflectionCollector() {
        super(ReportField.BUILD, ReportField.BUILD_CONFIG, ReportField.ENVIRONMENT);
    }

    /* renamed from: org.acra.collector.ReflectionCollector$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$acra$ReportField = new int[ReportField.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                org.acra.ReportField[] r0 = org.acra.ReportField.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$acra$ReportField = r0
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.acra.ReportField r1 = org.acra.ReportField.BUILD     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x001f }
                org.acra.ReportField r1 = org.acra.ReportField.BUILD_CONFIG     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x002a }
                org.acra.ReportField r1 = org.acra.ReportField.ENVIRONMENT     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.ReflectionCollector.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws JSONException, ClassNotFoundException {
        JSONObject jSONObject = new JSONObject();
        int i = AnonymousClass1.$SwitchMap$org$acra$ReportField[reportField.ordinal()];
        if (i == 1) {
            collectConstants(Build.class, jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            collectConstants(Build.VERSION.class, jSONObject2);
            jSONObject.put("VERSION", jSONObject2);
        } else if (i == 2) {
            collectConstants(getBuildConfigClass(context, coreConfiguration), jSONObject);
        } else if (i == 3) {
            collectStaticGettersResults(Environment.class, jSONObject);
        } else {
            throw new IllegalArgumentException();
        }
        crashReportData.put(reportField, jSONObject);
    }

    private static void collectConstants(Class<?> cls, JSONObject jSONObject) throws JSONException {
        for (Field field : cls.getFields()) {
            try {
                Object obj = field.get((Object) null);
                if (obj != null) {
                    if (field.getType().isArray()) {
                        jSONObject.put(field.getName(), new JSONArray(Arrays.asList((Object[]) obj)));
                    } else {
                        jSONObject.put(field.getName(), obj);
                    }
                }
            } catch (IllegalAccessException | IllegalArgumentException unused) {
            }
        }
    }

    private void collectStaticGettersResults(Class<?> cls, JSONObject jSONObject) throws JSONException {
        for (Method method : cls.getMethods()) {
            if (method.getParameterTypes().length == 0 && ((method.getName().startsWith("get") || method.getName().startsWith("is")) && !"getClass".equals(method.getName()))) {
                try {
                    jSONObject.put(method.getName(), method.invoke((Object) null, (Object[]) null));
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                }
            }
        }
    }

    private Class<?> getBuildConfigClass(Context context, CoreConfiguration coreConfiguration) throws ClassNotFoundException {
        Class<?> buildConfigClass = coreConfiguration.buildConfigClass();
        if (!buildConfigClass.equals(Object.class)) {
            return buildConfigClass;
        }
        return Class.forName(context.getPackageName() + ".BuildConfig");
    }
}
