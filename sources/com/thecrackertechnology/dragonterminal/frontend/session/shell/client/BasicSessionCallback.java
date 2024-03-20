package com.thecrackertechnology.dragonterminal.frontend.session.shell.client;

import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u001c\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u0012\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u0014\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000bH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0015"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BasicSessionCallback;", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession$SessionChangedCallback;", "terminalView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "(Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;)V", "getTerminalView", "()Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "setTerminalView", "onBell", "", "session", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "onClipboardText", "text", "", "onColorsChanged", "onSessionFinished", "finishedSession", "onTextChanged", "changedSession", "onTitleChanged", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BasicSessionCallback.kt */
public class BasicSessionCallback implements TerminalSession.SessionChangedCallback {
    private TerminalView terminalView;

    public void onBell(TerminalSession terminalSession) {
    }

    public void onClipboardText(TerminalSession terminalSession, String str) {
    }

    public void onSessionFinished(TerminalSession terminalSession) {
    }

    public void onTitleChanged(TerminalSession terminalSession) {
    }

    public BasicSessionCallback(TerminalView terminalView2) {
        Intrinsics.checkParameterIsNotNull(terminalView2, "terminalView");
        this.terminalView = terminalView2;
    }

    public final TerminalView getTerminalView() {
        return this.terminalView;
    }

    public final void setTerminalView(TerminalView terminalView2) {
        Intrinsics.checkParameterIsNotNull(terminalView2, "<set-?>");
        this.terminalView = terminalView2;
    }

    public void onTextChanged(TerminalSession terminalSession) {
        if (terminalSession != null) {
            this.terminalView.onScreenUpdated();
        }
    }

    public void onColorsChanged(TerminalSession terminalSession) {
        if (terminalSession != null) {
            this.terminalView.onScreenUpdated();
        }
    }
}
