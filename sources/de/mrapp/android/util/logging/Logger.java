package de.mrapp.android.util.logging;

import android.util.Log;
import de.mrapp.android.util.ClassUtil;
import de.mrapp.android.util.Condition;

public class Logger {
    private LogLevel logLevel;

    public Logger(LogLevel logLevel2) {
        setLogLevel(logLevel2);
    }

    public final LogLevel getLogLevel() {
        return this.logLevel;
    }

    public final void setLogLevel(LogLevel logLevel2) {
        Condition.ensureNotNull(logLevel2, "The log level may not be null");
        this.logLevel = logLevel2;
    }

    public final void logVerbose(Class<?> cls, String str) {
        Condition.ensureNotNull(cls, "The tag may not be null");
        Condition.ensureNotNull(str, "The message may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The message may not be empty");
        if (LogLevel.VERBOSE.getRank() >= getLogLevel().getRank()) {
            Log.v(ClassUtil.getTruncatedName(cls), str);
        }
    }

    public final void logVerbose(Class<?> cls, String str, Throwable th) {
        Condition.ensureNotNull(cls, "The tag may not be null");
        Condition.ensureNotNull(str, "The message may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The message may not be empty");
        Condition.ensureNotNull(th, "The cause may not be null");
        if (LogLevel.VERBOSE.getRank() >= getLogLevel().getRank()) {
            Log.v(ClassUtil.getTruncatedName(cls), str, th);
        }
    }

    public final void logDebug(Class<?> cls, String str) {
        Condition.ensureNotNull(cls, "The tag may not be null");
        Condition.ensureNotNull(str, "The message may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The message may not be empty");
        if (LogLevel.DEBUG.getRank() >= getLogLevel().getRank()) {
            Log.d(ClassUtil.getTruncatedName(cls), str);
        }
    }

    public final void logDebug(Class<?> cls, String str, Throwable th) {
        Condition.ensureNotNull(cls, "The tag may not be null");
        Condition.ensureNotNull(str, "The message may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The message may not be empty");
        Condition.ensureNotNull(th, "The cause may not be null");
        if (LogLevel.DEBUG.getRank() >= getLogLevel().getRank()) {
            Log.d(ClassUtil.getTruncatedName(cls), str, th);
        }
    }

    public final void logInfo(Class<?> cls, String str) {
        Condition.ensureNotNull(cls, "The tag may not be null");
        Condition.ensureNotNull(str, "The message may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The message may not be empty");
        if (LogLevel.INFO.getRank() >= getLogLevel().getRank()) {
            Log.i(ClassUtil.getTruncatedName(cls), str);
        }
    }

    public final void logInfo(Class<?> cls, String str, Throwable th) {
        Condition.ensureNotNull(cls, "The tag may not be null");
        Condition.ensureNotNull(str, "The message may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The message may not be empty");
        Condition.ensureNotNull(th, "The cause may not be null");
        if (LogLevel.INFO.getRank() >= getLogLevel().getRank()) {
            Log.i(ClassUtil.getTruncatedName(cls), str, th);
        }
    }

    public final void logWarn(Class<?> cls, String str) {
        Condition.ensureNotNull(cls, "The tag may not be null");
        Condition.ensureNotNull(str, "The message may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The message may not be empty");
        if (LogLevel.WARN.getRank() >= getLogLevel().getRank()) {
            Log.w(ClassUtil.getTruncatedName(cls), str);
        }
    }

    public final void logWarn(Class<?> cls, String str, Throwable th) {
        Condition.ensureNotNull(cls, "The tag may not be null");
        Condition.ensureNotNull(str, "The message may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The message may not be empty");
        Condition.ensureNotNull(th, "The cause may not be null");
        if (LogLevel.WARN.getRank() >= getLogLevel().getRank()) {
            Log.w(ClassUtil.getTruncatedName(cls), str, th);
        }
    }

    public final void logError(Class<?> cls, String str) {
        Condition.ensureNotNull(cls, "The tag may not be null");
        Condition.ensureNotNull(str, "The message may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The message may not be empty");
        if (LogLevel.ERROR.getRank() >= getLogLevel().getRank()) {
            Log.e(ClassUtil.getTruncatedName(cls), str);
        }
    }

    public final void logError(Class<?> cls, String str, Throwable th) {
        Condition.ensureNotNull(cls, "The tag may not be null");
        Condition.ensureNotNull(str, "The message may not be null");
        Condition.ensureNotEmpty((CharSequence) str, "The message may not be empty");
        Condition.ensureNotNull(th, "The cause may not be null");
        if (LogLevel.ERROR.getRank() >= getLogLevel().getRank()) {
            Log.e(ClassUtil.getTruncatedName(cls), str, th);
        }
    }
}
