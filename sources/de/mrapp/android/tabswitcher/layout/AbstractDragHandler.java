package de.mrapp.android.tabswitcher.layout;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import de.mrapp.android.tabswitcher.R;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.layout.AbstractDragHandler.Callback;
import de.mrapp.android.tabswitcher.layout.Arithmetics;
import de.mrapp.android.tabswitcher.model.TabItem;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.gesture.DragHelper;

public abstract class AbstractDragHandler<CallbackType extends Callback> {
    private final Arithmetics arithmetics;
    private CallbackType callback;
    private float dragDistance;
    private final DragHelper dragHelper = new DragHelper(0);
    private DragState dragState;
    private int dragThreshold;
    private float endOvershootThreshold;
    private final float maxFlingVelocity;
    private final float minFlingVelocity;
    private final float minSwipeVelocity;
    private int pointerId;
    private float startOvershootThreshold;
    private final DragHelper swipeDragHelper;
    private final boolean swipeEnabled;
    private TabItem swipedTabItem;
    private final TabSwitcher tabSwitcher;
    private VelocityTracker velocityTracker;

    public interface Callback {
        void onCancelFling();

        void onClick(TabItem tabItem);

        DragState onDrag(DragState dragState, float f);

        void onFling(float f, long j);

        void onRevertEndOvershoot();

        void onRevertStartOvershoot();

        void onSwipe(TabItem tabItem, float f);

        void onSwipeEnded(TabItem tabItem, boolean z, float f);
    }

    public enum DragState {
        NONE,
        DRAG_TO_START,
        DRAG_TO_END,
        OVERSHOOT_START,
        OVERSHOOT_END,
        SWIPE
    }

    /* access modifiers changed from: protected */
    public abstract TabItem getFocusedTab(float f);

    /* access modifiers changed from: protected */
    public boolean isSwipeThresholdReached(TabItem tabItem) {
        return false;
    }

    /* access modifiers changed from: protected */
    public float onOvershootEnd(float f, float f2) {
        return f2;
    }

    /* access modifiers changed from: protected */
    public void onOvershootReverted() {
    }

    /* access modifiers changed from: protected */
    public float onOvershootStart(float f, float f2) {
        return f2;
    }

    /* access modifiers changed from: protected */
    public void onReset() {
    }

