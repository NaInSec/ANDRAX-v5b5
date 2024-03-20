package org.acra.file;

import android.content.Context;
import java.io.File;
import java.util.Arrays;
import org.acra.ACRA;
import org.acra.log.ACRALog;

public final class BulkReportDeleter {
    private final ReportLocator reportLocator;

    public BulkReportDeleter(Context context) {
        this.reportLocator = new ReportLocator(context);
    }

    public void deleteReports(boolean z, int i) {
        File[] approvedReports = z ? this.reportLocator.getApprovedReports() : this.reportLocator.getUnapprovedReports();
        Arrays.sort(approvedReports, new LastModifiedComparator());
        for (int i2 = 0; i2 < approvedReports.length - i; i2++) {
            if (!approvedReports[i2].delete()) {
                ACRALog aCRALog = ACRA.log;
                String str = ACRA.LOG_TAG;
                aCRALog.w(str, "Could not delete report : " + approvedReports[i2]);
            }
        }
    }
}
