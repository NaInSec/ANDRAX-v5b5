package org.acra.collector;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.android.internal.util.Predicate;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collections.ImmutableList;
import org.acra.collector.Collector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.log.ACRALog;
import org.acra.prefs.SharedPreferencesFactory;
import org.acra.util.PackageManagerWrapper;
import org.acra.util.StreamReader;

public final class LogCatCollector extends BaseReportFieldCollector {
    private static final int READ_TIMEOUT = 3000;

    public LogCatCollector() {
        super(ReportField.LOGCAT, ReportField.EVENTSLOG, ReportField.RADIOLOG);
    }

    public Collector.Order getOrder() {
        return Collector.Order.FIRST;
    }

    private String collectLogCat(CoreConfiguration coreConfiguration, String str) throws IOException {
        String str2;
        int myPid = Process.myPid();
        $$Lambda$LogCatCollector$Rb0TOFHgZwC2I0fhPJcdwDxUjFQ r2 = null;
        if (Build.VERSION.SDK_INT >= 16 || !coreConfiguration.logcatFilterByPid() || myPid <= 0) {
            str2 = null;
        } else {
            str2 = Integer.toString(myPid) + "):";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("logcat");
        if (str != null) {
            arrayList.add("-b");
            arrayList.add(str);
        }
        ImmutableList<String> logcatArguments = coreConfiguration.logcatArguments();
        int indexOf = logcatArguments.indexOf("-t");
        int i = -1;
        if (indexOf > -1 && indexOf < logcatArguments.size()) {
            i = Integer.parseInt(logcatArguments.get(indexOf + 1));
        }
        arrayList.addAll(logcatArguments);
        Process start = new ProcessBuilder(new String[0]).command(arrayList).redirectErrorStream(true).start();
        if (ACRA.DEV_LOGGING) {
            ACRALog aCRALog = ACRA.log;
            String str3 = ACRA.LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Retrieving logcat output (buffer:");
            if (str == null) {
                str = "default";
            }
            sb.append(str);
            sb.append(")...");
            aCRALog.d(str3, sb.toString());
        }
        try {
            InputStream inputStream = start.getInputStream();
            if (str2 != null) {
                r2 = new Predicate(str2) {
                    private final /* synthetic */ String f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final boolean apply(Object obj) {
                        return ((String) obj).contains(this.f$0);
                    }
                };
            }
            return streamToString(coreConfiguration, inputStream, r2, i);
        } finally {
            start.destroy();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldCollect(Context context, CoreConfiguration coreConfiguration, ReportField reportField, ReportBuilder reportBuilder) {
        if (!super.shouldCollect(context, coreConfiguration, reportField, reportBuilder) || ((Build.VERSION.SDK_INT < 16 && !new PackageManagerWrapper(context).hasPermission("android.permission.READ_LOGS")) || !new SharedPreferencesFactory(context, coreConfiguration).create().getBoolean(ACRA.PREF_ENABLE_SYSTEM_LOGS, true))) {
            return false;
        }
        return true;
    }

    /* renamed from: org.acra.collector.LogCatCollector$1  reason: invalid class name */
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
                org.acra.ReportField r1 = org.acra.ReportField.LOGCAT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x001f }
                org.acra.ReportField r1 = org.acra.ReportField.EVENTSLOG     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x002a }
                org.acra.ReportField r1 = org.acra.ReportField.RADIOLOG     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.LogCatCollector.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws IOException {
        int i = AnonymousClass1.$SwitchMap$org$acra$ReportField[reportField.ordinal()];
        String str = null;
        if (i != 1) {
            if (i == 2) {
                str = "events";
            } else if (i == 3) {
                str = "radio";
            }
        }
        crashReportData.put(reportField, collectLogCat(coreConfiguration, str));
    }

    private String streamToString(CoreConfiguration coreConfiguration, InputStream inputStream, Predicate<String> predicate, int i) throws IOException {
        StreamReader limit = new StreamReader(inputStream).setFilter(predicate).setLimit(i);
        if (coreConfiguration.logcatReadNonBlocking()) {
            limit.setTimeout(3000);
        }
        return limit.read();
    }
}
