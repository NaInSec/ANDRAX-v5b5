package org.acra.collector;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;

public final class TimeCollector extends BaseReportFieldCollector implements ApplicationStartupCollector {
    private Calendar appStartDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(ACRAConstants.DATE_TIME_FORMAT_STRING, Locale.ENGLISH);

    public TimeCollector() {
        super(ReportField.USER_APP_START_DATE, ReportField.USER_CRASH_DATE);
    }

    /* renamed from: org.acra.collector.TimeCollector$1  reason: invalid class name */
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
                org.acra.ReportField r1 = org.acra.ReportField.USER_APP_START_DATE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x001f }
                org.acra.ReportField r1 = org.acra.ReportField.USER_CRASH_DATE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.TimeCollector.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) {
        Calendar calendar;
        int i = AnonymousClass1.$SwitchMap$org$acra$ReportField[reportField.ordinal()];
        if (i == 1) {
            calendar = this.appStartDate;
        } else if (i == 2) {
            calendar = new GregorianCalendar();
        } else {
            throw new IllegalArgumentException();
        }
        crashReportData.put(reportField, getTimeString(calendar));
    }

    public void collectApplicationStartUp(Context context, CoreConfiguration coreConfiguration) {
        if (coreConfiguration.reportContent().contains(ReportField.USER_APP_START_DATE)) {
            this.appStartDate = new GregorianCalendar();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldCollect(Context context, CoreConfiguration coreConfiguration, ReportField reportField, ReportBuilder reportBuilder) {
        return reportField == ReportField.USER_CRASH_DATE || super.shouldCollect(context, coreConfiguration, reportField, reportBuilder);
    }

    private String getTimeString(Calendar calendar) {
        return this.dateFormat.format(Long.valueOf(calendar.getTimeInMillis()));
    }
}
