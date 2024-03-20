package com.thecrackertechnology.dragonterminal.frontend.session.shell.client.event;

import com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/event/TabCloseEvent;", "", "termTab", "Lcom/thecrackertechnology/dragonterminal/ui/term/tab/TermTab;", "(Lcom/thecrackertechnology/dragonterminal/ui/term/tab/TermTab;)V", "getTermTab", "()Lcom/thecrackertechnology/dragonterminal/ui/term/tab/TermTab;", "setTermTab", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TabCloseEvent.kt */
public final class TabCloseEvent {
    private TermTab termTab;

    public TabCloseEvent(TermTab termTab2) {
        Intrinsics.checkParameterIsNotNull(termTab2, "termTab");
        this.termTab = termTab2;
    }

    public final TermTab getTermTab() {
        return this.termTab;
    }

    public final void setTermTab(TermTab termTab2) {
        Intrinsics.checkParameterIsNotNull(termTab2, "<set-?>");
        this.termTab = termTab2;
    }
}
