package android.support.design.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.design.circularreveal.CircularRevealWidget;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CircularRevealRelativeLayout extends RelativeLayout implements CircularRevealWidget {
    private final CircularRevealHelper helper;

    public CircularRevealRelativeLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public CircularRevealRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.helper = new CircularRevealHelper(this);
    }

    public void buildCircularRevealCache() {
        this.helper.buildCircularRevealCache();
    }

    public void destroyCircularRevealCache() {
        this.helper.destroyCircularRevealCache();
    }

    public CircularRevealWidget.RevealInfo getRevealInfo() {
        return this.helper.getRevealInfo();
    }

    public void setRevealInfo(CircularRevealWidget.RevealInfo revealInfo) {
        this.helper.setRevealInfo(revealInfo);
    }

    public int getCircularRevealScrimColor() {
        return this.helper.getCircularRevealScrimColor();
    }

    public void setCircularRevealScrimColor(int i) {
        this.helper.setCircularRevealScrimColor(i);
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.helper.getCircularRevealOverlayDrawable();
    }

    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.helper.setCircularRevealOverlayDrawable(drawable);
    }

    public void draw(Canvas canvas) {
        CircularRevealHelper circularRevealHelper = this.helper;
        if (circularRevealHelper != null) {
            circularRevealHelper.draw(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public void actualDraw(Canvas canvas) {
        super.draw(canvas);
    }

    public boolean isOpaque() {
        CircularRevealHelper circularRevealHelper = this.helper;
        if (circularRevealHelper != null) {
            return circularRevealHelper.isOpaque();
        }
        return super.isOpaque();
    }

    public boolean actualIsOpaque() {
        return super.isOpaque();
    }
}
