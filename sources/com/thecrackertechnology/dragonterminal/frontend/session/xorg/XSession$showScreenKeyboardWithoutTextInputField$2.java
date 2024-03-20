package com.thecrackertechnology.dragonterminal.frontend.session.xorg;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import com.thecrackertechnology.dragonterminal.NeoGLView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: XSession.kt */
final class XSession$showScreenKeyboardWithoutTextInputField$2 implements Runnable {
    final /* synthetic */ InputMethodManager $inputManager;
    final /* synthetic */ XSession this$0;

    XSession$showScreenKeyboardWithoutTextInputField$2(XSession xSession, InputMethodManager inputMethodManager) {
        this.this$0 = xSession;
        this.$inputManager = inputMethodManager;
    }

    public final void run() {
        if (this.this$0.getMSessionData().getScreenKeyboard() != null) {
            FrameLayout videoLayout = this.this$0.getMSessionData().getVideoLayout();
            if (videoLayout == null) {
                Intrinsics.throwNpe();
            }
            videoLayout.removeView(this.this$0.getMSessionData().getScreenKeyboard());
            this.this$0.getMSessionData().setScreenKeyboard((View) null);
        }
        this.this$0.getWindow().setSoftInputMode(2);
        InputMethodManager inputMethodManager = this.$inputManager;
        NeoGLView gLView = this.this$0.getGLView();
        if (gLView == null) {
            Intrinsics.throwNpe();
        }
        inputMethodManager.hideSoftInputFromWindow(gLView.getWindowToken(), 0);
    }
}
