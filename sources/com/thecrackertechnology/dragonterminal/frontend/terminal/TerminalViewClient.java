package com.thecrackertechnology.dragonterminal.frontend.terminal;

import android.view.KeyEvent;
import android.view.MotionEvent;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;

public interface TerminalViewClient {
    void copyModeChanged(boolean z);

    boolean onCodePoint(int i, boolean z, TerminalSession terminalSession);

    boolean onKeyDown(int i, KeyEvent keyEvent, TerminalSession terminalSession);

    boolean onKeyUp(int i, KeyEvent keyEvent);

    boolean onLongPress(MotionEvent motionEvent);

    float onScale(float f);

    void onSingleTapUp(MotionEvent motionEvent);

    boolean readAltKey();

    boolean readControlKey();

    boolean shouldBackButtonBeMappedToEscape();
}
