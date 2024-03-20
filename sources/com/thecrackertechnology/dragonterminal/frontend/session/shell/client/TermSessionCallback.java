package com.thecrackertechnology.dragonterminal.frontend.session.shell.client;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellTermSession;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0019\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u001b\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u0012H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionCallback;", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession$SessionChangedCallback;", "()V", "bellController", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BellController;", "getBellController", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BellController;", "setBellController", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BellController;)V", "termSessionData", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionData;", "getTermSessionData", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionData;", "setTermSessionData", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionData;)V", "onBell", "", "session", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "onClipboardText", "text", "", "onColorsChanged", "onSessionFinished", "finishedSession", "onTextChanged", "changedSession", "onTitleChanged", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TermSessionCallback.kt */
public final class TermSessionCallback implements TerminalSession.SessionChangedCallback {
    private BellController bellController;
    private TermSessionData termSessionData;

    public final TermSessionData getTermSessionData() {
        return this.termSessionData;
    }

    public final void setTermSessionData(TermSessionData termSessionData2) {
        this.termSessionData = termSessionData2;
    }

    public final BellController getBellController() {
        return this.bellController;
    }

    public final void setBellController(BellController bellController2) {
        this.bellController = bellController2;
    }

    public void onTextChanged(TerminalSession terminalSession) {
        TerminalView termView;
        TermSessionData termSessionData2 = this.termSessionData;
        if (termSessionData2 != null && (termView = termSessionData2.getTermView()) != null) {
            termView.onScreenUpdated();
        }
    }

    public void onTitleChanged(TerminalSession terminalSession) {
        TermSessionData termSessionData2;
        TermUiPresenter termUI;
        if ((terminalSession != null ? terminalSession.getTitle() : null) != null && (termSessionData2 = this.termSessionData) != null && (termUI = termSessionData2.getTermUI()) != null) {
            termUI.requireUpdateTitle(terminalSession.getTitle());
        }
    }

    public void onSessionFinished(TerminalSession terminalSession) {
        TermUiPresenter termUI;
        TermSessionData termSessionData2 = this.termSessionData;
        if (termSessionData2 != null && (termUI = termSessionData2.getTermUI()) != null) {
            termUI.requireOnSessionFinished();
        }
    }

    public void onClipboardText(TerminalSession terminalSession, String str) {
        TermSessionData termSessionData2 = this.termSessionData;
        TerminalView termView = termSessionData2 != null ? termSessionData2.getTermView() : null;
        if (termView != null) {
            Object systemService = termView.getContext().getSystemService("clipboard");
            if (systemService != null) {
                ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText("", str));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.content.ClipboardManager");
        }
    }

    public void onBell(TerminalSession terminalSession) {
        TerminalView termView;
        TermSessionData termSessionData2 = this.termSessionData;
        if (termSessionData2 != null && (termView = termSessionData2.getTermView()) != null) {
            if (terminalSession != null) {
                ShellTermSession shellTermSession = (ShellTermSession) terminalSession;
                if (this.bellController == null) {
                    this.bellController = new BellController();
                }
                BellController bellController2 = this.bellController;
                if (bellController2 != null) {
                    Context context = termView.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "termView.context");
                    bellController2.bellOrVibrate(context, shellTermSession);
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellTermSession");
        }
    }

    public void onColorsChanged(TerminalSession terminalSession) {
        TermSessionData termSessionData2 = this.termSessionData;
        TerminalView termView = termSessionData2 != null ? termSessionData2.getTermView() : null;
        if (terminalSession != null && termView != null) {
            termView.onScreenUpdated();
        }
    }
}
