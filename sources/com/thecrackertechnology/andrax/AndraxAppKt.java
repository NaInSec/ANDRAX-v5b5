package com.thecrackertechnology.andrax;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\"\u001a\u0010\u0000\u001a\u00020\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"versiondefault", "", "getVersiondefault", "()Ljava/lang/String;", "setVersiondefault", "(Ljava/lang/String;)V", "app_release"}, k = 2, mv = {1, 1, 15})
/* compiled from: AndraxApp.kt */
public final class AndraxAppKt {
    private static String versiondefault = "5005";

    public static final String getVersiondefault() {
        return versiondefault;
    }

    public static final void setVersiondefault(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        versiondefault = str;
    }
}
