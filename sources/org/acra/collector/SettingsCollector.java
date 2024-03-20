package org.acra.collector;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.json.JSONObject;

public final class SettingsCollector extends BaseReportFieldCollector {
    private static final String ERROR = "Error: ";

    public SettingsCollector() {
        super(ReportField.SETTINGS_SYSTEM, ReportField.SETTINGS_SECURE, ReportField.SETTINGS_GLOBAL);
    }

    /* renamed from: org.acra.collector.SettingsCollector$1  reason: invalid class name */
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
                org.acra.ReportField r1 = org.acra.ReportField.SETTINGS_SYSTEM     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x001f }
                org.acra.ReportField r1 = org.acra.ReportField.SETTINGS_SECURE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x002a }
                org.acra.ReportField r1 = org.acra.ReportField.SETTINGS_GLOBAL     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.SettingsCollector.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws Exception {
        int i = AnonymousClass1.$SwitchMap$org$acra$ReportField[reportField.ordinal()];
        if (i == 1) {
            crashReportData.put(ReportField.SETTINGS_SYSTEM, collectSettings(context, coreConfiguration, Settings.System.class));
        } else if (i == 2) {
            crashReportData.put(ReportField.SETTINGS_SECURE, collectSettings(context, coreConfiguration, Settings.Secure.class));
        } else if (i == 3) {
            crashReportData.put(ReportField.SETTINGS_GLOBAL, Build.VERSION.SDK_INT >= 17 ? collectSettings(context, coreConfiguration, Settings.Global.class) : null);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private JSONObject collectSettings(Context context, CoreConfiguration coreConfiguration, Class<?> cls) throws NoSuchMethodException {
        JSONObject jSONObject = new JSONObject();
        Field[] fields = cls.getFields();
        Method method = cls.getMethod("getString", new Class[]{ContentResolver.class, String.class});
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Deprecated.class) && field.getType() == String.class && isAuthorized(coreConfiguration, field)) {
                try {
                    Object invoke = method.invoke((Object) null, new Object[]{context.getContentResolver(), field.get((Object) null)});
                    if (invoke != null) {
                        jSONObject.put(field.getName(), invoke);
                    }
                } catch (Exception e) {
                    ACRA.log.w(ACRA.LOG_TAG, ERROR, e);
                }
            }
        }
        return jSONObject;
    }

    private boolean isAuthorized(CoreConfiguration coreConfiguration, Field field) {
        if (field == null || field.getName().startsWith("WIFI_AP")) {
            return false;
        }
        Iterator<String> it = coreConfiguration.excludeMatchingSettingsKeys().iterator();
        while (it.hasNext()) {
            if (field.getName().matches(it.next())) {
                return false;
            }
        }
        return true;
    }
}
