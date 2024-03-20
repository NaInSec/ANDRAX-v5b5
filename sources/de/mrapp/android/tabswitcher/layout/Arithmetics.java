package de.mrapp.android.tabswitcher.layout;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import de.mrapp.android.tabswitcher.layout.AbstractDragHandler;

public interface Arithmetics {

    public enum Axis {
        DRAGGING_AXIS,
        ORTHOGONAL_AXIS,
        X_AXIS,
        Y_AXIS
    }

    void animatePosition(Axis axis, ViewPropertyAnimator viewPropertyAnimator, View view, float f, boolean z);

    void animateRotation(Axis axis, ViewPropertyAnimator viewPropertyAnimator, float f);

    void animateScale(Axis axis, ViewPropertyAnimator viewPropertyAnimator, float f);

    int getPadding(Axis axis, int i, View view);

    float getPivot(Axis axis, View view, AbstractDragHandler.DragState dragState);

    float getPosition(Axis axis, MotionEvent motionEvent);

    float getPosition(Axis axis, View view);

    float getRotation(Axis axis, View view);

    float getScale(View view, boolean z);

    float getSize(Axis axis, View view);

    float getTabContainerSize(Axis axis);

    float getTabContainerSize(Axis axis, boolean z);

    void setPivot(Axis axis, View view, float f);

    void setPosition(Axis axis, View view, float f);

    void setRotation(Axis axis, View view, float f);

    void setScale(Axis axis, View view, float f);
}
