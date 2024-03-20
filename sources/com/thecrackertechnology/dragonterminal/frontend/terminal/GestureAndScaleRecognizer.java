package com.thecrackertechnology.dragonterminal.frontend.terminal;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/GestureAndScaleRecognizer;", "", "context", "Landroid/content/Context;", "mListener", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/GestureAndScaleRecognizer$Listener;", "(Landroid/content/Context;Lcom/thecrackertechnology/dragonterminal/frontend/terminal/GestureAndScaleRecognizer$Listener;)V", "isAfterLongPress", "", "()Z", "setAfterLongPress", "(Z)V", "isInProgress", "mGestureDetector", "Landroid/view/GestureDetector;", "getMListener", "()Lcom/thecrackertechnology/dragonterminal/frontend/terminal/GestureAndScaleRecognizer$Listener;", "mScaleDetector", "Landroid/view/ScaleGestureDetector;", "onTouchEvent", "", "event", "Landroid/view/MotionEvent;", "Listener", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: GestureAndScaleRecognizer.kt */
public final class GestureAndScaleRecognizer {
    private boolean isAfterLongPress;
    /* access modifiers changed from: private */
    public final GestureDetector mGestureDetector;
    private final Listener mListener;
    private final ScaleGestureDetector mScaleDetector;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH&J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH&J \u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\tH&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u001a"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/GestureAndScaleRecognizer$Listener;", "", "onDoubleTap", "", "e", "Landroid/view/MotionEvent;", "onDoubleTapEvent", "onDown", "x", "", "y", "onFling", "velocityX", "velocityY", "onLongPress", "", "onScale", "focusX", "focusY", "scale", "onScroll", "e2", "dx", "dy", "onSingleTapUp", "onUp", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: GestureAndScaleRecognizer.kt */
    public interface Listener {
        boolean onDoubleTap(MotionEvent motionEvent);

        boolean onDoubleTapEvent(MotionEvent motionEvent);

        boolean onDown(float f, float f2);

        boolean onFling(MotionEvent motionEvent, float f, float f2);

        void onLongPress(MotionEvent motionEvent);

        boolean onScale(float f, float f2, float f3);

        boolean onScroll(MotionEvent motionEvent, float f, float f2);

        boolean onSingleTapUp(MotionEvent motionEvent);

        boolean onUp(MotionEvent motionEvent);
    }

    public GestureAndScaleRecognizer(Context context, Listener listener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(listener, "mListener");
        this.mListener = listener;
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(this) {
            final /* synthetic */ GestureAndScaleRecognizer this$0;

            {
                this.this$0 = r1;
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                Intrinsics.checkParameterIsNotNull(motionEvent, "e1");
                Intrinsics.checkParameterIsNotNull(motionEvent2, "e2");
                return this.this$0.getMListener().onScroll(motionEvent2, f, f2);
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                Intrinsics.checkParameterIsNotNull(motionEvent, "e1");
                Intrinsics.checkParameterIsNotNull(motionEvent2, "e2");
                return this.this$0.getMListener().onFling(motionEvent2, f, f2);
            }

            public boolean onDown(MotionEvent motionEvent) {
                Intrinsics.checkParameterIsNotNull(motionEvent, "e");
                return this.this$0.getMListener().onDown(motionEvent.getX(), motionEvent.getY());
            }

            public void onLongPress(MotionEvent motionEvent) {
                Intrinsics.checkParameterIsNotNull(motionEvent, "e");
                this.this$0.getMListener().onLongPress(motionEvent);
                this.this$0.setAfterLongPress(true);
            }
        }, (Handler) null, true);
        this.mGestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener(this) {
            final /* synthetic */ GestureAndScaleRecognizer this$0;

            {
                this.this$0 = r1;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                Intrinsics.checkParameterIsNotNull(motionEvent, "e");
                this.this$0.getMListener().onUp(motionEvent);
                return this.this$0.getMListener().onSingleTapUp(motionEvent);
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                Intrinsics.checkParameterIsNotNull(motionEvent, "e");
                return this.this$0.getMListener().onDoubleTap(motionEvent);
            }

            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                Intrinsics.checkParameterIsNotNull(motionEvent, "e");
                if (motionEvent.isFromSource(8194)) {
                    return false;
                }
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.this$0.mGestureDetector.setIsLongpressEnabled(false);
                } else if (action == 1) {
                    this.this$0.mGestureDetector.setIsLongpressEnabled(true);
                }
                return this.this$0.getMListener().onDoubleTapEvent(motionEvent);
            }
        });
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener(this) {
            final /* synthetic */ GestureAndScaleRecognizer this$0;

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                Intrinsics.checkParameterIsNotNull(scaleGestureDetector, "detector");
                return true;
            }

            {
                this.this$0 = r1;
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                Intrinsics.checkParameterIsNotNull(scaleGestureDetector, "detector");
                return this.this$0.getMListener().onScale(scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), scaleGestureDetector.getScaleFactor());
            }
        });
        this.mScaleDetector.setQuickScaleEnabled(false);
    }

    public final Listener getMListener() {
        return this.mListener;
    }

    public final boolean isAfterLongPress() {
        return this.isAfterLongPress;
    }

    public final void setAfterLongPress(boolean z) {
        this.isAfterLongPress = z;
    }

    public final void onTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkParameterIsNotNull(motionEvent, NotificationCompat.CATEGORY_EVENT);
        this.mGestureDetector.onTouchEvent(motionEvent);
        this.mScaleDetector.onTouchEvent(motionEvent);
    }

    public final boolean isInProgress() {
        return this.mScaleDetector.isInProgress();
    }
}
