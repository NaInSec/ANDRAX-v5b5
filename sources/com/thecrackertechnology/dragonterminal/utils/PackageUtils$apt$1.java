package com.thecrackertechnology.dragonterminal.utils;

import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.floating.TerminalDialog;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"com/thecrackertechnology/dragonterminal/utils/PackageUtils$apt$1", "Lcom/thecrackertechnology/dragonterminal/frontend/floating/TerminalDialog$SessionFinishedCallback;", "onSessionFinished", "", "dialog", "Lcom/thecrackertechnology/dragonterminal/frontend/floating/TerminalDialog;", "finishedSession", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PackageUtils.kt */
public final class PackageUtils$apt$1 implements TerminalDialog.SessionFinishedCallback {
    final /* synthetic */ Function2 $callback;

    PackageUtils$apt$1(Function2 function2) {
        this.$callback = function2;
    }

    public void onSessionFinished(TerminalDialog terminalDialog, TerminalSession terminalSession) {
        Intrinsics.checkParameterIsNotNull(terminalDialog, "dialog");
        this.$callback.invoke(Integer.valueOf(terminalSession != null ? terminalSession.getExitStatus() : 1), terminalDialog);
    }
}
