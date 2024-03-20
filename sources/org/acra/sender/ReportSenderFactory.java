package org.acra.sender;

import android.content.Context;
import org.acra.config.CoreConfiguration;

public interface ReportSenderFactory {

    /* renamed from: org.acra.sender.ReportSenderFactory$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$enabled(ReportSenderFactory reportSenderFactory, CoreConfiguration coreConfiguration) {
            return true;
        }
    }

    ReportSender create(Context context, CoreConfiguration coreConfiguration);

    boolean enabled(CoreConfiguration coreConfiguration);
}
