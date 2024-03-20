package de.mrapp.android.tabswitcher.layout.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import de.mrapp.android.tabswitcher.Animation;
import de.mrapp.android.tabswitcher.Layout;
import de.mrapp.android.tabswitcher.PeekAnimation;
import de.mrapp.android.tabswitcher.R;
import de.mrapp.android.tabswitcher.RevealAnimation;
import de.mrapp.android.tabswitcher.SwipeAnimation;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.TabSwitcherDecorator;
import de.mrapp.android.tabswitcher.iterator.AbstractTabItemIterator;
import de.mrapp.android.tabswitcher.iterator.ArrayTabItemIterator;
import de.mrapp.android.tabswitcher.iterator.TabItemIterator;
import de.mrapp.android.tabswitcher.layout.AbstractDragHandler;
import de.mrapp.android.tabswitcher.layout.AbstractTabSwitcherLayout;
import de.mrapp.android.tabswitcher.layout.Arithmetics;
import de.mrapp.android.tabswitcher.layout.phone.PhoneDragHandler;
import de.mrapp.android.tabswitcher.model.Model;
import de.mrapp.android.tabswitcher.model.State;
import de.mrapp.android.tabswitcher.model.TabItem;
import de.mrapp.android.tabswitcher.model.TabSwitcherModel;
import de.mrapp.android.tabswitcher.model.Tag;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.logging.LogLevel;
import de.mrapp.android.util.logging.Logger;
import de.mrapp.android.util.view.AttachedViewRecycler;
import de.mrapp.android.util.view.ViewRecycler;
import java.util.Collections;

public class PhoneTabSwitcherLayout extends AbstractTabSwitcherLayout implements PhoneDragHandler.Callback {
    private static final float MIN_TAB_SPACING_RATIO = 0.375f;
    private static final float SELECTED_TAB_SPACING_RATIO = 1.5f;
    private ViewRecycler<Tab, Void> childViewRecycler;
    private final long clearAnimationDelay;
    private PhoneDragHandler dragHandler;
    private int firstVisibleIndex;
    private final long hideSwitcherAnimationDuration;
    private final int maxCameraDistance;
    private final float maxEndOvershootAngle;
    private final float maxStartOvershootAngle;
    /* access modifiers changed from: private */
    public final long peekAnimationDuration;
    /* access modifiers changed from: private */
    public PhoneRecyclerAdapter recyclerAdapter;
    private final long relocateAnimationDelay;
    private final long relocateAnimationDuration;
    private final long revealAnimationDuration;
    private final long revertOvershootAnimationDuration;
    private final long showSwitcherAnimationDuration;
    /* access modifiers changed from: private */
    public final int stackedTabCount;
    /* access modifiers changed from: private */
    public final int stackedTabSpacing;
    private final long swipeAnimationDuration;
    /* access modifiers changed from: private */
    public final float swipedTabAlpha;
    /* access modifiers changed from: private */
    public final float swipedTabScale;
    private final int tabBorderWidth;
    private ViewGroup tabContainer;
    private final int tabInset;
    /* access modifiers changed from: private */
    public final int tabTitleContainerHeight;
    /* access modifiers changed from: private */
    public int tabViewBottomMargin = -1;
    private Toolbar toolbar;
    private ViewPropertyAnimator toolbarAnimation = null;
    private final long toolbarVisibilityAnimationDelay;
    private final long toolbarVisibilityAnimationDuration;
    /* access modifiers changed from: private */
    public AttachedViewRecycler<TabItem, Integer> viewRecycler;

    public final void onTabBackgroundColorChanged(ColorStateList colorStateList) {
    }

    public final void onTabCloseButtonIconChanged(Drawable drawable) {
    }

    public final void onTabIconChanged(Drawable drawable) {
    }

    public final void onTabTitleColorChanged(ColorStateList colorStateList) {
    }

    private class InitialTabItemIterator extends AbstractTabItemIterator {
        private final TabItem[] array;

        private void calculateAndClipStartPosition(TabItem tabItem, TabItem tabItem2) {
            float calculateStartPosition = calculateStartPosition(tabItem);
            PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
            Pair access$100 = phoneTabSwitcherLayout.clipTabPosition(phoneTabSwitcherLayout.getModel().getCount(), tabItem.getIndex(), calculateStartPosition, tabItem2);
            tabItem.getTag().setPosition(((Float) access$100.first).floatValue());
            tabItem.getTag().setState((State) access$100.second);
        }

        private float calculateStartPosition(TabItem tabItem) {
            int i;
            int i2;
            if (tabItem.getIndex() != 0) {
                return -1.0f;
            }
            if (getCount() > PhoneTabSwitcherLayout.this.stackedTabCount) {
                i = PhoneTabSwitcherLayout.this.stackedTabCount;
                i2 = PhoneTabSwitcherLayout.this.stackedTabSpacing;
            } else {
                i = getCount() - 1;
                i2 = PhoneTabSwitcherLayout.this.stackedTabSpacing;
            }
            return (float) (i * i2);
        }

        private InitialTabItemIterator(TabItem[] tabItemArr, boolean z, int i) {
            Condition.ensureNotNull(tabItemArr, "The array may not be null");
            Integer valueOf = Integer.valueOf(tabItemArr.length);
            Integer valueOf2 = Integer.valueOf(PhoneTabSwitcherLayout.this.getModel().getCount());
            Condition.ensureEqual(valueOf, valueOf2, "The array's length must be " + PhoneTabSwitcherLayout.this.getModel().getCount());
            this.array = tabItemArr;
            initialize(z, i);
        }

        public final int getCount() {
            return this.array.length;
        }

        public final TabItem getItem(int i) {
            TabItem tabItem = this.array[i];
            if (tabItem == null) {
                tabItem = TabItem.create((Model) PhoneTabSwitcherLayout.this.getModel(), (AttachedViewRecycler<TabItem, ?>) PhoneTabSwitcherLayout.this.viewRecycler, i);
                calculateAndClipStartPosition(tabItem, i > 0 ? getItem(i - 1) : null);
                this.array[i] = tabItem;
            }
            return tabItem;
        }
    }

    private class CompoundLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private int count;
        private final ViewTreeObserver.OnGlobalLayoutListener listener;

        CompoundLayoutListener(int i, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            Condition.ensureGreater(i, 0, "The count must be greater than 0");
            this.count = i;
            this.listener = onGlobalLayoutListener;
        }

