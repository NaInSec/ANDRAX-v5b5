package de.mrapp.android.tabswitcher.layout.phone;

import android.content.res.Resources;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import de.mrapp.android.tabswitcher.Layout;
import de.mrapp.android.tabswitcher.R;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.iterator.TabItemIterator;
import de.mrapp.android.tabswitcher.layout.AbstractDragHandler;
import de.mrapp.android.tabswitcher.layout.Arithmetics;
import de.mrapp.android.tabswitcher.model.State;
import de.mrapp.android.tabswitcher.model.TabItem;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.gesture.DragHelper;
import de.mrapp.android.util.view.AttachedViewRecycler;

public class PhoneDragHandler extends AbstractDragHandler<Callback> {
    private final float maxEndOvershootAngle;
    private final int maxOvershootDistance;
    private final float maxStartOvershootAngle;
    private final DragHelper overshootDragHelper = new DragHelper(0);
    private final int stackedTabCount;
    private final int tabInset;
    private final AttachedViewRecycler<TabItem, ?> viewRecycler;

    public interface Callback extends AbstractDragHandler.Callback {
        void onStartOvershoot(float f);

        void onTiltOnEndOvershoot(float f);

        void onTiltOnStartOvershoot(float f);
    }

    private void notifyOnStartOvershoot(float f) {
        if (getCallback() != null) {
            ((Callback) getCallback()).onStartOvershoot(f);
        }
    }

    private void notifyOnTiltOnStartOvershoot(float f) {
        if (getCallback() != null) {
            ((Callback) getCallback()).onTiltOnStartOvershoot(f);
        }
    }

    private void notifyOnTiltOnEndOvershoot(float f) {
        if (getCallback() != null) {
            ((Callback) getCallback()).onTiltOnEndOvershoot(f);
        }
    }

    public PhoneDragHandler(TabSwitcher tabSwitcher, Arithmetics arithmetics, AttachedViewRecycler<TabItem, ?> attachedViewRecycler) {
        super(tabSwitcher, arithmetics, true);
        Condition.ensureNotNull(attachedViewRecycler, "The view recycler may not be null");
        this.viewRecycler = attachedViewRecycler;
        Resources resources = tabSwitcher.getResources();
        this.tabInset = resources.getDimensionPixelSize(R.dimen.tab_inset);
        this.stackedTabCount = resources.getInteger(R.integer.stacked_tab_count);
        this.maxOvershootDistance = resources.getDimensionPixelSize(R.dimen.max_overshoot_distance);
        this.maxStartOvershootAngle = (float) resources.getInteger(R.integer.max_start_overshoot_angle);
        this.maxEndOvershootAngle = (float) resources.getInteger(R.integer.max_end_overshoot_angle);
    }

    /* access modifiers changed from: protected */
    public final TabItem getFocusedTab(float f) {
        TabItemIterator create = new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).create();
        while (true) {
            TabItem next = create.next();
            if (next == null) {
                return null;
            }
            if (next.getTag().getState() == State.FLOATING || next.getTag().getState() == State.STACKED_START_ATOP || next.getTag().getState() == State.STACKED_START) {
                View view = next.getView();
                Toolbar[] toolbars = getTabSwitcher().getToolbars();
                if (getArithmetics().getPosition(Arithmetics.Axis.DRAGGING_AXIS, view) + ((getTabSwitcher().getLayout() == Layout.PHONE_LANDSCAPE || !getTabSwitcher().areToolbarsShown() || toolbars == null) ? 0.0f : (float) (toolbars[0].getHeight() - this.tabInset)) + ((float) getArithmetics().getPadding(Arithmetics.Axis.DRAGGING_AXIS, GravityCompat.START, getTabSwitcher())) <= f) {
                    return next;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final float onOvershootStart(float f, float f2) {
        float f3;
        this.overshootDragHelper.update(f);
        float dragDistance = this.overshootDragHelper.getDragDistance();
        if (dragDistance < 0.0f) {
            float abs = Math.abs(dragDistance);
            if (getTabSwitcher().getCount() >= this.stackedTabCount) {
                f3 = (float) this.maxOvershootDistance;
            } else {
                f3 = getTabSwitcher().getCount() > 1 ? ((float) this.maxOvershootDistance) / ((float) getTabSwitcher().getCount()) : 0.0f;
            }
            if (abs <= f3) {
                float max = Math.max(0.0f, Math.min(1.0f, abs / f3));
                float position = new TabItemIterator.Builder(getTabSwitcher(), this.viewRecycler).create().getItem(0).getTag().getPosition();
                notifyOnStartOvershoot(position - (max * position));
            } else {
                float f4 = (abs - f3) / ((float) this.maxOvershootDistance);
                if (f4 >= 1.0f) {
                    this.overshootDragHelper.setMinDragDistance(dragDistance);
                    f2 = f + ((float) this.maxOvershootDistance) + f3;
                }
                notifyOnTiltOnStartOvershoot(Math.max(0.0f, Math.min(1.0f, f4)) * this.maxStartOvershootAngle);
            }
        }
        return f2;
    }

    /* access modifiers changed from: protected */
    public final float onOvershootEnd(float f, float f2) {
        this.overshootDragHelper.update(f);
        float dragDistance = this.overshootDragHelper.getDragDistance();
        float f3 = dragDistance / ((float) this.maxOvershootDistance);
        if (f3 >= 1.0f) {
            this.overshootDragHelper.setMaxDragDistance(dragDistance);
            f2 = f - ((float) this.maxOvershootDistance);
        }
        notifyOnTiltOnEndOvershoot(Math.max(0.0f, Math.min(1.0f, f3)) * (-(getTabSwitcher().getCount() > 1 ? this.maxEndOvershootAngle : this.maxStartOvershootAngle)));
        return f2;
    }

    /* access modifiers changed from: protected */
    public final void onOvershootReverted() {
        this.overshootDragHelper.reset();
    }

    /* access modifiers changed from: protected */
    public final void onReset() {
        this.overshootDragHelper.reset();
    }

    /* access modifiers changed from: protected */
    public final boolean isSwipeThresholdReached(TabItem tabItem) {
        return Math.abs(getArithmetics().getPosition(Arithmetics.Axis.ORTHOGONAL_AXIS, tabItem.getView())) > getArithmetics().getTabContainerSize(Arithmetics.Axis.ORTHOGONAL_AXIS) / 2.0f;
    }
}
