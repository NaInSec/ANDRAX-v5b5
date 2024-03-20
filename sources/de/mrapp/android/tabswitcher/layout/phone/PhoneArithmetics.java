package de.mrapp.android.tabswitcher.layout.phone;

import android.content.res.Resources;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import de.mrapp.android.tabswitcher.Layout;
import de.mrapp.android.tabswitcher.R;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.layout.AbstractDragHandler;
import de.mrapp.android.tabswitcher.layout.Arithmetics;
import de.mrapp.android.util.Condition;

public class PhoneArithmetics implements Arithmetics {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final float endOvershootPivot;
    private final int stackedTabCount;
    private final float stackedTabSpacing;
    private final int tabInset;
    private final TabSwitcher tabSwitcher;
    private final int tabTitleContainerHeight;

    private Arithmetics.Axis getOrientationInvariantAxis(Arithmetics.Axis axis) {
        if (axis == Arithmetics.Axis.Y_AXIS) {
            return Arithmetics.Axis.DRAGGING_AXIS;
        }
        if (axis == Arithmetics.Axis.X_AXIS) {
            return Arithmetics.Axis.ORTHOGONAL_AXIS;
        }
        if (this.tabSwitcher.getLayout() == Layout.PHONE_LANDSCAPE) {
            return axis == Arithmetics.Axis.DRAGGING_AXIS ? Arithmetics.Axis.ORTHOGONAL_AXIS : Arithmetics.Axis.DRAGGING_AXIS;
        }
        return axis;
    }

    private float getDefaultPivot(Arithmetics.Axis axis, View view) {
        if (axis == Arithmetics.Axis.DRAGGING_AXIS || axis == Arithmetics.Axis.Y_AXIS) {
            if (this.tabSwitcher.getLayout() == Layout.PHONE_LANDSCAPE) {
                return getSize(axis, view) / 2.0f;
            }
            return 0.0f;
        } else if (this.tabSwitcher.getLayout() == Layout.PHONE_LANDSCAPE) {
            return 0.0f;
        } else {
            return getSize(axis, view) / 2.0f;
        }
    }

    private float getPivotWhenSwiping(Arithmetics.Axis axis, View view) {
        if (axis == Arithmetics.Axis.DRAGGING_AXIS || axis == Arithmetics.Axis.Y_AXIS) {
            return this.endOvershootPivot;
        }
        return getDefaultPivot(axis, view);
    }

    private float getPivotWhenOvershootingAtStart(Arithmetics.Axis axis, View view) {
        return getSize(axis, view) / 2.0f;
    }

    private float getPivotWhenOvershootingAtEnd(Arithmetics.Axis axis, View view) {
        if (axis == Arithmetics.Axis.DRAGGING_AXIS || axis == Arithmetics.Axis.Y_AXIS) {
            return this.tabSwitcher.getCount() > 1 ? this.endOvershootPivot : getSize(axis, view) / 2.0f;
        }
        return getSize(axis, view) / 2.0f;
    }

    public PhoneArithmetics(TabSwitcher tabSwitcher2) {
        Condition.ensureNotNull(tabSwitcher2, "The tab switcher may not be null");
        this.tabSwitcher = tabSwitcher2;
        Resources resources = tabSwitcher2.getResources();
        this.tabTitleContainerHeight = resources.getDimensionPixelSize(R.dimen.tab_title_container_height);
        this.tabInset = resources.getDimensionPixelSize(R.dimen.tab_inset);
        this.stackedTabCount = resources.getInteger(R.integer.stacked_tab_count);
        this.stackedTabSpacing = (float) resources.getDimensionPixelSize(R.dimen.stacked_tab_spacing);
        this.endOvershootPivot = (float) resources.getDimensionPixelSize(R.dimen.end_overshoot_pivot);
    }

