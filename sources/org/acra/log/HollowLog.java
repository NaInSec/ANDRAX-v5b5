package org.acra.log;

public final class HollowLog implements ACRALog {
    public int d(String str, String str2) {
        return 0;
    }

    public int d(String str, String str2, Throwable th) {
        return 0;
    }

    public int e(String str, String str2) {
        return 0;
    }

    public int e(String str, String str2, Throwable th) {
        return 0;
    }

    public String getStackTraceString(Throwable th) {
        return null;
    }

    public int i(String str, String str2) {
        return 0;
    }

    public int i(String str, String str2, Throwable th) {
        return 0;
    }

    public int v(String str, String str2) {
        return 0;
    }

    public int v(String str, String str2, Throwable th) {
        return 0;
    }

    public int w(String str, String str2) {
        return 0;
    }

    public int w(String str, String str2, Throwable th) {
        return 0;
    }

    public int w(String str, Throwable th) {
        return 0;
    }
}
