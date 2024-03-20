package com.thecrackertechnology.dragonterminal.frontend.session.shell.client;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&¨\u0006\u0013"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermUiPresenter;", "", "requireClose", "", "requireCreateNew", "requireFinishAutoCompletion", "", "requireHideIme", "requireOnSessionFinished", "requirePaste", "requireSwitchTo", "index", "", "requireSwitchToNext", "requireSwitchToPrevious", "requireToggleFullScreen", "requireUpdateTitle", "title", "", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TermUiPresenter.kt */
public interface TermUiPresenter {
    void requireClose();

    void requireCreateNew();

    boolean requireFinishAutoCompletion();

    void requireHideIme();

    void requireOnSessionFinished();

    void requirePaste();

    void requireSwitchTo(int i);

    void requireSwitchToNext();

    void requireSwitchToPrevious();

    void requireToggleFullScreen();

    void requireUpdateTitle(String str);
}
