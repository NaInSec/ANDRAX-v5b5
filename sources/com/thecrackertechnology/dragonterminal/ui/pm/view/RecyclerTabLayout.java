package com.thecrackertechnology.dragonterminal.ui.pm.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.thecrackertechnology.andrax.R;

public class RecyclerTabLayout extends RecyclerView {
    protected static final float DEFAULT_POSITION_THRESHOLD = 0.6f;
    protected static final long DEFAULT_SCROLL_DURATION = 200;
    protected static final float POSITION_THRESHOLD_ALLOWABLE = 0.001f;
    protected Adapter<?> mAdapter;
    protected int mIndicatorGap;
    protected int mIndicatorHeight;
    protected Paint mIndicatorPaint;
    protected int mIndicatorPosition;
    protected int mIndicatorScroll;
    protected LinearLayoutManager mLinearLayoutManager;
    private int mOldPosition;
    protected float mOldPositionOffset;
    private int mOldScrollOffset;
    protected float mPositionThreshold;
    protected RecyclerOnScrollListener mRecyclerOnScrollListener;
    protected boolean mRequestScrollToTab;
    protected boolean mScrollEanbled;
    protected int mTabBackgroundResId;
    protected int mTabMaxWidth;
    protected int mTabMinWidth;
    protected int mTabOnScreenLimit;
    protected int mTabPaddingBottom;
    protected int mTabPaddingEnd;
    protected int mTabPaddingStart;
    protected int mTabPaddingTop;
    protected int mTabSelectedTextColor;
    protected boolean mTabSelectedTextColorSet;
    protected int mTabTextAppearance;
    protected ViewPager mViewPager;

