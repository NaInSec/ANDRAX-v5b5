package com.thecrackertechnology.dragonterminal.frontend.session.xorg;

import android.widget.EditText;
import com.thecrackertechnology.andrax.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: XSession.kt */
final class XSession$setScreenKeyboardHintMessage$1 implements Runnable {
    final /* synthetic */ String $hideMessage;
    final /* synthetic */ XSession this$0;

    XSession$setScreenKeyboardHintMessage$1(XSession xSession, String str) {
        this.this$0 = xSession;
        this.$hideMessage = str;
    }

    public final void run() {
        EditText editText = (EditText) this.this$0.getMSessionData().getScreenKeyboard();
        if (editText != null) {
            String str = this.$hideMessage;
            if (str == null) {
                str = this.this$0.mActivity.getString(R.string.text_edit_click_here);
            }
            editText.setHint(str);
        }
    }
}
