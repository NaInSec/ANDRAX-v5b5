package de.mrapp.android.tabswitcher.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import de.mrapp.android.tabswitcher.Animation;
import de.mrapp.android.tabswitcher.R;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.TabSwitcherListener;
import de.mrapp.android.util.Condition;
import de.mrapp.android.util.ThemeUtil;

public class TabSwitcherDrawable extends Drawable implements TabSwitcherListener {
    private final Drawable background;
    private String label;
    private final Paint paint = new Paint(1);
    private final int size;
    private final int textSizeNormal;
    private final int textSizeSmall;

    public final int getOpacity() {
        return -3;
    }

    public final void onSelectionChanged(TabSwitcher tabSwitcher, int i, Tab tab) {
    }

    public final void onSwitcherHidden(TabSwitcher tabSwitcher) {
    }

    public final void onSwitcherShown(TabSwitcher tabSwitcher) {
    }

    public TabSwitcherDrawable(Context context) {
        Condition.ensureNotNull(context, "The context may not be null");
        Resources resources = context.getResources();
        this.size = resources.getDimensionPixelSize(R.dimen.tab_switcher_drawable_size);
        this.textSizeNormal = resources.getDimensionPixelSize(R.dimen.tab_switcher_drawable_font_size_normal);
        this.textSizeSmall = resources.getDimensionPixelSize(R.dimen.tab_switcher_drawable_font_size_small);
        this.background = ContextCompat.getDrawable(context, R.drawable.tab_switcher_drawable_background).mutate();
        this.paint.setColor(-1);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setTextSize((float) this.textSizeNormal);
        this.paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 1));
        this.label = Integer.toString(0);
        setColorFilter(ThemeUtil.getColor(context, 16842806), PorterDuff.Mode.MULTIPLY);
    }

    public final void setCount(int i) {
        Condition.ensureAtLeast(i, 0, "The count must be at least 0");
        this.label = Integer.toString(i);
        if (this.label.length() > 2) {
            this.label = "99+";
            this.paint.setTextSize((float) this.textSizeSmall);
        } else {
            this.paint.setTextSize((float) this.textSizeNormal);
        }
        invalidateSelf();
    }

    public final void draw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int intrinsicWidth = this.background.getIntrinsicWidth();
        int intrinsicHeight = this.background.getIntrinsicHeight();
        int i = (width / 2) - (intrinsicWidth / 2);
        int i2 = (height / 2) - (intrinsicHeight / 2);
        this.background.getIntrinsicWidth();
        this.background.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
        this.background.draw(canvas);
        canvas.drawText(this.label, ((float) width) / 2.0f, (((float) height) / 2.0f) - ((this.paint.descent() + this.paint.ascent()) / 2.0f), this.paint);
    }

    public final int getIntrinsicWidth() {
        return this.size;
    }

    public final int getIntrinsicHeight() {
        return this.size;
    }

    public final void setAlpha(int i) {
        this.background.setAlpha(i);
        this.paint.setAlpha(i);
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.background.setColorFilter(colorFilter);
        this.paint.setColorFilter(colorFilter);
    }

    public final void onTabAdded(TabSwitcher tabSwitcher, int i, Tab tab, Animation animation) {
        setCount(tabSwitcher.getCount());
    }

    public final void onTabRemoved(TabSwitcher tabSwitcher, int i, Tab tab, Animation animation) {
        setCount(tabSwitcher.getCount());
    }

    public final void onAllTabsRemoved(TabSwitcher tabSwitcher, Tab[] tabArr, Animation animation) {
        setCount(tabSwitcher.getCount());
    }
}
