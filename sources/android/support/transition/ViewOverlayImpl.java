package android.support.transition;

import android.graphics.drawable.Drawable;

interface ViewOverlayImpl {
    void add(Drawable drawable);

    void clear();

    void remove(Drawable drawable);
}