    private void resetDragging(int i) {
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
            this.velocityTracker = null;
        }
        this.pointerId = -1;
        this.dragState = DragState.NONE;
        this.swipedTabItem = null;
        this.dragDistance = 0.0f;
        this.startOvershootThreshold = -3.4028235E38f;
        this.endOvershootThreshold = Float.MAX_VALUE;
        this.dragThreshold = i;
        this.dragHelper.reset(i);
        this.swipeDragHelper.reset();
    }

    private void handleDown(MotionEvent motionEvent) {
        this.pointerId = motionEvent.getPointerId(0);
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 == null) {
            this.velocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker2.clear();
        }
        this.velocityTracker.addMovement(motionEvent);
    }

    private void handleClick(MotionEvent motionEvent) {
        TabItem focusedTab = getFocusedTab(this.arithmetics.getPosition(Arithmetics.Axis.DRAGGING_AXIS, motionEvent));
        if (focusedTab != null) {
            notifyOnClick(focusedTab);
        }
    }

    private void handleFling(MotionEvent motionEvent, DragState dragState2) {
        int pointerId2 = motionEvent.getPointerId(0);
        this.velocityTracker.computeCurrentVelocity(1000, this.maxFlingVelocity);
        float abs = Math.abs(this.velocityTracker.getYVelocity(pointerId2));
        if (abs > this.minFlingVelocity) {
            float f = 0.25f * abs;
            if (dragState2 == DragState.DRAG_TO_START) {
                f *= -1.0f;
            }
            notifyOnFling(f, (long) Math.round((Math.abs(f) / abs) * 3000.0f));
        }
    }

    private void handleOvershoot() {
        if (!this.dragHelper.isReset()) {
            this.dragHelper.reset(0);
            this.dragDistance = 0.0f;
        }
    }

    private DragState notifyOnDrag(DragState dragState2, float f) {
        CallbackType callbacktype = this.callback;
        if (callbacktype != null) {
            return callbacktype.onDrag(dragState2, f);
        }
        return null;
    }

    private void notifyOnClick(TabItem tabItem) {
        CallbackType callbacktype = this.callback;
        if (callbacktype != null) {
            callbacktype.onClick(tabItem);
        }
    }

    private void notifyOnFling(float f, long j) {
        CallbackType callbacktype = this.callback;
        if (callbacktype != null) {
            callbacktype.onFling(f, j);
        }
    }

    private void notifyOnCancelFling() {
        CallbackType callbacktype = this.callback;
        if (callbacktype != null) {
            callbacktype.onCancelFling();
        }
    }

    private void notifyOnRevertStartOvershoot() {
        CallbackType callbacktype = this.callback;
        if (callbacktype != null) {
            callbacktype.onRevertStartOvershoot();
        }
    }

    private void notifyOnRevertEndOvershoot() {
        CallbackType callbacktype = this.callback;
        if (callbacktype != null) {
            callbacktype.onRevertEndOvershoot();
        }
    }

    private void notifyOnSwipe(TabItem tabItem, float f) {
        CallbackType callbacktype = this.callback;
        if (callbacktype != null) {
            callbacktype.onSwipe(tabItem, f);
        }
    }

    private void notifyOnSwipeEnded(TabItem tabItem, boolean z, float f) {
        CallbackType callbacktype = this.callback;
        if (callbacktype != null) {
            callbacktype.onSwipeEnded(tabItem, z, f);
        }
    }

    /* access modifiers changed from: protected */
    public TabSwitcher getTabSwitcher() {
        return this.tabSwitcher;
    }

    /* access modifiers changed from: protected */
    public Arithmetics getArithmetics() {
        return this.arithmetics;
    }

    /* access modifiers changed from: protected */
    public CallbackType getCallback() {
        return this.callback;
    }

    public AbstractDragHandler(TabSwitcher tabSwitcher2, Arithmetics arithmetics2, boolean z) {
        Condition.ensureNotNull(tabSwitcher2, "The tab switcher may not be null");
        Condition.ensureNotNull(arithmetics2, "The arithmetics may not be null");
        this.tabSwitcher = tabSwitcher2;
        this.arithmetics = arithmetics2;
        this.swipeEnabled = z;
        Resources resources = tabSwitcher2.getResources();
        this.swipeDragHelper = new DragHelper(resources.getDimensionPixelSize(R.dimen.swipe_threshold));
        this.callback = null;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(tabSwitcher2.getContext());
        this.minFlingVelocity = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.maxFlingVelocity = (float) viewConfiguration.getScaledMaximumFlingVelocity();
        this.minSwipeVelocity = (float) resources.getDimensionPixelSize(R.dimen.min_swipe_velocity);
        resetDragging(resources.getDimensionPixelSize(R.dimen.drag_threshold));
    }

    public final void setCallback(CallbackType callbacktype) {
        this.callback = callbacktype;
    }

    public final boolean handleTouchEvent(MotionEvent motionEvent) {
        Condition.ensureNotNull(motionEvent, "The motion event may not be null");
        if (this.tabSwitcher.isSwitcherShown() && !this.tabSwitcher.isEmpty()) {
            notifyOnCancelFling();
            int action = motionEvent.getAction();
            if (action == 0) {
                handleDown(motionEvent);
                return true;
            } else if (action == 1) {
                if (!this.tabSwitcher.isAnimationRunning() && motionEvent.getPointerId(0) == this.pointerId) {
                    handleRelease(motionEvent, this.dragThreshold);
                }
                return true;
            } else if (action == 2) {
                if (this.tabSwitcher.isAnimationRunning() || motionEvent.getPointerId(0) != this.pointerId) {
                    handleRelease((MotionEvent) null, this.dragThreshold);
                    handleDown(motionEvent);
                } else {
                    if (this.velocityTracker == null) {
                        this.velocityTracker = VelocityTracker.obtain();
                    }
                    this.velocityTracker.addMovement(motionEvent);
                    handleDrag(this.arithmetics.getPosition(Arithmetics.Axis.DRAGGING_AXIS, motionEvent), this.arithmetics.getPosition(Arithmetics.Axis.ORTHOGONAL_AXIS, motionEvent));
                }
                return true;
            }
        }
        return false;
    }

    public final boolean handleDrag(float f, float f2) {
        TabItem focusedTab;
        if (f <= this.startOvershootThreshold) {
            handleOvershoot();
            this.dragState = DragState.OVERSHOOT_START;
            this.startOvershootThreshold = onOvershootStart(f, this.startOvershootThreshold);
            return false;
        } else if (f >= this.endOvershootThreshold) {
            handleOvershoot();
            this.dragState = DragState.OVERSHOOT_END;
            this.endOvershootThreshold = onOvershootEnd(f, this.endOvershootThreshold);
            return false;
        } else {
            onOvershootReverted();
            float dragDistance2 = this.dragHelper.isReset() ? 0.0f : this.dragHelper.getDragDistance();
            this.dragHelper.update(f);
            if (this.swipeEnabled) {
                this.swipeDragHelper.update(f2);
                if (this.dragState == DragState.NONE && this.swipeDragHelper.hasThresholdBeenReached() && (focusedTab = getFocusedTab(this.dragHelper.getDragStartPosition())) != null) {
                    this.dragState = DragState.SWIPE;
                    this.swipedTabItem = focusedTab;
                }
            }
            if (this.dragState != DragState.SWIPE && this.dragHelper.hasThresholdBeenReached()) {
                if (this.dragState == DragState.OVERSHOOT_START) {
                    this.dragState = DragState.DRAG_TO_END;
                } else if (this.dragState == DragState.OVERSHOOT_END) {
                    this.dragState = DragState.DRAG_TO_START;
                } else {
                    float dragDistance3 = this.dragHelper.getDragDistance();
                    if (dragDistance3 == 0.0f) {
                        this.dragState = DragState.NONE;
                    } else {
                        this.dragState = dragDistance2 - dragDistance3 < 0.0f ? DragState.DRAG_TO_END : DragState.DRAG_TO_START;
                    }
                }
            }
            if (this.dragState == DragState.SWIPE) {
                notifyOnSwipe(this.swipedTabItem, this.swipeDragHelper.getDragDistance());
                return false;
            } else if (this.dragState == DragState.NONE) {
                return false;
            } else {
                float dragDistance4 = this.dragHelper.getDragDistance();
                float f3 = dragDistance4 - this.dragDistance;
                this.dragDistance = dragDistance4;
                DragState notifyOnDrag = notifyOnDrag(this.dragState, f3);
                if (notifyOnDrag == DragState.OVERSHOOT_END && (this.dragState == DragState.DRAG_TO_END || this.dragState == DragState.OVERSHOOT_END)) {
                    this.endOvershootThreshold = f;
                    this.dragState = DragState.OVERSHOOT_END;
                    return true;
                } else if (notifyOnDrag != DragState.OVERSHOOT_START) {
                    return true;
                } else {
                    if (this.dragState != DragState.DRAG_TO_START && this.dragState != DragState.OVERSHOOT_START) {
                        return true;
                    }
                    this.startOvershootThreshold = f;
                    this.dragState = DragState.OVERSHOOT_START;
                    return true;
                }
            }
        }
    }

    public final void handleRelease(MotionEvent motionEvent, int i) {
        float f;
        if (this.dragState == DragState.SWIPE) {
            boolean z = false;
            if (motionEvent == null || this.velocityTracker == null) {
                f = 0.0f;
            } else {
                int pointerId2 = motionEvent.getPointerId(0);
                this.velocityTracker.computeCurrentVelocity(1000, this.maxFlingVelocity);
                f = Math.abs(this.velocityTracker.getXVelocity(pointerId2));
            }
            if (this.swipedTabItem.getTab().isCloseable() && (f >= this.minSwipeVelocity || isSwipeThresholdReached(this.swipedTabItem))) {
                z = true;
            }
            TabItem tabItem = this.swipedTabItem;
            if (f < this.minSwipeVelocity) {
                f = 0.0f;
            }
            notifyOnSwipeEnded(tabItem, z, f);
        } else if (this.dragState == DragState.DRAG_TO_START || this.dragState == DragState.DRAG_TO_END) {
            if (!(motionEvent == null || this.velocityTracker == null || !this.dragHelper.hasThresholdBeenReached())) {
                handleFling(motionEvent, this.dragState);
            }
        } else if (this.dragState == DragState.OVERSHOOT_END) {
            notifyOnRevertEndOvershoot();
        } else if (this.dragState == DragState.OVERSHOOT_START) {
            notifyOnRevertStartOvershoot();
        } else if (motionEvent != null) {
            handleClick(motionEvent);
        }
        resetDragging(i);
    }

    public final void reset(int i) {
        resetDragging(i);
        onReset();
    }
}
