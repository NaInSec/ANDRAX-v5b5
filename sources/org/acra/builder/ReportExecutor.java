package org.acra.builder;

import android.content.Context;
import android.os.Debug;
import android.os.Looper;
import android.os.StrictMode;
import java.io.File;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import org.acra.ACRA;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.config.CoreConfiguration;
import org.acra.config.ReportingAdministrator;
import org.acra.data.CrashReportData;
import org.acra.data.CrashReportDataFactory;
import org.acra.file.CrashReportPersister;
import org.acra.file.ReportLocator;
import org.acra.interaction.ReportInteractionExecutor;
import org.acra.log.ACRALog;
import org.acra.sender.SenderServiceStarter;
import org.acra.util.ProcessFinisher;
import org.acra.util.ToastSender;

public class ReportExecutor {
    private final CoreConfiguration config;
    private final Context context;
    private final CrashReportDataFactory crashReportDataFactory;
    private final Thread.UncaughtExceptionHandler defaultExceptionHandler;
    private boolean enabled = false;
    private final ProcessFinisher processFinisher;
    private final List<ReportingAdministrator> reportingAdministrators;

    public ReportExecutor(Context context2, CoreConfiguration coreConfiguration, CrashReportDataFactory crashReportDataFactory2, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, ProcessFinisher processFinisher2) {
        this.context = context2;
        this.config = coreConfiguration;
        this.crashReportDataFactory = crashReportDataFactory2;
        this.defaultExceptionHandler = uncaughtExceptionHandler;
        this.processFinisher = processFinisher2;
        this.reportingAdministrators = new ArrayList();
        Iterator<S> it = ServiceLoader.load(ReportingAdministrator.class, getClass().getClassLoader()).iterator();
        while (it.hasNext()) {
            try {
                ReportingAdministrator reportingAdministrator = (ReportingAdministrator) it.next();
                if (reportingAdministrator.enabled(coreConfiguration)) {
                    if (ACRA.DEV_LOGGING) {
                        ACRALog aCRALog = ACRA.log;
                        String str = ACRA.LOG_TAG;
                        aCRALog.d(str, "Loaded ReportingAdministrator of class " + reportingAdministrator.getClass().getName());
                    }
                    this.reportingAdministrators.add(reportingAdministrator);
                }
            } catch (ServiceConfigurationError e) {
                ACRA.log.e(ACRA.LOG_TAG, "Unable to load ReportingAdministrator", e);
            }
        }
    }

