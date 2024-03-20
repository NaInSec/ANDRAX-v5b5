package org.acra.collector;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.prefs.SharedPreferencesFactory;
import org.json.JSONException;
import org.json.JSONObject;

public final class SharedPreferencesCollector extends BaseReportFieldCollector {
    public SharedPreferencesCollector() {
        super(ReportField.USER_EMAIL, ReportField.SHARED_PREFERENCES);
    }

    /* renamed from: org.acra.collector.SharedPreferencesCollector$1  reason: invalid class name */
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
                org.acra.ReportField r1 = org.acra.ReportField.USER_EMAIL     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x001f }
                org.acra.ReportField r1 = org.acra.ReportField.SHARED_PREFERENCES     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.SharedPreferencesCollector.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws Exception {
        int i = AnonymousClass1.$SwitchMap$org$acra$ReportField[reportField.ordinal()];
        if (i == 1) {
            crashReportData.put(ReportField.USER_EMAIL, new SharedPreferencesFactory(context, coreConfiguration).create().getString(ACRA.PREF_USER_EMAIL_ADDRESS, (String) null));
        } else if (i == 2) {
            crashReportData.put(ReportField.SHARED_PREFERENCES, collect(context, coreConfiguration));
        } else {
            throw new IllegalArgumentException();
        }
    }

    private JSONObject collect(Context context, CoreConfiguration coreConfiguration) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        TreeMap treeMap = new TreeMap();
        treeMap.put("default", PreferenceManager.getDefaultSharedPreferences(context));
        Iterator<String> it = coreConfiguration.additionalSharedPreferences().iterator();
        while (it.hasNext()) {
            String next = it.next();
            treeMap.put(next, context.getSharedPreferences(next, 0));
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String str = (String) entry.getKey();
            Map<String, ?> all = ((SharedPreferences) entry.getValue()).getAll();
            if (all.isEmpty()) {
                jSONObject.put(str, "empty");
            } else {
                Iterator<String> it2 = all.keySet().iterator();
                while (it2.hasNext()) {
                    if (filteredKey(coreConfiguration, it2.next())) {
                        it2.remove();
                    }
                }
                jSONObject.put(str, new JSONObject(all));
            }
        }
        return jSONObject;
    }

    private boolean filteredKey(CoreConfiguration coreConfiguration, String str) {
        Iterator<String> it = coreConfiguration.excludeMatchingSharedPreferencesKeys().iterator();
        while (it.hasNext()) {
            if (str.matches(it.next())) {
                return true;
            }
        }
        return false;
    }
}
