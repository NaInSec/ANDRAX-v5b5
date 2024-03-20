package org.acra.collector;

import android.content.Context;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collector.Collector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;

abstract class BaseReportFieldCollector implements Collector {
    private final ReportField[] reportFields;

    /* access modifiers changed from: package-private */
    public abstract void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws Exception;

    public /* synthetic */ Collector.Order getOrder() {
        return Collector.CC.$default$getOrder(this);
    }

    BaseReportFieldCollector(ReportField reportField, ReportField... reportFieldArr) {
        this.reportFields = new ReportField[(reportFieldArr.length + 1)];
        ReportField[] reportFieldArr2 = this.reportFields;
        reportFieldArr2[0] = reportField;
        if (reportFieldArr.length > 0) {
            System.arraycopy(reportFieldArr, 0, reportFieldArr2, 1, reportFieldArr.length);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldCollect(Context context, CoreConfiguration coreConfiguration, ReportField reportField, ReportBuilder reportBuilder) {
        return coreConfiguration.reportContent().contains(reportField);
    }

    public final void collect(Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws CollectorException {
        ReportField[] reportFieldArr = this.reportFields;
        int length = reportFieldArr.length;
        int i = 0;
        while (i < length) {
            ReportField reportField = reportFieldArr[i];
            try {
                if (shouldCollect(context, coreConfiguration, reportField, reportBuilder)) {
                    collect(reportField, context, coreConfiguration, reportBuilder, crashReportData);
                }
                i++;
            } catch (Throwable th) {
                crashReportData.put(reportField, (String) null);
                throw new CollectorException("Error while retrieving " + reportField.name() + " data:" + th.getMessage(), th);
            }
        }
    }
}
