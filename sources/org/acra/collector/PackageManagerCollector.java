package org.acra.collector;

import android.content.Context;
import android.content.pm.PackageInfo;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.util.PackageManagerWrapper;

public final class PackageManagerCollector extends BaseReportFieldCollector {
    public PackageManagerCollector() {
        super(ReportField.APP_VERSION_NAME, ReportField.APP_VERSION_CODE);
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws CollectorException {
        PackageInfo packageInfo = new PackageManagerWrapper(context).getPackageInfo();
        if (packageInfo != null) {
            int i = AnonymousClass1.$SwitchMap$org$acra$ReportField[reportField.ordinal()];
            if (i == 1) {
                crashReportData.put(ReportField.APP_VERSION_NAME, packageInfo.versionName);
            } else if (i == 2) {
                crashReportData.put(ReportField.APP_VERSION_CODE, packageInfo.versionCode);
            }
        } else {
            throw new CollectorException("Failed to get package info");
        }
    }

    /* renamed from: org.acra.collector.PackageManagerCollector$1  reason: invalid class name */
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
                org.acra.ReportField r1 = org.acra.ReportField.APP_VERSION_NAME     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$acra$ReportField     // Catch:{ NoSuchFieldError -> 0x001f }
                org.acra.ReportField r1 = org.acra.ReportField.APP_VERSION_CODE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.PackageManagerCollector.AnonymousClass1.<clinit>():void");
        }
    }
}
