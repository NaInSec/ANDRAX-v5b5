package com.thecrackertechnology.dragonterminal.ui.term.tab;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.thecrackertechnology.dragonterminal.Globals;
import com.thecrackertechnology.dragonterminal.NeoGLView;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.client.XSessionData;
import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: NeoTabDecorator.kt */
final class NeoTabDecorator$bindXSessionView$1 implements Runnable {
    final /* synthetic */ XSessionData $sessionData;
    final /* synthetic */ FrameLayout $videoLayout;
    final /* synthetic */ NeoTabDecorator this$0;

    NeoTabDecorator$bindXSessionView$1(NeoTabDecorator neoTabDecorator, XSessionData xSessionData, FrameLayout frameLayout) {
        this.this$0 = neoTabDecorator;
        this.$sessionData = xSessionData;
        this.$videoLayout = frameLayout;
    }

    public final void run() {
        NeoXorgViewClient client = this.$sessionData.getClient();
        if (client != null) {
            client.runOnUiThread(new Runnable(this) {
                final /* synthetic */ NeoTabDecorator$bindXSessionView$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    NeoGLView glView;
                    this.this$0.$sessionData.setGlView(new NeoGLView(this.this$0.$sessionData.getClient()));
                    NeoGLView glView2 = this.this$0.$sessionData.getGlView();
                    if (glView2 != null) {
                        glView2.setFocusableInTouchMode(true);
                    }
                    NeoGLView glView3 = this.this$0.$sessionData.getGlView();
                    if (glView3 != null) {
                        glView3.setFocusable(true);
                    }
                    NeoGLView glView4 = this.this$0.$sessionData.getGlView();
                    if (glView4 != null) {
                        glView4.requestFocus();
                    }
                    Unit unused = this.this$0.this$0.setViewLayerType(this.this$0.$sessionData.getGlView());
                    this.this$0.$videoLayout.addView(this.this$0.$sessionData.getGlView(), new FrameLayout.LayoutParams(-1, -1));
                    if (Globals.HideSystemMousePointer && Build.VERSION.SDK_INT >= 24 && (glView = this.this$0.$sessionData.getGlView()) != null) {
                        glView.setPointerIcon(PointerIcon.getSystemIcon(this.this$0.this$0.getContext(), 0));
                    }
                    Rect rect = new Rect();
                    this.this$0.$videoLayout.getWindowVisibleDisplayFrame(rect);
                    NeoGLView glView5 = this.this$0.$sessionData.getGlView();
                    if (glView5 != null) {
                        glView5.callNativeScreenVisibleRect(rect.left, rect.top, rect.right, rect.bottom);
                    }
                    this.this$0.$videoLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(this) {
                        final /* synthetic */ AnonymousClass1 this$0;

                        {
                            this.this$0 = r1;
                        }

                        public final void onGlobalLayout() {
                            final Rect rect = new Rect();
                            this.this$0.this$0.$videoLayout.getWindowVisibleDisplayFrame(rect);
                            View rootView = this.this$0.this$0.$videoLayout.getRootView();
                            Intrinsics.checkExpressionValueIsNotNull(rootView, "videoLayout.rootView");
                            final int height = rootView.getHeight() - this.this$0.this$0.$videoLayout.getHeight();
                            View rootView2 = this.this$0.this$0.$videoLayout.getRootView();
                            Intrinsics.checkExpressionValueIsNotNull(rootView2, "videoLayout.rootView");
                            final int width = rootView2.getWidth() - this.this$0.this$0.$videoLayout.getWidth();
                            Log.v("SDL", "Main window visible region changed: " + rect.left + ":" + rect.top + ":" + rect.width() + ":" + rect.height());
                            this.this$0.this$0.$videoLayout.postDelayed(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 this$0;

                                {
                                    this.this$0 = r1;
                                }

                                public final void run() {
                                    NeoGLView glView = this.this$0.this$0.this$0.$sessionData.getGlView();
                                    if (glView != null) {
                                        glView.callNativeScreenVisibleRect(rect.left + width, rect.top + height, rect.width(), rect.height());
                                    }
                                }
                            }, 300);
                            this.this$0.this$0.$videoLayout.postDelayed(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 this$0;

                                {
                                    this.this$0 = r1;
                                }

                                public final void run() {
                                    NeoGLView glView = this.this$0.this$0.this$0.$sessionData.getGlView();
                                    if (glView != null) {
                                        glView.callNativeScreenVisibleRect(rect.left + width, rect.top + height, rect.width(), rect.height());
                                    }
                                }
                            }, 600);
                        }
                    });
                }
            });
        }
    }
}
