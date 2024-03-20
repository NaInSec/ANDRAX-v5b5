package com.thecrackertechnology.dragonterminal.frontend.floating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalViewClient;
import com.thecrackertechnology.dragonterminal.utils.TerminalUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u00020\u00112\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R \u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8\u0006@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\f@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/floating/WindowTermView;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "<set-?>", "Landroid/view/View;", "rootView", "getRootView", "()Landroid/view/View;", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "terminalView", "getTerminalView", "()Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "attachSession", "", "terminalSession", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "setInputMethodEnabled", "enabled", "", "setTerminalViewClient", "terminalViewClient", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalViewClient;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: WindowTermView.kt */
public final class WindowTermView {
    private final Context context;
    private View rootView;
    private TerminalView terminalView;

    public WindowTermView(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.ui_term_dialog, (ViewGroup) null, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(cont…term_dialog, null, false)");
        this.rootView = inflate;
        View findViewById = this.rootView.findViewById(R.id.terminal_view_dialog);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "rootView.findViewById<Te….id.terminal_view_dialog)");
        this.terminalView = (TerminalView) findViewById;
        TerminalUtils.setupTerminalView$default(TerminalUtils.INSTANCE, this.terminalView, (TerminalViewClient) null, 2, (Object) null);
    }

    public final Context getContext() {
        return this.context;
    }

    public final View getRootView() {
        return this.rootView;
    }

    public final TerminalView getTerminalView() {
        return this.terminalView;
    }

    public final void setTerminalViewClient(TerminalViewClient terminalViewClient) {
        this.terminalView.setTerminalViewClient(terminalViewClient);
    }

    public final void attachSession(TerminalSession terminalSession) {
        this.terminalView.attachSession(terminalSession);
    }

    public final void setInputMethodEnabled(boolean z) {
        this.terminalView.setFocusable(z);
        this.terminalView.setFocusableInTouchMode(z);
    }
}
