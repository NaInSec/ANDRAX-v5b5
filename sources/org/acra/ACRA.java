package org.acra;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;
import java.io.IOException;
import org.acra.config.ACRAConfigurationException;
import org.acra.config.CoreConfiguration;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.legacy.LegacyFileHandler;
import org.acra.log.ACRALog;
import org.acra.log.AndroidLogDelegate;
import org.acra.prefs.SharedPreferencesFactory;
import org.acra.reporter.ErrorReporterImpl;
import org.acra.util.ApplicationStartupProcessor;
import org.acra.util.StreamReader;
import org.acra.util.StubCreator;

public final class ACRA {
    private static final String ACRA_PRIVATE_PROCESS_NAME = ":acra";
    public static boolean DEV_LOGGING = false;
    public static final String LOG_TAG = ACRA.class.getSimpleName();
    public static final String PREF_ALWAYS_ACCEPT = "acra.alwaysaccept";
    public static final String PREF_DISABLE_ACRA = "acra.disable";
    public static final String PREF_ENABLE_ACRA = "acra.enable";
    public static final String PREF_ENABLE_DEVICE_ID = "acra.deviceid.enable";
    public static final String PREF_ENABLE_SYSTEM_LOGS = "acra.syslog.enable";
    public static final String PREF_LAST_VERSION_NR = "acra.lastVersionNr";
    public static final String PREF_USER_EMAIL_ADDRESS = "acra.user.email";
    private static ErrorReporter errorReporterSingleton = StubCreator.createErrorReporterStub();
    public static ACRALog log = new AndroidLogDelegate();

    private ACRA() {
    }

    public static void init(Application application) {
        init(application, new CoreConfigurationBuilder(application));
    }

    public static void init(Application application, CoreConfigurationBuilder coreConfigurationBuilder) {
        init(application, coreConfigurationBuilder, true);
    }

    public static void init(Application application, CoreConfigurationBuilder coreConfigurationBuilder, boolean z) {
        try {
            init(application, coreConfigurationBuilder.build(), z);
        } catch (ACRAConfigurationException e) {
            ACRALog aCRALog = log;
            String str = LOG_TAG;
            aCRALog.w(str, "Configuration Error - ACRA not started : " + e.getMessage());
        }
    }

    public static void init(Application application, CoreConfiguration coreConfiguration) {
        init(application, coreConfiguration, true);
    }

    public static void init(Application application, CoreConfiguration coreConfiguration, boolean z) {
        boolean isACRASenderServiceProcess = isACRASenderServiceProcess();
        if (isACRASenderServiceProcess && DEV_LOGGING) {
            log.d(LOG_TAG, "Not initialising ACRA to listen for uncaught Exceptions as this is the SendWorker process and we only send reports, we don't capture them to avoid infinite loops");
        }
        boolean z2 = true;
        boolean z3 = Build.VERSION.SDK_INT >= 14;
        if (!z3) {
            log.w(LOG_TAG, "ACRA 5.1.0+ requires ICS or greater. ACRA is disabled and will NOT catch crashes or send messages.");
        }
        if (isInitialised()) {
            log.w(LOG_TAG, "ACRA#init called more than once. Won't do anything more.");
        } else if (coreConfiguration == null) {
            log.e(LOG_TAG, "ACRA#init called but no CoreConfiguration provided");
        } else {
            SharedPreferences create = new SharedPreferencesFactory(application, coreConfiguration).create();
            new LegacyFileHandler(application, create).updateToCurrentVersionIfNecessary();
            if (!isACRASenderServiceProcess) {
                if (!z3 || !SharedPreferencesFactory.shouldEnableACRA(create)) {
                    z2 = false;
                }
                ACRALog aCRALog = log;
                String str = LOG_TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("ACRA is ");
                sb.append(z2 ? "enabled" : "disabled");
                sb.append(" for ");
                sb.append(application.getPackageName());
                sb.append(", initializing...");
                aCRALog.i(str, sb.toString());
                ErrorReporterImpl errorReporterImpl = new ErrorReporterImpl(application, coreConfiguration, z2, z3);
                errorReporterSingleton = errorReporterImpl;
                if (z) {
                    new ApplicationStartupProcessor(application, coreConfiguration).checkReports(z2);
                }
                create.registerOnSharedPreferenceChangeListener(errorReporterImpl);
            }
        }
    }

    public static boolean isInitialised() {
        return errorReporterSingleton instanceof ErrorReporterImpl;
    }

    public static boolean isACRASenderServiceProcess() {
        String currentProcessName = getCurrentProcessName();
        if (DEV_LOGGING) {
            ACRALog aCRALog = log;
            String str = LOG_TAG;
            aCRALog.d(str, "ACRA processName='" + currentProcessName + '\'');
        }
        return currentProcessName != null && currentProcessName.endsWith(ACRA_PRIVATE_PROCESS_NAME);
    }

    private static String getCurrentProcessName() {
        try {
            return new StreamReader("/proc/self/cmdline").read().trim();
        } catch (IOException unused) {
            return null;
        }
    }

    public static ErrorReporter getErrorReporter() {
        return errorReporterSingleton;
    }

    public static void setLog(ACRALog aCRALog) {
        if (aCRALog != null) {
            log = aCRALog;
            return;
        }
        throw new NullPointerException("ACRALog cannot be null");
    }
}
