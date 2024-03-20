package org.acra.collector;

import android.content.Context;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;

public interface Collector {

    public enum Order {
        FIRST,
        EARLY,
        NORMAL,
        LATE,
        LAST
    }

    void collect(Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws CollectorException;

    Order getOrder();

    /* renamed from: org.acra.collector.Collector$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Order $default$getOrder(Collector _this) {
            return Order.NORMAL;
        }
    }
}