    public RecyclerTabLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public RecyclerTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecyclerTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setWillNotDraw(false);
        this.mIndicatorPaint = new Paint();
        getAttributes(context, attributeSet, i);
        this.mLinearLayoutManager = new LinearLayoutManager(getContext()) {
            public boolean canScrollHorizontally() {
                return RecyclerTabLayout.this.mScrollEanbled;
            }
        };
        this.mLinearLayoutManager.setOrientation(0);
        setLayoutManager(this.mLinearLayoutManager);
        setItemAnimator((RecyclerView.ItemAnimator) null);
        this.mPositionThreshold = DEFAULT_POSITION_THRESHOLD;
    }

    private void getAttributes(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.rtl_RecyclerTabLayout, i, R.style.rtl_RecyclerTabLayout);
        setIndicatorColor(obtainStyledAttributes.getColor(2, 0));
        setIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(3, 0));
        this.mTabTextAppearance = obtainStyledAttributes.getResourceId(13, R.style.rtl_RecyclerTabLayout_Tab);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.mTabPaddingBottom = dimensionPixelSize;
        this.mTabPaddingEnd = dimensionPixelSize;
        this.mTabPaddingTop = dimensionPixelSize;
        this.mTabPaddingStart = dimensionPixelSize;
        this.mTabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(10, this.mTabPaddingStart);
        this.mTabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(11, this.mTabPaddingTop);
        this.mTabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(9, this.mTabPaddingEnd);
        this.mTabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(8, this.mTabPaddingBottom);
        if (obtainStyledAttributes.hasValue(12)) {
            this.mTabSelectedTextColor = obtainStyledAttributes.getColor(12, 0);
            this.mTabSelectedTextColorSet = true;
        }
        this.mTabOnScreenLimit = obtainStyledAttributes.getInteger(6, 0);
        if (this.mTabOnScreenLimit == 0) {
            this.mTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(5, 0);
            this.mTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        }
        this.mTabBackgroundResId = obtainStyledAttributes.getResourceId(1, 0);
        this.mScrollEanbled = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        RecyclerOnScrollListener recyclerOnScrollListener = this.mRecyclerOnScrollListener;
        if (recyclerOnScrollListener != null) {
            removeOnScrollListener(recyclerOnScrollListener);
            this.mRecyclerOnScrollListener = null;
        }
        super.onDetachedFromWindow();
    }

    public void setIndicatorColor(int i) {
        this.mIndicatorPaint.setColor(i);
    }

    public void setIndicatorHeight(int i) {
        this.mIndicatorHeight = i;
    }

    public void setAutoSelectionMode(boolean z) {
        RecyclerOnScrollListener recyclerOnScrollListener = this.mRecyclerOnScrollListener;
        if (recyclerOnScrollListener != null) {
            removeOnScrollListener(recyclerOnScrollListener);
            this.mRecyclerOnScrollListener = null;
        }
        if (z) {
            this.mRecyclerOnScrollListener = new RecyclerOnScrollListener(this, this.mLinearLayoutManager);
            addOnScrollListener(this.mRecyclerOnScrollListener);
        }
    }

    public void setPositionThreshold(float f) {
        this.mPositionThreshold = f;
    }

    public void setUpWithViewPager(ViewPager viewPager) {
        DefaultAdapter defaultAdapter = new DefaultAdapter(viewPager);
        defaultAdapter.setTabPadding(this.mTabPaddingStart, this.mTabPaddingTop, this.mTabPaddingEnd, this.mTabPaddingBottom);
        defaultAdapter.setTabTextAppearance(this.mTabTextAppearance);
        defaultAdapter.setTabSelectedTextColor(this.mTabSelectedTextColorSet, this.mTabSelectedTextColor);
        defaultAdapter.setTabMaxWidth(this.mTabMaxWidth);
        defaultAdapter.setTabMinWidth(this.mTabMinWidth);
        defaultAdapter.setTabBackgroundResId(this.mTabBackgroundResId);
        defaultAdapter.setTabOnScreenLimit(this.mTabOnScreenLimit);
        setUpWithAdapter(defaultAdapter);
    }

    public void setUpWithAdapter(Adapter<?> adapter) {
        this.mAdapter = adapter;
        this.mViewPager = adapter.getViewPager();
        if (this.mViewPager.getAdapter() != null) {
            this.mViewPager.addOnPageChangeListener(new ViewPagerOnPageChangeListener(this));
            setAdapter(adapter);
            scrollToTab(this.mViewPager.getCurrentItem());
            return;
        }
        throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
    }

    public void setCurrentItem(int i, boolean z) {
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            viewPager.setCurrentItem(i, z);
            scrollToTab(this.mViewPager.getCurrentItem());
        } else if (!z || i == this.mIndicatorPosition) {
            scrollToTab(i);
        } else {
            startAnimation(i);
        }
    }

    /* access modifiers changed from: protected */
    public void startAnimation(final int i) {
        float f;
        ValueAnimator valueAnimator;
        View findViewByPosition = this.mLinearLayoutManager.findViewByPosition(i);
        if (findViewByPosition != null) {
            f = Math.abs((((float) getMeasuredWidth()) / 2.0f) - (findViewByPosition.getX() + (((float) findViewByPosition.getMeasuredWidth()) / 2.0f))) / ((float) findViewByPosition.getMeasuredWidth());
        } else {
            f = 1.0f;
        }
        if (i < this.mIndicatorPosition) {
            valueAnimator = ValueAnimator.ofFloat(new float[]{f, 0.0f});
        } else {
            valueAnimator = ValueAnimator.ofFloat(new float[]{-f, 0.0f});
        }
        valueAnimator.setDuration(DEFAULT_SCROLL_DURATION);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                RecyclerTabLayout.this.scrollToTab(i, ((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
            }
        });
        valueAnimator.start();
    }

    /* access modifiers changed from: protected */
    public void scrollToTab(int i) {
        scrollToTab(i, 0.0f, false);
        this.mAdapter.setCurrentIndicatorPosition(i);
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0080, code lost:
        r11 = r8.mTabMaxWidth;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0084, code lost:
        r0 = r8.mTabMinWidth;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void scrollToTab(int r9, float r10, boolean r11) {
        /*
            r8 = this;
            android.support.v7.widget.LinearLayoutManager r0 = r8.mLinearLayoutManager
            android.view.View r0 = r0.findViewByPosition(r9)
            android.support.v7.widget.LinearLayoutManager r1 = r8.mLinearLayoutManager
            int r2 = r9 + 1
            android.view.View r1 = r1.findViewByPosition(r2)
            r2 = 1073741824(0x40000000, float:2.0)
            r3 = 0
            if (r0 == 0) goto L_0x007a
            int r4 = r8.getMeasuredWidth()
            if (r9 != 0) goto L_0x001b
            r5 = 0
            goto L_0x0024
        L_0x001b:
            float r5 = (float) r4
            float r5 = r5 / r2
            int r6 = r0.getMeasuredWidth()
            float r6 = (float) r6
            float r6 = r6 / r2
            float r5 = r5 - r6
        L_0x0024:
            int r6 = r0.getMeasuredWidth()
            float r6 = (float) r6
            float r6 = r6 + r5
            if (r1 == 0) goto L_0x006e
            float r4 = (float) r4
            float r4 = r4 / r2
            int r7 = r1.getMeasuredWidth()
            float r7 = (float) r7
            float r7 = r7 / r2
            float r4 = r4 - r7
            float r6 = r6 - r4
            float r6 = r6 * r10
            float r5 = r5 - r6
            int r2 = (int) r5
            if (r9 != 0) goto L_0x0059
            int r1 = r1.getMeasuredWidth()
            int r4 = r0.getMeasuredWidth()
            int r1 = r1 - r4
            int r1 = r1 / 2
            float r1 = (float) r1
            float r4 = r1 * r10
            int r4 = (int) r4
            r8.mIndicatorGap = r4
            int r0 = r0.getMeasuredWidth()
            float r0 = (float) r0
            float r0 = r0 + r1
            float r0 = r0 * r10
            int r0 = (int) r0
            r8.mIndicatorScroll = r0
            goto L_0x0073
        L_0x0059:
            int r1 = r1.getMeasuredWidth()
            int r0 = r0.getMeasuredWidth()
            int r1 = r1 - r0
            int r1 = r1 / 2
            float r0 = (float) r1
            float r0 = r0 * r10
            int r0 = (int) r0
            r8.mIndicatorGap = r0
            int r0 = (int) r6
            r8.mIndicatorScroll = r0
            goto L_0x0073
        L_0x006e:
            int r2 = (int) r5
            r8.mIndicatorScroll = r3
            r8.mIndicatorGap = r3
        L_0x0073:
            if (r11 == 0) goto L_0x009d
            r8.mIndicatorScroll = r3
            r8.mIndicatorGap = r3
            goto L_0x009d
        L_0x007a:
            int r11 = r8.getMeasuredWidth()
            if (r11 <= 0) goto L_0x0099
            int r11 = r8.mTabMaxWidth
            if (r11 <= 0) goto L_0x0099
            int r0 = r8.mTabMinWidth
            if (r0 != r11) goto L_0x0099
            int r11 = -r0
            float r11 = (float) r11
            float r11 = r11 * r10
            int r11 = (int) r11
            int r1 = r8.getMeasuredWidth()
            int r1 = r1 - r0
            float r0 = (float) r1
            float r0 = r0 / r2
            int r0 = (int) r0
            int r3 = r11 + r0
            r2 = r3
            goto L_0x009a
        L_0x0099:
            r2 = 0
        L_0x009a:
            r11 = 1
            r8.mRequestScrollToTab = r11
        L_0x009d:
            float r11 = r8.mOldPositionOffset
            float r11 = r10 - r11
            r8.updateCurrentIndicatorPosition(r9, r11, r10)
            r8.mIndicatorPosition = r9
            r8.stopScroll()
            int r11 = r8.mOldPosition
            if (r9 != r11) goto L_0x00b1
            int r11 = r8.mOldScrollOffset
            if (r2 == r11) goto L_0x00b6
        L_0x00b1:
            android.support.v7.widget.LinearLayoutManager r11 = r8.mLinearLayoutManager
            r11.scrollToPositionWithOffset(r9, r2)
        L_0x00b6:
            int r11 = r8.mIndicatorHeight
            if (r11 <= 0) goto L_0x00bd
            r8.invalidate()
        L_0x00bd:
            r8.mOldPosition = r9
            r8.mOldScrollOffset = r2
            r8.mOldPositionOffset = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.ui.pm.view.RecyclerTabLayout.scrollToTab(int, float, boolean):void");
    }

    /* access modifiers changed from: protected */
    public void updateCurrentIndicatorPosition(int i, float f, float f2) {
        if (this.mAdapter != null) {
            if (f > 0.0f && f2 >= this.mPositionThreshold - POSITION_THRESHOLD_ALLOWABLE) {
                i++;
            } else if (f >= 0.0f || f2 > (1.0f - this.mPositionThreshold) + POSITION_THRESHOLD_ALLOWABLE) {
                i = -1;
            }
            if (i >= 0 && i != this.mAdapter.getCurrentIndicatorPosition()) {
                this.mAdapter.setCurrentIndicatorPosition(i);
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        View findViewByPosition = this.mLinearLayoutManager.findViewByPosition(this.mIndicatorPosition);
        if (findViewByPosition != null) {
            this.mRequestScrollToTab = false;
            if (isLayoutRtl()) {
                i2 = (findViewByPosition.getLeft() - this.mIndicatorScroll) - this.mIndicatorGap;
                i3 = findViewByPosition.getRight() - this.mIndicatorScroll;
                i = this.mIndicatorGap;
            } else {
                i2 = (findViewByPosition.getLeft() + this.mIndicatorScroll) - this.mIndicatorGap;
                i3 = findViewByPosition.getRight() + this.mIndicatorScroll;
                i = this.mIndicatorGap;
            }
            int i4 = i3 + i;
            Canvas canvas2 = canvas;
            canvas2.drawRect((float) i2, (float) (getHeight() - this.mIndicatorHeight), (float) i4, (float) getHeight(), this.mIndicatorPaint);
        } else if (this.mRequestScrollToTab) {
            this.mRequestScrollToTab = false;
            scrollToTab(this.mViewPager.getCurrentItem());
        }
    }

    /* access modifiers changed from: protected */
    public boolean isLayoutRtl() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    protected static class RecyclerOnScrollListener extends RecyclerView.OnScrollListener {
        public int mDx;
        protected LinearLayoutManager mLinearLayoutManager;
        protected RecyclerTabLayout mRecyclerTabLayout;

        public RecyclerOnScrollListener(RecyclerTabLayout recyclerTabLayout, LinearLayoutManager linearLayoutManager) {
            this.mRecyclerTabLayout = recyclerTabLayout;
            this.mLinearLayoutManager = linearLayoutManager;
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            this.mDx += i;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 0) {
                if (this.mDx > 0) {
                    selectCenterTabForRightScroll();
                } else {
                    selectCenterTabForLeftScroll();
                }
                this.mDx = 0;
            }
        }

        /* access modifiers changed from: protected */
        public void selectCenterTabForRightScroll() {
            int findLastVisibleItemPosition = this.mLinearLayoutManager.findLastVisibleItemPosition();
            int width = this.mRecyclerTabLayout.getWidth() / 2;
            for (int findFirstVisibleItemPosition = this.mLinearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                View findViewByPosition = this.mLinearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                if (findViewByPosition.getLeft() + findViewByPosition.getWidth() >= width) {
                    this.mRecyclerTabLayout.setCurrentItem(findFirstVisibleItemPosition, false);
                    return;
                }
            }
        }

        /* access modifiers changed from: protected */
        public void selectCenterTabForLeftScroll() {
            int findFirstVisibleItemPosition = this.mLinearLayoutManager.findFirstVisibleItemPosition();
            int width = this.mRecyclerTabLayout.getWidth() / 2;
            for (int findLastVisibleItemPosition = this.mLinearLayoutManager.findLastVisibleItemPosition(); findLastVisibleItemPosition >= findFirstVisibleItemPosition; findLastVisibleItemPosition--) {
                if (this.mLinearLayoutManager.findViewByPosition(findLastVisibleItemPosition).getLeft() <= width) {
                    this.mRecyclerTabLayout.setCurrentItem(findLastVisibleItemPosition, false);
                    return;
                }
            }
        }
    }

    protected static class ViewPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private final RecyclerTabLayout mRecyclerTabLayout;
        private int mScrollState;

        public ViewPagerOnPageChangeListener(RecyclerTabLayout recyclerTabLayout) {
            this.mRecyclerTabLayout = recyclerTabLayout;
        }

        public void onPageScrolled(int i, float f, int i2) {
            this.mRecyclerTabLayout.scrollToTab(i, f, false);
        }

        public void onPageScrollStateChanged(int i) {
            this.mScrollState = i;
        }

        public void onPageSelected(int i) {
            if (this.mScrollState == 0 && this.mRecyclerTabLayout.mIndicatorPosition != i) {
                this.mRecyclerTabLayout.scrollToTab(i);
            }
        }
    }

    public static abstract class Adapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
        protected int mIndicatorPosition;
        protected ViewPager mViewPager;

        public Adapter(ViewPager viewPager) {
            this.mViewPager = viewPager;
        }

        public ViewPager getViewPager() {
            return this.mViewPager;
        }

        public void setCurrentIndicatorPosition(int i) {
            this.mIndicatorPosition = i;
        }

        public int getCurrentIndicatorPosition() {
            return this.mIndicatorPosition;
        }
    }

    public static class DefaultAdapter extends Adapter<ViewHolder> {
        protected static final int MAX_TAB_TEXT_LINES = 2;
        private int mTabBackgroundResId;
        private int mTabMaxWidth;
        private int mTabMinWidth;
        private int mTabOnScreenLimit;
        protected int mTabPaddingBottom;
        protected int mTabPaddingEnd;
        protected int mTabPaddingStart;
        protected int mTabPaddingTop;
        protected int mTabSelectedTextColor;
        protected boolean mTabSelectedTextColorSet;
        protected int mTabTextAppearance;

        public DefaultAdapter(ViewPager viewPager) {
            super(viewPager);
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            TabTextView tabTextView = new TabTextView(viewGroup.getContext());
            if (this.mTabSelectedTextColorSet) {
                tabTextView.setTextColor(tabTextView.createColorStateList(tabTextView.getCurrentTextColor(), this.mTabSelectedTextColor));
            }
            ViewCompat.setPaddingRelative(tabTextView, this.mTabPaddingStart, this.mTabPaddingTop, this.mTabPaddingEnd, this.mTabPaddingBottom);
            tabTextView.setTextAppearance(viewGroup.getContext(), this.mTabTextAppearance);
            tabTextView.setGravity(17);
            tabTextView.setMaxLines(2);
            tabTextView.setEllipsize(TextUtils.TruncateAt.END);
            if (this.mTabOnScreenLimit > 0) {
                int measuredWidth = viewGroup.getMeasuredWidth() / this.mTabOnScreenLimit;
                tabTextView.setMaxWidth(measuredWidth);
                tabTextView.setMinWidth(measuredWidth);
            } else {
                int i2 = this.mTabMaxWidth;
                if (i2 > 0) {
                    tabTextView.setMaxWidth(i2);
                }
                tabTextView.setMinWidth(this.mTabMinWidth);
            }
            tabTextView.setTextAppearance(tabTextView.getContext(), this.mTabTextAppearance);
            if (this.mTabSelectedTextColorSet) {
                tabTextView.setTextColor(tabTextView.createColorStateList(tabTextView.getCurrentTextColor(), this.mTabSelectedTextColor));
            }
            if (this.mTabBackgroundResId != 0) {
                tabTextView.setBackgroundDrawable(AppCompatResources.getDrawable(tabTextView.getContext(), this.mTabBackgroundResId));
            }
            tabTextView.setLayoutParams(createLayoutParamsForTabs());
            return new ViewHolder(tabTextView);
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.title.setText(getViewPager().getAdapter().getPageTitle(i));
            viewHolder.title.setSelected(getCurrentIndicatorPosition() == i);
        }

        public int getItemCount() {
            return getViewPager().getAdapter().getCount();
        }

        public void setTabPadding(int i, int i2, int i3, int i4) {
            this.mTabPaddingStart = i;
            this.mTabPaddingTop = i2;
            this.mTabPaddingEnd = i3;
            this.mTabPaddingBottom = i4;
        }

        public void setTabTextAppearance(int i) {
            this.mTabTextAppearance = i;
        }

        public void setTabSelectedTextColor(boolean z, int i) {
            this.mTabSelectedTextColorSet = z;
            this.mTabSelectedTextColor = i;
        }

        public void setTabMaxWidth(int i) {
            this.mTabMaxWidth = i;
        }

        public void setTabMinWidth(int i) {
            this.mTabMinWidth = i;
        }

        public void setTabBackgroundResId(int i) {
            this.mTabBackgroundResId = i;
        }

        public void setTabOnScreenLimit(int i) {
            this.mTabOnScreenLimit = i;
        }

        /* access modifiers changed from: protected */
        public RecyclerView.LayoutParams createLayoutParamsForTabs() {
            return new RecyclerView.LayoutParams(-2, -1);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView title;

            public ViewHolder(View view) {
                super(view);
                this.title = (TextView) view;
                view.setOnClickListener(new View.OnClickListener(DefaultAdapter.this) {
                    public void onClick(View view) {
                        int adapterPosition = ViewHolder.this.getAdapterPosition();
                        if (adapterPosition != -1) {
                            DefaultAdapter.this.getViewPager().setCurrentItem(adapterPosition, true);
                        }
                    }
                });
            }
        }
    }

    public static class TabTextView extends AppCompatTextView {
        public TabTextView(Context context) {
            super(context);
        }

        public ColorStateList createColorStateList(int i, int i2) {
            return new ColorStateList(new int[][]{SELECTED_STATE_SET, EMPTY_STATE_SET}, new int[]{i2, i});
        }
    }
}
