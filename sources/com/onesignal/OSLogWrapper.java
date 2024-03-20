package com.onesignal;

import com.onesignal.OneSignal;

class OSLogWrapper implements OSLogger {
    OSLogWrapper() {
    }

    public void verbose(String str) {
        OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, str);
    }

    public void debug(String str) {
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, str);
    }

    public void warning(String str) {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, str);
    }

    public void error(String str, Throwable th) {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, str, th);
    }
}
