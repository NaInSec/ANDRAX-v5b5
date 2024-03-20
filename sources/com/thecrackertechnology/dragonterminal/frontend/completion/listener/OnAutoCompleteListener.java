package com.thecrackertechnology.dragonterminal.frontend.completion.listener;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&¨\u0006\r"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/completion/listener/OnAutoCompleteListener;", "", "onCleanUp", "", "onCompletionRequired", "newText", "", "onFinishCompletion", "", "onKeyCode", "keyCode", "", "keyMod", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: OnAutoCompleteListener.kt */
public interface OnAutoCompleteListener {
    void onCleanUp();

    void onCompletionRequired(String str);

    boolean onFinishCompletion();

    void onKeyCode(int i, int i2);
}