    public void handReportToDefaultExceptionHandler(Thread thread, Throwable th) {
        if (this.defaultExceptionHandler != null) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.i(str, "ACRA is disabled for " + this.context.getPackageName() + " - forwarding uncaught Exception on to default ExceptionHandler");
            this.defaultExceptionHandler.uncaughtException(thread, th);
            return;
        }
        ACRALog aCRALog2 = ACRA.log;
        String str2 = ACRA.LOG_TAG;
        aCRALog2.e(str2, "ACRA is disabled for " + this.context.getPackageName() + " - no default ExceptionHandler");
        ACRALog aCRALog3 = ACRA.log;
        String str3 = ACRA.LOG_TAG;
        aCRALog3.e(str3, "ACRA caught a " + th.getClass().getSimpleName() + " for " + this.context.getPackageName(), th);
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final void execute(ReportBuilder reportBuilder) {
        if (!this.enabled) {
            ACRA.log.v(ACRA.LOG_TAG, "ACRA is disabled. Report not sent.");
            return;
        }
        CrashReportData crashReportData = null;
        ReportingAdministrator reportingAdministrator = null;
        for (ReportingAdministrator next : this.reportingAdministrators) {
            try {
                if (!next.shouldStartCollecting(this.context, this.config, reportBuilder)) {
                    reportingAdministrator = next;
                }
            } catch (Throwable th) {
                ACRA.log.w(ACRA.LOG_TAG, "ReportingAdministrator " + next.getClass().getName() + " threw exception", th);
            }
        }
        if (reportingAdministrator == null) {
            crashReportData = this.crashReportDataFactory.createCrashData(reportBuilder);
            for (ReportingAdministrator next2 : this.reportingAdministrators) {
                try {
                    if (!next2.shouldSendReport(this.context, this.config, crashReportData)) {
                        reportingAdministrator = next2;
                    }
                } catch (Throwable th2) {
                    ACRA.log.w(ACRA.LOG_TAG, "ReportingAdministrator " + next2.getClass().getName() + " threw exception", th2);
                }
            }
        } else if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Not collecting crash report because of ReportingAdministrator " + reportingAdministrator.getClass().getName());
        }
        if (reportBuilder.isEndApplication()) {
            this.processFinisher.finishLastActivity(reportBuilder.getUncaughtExceptionThread());
        }
        if (reportingAdministrator == null) {
            StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
            File reportFileName = getReportFileName(crashReportData);
            saveCrashReportFile(reportFileName, crashReportData);
            ReportInteractionExecutor reportInteractionExecutor = new ReportInteractionExecutor(this.context, this.config);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            if (reportBuilder.isSendSilently()) {
                startSendingReports(reportInteractionExecutor.hasInteractions());
            } else if (reportInteractionExecutor.performInteractions(reportFileName)) {
                startSendingReports(false);
            }
        } else {
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Not sending crash report because of ReportingAdministrator " + reportingAdministrator.getClass().getName());
            }
            try {
                reportingAdministrator.notifyReportDropped(this.context, this.config);
            } catch (Throwable th3) {
                ACRA.log.w(ACRA.LOG_TAG, "ReportingAdministrator " + reportingAdministrator.getClass().getName() + " threw exeption", th3);
            }
        }
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Wait for Interactions + worker ended. Kill Application ? " + reportBuilder.isEndApplication());
        }
        if (reportBuilder.isEndApplication()) {
            boolean z = true;
            for (ReportingAdministrator next3 : this.reportingAdministrators) {
                try {
                    if (!next3.shouldKillApplication(this.context, this.config, reportBuilder, crashReportData)) {
                        z = false;
                    }
                } catch (Throwable th4) {
                    ACRA.log.w(ACRA.LOG_TAG, "ReportingAdministrator " + next3.getClass().getName() + " threw exception", th4);
                }
            }
            if (!z) {
                return;
            }
            if (Debug.isDebuggerConnected()) {
                new Thread(new Runnable() {
                    public final void run() {
                        ReportExecutor.this.lambda$execute$0$ReportExecutor();
                    }
                }).start();
                ACRA.log.w(ACRA.LOG_TAG, "Warning: Acra may behave differently with a debugger attached");
                return;
            }
            endApplication(reportBuilder.getUncaughtExceptionThread(), reportBuilder.getException());
        }
    }

    public /* synthetic */ void lambda$execute$0$ReportExecutor() {
        Looper.prepare();
        ToastSender.sendToast(this.context, "Warning: Acra may behave differently with a debugger attached", 1);
        Looper.loop();
    }

    private void endApplication(Thread thread, Throwable th) {
        boolean alsoReportToAndroidFramework = this.config.alsoReportToAndroidFramework();
        if (!(thread != null) || !alsoReportToAndroidFramework || this.defaultExceptionHandler == null) {
            this.processFinisher.endApplication();
            return;
        }
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Handing Exception on to default ExceptionHandler");
        }
        this.defaultExceptionHandler.uncaughtException(thread, th);
    }

    private void startSendingReports(boolean z) {
        if (this.enabled) {
            new SenderServiceStarter(this.context, this.config).startService(z, true);
        } else {
            ACRA.log.w(ACRA.LOG_TAG, "Would be sending reports, but ACRA is disabled");
        }
    }

    private File getReportFileName(CrashReportData crashReportData) {
        String string = crashReportData.getString(ReportField.USER_CRASH_DATE);
        String string2 = crashReportData.getString(ReportField.IS_SILENT);
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(string2 != null ? ACRAConstants.SILENT_SUFFIX : "");
        sb.append(ACRAConstants.REPORTFILE_EXTENSION);
        return new File(new ReportLocator(this.context).getUnapprovedFolder(), sb.toString());
    }

    private void saveCrashReportFile(File file, CrashReportData crashReportData) {
        try {
            if (ACRA.DEV_LOGGING) {
                ACRALog aCRALog = ACRA.log;
                String str = ACRA.LOG_TAG;
                aCRALog.d(str, "Writing crash report file " + file);
            }
            new CrashReportPersister().store(crashReportData, file);
        } catch (Exception e) {
            ACRA.log.e(ACRA.LOG_TAG, "An error occurred while writing the report file...", e);
        }
    }
}
