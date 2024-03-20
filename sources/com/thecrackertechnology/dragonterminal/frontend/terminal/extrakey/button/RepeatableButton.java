package com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u000e"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/RepeatableButton;", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/ControlButton;", "buttonText", "", "(Ljava/lang/String;)V", "makeButton", "Landroid/widget/Button;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "RepeatableButtonWidget", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: RepeatableButton.kt */
public class RepeatableButton extends ControlButton {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RepeatableButton(String str) {
        super(str);
        Intrinsics.checkParameterIsNotNull(str, "buttonText");
    }

    public Button makeButton(Context context, AttributeSet attributeSet, int i) {
        return new RepeatableButtonWidget(context, attributeSet, i);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0013\u001a\u00020\fH\u0016R\u000e\u0010\t\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/RepeatableButton$RepeatableButtonWidget;", "Landroid/widget/Button;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "LONG_CLICK_ACTION_INTERVAL", "", "isMotionEventUp", "", "mHandler", "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "setMHandler", "(Landroid/os/Handler;)V", "performClick", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: RepeatableButton.kt */
    private static final class RepeatableButtonWidget extends Button {
        /* access modifiers changed from: private */
        public final long LONG_CLICK_ACTION_INTERVAL = 100;
        private HashMap _$_findViewCache;
        /* access modifiers changed from: private */
        public boolean isMotionEventUp = true;
        private Handler mHandler = new RepeatableButton$RepeatableButtonWidget$mHandler$1(this, Looper.getMainLooper());

        public void _$_clearFindViewByIdCache() {
            HashMap hashMap = this._$_findViewCache;
            if (hashMap != null) {
                hashMap.clear();
            }
        }

        public View _$_findCachedViewById(int i) {
            if (this._$_findViewCache == null) {
                this._$_findViewCache = new HashMap();
            }
            View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
            if (view != null) {
                return view;
            }
            View findViewById = findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }

        public RepeatableButtonWidget(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            setOnLongClickListener(new View.OnLongClickListener(this) {
                final /* synthetic */ RepeatableButtonWidget this$0;

                {
                    this.this$0 = r1;
                }

                public final boolean onLongClick(View view) {
                    this.this$0.isMotionEventUp = false;
                    this.this$0.getMHandler().sendEmptyMessage(0);
                    return false;
                }
            });
            setOnTouchListener(new View.OnTouchListener(this) {
                final /* synthetic */ RepeatableButtonWidget this$0;

                {
                    this.this$0 = r1;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    Intrinsics.checkExpressionValueIsNotNull(motionEvent, NotificationCompat.CATEGORY_EVENT);
                    if (motionEvent.getAction() != 1) {
                        return false;
                    }
                    this.this$0.isMotionEventUp = true;
                    return false;
                }
            });
        }

        public final Handler getMHandler() {
            return this.mHandler;
        }

        public final void setMHandler(Handler handler) {
            Intrinsics.checkParameterIsNotNull(handler, "<set-?>");
            this.mHandler = handler;
        }

        public boolean performClick() {
            return super.performClick();
        }
    }
}
