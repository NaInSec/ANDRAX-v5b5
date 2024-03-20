package com.thecrackertechnology.dragonterminal.ui.term;

import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XSession;
import com.thecrackertechnology.dragonterminal.services.NeoTermService;
import com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab;
import com.thecrackertechnology.dragonterminal.ui.term.tab.XSessionTab;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\tH\u0002J\u0018\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u000e¨\u0006\u000f"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/term/SessionRemover;", "", "()V", "removeFinishedSession", "", "termService", "Lcom/thecrackertechnology/dragonterminal/services/NeoTermService;", "finishedSession", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession;", "removeSession", "tab", "Lcom/thecrackertechnology/dragonterminal/ui/term/tab/TermTab;", "removeXSession", "Lcom/thecrackertechnology/dragonterminal/ui/term/tab/XSessionTab;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: SessionRemover.kt */
public final class SessionRemover {
    public static final SessionRemover INSTANCE = new SessionRemover();

    private SessionRemover() {
    }

    public final void removeSession(NeoTermService neoTermService, TermTab termTab) {
        Intrinsics.checkParameterIsNotNull(termTab, "tab");
        TerminalSession termSession = termTab.getTermData().getTermSession();
        if (termSession != null) {
            termSession.finishIfRunning();
        }
        removeFinishedSession(neoTermService, termTab.getTermData().getTermSession());
        termTab.cleanup();
    }

    public final void removeXSession(NeoTermService neoTermService, XSessionTab xSessionTab) {
        removeFinishedSession(neoTermService, xSessionTab != null ? xSessionTab.getSession() : null);
    }

    private final void removeFinishedSession(NeoTermService neoTermService, TerminalSession terminalSession) {
        if (neoTermService != null && terminalSession != null) {
            neoTermService.removeTermSession(terminalSession);
        }
    }

    private final void removeFinishedSession(NeoTermService neoTermService, XSession xSession) {
        if (neoTermService != null && xSession != null) {
            neoTermService.removeXSession(xSession);
        }
    }
}
