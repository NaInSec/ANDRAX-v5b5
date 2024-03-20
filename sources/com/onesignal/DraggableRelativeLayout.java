package com.onesignal;

import android.content.Context;
import android.content.res.Resources;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

class DraggableRelativeLayout extends RelativeLayout {
    private static final int EXTRA_PX_DISMISS = OSViewUtils.dpToPx(64);
    private static final int MARGIN_PX_SIZE = OSViewUtils.dpToPx(28);
    /* access modifiers changed from: private */
    public boolean dismissing;
    /* access modifiers changed from: private */
    public ViewDragHelper mDragHelper;
    /* access modifiers changed from: private */
    public DraggableListener mListener;
    /* access modifiers changed from: private */
    public Params params;

    interface DraggableListener {
        void onDismiss();

        void onDragEnd();

        void onDragStart();
    }

    static class Params {
        static final int DRAGGABLE_DIRECTION_DOWN = 1;
        static final int DRAGGABLE_DIRECTION_UP = 0;
        /* access modifiers changed from: private */
        public int dismissingYPos;
        /* access modifiers changed from: private */
        public int dismissingYVelocity;
        int dragDirection;
        int dragThresholdY;
        int height;
        int maxXPos;
        int maxYPos;
        int messageHeight;
        /* access modifiers changed from: private */
        public int offScreenYPos;
        int posY;

        Params() {
        }
    }

    public DraggableRelativeLayout(Context context) {
        super(context);
        setClipChildren(false);
        createDragHelper();
    }

    /* access modifiers changed from: package-private */
    public void setListener(DraggableListener draggableListener) {
        this.mListener = draggableListener;
    }

    /* access modifiers changed from: package-private */
    public void setParams(Params params2) {
        this.params = params2;
        int unused = params2.offScreenYPos = params2.messageHeight + params2.posY + ((Resources.getSystem().getDisplayMetrics().heightPixels - params2.messageHeight) - params2.posY) + EXTRA_PX_DISMISS;
        int unused2 = params2.dismissingYVelocity = OSViewUtils.dpToPx(PathInterpolatorCompat.MAX_NUM_POINTS);
        if (params2.dragDirection == 0) {
            int unused3 = params2.offScreenYPos = (-params2.messageHeight) - MARGIN_PX_SIZE;
            int unused4 = params2.dismissingYVelocity = -params2.dismissingYVelocity;
            int unused5 = params2.dismissingYPos = params2.offScreenYPos / 3;
            return;
        }
        int unused6 = params2.dismissingYPos = (params2.messageHeight / 3) + (params2.maxYPos * 2);
    }

    private void createDragHelper() {
        this.mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            private int lastYPos;

            public boolean tryCaptureView(View view, int i) {
                return true;
            }

            public int clampViewPositionVertical(View view, int i, int i2) {
                this.lastYPos = i;
                if (DraggableRelativeLayout.this.params.dragDirection == 1) {
                    if (i >= DraggableRelativeLayout.this.params.dragThresholdY && DraggableRelativeLayout.this.mListener != null) {
                        DraggableRelativeLayout.this.mListener.onDragStart();
                    }
                    if (i < DraggableRelativeLayout.this.params.maxYPos) {
                        return DraggableRelativeLayout.this.params.maxYPos;
                    }
                } else {
                    if (i <= DraggableRelativeLayout.this.params.dragThresholdY && DraggableRelativeLayout.this.mListener != null) {
                        DraggableRelativeLayout.this.mListener.onDragStart();
                    }
                    if (i > DraggableRelativeLayout.this.params.maxYPos) {
                        return DraggableRelativeLayout.this.params.maxYPos;
                    }
                }
                return i;
            }

            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return DraggableRelativeLayout.this.params.maxXPos;
            }

            public void onViewReleased(View view, float f, float f2) {
                int i = DraggableRelativeLayout.this.params.maxYPos;
                if (!DraggableRelativeLayout.this.dismissing) {
                    if (DraggableRelativeLayout.this.params.dragDirection == 1) {
                        if (this.lastYPos > DraggableRelativeLayout.this.params.dismissingYPos || f2 > ((float) DraggableRelativeLayout.this.params.dismissingYVelocity)) {
                            i = DraggableRelativeLayout.this.params.offScreenYPos;
                            boolean unused = DraggableRelativeLayout.this.dismissing = true;
                            if (DraggableRelativeLayout.this.mListener != null) {
                                DraggableRelativeLayout.this.mListener.onDismiss();
                            }
                        }
                    } else if (this.lastYPos < DraggableRelativeLayout.this.params.dismissingYPos || f2 < ((float) DraggableRelativeLayout.this.params.dismissingYVelocity)) {
                        i = DraggableRelativeLayout.this.params.offScreenYPos;
                        boolean unused2 = DraggableRelativeLayout.this.dismissing = true;
                        if (DraggableRelativeLayout.this.mListener != null) {
                            DraggableRelativeLayout.this.mListener.onDismiss();
                        }
                    }
                }
                if (DraggableRelativeLayout.this.mDragHelper.settleCapturedViewAt(DraggableRelativeLayout.this.params.maxXPos, i)) {
                    ViewCompat.postInvalidateOnAnimation(DraggableRelativeLayout.this);
                }
            }
        });
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        DraggableListener draggableListener;
        if (this.dismissing) {
            return true;
        }
        int action = motionEvent.getAction();
        if ((action == 0 || action == 5) && (draggableListener = this.mListener) != null) {
            draggableListener.onDragEnd();
        }
        this.mDragHelper.processTouchEvent(motionEvent);
        return false;
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void dismiss() {
        this.dismissing = true;
        this.mDragHelper.smoothSlideViewTo(this, getLeft(), this.params.offScreenYPos);
        ViewCompat.postInvalidateOnAnimation(this);
    }
}
