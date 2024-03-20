package org.acra.legacy;

import android.content.Context;
import java.io.File;
import org.acra.ACRA;
import org.acra.file.CrashReportFileNameParser;
import org.acra.file.ReportLocator;
import org.acra.log.ACRALog;

final class ReportMigrator {
    private final Context context;
    private final CrashReportFileNameParser fileNameParser = new CrashReportFileNameParser();
    private final ReportLocator reportLocator;

    ReportMigrator(Context context2) {
        this.context = context2;
        this.reportLocator = new ReportLocator(context2);
    }

    /* access modifiers changed from: package-private */
    public void migrate() {
        ACRA.log.i(ACRA.LOG_TAG, "Migrating unsent ACRA reports to new file locations");
        File[] crashReportFiles = getCrashReportFiles();
        for (File file : crashReportFiles) {
            String name = file.getName();
            if (this.fileNameParser.isApproved(name)) {
                if (file.renameTo(new File(this.reportLocator.getApprovedFolder(), name)) && ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "Cold not migrate unsent ACRA crash report : " + name);
                }
            } else if (file.renameTo(new File(this.reportLocator.getUnapprovedFolder(), name)) && ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Cold not migrate unsent ACRA crash report : " + name);
            }
        }
        ACRA.log.i(ACRA.LOG_TAG, "Migrated " + crashReportFiles.length + " unsent reports");
    }

    private File[] getCrashReportFiles() {
        File filesDir = this.context.getFilesDir();
        if (filesDir == null) {
            ACRA.log.w(ACRA.LOG_TAG, "Application files directory does not exist! The application may not be installed correctly. Please try reinstalling.");
            return new File[0];
        }
        if (ACRA.DEV_LOGGING) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.d(str, "Looking for error files in " + filesDir.getAbsolutePath());
        }
        File[] listFiles = filesDir.listFiles($$Lambda$ReportMigrator$yKiW9Ll1HFwaZ9CdauG8grunHA0.INSTANCE);
        return listFiles == null ? new File[0] : listFiles;
    }
}
