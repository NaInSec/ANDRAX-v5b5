package com.thecrackertechnology.dragonterminal.frontend.session.shell.client;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalViewClient;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\"\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J$\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u001a\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0016\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016J\u0012\u0010\u001c\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001d\u001a\u00020\nH\u0016J\b\u0010\u001e\u001a\u00020\nH\u0016J\b\u0010\u001f\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006 "}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/BasicViewClient;", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalViewClient;", "terminalView", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "(Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;)V", "getTerminalView", "()Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalView;", "copyModeChanged", "", "copyMode", "", "onCodePoint", "codePoint", "", "ctrlDown", "session", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "onKeyDown", "keyCode", "e", "Landroid/view/KeyEvent;", "onKeyUp", "onLongPress", "event", "Landroid/view/MotionEvent;", "onScale", "", "scale", "onSingleTapUp", "readAltKey", "readControlKey", "shouldBackButtonBeMappedToEscape", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BasicViewClient.kt */
public final class BasicViewClient implements TerminalViewClient {
    private final TerminalView terminalView;

    public void copyModeChanged(boolean z) {
    }

    public boolean onCodePoint(int i, boolean z, TerminalSession terminalSession) {
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent, TerminalSession terminalSession) {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onLongPress(MotionEvent motionEvent) {
        return false;
    }

    public boolean readAltKey() {
        return false;
    }

    public boolean readControlKey() {
        return false;
    }

    public boolean shouldBackButtonBeMappedToEscape() {
        return false;
    }

    public BasicViewClient(TerminalView terminalView2) {
        Intrinsics.checkParameterIsNotNull(terminalView2, "terminalView");
        this.terminalView = terminalView2;
    }

    public final TerminalView getTerminalView() {
        return this.terminalView;
    }

    public float onScale(float f) {
        if (f >= 0.9f && f <= 1.1f) {
            return f;
        }
        this.terminalView.setTextSize(NeoPreference.INSTANCE.validateFontSize(this.terminalView.getTextSize() + (((f > 1.0f ? 1 : (f == 1.0f ? 0 : -1)) > 0 ? 1 : -1) * 1)));
        return 1.0f;
    }

    public void onSingleTapUp(MotionEvent motionEvent) {
        if (this.terminalView.isFocusable() && this.terminalView.isFocusableInTouchMode()) {
            Object systemService = this.terminalView.getContext().getSystemService("input_method");
            if (systemService != null) {
                ((InputMethodManager) systemService).showSoftInput(this.terminalView, 1);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
    }
}
