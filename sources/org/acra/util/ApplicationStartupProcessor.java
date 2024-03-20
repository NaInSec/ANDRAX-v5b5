package org.acra.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Handler;
import java.io.File;
import java.util.Calendar;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;
import org.acra.file.BulkReportDeleter;
import org.acra.file.CrashReportFileNameParser;
import org.acra.file.ReportLocator;
import org.acra.interaction.ReportInteractionExecutor;
import org.acra.prefs.SharedPreferencesFactory;
import org.acra.sender.SenderServiceStarter;

public final class ApplicationStartupProcessor {
    private final CoreConfiguration config;
    private final Context context;
    private final BulkReportDeleter reportDeleter;
    private final ReportLocator reportLocator;

    public ApplicationStartupProcessor(Context context2, CoreConfiguration coreConfiguration) {
        this.context = context2;
        this.config = coreConfiguration;
        this.reportDeleter = new BulkReportDeleter(context2);
        this.reportLocator = new ReportLocator(context2);
    }

    public void checkReports(boolean z) {
        new Handler(this.context.getMainLooper()).post(new Runnable(z, Calendar.getInstance()) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ Calendar f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                ApplicationStartupProcessor.this.lambda$checkReports$1$ApplicationStartupProcessor(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$checkReports$1$ApplicationStartupProcessor(boolean z, Calendar calendar) {
        new Thread(new Runnable(z, calendar) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ Calendar f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                ApplicationStartupProcessor.this.lambda$null$0$ApplicationStartupProcessor(this.f$1, this.f$2);
            }
        }).start();
    }

    public /* synthetic */ void lambda$null$0$ApplicationStartupProcessor(boolean z, Calendar calendar) {
        if (this.config.deleteOldUnsentReportsOnApplicationStart()) {
            deleteUnsentReportsFromOldAppVersion();
        }
        if (this.config.deleteUnapprovedReportsOnApplicationStart()) {
            this.reportDeleter.deleteReports(false, 1);
        }
        if (z) {
            sendApprovedReports();
            approveOneReport(calendar);
        }
    }

    private void approveOneReport(Calendar calendar) {
        File[] unapprovedReports = this.reportLocator.getUnapprovedReports();
        if (unapprovedReports.length != 0 && new CrashReportFileNameParser().getTimestamp(unapprovedReports[0].getName()).before(calendar)) {
            new ReportInteractionExecutor(this.context, this.config).performInteractions(unapprovedReports[0]);
        }
    }

    private void deleteUnsentReportsFromOldAppVersion() {
        SharedPreferences create = new SharedPreferencesFactory(this.context, this.config).create();
        int appVersion = getAppVersion();
        if (((long) appVersion) > ((long) create.getInt(ACRA.PREF_LAST_VERSION_NR, 0))) {
            this.reportDeleter.deleteReports(true, 0);
            this.reportDeleter.deleteReports(false, 0);
            create.edit().putInt(ACRA.PREF_LAST_VERSION_NR, appVersion).apply();
        }
    }

    private void sendApprovedReports() {
        if (this.reportLocator.getApprovedReports().length != 0) {
            new SenderServiceStarter(this.context, this.config).startService(false, false);
        }
    }

    private int getAppVersion() {
        PackageInfo packageInfo = new PackageManagerWrapper(this.context).getPackageInfo();
        if (packageInfo == null) {
            return 0;
        }
        return packageInfo.versionCode;
    }
}
