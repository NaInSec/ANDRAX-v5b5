package com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.RepeatableButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/RepeatableButton$RepeatableButtonWidget$mHandler$1", "Landroid/os/Handler;", "handleMessage", "", "msg", "Landroid/os/Message;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: RepeatableButton.kt */
public final class RepeatableButton$RepeatableButtonWidget$mHandler$1 extends Handler {
    final /* synthetic */ RepeatableButton.RepeatableButtonWidget this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RepeatableButton$RepeatableButtonWidget$mHandler$1(RepeatableButton.RepeatableButtonWidget repeatableButtonWidget, Looper looper) {
        super(looper);
        this.this$0 = repeatableButtonWidget;
    }

    public void handleMessage(Message message) {
        Intrinsics.checkParameterIsNotNull(message, NotificationCompat.CATEGORY_MESSAGE);
        if (!this.this$0.isMotionEventUp && this.this$0.isEnabled()) {
            this.this$0.performClick();
            sendEmptyMessageDelayed(0, this.this$0.LONG_CLICK_ACTION_INTERVAL);
        }
    }
}
