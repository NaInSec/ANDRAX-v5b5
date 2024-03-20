package com.simplecityapps.recyclerview_fastscroll.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import com.simplecityapps.recyclerview_fastscroll.interfaces.OnFastScrollStateChangeListener;
import com.simplecityapps.recyclerview_fastscroll.utils.Utils;

public class FastScrollRecyclerView extends RecyclerView implements RecyclerView.OnItemTouchListener {
    private int mDownX;
    private int mDownY;
    private int mLastY;
    private ScrollOffsetInvalidator mScrollOffsetInvalidator;
    /* access modifiers changed from: private */
    public SparseIntArray mScrollOffsets;
    private ScrollPositionState mScrollPosState;
    private FastScroller mScrollbar;
    private OnFastScrollStateChangeListener mStateChangeListener;

    public interface MeasurableAdapter {
        int getViewTypeHeight(RecyclerView recyclerView, int i);
    }

    public static class ScrollPositionState {
        public int rowHeight;
        public int rowIndex;
        public int rowTopOffset;
    }

    public interface SectionedAdapter {
        String getSectionName(int i);
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    public FastScrollRecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FastScrollRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FastScrollRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScrollPosState = new ScrollPositionState();
        this.mScrollbar = new FastScroller(context, this, attributeSet);
        this.mScrollOffsetInvalidator = new ScrollOffsetInvalidator();
        this.mScrollOffsets = new SparseIntArray();
    }

    public int getScrollBarWidth() {
        return this.mScrollbar.getWidth();
    }