        public void onGlobalLayout() {
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
            int i = this.count - 1;
            this.count = i;
            if (i == 0 && (onGlobalLayoutListener = this.listener) != null) {
                onGlobalLayoutListener.onGlobalLayout();
            }
        }
    }

    private void adaptLogLevel() {
        this.viewRecycler.setLogLevel(getModel().getLogLevel());
        this.childViewRecycler.setLogLevel(getModel().getLogLevel());
    }

    private void adaptDecorator() {
        this.childViewRecycler.setAdapter(getModel().getChildRecyclerAdapter());
        this.recyclerAdapter.clearCachedPreviews();
    }

    private void adaptToolbarMargin() {
        ((FrameLayout.LayoutParams) this.toolbar.getLayoutParams()).setMargins(getModel().getPaddingLeft(), getModel().getPaddingTop(), getModel().getPaddingRight(), 0);
    }

    private void calculatePositionsWhenDraggingToEnd(float f) {
        this.firstVisibleIndex = -1;
        boolean z = false;
        TabItemIterator create = ((TabItemIterator.Builder) new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).start(Math.max(0, this.firstVisibleIndex))).create();
        while (true) {
            TabItem next = create.next();
            if (next != null && !z) {
                if (getTabSwitcher().getCount() - next.getIndex() > 1) {
                    z = calculatePositionWhenDraggingToEnd(f, next, create.previous());
                    if (this.firstVisibleIndex == -1 && next.getTag().getState() == State.FLOATING) {
                        this.firstVisibleIndex = next.getIndex();
                    }
                } else {
                    Pair<Float, State> clipTabPosition = clipTabPosition(getTabSwitcher().getCount(), next.getIndex(), next.getTag().getPosition(), create.previous());
                    next.getTag().setPosition(((Float) clipTabPosition.first).floatValue());
                    next.getTag().setState((State) clipTabPosition.second);
                }
                inflateOrRemoveView(next);
            } else {
                return;
            }
        }
    }

    private boolean calculatePositionWhenDraggingToEnd(float f, TabItem tabItem, TabItem tabItem2) {
        if (tabItem2 != null && tabItem2.getTag().getState() == State.FLOATING) {
            Pair<Float, State> clipTabPosition = clipTabPosition(getTabSwitcher().getCount(), tabItem.getIndex(), Math.min(calculateNonLinearPosition(tabItem, tabItem2), calculateEndPosition(tabItem.getIndex())), tabItem2);
            tabItem.getTag().setPosition(((Float) clipTabPosition.first).floatValue());
            tabItem.getTag().setState((State) clipTabPosition.second);
            return false;
        } else if ((tabItem.getTag().getState() != State.STACKED_START_ATOP || tabItem.getIndex() != 0) && tabItem.getTag().getState() != State.FLOATING) {
            return tabItem.getTag().getState() == State.STACKED_START_ATOP;
        } else {
            Pair<Float, State> clipTabPosition2 = clipTabPosition(getTabSwitcher().getCount(), tabItem.getIndex(), Math.min(tabItem.getTag().getPosition() + f, calculateEndPosition(tabItem.getIndex())), tabItem2);
            tabItem.getTag().setPosition(((Float) clipTabPosition2.first).floatValue());
            tabItem.getTag().setState((State) clipTabPosition2.second);
            return false;
        }
    }

    private void calculatePositionsWhenDraggingToStart(float f) {
        boolean z = false;
        TabItemIterator create = ((TabItemIterator.Builder) new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).start(Math.max(0, this.firstVisibleIndex))).create();
        while (true) {
            TabItem next = create.next();
            if (next == null || z) {
                int i = this.firstVisibleIndex;
            } else {
                if (getTabSwitcher().getCount() - next.getIndex() > 1) {
                    z = calculatePositionWhenDraggingToStart(f, next, create.previous());
                } else {
                    Pair<Float, State> clipTabPosition = clipTabPosition(getTabSwitcher().getCount(), next.getIndex(), next.getTag().getPosition(), create.previous());
                    next.getTag().setPosition(((Float) clipTabPosition.first).floatValue());
                    next.getTag().setState((State) clipTabPosition.second);
                }
                inflateOrRemoveView(next);
            }
        }
        int i2 = this.firstVisibleIndex;
        if (i2 > 0) {
            int i3 = i2 - 1;
            TabItemIterator create2 = ((TabItemIterator.Builder) ((TabItemIterator.Builder) new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).reverse(true)).start(i3)).create();
            while (true) {
                TabItem next2 = create2.next();
                if (next2 != null) {
                    TabItem previous = create2.previous();
                    float position = previous.getTag().getPosition();
                    if (next2.getIndex() < i3) {
                        Pair<Float, State> clipTabPosition2 = clipTabPosition(getTabSwitcher().getCount(), previous.getIndex(), position, next2);
                        previous.getTag().setPosition(((Float) clipTabPosition2.first).floatValue());
                        previous.getTag().setState((State) clipTabPosition2.second);
                        inflateOrRemoveView(previous);
                        if (previous.getTag().getState() == State.FLOATING) {
                            this.firstVisibleIndex = previous.getIndex();
                        } else {
                            return;
                        }
                    }
                    float calculateMaxTabSpacing = position + calculateMaxTabSpacing(getTabSwitcher().getCount(), previous);
                    next2.getTag().setPosition(calculateMaxTabSpacing);
                    if (!create2.hasNext()) {
                        Pair<Float, State> clipTabPosition3 = clipTabPosition(getTabSwitcher().getCount(), next2.getIndex(), calculateMaxTabSpacing, (TabItem) null);
                        next2.getTag().setPosition(((Float) clipTabPosition3.first).floatValue());
                        next2.getTag().setState((State) clipTabPosition3.second);
                        inflateOrRemoveView(next2);
                        if (next2.getTag().getState() == State.FLOATING) {
                            this.firstVisibleIndex = next2.getIndex();
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    private boolean calculatePositionWhenDraggingToStart(float f, TabItem tabItem, TabItem tabItem2) {
        if (tabItem2 != null && tabItem2.getTag().getState() == State.FLOATING && tabItem2.getTag().getPosition() <= calculateAttachedPosition(getTabSwitcher().getCount())) {
            Pair<Float, State> clipTabPosition = clipTabPosition(getTabSwitcher().getCount(), tabItem.getIndex(), calculateNonLinearPosition(tabItem, tabItem2), tabItem2);
            tabItem.getTag().setPosition(((Float) clipTabPosition.first).floatValue());
            tabItem.getTag().setState((State) clipTabPosition.second);
            return false;
        } else if (tabItem.getTag().getState() == State.FLOATING) {
            Pair<Float, State> clipTabPosition2 = clipTabPosition(getTabSwitcher().getCount(), tabItem.getIndex(), tabItem.getTag().getPosition() + f, tabItem2);
            tabItem.getTag().setPosition(((Float) clipTabPosition2.first).floatValue());
            tabItem.getTag().setState((State) clipTabPosition2.second);
            return false;
        } else if (tabItem.getTag().getState() == State.STACKED_START_ATOP) {
            Pair<Float, State> clipTabPosition3 = clipTabPosition(getTabSwitcher().getCount(), tabItem.getIndex(), tabItem.getTag().getPosition(), tabItem2);
            tabItem.getTag().setPosition(((Float) clipTabPosition3.first).floatValue());
            tabItem.getTag().setState((State) clipTabPosition3.second);
            return true;
        } else if (tabItem.getTag().getState() == State.HIDDEN || tabItem.getTag().getState() == State.STACKED_START) {
            return true;
        } else {
            return false;
        }
    }

    private float calculateNonLinearPosition(TabItem tabItem, TabItem tabItem2) {
        return calculateNonLinearPosition(tabItem2.getTag().getPosition(), calculateMaxTabSpacing(getTabSwitcher().getCount(), tabItem));
    }

    private float calculateNonLinearPosition(float f, float f2) {
        float min = Math.min(1.0f, f / calculateAttachedPosition(getTabSwitcher().getCount()));
        float calculateMinTabSpacing = calculateMinTabSpacing(getTabSwitcher().getCount());
        return (f - calculateMinTabSpacing) - (min * (f2 - calculateMinTabSpacing));
    }

    private float calculateEndPosition(int i) {
        float calculateMaxTabSpacing = calculateMaxTabSpacing(getTabSwitcher().getCount(), (TabItem) null);
        int selectedTabIndex = getTabSwitcher().getSelectedTabIndex();
        if (selectedTabIndex <= i) {
            return ((float) ((getTabSwitcher().getCount() - 1) - i)) * calculateMaxTabSpacing;
        }
        return (((float) ((getTabSwitcher().getCount() - 2) - i)) * calculateMaxTabSpacing) + calculateMaxTabSpacing(getTabSwitcher().getCount(), new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).create().getItem(selectedTabIndex));
    }

    /* access modifiers changed from: private */
    public float calculateSwipePosition() {
        return getArithmetics().getTabContainerSize(Arithmetics.Axis.ORTHOGONAL_AXIS, true);
    }

    private float calculateMaxTabSpacing(int i, TabItem tabItem) {
        float tabContainerSize = getArithmetics().getTabContainerSize(Arithmetics.Axis.DRAGGING_AXIS, false) * (i <= 2 ? 0.66f : i == 3 ? 0.33f : i == 4 ? 0.3f : 0.25f);
        return (i <= 4 || tabItem == null || tabItem.getTab() != getTabSwitcher().getSelectedTab()) ? tabContainerSize : tabContainerSize * SELECTED_TAB_SPACING_RATIO;
    }

    private float calculateMinTabSpacing(int i) {
        return calculateMaxTabSpacing(i, (TabItem) null) * MIN_TAB_SPACING_RATIO;
    }

    /* access modifiers changed from: private */
    public float calculateAttachedPosition(int i) {
        return getArithmetics().getTabContainerSize(Arithmetics.Axis.DRAGGING_AXIS, false) * (i == 3 ? 0.66f : i == 4 ? 0.6f : 0.5f);
    }

    /* access modifiers changed from: private */
    public Pair<Float, State> clipTabPosition(int i, int i2, float f, TabItem tabItem) {
        return clipTabPosition(i, i2, f, tabItem != null ? tabItem.getTag().getState() : null);
    }

    private Pair<Float, State> clipTabPosition(int i, int i2, float f, State state) {
        Pair<Float, State> calculatePositionAndStateWhenStackedAtStart = calculatePositionAndStateWhenStackedAtStart(i, i2, state);
        float floatValue = ((Float) calculatePositionAndStateWhenStackedAtStart.first).floatValue();
        if (f <= floatValue) {
            return Pair.create(Float.valueOf(floatValue), (State) calculatePositionAndStateWhenStackedAtStart.second);
        }
        Pair<Float, State> calculatePositionAndStateWhenStackedAtEnd = calculatePositionAndStateWhenStackedAtEnd(i2);
        float floatValue2 = ((Float) calculatePositionAndStateWhenStackedAtEnd.first).floatValue();
        if (f >= floatValue2) {
            return Pair.create(Float.valueOf(floatValue2), (State) calculatePositionAndStateWhenStackedAtEnd.second);
        }
        return Pair.create(Float.valueOf(f), State.FLOATING);
    }

    private Pair<Float, State> calculatePositionAndStateWhenStackedAtStart(int i, int i2, TabItem tabItem) {
        return calculatePositionAndStateWhenStackedAtStart(i, i2, tabItem != null ? tabItem.getTag().getState() : null);
    }

    private Pair<Float, State> calculatePositionAndStateWhenStackedAtStart(int i, int i2, State state) {
        int i3 = i - i2;
        int i4 = this.stackedTabCount;
        if (i3 <= i4) {
            return Pair.create(Float.valueOf((float) (this.stackedTabSpacing * (i - (i2 + 1)))), (state == null || state == State.FLOATING) ? State.STACKED_START_ATOP : State.STACKED_START);
        }
        return Pair.create(Float.valueOf((float) (this.stackedTabSpacing * i4)), (state == null || state == State.FLOATING) ? State.STACKED_START_ATOP : State.HIDDEN);
    }

    private Pair<Float, State> calculatePositionAndStateWhenStackedAtEnd(int i) {
        float tabContainerSize = getArithmetics().getTabContainerSize(Arithmetics.Axis.DRAGGING_AXIS, false);
        int i2 = this.stackedTabCount;
        if (i < i2) {
            return Pair.create(Float.valueOf((tabContainerSize - ((float) this.tabInset)) - ((float) (this.stackedTabSpacing * (i + 1)))), State.STACKED_END);
        }
        return Pair.create(Float.valueOf((tabContainerSize - ((float) this.tabInset)) - ((float) (this.stackedTabSpacing * i2))), State.HIDDEN);
    }

    private boolean isOvershootingAtStart() {
        if (getTabSwitcher().getCount() > 1 && new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).create().getItem(0).getTag().getState() != State.STACKED_START_ATOP) {
            return false;
        }
        return true;
    }

    private boolean isOvershootingAtEnd(AbstractTabItemIterator abstractTabItemIterator) {
        if (getTabSwitcher().getCount() <= 1) {
            return true;
        }
        if (Math.round(abstractTabItemIterator.getItem(getTabSwitcher().getCount() - 2).getTag().getPosition()) >= Math.round(calculateMaxTabSpacing(getTabSwitcher().getCount(), abstractTabItemIterator.getItem(getTabSwitcher().getCount() - 1)))) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public int calculateBottomMargin(View view) {
        float height = ((float) (view.getHeight() - (this.tabInset * 2))) * getArithmetics().getScale(view, true);
        int i = 0;
        float tabContainerSize = getArithmetics().getTabContainerSize(Arithmetics.Axis.Y_AXIS, false);
        if (getTabSwitcher().getLayout() != Layout.PHONE_LANDSCAPE) {
            i = this.stackedTabSpacing * this.stackedTabCount;
        }
        return Math.round(((height + ((float) this.tabInset)) + ((float) i)) - tabContainerSize);
    }

    private void animateBottomMargin(final View view, int i, long j, long j2) {
        final int i2 = ((FrameLayout.LayoutParams) view.getLayoutParams()).bottomMargin;
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i - i2});
        ofInt.setDuration(j);
        ofInt.addListener(new AbstractTabSwitcherLayout.AnimationListenerWrapper((Animator.AnimatorListener) null));
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setStartDelay(j2);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                layoutParams.bottomMargin = i2 + ((Integer) valueAnimator.getAnimatedValue()).intValue();
                view.setLayoutParams(layoutParams);
            }
        });
        ofInt.start();
    }

    /* access modifiers changed from: private */
    public void animateToolbarVisibility(boolean z, long j) {
        ViewPropertyAnimator viewPropertyAnimator = this.toolbarAnimation;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        float f = z ? 1.0f : 0.0f;
        if (this.toolbar.getAlpha() != f) {
            this.toolbarAnimation = this.toolbar.animate();
            this.toolbarAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            this.toolbarAnimation.setDuration(this.toolbarVisibilityAnimationDuration);
            this.toolbarAnimation.setStartDelay(j);
            this.toolbarAnimation.alpha(f);
            this.toolbarAnimation.start();
        }
    }

    private void animateShowSwitcher() {
        InitialTabItemIterator initialTabItemIterator = new InitialTabItemIterator(calculateInitialTabItems(-1, -1.0f), false, 0);
        while (true) {
            TabItem next = initialTabItemIterator.next();
            if (next == null) {
                animateToolbarVisibility(getModel().areToolbarsShown(), this.toolbarVisibilityAnimationDelay);
                return;
            } else if (next.getTab() == getModel().getSelectedTab() || next.isVisible()) {
                this.viewRecycler.inflate(next, new Integer[0]);
                View view = next.getView();
                if (!ViewCompat.isLaidOut(view)) {
                    view.getViewTreeObserver().addOnGlobalLayoutListener(new AbstractTabSwitcherLayout.LayoutListenerWrapper(view, createShowSwitcherLayoutListener(next)));
                } else {
                    animateShowSwitcher(next, createUpdateViewAnimationListener(next));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public TabItem[] calculateInitialTabItems(int i, float f) {
        int i2;
        float f2;
        Pair<Float, State> pair;
        TabItem tabItem;
        float f3;
        Pair<Float, State> clipTabPosition;
        float f4;
        float f5;
        Pair<Float, State> clipTabPosition2;
        int i3 = i;
        this.dragHandler.reset(getDragThreshold());
        this.firstVisibleIndex = -1;
        TabItem[] tabItemArr = new TabItem[getModel().getCount()];
        if (!getModel().isEmpty()) {
            int selectedTabIndex = getModel().getSelectedTabIndex();
            float calculateAttachedPosition = calculateAttachedPosition(getModel().getCount());
            int i4 = (i3 == -1 || f == -1.0f) ? selectedTabIndex : i3;
            float min = Math.min(calculateEndPosition(i4), (i3 == -1 || f == -1.0f) ? calculateAttachedPosition : f);
            InitialTabItemIterator initialTabItemIterator = new InitialTabItemIterator(tabItemArr, false, i4);
            do {
                TabItem next = initialTabItemIterator.next();
                i2 = 1;
                if (next == null) {
                    break;
                }
                TabItem previous = initialTabItemIterator.previous();
                if (next.getIndex() == getModel().getCount() - 1) {
                    f5 = 0.0f;
                } else if (next.getIndex() == i4) {
                    f5 = min;
                } else {
                    f5 = calculateNonLinearPosition(next, previous);
                }
                clipTabPosition2 = clipTabPosition(getModel().getCount(), next.getIndex(), f5, previous);
                next.getTag().setPosition(((Float) clipTabPosition2.first).floatValue());
                next.getTag().setState((State) clipTabPosition2.second);
                if (!(this.firstVisibleIndex != -1 || clipTabPosition2.second == State.STACKED_END || clipTabPosition2.second == State.HIDDEN)) {
                    this.firstVisibleIndex = next.getIndex();
                }
                if (clipTabPosition2.second == State.STACKED_START) {
                    break;
                }
            } while (clipTabPosition2.second == State.STACKED_START_ATOP);
            boolean z = i4 == getModel().getCount() - 1 || isOvershootingAtEnd(initialTabItemIterator);
            InitialTabItemIterator initialTabItemIterator2 = new InitialTabItemIterator(tabItemArr, true, i4 - 1);
            float calculateMinTabSpacing = calculateMinTabSpacing(getModel().getCount());
            float calculateMaxTabSpacing = calculateMaxTabSpacing(getModel().getCount(), (TabItem) null);
            float calculateMaxTabSpacing2 = calculateMaxTabSpacing(getModel().getCount(), TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, selectedTabIndex));
            TabItem item = initialTabItemIterator2.getItem(i4);
            while (true) {
                TabItem next2 = initialTabItemIterator2.next();
                if (next2 == null || (!z && next2.getIndex() >= i4)) {
                    break;
                }
                float calculateMaxTabSpacing3 = calculateMaxTabSpacing(getModel().getCount(), item);
                TabItem peek = initialTabItemIterator2.peek();
                if (z) {
                    if (i4 > next2.getIndex()) {
                        f4 = (((float) (((getModel().getCount() - i2) - next2.getIndex()) - i2)) * calculateMaxTabSpacing) + calculateMaxTabSpacing2;
                    } else {
                        f4 = ((float) ((getModel().getCount() - i2) - next2.getIndex())) * calculateMaxTabSpacing;
                    }
                    tabItem = item;
                    clipTabPosition = clipTabPosition(getModel().getCount(), next2.getIndex(), f4, peek);
                } else {
                    tabItem = item;
                    float f6 = calculateAttachedPosition - calculateMaxTabSpacing3;
                    if (min >= f6) {
                        if (selectedTabIndex <= next2.getIndex() || selectedTabIndex > i4) {
                            f3 = (((float) (i4 - next2.getIndex())) * calculateMaxTabSpacing) + min;
                        } else {
                            f3 = min + calculateMaxTabSpacing2 + (((float) ((i4 - next2.getIndex()) - 1)) * calculateMaxTabSpacing);
                        }
                        clipTabPosition = clipTabPosition(getModel().getCount(), next2.getIndex(), f3, peek);
                    } else {
                        f2 = calculateMinTabSpacing;
                        pair = clipTabPosition(getModel().getCount(), next2.getIndex(), ((initialTabItemIterator2.previous().getTag().getPosition() + calculateMinTabSpacing) * calculateAttachedPosition) / ((calculateMinTabSpacing + calculateAttachedPosition) - calculateMaxTabSpacing3), peek);
                        if (((Float) pair.first).floatValue() >= f6) {
                            min = ((Float) pair.first).floatValue();
                            item = next2;
                            i4 = next2.getIndex();
                            next2.getTag().setPosition(((Float) pair.first).floatValue());
                            next2.getTag().setState((State) pair.second);
                            int i5 = this.firstVisibleIndex;
                            if ((i5 == -1 || i5 > next2.getIndex()) && pair.second == State.FLOATING) {
                                this.firstVisibleIndex = next2.getIndex();
                            }
                            calculateMinTabSpacing = f2;
                            i2 = 1;
                        }
                        item = tabItem;
                        next2.getTag().setPosition(((Float) pair.first).floatValue());
                        next2.getTag().setState((State) pair.second);
                        int i52 = this.firstVisibleIndex;
                        this.firstVisibleIndex = next2.getIndex();
                        calculateMinTabSpacing = f2;
                        i2 = 1;
                    }
                }
                f2 = calculateMinTabSpacing;
                pair = clipTabPosition;
                item = tabItem;
                next2.getTag().setPosition(((Float) pair.first).floatValue());
                next2.getTag().setState((State) pair.second);
                int i522 = this.firstVisibleIndex;
                this.firstVisibleIndex = next2.getIndex();
                calculateMinTabSpacing = f2;
                i2 = 1;
            }
        }
        this.dragHandler.setCallback(this);
        return tabItemArr;
    }

    private void addAllTabs(int i, Tab[] tabArr, Animation animation) {
        SwipeAnimation swipeAnimation;
        if (tabArr.length <= 0) {
            return;
        }
        if (getModel().isSwitcherShown()) {
            if (animation instanceof SwipeAnimation) {
                swipeAnimation = (SwipeAnimation) animation;
            } else {
                swipeAnimation = new SwipeAnimation.Builder().create();
            }
            TabItem[] tabItemArr = new TabItem[tabArr.length];
            CompoundLayoutListener compoundLayoutListener = new CompoundLayoutListener(tabArr.length, createSwipeLayoutListener(tabItemArr, swipeAnimation));
            for (int i2 = 0; i2 < tabArr.length; i2++) {
                TabItem tabItem = new TabItem(i + i2, tabArr[i2]);
                tabItemArr[i2] = tabItem;
                inflateView(tabItem, compoundLayoutListener, new Integer[0]);
            }
        } else if (!getModel().isSwitcherShown()) {
            this.toolbar.setAlpha(0.0f);
            if (getModel().getSelectedTab() == tabArr[0]) {
                TabItem create = TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, i);
                inflateView(create, createAddSelectedTabLayoutListener(create), new Integer[0]);
            }
        }
    }

    /* access modifiers changed from: private */
    public void animateShowSwitcher(TabItem tabItem, Animator.AnimatorListener animatorListener) {
        animateShowSwitcher(tabItem, this.showSwitcherAnimationDuration, new AccelerateDecelerateInterpolator(), animatorListener);
    }

    private void animateShowSwitcher(TabItem tabItem, long j, Interpolator interpolator, Animator.AnimatorListener animatorListener) {
        View view = tabItem.getView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        view.setX((float) layoutParams.leftMargin);
        view.setY((float) layoutParams.topMargin);
        getArithmetics().setScale(Arithmetics.Axis.DRAGGING_AXIS, view, 1.0f);
        getArithmetics().setScale(Arithmetics.Axis.ORTHOGONAL_AXIS, view, 1.0f);
        getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.NONE));
        getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.NONE));
        float scale = getArithmetics().getScale(view, true);
        int selectedTabIndex = getModel().getSelectedTabIndex();
        if (tabItem.getIndex() < selectedTabIndex) {
            getArithmetics().setPosition(Arithmetics.Axis.DRAGGING_AXIS, view, getArithmetics().getTabContainerSize(Arithmetics.Axis.DRAGGING_AXIS));
        } else if (tabItem.getIndex() > selectedTabIndex) {
            getArithmetics().setPosition(Arithmetics.Axis.DRAGGING_AXIS, view, getTabSwitcher().getLayout() == Layout.PHONE_LANDSCAPE ? 0.0f : (float) layoutParams.topMargin);
        }
        if (this.tabViewBottomMargin == -1) {
            this.tabViewBottomMargin = calculateBottomMargin(view);
        }
        animateBottomMargin(view, this.tabViewBottomMargin, j, 0);
        ViewPropertyAnimator animate = view.animate();
        animate.setDuration(j);
        animate.setInterpolator(interpolator);
        animate.setListener(new AbstractTabSwitcherLayout.AnimationListenerWrapper(animatorListener));
        getArithmetics().animateScale(Arithmetics.Axis.DRAGGING_AXIS, animate, scale);
        getArithmetics().animateScale(Arithmetics.Axis.ORTHOGONAL_AXIS, animate, scale);
        ViewPropertyAnimator viewPropertyAnimator = animate;
        View view2 = view;
        getArithmetics().animatePosition(Arithmetics.Axis.DRAGGING_AXIS, viewPropertyAnimator, view2, tabItem.getTag().getPosition(), true);
        getArithmetics().animatePosition(Arithmetics.Axis.ORTHOGONAL_AXIS, viewPropertyAnimator, view2, 0.0f, true);
        animate.setStartDelay(0);
        animate.start();
    }

    private void animateHideSwitcher() {
        this.dragHandler.setCallback(null);
        TabItemIterator create = new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).create();
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                break;
            } else if (next.isInflated()) {
                animateHideSwitcher(next, next.getIndex() == getModel().getSelectedTabIndex() ? createHideSwitcherAnimationListener() : null);
            } else if (next.getTab() == getModel().getSelectedTab()) {
                inflateAndUpdateView(next, createHideSwitcherLayoutListener(next));
            }
        }
        animateToolbarVisibility(getModel().areToolbarsShown() && getModel().isEmpty(), 0);
    }

    /* access modifiers changed from: private */
    public void animateHideSwitcher(TabItem tabItem, Animator.AnimatorListener animatorListener) {
        animateHideSwitcher(tabItem, this.hideSwitcherAnimationDuration, new AccelerateDecelerateInterpolator(), 0, animatorListener);
    }

    /* access modifiers changed from: private */
    public void animateHideSwitcher(TabItem tabItem, long j, Interpolator interpolator, long j2, Animator.AnimatorListener animatorListener) {
        View view = tabItem.getView();
        animateBottomMargin(view, -(this.tabInset + this.tabBorderWidth), j, j2);
        ViewPropertyAnimator animate = view.animate();
        animate.setDuration(j);
        animate.setInterpolator(interpolator);
        animate.setListener(new AbstractTabSwitcherLayout.AnimationListenerWrapper(animatorListener));
        getArithmetics().animateScale(Arithmetics.Axis.DRAGGING_AXIS, animate, 1.0f);
        getArithmetics().animateScale(Arithmetics.Axis.ORTHOGONAL_AXIS, animate, 1.0f);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        getArithmetics().animatePosition(Arithmetics.Axis.ORTHOGONAL_AXIS, animate, view, getTabSwitcher().getLayout() == Layout.PHONE_LANDSCAPE ? (float) layoutParams.topMargin : 0.0f, false);
        int selectedTabIndex = getModel().getSelectedTabIndex();
        if (tabItem.getIndex() < selectedTabIndex) {
            getArithmetics().animatePosition(Arithmetics.Axis.DRAGGING_AXIS, animate, view, getArithmetics().getTabContainerSize(Arithmetics.Axis.DRAGGING_AXIS), false);
        } else if (tabItem.getIndex() > selectedTabIndex) {
            getArithmetics().animatePosition(Arithmetics.Axis.DRAGGING_AXIS, animate, view, getTabSwitcher().getLayout() == Layout.PHONE_LANDSCAPE ? 0.0f : (float) layoutParams.topMargin, false);
        } else {
            getArithmetics().animatePosition(Arithmetics.Axis.DRAGGING_AXIS, animate, view, getTabSwitcher().getLayout() == Layout.PHONE_LANDSCAPE ? 0.0f : (float) layoutParams.topMargin, false);
        }
        animate.setStartDelay(j2);
        animate.start();
    }

    /* access modifiers changed from: private */
    public void animateSwipe(TabItem tabItem, boolean z, long j, SwipeAnimation swipeAnimation, Animator.AnimatorListener animatorListener) {
        long j2;
        View view = tabItem.getView();
        float scale = getArithmetics().getScale(view, true);
        float calculateSwipePosition = calculateSwipePosition();
        float f = z ? swipeAnimation.getDirection() == SwipeAnimation.SwipeDirection.LEFT ? -1.0f * calculateSwipePosition : calculateSwipePosition : 0.0f;
        float abs = Math.abs(f - getArithmetics().getPosition(Arithmetics.Axis.ORTHOGONAL_AXIS, view));
        if (swipeAnimation.getDuration() != -1) {
            j2 = swipeAnimation.getDuration();
        } else {
            j2 = (long) Math.round(((float) this.swipeAnimationDuration) * (abs / calculateSwipePosition));
        }
        ViewPropertyAnimator animate = view.animate();
        animate.setInterpolator(swipeAnimation.getInterpolator() != null ? swipeAnimation.getInterpolator() : new AccelerateDecelerateInterpolator());
        animate.setListener(new AbstractTabSwitcherLayout.AnimationListenerWrapper(animatorListener));
        animate.setDuration(j2);
        getArithmetics().animatePosition(Arithmetics.Axis.ORTHOGONAL_AXIS, animate, view, f, true);
        getArithmetics().animateScale(Arithmetics.Axis.ORTHOGONAL_AXIS, animate, z ? this.swipedTabScale * scale : scale);
        Arithmetics arithmetics = getArithmetics();
        Arithmetics.Axis axis = Arithmetics.Axis.DRAGGING_AXIS;
        if (z) {
            scale *= this.swipedTabScale;
        }
        arithmetics.animateScale(axis, animate, scale);
        animate.alpha(z ? this.swipedTabAlpha : 1.0f);
        animate.setStartDelay(j);
        animate.start();
    }

    /* access modifiers changed from: private */
    public void animateRemove(TabItem tabItem, SwipeAnimation swipeAnimation) {
        View view = tabItem.getView();
        getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.SWIPE));
        getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.SWIPE));
        animateSwipe(tabItem, true, 0, swipeAnimation, createRemoveAnimationListener(tabItem));
    }

    /* access modifiers changed from: private */
    public void animateRelocate(TabItem tabItem, float f, Tag tag, long j, Animator.AnimatorListener animatorListener) {
        if (tag != null) {
            tabItem.getView().setTag(R.id.tag_properties, tag);
            tabItem.setTag(tag);
        }
        View view = tabItem.getView();
        ViewPropertyAnimator animate = view.animate();
        animate.setListener(new AbstractTabSwitcherLayout.AnimationListenerWrapper(animatorListener));
        animate.setInterpolator(new AccelerateDecelerateInterpolator());
        animate.setDuration(this.relocateAnimationDuration);
        getArithmetics().animatePosition(Arithmetics.Axis.DRAGGING_AXIS, animate, view, f, true);
        animate.setStartDelay(j);
        animate.start();
    }

    private void animateRevertStartOvershoot() {
        if (!animateTilt(new AccelerateInterpolator(), this.maxStartOvershootAngle, createRevertStartOvershootAnimationListener())) {
            animateRevertStartOvershoot(new AccelerateDecelerateInterpolator());
        }
    }

    /* access modifiers changed from: private */
    public void animateRevertStartOvershoot(Interpolator interpolator) {
        TabItem create = TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, 0);
        View view = create.getView();
        getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.NONE));
        getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.NONE));
        float position = getArithmetics().getPosition(Arithmetics.Axis.DRAGGING_AXIS, view);
        float position2 = create.getTag().getPosition();
        final float position3 = getArithmetics().getPosition(Arithmetics.Axis.DRAGGING_AXIS, view);
        float f = position2 - position;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f});
        ofFloat.setDuration((long) Math.round(((float) this.revertOvershootAnimationDuration) * Math.abs(f / ((float) (this.stackedTabCount * this.stackedTabSpacing)))));
        ofFloat.addListener(new AbstractTabSwitcherLayout.AnimationListenerWrapper((Animator.AnimatorListener) null));
        ofFloat.setInterpolator(interpolator);
        ofFloat.setStartDelay(0);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                TabItemIterator create = new TabItemIterator.Builder(PhoneTabSwitcherLayout.this.getTabSwitcher(), PhoneTabSwitcherLayout.this.viewRecycler).create();
                while (true) {
                    TabItem next = create.next();
                    if (next == null) {
                        return;
                    }
                    if (next.getIndex() == 0) {
                        PhoneTabSwitcherLayout.this.getArithmetics().setPosition(Arithmetics.Axis.DRAGGING_AXIS, next.getView(), position3 + ((Float) valueAnimator.getAnimatedValue()).floatValue());
                    } else if (next.isInflated()) {
                        View view = create.first().getView();
                        View view2 = next.getView();
                        view2.setVisibility(PhoneTabSwitcherLayout.this.getArithmetics().getPosition(Arithmetics.Axis.DRAGGING_AXIS, view) <= PhoneTabSwitcherLayout.this.getArithmetics().getPosition(Arithmetics.Axis.DRAGGING_AXIS, view2) ? 4 : 0);
                    }
                }
            }
        });
        ofFloat.start();
    }

    private void animateRevertEndOvershoot() {
        animateTilt(new AccelerateDecelerateInterpolator(), this.maxEndOvershootAngle, (Animator.AnimatorListener) null);
    }

    private boolean animateTilt(Interpolator interpolator, float f, Animator.AnimatorListener animatorListener) {
        TabItemIterator create = ((TabItemIterator.Builder) new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).reverse(true)).create();
        boolean z = false;
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                return z;
            }
            if (next.isInflated()) {
                View view = next.getView();
                if (getArithmetics().getRotation(Arithmetics.Axis.ORTHOGONAL_AXIS, view) != 0.0f) {
                    ViewPropertyAnimator animate = view.animate();
                    animate.setListener(new AbstractTabSwitcherLayout.AnimationListenerWrapper(createRevertOvershootAnimationListener(view, !z ? animatorListener : null)));
                    animate.setDuration((long) Math.round(((float) this.revertOvershootAnimationDuration) * (Math.abs(getArithmetics().getRotation(Arithmetics.Axis.ORTHOGONAL_AXIS, view)) / f)));
                    animate.setInterpolator(interpolator);
                    getArithmetics().animateRotation(Arithmetics.Axis.ORTHOGONAL_AXIS, animate, 0.0f);
                    animate.setStartDelay(0);
                    animate.start();
                    z = true;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void animateReveal(TabItem tabItem, RevealAnimation revealAnimation) {
        this.tabViewBottomMargin = -1;
        this.recyclerAdapter.clearCachedPreviews();
        this.dragHandler.setCallback(null);
        ViewPropertyAnimator animate = tabItem.getView().animate();
        animate.setInterpolator(revealAnimation.getInterpolator() != null ? revealAnimation.getInterpolator() : new AccelerateDecelerateInterpolator());
        animate.setListener(new AbstractTabSwitcherLayout.AnimationListenerWrapper(createHideSwitcherAnimationListener()));
        animate.setStartDelay(0);
        animate.setDuration(revealAnimation.getDuration() != -1 ? revealAnimation.getDuration() : this.revealAnimationDuration);
        getArithmetics().animateScale(Arithmetics.Axis.DRAGGING_AXIS, animate, 1.0f);
        getArithmetics().animateScale(Arithmetics.Axis.ORTHOGONAL_AXIS, animate, 1.0f);
        animate.start();
        animateToolbarVisibility(getModel().areToolbarsShown() && getModel().isEmpty(), 0);
    }

    /* access modifiers changed from: private */
    public void animatePeek(TabItem tabItem, long j, Interpolator interpolator, float f, PeekAnimation peekAnimation) {
        tabItem.getViewHolder().closeButton.setVisibility(8);
        View view = tabItem.getView();
        float x = peekAnimation.getX();
        float y = peekAnimation.getY() + ((float) this.tabTitleContainerHeight);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        view.setAlpha(1.0f);
        getArithmetics().setPivot(Arithmetics.Axis.X_AXIS, view, x);
        getArithmetics().setPivot(Arithmetics.Axis.Y_AXIS, view, y);
        view.setX((float) layoutParams.leftMargin);
        view.setY((float) layoutParams.topMargin);
        getArithmetics().setScale(Arithmetics.Axis.DRAGGING_AXIS, view, 0.0f);
        getArithmetics().setScale(Arithmetics.Axis.ORTHOGONAL_AXIS, view, 0.0f);
        ViewPropertyAnimator animate = view.animate();
        animate.setInterpolator(interpolator);
        animate.setListener(new AbstractTabSwitcherLayout.AnimationListenerWrapper(createPeekAnimationListener(tabItem, peekAnimation)));
        animate.setStartDelay(0);
        animate.setDuration(j);
        getArithmetics().animateScale(Arithmetics.Axis.DRAGGING_AXIS, animate, 1.0f);
        getArithmetics().animateScale(Arithmetics.Axis.ORTHOGONAL_AXIS, animate, 1.0f);
        getArithmetics().animatePosition(Arithmetics.Axis.DRAGGING_AXIS, animate, view, f, true);
        animate.start();
        TabItem create = TabItem.create((Model) getModel(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, getModel().getSelectedTabIndex());
        this.viewRecycler.inflate(create, new Integer[0]);
        create.getTag().setPosition(0.0f);
        create.getViewHolder().closeButton.setVisibility(8);
        animateShowSwitcher(create, j, interpolator, createZoomOutAnimationListener(create, peekAnimation));
    }

    private ViewTreeObserver.OnGlobalLayoutListener createShowSwitcherLayoutListener(final TabItem tabItem) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
                TabItem tabItem = tabItem;
                phoneTabSwitcherLayout.animateShowSwitcher(tabItem, phoneTabSwitcherLayout.createUpdateViewAnimationListener(tabItem));
            }
        };
    }

    private ViewTreeObserver.OnGlobalLayoutListener createHideSwitcherLayoutListener(final TabItem tabItem) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
                TabItem tabItem = tabItem;
                phoneTabSwitcherLayout.animateHideSwitcher(tabItem, tabItem.getIndex() == PhoneTabSwitcherLayout.this.getModel().getSelectedTabIndex() ? PhoneTabSwitcherLayout.this.createHideSwitcherAnimationListener() : null);
            }
        };
    }

    private ViewTreeObserver.OnGlobalLayoutListener createRemoveLayoutListener(final TabItem tabItem, final SwipeAnimation swipeAnimation) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PhoneTabSwitcherLayout.this.animateRemove(tabItem, swipeAnimation);
            }
        };
    }

    private ViewTreeObserver.OnGlobalLayoutListener createRelocateLayoutListener(TabItem tabItem, float f, Tag tag, long j, Animator.AnimatorListener animatorListener) {
        final TabItem tabItem2 = tabItem;
        final float f2 = f;
        final Tag tag2 = tag;
        final long j2 = j;
        final Animator.AnimatorListener animatorListener2 = animatorListener;
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PhoneTabSwitcherLayout.this.animateRelocate(tabItem2, f2, tag2, j2, animatorListener2);
            }
        };
    }

    private ViewTreeObserver.OnGlobalLayoutListener createAddSelectedTabLayoutListener(final TabItem tabItem) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                View view = tabItem.getView();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                view.setAlpha(1.0f);
                PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, PhoneTabSwitcherLayout.this.getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.NONE));
                PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, PhoneTabSwitcherLayout.this.getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.NONE));
                view.setX((float) layoutParams.leftMargin);
                view.setY((float) layoutParams.topMargin);
                PhoneTabSwitcherLayout.this.getArithmetics().setScale(Arithmetics.Axis.DRAGGING_AXIS, view, 1.0f);
                PhoneTabSwitcherLayout.this.getArithmetics().setScale(Arithmetics.Axis.ORTHOGONAL_AXIS, view, 1.0f);
            }
        };
    }

    private ViewTreeObserver.OnGlobalLayoutListener createRevealLayoutListener(final TabItem tabItem, final RevealAnimation revealAnimation) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                View view = tabItem.getView();
                float x = revealAnimation.getX();
                float y = revealAnimation.getY() + ((float) PhoneTabSwitcherLayout.this.tabTitleContainerHeight);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                view.setAlpha(1.0f);
                PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.X_AXIS, view, x);
                PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.Y_AXIS, view, y);
                view.setX((float) layoutParams.leftMargin);
                view.setY((float) layoutParams.topMargin);
                PhoneTabSwitcherLayout.this.getArithmetics().setScale(Arithmetics.Axis.DRAGGING_AXIS, view, 0.0f);
                PhoneTabSwitcherLayout.this.getArithmetics().setScale(Arithmetics.Axis.ORTHOGONAL_AXIS, view, 0.0f);
                PhoneTabSwitcherLayout.this.animateReveal(tabItem, revealAnimation);
            }
        };
    }

    private ViewTreeObserver.OnGlobalLayoutListener createPeekLayoutListener(final TabItem tabItem, final PeekAnimation peekAnimation) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                long j;
                if (peekAnimation.getDuration() != -1) {
                    j = peekAnimation.getDuration();
                } else {
                    j = PhoneTabSwitcherLayout.this.peekAnimationDuration;
                }
                PhoneTabSwitcherLayout.this.animatePeek(tabItem, j / 3, peekAnimation.getInterpolator() != null ? peekAnimation.getInterpolator() : new AccelerateDecelerateInterpolator(), PhoneTabSwitcherLayout.this.getArithmetics().getTabContainerSize(Arithmetics.Axis.DRAGGING_AXIS, false) * 0.66f, peekAnimation);
            }
        };
    }

    private ViewTreeObserver.OnGlobalLayoutListener createSwipeLayoutListener(final TabItem[] tabItemArr, final SwipeAnimation swipeAnimation) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                TabItem[] tabItemArr;
                int i;
                int count = PhoneTabSwitcherLayout.this.getModel().getCount();
                float access$3600 = PhoneTabSwitcherLayout.this.calculateAttachedPosition(count - tabItemArr.length);
                float access$36002 = PhoneTabSwitcherLayout.this.calculateAttachedPosition(count);
                TabItem[] tabItemArr2 = tabItemArr;
                Tag tag = null;
                int i2 = 0;
                if (count - tabItemArr2.length == 0) {
                    tabItemArr = PhoneTabSwitcherLayout.this.calculateInitialTabItems(-1, -1.0f);
                } else {
                    int index = tabItemArr2[0].getIndex();
                    boolean z = index > 0;
                    if (z) {
                        i = index - 1;
                    } else {
                        TabItem[] tabItemArr3 = tabItemArr;
                        i = (tabItemArr3.length + index) - 1 < count + -1 ? tabItemArr3.length + index : -1;
                    }
                    TabItem create = i != -1 ? TabItem.create((Model) PhoneTabSwitcherLayout.this.getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) PhoneTabSwitcherLayout.this.viewRecycler, i) : null;
                    State state = create != null ? create.getTag().getState() : null;
                    if (state == null || state == State.STACKED_START) {
                        tabItemArr = PhoneTabSwitcherLayout.this.relocateWhenAddingStackedTabs(true, tabItemArr);
                    } else if (state == State.STACKED_END) {
                        tabItemArr = PhoneTabSwitcherLayout.this.relocateWhenAddingStackedTabs(false, tabItemArr);
                    } else if (state == State.FLOATING || (state == State.STACKED_START_ATOP && (index > 0 || count <= 2))) {
                        tabItemArr = PhoneTabSwitcherLayout.this.relocateWhenAddingFloatingTabs(tabItemArr, create, z, access$36002, access$36002 != access$3600);
                    } else {
                        tabItemArr = PhoneTabSwitcherLayout.this.relocateWhenAddingHiddenTabs(tabItemArr, create);
                    }
                }
                int length = tabItemArr.length;
                while (i2 < length) {
                    TabItem tabItem = tabItemArr[i2];
                    Tag tag2 = tabItem.getTag();
                    if (tag == null || tag2.getPosition() != tag.getPosition()) {
                        PhoneTabSwitcherLayout.this.createBottomMarginLayoutListener(tabItem).onGlobalLayout();
                        View view = tabItem.getView();
                        view.setTag(R.id.tag_properties, tag2);
                        view.setAlpha(PhoneTabSwitcherLayout.this.swipedTabAlpha);
                        float access$4400 = PhoneTabSwitcherLayout.this.calculateSwipePosition();
                        float scale = PhoneTabSwitcherLayout.this.getArithmetics().getScale(view, true);
                        PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, PhoneTabSwitcherLayout.this.getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.NONE));
                        PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, PhoneTabSwitcherLayout.this.getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.NONE));
                        PhoneTabSwitcherLayout.this.getArithmetics().setPosition(Arithmetics.Axis.DRAGGING_AXIS, view, tag2.getPosition());
                        Arithmetics access$5100 = PhoneTabSwitcherLayout.this.getArithmetics();
                        Arithmetics.Axis axis = Arithmetics.Axis.ORTHOGONAL_AXIS;
                        if (swipeAnimation.getDirection() == SwipeAnimation.SwipeDirection.LEFT) {
                            access$4400 *= -1.0f;
                        }
                        access$5100.setPosition(axis, view, access$4400);
                        PhoneTabSwitcherLayout.this.getArithmetics().setScale(Arithmetics.Axis.DRAGGING_AXIS, view, scale);
                        PhoneTabSwitcherLayout.this.getArithmetics().setScale(Arithmetics.Axis.ORTHOGONAL_AXIS, view, scale);
                        PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, PhoneTabSwitcherLayout.this.getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.SWIPE));
                        PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, PhoneTabSwitcherLayout.this.getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.SWIPE));
                        PhoneTabSwitcherLayout.this.getArithmetics().setScale(Arithmetics.Axis.DRAGGING_AXIS, view, PhoneTabSwitcherLayout.this.swipedTabScale * scale);
                        PhoneTabSwitcherLayout.this.getArithmetics().setScale(Arithmetics.Axis.ORTHOGONAL_AXIS, view, PhoneTabSwitcherLayout.this.swipedTabScale * scale);
                        PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
                        phoneTabSwitcherLayout.animateSwipe(tabItem, false, 0, swipeAnimation, phoneTabSwitcherLayout.createSwipeAnimationListener(tabItem));
                    } else {
                        PhoneTabSwitcherLayout.this.viewRecycler.remove(tabItem);
                    }
                    i2++;
                    tag = tag2;
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public ViewTreeObserver.OnGlobalLayoutListener createBottomMarginLayoutListener(final TabItem tabItem) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                View view = tabItem.getView();
                if (PhoneTabSwitcherLayout.this.tabViewBottomMargin == -1) {
                    PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
                    int unused = phoneTabSwitcherLayout.tabViewBottomMargin = phoneTabSwitcherLayout.calculateBottomMargin(view);
                }
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                layoutParams.bottomMargin = PhoneTabSwitcherLayout.this.tabViewBottomMargin;
                view.setLayoutParams(layoutParams);
            }
        };
    }

    private ViewTreeObserver.OnGlobalLayoutListener createInflateViewLayoutListener(final TabItem tabItem, final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PhoneTabSwitcherLayout.this.adaptViewSize(tabItem);
                PhoneTabSwitcherLayout.this.updateView(tabItem);
                ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = onGlobalLayoutListener;
                if (onGlobalLayoutListener != null) {
                    onGlobalLayoutListener.onGlobalLayout();
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public Animator.AnimatorListener createUpdateViewAnimationListener(final TabItem tabItem) {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PhoneTabSwitcherLayout.this.inflateOrRemoveView(tabItem);
            }
        };
    }

    /* access modifiers changed from: private */
    public Animator.AnimatorListener createHideSwitcherAnimationListener() {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                TabItemIterator create = new TabItemIterator.Builder(PhoneTabSwitcherLayout.this.getTabSwitcher(), PhoneTabSwitcherLayout.this.viewRecycler).create();
                while (true) {
                    TabItem next = create.next();
                    if (next == null) {
                        PhoneTabSwitcherLayout.this.viewRecycler.clearCache();
                        PhoneTabSwitcherLayout.this.recyclerAdapter.clearCachedPreviews();
                        int unused = PhoneTabSwitcherLayout.this.tabViewBottomMargin = -1;
                        return;
                    } else if (next.getTab() == PhoneTabSwitcherLayout.this.getModel().getSelectedTab()) {
                        View view = (View) PhoneTabSwitcherLayout.this.viewRecycler.inflate(next, new Integer[0]).first;
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                        view.setAlpha(1.0f);
                        PhoneTabSwitcherLayout.this.getArithmetics().setScale(Arithmetics.Axis.DRAGGING_AXIS, view, 1.0f);
                        PhoneTabSwitcherLayout.this.getArithmetics().setScale(Arithmetics.Axis.ORTHOGONAL_AXIS, view, 1.0f);
                        view.setX((float) layoutParams.leftMargin);
                        view.setY((float) layoutParams.topMargin);
                    } else {
                        PhoneTabSwitcherLayout.this.viewRecycler.remove(next);
                    }
                }
            }
        };
    }

    private Animator.AnimatorListener createClearAnimationListener() {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PhoneTabSwitcherLayout.this.viewRecycler.removeAll();
                PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
                phoneTabSwitcherLayout.animateToolbarVisibility(phoneTabSwitcherLayout.getModel().areToolbarsShown(), 0);
            }
        };
    }

    /* access modifiers changed from: private */
    public Animator.AnimatorListener createSwipeAnimationListener(final TabItem tabItem) {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PhoneTabSwitcherLayout.this.inflateOrRemoveView(tabItem);
                View view = tabItem.getView();
                PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
                TabItem tabItem = tabItem;
                phoneTabSwitcherLayout.adaptStackOnSwipeAborted(tabItem, tabItem.getIndex() + 1);
                tabItem.getTag().setClosing(false);
                PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, PhoneTabSwitcherLayout.this.getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.NONE));
                PhoneTabSwitcherLayout.this.animateToolbarVisibility(true, 0);
            }
        };
    }

    private Animator.AnimatorListener createRemoveAnimationListener(final TabItem tabItem) {
        return new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (PhoneTabSwitcherLayout.this.getModel().isEmpty()) {
                    PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
                    phoneTabSwitcherLayout.animateToolbarVisibility(phoneTabSwitcherLayout.getModel().areToolbarsShown(), 0);
                }
                PhoneTabSwitcherLayout phoneTabSwitcherLayout2 = PhoneTabSwitcherLayout.this;
                boolean z = true;
                float access$3600 = phoneTabSwitcherLayout2.calculateAttachedPosition(phoneTabSwitcherLayout2.getModel().getCount() + 1);
                PhoneTabSwitcherLayout phoneTabSwitcherLayout3 = PhoneTabSwitcherLayout.this;
                float access$36002 = phoneTabSwitcherLayout3.calculateAttachedPosition(phoneTabSwitcherLayout3.getModel().getCount());
                State state = tabItem.getTag().getState();
                if (state == State.STACKED_END) {
                    PhoneTabSwitcherLayout.this.relocateWhenRemovingStackedTab(tabItem, false);
                } else if (state == State.STACKED_START) {
                    PhoneTabSwitcherLayout.this.relocateWhenRemovingStackedTab(tabItem, true);
                } else if (state == State.FLOATING || state == State.STACKED_START_ATOP) {
                    PhoneTabSwitcherLayout phoneTabSwitcherLayout4 = PhoneTabSwitcherLayout.this;
                    TabItem tabItem = tabItem;
                    if (access$3600 == access$36002) {
                        z = false;
                    }
                    phoneTabSwitcherLayout4.relocateWhenRemovingFloatingTab(tabItem, access$36002, z);
                }
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PhoneTabSwitcherLayout.this.viewRecycler.remove(tabItem);
            }
        };
    }

    private Animator.AnimatorListener createRelocateAnimationListener(final TabItem tabItem) {
        return new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                tabItem.getView().setVisibility(0);
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (tabItem.getTag().getState() == State.STACKED_START_ATOP) {
                    PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
                    TabItem tabItem = tabItem;
                    phoneTabSwitcherLayout.adaptStackOnSwipeAborted(tabItem, tabItem.getIndex() + 1);
                }
                if (tabItem.isVisible()) {
                    PhoneTabSwitcherLayout.this.updateView(tabItem);
                } else {
                    PhoneTabSwitcherLayout.this.viewRecycler.remove(tabItem);
                }
            }
        };
    }

    private Animator.AnimatorListener createRevertOvershootAnimationListener(final View view, final Animator.AnimatorListener animatorListener) {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, PhoneTabSwitcherLayout.this.getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.NONE));
                PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, PhoneTabSwitcherLayout.this.getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.NONE));
                Animator.AnimatorListener animatorListener = animatorListener;
                if (animatorListener != null) {
                    animatorListener.onAnimationEnd(animator);
                }
            }
        };
    }

    private Animator.AnimatorListener createRevertStartOvershootAnimationListener() {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PhoneTabSwitcherLayout.this.animateRevertStartOvershoot(new DecelerateInterpolator());
            }
        };
    }

    private Animator.AnimatorListener createPeekAnimationListener(final TabItem tabItem, final PeekAnimation peekAnimation) {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                long j;
                super.onAnimationEnd(animator);
                if (peekAnimation.getDuration() != -1) {
                    j = peekAnimation.getDuration();
                } else {
                    j = PhoneTabSwitcherLayout.this.peekAnimationDuration;
                }
                long j2 = j / 3;
                TimeInterpolator interpolator = peekAnimation.getInterpolator() != null ? peekAnimation.getInterpolator() : new AccelerateDecelerateInterpolator();
                View view = tabItem.getView();
                PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, (float) PhoneTabSwitcherLayout.this.tabTitleContainerHeight);
                PhoneTabSwitcherLayout.this.getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, PhoneTabSwitcherLayout.this.getArithmetics().getSize(Arithmetics.Axis.ORTHOGONAL_AXIS, view) / 2.0f);
                ViewPropertyAnimator animate = view.animate();
                animate.setDuration(j2);
                animate.setStartDelay(j2);
                animate.setInterpolator(interpolator);
                PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
                animate.setListener(new AbstractTabSwitcherLayout.AnimationListenerWrapper(phoneTabSwitcherLayout.createRevertPeekAnimationListener(tabItem)));
                animate.alpha(0.0f);
                PhoneTabSwitcherLayout.this.getArithmetics().animatePosition(Arithmetics.Axis.DRAGGING_AXIS, animate, view, PhoneTabSwitcherLayout.this.getArithmetics().getPosition(Arithmetics.Axis.DRAGGING_AXIS, view) * PhoneTabSwitcherLayout.SELECTED_TAB_SPACING_RATIO, false);
                PhoneTabSwitcherLayout.this.getArithmetics().animateScale(Arithmetics.Axis.DRAGGING_AXIS, animate, 0.0f);
                PhoneTabSwitcherLayout.this.getArithmetics().animateScale(Arithmetics.Axis.ORTHOGONAL_AXIS, animate, 0.0f);
                animate.start();
            }
        };
    }

    /* access modifiers changed from: private */
    public Animator.AnimatorListener createRevertPeekAnimationListener(final TabItem tabItem) {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PhoneTabSwitcherLayout.this.viewRecycler.remove(tabItem);
            }
        };
    }

    private Animator.AnimatorListener createZoomOutAnimationListener(final TabItem tabItem, final PeekAnimation peekAnimation) {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                long j;
                super.onAnimationEnd(animator);
                PhoneTabSwitcherLayout.this.getModel().removeListener(PhoneTabSwitcherLayout.this);
                PhoneTabSwitcherLayout.this.getModel().hideSwitcher();
                if (peekAnimation.getDuration() != -1) {
                    j = peekAnimation.getDuration();
                } else {
                    j = PhoneTabSwitcherLayout.this.peekAnimationDuration;
                }
                long j2 = j / 3;
                Interpolator interpolator = peekAnimation.getInterpolator() != null ? peekAnimation.getInterpolator() : new AccelerateDecelerateInterpolator();
                PhoneTabSwitcherLayout phoneTabSwitcherLayout = PhoneTabSwitcherLayout.this;
                TabItem tabItem = tabItem;
                phoneTabSwitcherLayout.animateHideSwitcher(tabItem, j2, interpolator, j2, phoneTabSwitcherLayout.createZoomInAnimationListener(tabItem));
            }
        };
    }

    /* access modifiers changed from: private */
    public Animator.AnimatorListener createZoomInAnimationListener(final TabItem tabItem) {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                PhoneTabSwitcherLayout.this.getModel().addListener(PhoneTabSwitcherLayout.this);
                PhoneTabSwitcherLayout.this.viewRecycler.inflate(tabItem, new Integer[0]);
                PhoneTabSwitcherLayout.this.viewRecycler.clearCache();
                PhoneTabSwitcherLayout.this.recyclerAdapter.clearCachedPreviews();
                int unused = PhoneTabSwitcherLayout.this.tabViewBottomMargin = -1;
            }
        };
    }

    private void adaptStackOnSwipe(TabItem tabItem, int i, int i2) {
        if (tabItem.getTag().getState() == State.STACKED_START_ATOP && i < getModel().getCount()) {
            TabItem create = TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, i);
            State state = create.getTag().getState();
            if (state == State.HIDDEN || state == State.STACKED_START) {
                Pair<Float, State> calculatePositionAndStateWhenStackedAtStart = calculatePositionAndStateWhenStackedAtStart(i2, tabItem.getIndex(), (TabItem) null);
                create.getTag().setPosition(((Float) calculatePositionAndStateWhenStackedAtStart.first).floatValue());
                create.getTag().setState((State) calculatePositionAndStateWhenStackedAtStart.second);
                inflateOrRemoveView(create);
            }
        }
    }

    /* access modifiers changed from: private */
    public void adaptStackOnSwipeAborted(TabItem tabItem, int i) {
        if (tabItem.getTag().getState() == State.STACKED_START_ATOP && i < getModel().getCount()) {
            TabItem create = TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, i);
            if (create.getTag().getState() == State.STACKED_START_ATOP) {
                Pair<Float, State> calculatePositionAndStateWhenStackedAtStart = calculatePositionAndStateWhenStackedAtStart(getTabSwitcher().getCount(), create.getIndex(), tabItem);
                create.getTag().setPosition(((Float) calculatePositionAndStateWhenStackedAtStart.first).floatValue());
                create.getTag().setState((State) calculatePositionAndStateWhenStackedAtStart.second);
                inflateOrRemoveView(create);
            }
        }
    }

    /* access modifiers changed from: private */
    public void inflateOrRemoveView(TabItem tabItem) {
        if (tabItem.isInflated() && !tabItem.isVisible()) {
            this.viewRecycler.remove(tabItem);
        } else if (!tabItem.isVisible()) {
        } else {
            if (!tabItem.isInflated()) {
                inflateAndUpdateView(tabItem, (ViewTreeObserver.OnGlobalLayoutListener) null);
            } else {
                updateView(tabItem);
            }
        }
    }

    private void inflateAndUpdateView(TabItem tabItem, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        inflateView(tabItem, createInflateViewLayoutListener(tabItem, onGlobalLayoutListener), Integer.valueOf(this.tabViewBottomMargin));
    }

    private void inflateView(TabItem tabItem, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, Integer... numArr) {
        Pair<View, Boolean> inflate = this.viewRecycler.inflate(tabItem, numArr);
        if (onGlobalLayoutListener == null) {
            return;
        }
        if (((Boolean) inflate.second).booleanValue()) {
            View view = (View) inflate.first;
            view.getViewTreeObserver().addOnGlobalLayoutListener(new AbstractTabSwitcherLayout.LayoutListenerWrapper(view, onGlobalLayoutListener));
            return;
        }
        onGlobalLayoutListener.onGlobalLayout();
    }

    /* access modifiers changed from: private */
    public void adaptViewSize(TabItem tabItem) {
        View view = tabItem.getView();
        getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.NONE));
        getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.NONE));
        float scale = getArithmetics().getScale(view, true);
        getArithmetics().setScale(Arithmetics.Axis.DRAGGING_AXIS, view, scale);
        getArithmetics().setScale(Arithmetics.Axis.ORTHOGONAL_AXIS, view, scale);
    }

    /* access modifiers changed from: private */
    public void updateView(TabItem tabItem) {
        float position = tabItem.getTag().getPosition();
        View view = tabItem.getView();
        view.setAlpha(1.0f);
        view.setVisibility(0);
        getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.NONE));
        getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.NONE));
        getArithmetics().setPosition(Arithmetics.Axis.DRAGGING_AXIS, view, position);
        getArithmetics().setPosition(Arithmetics.Axis.ORTHOGONAL_AXIS, view, 0.0f);
        getArithmetics().setRotation(Arithmetics.Axis.ORTHOGONAL_AXIS, view, 0.0f);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x01e1 A[EDGE_INSN: B:54:0x01e1->B:36:0x01e1 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void relocateWhenRemovingFloatingTab(de.mrapp.android.tabswitcher.model.TabItem r20, float r21, boolean r22) {
        /*
            r19 = this;
            r6 = r19
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r0 = r19.getModel()
            int r0 = r0.getCount()
            r1 = 0
            float r7 = r6.calculateMaxTabSpacing(r0, r1)
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r0 = r19.getModel()
            int r0 = r0.getCount()
            float r8 = r6.calculateMinTabSpacing(r0)
            int r0 = r20.getIndex()
            de.mrapp.android.tabswitcher.model.Tag r1 = r20.getTag()
            float r1 = r1.getPosition()
            if (r22 == 0) goto L_0x0058
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r2 = r19.getModel()
            int r2 = r2.getCount()
            if (r2 <= 0) goto L_0x0058
            int r2 = r20.getIndex()
            if (r2 <= 0) goto L_0x003c
            int r2 = r0 + -1
            goto L_0x003d
        L_0x003c:
            r2 = r0
        L_0x003d:
            de.mrapp.android.tabswitcher.TabSwitcher r3 = r19.getTabSwitcher()
            de.mrapp.android.util.view.AttachedViewRecycler<de.mrapp.android.tabswitcher.model.TabItem, java.lang.Integer> r4 = r6.viewRecycler
            de.mrapp.android.tabswitcher.model.TabItem r2 = de.mrapp.android.tabswitcher.model.TabItem.create((de.mrapp.android.tabswitcher.model.Model) r3, (de.mrapp.android.util.view.AttachedViewRecycler<de.mrapp.android.tabswitcher.model.TabItem, ?>) r4, (int) r2)
            de.mrapp.android.tabswitcher.model.Tag r2 = r2.getTag()
            float r2 = r2.getPosition()
            float r2 = r2 - r1
            float r2 = java.lang.Math.abs(r2)
            r3 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 / r3
            float r1 = r1 + r2
        L_0x0058:
            int r2 = r20.getIndex()
            r9 = 1
            int r2 = r2 - r9
            float r2 = r6.calculateEndPosition(r2)
            float r10 = java.lang.Math.min(r2, r1)
            int r1 = r20.getIndex()
            if (r1 <= 0) goto L_0x01e1
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r1 = r19.getModel()
            int r11 = r1.getSelectedTabIndex()
            de.mrapp.android.tabswitcher.TabSwitcher r1 = r19.getTabSwitcher()
            de.mrapp.android.util.view.AttachedViewRecycler<de.mrapp.android.tabswitcher.model.TabItem, java.lang.Integer> r2 = r6.viewRecycler
            de.mrapp.android.tabswitcher.model.TabItem r1 = de.mrapp.android.tabswitcher.model.TabItem.create((de.mrapp.android.tabswitcher.model.Model) r1, (de.mrapp.android.util.view.AttachedViewRecycler<de.mrapp.android.tabswitcher.model.TabItem, ?>) r2, (int) r11)
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r2 = r19.getModel()
            int r2 = r2.getCount()
            float r12 = r6.calculateMaxTabSpacing(r2, r1)
            de.mrapp.android.tabswitcher.iterator.TabItemIterator$Builder r1 = new de.mrapp.android.tabswitcher.iterator.TabItemIterator$Builder
            de.mrapp.android.tabswitcher.TabSwitcher r2 = r19.getTabSwitcher()
            de.mrapp.android.util.view.AttachedViewRecycler<de.mrapp.android.tabswitcher.model.TabItem, java.lang.Integer> r3 = r6.viewRecycler
            r1.<init>(r2, r3)
            int r2 = r20.getIndex()
            int r2 = r2 - r9
            de.mrapp.android.tabswitcher.iterator.AbstractTabItemIterator$AbstractBuilder r1 = r1.start(r2)
            de.mrapp.android.tabswitcher.iterator.TabItemIterator$Builder r1 = (de.mrapp.android.tabswitcher.iterator.TabItemIterator.Builder) r1
            de.mrapp.android.tabswitcher.iterator.AbstractTabItemIterator$AbstractBuilder r1 = r1.reverse(r9)
            de.mrapp.android.tabswitcher.iterator.TabItemIterator$Builder r1 = (de.mrapp.android.tabswitcher.iterator.TabItemIterator.Builder) r1
            de.mrapp.android.tabswitcher.iterator.TabItemIterator r13 = r1.create()
            r2 = r0
            r1 = r10
            r0 = r20
        L_0x00ae:
            de.mrapp.android.tabswitcher.model.TabItem r3 = r13.next()
            if (r3 == 0) goto L_0x01e1
            de.mrapp.android.tabswitcher.model.TabItem r4 = r13.peek()
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r5 = r19.getModel()
            int r5 = r5.getCount()
            float r5 = r6.calculateMaxTabSpacing(r5, r0)
            int r14 = r3.getIndex()
            int r15 = r20.getIndex()
            int r15 = r15 - r9
            if (r14 != r15) goto L_0x00f1
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r0 = r19.getModel()
            int r0 = r0.getCount()
            int r2 = r3.getIndex()
            android.support.v4.util.Pair r0 = r6.clipTabPosition((int) r0, (int) r2, (float) r1, (de.mrapp.android.tabswitcher.model.TabItem) r4)
            F r1 = r0.first
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            int r2 = r3.getIndex()
            r4 = r0
            r9 = r1
            r15 = r2
        L_0x00ee:
            r14 = r3
            goto L_0x016b
        L_0x00f1:
            float r14 = r21 - r5
            int r15 = (r1 > r14 ? 1 : (r1 == r14 ? 0 : -1))
            if (r15 < 0) goto L_0x0128
            int r5 = r3.getIndex()
            if (r11 <= r5) goto L_0x010d
            if (r11 > r2) goto L_0x010d
            float r5 = r1 + r12
            int r14 = r3.getIndex()
            int r14 = r2 - r14
            int r14 = r14 - r9
            float r14 = (float) r14
            float r14 = r14 * r7
            float r5 = r5 + r14
            goto L_0x0117
        L_0x010d:
            int r5 = r3.getIndex()
            int r5 = r2 - r5
            float r5 = (float) r5
            float r5 = r5 * r7
            float r5 = r5 + r1
        L_0x0117:
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r14 = r19.getModel()
            int r14 = r14.getCount()
            int r15 = r3.getIndex()
            android.support.v4.util.Pair r4 = r6.clipTabPosition((int) r14, (int) r15, (float) r5, (de.mrapp.android.tabswitcher.model.TabItem) r4)
            goto L_0x0168
        L_0x0128:
            de.mrapp.android.tabswitcher.model.TabItem r15 = r13.previous()
            de.mrapp.android.tabswitcher.model.Tag r15 = r15.getTag()
            float r15 = r15.getPosition()
            float r15 = r15 + r8
            float r15 = r15 * r21
            float r16 = r8 + r21
            float r16 = r16 - r5
            float r15 = r15 / r16
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r5 = r19.getModel()
            int r5 = r5.getCount()
            int r9 = r3.getIndex()
            android.support.v4.util.Pair r4 = r6.clipTabPosition((int) r5, (int) r9, (float) r15, (de.mrapp.android.tabswitcher.model.TabItem) r4)
            F r5 = r4.first
            java.lang.Float r5 = (java.lang.Float) r5
            float r5 = r5.floatValue()
            int r5 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r5 < 0) goto L_0x0168
            F r0 = r4.first
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            int r1 = r3.getIndex()
            r9 = r0
            r15 = r1
            goto L_0x00ee
        L_0x0168:
            r14 = r0
            r9 = r1
            r15 = r2
        L_0x016b:
            de.mrapp.android.tabswitcher.model.Tag r0 = r3.getTag()
            de.mrapp.android.tabswitcher.model.Tag r5 = r0.clone()
            F r0 = r4.first
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r5.setPosition(r0)
            S r0 = r4.second
            de.mrapp.android.tabswitcher.model.State r0 = (de.mrapp.android.tabswitcher.model.State) r0
            r5.setState(r0)
            de.mrapp.android.tabswitcher.model.State r0 = r5.getState()
            de.mrapp.android.tabswitcher.model.State r1 = de.mrapp.android.tabswitcher.model.State.HIDDEN
            if (r0 == r1) goto L_0x01e1
            int r0 = r20.getIndex()
            int r1 = r3.getIndex()
            int r0 = r0 - r1
            int r0 = java.lang.Math.abs(r0)
            long r0 = (long) r0
            r17 = r7
            r18 = r8
            long r7 = r6.relocateAnimationDelay
            long r7 = r7 * r0
            boolean r0 = r3.isInflated()
            if (r0 != 0) goto L_0x01cb
            int r0 = r3.getIndex()
            android.support.v4.util.Pair r0 = r6.calculatePositionAndStateWhenStackedAtEnd(r0)
            de.mrapp.android.tabswitcher.model.Tag r1 = r3.getTag()
            F r2 = r0.first
            java.lang.Float r2 = (java.lang.Float) r2
            float r2 = r2.floatValue()
            r1.setPosition(r2)
            de.mrapp.android.tabswitcher.model.Tag r1 = r3.getTag()
            S r0 = r0.second
            de.mrapp.android.tabswitcher.model.State r0 = (de.mrapp.android.tabswitcher.model.State) r0
            r1.setState(r0)
        L_0x01cb:
            float r2 = r5.getPosition()
            r0 = r19
            r1 = r3
            r3 = r5
            r4 = r7
            r0.relocate(r1, r2, r3, r4)
            r1 = r9
            r0 = r14
            r2 = r15
            r7 = r17
            r8 = r18
            r9 = 1
            goto L_0x00ae
        L_0x01e1:
            if (r22 == 0) goto L_0x02d6
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r0 = r19.getModel()
            int r0 = r0.getCount()
            r1 = 2
            if (r0 <= r1) goto L_0x02d6
            de.mrapp.android.tabswitcher.model.Tag r0 = r20.getTag()
            de.mrapp.android.tabswitcher.model.State r0 = r0.getState()
            de.mrapp.android.tabswitcher.model.State r1 = de.mrapp.android.tabswitcher.model.State.STACKED_START_ATOP
            if (r0 == r1) goto L_0x02d6
            de.mrapp.android.tabswitcher.iterator.TabItemIterator$Builder r0 = new de.mrapp.android.tabswitcher.iterator.TabItemIterator$Builder
            de.mrapp.android.tabswitcher.TabSwitcher r1 = r19.getTabSwitcher()
            de.mrapp.android.util.view.AttachedViewRecycler<de.mrapp.android.tabswitcher.model.TabItem, java.lang.Integer> r2 = r6.viewRecycler
            r0.<init>(r1, r2)
            int r1 = r20.getIndex()
            de.mrapp.android.tabswitcher.iterator.AbstractTabItemIterator$AbstractBuilder r0 = r0.start(r1)
            de.mrapp.android.tabswitcher.iterator.TabItemIterator$Builder r0 = (de.mrapp.android.tabswitcher.iterator.TabItemIterator.Builder) r0
            de.mrapp.android.tabswitcher.iterator.TabItemIterator r7 = r0.create()
            de.mrapp.android.tabswitcher.model.Tag r0 = r20.getTag()
        L_0x0217:
            de.mrapp.android.tabswitcher.model.TabItem r1 = r7.next()
            if (r1 == 0) goto L_0x02d6
            int r2 = r1.getIndex()
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r3 = r19.getModel()
            int r3 = r3.getCount()
            r4 = 1
            int r3 = r3 - r4
            if (r2 >= r3) goto L_0x02d6
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r2 = r19.getModel()
            int r2 = r2.getCount()
            float r2 = r6.calculateMaxTabSpacing(r2, r1)
            float r2 = r6.calculateNonLinearPosition((float) r10, (float) r2)
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r3 = r19.getModel()
            int r3 = r3.getCount()
            int r4 = r1.getIndex()
            de.mrapp.android.tabswitcher.model.State r0 = r0.getState()
            android.support.v4.util.Pair r8 = r6.clipTabPosition((int) r3, (int) r4, (float) r2, (de.mrapp.android.tabswitcher.model.State) r0)
            de.mrapp.android.tabswitcher.model.Tag r0 = r1.getTag()
            de.mrapp.android.tabswitcher.model.Tag r9 = r0.clone()
            F r0 = r8.first
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r9.setPosition(r0)
            S r0 = r8.second
            de.mrapp.android.tabswitcher.model.State r0 = (de.mrapp.android.tabswitcher.model.State) r0
            r9.setState(r0)
            int r0 = r20.getIndex()
            int r2 = r1.getIndex()
            int r0 = r0 - r2
            int r0 = java.lang.Math.abs(r0)
            r10 = 1
            int r0 = r0 + r10
            long r2 = (long) r0
            long r4 = r6.relocateAnimationDelay
            long r4 = r4 * r2
            boolean r0 = r1.isInflated()
            if (r0 != 0) goto L_0x02b3
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r0 = r19.getModel()
            int r0 = r0.getCount()
            int r2 = r1.getIndex()
            de.mrapp.android.tabswitcher.model.TabItem r3 = r7.previous()
            android.support.v4.util.Pair r0 = r6.calculatePositionAndStateWhenStackedAtStart((int) r0, (int) r2, (de.mrapp.android.tabswitcher.model.TabItem) r3)
            de.mrapp.android.tabswitcher.model.Tag r2 = r1.getTag()
            F r3 = r0.first
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            r2.setPosition(r3)
            de.mrapp.android.tabswitcher.model.Tag r2 = r1.getTag()
            S r0 = r0.second
            de.mrapp.android.tabswitcher.model.State r0 = (de.mrapp.android.tabswitcher.model.State) r0
            r2.setState(r0)
        L_0x02b3:
            float r2 = r9.getPosition()
            r0 = r19
            r3 = r9
            r0.relocate(r1, r2, r3, r4)
            F r0 = r8.first
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            S r1 = r8.second
            de.mrapp.android.tabswitcher.model.State r2 = de.mrapp.android.tabswitcher.model.State.HIDDEN
            if (r1 == r2) goto L_0x02d6
            S r1 = r8.second
            de.mrapp.android.tabswitcher.model.State r2 = de.mrapp.android.tabswitcher.model.State.STACKED_START
            if (r1 != r2) goto L_0x02d2
            goto L_0x02d6
        L_0x02d2:
            r10 = r0
            r0 = r9
            goto L_0x0217
        L_0x02d6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.tabswitcher.layout.phone.PhoneTabSwitcherLayout.relocateWhenRemovingFloatingTab(de.mrapp.android.tabswitcher.model.TabItem, float, boolean):void");
    }

    /* access modifiers changed from: private */
    public void relocateWhenRemovingStackedTab(TabItem tabItem, boolean z) {
        Pair<Float, State> pair;
        int index = tabItem.getIndex() + (z ? -1 : 0);
        TabItemIterator create = ((TabItemIterator.Builder) ((TabItemIterator.Builder) new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).reverse(z)).start(index)).create();
        float position = tabItem.getTag().getPosition();
        while (true) {
            float f = position;
            TabItem next = create.next();
            if (next == null) {
                return;
            }
            if (next.getTag().getState() == State.HIDDEN || next.getTag().getState() == State.STACKED_START || next.getTag().getState() == State.STACKED_START_ATOP || next.getTag().getState() == State.STACKED_END) {
                position = next.getTag().getPosition();
                if (next.getTag().getState() == State.HIDDEN) {
                    next.getTag().setState(create.previous().getTag().getState());
                    if (next.isVisible()) {
                        if (z) {
                            pair = calculatePositionAndStateWhenStackedAtStart(getTabSwitcher().getCount(), next.getIndex(), next);
                        } else {
                            pair = calculatePositionAndStateWhenStackedAtEnd(next.getIndex());
                        }
                        next.getTag().setPosition(((Float) pair.first).floatValue());
                        next.getTag().setState((State) pair.second);
                        inflateAndUpdateView(next, (ViewTreeObserver.OnGlobalLayoutListener) null);
                        return;
                    }
                    return;
                }
                next.getTag().setPosition(f);
                animateRelocate(next, f, (Tag) null, ((long) (Math.abs(index - next.getIndex()) + 1)) * this.relocateAnimationDelay, createRelocateAnimationListener(next));
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public TabItem[] relocateWhenAddingFloatingTabs(TabItem[] tabItemArr, TabItem tabItem, boolean z, float f, boolean z2) {
        int i;
        TabItemIterator.Builder builder;
        int i2;
        float f2;
        int i3;
        TabItem tabItem2;
        Pair<Float, State> pair;
        float f3;
        TabItem[] tabItemArr2 = tabItemArr;
        int count = getTabSwitcher().getCount();
        TabItem tabItem3 = tabItemArr2[0];
        boolean z3 = true;
        TabItem tabItem4 = tabItemArr2[tabItemArr2.length - 1];
        float position = tabItem.getTag().getPosition();
        if (z && z2 && tabItem4.getIndex() < count - 1) {
            position -= Math.abs(position - TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, tabItem4.getIndex() + 1).getTag().getPosition()) / 2.0f;
        }
        int selectedTabIndex = getModel().getSelectedTabIndex();
        TabItem create = TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, selectedTabIndex);
        float calculateMaxTabSpacing = calculateMaxTabSpacing(count, (TabItem) null);
        float calculateMaxTabSpacing2 = calculateMaxTabSpacing(count, create);
        float calculateMinTabSpacing = calculateMinTabSpacing(count);
        int index = tabItem.getIndex();
        TabItemIterator.Builder builder2 = new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler);
        int length = tabItemArr2.length;
        TabItem tabItem5 = tabItem;
        float f4 = position;
        int i4 = 0;
        while (i4 < length) {
            TabItem tabItem6 = tabItemArr2[i4];
            AbstractTabItemIterator create2 = builder2.start(tabItem6.getIndex()).reverse(z3).create();
            int i5 = index;
            TabItem tabItem7 = tabItem5;
            float f5 = f4;
            float f6 = position;
            while (true) {
                TabItem next = create2.next();
                if (next == null) {
                    i = i4;
                    builder = builder2;
                    i2 = length;
                    break;
                }
                TabItem peek = create2.peek();
                float calculateMaxTabSpacing3 = calculateMaxTabSpacing(count, tabItem5);
                if (!z || next.getIndex() != tabItem6.getIndex()) {
                    float f7 = f - calculateMaxTabSpacing3;
                    if (position >= f7) {
                        if (selectedTabIndex <= next.getIndex() || selectedTabIndex > index) {
                            f3 = (((float) (index - next.getIndex())) * calculateMaxTabSpacing) + position;
                        } else {
                            f3 = position + calculateMaxTabSpacing2 + (((float) ((index - next.getIndex()) - 1)) * calculateMaxTabSpacing);
                        }
                        pair = clipTabPosition(count, next.getIndex(), f3, peek);
                        f2 = position;
                        i3 = index;
                        tabItem2 = tabItem5;
                    } else {
                        float f8 = position;
                        Pair<Float, State> clipTabPosition = clipTabPosition(count, next.getIndex(), ((create2.previous().getTag().getPosition() + calculateMinTabSpacing) * f) / ((calculateMinTabSpacing + f) - calculateMaxTabSpacing3), peek);
                        if (((Float) clipTabPosition.first).floatValue() >= f7) {
                            pair = clipTabPosition;
                            f2 = ((Float) clipTabPosition.first).floatValue();
                            i3 = next.getIndex();
                            tabItem2 = next;
                        } else {
                            pair = clipTabPosition;
                            tabItem2 = tabItem5;
                            f2 = f8;
                            i3 = index;
                        }
                    }
                } else {
                    State state = peek != null ? peek.getTag().getState() : null;
                    int index2 = next.getIndex();
                    if (state == State.STACKED_START_ATOP) {
                        state = State.FLOATING;
                    }
                    Pair<Float, State> clipTabPosition2 = clipTabPosition(count, index2, position, state);
                    f5 = ((Float) clipTabPosition2.first).floatValue();
                    pair = clipTabPosition2;
                    i5 = next.getIndex();
                    i3 = i5;
                    f6 = f5;
                    f2 = f6;
                    tabItem2 = next;
                    tabItem7 = tabItem2;
                }
                if (next.getIndex() < tabItem3.getIndex() || next.getIndex() > tabItem4.getIndex()) {
                    Tag clone = next.getTag().clone();
                    clone.setPosition(((Float) pair.first).floatValue());
                    clone.setState((State) pair.second);
                    if (!next.isInflated()) {
                        Pair<Float, State> calculatePositionAndStateWhenStackedAtEnd = calculatePositionAndStateWhenStackedAtEnd(next.getIndex());
                        next.getTag().setPosition(((Float) calculatePositionAndStateWhenStackedAtEnd.first).floatValue());
                        next.getTag().setState((State) calculatePositionAndStateWhenStackedAtEnd.second);
                    }
                    float position2 = clone.getPosition();
                    TabItem tabItem8 = next;
                    i = i4;
                    Tag tag = clone;
                    builder = builder2;
                    i2 = length;
                    relocate(tabItem8, position2, tag, 0);
                } else {
                    if (!z && z2 && count > 3) {
                        Pair<Float, State> clipTabPosition3 = clipTabPosition(count, next.getIndex(), ((Float) pair.first).floatValue() - (Math.abs(((Float) pair.first).floatValue() - create2.previous().getTag().getPosition()) / 2.0f), peek);
                        f5 = ((Float) clipTabPosition3.first).floatValue();
                        pair = clipTabPosition3;
                    }
                    Tag tag2 = tabItemArr2[next.getIndex() - tabItem3.getIndex()].getTag();
                    tag2.setPosition(((Float) pair.first).floatValue());
                    tag2.setState((State) pair.second);
                    i = i4;
                    builder = builder2;
                    i2 = length;
                }
                if (pair.second == State.HIDDEN || pair.second == State.STACKED_END) {
                    this.firstVisibleIndex++;
                } else {
                    builder2 = builder;
                    tabItem5 = tabItem2;
                    i4 = i;
                    index = i3;
                    position = f2;
                    length = i2;
                }
            }
            this.firstVisibleIndex++;
            position = f6;
            f4 = f5;
            tabItem5 = tabItem7;
            i4 = i + 1;
            builder2 = builder;
            index = i5;
            length = i2;
            z3 = true;
        }
        TabItemIterator.Builder builder3 = builder2;
        if (z2 && count > 3) {
            AbstractTabItemIterator create3 = builder3.start(tabItem4.getIndex() + 1).reverse(false).create();
            Tag tag3 = tabItem4.getTag();
            float f9 = f4;
            while (true) {
                TabItem next2 = create3.next();
                if (next2 == null || next2.getIndex() >= count - 1) {
                    break;
                }
                Pair<Float, State> clipTabPosition4 = clipTabPosition(count, next2.getIndex(), calculateNonLinearPosition(f9, calculateMaxTabSpacing(count, next2)), tag3.getState());
                Tag clone2 = next2.getTag().clone();
                clone2.setPosition(((Float) clipTabPosition4.first).floatValue());
                clone2.setState((State) clipTabPosition4.second);
                if (!next2.isInflated()) {
                    Pair<Float, State> calculatePositionAndStateWhenStackedAtStart = calculatePositionAndStateWhenStackedAtStart(count, next2.getIndex(), create3.previous());
                    next2.getTag().setPosition(((Float) calculatePositionAndStateWhenStackedAtStart.first).floatValue());
                    next2.getTag().setState((State) calculatePositionAndStateWhenStackedAtStart.second);
                }
                relocate(next2, clone2.getPosition(), clone2, 0);
                f9 = ((Float) clipTabPosition4.first).floatValue();
                if (clipTabPosition4.second == State.HIDDEN || clipTabPosition4.second == State.STACKED_START) {
                    break;
                }
                tag3 = clone2;
            }
        }
        return tabItemArr2;
    }

    /* access modifiers changed from: private */
    public TabItem[] relocateWhenAddingStackedTabs(boolean z, TabItem[] tabItemArr) {
        Pair<Float, State> pair;
        if (!z) {
            this.firstVisibleIndex += tabItemArr.length;
        }
        int count = getTabSwitcher().getCount();
        TabItem tabItem = tabItemArr[0];
        TabItem tabItem2 = tabItemArr[tabItemArr.length - 1];
        TabItemIterator create = ((TabItemIterator.Builder) ((TabItemIterator.Builder) new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).start(z ? tabItem2.getIndex() : tabItem.getIndex())).reverse(z)).create();
        while (true) {
            TabItem next = create.next();
            if (next == null || (next.getTag().getState() != State.STACKED_START && next.getTag().getState() != State.STACKED_START_ATOP && next.getTag().getState() != State.STACKED_END && next.getTag().getState() != State.HIDDEN)) {
                break;
            }
            TabItem peek = z ? create.peek() : create.previous();
            if (z) {
                pair = calculatePositionAndStateWhenStackedAtStart(count, next.getIndex(), peek);
            } else {
                pair = calculatePositionAndStateWhenStackedAtEnd(next.getIndex());
            }
            if (z && peek != null && peek.getTag().getState() == State.FLOATING && peek.getTag().getPosition() - ((Float) pair.first).floatValue() > calculateMinTabSpacing(count)) {
                pair = clipTabPosition(count, next.getIndex(), calculateNonLinearPosition(next, peek), peek);
            }
            if (next.getIndex() < tabItem.getIndex() || next.getIndex() > tabItem2.getIndex()) {
                if (!next.isInflated()) {
                    break;
                }
                Tag clone = next.getTag().clone();
                clone.setPosition(((Float) pair.first).floatValue());
                clone.setState((State) pair.second);
                animateRelocate(next, clone.getPosition(), clone, 0, createRelocateAnimationListener(next));
            } else {
                Tag tag = tabItemArr[next.getIndex() - tabItem.getIndex()].getTag();
                tag.setPosition(((Float) pair.first).floatValue());
                tag.setState((State) pair.second);
            }
        }
        return tabItemArr;
    }

    /* access modifiers changed from: private */
    public TabItem[] relocateWhenAddingHiddenTabs(TabItem[] tabItemArr, TabItem tabItem) {
        Pair<Float, State> pair;
        boolean isStackedAtStart = isStackedAtStart(tabItem.getIndex());
        for (TabItem tabItem2 : tabItemArr) {
            if (isStackedAtStart) {
                pair = calculatePositionAndStateWhenStackedAtStart(getModel().getCount(), tabItem2.getIndex(), tabItem2.getIndex() > 0 ? TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, tabItem2.getIndex() - 1) : null);
            } else {
                pair = calculatePositionAndStateWhenStackedAtEnd(tabItem2.getIndex());
            }
            Tag tag = tabItem2.getTag();
            tag.setPosition(((Float) pair.first).floatValue());
            tag.setState((State) pair.second);
        }
        return tabItemArr;
    }

    private void relocate(TabItem tabItem, float f, Tag tag, long j) {
        if (tabItem.isInflated()) {
            animateRelocate(tabItem, f, tag, j, createRelocateAnimationListener(tabItem));
            return;
        }
        inflateAndUpdateView(tabItem, createRelocateLayoutListener(tabItem, f, tag, j, createRelocateAnimationListener(tabItem)));
        tabItem.getView().setVisibility(4);
    }

    private void swipe(TabItem tabItem, float f) {
        View view = tabItem.getView();
        if (!tabItem.getTag().isClosing()) {
            adaptStackOnSwipe(tabItem, tabItem.getIndex() + 1, getModel().getCount() - 1);
        }
        tabItem.getTag().setClosing(true);
        if (!tabItem.getTab().isCloseable()) {
            float pow = (float) Math.pow((double) Math.abs(f), 0.75d);
            if (f < 0.0f) {
                pow *= -1.0f;
            }
            f = pow;
        }
        getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.SWIPE));
        getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.SWIPE));
        float scale = getArithmetics().getScale(view, true);
        float abs = 1.0f - (Math.abs(f) / calculateSwipePosition());
        float f2 = this.swipedTabScale * scale;
        float f3 = f2 + ((scale - f2) * abs);
        getArithmetics().setScale(Arithmetics.Axis.DRAGGING_AXIS, view, f3);
        getArithmetics().setScale(Arithmetics.Axis.ORTHOGONAL_AXIS, view, f3);
        float f4 = this.swipedTabAlpha;
        view.setAlpha(f4 + (abs * (1.0f - f4)));
        getArithmetics().setPosition(Arithmetics.Axis.ORTHOGONAL_AXIS, view, f);
    }

    private void startOvershoot(float f) {
        TabItemIterator create = new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).create();
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                return;
            }
            if (next.getIndex() == 0) {
                View view = next.getView();
                getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.NONE));
                getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.NONE));
                getArithmetics().setPosition(Arithmetics.Axis.DRAGGING_AXIS, view, f);
            } else if (next.isInflated()) {
                View view2 = create.first().getView();
                View view3 = next.getView();
                view3.setVisibility(getArithmetics().getPosition(Arithmetics.Axis.DRAGGING_AXIS, view2) <= getArithmetics().getPosition(Arithmetics.Axis.DRAGGING_AXIS, view3) ? 4 : 0);
            }
        }
    }

    private void tiltOnStartOvershoot(float f) {
        TabItemIterator create = new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).create();
        while (true) {
            TabItem next = create.next();
            if (next != null) {
                View view = next.getView();
                if (next.getIndex() == 0) {
                    view.setCameraDistance((float) this.maxCameraDistance);
                    getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.OVERSHOOT_START));
                    getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.OVERSHOOT_START));
                    getArithmetics().setRotation(Arithmetics.Axis.ORTHOGONAL_AXIS, view, f);
                } else if (next.isInflated()) {
                    next.getView().setVisibility(4);
                }
            } else {
                return;
            }
        }
    }

    private void tiltOnEndOvershoot(float f) {
        float f2 = ((float) this.maxCameraDistance) / 2.0f;
        TabItemIterator create = new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).create();
        int i = -1;
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                return;
            }
            if (next.isInflated()) {
                View view = next.getView();
                if (!create.hasNext()) {
                    view.setCameraDistance((float) this.maxCameraDistance);
                } else if (i == -1) {
                    view.setCameraDistance(f2);
                    if (next.getTag().getState() == State.FLOATING) {
                        i = next.getIndex();
                    }
                } else {
                    view.setCameraDistance(((((float) this.maxCameraDistance) - f2) * (((float) (next.getIndex() - i)) / ((float) (getModel().getCount() - i)))) + f2);
                }
                getArithmetics().setPivot(Arithmetics.Axis.DRAGGING_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.DRAGGING_AXIS, view, AbstractDragHandler.DragState.OVERSHOOT_END));
                getArithmetics().setPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, getArithmetics().getPivot(Arithmetics.Axis.ORTHOGONAL_AXIS, view, AbstractDragHandler.DragState.OVERSHOOT_END));
                getArithmetics().setRotation(Arithmetics.Axis.ORTHOGONAL_AXIS, view, f);
            }
        }
    }

    private boolean isStackedAtStart(int i) {
        State state;
        TabItemIterator create = ((TabItemIterator.Builder) new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).start(i + 1)).create();
        do {
            TabItem next = create.next();
            if (next == null || (state = next.getTag().getState()) == State.STACKED_START) {
                return true;
            }
        } while (state != State.FLOATING);
        return false;
    }

    public PhoneTabSwitcherLayout(TabSwitcher tabSwitcher, TabSwitcherModel tabSwitcherModel, PhoneArithmetics phoneArithmetics) {
        super(tabSwitcher, tabSwitcherModel, phoneArithmetics);
        Resources resources = tabSwitcher.getResources();
        this.tabInset = resources.getDimensionPixelSize(R.dimen.tab_inset);
        this.tabBorderWidth = resources.getDimensionPixelSize(R.dimen.tab_border_width);
        this.tabTitleContainerHeight = resources.getDimensionPixelSize(R.dimen.tab_title_container_height);
        this.stackedTabCount = resources.getInteger(R.integer.stacked_tab_count);
        this.stackedTabSpacing = resources.getDimensionPixelSize(R.dimen.stacked_tab_spacing);
        this.maxCameraDistance = resources.getDimensionPixelSize(R.dimen.max_camera_distance);
        TypedValue typedValue = new TypedValue();
        resources.getValue(R.dimen.swiped_tab_scale, typedValue, true);
        this.swipedTabScale = typedValue.getFloat();
        resources.getValue(R.dimen.swiped_tab_alpha, typedValue, true);
        this.swipedTabAlpha = typedValue.getFloat();
        this.showSwitcherAnimationDuration = (long) resources.getInteger(R.integer.show_switcher_animation_duration);
        this.hideSwitcherAnimationDuration = (long) resources.getInteger(R.integer.hide_switcher_animation_duration);
        this.toolbarVisibilityAnimationDuration = (long) resources.getInteger(R.integer.toolbar_visibility_animation_duration);
        this.toolbarVisibilityAnimationDelay = (long) resources.getInteger(R.integer.toolbar_visibility_animation_delay);
        this.swipeAnimationDuration = (long) resources.getInteger(R.integer.swipe_animation_duration);
        this.clearAnimationDelay = (long) resources.getInteger(R.integer.clear_animation_delay);
        this.relocateAnimationDuration = (long) resources.getInteger(R.integer.relocate_animation_duration);
        this.relocateAnimationDelay = (long) resources.getInteger(R.integer.relocate_animation_delay);
        this.revertOvershootAnimationDuration = (long) resources.getInteger(R.integer.revert_overshoot_animation_duration);
        this.revealAnimationDuration = (long) resources.getInteger(R.integer.reveal_animation_duration);
        this.peekAnimationDuration = (long) resources.getInteger(R.integer.peek_animation_duration);
        this.maxStartOvershootAngle = (float) resources.getInteger(R.integer.max_start_overshoot_angle);
        this.maxEndOvershootAngle = (float) resources.getInteger(R.integer.max_end_overshoot_angle);
    }

    /* access modifiers changed from: protected */
    public final AbstractDragHandler<?> onInflateLayout(boolean z) {
        LayoutInflater from = LayoutInflater.from(getContext());
        if (z) {
            this.toolbar = (Toolbar) getTabSwitcher().findViewById(R.id.primary_toolbar);
        } else {
            int i = 0;
            this.toolbar = (Toolbar) from.inflate(R.layout.phone_toolbar, getTabSwitcher(), false);
            Toolbar toolbar2 = this.toolbar;
            if (!getModel().areToolbarsShown()) {
                i = 4;
            }
            toolbar2.setVisibility(i);
            getTabSwitcher().addView(this.toolbar);
        }
        this.tabContainer = new FrameLayout(getContext());
        getTabSwitcher().addView(this.tabContainer, -1, -1);
        this.childViewRecycler = new ViewRecycler<>(from);
        this.recyclerAdapter = new PhoneRecyclerAdapter(getTabSwitcher(), getModel(), this.childViewRecycler);
        getModel().addListener(this.recyclerAdapter);
        this.viewRecycler = new AttachedViewRecycler<>(this.tabContainer, from, Collections.reverseOrder(new TabItem.Comparator(getTabSwitcher())));
        this.viewRecycler.setAdapter(this.recyclerAdapter);
        this.recyclerAdapter.setViewRecycler(this.viewRecycler);
        this.dragHandler = new PhoneDragHandler(getTabSwitcher(), getArithmetics(), this.viewRecycler);
        adaptLogLevel();
        adaptDecorator();
        adaptToolbarMargin();
        return this.dragHandler;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.support.v4.util.Pair<java.lang.Integer, java.lang.Float> onDetachLayout(boolean r4) {
        /*
            r3 = this;
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r0 = r3.getModel()
            boolean r0 = r0.isSwitcherShown()
            if (r0 == 0) goto L_0x003e
            int r0 = r3.firstVisibleIndex
            r1 = -1
            if (r0 == r1) goto L_0x003e
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r0 = r3.getModel()
            de.mrapp.android.util.view.AttachedViewRecycler<de.mrapp.android.tabswitcher.model.TabItem, java.lang.Integer> r1 = r3.viewRecycler
            int r2 = r3.firstVisibleIndex
            de.mrapp.android.tabswitcher.model.TabItem r0 = de.mrapp.android.tabswitcher.model.TabItem.create((de.mrapp.android.tabswitcher.model.Model) r0, (de.mrapp.android.util.view.AttachedViewRecycler<de.mrapp.android.tabswitcher.model.TabItem, ?>) r1, (int) r2)
            de.mrapp.android.tabswitcher.model.Tag r1 = r0.getTag()
            de.mrapp.android.tabswitcher.model.State r1 = r1.getState()
            de.mrapp.android.tabswitcher.model.State r2 = de.mrapp.android.tabswitcher.model.State.HIDDEN
            if (r1 == r2) goto L_0x003e
            de.mrapp.android.tabswitcher.model.Tag r0 = r0.getTag()
            float r0 = r0.getPosition()
            int r1 = r3.firstVisibleIndex
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            android.support.v4.util.Pair r0 = android.support.v4.util.Pair.create(r1, r0)
            goto L_0x003f
        L_0x003e:
            r0 = 0
        L_0x003f:
            de.mrapp.android.util.view.ViewRecycler<de.mrapp.android.tabswitcher.Tab, java.lang.Void> r1 = r3.childViewRecycler
            r1.removeAll()
            de.mrapp.android.util.view.ViewRecycler<de.mrapp.android.tabswitcher.Tab, java.lang.Void> r1 = r3.childViewRecycler
            r1.clearCache()
            de.mrapp.android.util.view.AttachedViewRecycler<de.mrapp.android.tabswitcher.model.TabItem, java.lang.Integer> r1 = r3.viewRecycler
            r1.removeAll()
            de.mrapp.android.util.view.AttachedViewRecycler<de.mrapp.android.tabswitcher.model.TabItem, java.lang.Integer> r1 = r3.viewRecycler
            r1.clearCache()
            de.mrapp.android.tabswitcher.layout.phone.PhoneRecyclerAdapter r1 = r3.recyclerAdapter
            r1.clearCachedPreviews()
            if (r4 != 0) goto L_0x0075
            de.mrapp.android.tabswitcher.model.TabSwitcherModel r4 = r3.getModel()
            de.mrapp.android.tabswitcher.layout.phone.PhoneRecyclerAdapter r1 = r3.recyclerAdapter
            r4.removeListener(r1)
            de.mrapp.android.tabswitcher.TabSwitcher r4 = r3.getTabSwitcher()
            android.support.v7.widget.Toolbar r1 = r3.toolbar
            r4.removeView(r1)
            de.mrapp.android.tabswitcher.TabSwitcher r4 = r3.getTabSwitcher()
            android.view.ViewGroup r1 = r3.tabContainer
            r4.removeView(r1)
        L_0x0075:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: de.mrapp.android.tabswitcher.layout.phone.PhoneTabSwitcherLayout.onDetachLayout(boolean):android.support.v4.util.Pair");
    }

    public final boolean handleTouchEvent(MotionEvent motionEvent) {
        return this.dragHandler.handleTouchEvent(motionEvent);
    }

    public final ViewGroup getTabContainer() {
        return this.tabContainer;
    }

    public final Toolbar[] getToolbars() {
        return new Toolbar[]{this.toolbar};
    }

    public final void onLogLevelChanged(LogLevel logLevel) {
        adaptLogLevel();
    }

    public final void onDecoratorChanged(TabSwitcherDecorator tabSwitcherDecorator) {
        adaptDecorator();
        super.onDecoratorChanged(tabSwitcherDecorator);
    }

    public final void onSwitcherShown() {
        getLogger().logInfo(getClass(), "Showed tab switcher");
        animateShowSwitcher();
    }

    public final void onSwitcherHidden() {
        getLogger().logInfo(getClass(), "Hid tab switcher");
        animateHideSwitcher();
    }

    public final void onSelectionChanged(int i, int i2, Tab tab, boolean z) {
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logInfo(cls, "Selected tab at index " + i2);
        if (z) {
            animateHideSwitcher();
            return;
        }
        this.viewRecycler.remove(TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, i));
        this.viewRecycler.inflate(TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, i2), new Integer[0]);
    }

    public final void onTabAdded(int i, Tab tab, int i2, int i3, boolean z, Animation animation) {
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logInfo(cls, "Added tab at index " + i + " using a " + animation.getClass().getSimpleName());
        if ((animation instanceof PeekAnimation) && !getModel().isEmpty()) {
            Condition.ensureTrue(z, animation.getClass().getSimpleName() + " not supported when the tab switcher is shown");
            TabItem tabItem = new TabItem(0, tab);
            inflateView(tabItem, createPeekLayoutListener(tabItem, (PeekAnimation) animation), new Integer[0]);
        } else if (!(animation instanceof RevealAnimation) || !z) {
            addAllTabs(i, new Tab[]{tab}, animation);
        } else {
            TabItem tabItem2 = new TabItem(0, tab);
            inflateView(tabItem2, createRevealLayoutListener(tabItem2, (RevealAnimation) animation), new Integer[0]);
        }
    }

    public final void onAllTabsAdded(int i, Tab[] tabArr, int i2, int i3, Animation animation) {
        Condition.ensureTrue(animation instanceof SwipeAnimation, animation.getClass().getSimpleName() + " not supported for adding multiple tabs");
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logInfo(cls, "Added " + tabArr.length + " tabs at index " + i + " using a " + animation.getClass().getSimpleName());
        addAllTabs(i, tabArr, animation);
    }

    public final void onTabRemoved(int i, Tab tab, int i2, int i3, Animation animation) {
        SwipeAnimation swipeAnimation;
        Pair<Float, State> pair;
        boolean z = animation instanceof SwipeAnimation;
        Condition.ensureTrue(z, animation.getClass().getSimpleName() + " not supported for removing tabs");
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logInfo(cls, "Removed tab at index " + i + " using a " + animation.getClass().getSimpleName());
        TabItem create = TabItem.create((AttachedViewRecycler<TabItem, ?>) this.viewRecycler, i, tab);
        if (!getModel().isSwitcherShown()) {
            this.viewRecycler.remove(create);
            if (getModel().isEmpty()) {
                this.toolbar.setAlpha(getModel().areToolbarsShown() ? 1.0f : 0.0f);
            } else if (i3 != i2) {
                this.viewRecycler.inflate(TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, i3), new Integer[0]);
            }
        } else {
            adaptStackOnSwipe(create, create.getIndex(), getModel().getCount());
            create.getTag().setClosing(true);
            if (z) {
                swipeAnimation = (SwipeAnimation) animation;
            } else {
                swipeAnimation = new SwipeAnimation.Builder().create();
            }
            if (create.isInflated()) {
                animateRemove(create, swipeAnimation);
                return;
            }
            boolean isStackedAtStart = isStackedAtStart(i);
            TabItem create2 = TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, i - 1);
            if (isStackedAtStart) {
                pair = calculatePositionAndStateWhenStackedAtStart(getModel().getCount(), i, create2);
            } else {
                pair = calculatePositionAndStateWhenStackedAtEnd(i);
            }
            create.getTag().setPosition(((Float) pair.first).floatValue());
            create.getTag().setState((State) pair.second);
            inflateAndUpdateView(create, createRemoveLayoutListener(create, swipeAnimation));
        }
    }

    public final void onAllTabsRemoved(Tab[] tabArr, Animation animation) {
        SwipeAnimation swipeAnimation;
        boolean z = animation instanceof SwipeAnimation;
        Condition.ensureTrue(z, animation.getClass().getSimpleName() + " not supported for removing tabs ");
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logInfo(cls, "Removed all tabs using a " + animation.getClass().getSimpleName());
        if (!getModel().isSwitcherShown()) {
            this.viewRecycler.removeAll();
            this.toolbar.setAlpha(getModel().areToolbarsShown() ? 1.0f : 0.0f);
            return;
        }
        if (z) {
            swipeAnimation = (SwipeAnimation) animation;
        } else {
            swipeAnimation = new SwipeAnimation.Builder().create();
        }
        ArrayTabItemIterator create = ((ArrayTabItemIterator.Builder) new ArrayTabItemIterator.Builder(this.viewRecycler, tabArr).reverse(true)).create();
        int i = 0;
        while (true) {
            TabItem next = create.next();
            if (next != null) {
                TabItem previous = create.previous();
                if (next.getTag().getState() == State.FLOATING || (previous != null && previous.getTag().getState() == State.FLOATING)) {
                    i = (int) (((long) i) + this.clearAnimationDelay);
                }
                int i2 = i;
                if (next.isInflated()) {
                    animateSwipe(next, true, (long) i2, swipeAnimation, !create.hasNext() ? createClearAnimationListener() : null);
                }
                i = i2;
            } else {
                return;
            }
        }
    }

    public final void onPaddingChanged(int i, int i2, int i3, int i4) {
        adaptToolbarMargin();
    }

    public final void onGlobalLayout() {
        if (getModel().isSwitcherShown()) {
            InitialTabItemIterator initialTabItemIterator = new InitialTabItemIterator(calculateInitialTabItems(getModel().getFirstVisibleTabIndex(), getModel().getFirstVisibleTabPosition()), false, 0);
            while (true) {
                TabItem next = initialTabItemIterator.next();
                if (next == null) {
                    break;
                } else if (next.isVisible()) {
                    inflateAndUpdateView(next, createBottomMarginLayoutListener(next));
                }
            }
            this.toolbar.setAlpha(getModel().areToolbarsShown() ? 1.0f : 0.0f);
        } else if (getModel().getSelectedTab() != null) {
            this.viewRecycler.inflate(TabItem.create((Model) getTabSwitcher(), (AttachedViewRecycler<TabItem, ?>) this.viewRecycler, getModel().getSelectedTabIndex()), new Integer[0]);
        }
    }

    public final AbstractDragHandler.DragState onDrag(AbstractDragHandler.DragState dragState, float f) {
        AbstractDragHandler.DragState dragState2;
        if (f != 0.0f) {
            if (dragState == AbstractDragHandler.DragState.DRAG_TO_END) {
                calculatePositionsWhenDraggingToEnd(f);
            } else {
                calculatePositionsWhenDraggingToStart(f);
            }
        }
        if (isOvershootingAtEnd(new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).create())) {
            dragState2 = AbstractDragHandler.DragState.OVERSHOOT_END;
        } else {
            dragState2 = isOvershootingAtStart() ? AbstractDragHandler.DragState.OVERSHOOT_START : null;
        }
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logVerbose(cls, "Dragging using a distance of " + f + " pixels. Drag state is " + dragState + ", overshoot is " + dragState2);
        return dragState2;
    }

    public final void onClick(TabItem tabItem) {
        getModel().selectTab(tabItem.getTab());
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logVerbose(cls, "Clicked tab at index " + tabItem.getIndex());
    }

    public final void onRevertStartOvershoot() {
        animateRevertStartOvershoot();
        getLogger().logVerbose(getClass(), "Reverting overshoot at the start");
    }

    public final void onRevertEndOvershoot() {
        animateRevertEndOvershoot();
        getLogger().logVerbose(getClass(), "Reverting overshoot at the end");
    }

    public final void onStartOvershoot(float f) {
        startOvershoot(f);
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logVerbose(cls, "Overshooting at the start using a position of " + f + " pixels");
    }

    public final void onTiltOnStartOvershoot(float f) {
        tiltOnStartOvershoot(f);
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logVerbose(cls, "Tilting on start overshoot using an angle of " + f + " degrees");
    }

    public final void onTiltOnEndOvershoot(float f) {
        tiltOnEndOvershoot(f);
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logVerbose(cls, "Tilting on end overshoot using an angle of " + f + " degrees");
    }

    public final void onSwipe(TabItem tabItem, float f) {
        swipe(tabItem, f);
        Logger logger = getLogger();
        Class<?> cls = getClass();
        logger.logVerbose(cls, "Swiping tab at index " + tabItem.getIndex() + ". Current swipe distance is " + f + " pixels");
    }

    public final void onSwipeEnded(TabItem tabItem, boolean z, float f) {
        if (z) {
            getModel().removeTab(tabItem.getTab(), ((SwipeAnimation.Builder) new SwipeAnimation.Builder().setDirection(getArithmetics().getPosition(Arithmetics.Axis.ORTHOGONAL_AXIS, tabItem.getView()) < 0.0f ? SwipeAnimation.SwipeDirection.LEFT : SwipeAnimation.SwipeDirection.RIGHT).setDuration(f > 0.0f ? (long) Math.round((calculateSwipePosition() / f) * 1000.0f) : -1)).create());
        } else {
            animateSwipe(tabItem, false, 0, new SwipeAnimation.Builder().create(), createSwipeAnimationListener(tabItem));
        }
        Logger logger = getLogger();
        Class<?> cls = getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("Ended swiping tab at index ");
        sb.append(tabItem.getIndex());
        sb.append(". Tab will ");
        sb.append(z ? "" : "not ");
        sb.append("be removed");
        logger.logVerbose(cls, sb.toString());
    }
}
