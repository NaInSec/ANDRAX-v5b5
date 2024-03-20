package org.acra.sender;

import android.content.Context;
import org.acra.ACRA;
import org.acra.data.CrashReportData;
import org.acra.log.ACRALog;

final class NullSender implements ReportSender {
    NullSender() {
    }

    public void send(Context context, CrashReportData crashReportData) {
        ACRALog aCRALog = ACRA.log;
        String str = ACRA.LOG_TAG;
        aCRALog.w(str, context.getPackageName() + " reports will NOT be sent - no valid ReportSender is configured. Try setting 'formUri' or 'mailTo'");
    }
}
