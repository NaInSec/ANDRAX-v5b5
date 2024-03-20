package com.bumptech.glide.request.target;

import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.bumptech.glide.request.Request;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class ViewTarget<T extends View, Z> extends BaseTarget<Z> {
    private static final String TAG = "ViewTarget";
    private static boolean isTagUsedAtLeastOnce = false;
    private static Integer tagId;
    private final SizeDeterminer sizeDeterminer;
    protected final T view;

    public static void setTagId(int i) {
        if (tagId != null || isTagUsedAtLeastOnce) {
            throw new IllegalArgumentException("You cannot set the tag id more than once or change the tag id after the first request has been made");
        }
        tagId = Integer.valueOf(i);
    }

    public ViewTarget(T t) {
        if (t != null) {
            this.view = t;
            this.sizeDeterminer = new SizeDeterminer(t);
            return;
        }
        throw new NullPointerException("View must not be null!");
    }

    public T getView() {
        return this.view;
    }

    public void getSize(SizeReadyCallback sizeReadyCallback) {
        this.sizeDeterminer.getSize(sizeReadyCallback);
    }

    public void setRequest(Request request) {
        setTag(request);
    }

    public Request getRequest() {
        Object tag = getTag();
        if (tag == null) {
            return null;
        }
        if (tag instanceof Request) {
            return (Request) tag;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    private void setTag(Object obj) {
        Integer num = tagId;
        if (num == null) {
            isTagUsedAtLeastOnce = true;
            this.view.setTag(obj);
            return;
        }
        this.view.setTag(num.intValue(), obj);
    }

    private Object getTag() {
        Integer num = tagId;
        if (num == null) {
            return this.view.getTag();
        }
        return this.view.getTag(num.intValue());
    }

    public String toString() {
        return "Target for: " + this.view;
    }

    private static class SizeDeterminer {
        private static final int PENDING_SIZE = 0;
        private final List<SizeReadyCallback> cbs = new ArrayList();
        private Point displayDimens;
        private SizeDeterminerLayoutListener layoutListener;
        private final View view;

        private boolean isSizeValid(int i) {
            return i > 0 || i == -2;
        }

        public SizeDeterminer(View view2) {
            this.view = view2;
        }

        private void notifyCbs(int i, int i2) {
            for (SizeReadyCallback onSizeReady : this.cbs) {
                onSizeReady.onSizeReady(i, i2);
            }
            this.cbs.clear();
        }

        /* access modifiers changed from: private */
        public void checkCurrentDimens() {
            if (!this.cbs.isEmpty()) {
                int viewWidthOrParam = getViewWidthOrParam();
                int viewHeightOrParam = getViewHeightOrParam();
                if (isSizeValid(viewWidthOrParam) && isSizeValid(viewHeightOrParam)) {
                    notifyCbs(viewWidthOrParam, viewHeightOrParam);
                    ViewTreeObserver viewTreeObserver = this.view.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnPreDrawListener(this.layoutListener);
                    }
                    this.layoutListener = null;
                }
            }
        }

        public void getSize(SizeReadyCallback sizeReadyCallback) {
            int viewWidthOrParam = getViewWidthOrParam();
            int viewHeightOrParam = getViewHeightOrParam();
            if (!isSizeValid(viewWidthOrParam) || !isSizeValid(viewHeightOrParam)) {
                if (!this.cbs.contains(sizeReadyCallback)) {
                    this.cbs.add(sizeReadyCallback);
                }
                if (this.layoutListener == null) {
                    ViewTreeObserver viewTreeObserver = this.view.getViewTreeObserver();
                    this.layoutListener = new SizeDeterminerLayoutListener(this);
                    viewTreeObserver.addOnPreDrawListener(this.layoutListener);
                    return;
                }
                return;
            }
            sizeReadyCallback.onSizeReady(viewWidthOrParam, viewHeightOrParam);
        }

        private int getViewHeightOrParam() {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (isSizeValid(this.view.getHeight())) {
                return this.view.getHeight();
            }
            if (layoutParams != null) {
                return getSizeForParam(layoutParams.height, true);
            }
            return 0;
        }

        private int getViewWidthOrParam() {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (isSizeValid(this.view.getWidth())) {
                return this.view.getWidth();
            }
            if (layoutParams != null) {
                return getSizeForParam(layoutParams.width, false);
            }
            return 0;
        }

        private int getSizeForParam(int i, boolean z) {
            if (i != -2) {
                return i;
            }
            Point displayDimens2 = getDisplayDimens();
            return z ? displayDimens2.y : displayDimens2.x;
        }

        private Point getDisplayDimens() {
            Point point = this.displayDimens;
            if (point != null) {
                return point;
            }
            Display defaultDisplay = ((WindowManager) this.view.getContext().getSystemService("window")).getDefaultDisplay();
            if (Build.VERSION.SDK_INT >= 13) {
                this.displayDimens = new Point();
                defaultDisplay.getSize(this.displayDimens);
            } else {
                this.displayDimens = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
            }
            return this.displayDimens;
        }

        private static class SizeDeterminerLayoutListener implements ViewTreeObserver.OnPreDrawListener {
            private final WeakReference<SizeDeterminer> sizeDeterminerRef;

            public SizeDeterminerLayoutListener(SizeDeterminer sizeDeterminer) {
                this.sizeDeterminerRef = new WeakReference<>(sizeDeterminer);
            }

            public boolean onPreDraw() {
                if (Log.isLoggable(ViewTarget.TAG, 2)) {
                    Log.v(ViewTarget.TAG, "OnGlobalLayoutListener called listener=" + this);
                }
                SizeDeterminer sizeDeterminer = (SizeDeterminer) this.sizeDeterminerRef.get();
                if (sizeDeterminer == null) {
                    return true;
                }
                sizeDeterminer.checkCurrentDimens();
                return true;
            }
        }
    }
}
