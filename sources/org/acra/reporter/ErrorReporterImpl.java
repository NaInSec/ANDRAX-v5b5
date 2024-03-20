package org.acra.reporter;

import android.app.Application;
import android.content.SharedPreferences;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;
import org.acra.ACRA;
import org.acra.ErrorReporter;
import org.acra.builder.LastActivityManager;
import org.acra.builder.ReportBuilder;
import org.acra.builder.ReportExecutor;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportDataFactory;
import org.acra.log.ACRALog;
import org.acra.prefs.SharedPreferencesFactory;
import org.acra.util.InstanceCreator;
import org.acra.util.ProcessFinisher;

public class ErrorReporterImpl implements Thread.UncaughtExceptionHandler, SharedPreferences.OnSharedPreferenceChangeListener, ErrorReporter {
    private final Application context;
    private final Map<String, String> customData = new HashMap();
    private final ReportExecutor reportExecutor;
    private final boolean supportedAndroidVersion;

    public ErrorReporterImpl(Application application, CoreConfiguration coreConfiguration, boolean z, boolean z2) {
        this.context = application;
        this.supportedAndroidVersion = z2;
        CrashReportDataFactory crashReportDataFactory = new CrashReportDataFactory(application, coreConfiguration);
        crashReportDataFactory.collectStartUp();
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        LastActivityManager lastActivityManager = new LastActivityManager(this.context);
        new InstanceCreator();
        this.reportExecutor = new ReportExecutor(application, coreConfiguration, crashReportDataFactory, defaultUncaughtExceptionHandler, new ProcessFinisher(application, coreConfiguration, lastActivityManager));
        this.reportExecutor.setEnabled(z);
    }

    public String putCustomData(String str, String str2) {
        return this.customData.put(str, str2);
    }

    public String removeCustomData(String str) {
        return this.customData.remove(str);
    }

    public void clearCustomData() {
        this.customData.clear();
    }

    public String getCustomData(String str) {
        return this.customData.get(str);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (!this.reportExecutor.isEnabled()) {
            this.reportExecutor.handReportToDefaultExceptionHandler(thread, th);
            return;
        }
        try {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.e(str, "ACRA caught a " + th.getClass().getSimpleName() + " for " + this.context.getPackageName(), th);
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Building report");
            }
            new ReportBuilder().uncaughtExceptionThread(thread).exception(th).customData(this.customData).endApplication().build(this.reportExecutor);
        } catch (Throwable th2) {
            ACRA.log.e(ACRA.LOG_TAG, "ACRA failed to capture the error - handing off to native error reporter", th2);
            this.reportExecutor.handReportToDefaultExceptionHandler(thread, th);
        }
    }

    public void handleSilentException(Throwable th) {
        new ReportBuilder().exception(th).customData(this.customData).sendSilently().build(this.reportExecutor);
    }

    public void setEnabled(boolean z) {
        if (this.supportedAndroidVersion) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("ACRA is ");
            sb.append(z ? "enabled" : "disabled");
            sb.append(" for ");
            sb.append(this.context.getPackageName());
            aCRALog.i(str, sb.toString());
            this.reportExecutor.setEnabled(z);
            return;
        }
        ACRA.log.w(ACRA.LOG_TAG, "ACRA 4.7.0+ requires Froyo or greater. ACRA is disabled and will NOT catch crashes or send messages.");
    }

    public void handleException(Throwable th, boolean z) {
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.exception(th).customData(this.customData);
        if (z) {
            reportBuilder.endApplication();
        }
        reportBuilder.build(this.reportExecutor);
    }

    public void handleException(Throwable th) {
        handleException(th, false);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (ACRA.PREF_DISABLE_ACRA.equals(str) || ACRA.PREF_ENABLE_ACRA.equals(str)) {
            setEnabled(SharedPreferencesFactory.shouldEnableACRA(sharedPreferences));
        }
    }
}
