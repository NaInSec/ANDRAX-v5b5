package com.thecrackertechnology.dragonterminal.frontend.session.xorg;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.widget.EditText;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: XSession.kt */
final class XSession$showScreenKeyboard$1 implements Runnable {
    final /* synthetic */ EditText $screenKeyboard;

    XSession$showScreenKeyboard$1(EditText editText) {
        this.$screenKeyboard = editText;
    }

    public final void run() {
        this.$screenKeyboard.requestFocus();
        this.$screenKeyboard.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0f, 0.0f, 0));
        this.$screenKeyboard.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0f, 0.0f, 0));
        this.$screenKeyboard.postDelayed(new Runnable(this) {
            final /* synthetic */ XSession$showScreenKeyboard$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                this.this$0.$screenKeyboard.requestFocus();
                this.this$0.$screenKeyboard.setSelection(this.this$0.$screenKeyboard.getText().length());
            }
        }, 100);
    }
}
