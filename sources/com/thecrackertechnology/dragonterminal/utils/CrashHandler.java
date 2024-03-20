package com.thecrackertechnology.dragonterminal.utils;

import android.content.Intent;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.dragonterminal.ui.crash.CrashActivity;
import java.lang.Thread;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0004\u001a\u00020\u0005J\u001c\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0001X.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/CrashHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "()V", "defaultHandler", "init", "", "uncaughtException", "t", "Ljava/lang/Thread;", "e", "", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CrashHandler.kt */
public final class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final CrashHandler INSTANCE = new CrashHandler();
    private static Thread.UncaughtExceptionHandler defaultHandler;

    private CrashHandler() {
    }

    public final void init() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Intrinsics.checkExpressionValueIsNotNull(defaultUncaughtExceptionHandler, "Thread.getDefaultUncaughtExceptionHandler()");
        defaultHandler = defaultUncaughtExceptionHandler;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            th.printStackTrace();
        }
        Intent intent = new Intent(AndraxApp.Companion.get(), CrashActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("exception", th);
        AndraxApp.Companion.get().startActivity(intent);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = defaultHandler;
        if (uncaughtExceptionHandler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("defaultHandler");
        }
        uncaughtExceptionHandler.uncaughtException(thread, th);
    }
}
