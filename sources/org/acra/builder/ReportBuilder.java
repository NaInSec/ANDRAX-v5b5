package org.acra.builder;

import java.util.HashMap;
import java.util.Map;

public final class ReportBuilder {
    private final Map<String, String> customData = new HashMap();
    private boolean endApplication = false;
    private Throwable exception;
    private String message;
    private boolean sendSilently = false;
    private Thread uncaughtExceptionThread;

    public ReportBuilder message(String str) {
        this.message = str;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ReportBuilder uncaughtExceptionThread(Thread thread) {
        this.uncaughtExceptionThread = thread;
        return this;
    }

    public Thread getUncaughtExceptionThread() {
        return this.uncaughtExceptionThread;
    }

    public ReportBuilder exception(Throwable th) {
        this.exception = th;
        return this;
    }

    public Throwable getException() {
        return this.exception;
    }

    public ReportBuilder customData(Map<String, String> map) {
        this.customData.putAll(map);
        return this;
    }

    public ReportBuilder customData(String str, String str2) {
        this.customData.put(str, str2);
        return this;
    }

    public Map<String, String> getCustomData() {
        return new HashMap(this.customData);
    }

    public ReportBuilder sendSilently() {
        this.sendSilently = true;
        return this;
    }

    public boolean isSendSilently() {
        return this.sendSilently;
    }

    public ReportBuilder endApplication() {
        this.endApplication = true;
        return this;
    }

    public boolean isEndApplication() {
        return this.endApplication;
    }

    public void build(ReportExecutor reportExecutor) {
        if (this.message == null && this.exception == null) {
            this.message = "Report requested by developer";
        }
        reportExecutor.execute(this);
    }
}
