package com.simplecityapps.recyclerview_fastscroll.views;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import com.simplecityapps.recyclerview_fastscroll.utils.Utils;

public class FastScrollPopup {
    private float mAlpha = 1.0f;
    private ObjectAnimator mAlphaAnimator;
    private int mBackgroundColor = ViewCompat.MEASURED_STATE_MASK;
    private Paint mBackgroundPaint;
    private Path mBackgroundPath = new Path();
    private RectF mBackgroundRect = new RectF();
    private int mBackgroundSize;
    private Rect mBgBounds = new Rect();
    private int mCornerRadius;
    private Rect mInvalidateRect = new Rect();
    private int mPosition;
    private FastScrollRecyclerView mRecyclerView;
    private Resources mRes;
    private String mSectionName;
    private Rect mTextBounds = new Rect();
    private Paint mTextPaint;
    private Rect mTmpRect = new Rect();
    private boolean mVisible;

    public FastScrollPopup(Resources resources, FastScrollRecyclerView fastScrollRecyclerView) {
        this.mRes = resources;
        this.mRecyclerView = fastScrollRecyclerView;
        this.mBackgroundPaint = new Paint(1);
        this.mTextPaint = new Paint(1);
        this.mTextPaint.setAlpha(0);
        setTextSize(Utils.toScreenPixels(this.mRes, 56.0f));
        setBackgroundSize(Utils.toPixels(this.mRes, 88.0f));
    }

    public void setBgColor(int i) {
        this.mBackgroundColor = i;
        this.mBackgroundPaint.setColor(i);
        this.mRecyclerView.invalidate(this.mBgBounds);
    }

    public void setTextColor(int i) {
        this.mTextPaint.setColor(i);
        this.mRecyclerView.invalidate(this.mBgBounds);
    }

    public void setTextSize(int i) {
        this.mTextPaint.setTextSize((float) i);
        this.mRecyclerView.invalidate(this.mBgBounds);
    }

    public void setBackgroundSize(int i) {
        this.mBackgroundSize = i;
        this.mCornerRadius = this.mBackgroundSize / 2;
        this.mRecyclerView.invalidate(this.mBgBounds);
    }

    public void setTypeface(Typeface typeface) {
        this.mTextPaint.setTypeface(typeface);
        this.mRecyclerView.invalidate(this.mBgBounds);
    }

    public void animateVisibility(boolean z) {
        if (this.mVisible != z) {
            this.mVisible = z;
            ObjectAnimator objectAnimator = this.mAlphaAnimator;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            float[] fArr = new float[1];
            fArr[0] = z ? 1.0f : 0.0f;
            this.mAlphaAnimator = ObjectAnimator.ofFloat(this, "alpha", fArr);
            this.mAlphaAnimator.setDuration(z ? 200 : 150);
            this.mAlphaAnimator.start();
        }
    }

    public void setAlpha(float f) {
        this.mAlpha = f;
        this.mRecyclerView.invalidate(this.mBgBounds);
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public void setPopupPosition(int i) {
        this.mPosition = i;
    }

    public int getPopupPosition() {
        return this.mPosition;
    }

    private float[] createRadii() {
        if (this.mPosition == 1) {
            int i = this.mCornerRadius;
            return new float[]{(float) i, (float) i, (float) i, (float) i, (float) i, (float) i, (float) i, (float) i};
        } else if (Utils.isRtl(this.mRes)) {
            int i2 = this.mCornerRadius;
            return new float[]{(float) i2, (float) i2, (float) i2, (float) i2, (float) i2, (float) i2, 0.0f, 0.0f};
        } else {
            int i3 = this.mCornerRadius;
            return new float[]{(float) i3, (float) i3, (float) i3, (float) i3, 0.0f, 0.0f, (float) i3, (float) i3};
        }
    }

    public void draw(Canvas canvas) {
        if (isVisible()) {
            int save = canvas.save(1);
            canvas.translate((float) this.mBgBounds.left, (float) this.mBgBounds.top);
            this.mTmpRect.set(this.mBgBounds);
            this.mTmpRect.offsetTo(0, 0);
            this.mBackgroundPath.reset();
            this.mBackgroundRect.set(this.mTmpRect);
            this.mBackgroundPath.addRoundRect(this.mBackgroundRect, createRadii(), Path.Direction.CW);
            this.mBackgroundPaint.setAlpha((int) (((float) Color.alpha(this.mBackgroundColor)) * this.mAlpha));
            this.mTextPaint.setAlpha((int) (this.mAlpha * 255.0f));
            canvas.drawPath(this.mBackgroundPath, this.mBackgroundPaint);
            canvas.drawText(this.mSectionName, (float) ((this.mBgBounds.width() - this.mTextBounds.width()) / 2), (float) (this.mBgBounds.height() - ((this.mBgBounds.height() - this.mTextBounds.height()) / 2)), this.mTextPaint);
            canvas.restoreToCount(save);
        }
    }

    public void setSectionName(String str) {
        if (!str.equals(this.mSectionName)) {
            this.mSectionName = str;
            this.mTextPaint.getTextBounds(str, 0, str.length(), this.mTextBounds);
            Rect rect = this.mTextBounds;
            rect.right = (int) (((float) rect.left) + this.mTextPaint.measureText(str));
        }
    }

    public Rect updateFastScrollerBounds(FastScrollRecyclerView fastScrollRecyclerView, int i) {
        this.mInvalidateRect.set(this.mBgBounds);
        if (isVisible()) {
            int scrollBarWidth = fastScrollRecyclerView.getScrollBarWidth();
            int i2 = this.mBackgroundSize;
            int max = Math.max(i2, this.mTextBounds.width() + (((this.mBackgroundSize - this.mTextBounds.height()) / 2) * 2));
            if (this.mPosition == 1) {
                this.mBgBounds.left = (fastScrollRecyclerView.getWidth() - max) / 2;
                Rect rect = this.mBgBounds;
                rect.right = rect.left + max;
                this.mBgBounds.top = (fastScrollRecyclerView.getHeight() - i2) / 2;
            } else {
                if (Utils.isRtl(this.mRes)) {
                    this.mBgBounds.left = fastScrollRecyclerView.getScrollBarWidth() * 2;
                    Rect rect2 = this.mBgBounds;
                    rect2.right = rect2.left + max;
                } else {
                    this.mBgBounds.right = fastScrollRecyclerView.getWidth() - (fastScrollRecyclerView.getScrollBarWidth() * 2);
                    Rect rect3 = this.mBgBounds;
                    rect3.left = rect3.right - max;
                }
                this.mBgBounds.top = (i - i2) + (fastScrollRecyclerView.getScrollBarThumbHeight() / 2);
                Rect rect4 = this.mBgBounds;
                rect4.top = Math.max(scrollBarWidth, Math.min(rect4.top, (fastScrollRecyclerView.getHeight() - scrollBarWidth) - i2));
            }
            Rect rect5 = this.mBgBounds;
            rect5.bottom = rect5.top + i2;
        } else {
            this.mBgBounds.setEmpty();
        }
        this.mInvalidateRect.union(this.mBgBounds);
        return this.mInvalidateRect;
    }

    public boolean isVisible() {
        return this.mAlpha > 0.0f && !TextUtils.isEmpty(this.mSectionName);
    }
}
