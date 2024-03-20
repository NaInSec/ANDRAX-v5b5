package com.thecrackertechnology.dragonterminal.frontend.session.shell.client;

import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.completion.listener.OnAutoCompleteListener;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellProfile;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellTermSession;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00103\u001a\u000204J\"\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\u001c2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010-\u001a\u0004\u0018\u00010.J$\u00107\u001a\u0002042\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u00108\u001a\u0004\u0018\u00010\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010-\u001a\u0004\u0018\u00010.X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102¨\u00069"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionData;", "", "()V", "extraKeysView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "getExtraKeysView", "()Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;", "setExtraKeysView", "(Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView;)V", "onAutoCompleteListener", "Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/OnAutoCompleteListener;", "getOnAutoCompleteListener", "()Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/OnAutoCompleteListener;", "setOnAutoCompleteListener", "(Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/OnAutoCompleteListener;)V", "profile", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;", "getProfile", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;", "setProfile", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellProfile;)V", "sessionCallback", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionCallback;", "getSessionCallback", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionCallback;", "setSessionCallback", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionCallback;)V", "termSession", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "getTermSession", "()Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "setTermSession", "(Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;)V", "termUI", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermUiPresenter;", "getTermUI", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermUiPresenter;", "setTermUI", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermUiPresenter;)V", "termView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "getTermView", "()Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "setTermView", "(Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;)V", "viewClient", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermViewClient;", "getViewClient", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermViewClient;", "setViewClient", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermViewClient;)V", "cleanup", "", "initializeSessionWith", "session", "initializeViewWith", "eks", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TermSessionData.kt */
public final class TermSessionData {
    private ExtraKeysView extraKeysView;
    private OnAutoCompleteListener onAutoCompleteListener;
    private ShellProfile profile;
    private TermSessionCallback sessionCallback;
    private TerminalSession termSession;
    private TermUiPresenter termUI;
    private TerminalView termView;
    private TermViewClient viewClient;

    public final TerminalSession getTermSession() {
        return this.termSession;
    }

    public final void setTermSession(TerminalSession terminalSession) {
        this.termSession = terminalSession;
    }

    public final TermSessionCallback getSessionCallback() {
        return this.sessionCallback;
    }

    public final void setSessionCallback(TermSessionCallback termSessionCallback) {
        this.sessionCallback = termSessionCallback;
    }

    public final TermViewClient getViewClient() {
        return this.viewClient;
    }

    public final void setViewClient(TermViewClient termViewClient) {
        this.viewClient = termViewClient;
    }

    public final OnAutoCompleteListener getOnAutoCompleteListener() {
        return this.onAutoCompleteListener;
    }

    public final void setOnAutoCompleteListener(OnAutoCompleteListener onAutoCompleteListener2) {
        this.onAutoCompleteListener = onAutoCompleteListener2;
    }

    public final TermUiPresenter getTermUI() {
        return this.termUI;
    }

    public final void setTermUI(TermUiPresenter termUiPresenter) {
        this.termUI = termUiPresenter;
    }

    public final TerminalView getTermView() {
        return this.termView;
    }

    public final void setTermView(TerminalView terminalView) {
        this.termView = terminalView;
    }

    public final ExtraKeysView getExtraKeysView() {
        return this.extraKeysView;
    }

    public final void setExtraKeysView(ExtraKeysView extraKeysView2) {
        this.extraKeysView = extraKeysView2;
    }

    public final ShellProfile getProfile() {
        return this.profile;
    }

    public final void setProfile(ShellProfile shellProfile) {
        this.profile = shellProfile;
    }

    public final void cleanup() {
        OnAutoCompleteListener onAutoCompleteListener2 = this.onAutoCompleteListener;
        if (onAutoCompleteListener2 != null) {
            onAutoCompleteListener2.onCleanUp();
        }
        this.onAutoCompleteListener = null;
        TermSessionCallback termSessionCallback = this.sessionCallback;
        if (termSessionCallback != null) {
            termSessionCallback.setTermSessionData((TermSessionData) null);
        }
        TermViewClient termViewClient = this.viewClient;
        if (termViewClient != null) {
            termViewClient.setTermSessionData((TermSessionData) null);
        }
        this.termUI = null;
        this.termView = null;
        this.extraKeysView = null;
        this.termSession = null;
        this.profile = null;
    }

    public final void initializeSessionWith(TerminalSession terminalSession, TermSessionCallback termSessionCallback, TermViewClient termViewClient) {
        Intrinsics.checkParameterIsNotNull(terminalSession, "session");
        this.termSession = terminalSession;
        this.sessionCallback = termSessionCallback;
        this.viewClient = termViewClient;
        TermSessionCallback termSessionCallback2 = this.sessionCallback;
        if (termSessionCallback2 != null) {
            termSessionCallback2.setTermSessionData(this);
        }
        TermViewClient termViewClient2 = this.viewClient;
        if (termViewClient2 != null) {
            termViewClient2.setTermSessionData(this);
        }
        if (terminalSession instanceof ShellTermSession) {
            this.profile = ((ShellTermSession) terminalSession).getShellProfile();
        }
    }

    public final void initializeViewWith(TermUiPresenter termUiPresenter, TerminalView terminalView, ExtraKeysView extraKeysView2) {
        this.termUI = termUiPresenter;
        this.termView = terminalView;
        this.extraKeysView = extraKeysView2;
    }
}
