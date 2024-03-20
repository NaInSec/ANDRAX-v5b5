package com.afollestad.materialdialogs.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ScrollView;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.R;
import com.afollestad.materialdialogs.StackingBehavior;
import com.afollestad.materialdialogs.util.DialogUtils;

public class MDRootLayout extends ViewGroup {
    private static final int INDEX_NEGATIVE = 1;
    private static final int INDEX_NEUTRAL = 0;
    private static final int INDEX_POSITIVE = 2;
    private ViewTreeObserver.OnScrollChangedListener bottomOnScrollChangedListener;
    private int buttonBarHeight;
    private GravityEnum buttonGravity = GravityEnum.START;
    private int buttonHorizontalEdgeMargin;
    private int buttonPaddingFull;
    /* access modifiers changed from: private */
    public final MDButton[] buttons = new MDButton[3];
    private View content;
    private Paint dividerPaint;
    private int dividerWidth;
    /* access modifiers changed from: private */
    public boolean drawBottomDivider = false;
    /* access modifiers changed from: private */
    public boolean drawTopDivider = false;
    private boolean isStacked = false;
    private int maxHeight;
    private boolean noTitleNoPadding;
    private int noTitlePaddingFull;
    private boolean reducePaddingNoTitleNoButtons;
    private StackingBehavior stackBehavior = StackingBehavior.ADAPTIVE;
    private View titleBar;
    private ViewTreeObserver.OnScrollChangedListener topOnScrollChangedListener;
    private boolean useFullPadding = true;