    public int getScrollBarThumbHeight() {
        return this.mScrollbar.getThumbHeight();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        addOnItemTouchListener(this);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (getAdapter() != null) {
            getAdapter().unregisterAdapterDataObserver(this.mScrollOffsetInvalidator);
        }
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mScrollOffsetInvalidator);
        }
        super.setAdapter(adapter);
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        return handleTouchEvent(motionEvent);
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        handleTouchEvent(motionEvent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r0 != 3) goto L_0x004e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean handleTouchEvent(android.view.MotionEvent r15) {
        /*
            r14 = this;
            int r0 = r15.getAction()
            float r1 = r15.getX()
            int r1 = (int) r1
            float r2 = r15.getY()
            int r2 = (int) r2
            if (r0 == 0) goto L_0x003a
            r1 = 1
            if (r0 == r1) goto L_0x002b
            r1 = 2
            if (r0 == r1) goto L_0x001a
            r1 = 3
            if (r0 == r1) goto L_0x002b
            goto L_0x004e
        L_0x001a:
            r14.mLastY = r2
            com.simplecityapps.recyclerview_fastscroll.views.FastScroller r2 = r14.mScrollbar
            int r4 = r14.mDownX
            int r5 = r14.mDownY
            int r6 = r14.mLastY
            com.simplecityapps.recyclerview_fastscroll.interfaces.OnFastScrollStateChangeListener r7 = r14.mStateChangeListener
            r3 = r15
            r2.handleTouchEvent(r3, r4, r5, r6, r7)
            goto L_0x004e
        L_0x002b:
            com.simplecityapps.recyclerview_fastscroll.views.FastScroller r8 = r14.mScrollbar
            int r10 = r14.mDownX
            int r11 = r14.mDownY
            int r12 = r14.mLastY
            com.simplecityapps.recyclerview_fastscroll.interfaces.OnFastScrollStateChangeListener r13 = r14.mStateChangeListener
            r9 = r15
            r8.handleTouchEvent(r9, r10, r11, r12, r13)
            goto L_0x004e
        L_0x003a:
            r14.mDownX = r1
            r14.mLastY = r2
            r14.mDownY = r2
            com.simplecityapps.recyclerview_fastscroll.views.FastScroller r0 = r14.mScrollbar
            int r2 = r14.mDownX
            int r3 = r14.mDownY
            int r4 = r14.mLastY
            com.simplecityapps.recyclerview_fastscroll.interfaces.OnFastScrollStateChangeListener r5 = r14.mStateChangeListener
            r1 = r15
            r0.handleTouchEvent(r1, r2, r3, r4, r5)
        L_0x004e:
            com.simplecityapps.recyclerview_fastscroll.views.FastScroller r15 = r14.mScrollbar
            boolean r15 = r15.isDragging()
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView.handleTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public int getAvailableScrollHeight(int i, int i2, int i3) {
        return getAvailableScrollHeight(i * i2, i3);
    }

    /* access modifiers changed from: protected */
    public int getAvailableScrollHeight(int i, int i2) {
        return (((getPaddingTop() + i2) + i) + getPaddingBottom()) - getHeight();
    }

    /* access modifiers changed from: protected */
    public int getAvailableScrollBarHeight() {
        return getHeight() - this.mScrollbar.getThumbHeight();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        onUpdateScrollbar();
        this.mScrollbar.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void updateThumbPosition(ScrollPositionState scrollPositionState, int i, int i2) {
        int i3;
        int availableScrollHeight = getAvailableScrollHeight(i, scrollPositionState.rowHeight, i2);
        int availableScrollBarHeight = getAvailableScrollBarHeight();
        if (availableScrollHeight <= 0) {
            this.mScrollbar.setThumbPosition(-1, -1);
            return;
        }
        int paddingTop = (int) ((((float) (((getPaddingTop() + i2) + (scrollPositionState.rowIndex * scrollPositionState.rowHeight)) - scrollPositionState.rowTopOffset)) / ((float) availableScrollHeight)) * ((float) availableScrollBarHeight));
        if (Utils.isRtl(getResources())) {
            i3 = 0;
        } else {
            i3 = getWidth() - this.mScrollbar.getWidth();
        }
        this.mScrollbar.setThumbPosition(i3, paddingTop);
    }

    /* access modifiers changed from: protected */
    public void updateThumbPositionWithMeasurableAdapter(ScrollPositionState scrollPositionState, int i) {
        int i2;
        int availableScrollHeight = getAvailableScrollHeight(calculateAdapterHeight(), i);
        int availableScrollBarHeight = getAvailableScrollBarHeight();
        if (availableScrollHeight <= 0) {
            this.mScrollbar.setThumbPosition(-1, -1);
            return;
        }
        int paddingTop = (int) ((((float) (((getPaddingTop() + i) + calculateScrollDistanceToPosition(scrollPositionState.rowIndex)) - scrollPositionState.rowTopOffset)) / ((float) availableScrollHeight)) * ((float) availableScrollBarHeight));
        if (Utils.isRtl(getResources())) {
            i2 = 0;
        } else {
            i2 = getWidth() - this.mScrollbar.getWidth();
        }
        this.mScrollbar.setThumbPosition(i2, paddingTop);
    }

    public String scrollToPositionAtProgress(float f) {
        int i;
        int itemCount = getAdapter().getItemCount();
        if (itemCount == 0) {
            return "";
        }
        int i2 = 1;
        if (getLayoutManager() instanceof GridLayoutManager) {
            i2 = ((GridLayoutManager) getLayoutManager()).getSpanCount();
            i = (int) Math.ceil(((double) itemCount) / ((double) i2));
        } else {
            i = itemCount;
        }
        stopScroll();
        getCurScrollState(this.mScrollPosState);
        float f2 = ((float) itemCount) * f;
        int availableScrollHeight = (int) (((float) getAvailableScrollHeight(i, this.mScrollPosState.rowHeight, 0)) * f);
        ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset((i2 * availableScrollHeight) / this.mScrollPosState.rowHeight, -(availableScrollHeight % this.mScrollPosState.rowHeight));
        if (!(getAdapter() instanceof SectionedAdapter)) {
            return "";
        }
        if (f == 1.0f) {
            f2 -= 1.0f;
        }
        return ((SectionedAdapter) getAdapter()).getSectionName((int) f2);
    }

    public void onUpdateScrollbar() {
        if (getAdapter() != null) {
            int itemCount = getAdapter().getItemCount();
            if (getLayoutManager() instanceof GridLayoutManager) {
                itemCount = (int) Math.ceil(((double) itemCount) / ((double) ((GridLayoutManager) getLayoutManager()).getSpanCount()));
            }
            if (itemCount == 0) {
                this.mScrollbar.setThumbPosition(-1, -1);
                return;
            }
            getCurScrollState(this.mScrollPosState);
            if (this.mScrollPosState.rowIndex < 0) {
                this.mScrollbar.setThumbPosition(-1, -1);
            } else if (getAdapter() instanceof MeasurableAdapter) {
                updateThumbPositionWithMeasurableAdapter(this.mScrollPosState, 0);
            } else {
                updateThumbPosition(this.mScrollPosState, itemCount, 0);
            }
        }
    }

    private int calculateAdapterHeight() {
        return calculateScrollDistanceToPosition(getAdapter().getItemCount());
    }

    private int calculateScrollDistanceToPosition(int i) {
        if (this.mScrollOffsets.indexOfKey(i) >= 0) {
            return this.mScrollOffsets.get(i);
        }
        MeasurableAdapter measurableAdapter = (MeasurableAdapter) getAdapter();
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            this.mScrollOffsets.put(i3, i2);
            i2 += measurableAdapter.getViewTypeHeight(this, getAdapter().getItemViewType(i3));
        }
        this.mScrollOffsets.put(i, i2);
        return i2;
    }

    private void getCurScrollState(ScrollPositionState scrollPositionState) {
        scrollPositionState.rowIndex = -1;
        scrollPositionState.rowTopOffset = -1;
        scrollPositionState.rowHeight = -1;
        if (getAdapter().getItemCount() != 0 && getChildCount() != 0) {
            View childAt = getChildAt(0);
            scrollPositionState.rowIndex = getChildAdapterPosition(childAt);
            if (getLayoutManager() instanceof GridLayoutManager) {
                scrollPositionState.rowIndex /= ((GridLayoutManager) getLayoutManager()).getSpanCount();
            }
            scrollPositionState.rowTopOffset = getLayoutManager().getDecoratedTop(childAt);
            scrollPositionState.rowHeight = childAt.getHeight() + getLayoutManager().getTopDecorationHeight(childAt) + getLayoutManager().getBottomDecorationHeight(childAt);
        }
    }

    public void setThumbColor(int i) {
        this.mScrollbar.setThumbColor(i);
    }

    public void setTrackColor(int i) {
        this.mScrollbar.setTrackColor(i);
    }

    public void setPopupBgColor(int i) {
        this.mScrollbar.setPopupBgColor(i);
    }

    public void setPopupTextColor(int i) {
        this.mScrollbar.setPopupTextColor(i);
    }

    public void setPopupTextSize(int i) {
        this.mScrollbar.setPopupTextSize(i);
    }

    public void setPopUpTypeface(Typeface typeface) {
        this.mScrollbar.setPopupTypeface(typeface);
    }

    public void setAutoHideDelay(int i) {
        this.mScrollbar.setAutoHideDelay(i);
    }

    public void setAutoHideEnabled(boolean z) {
        this.mScrollbar.setAutoHideEnabled(z);
    }

    public void setStateChangeListener(OnFastScrollStateChangeListener onFastScrollStateChangeListener) {
        this.mStateChangeListener = onFastScrollStateChangeListener;
    }

    public void setPopupPosition(int i) {
        this.mScrollbar.setPopupPosition(i);
    }

    private class ScrollOffsetInvalidator extends RecyclerView.AdapterDataObserver {
        private ScrollOffsetInvalidator() {
        }

        private void invalidateAllScrollOffsets() {
            FastScrollRecyclerView.this.mScrollOffsets.clear();
        }

        public void onChanged() {
            invalidateAllScrollOffsets();
        }

        public void onItemRangeChanged(int i, int i2) {
            invalidateAllScrollOffsets();
        }

        public void onItemRangeChanged(int i, int i2, Object obj) {
            invalidateAllScrollOffsets();
        }

        public void onItemRangeInserted(int i, int i2) {
            invalidateAllScrollOffsets();
        }

        public void onItemRangeRemoved(int i, int i2) {
            invalidateAllScrollOffsets();
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            invalidateAllScrollOffsets();
        }
    }
}
