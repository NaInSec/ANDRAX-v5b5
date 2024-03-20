package de.mrapp.android.tabswitcher.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.util.Pair;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import de.mrapp.android.tabswitcher.R;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.TabSwitcherDecorator;
import de.mrapp.android.tabswitcher.layout.AbstractDragHandler;
import de.mrapp.android.tabswitcher.model.Model;
import de.mrapp.android.tabswitcher.model.TabItem;
import de.mrapp.android.tabswitcher.model.TabSwitcherModel;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.ViewUtil;
import de.mrapp.android.util.logging.Logger;

public abstract class AbstractTabSwitcherLayout implements TabSwitcherLayout, ViewTreeObserver.OnGlobalLayoutListener, Model.Listener, AbstractDragHandler.Callback {
    private final Arithmetics arithmetics;
    private Callback callback;
    /* access modifiers changed from: private */
    public AbstractDragHandler<?> dragHandler;
    /* access modifiers changed from: private */
    public final int dragThreshold = getTabSwitcher().getResources().getDimensionPixelSize(R.dimen.drag_threshold);
    /* access modifiers changed from: private */
    public Animation flingAnimation;
    private final Logger logger;
    private final TabSwitcherModel model;
    private int runningAnimations;
    private final TabSwitcher tabSwitcher;

    public interface Callback {
        void onAnimationsEnded();
    }

    public abstract boolean handleTouchEvent(MotionEvent motionEvent);

    /* access modifiers changed from: protected */
    public abstract Pair<Integer, Float> onDetachLayout(boolean z);

    /* access modifiers changed from: protected */
    public abstract AbstractDragHandler<?> onInflateLayout(boolean z);

    public void onRevertEndOvershoot() {
    }

    public void onRevertStartOvershoot() {
    }

    public void onSwipe(TabItem tabItem, float f) {
    }

    public void onSwipeEnded(TabItem tabItem, boolean z, float f) {
    }

    static /* synthetic */ int access$006(AbstractTabSwitcherLayout abstractTabSwitcherLayout) {
        int i = abstractTabSwitcherLayout.runningAnimations - 1;
        abstractTabSwitcherLayout.runningAnimations = i;
        return i;
    }

    static /* synthetic */ int access$008(AbstractTabSwitcherLayout abstractTabSwitcherLayout) {
        int i = abstractTabSwitcherLayout.runningAnimations;
        abstractTabSwitcherLayout.runningAnimations = i + 1;
        return i;
    }

    public static class LayoutListenerWrapper implements ViewTreeObserver.OnGlobalLayoutListener {
        private final ViewTreeObserver.OnGlobalLayoutListener listener;
        private final View view;

        public LayoutListenerWrapper(View view2, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            Condition.ensureNotNull(view2, "The view may not be null");
            this.view = view2;
            this.listener = onGlobalLayoutListener;
        }

        public void onGlobalLayout() {
            ViewUtil.removeOnGlobalLayoutListener(this.view.getViewTreeObserver(), this);
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.listener;
            if (onGlobalLayoutListener != null) {
                onGlobalLayoutListener.onGlobalLayout();
            }
        }
    }

    protected class AnimationListenerWrapper extends AnimatorListenerAdapter {
        private final Animator.AnimatorListener listener;

        private void endAnimation() {
            if (AbstractTabSwitcherLayout.access$006(AbstractTabSwitcherLayout.this) == 0) {
                AbstractTabSwitcherLayout.this.notifyOnAnimationsEnded();
            }
        }

