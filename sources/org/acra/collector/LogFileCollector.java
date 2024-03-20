package org.acra.collector;

import android.content.Context;
import java.io.IOException;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collector.Collector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.util.StreamReader;

public final class LogFileCollector extends BaseReportFieldCollector {
    public LogFileCollector() {
        super(ReportField.APPLICATION_LOG, new ReportField[0]);
    }

    public Collector.Order getOrder() {
        return Collector.Order.LATE;
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws IOException {
        crashReportData.put(ReportField.APPLICATION_LOG, new StreamReader(coreConfiguration.applicationLogFileDir().getFile(context, coreConfiguration.applicationLogFile())).setLimit(coreConfiguration.applicationLogFileLines()).read());
    }
}
