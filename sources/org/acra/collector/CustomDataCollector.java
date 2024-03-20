package org.acra.collector;

import android.content.Context;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.json.JSONObject;

public final class CustomDataCollector extends BaseReportFieldCollector {
    public CustomDataCollector() {
        super(ReportField.CUSTOM_DATA, new ReportField[0]);
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) {
        crashReportData.put(ReportField.CUSTOM_DATA, new JSONObject(reportBuilder.getCustomData()));
    }
}