        public AnimationListenerWrapper(Animator.AnimatorListener animatorListener) {
            this.listener = animatorListener;
        }

        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            AbstractTabSwitcherLayout.access$008(AbstractTabSwitcherLayout.this);
            Animator.AnimatorListener animatorListener = this.listener;
            if (animatorListener != null) {
                animatorListener.onAnimationStart(animator);
            }
        }

        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            Animator.AnimatorListener animatorListener = this.listener;
            if (animatorListener != null) {
                animatorListener.onAnimationEnd(animator);
            }
            endAnimation();
        }

        public void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
            Animator.AnimatorListener animatorListener = this.listener;
            if (animatorListener != null) {
                animatorListener.onAnimationCancel(animator);
            }
            endAnimation();
        }
    }

    private class FlingAnimation extends Animation {
        private final float distance;

        FlingAnimation(float f) {
            this.distance = f;
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            if (AbstractTabSwitcherLayout.this.flingAnimation != null) {
                AbstractTabSwitcherLayout.this.dragHandler.handleDrag(this.distance * f, 0.0f);
            }
        }
    }

    private void adaptToolbarVisibility() {
        Toolbar[] toolbars = getToolbars();
        if (toolbars != null) {
            for (Toolbar visibility : toolbars) {
                visibility.setVisibility(getModel().areToolbarsShown() ? 0 : 4);
            }
        }
    }

    private void adaptToolbarTitle() {
        Toolbar[] toolbars = getToolbars();
        if (toolbars != null) {
            toolbars[0].setTitle(getModel().getToolbarTitle());
        }
    }

    private void adaptToolbarNavigationIcon() {
        Toolbar[] toolbars = getToolbars();
        if (toolbars != null) {
            Toolbar toolbar = toolbars[0];
            toolbar.setNavigationIcon(getModel().getToolbarNavigationIcon());
            toolbar.setNavigationOnClickListener(getModel().getToolbarNavigationIconListener());
        }
    }

    private void inflateToolbarMenu() {
        Toolbar[] toolbars = getToolbars();
        int toolbarMenuId = getModel().getToolbarMenuId();
        if (toolbars != null && toolbarMenuId != -1) {
            Toolbar toolbar = toolbars.length > 1 ? toolbars[1] : toolbars[0];
            toolbar.inflateMenu(toolbarMenuId);
            toolbar.setOnMenuItemClickListener(getModel().getToolbarMenuItemListener());
        }
    }

    private Animation.AnimationListener createFlingAnimationListener() {
        return new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                AbstractTabSwitcherLayout.this.dragHandler.handleRelease((MotionEvent) null, AbstractTabSwitcherLayout.this.dragThreshold);
                Animation unused = AbstractTabSwitcherLayout.this.flingAnimation = null;
                AbstractTabSwitcherLayout.this.notifyOnAnimationsEnded();
            }
        };
    }

    /* access modifiers changed from: private */
    public void notifyOnAnimationsEnded() {
        Callback callback2 = this.callback;
        if (callback2 != null) {
            callback2.onAnimationsEnded();
        }
    }

    /* access modifiers changed from: protected */
    public final TabSwitcher getTabSwitcher() {
        return this.tabSwitcher;
    }

    /* access modifiers changed from: protected */
    public final TabSwitcherModel getModel() {
        return this.model;
    }

    /* access modifiers changed from: protected */
    public final Arithmetics getArithmetics() {
        return this.arithmetics;
    }

    /* access modifiers changed from: protected */
    public final int getDragThreshold() {
        return this.dragThreshold;
    }

    /* access modifiers changed from: protected */
    public final Logger getLogger() {
        return this.logger;
    }

    /* access modifiers changed from: protected */
    public final Context getContext() {
        return this.tabSwitcher.getContext();
    }

    public AbstractTabSwitcherLayout(TabSwitcher tabSwitcher2, TabSwitcherModel tabSwitcherModel, Arithmetics arithmetics2) {
        Condition.ensureNotNull(tabSwitcher2, "The tab switcher may not be null");
        Condition.ensureNotNull(tabSwitcherModel, "The model may not be null");
        Condition.ensureNotNull(arithmetics2, "The arithmetics may not be null");
        this.tabSwitcher = tabSwitcher2;
        this.model = tabSwitcherModel;
        this.arithmetics = arithmetics2;
        this.logger = new Logger(tabSwitcherModel.getLogLevel());
        this.callback = null;
        this.runningAnimations = 0;
        this.flingAnimation = null;
        this.dragHandler = null;
    }

    public final void inflateLayout(boolean z) {
        this.dragHandler = onInflateLayout(z);
        if (!z) {
            adaptToolbarVisibility();
            adaptToolbarTitle();
            adaptToolbarNavigationIcon();
            inflateToolbarMenu();
        }
    }

    public final Pair<Integer, Float> detachLayout(boolean z) {
        return onDetachLayout(z);
    }

    public final void setCallback(Callback callback2) {
        this.callback = callback2;
    }

    public final boolean isAnimationRunning() {
        return this.runningAnimations > 0 || this.flingAnimation != null;
    }

    public final Menu getToolbarMenu() {
        Toolbar[] toolbars = getToolbars();
        if (toolbars == null) {
            return null;
        }
        return (toolbars.length > 1 ? toolbars[1] : toolbars[0]).getMenu();
    }

    public void onDecoratorChanged(TabSwitcherDecorator tabSwitcherDecorator) {
        detachLayout(true);
        onGlobalLayout();
    }

    public final void onToolbarVisibilityChanged(boolean z) {
        adaptToolbarVisibility();
    }

    public final void onToolbarTitleChanged(CharSequence charSequence) {
        adaptToolbarTitle();
    }

    public final void onToolbarNavigationIconChanged(Drawable drawable, View.OnClickListener onClickListener) {
        adaptToolbarNavigationIcon();
    }

    public final void onToolbarMenuInflated(int i, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        inflateToolbarMenu();
    }

    public final void onFling(float f, long j) {
        if (this.dragHandler != null) {
            this.flingAnimation = new FlingAnimation(f);
            this.flingAnimation.setFillAfter(true);
            this.flingAnimation.setAnimationListener(createFlingAnimationListener());
            this.flingAnimation.setDuration(j);
            this.flingAnimation.setInterpolator(new DecelerateInterpolator());
            getTabSwitcher().startAnimation(this.flingAnimation);
            Logger logger2 = this.logger;
            Class<?> cls = getClass();
            logger2.logVerbose(cls, "Started fling animation using a distance of " + f + " pixels and a duration of " + j + " milliseconds");
        }
    }

    public final void onCancelFling() {
        Animation animation = this.flingAnimation;
        if (animation != null) {
            animation.cancel();
            this.flingAnimation = null;
            this.dragHandler.handleRelease((MotionEvent) null, this.dragThreshold);
            this.logger.logVerbose(getClass(), "Canceled fling animation");
        }
    }
}