    public MDRootLayout(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public MDRootLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public MDRootLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    public MDRootLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i);
    }

    private static boolean isVisible(View view) {
        boolean z = (view == null || view.getVisibility() == 8) ? false : true;
        if (!z || !(view instanceof MDButton)) {
            return z;
        }
        return ((MDButton) view).getText().toString().trim().length() > 0;
    }

    public static boolean canRecyclerViewScroll(RecyclerView recyclerView) {
        return (recyclerView == null || recyclerView.getLayoutManager() == null || !recyclerView.getLayoutManager().canScrollVertically()) ? false : true;
    }

    private static boolean canScrollViewScroll(ScrollView scrollView) {
        if (scrollView.getChildCount() != 0 && (scrollView.getMeasuredHeight() - scrollView.getPaddingTop()) - scrollView.getPaddingBottom() < scrollView.getChildAt(0).getMeasuredHeight()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static boolean canWebViewScroll(WebView webView) {
        return ((float) webView.getMeasuredHeight()) < ((float) webView.getContentHeight()) * webView.getScale();
    }

    private static boolean canAdapterViewScroll(AdapterView adapterView) {
        if (adapterView.getLastVisiblePosition() == -1) {
            return false;
        }
        boolean z = adapterView.getFirstVisiblePosition() == 0;
        boolean z2 = adapterView.getLastVisiblePosition() == adapterView.getCount() - 1;
        if (!z || !z2 || adapterView.getChildCount() <= 0 || adapterView.getChildAt(0).getTop() < adapterView.getPaddingTop() || adapterView.getChildAt(adapterView.getChildCount() - 1).getBottom() > adapterView.getHeight() - adapterView.getPaddingBottom()) {
            return true;
        }
        return false;
    }

    private static View getBottomView(ViewGroup viewGroup) {
        if (viewGroup == null || viewGroup.getChildCount() == 0) {
            return null;
        }
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt.getVisibility() == 0 && childAt.getBottom() == viewGroup.getMeasuredHeight()) {
                return childAt;
            }
        }
        return null;
    }

    private static View getTopView(ViewGroup viewGroup) {
        if (viewGroup == null || viewGroup.getChildCount() == 0) {
            return null;
        }
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt.getVisibility() == 0 && childAt.getTop() == 0) {
                return childAt;
            }
        }
        return null;
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MDRootLayout, i, 0);
        this.reducePaddingNoTitleNoButtons = obtainStyledAttributes.getBoolean(R.styleable.MDRootLayout_md_reduce_padding_no_title_no_buttons, true);
        obtainStyledAttributes.recycle();
        this.noTitlePaddingFull = resources.getDimensionPixelSize(R.dimen.md_notitle_vertical_padding);
        this.buttonPaddingFull = resources.getDimensionPixelSize(R.dimen.md_button_frame_vertical_padding);
        this.buttonHorizontalEdgeMargin = resources.getDimensionPixelSize(R.dimen.md_button_padding_frame_side);
        this.buttonBarHeight = resources.getDimensionPixelSize(R.dimen.md_button_height);
        this.dividerPaint = new Paint();
        this.dividerWidth = resources.getDimensionPixelSize(R.dimen.md_divider_height);
        this.dividerPaint.setColor(DialogUtils.resolveColor(context, R.attr.md_divider_color));
        setWillNotDraw(false);
    }

    public void setMaxHeight(int i) {
        this.maxHeight = i;
    }

    public void noTitleNoPadding() {
        this.noTitleNoPadding = true;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getId() == R.id.md_titleFrame) {
                this.titleBar = childAt;
            } else if (childAt.getId() == R.id.md_buttonDefaultNeutral) {
                this.buttons[0] = (MDButton) childAt;
            } else if (childAt.getId() == R.id.md_buttonDefaultNegative) {
                this.buttons[1] = (MDButton) childAt;
            } else if (childAt.getId() == R.id.md_buttonDefaultPositive) {
                this.buttons[2] = (MDButton) childAt;
            } else {
                this.content = childAt;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0112  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r12, int r13) {
        /*
            r11 = this;
            int r0 = android.view.View.MeasureSpec.getSize(r12)
            int r1 = android.view.View.MeasureSpec.getSize(r13)
            int r2 = r11.maxHeight
            if (r1 <= r2) goto L_0x000d
            r1 = r2
        L_0x000d:
            r2 = 1
            r11.useFullPadding = r2
            com.afollestad.materialdialogs.StackingBehavior r3 = r11.stackBehavior
            com.afollestad.materialdialogs.StackingBehavior r4 = com.afollestad.materialdialogs.StackingBehavior.ALWAYS
            r5 = 0
            if (r3 != r4) goto L_0x001a
            r3 = 1
        L_0x0018:
            r8 = 0
            goto L_0x005a
        L_0x001a:
            com.afollestad.materialdialogs.StackingBehavior r3 = r11.stackBehavior
            com.afollestad.materialdialogs.StackingBehavior r4 = com.afollestad.materialdialogs.StackingBehavior.NEVER
            if (r3 != r4) goto L_0x0022
            r3 = 0
            goto L_0x0018
        L_0x0022:
            com.afollestad.materialdialogs.internal.MDButton[] r3 = r11.buttons
            int r4 = r3.length
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x0028:
            if (r6 >= r4) goto L_0x0043
            r9 = r3[r6]
            if (r9 == 0) goto L_0x0040
            boolean r10 = isVisible(r9)
            if (r10 == 0) goto L_0x0040
            r9.setStacked(r5, r5)
            r11.measureChild(r9, r12, r13)
            int r8 = r9.getMeasuredWidth()
            int r7 = r7 + r8
            r8 = 1
        L_0x0040:
            int r6 = r6 + 1
            goto L_0x0028
        L_0x0043:
            android.content.Context r3 = r11.getContext()
            android.content.res.Resources r3 = r3.getResources()
            int r4 = com.afollestad.materialdialogs.R.dimen.md_neutral_button_margin
            int r3 = r3.getDimensionPixelSize(r4)
            int r3 = r3 * 2
            int r3 = r0 - r3
            if (r7 <= r3) goto L_0x0059
            r3 = 1
            goto L_0x005a
        L_0x0059:
            r3 = 0
        L_0x005a:
            r11.isStacked = r3
            if (r3 == 0) goto L_0x007e
            com.afollestad.materialdialogs.internal.MDButton[] r3 = r11.buttons
            int r4 = r3.length
            r6 = 0
            r7 = 0
        L_0x0063:
            if (r6 >= r4) goto L_0x007f
            r9 = r3[r6]
            if (r9 == 0) goto L_0x007b
            boolean r10 = isVisible(r9)
            if (r10 == 0) goto L_0x007b
            r9.setStacked(r2, r5)
            r11.measureChild(r9, r12, r13)
            int r8 = r9.getMeasuredHeight()
            int r7 = r7 + r8
            r8 = 1
        L_0x007b:
            int r6 = r6 + 1
            goto L_0x0063
        L_0x007e:
            r7 = 0
        L_0x007f:
            if (r8 == 0) goto L_0x009b
            boolean r12 = r11.isStacked
            if (r12 == 0) goto L_0x0090
            int r12 = r1 - r7
            int r13 = r11.buttonPaddingFull
            int r3 = r13 * 2
            int r3 = r3 + r5
            int r13 = r13 * 2
            int r13 = r13 + r5
            goto L_0x00a3
        L_0x0090:
            int r12 = r11.buttonBarHeight
            int r12 = r1 - r12
            int r13 = r11.buttonPaddingFull
            int r13 = r13 * 2
            int r3 = r13 + 0
            goto L_0x00a2
        L_0x009b:
            int r12 = r11.buttonPaddingFull
            int r12 = r12 * 2
            int r3 = r12 + 0
            r12 = r1
        L_0x00a2:
            r13 = 0
        L_0x00a3:
            android.view.View r4 = r11.titleBar
            boolean r4 = isVisible(r4)
            r6 = 1073741824(0x40000000, float:2.0)
            if (r4 == 0) goto L_0x00be
            android.view.View r4 = r11.titleBar
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r6)
            r4.measure(r7, r5)
            android.view.View r4 = r11.titleBar
            int r4 = r4.getMeasuredHeight()
            int r12 = r12 - r4
            goto L_0x00c5
        L_0x00be:
            boolean r4 = r11.noTitleNoPadding
            if (r4 != 0) goto L_0x00c5
            int r4 = r11.noTitlePaddingFull
            int r3 = r3 + r4
        L_0x00c5:
            android.view.View r4 = r11.content
            boolean r4 = isVisible(r4)
            if (r4 == 0) goto L_0x0112
            android.view.View r4 = r11.content
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r6)
            int r7 = r12 - r13
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r9)
            r4.measure(r6, r7)
            android.view.View r4 = r11.content
            int r4 = r4.getMeasuredHeight()
            int r6 = r12 - r3
            if (r4 > r6) goto L_0x010f
            boolean r4 = r11.reducePaddingNoTitleNoButtons
            if (r4 == 0) goto L_0x0103
            android.view.View r4 = r11.titleBar
            boolean r4 = isVisible(r4)
            if (r4 != 0) goto L_0x0103
            if (r8 == 0) goto L_0x00f7
            goto L_0x0103
        L_0x00f7:
            r11.useFullPadding = r5
            android.view.View r2 = r11.content
            int r2 = r2.getMeasuredHeight()
            int r2 = r2 + r13
            int r5 = r12 - r2
            goto L_0x0113
        L_0x0103:
            r11.useFullPadding = r2
            android.view.View r13 = r11.content
            int r13 = r13.getMeasuredHeight()
            int r13 = r13 + r3
            int r5 = r12 - r13
            goto L_0x0113
        L_0x010f:
            r11.useFullPadding = r5
            goto L_0x0113
        L_0x0112:
            r5 = r12
        L_0x0113:
            int r1 = r1 - r5
            r11.setMeasuredDimension(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.internal.MDRootLayout.onMeasure(int, int):void");
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        View view = this.content;
        if (view != null) {
            if (this.drawTopDivider) {
                int top = view.getTop();
                canvas.drawRect(0.0f, (float) (top - this.dividerWidth), (float) getMeasuredWidth(), (float) top, this.dividerPaint);
            }
            if (this.drawBottomDivider) {
                int bottom = this.content.getBottom();
                canvas.drawRect(0.0f, (float) bottom, (float) getMeasuredWidth(), (float) (bottom + this.dividerWidth), this.dividerPaint);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        if (isVisible(this.titleBar)) {
            int measuredHeight = this.titleBar.getMeasuredHeight() + i2;
            this.titleBar.layout(i, i2, i3, measuredHeight);
            i2 = measuredHeight;
        } else if (!this.noTitleNoPadding && this.useFullPadding) {
            i2 += this.noTitlePaddingFull;
        }
        if (isVisible(this.content)) {
            View view = this.content;
            view.layout(i, i2, i3, view.getMeasuredHeight() + i2);
        }
        if (this.isStacked) {
            int i14 = i4 - this.buttonPaddingFull;
            for (MDButton mDButton : this.buttons) {
                if (isVisible(mDButton)) {
                    mDButton.layout(i, i14 - mDButton.getMeasuredHeight(), i3, i14);
                    i14 -= mDButton.getMeasuredHeight();
                }
            }
        } else {
            if (this.useFullPadding) {
                i4 -= this.buttonPaddingFull;
            }
            int i15 = i4 - this.buttonBarHeight;
            int i16 = this.buttonHorizontalEdgeMargin;
            if (isVisible(this.buttons[2])) {
                if (this.buttonGravity == GravityEnum.END) {
                    i13 = i + i16;
                    i12 = this.buttons[2].getMeasuredWidth() + i13;
                    i5 = -1;
                } else {
                    i12 = i3 - i16;
                    i13 = i12 - this.buttons[2].getMeasuredWidth();
                    i5 = i13;
                }
                this.buttons[2].layout(i13, i15, i12, i4);
                i16 += this.buttons[2].getMeasuredWidth();
            } else {
                i5 = -1;
            }
            if (isVisible(this.buttons[1])) {
                if (this.buttonGravity == GravityEnum.END) {
                    i11 = i16 + i;
                    i10 = this.buttons[1].getMeasuredWidth() + i11;
                } else if (this.buttonGravity == GravityEnum.START) {
                    i10 = i3 - i16;
                    i11 = i10 - this.buttons[1].getMeasuredWidth();
                } else {
                    i11 = this.buttonHorizontalEdgeMargin + i;
                    i10 = this.buttons[1].getMeasuredWidth() + i11;
                    i6 = i10;
                    this.buttons[1].layout(i11, i15, i10, i4);
                }
                i6 = -1;
                this.buttons[1].layout(i11, i15, i10, i4);
            } else {
                i6 = -1;
            }
            if (isVisible(this.buttons[0])) {
                if (this.buttonGravity == GravityEnum.END) {
                    i7 = i3 - this.buttonHorizontalEdgeMargin;
                    i8 = i7 - this.buttons[0].getMeasuredWidth();
                } else if (this.buttonGravity == GravityEnum.START) {
                    i8 = i + this.buttonHorizontalEdgeMargin;
                    i7 = this.buttons[0].getMeasuredWidth() + i8;
                } else {
                    if (i6 != -1 || i5 == -1) {
                        if (i5 == -1 && i6 != -1) {
                            i9 = this.buttons[0].getMeasuredWidth();
                        } else if (i5 == -1) {
                            i6 = ((i3 - i) / 2) - (this.buttons[0].getMeasuredWidth() / 2);
                            i9 = this.buttons[0].getMeasuredWidth();
                        }
                        i5 = i6 + i9;
                    } else {
                        i6 = i5 - this.buttons[0].getMeasuredWidth();
                    }
                    i8 = i6;
                    i7 = i5;
                }
                this.buttons[0].layout(i8, i15, i7, i4);
            }
        }
        setUpDividersVisibility(this.content, true, true);
    }

    public void setStackingBehavior(StackingBehavior stackingBehavior) {
        this.stackBehavior = stackingBehavior;
        invalidate();
    }

    public void setDividerColor(int i) {
        this.dividerPaint.setColor(i);
        invalidate();
    }

    public void setButtonGravity(GravityEnum gravityEnum) {
        this.buttonGravity = gravityEnum;
        invertGravityIfNecessary();
    }

    private void invertGravityIfNecessary() {
        if (Build.VERSION.SDK_INT >= 17 && getResources().getConfiguration().getLayoutDirection() == 1) {
            int i = AnonymousClass4.$SwitchMap$com$afollestad$materialdialogs$GravityEnum[this.buttonGravity.ordinal()];
            if (i == 1) {
                this.buttonGravity = GravityEnum.END;
            } else if (i == 2) {
                this.buttonGravity = GravityEnum.START;
            }
        }
    }

    /* renamed from: com.afollestad.materialdialogs.internal.MDRootLayout$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$afollestad$materialdialogs$GravityEnum = new int[GravityEnum.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.afollestad.materialdialogs.GravityEnum[] r0 = com.afollestad.materialdialogs.GravityEnum.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$afollestad$materialdialogs$GravityEnum = r0
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$GravityEnum     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.afollestad.materialdialogs.GravityEnum r1 = com.afollestad.materialdialogs.GravityEnum.START     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$GravityEnum     // Catch:{ NoSuchFieldError -> 0x001f }
                com.afollestad.materialdialogs.GravityEnum r1 = com.afollestad.materialdialogs.GravityEnum.END     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.internal.MDRootLayout.AnonymousClass4.<clinit>():void");
        }
    }

    public void setButtonStackedGravity(GravityEnum gravityEnum) {
        for (MDButton mDButton : this.buttons) {
            if (mDButton != null) {
                mDButton.setStackedGravity(gravityEnum);
            }
        }
    }

    private void setUpDividersVisibility(final View view, final boolean z, final boolean z2) {
        if (view != null) {
            if (view instanceof ScrollView) {
                ScrollView scrollView = (ScrollView) view;
                if (canScrollViewScroll(scrollView)) {
                    addScrollListener(scrollView, z, z2);
                    return;
                }
                if (z) {
                    this.drawTopDivider = false;
                }
                if (z2) {
                    this.drawBottomDivider = false;
                }
            } else if (view instanceof AdapterView) {
                AdapterView adapterView = (AdapterView) view;
                if (canAdapterViewScroll(adapterView)) {
                    addScrollListener(adapterView, z, z2);
                    return;
                }
                if (z) {
                    this.drawTopDivider = false;
                }
                if (z2) {
                    this.drawBottomDivider = false;
                }
            } else if (view instanceof WebView) {
                view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    public boolean onPreDraw() {
                        if (view.getMeasuredHeight() == 0) {
                            return true;
                        }
                        if (!MDRootLayout.canWebViewScroll((WebView) view)) {
                            if (z) {
                                boolean unused = MDRootLayout.this.drawTopDivider = false;
                            }
                            if (z2) {
                                boolean unused2 = MDRootLayout.this.drawBottomDivider = false;
                            }
                        } else {
                            MDRootLayout.this.addScrollListener((ViewGroup) view, z, z2);
                        }
                        view.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                });
            } else if (view instanceof RecyclerView) {
                boolean canRecyclerViewScroll = canRecyclerViewScroll((RecyclerView) view);
                if (z) {
                    this.drawTopDivider = canRecyclerViewScroll;
                }
                if (z2) {
                    this.drawBottomDivider = canRecyclerViewScroll;
                }
                if (canRecyclerViewScroll) {
                    addScrollListener((ViewGroup) view, z, z2);
                }
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                View topView = getTopView(viewGroup);
                setUpDividersVisibility(topView, z, z2);
                View bottomView = getBottomView(viewGroup);
                if (bottomView != topView) {
                    setUpDividersVisibility(bottomView, false, true);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void addScrollListener(final ViewGroup viewGroup, final boolean z, final boolean z2) {
        if ((!z2 && this.topOnScrollChangedListener == null) || (z2 && this.bottomOnScrollChangedListener == null)) {
            if (viewGroup instanceof RecyclerView) {
                AnonymousClass2 r0 = new RecyclerView.OnScrollListener() {
                    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                        super.onScrolled(recyclerView, i, i2);
                        MDButton[] access$400 = MDRootLayout.this.buttons;
                        int length = access$400.length;
                        boolean z = false;
                        int i3 = 0;
                        while (true) {
                            if (i3 < length) {
                                MDButton mDButton = access$400[i3];
                                if (mDButton != null && mDButton.getVisibility() != 8) {
                                    z = true;
                                    break;
                                }
                                i3++;
                            } else {
                                break;
                            }
                        }
                        MDRootLayout.this.invalidateDividersForScrollingView(viewGroup, z, z2, z);
                        MDRootLayout.this.invalidate();
                    }
                };
                RecyclerView recyclerView = (RecyclerView) viewGroup;
                recyclerView.addOnScrollListener(r0);
                r0.onScrolled(recyclerView, 0, 0);
                return;
            }
            AnonymousClass3 r02 = new ViewTreeObserver.OnScrollChangedListener() {
                public void onScrollChanged() {
                    MDButton[] access$400 = MDRootLayout.this.buttons;
                    int length = access$400.length;
                    boolean z = false;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            MDButton mDButton = access$400[i];
                            if (mDButton != null && mDButton.getVisibility() != 8) {
                                z = true;
                                break;
                            }
                            i++;
                        } else {
                            break;
                        }
                    }
                    ViewGroup viewGroup = viewGroup;
                    if (viewGroup instanceof WebView) {
                        MDRootLayout.this.invalidateDividersForWebView((WebView) viewGroup, z, z2, z);
                    } else {
                        MDRootLayout.this.invalidateDividersForScrollingView(viewGroup, z, z2, z);
                    }
                    MDRootLayout.this.invalidate();
                }
            };
            if (!z2) {
                this.topOnScrollChangedListener = r02;
                viewGroup.getViewTreeObserver().addOnScrollChangedListener(this.topOnScrollChangedListener);
            } else {
                this.bottomOnScrollChangedListener = r02;
                viewGroup.getViewTreeObserver().addOnScrollChangedListener(this.bottomOnScrollChangedListener);
            }
            r02.onScrollChanged();
        }
    }

    /* access modifiers changed from: private */
    public void invalidateDividersForScrollingView(ViewGroup viewGroup, boolean z, boolean z2, boolean z3) {
        boolean z4 = true;
        if (z && viewGroup.getChildCount() > 0) {
            View view = this.titleBar;
            this.drawTopDivider = (view == null || view.getVisibility() == 8 || viewGroup.getScrollY() + viewGroup.getPaddingTop() <= viewGroup.getChildAt(0).getTop()) ? false : true;
        }
        if (z2 && viewGroup.getChildCount() > 0) {
            if (!z3 || (viewGroup.getScrollY() + viewGroup.getHeight()) - viewGroup.getPaddingBottom() >= viewGroup.getChildAt(viewGroup.getChildCount() - 1).getBottom()) {
                z4 = false;
            }
            this.drawBottomDivider = z4;
        }
    }

    /* access modifiers changed from: private */
    public void invalidateDividersForWebView(WebView webView, boolean z, boolean z2, boolean z3) {
        boolean z4 = true;
        if (z) {
            View view = this.titleBar;
            this.drawTopDivider = (view == null || view.getVisibility() == 8 || webView.getScrollY() + webView.getPaddingTop() <= 0) ? false : true;
        }
        if (z2) {
            if (!z3 || ((float) ((webView.getScrollY() + webView.getMeasuredHeight()) - webView.getPaddingBottom())) >= ((float) webView.getContentHeight()) * webView.getScale()) {
                z4 = false;
            }
            this.drawBottomDivider = z4;
        }
    }
}
