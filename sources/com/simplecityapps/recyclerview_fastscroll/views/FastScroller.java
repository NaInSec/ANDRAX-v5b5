package com.simplecityapps.recyclerview_fastscroll.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.simplecityapps.recyclerview_fastscroll.R;
import com.simplecityapps.recyclerview_fastscroll.interfaces.OnFastScrollStateChangeListener;
import com.simplecityapps.recyclerview_fastscroll.utils.Utils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FastScroller {
    private static final int DEFAULT_AUTO_HIDE_DELAY = 1500;
    boolean mAnimatingShow;
    /* access modifiers changed from: private */
    public Animator mAutoHideAnimator;
    private int mAutoHideDelay = 1500;
    private boolean mAutoHideEnabled = true;
    private final Runnable mHideRunnable;
    private Rect mInvalidateRect = new Rect();
    private Rect mInvalidateTmpRect = new Rect();
    /* access modifiers changed from: private */
    public boolean mIsDragging;
    public Point mOffset = new Point(0, 0);
    private FastScrollPopup mPopup;
    /* access modifiers changed from: private */
    public FastScrollRecyclerView mRecyclerView;
    private Paint mThumb;
    private int mThumbHeight;
    public Point mThumbPosition = new Point(-1, -1);
    private Rect mTmpRect = new Rect();
    private int mTouchInset;
    private int mTouchOffset;
    private Paint mTrack;
    /* access modifiers changed from: private */
    public int mWidth;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FastScrollerPopupPosition {
        public static final int ADJACENT = 0;
        public static final int CENTER = 1;
    }

    /* JADX INFO: finally extract failed */
    public FastScroller(Context context, FastScrollRecyclerView fastScrollRecyclerView, AttributeSet attributeSet) {
        Resources resources = context.getResources();
        this.mRecyclerView = fastScrollRecyclerView;
        this.mPopup = new FastScrollPopup(resources, fastScrollRecyclerView);
        this.mThumbHeight = Utils.toPixels(resources, 48.0f);
        this.mWidth = Utils.toPixels(resources, 8.0f);
        this.mTouchInset = Utils.toPixels(resources, -24.0f);
        this.mThumb = new Paint(1);
        this.mTrack = new Paint(1);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.FastScrollRecyclerView, 0, 0);
        try {
            this.mAutoHideEnabled = obtainStyledAttributes.getBoolean(R.styleable.FastScrollRecyclerView_fastScrollAutoHide, true);
            this.mAutoHideDelay = obtainStyledAttributes.getInteger(R.styleable.FastScrollRecyclerView_fastScrollAutoHideDelay, 1500);
            int color = obtainStyledAttributes.getColor(R.styleable.FastScrollRecyclerView_fastScrollTrackColor, 520093696);
            int color2 = obtainStyledAttributes.getColor(R.styleable.FastScrollRecyclerView_fastScrollThumbColor, ViewCompat.MEASURED_STATE_MASK);
            int color3 = obtainStyledAttributes.getColor(R.styleable.FastScrollRecyclerView_fastScrollPopupBgColor, ViewCompat.MEASURED_STATE_MASK);
            int color4 = obtainStyledAttributes.getColor(R.styleable.FastScrollRecyclerView_fastScrollPopupTextColor, -1);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FastScrollRecyclerView_fastScrollPopupTextSize, Utils.toScreenPixels(resources, 56.0f));
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FastScrollRecyclerView_fastScrollPopupBackgroundSize, Utils.toPixels(resources, 88.0f));
            int integer = obtainStyledAttributes.getInteger(R.styleable.FastScrollRecyclerView_fastScrollPopupPosition, 0);
            this.mTrack.setColor(color);
            this.mThumb.setColor(color2);
            this.mPopup.setBgColor(color3);
            this.mPopup.setTextColor(color4);
            this.mPopup.setTextSize(dimensionPixelSize);
            this.mPopup.setBackgroundSize(dimensionPixelSize2);
            this.mPopup.setPopupPosition(integer);
            obtainStyledAttributes.recycle();
            this.mHideRunnable = new Runnable() {
                public void run() {
                    if (!FastScroller.this.mIsDragging) {
                        if (FastScroller.this.mAutoHideAnimator != null) {
                            FastScroller.this.mAutoHideAnimator.cancel();
                        }
                        FastScroller fastScroller = FastScroller.this;
                        int i = 1;
                        int[] iArr = new int[1];
                        if (Utils.isRtl(fastScroller.mRecyclerView.getResources())) {
                            i = -1;
                        }
                        iArr[0] = i * FastScroller.this.mWidth;
                        Animator unused = fastScroller.mAutoHideAnimator = ObjectAnimator.ofInt(fastScroller, "offsetX", iArr);
                        FastScroller.this.mAutoHideAnimator.setInterpolator(new FastOutLinearInInterpolator());
                        FastScroller.this.mAutoHideAnimator.setDuration(200);
                        FastScroller.this.mAutoHideAnimator.start();
                    }
                }
            };
            this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    if (!FastScroller.this.mRecyclerView.isInEditMode()) {
                        FastScroller.this.show();
                    }
                }
            });
            if (this.mAutoHideEnabled) {
                postAutoHideDelayed();
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public int getThumbHeight() {
        return this.mThumbHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isDragging() {
        return this.mIsDragging;
    }

    public void handleTouchEvent(MotionEvent motionEvent, int i, int i2, int i3, OnFastScrollStateChangeListener onFastScrollStateChangeListener) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this.mRecyclerView.getContext());
        int action = motionEvent.getAction();
        int y = (int) motionEvent.getY();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    if (!this.mIsDragging && isNearPoint(i, i2) && Math.abs(y - i2) > viewConfiguration.getScaledTouchSlop()) {
                        this.mRecyclerView.getParent().requestDisallowInterceptTouchEvent(true);
                        this.mIsDragging = true;
                        this.mTouchOffset += i3 - i2;
                        this.mPopup.animateVisibility(true);
                        if (onFastScrollStateChangeListener != null) {
                            onFastScrollStateChangeListener.onFastScrollStart();
                        }
                    }
                    if (this.mIsDragging) {
                        int height = this.mRecyclerView.getHeight() - this.mThumbHeight;
                        String scrollToPositionAtProgress = this.mRecyclerView.scrollToPositionAtProgress((((float) Math.max(0, Math.min(height, y - this.mTouchOffset))) - ((float) 0)) / ((float) (height - 0)));
                        this.mPopup.setSectionName(scrollToPositionAtProgress);
                        this.mPopup.animateVisibility(!scrollToPositionAtProgress.isEmpty());
                        FastScrollRecyclerView fastScrollRecyclerView = this.mRecyclerView;
                        fastScrollRecyclerView.invalidate(this.mPopup.updateFastScrollerBounds(fastScrollRecyclerView, this.mThumbPosition.y));
                        return;
                    }
                    return;
                } else if (action != 3) {
                    return;
                }
            }
            this.mTouchOffset = 0;
            if (this.mIsDragging) {
                this.mIsDragging = false;
                this.mPopup.animateVisibility(false);
                if (onFastScrollStateChangeListener != null) {
                    onFastScrollStateChangeListener.onFastScrollStop();
                }
            }
        } else if (isNearPoint(i, i2)) {
            this.mTouchOffset = i2 - this.mThumbPosition.y;
        }
    }

    public void draw(Canvas canvas) {
        if (this.mThumbPosition.x >= 0 && this.mThumbPosition.y >= 0) {
            canvas.drawRect((float) (this.mThumbPosition.x + this.mOffset.x), (float) ((this.mThumbHeight / 2) + this.mOffset.y), (float) (this.mThumbPosition.x + this.mOffset.x + this.mWidth), (float) ((this.mRecyclerView.getHeight() + this.mOffset.y) - (this.mThumbHeight / 2)), this.mTrack);
            canvas.drawRect((float) (this.mThumbPosition.x + this.mOffset.x), (float) (this.mThumbPosition.y + this.mOffset.y), (float) (this.mThumbPosition.x + this.mOffset.x + this.mWidth), (float) (this.mThumbPosition.y + this.mOffset.y + this.mThumbHeight), this.mThumb);
            this.mPopup.draw(canvas);
        }
    }

    private boolean isNearPoint(int i, int i2) {
        this.mTmpRect.set(this.mThumbPosition.x, this.mThumbPosition.y, this.mThumbPosition.x + this.mWidth, this.mThumbPosition.y + this.mThumbHeight);
        Rect rect = this.mTmpRect;
        int i3 = this.mTouchInset;
        rect.inset(i3, i3);
        return this.mTmpRect.contains(i, i2);
    }

    public void setThumbPosition(int i, int i2) {
        if (this.mThumbPosition.x != i || this.mThumbPosition.y != i2) {
            this.mInvalidateRect.set(this.mThumbPosition.x + this.mOffset.x, this.mOffset.y, this.mThumbPosition.x + this.mOffset.x + this.mWidth, this.mRecyclerView.getHeight() + this.mOffset.y);
            this.mThumbPosition.set(i, i2);
            this.mInvalidateTmpRect.set(this.mThumbPosition.x + this.mOffset.x, this.mOffset.y, this.mThumbPosition.x + this.mOffset.x + this.mWidth, this.mRecyclerView.getHeight() + this.mOffset.y);
            this.mInvalidateRect.union(this.mInvalidateTmpRect);
            this.mRecyclerView.invalidate(this.mInvalidateRect);
        }
    }

    public void setOffset(int i, int i2) {
        if (this.mOffset.x != i || this.mOffset.y != i2) {
            this.mInvalidateRect.set(this.mThumbPosition.x + this.mOffset.x, this.mOffset.y, this.mThumbPosition.x + this.mOffset.x + this.mWidth, this.mRecyclerView.getHeight() + this.mOffset.y);
            this.mOffset.set(i, i2);
            this.mInvalidateTmpRect.set(this.mThumbPosition.x + this.mOffset.x, this.mOffset.y, this.mThumbPosition.x + this.mOffset.x + this.mWidth, this.mRecyclerView.getHeight() + this.mOffset.y);
            this.mInvalidateRect.union(this.mInvalidateTmpRect);
            this.mRecyclerView.invalidate(this.mInvalidateRect);
        }
    }

    public void setOffsetX(int i) {
        setOffset(i, this.mOffset.y);
    }

    public int getOffsetX() {
        return this.mOffset.x;
    }

    public void show() {
        if (!this.mAnimatingShow) {
            Animator animator = this.mAutoHideAnimator;
            if (animator != null) {
                animator.cancel();
            }
            this.mAutoHideAnimator = ObjectAnimator.ofInt(this, "offsetX", new int[]{0});
            this.mAutoHideAnimator.setInterpolator(new LinearOutSlowInInterpolator());
            this.mAutoHideAnimator.setDuration(150);
            this.mAutoHideAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator animator) {
                    super.onAnimationCancel(animator);
                    FastScroller.this.mAnimatingShow = false;
                }

                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    FastScroller.this.mAnimatingShow = false;
                }
            });
            this.mAnimatingShow = true;
            this.mAutoHideAnimator.start();
        }
        if (this.mAutoHideEnabled) {
            postAutoHideDelayed();
        } else {
            cancelAutoHide();
        }
    }

    /* access modifiers changed from: protected */
    public void postAutoHideDelayed() {
        if (this.mRecyclerView != null) {
            cancelAutoHide();
            this.mRecyclerView.postDelayed(this.mHideRunnable, (long) this.mAutoHideDelay);
        }
    }

    /* access modifiers changed from: protected */
    public void cancelAutoHide() {
        FastScrollRecyclerView fastScrollRecyclerView = this.mRecyclerView;
        if (fastScrollRecyclerView != null) {
            fastScrollRecyclerView.removeCallbacks(this.mHideRunnable);
        }
    }

    public void setThumbColor(int i) {
        this.mThumb.setColor(i);
        this.mRecyclerView.invalidate(this.mInvalidateRect);
    }

    public void setTrackColor(int i) {
        this.mTrack.setColor(i);
        this.mRecyclerView.invalidate(this.mInvalidateRect);
    }

    public void setPopupBgColor(int i) {
        this.mPopup.setBgColor(i);
    }

    public void setPopupTextColor(int i) {
        this.mPopup.setTextColor(i);
    }

    public void setPopupTypeface(Typeface typeface) {
        this.mPopup.setTypeface(typeface);
    }

    public void setPopupTextSize(int i) {
        this.mPopup.setTextSize(i);
    }

    public void setAutoHideDelay(int i) {
        this.mAutoHideDelay = i;
        if (this.mAutoHideEnabled) {
            postAutoHideDelayed();
        }
    }

    public void setAutoHideEnabled(boolean z) {
        this.mAutoHideEnabled = z;
        if (z) {
            postAutoHideDelayed();
        } else {
            cancelAutoHide();
        }
    }

    public void setPopupPosition(int i) {
        this.mPopup.setPopupPosition(i);
    }
}
