package org.acra.sender;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;
import org.acra.log.ACRALog;
import org.acra.sender.ReportSenderFactory;

public final class DefaultReportSenderFactory implements ReportSenderFactory {
    public /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return ReportSenderFactory.CC.$default$enabled(this, coreConfiguration);
    }

    public ReportSender create(Context context, CoreConfiguration coreConfiguration) {
        ArrayList arrayList = new ArrayList();
        Iterator<S> it = ServiceLoader.load(ReportSenderFactory.class, getClass().getClassLoader()).iterator();
        while (it.hasNext()) {
            try {
                ReportSenderFactory reportSenderFactory = (ReportSenderFactory) it.next();
                if (reportSenderFactory.enabled(coreConfiguration)) {
                    arrayList.add(reportSenderFactory);
                } else if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "Ignoring disabled ReportSenderFactory of type " + reportSenderFactory.getClass().getSimpleName());
                }
            } catch (ServiceConfigurationError e) {
                ACRA.log.e(ACRA.LOG_TAG, "Unable to load ReportSenderFactory", e);
            }
        }
        if (arrayList.size() == 1) {
            if (ACRA.DEV_LOGGING) {
                ACRALog aCRALog2 = ACRA.log;
                String str2 = ACRA.LOG_TAG;
                aCRALog2.d(str2, "Autodiscovered ReportSenderFactory of type " + ((ReportSenderFactory) arrayList.get(0)).getClass().getSimpleName());
            }
            return ((ReportSenderFactory) arrayList.get(0)).create(context, coreConfiguration);
        }
        if (arrayList.size() > 1) {
            ACRA.log.w(ACRA.LOG_TAG, "Multiple ReportSenderFactories were discovered - please configure those you want to use. No reports will be sent");
        } else {
            ACRA.log.w(ACRA.LOG_TAG, "No ReportSenderFactories were discovered. No reports will be sent");
        }
        return new NullSender();
    }
}
