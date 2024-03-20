package de.mrapp.android.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import de.mrapp.android.util.R;

public class ScrimInsetsLayout extends FrameLayout {
    private Callback callback;
    private Drawable insetDrawable;
    private Rect insets;

    public interface Callback {
        void onInsetsChanged(Rect rect);
    }

    private void initialize(AttributeSet attributeSet, int i, int i2) {
        this.insets = null;
        this.callback = null;
        setWillNotDraw(true);
        obtainStyledAttributes(attributeSet, i, i2);
    }

    private void obtainStyledAttributes(AttributeSet attributeSet, int i, int i2) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ScrimInsetsLayout, i, i2);
            try {
                obtainInsetForeground(obtainStyledAttributes);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    private void obtainInsetForeground(TypedArray typedArray) {
        int color = typedArray.getColor(R.styleable.ScrimInsetsLayout_insetDrawable, -1);
        if (color == -1) {
            Drawable drawable = typedArray.getDrawable(R.styleable.ScrimInsetsLayout_insetDrawable);
            if (drawable != null) {
                setInsetDrawable(drawable);
            } else {
                setInsetColor(ContextCompat.getColor(getContext(), R.color.scrim_insets_layout_insets_drawable_default_value));
            }
        } else {
            setInsetColor(color);
        }
    }

    private void notifyOnInsetsChanged(Rect rect) {
        Callback callback2 = this.callback;
        if (callback2 != null) {
            callback2.onInsetsChanged(rect);
        }
    }

    public ScrimInsetsLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public ScrimInsetsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(attributeSet, 0, 0);
    }

    public ScrimInsetsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(attributeSet, i, 0);
    }

    public ScrimInsetsLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        initialize(attributeSet, i, i2);
    }

    public final void setCallback(Callback callback2) {
        this.callback = callback2;
    }

    public final Drawable getInsetDrawable() {
        return this.insetDrawable;
    }

    public final void setInsetDrawable(Drawable drawable) {
        this.insetDrawable = drawable;
        invalidate();
    }

    public final void setInsetColor(int i) {
        setInsetDrawable(new ColorDrawable(i));
    }

    public final Rect getInsets() {
        return this.insets;
    }

    public final void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.insets != null && this.insetDrawable != null) {
            int save = canvas.save();
            canvas.translate((float) getScrollX(), (float) getScrollY());
            this.insetDrawable.setBounds(0, 0, width, this.insets.top);
            this.insetDrawable.draw(canvas);
            this.insetDrawable.setBounds(0, height - this.insets.bottom, width, height);
            this.insetDrawable.draw(canvas);
            this.insetDrawable.setBounds(0, this.insets.top, this.insets.left, height - this.insets.bottom);
            this.insetDrawable.draw(canvas);
            this.insetDrawable.setBounds(width - this.insets.right, this.insets.top, width, height - this.insets.bottom);
            this.insetDrawable.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        this.insets = new Rect(rect);
        setWillNotDraw(this.insetDrawable == null);
        ViewCompat.postInvalidateOnAnimation(this);
        notifyOnInsetsChanged(rect);
        return true;
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.insetDrawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.insetDrawable;
        if (drawable != null) {
            drawable.setCallback((Drawable.Callback) null);
        }
    }
}
