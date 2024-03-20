package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.Resource;

public abstract class DrawableResource<T extends Drawable> implements Resource<T> {
    protected final T drawable;

    public DrawableResource(T t) {
        if (t != null) {
            this.drawable = t;
            return;
        }
        throw new NullPointerException("Drawable must not be null!");
    }

    public final T get() {
        return this.drawable.getConstantState().newDrawable();
    }
}
