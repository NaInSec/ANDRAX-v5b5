package de.mrapp.android.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.DisplayUtil;
import de.mrapp.android.util.ElevationUtil;
import de.mrapp.android.util.R;

public class ElevationShadowView extends AppCompatImageView {
    private int elevation;
    private boolean emulateParallelLight;
    private ElevationUtil.Orientation orientation;

    private void initialize(AttributeSet attributeSet, int i, int i2) {
        obtainStyledAttributes(attributeSet, i, i2);
        adaptElevationShadow();
    }

    private void obtainStyledAttributes(AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ElevationShadowView, i, i2);
        try {
            obtainShadowElevation(obtainStyledAttributes);
            obtainShadowOrientation(obtainStyledAttributes);
            obtainEmulateParallelLight(obtainStyledAttributes);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void obtainShadowElevation(TypedArray typedArray) {
        this.elevation = DisplayUtil.pixelsToDp(getContext(), typedArray.getDimensionPixelSize(R.styleable.ElevationShadowView_shadowElevation, getResources().getDimensionPixelSize(R.dimen.elevation_shadow_view_shadow_elevation_default_value)));
    }

    private void obtainShadowOrientation(TypedArray typedArray) {
        this.orientation = ElevationUtil.Orientation.fromValue(typedArray.getInteger(R.styleable.ElevationShadowView_shadowOrientation, getResources().getInteger(R.integer.elevation_shadow_view_shadow_orientation_default_value)));
    }

    private void obtainEmulateParallelLight(TypedArray typedArray) {
        this.emulateParallelLight = typedArray.getBoolean(R.styleable.ElevationShadowView_emulateParallelLight, getResources().getBoolean(R.bool.elevation_shadow_view_emulate_parallel_light_default_value));
    }

    private void adaptElevationShadow() {
        setImageBitmap(ElevationUtil.createElevationShadow(getContext(), this.elevation, this.orientation, this.emulateParallelLight));
        setScaleType((this.orientation == ElevationUtil.Orientation.LEFT || this.orientation == ElevationUtil.Orientation.TOP || this.orientation == ElevationUtil.Orientation.RIGHT || this.orientation == ElevationUtil.Orientation.BOTTOM) ? ImageView.ScaleType.FIT_XY : ImageView.ScaleType.FIT_CENTER);
    }

    public ElevationShadowView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ElevationShadowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(attributeSet, 0, 0);
    }

    public ElevationShadowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(attributeSet, i, 0);
    }

    public final int getShadowElevation() {
        return this.elevation;
    }

    public final void setShadowElevation(int i) {
        Condition.ensureAtLeast(i, 0, "The elevation must be at least 0");
        Condition.ensureAtMaximum(i, 16, "The elevation must be at maximum 16");
        this.elevation = i;
        adaptElevationShadow();
    }

    public final ElevationUtil.Orientation getShadowOrientation() {
        return this.orientation;
    }

    public final void setShadowOrientation(ElevationUtil.Orientation orientation2) {
        Condition.ensureNotNull(orientation2, "The orientation may not be null");
        this.orientation = orientation2;
        adaptElevationShadow();
    }

    public final boolean isParallelLightEmulated() {
        return this.emulateParallelLight;
    }

    public final void emulateParallelLight(boolean z) {
        this.emulateParallelLight = z;
        adaptElevationShadow();
    }
}
