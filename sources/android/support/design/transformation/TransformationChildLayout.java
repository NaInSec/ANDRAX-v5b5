package android.support.design.transformation;

import android.content.Context;
import android.support.design.circularreveal.CircularRevealFrameLayout;
import android.util.AttributeSet;

public class TransformationChildLayout extends CircularRevealFrameLayout {
    public TransformationChildLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public TransformationChildLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