    public final float getPosition(Arithmetics.Axis axis, MotionEvent motionEvent) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(motionEvent, "The motion event may not be null");
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            return motionEvent.getY();
        }
        return motionEvent.getX();
    }

    public final float getPosition(Arithmetics.Axis axis, View view) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(view, "The view may not be null");
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            Toolbar[] toolbars = this.tabSwitcher.getToolbars();
            float y = view.getY();
            int i = 0;
            if (this.tabSwitcher.areToolbarsShown() && this.tabSwitcher.isSwitcherShown() && toolbars != null) {
                i = toolbars[0].getHeight() - this.tabInset;
            }
            return (y - ((float) i)) - ((float) getPadding(axis, GravityCompat.START, this.tabSwitcher));
        }
        return ((view.getX() - ((float) ((FrameLayout.LayoutParams) view.getLayoutParams()).leftMargin)) - (((float) this.tabSwitcher.getPaddingLeft()) / 2.0f)) + (((float) this.tabSwitcher.getPaddingRight()) / 2.0f) + ((this.tabSwitcher.getLayout() != Layout.PHONE_LANDSCAPE || !this.tabSwitcher.isSwitcherShown()) ? 0.0f : (((float) this.stackedTabCount) * this.stackedTabSpacing) / 2.0f);
    }

    public final void setPosition(Arithmetics.Axis axis, View view, float f) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(view, "The view may not be null");
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            Toolbar[] toolbars = this.tabSwitcher.getToolbars();
            int i = 0;
            if (this.tabSwitcher.areToolbarsShown() && this.tabSwitcher.isSwitcherShown() && toolbars != null) {
                i = toolbars[0].getHeight() - this.tabInset;
            }
            view.setY(((float) (i + getPadding(axis, GravityCompat.START, this.tabSwitcher))) + f);
            return;
        }
        view.setX((((f + ((float) ((FrameLayout.LayoutParams) view.getLayoutParams()).leftMargin)) + (((float) this.tabSwitcher.getPaddingLeft()) / 2.0f)) - (((float) this.tabSwitcher.getPaddingRight()) / 2.0f)) - ((this.tabSwitcher.getLayout() != Layout.PHONE_LANDSCAPE || !this.tabSwitcher.isSwitcherShown()) ? 0.0f : (((float) this.stackedTabCount) * this.stackedTabSpacing) / 2.0f));
    }

    public final void animatePosition(Arithmetics.Axis axis, ViewPropertyAnimator viewPropertyAnimator, View view, float f, boolean z) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(viewPropertyAnimator, "The animator may not be null");
        Condition.ensureNotNull(view, "The view may not be null");
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            Toolbar[] toolbars = this.tabSwitcher.getToolbars();
            int i = 0;
            int height = (!this.tabSwitcher.areToolbarsShown() || !this.tabSwitcher.isSwitcherShown() || toolbars == null) ? 0 : toolbars[0].getHeight() - this.tabInset;
            if (z) {
                i = getPadding(axis, GravityCompat.START, this.tabSwitcher);
            }
            viewPropertyAnimator.y(((float) (height + i)) + f);
            return;
        }
        float f2 = 0.0f;
        float paddingLeft = f + ((float) ((FrameLayout.LayoutParams) view.getLayoutParams()).leftMargin) + (z ? (((float) this.tabSwitcher.getPaddingLeft()) / 2.0f) - (((float) this.tabSwitcher.getPaddingRight()) / 2.0f) : 0.0f);
        if (this.tabSwitcher.getLayout() == Layout.PHONE_LANDSCAPE && this.tabSwitcher.isSwitcherShown()) {
            f2 = (((float) this.stackedTabCount) * this.stackedTabSpacing) / 2.0f;
        }
        viewPropertyAnimator.x(paddingLeft - f2);
    }

    public final int getPadding(Arithmetics.Axis axis, int i, View view) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureTrue(i == 8388611 || i == 8388613, "Invalid gravity");
        Condition.ensureNotNull(view, "The view may not be null");
        return getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS ? i == 8388611 ? view.getPaddingTop() : view.getPaddingBottom() : i == 8388611 ? view.getPaddingLeft() : view.getPaddingRight();
    }

    public final float getScale(View view, boolean z) {
        Condition.ensureNotNull(view, "The view may not be null");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        float width = (float) view.getWidth();
        return ((((((float) layoutParams.leftMargin) + width) + ((float) layoutParams.rightMargin)) - ((float) (z ? this.tabSwitcher.getPaddingLeft() + this.tabSwitcher.getPaddingRight() : 0))) - (this.tabSwitcher.getLayout() == Layout.PHONE_LANDSCAPE ? ((float) this.stackedTabCount) * this.stackedTabSpacing : 0.0f)) / width;
    }

    public final void setScale(Arithmetics.Axis axis, View view, float f) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(view, "The view may not be null");
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            view.setScaleY(f);
        } else {
            view.setScaleX(f);
        }
    }

    public final void animateScale(Arithmetics.Axis axis, ViewPropertyAnimator viewPropertyAnimator, float f) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(viewPropertyAnimator, "The animator may not be null");
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            viewPropertyAnimator.scaleY(f);
        } else {
            viewPropertyAnimator.scaleX(f);
        }
    }

    public final float getSize(Arithmetics.Axis axis, View view) {
        float width;
        float scale;
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(view, "The view may not be null");
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            width = (float) view.getHeight();
            scale = getScale(view, false);
        } else {
            width = (float) view.getWidth();
            scale = getScale(view, false);
        }
        return width * scale;
    }

    public final float getTabContainerSize(Arithmetics.Axis axis) {
        return getTabContainerSize(axis, true);
    }

    public final float getTabContainerSize(Arithmetics.Axis axis, boolean z) {
        int width;
        Condition.ensureNotNull(axis, "The axis may not be null");
        ViewGroup tabContainer = this.tabSwitcher.getTabContainer();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) tabContainer.getLayoutParams();
        int i = 0;
        int padding = !z ? getPadding(axis, GravityCompat.START, this.tabSwitcher) + getPadding(axis, GravityCompat.END, this.tabSwitcher) : 0;
        Toolbar[] toolbars = this.tabSwitcher.getToolbars();
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            if (!z && this.tabSwitcher.areToolbarsShown() && toolbars != null) {
                i = toolbars[0].getHeight() - this.tabInset;
            }
            width = (((tabContainer.getHeight() - layoutParams.topMargin) - layoutParams.bottomMargin) - padding) - i;
        } else {
            width = ((tabContainer.getWidth() - layoutParams.leftMargin) - layoutParams.rightMargin) - padding;
        }
        return (float) width;
    }

    public final float getPivot(Arithmetics.Axis axis, View view, AbstractDragHandler.DragState dragState) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(view, "The view may not be null");
        Condition.ensureNotNull(dragState, "The drag state may not be null");
        if (dragState == AbstractDragHandler.DragState.SWIPE) {
            return getPivotWhenSwiping(axis, view);
        }
        if (dragState == AbstractDragHandler.DragState.OVERSHOOT_START) {
            return getPivotWhenOvershootingAtStart(axis, view);
        }
        if (dragState == AbstractDragHandler.DragState.OVERSHOOT_END) {
            return getPivotWhenOvershootingAtEnd(axis, view);
        }
        return getDefaultPivot(axis, view);
    }

    public final void setPivot(Arithmetics.Axis axis, View view, float f) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(view, "The view may not be null");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            float f2 = (f - ((float) layoutParams.topMargin)) - ((float) this.tabTitleContainerHeight);
            view.setTranslationY(view.getTranslationY() + ((view.getPivotY() - f2) * (1.0f - view.getScaleY())));
            view.setPivotY(f2);
            return;
        }
        float f3 = f - ((float) layoutParams.leftMargin);
        view.setTranslationX(view.getTranslationX() + ((view.getPivotX() - f3) * (1.0f - view.getScaleX())));
        view.setPivotX(f3);
    }

    public final float getRotation(Arithmetics.Axis axis, View view) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(view, "The view may not be null");
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            return view.getRotationY();
        }
        return view.getRotationX();
    }

    public final void setRotation(Arithmetics.Axis axis, View view, float f) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(view, "The view may not be null");
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            if (this.tabSwitcher.getLayout() == Layout.PHONE_LANDSCAPE) {
                f *= -1.0f;
            }
            view.setRotationY(f);
            return;
        }
        if (this.tabSwitcher.getLayout() == Layout.PHONE_LANDSCAPE) {
            f *= -1.0f;
        }
        view.setRotationX(f);
    }

    public final void animateRotation(Arithmetics.Axis axis, ViewPropertyAnimator viewPropertyAnimator, float f) {
        Condition.ensureNotNull(axis, "The axis may not be null");
        Condition.ensureNotNull(viewPropertyAnimator, "The animator may not be null");
        if (getOrientationInvariantAxis(axis) == Arithmetics.Axis.DRAGGING_AXIS) {
            if (this.tabSwitcher.getLayout() == Layout.PHONE_LANDSCAPE) {
                f *= -1.0f;
            }
            viewPropertyAnimator.rotationY(f);
            return;
        }
        if (this.tabSwitcher.getLayout() == Layout.PHONE_LANDSCAPE) {
            f *= -1.0f;
        }
        viewPropertyAnimator.rotationX(f);
    }
}
