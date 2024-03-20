package org.acra;

public interface ErrorReporter {
    void clearCustomData();

    String getCustomData(String str);

    void handleException(Throwable th);

    void handleException(Throwable th, boolean z);

    void handleSilentException(Throwable th);

    String putCustomData(String str, String str2);

    String removeCustomData(String str);

    void setEnabled(boolean z);
}
