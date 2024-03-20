package com.thecrackertechnology.dragonterminal.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/FullScreenHelper;", "", "activity", "Landroid/app/Activity;", "fullScreen", "", "shouldSkipFirst", "(Landroid/app/Activity;ZZ)V", "frameLayoutParams", "Landroid/widget/FrameLayout$LayoutParams;", "getFullScreen", "()Z", "setFullScreen", "(Z)V", "mChildOfContent", "Landroid/view/View;", "mKeyBoardListener", "Lcom/thecrackertechnology/dragonterminal/utils/FullScreenHelper$KeyBoardListener;", "mOriginHeight", "", "mPreHeight", "usableHeightPrevious", "computeUsableHeight", "monitorImeStatus", "", "possiblyResizeChildOfContent", "setKeyBoardListener", "Companion", "KeyBoardListener", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: FullScreenHelper.kt */
public final class FullScreenHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final FrameLayout.LayoutParams frameLayoutParams;
    private boolean fullScreen;
    private final View mChildOfContent;
    private KeyBoardListener mKeyBoardListener;
    private int mOriginHeight;
    private int mPreHeight;
    private boolean shouldSkipFirst;
    private int usableHeightPrevious;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/FullScreenHelper$KeyBoardListener;", "", "onKeyboardChange", "", "isShow", "", "keyboardHeight", "", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: FullScreenHelper.kt */
    public interface KeyBoardListener {
        void onKeyboardChange(boolean z, int i);
    }

    private FullScreenHelper(Activity activity, boolean z, boolean z2) {
        this.fullScreen = z;
        this.shouldSkipFirst = z2;
        View childAt = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt, "content.getChildAt(0)");
        this.mChildOfContent = childAt;
        this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(this) {
            final /* synthetic */ FullScreenHelper this$0;

            {
                this.this$0 = r1;
            }

            public final void onGlobalLayout() {
                if (this.this$0.getFullScreen()) {
                    this.this$0.possiblyResizeChildOfContent();
                }
                this.this$0.monitorImeStatus();
            }
        });
        ViewGroup.LayoutParams layoutParams = this.mChildOfContent.getLayoutParams();
        if (layoutParams != null) {
            this.frameLayoutParams = (FrameLayout.LayoutParams) layoutParams;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
    }

    public /* synthetic */ FullScreenHelper(Activity activity, boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity, z, z2);
    }

    public final boolean getFullScreen() {
        return this.fullScreen;
    }

    public final void setFullScreen(boolean z) {
        this.fullScreen = z;
    }

    public final void setKeyBoardListener(KeyBoardListener keyBoardListener) {
        Intrinsics.checkParameterIsNotNull(keyBoardListener, "mKeyBoardListener");
        this.mKeyBoardListener = keyBoardListener;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void monitorImeStatus() {
        /*
            r5 = this;
            android.view.View r0 = r5.mChildOfContent
            int r0 = r0.getHeight()
            if (r0 != 0) goto L_0x000d
            boolean r1 = r5.shouldSkipFirst
            if (r1 == 0) goto L_0x000d
            return
        L_0x000d:
            r1 = 0
            r5.shouldSkipFirst = r1
            int r2 = r5.mPreHeight
            r3 = 1
            if (r2 != 0) goto L_0x001b
            r5.mPreHeight = r0
            r5.mOriginHeight = r0
        L_0x0019:
            r2 = 0
            goto L_0x0020
        L_0x001b:
            if (r2 == r0) goto L_0x0019
            r5.mPreHeight = r0
            r2 = 1
        L_0x0020:
            if (r2 == 0) goto L_0x003e
            int r2 = r5.mOriginHeight
            int r2 = r2 - r0
            int r2 = java.lang.Math.abs(r2)
            r4 = 100
            if (r2 >= r4) goto L_0x002f
            r3 = 0
            goto L_0x0032
        L_0x002f:
            int r1 = r5.mOriginHeight
            int r1 = r1 - r0
        L_0x0032:
            com.thecrackertechnology.dragonterminal.utils.FullScreenHelper$KeyBoardListener r0 = r5.mKeyBoardListener
            if (r0 == 0) goto L_0x003e
            if (r0 != 0) goto L_0x003b
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x003b:
            r0.onKeyboardChange(r3, r1)
        L_0x003e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.utils.FullScreenHelper.monitorImeStatus():void");
    }

    /* access modifiers changed from: private */
    public final void possiblyResizeChildOfContent() {
        int computeUsableHeight = computeUsableHeight();
        if (computeUsableHeight != this.usableHeightPrevious) {
            View rootView = this.mChildOfContent.getRootView();
            Intrinsics.checkExpressionValueIsNotNull(rootView, "mChildOfContent.rootView");
            int height = rootView.getHeight();
            this.frameLayoutParams.height = height - (height - computeUsableHeight);
            this.mChildOfContent.requestLayout();
            this.usableHeightPrevious = computeUsableHeight;
        }
    }

    private final int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - rect.top;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/FullScreenHelper$Companion;", "", "()V", "injectActivity", "Lcom/thecrackertechnology/dragonterminal/utils/FullScreenHelper;", "activity", "Landroid/app/Activity;", "fullScreen", "", "recreate", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: FullScreenHelper.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FullScreenHelper injectActivity(Activity activity, boolean z, boolean z2) {
            Intrinsics.checkParameterIsNotNull(activity, "activity");
            return new FullScreenHelper(activity, z, z2, (DefaultConstructorMarker) null);
        }
    }
}
