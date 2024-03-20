package com.thecrackertechnology.dragonterminal.frontend.session.xorg;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XSession;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: XSession.kt */
final class XSession$showScreenKeyboardWithoutTextInputField$1 implements Runnable {
    final /* synthetic */ InputMethodManager $inputManager;
    final /* synthetic */ int $keyboard;
    final /* synthetic */ XSession this$0;

    XSession$showScreenKeyboardWithoutTextInputField$1(XSession xSession, int i, InputMethodManager inputMethodManager) {
        this.this$0 = xSession;
        this.$keyboard = i;
        this.$inputManager = inputMethodManager;
    }

    public final void run() {
        if (this.$keyboard == 0) {
            this.$inputManager.toggleSoftInput(2, 0);
            this.$inputManager.showSoftInput(this.this$0.getGLView(), 2);
            this.this$0.getWindow().setSoftInputMode(4);
        } else if (this.this$0.getMSessionData().getScreenKeyboard() == null) {
            final XSession.BuiltInKeyboardView builtInKeyboardView = new XSession.BuiltInKeyboardView(this.this$0.mActivity, (AttributeSet) null);
            builtInKeyboardView.setAlpha(0.7f);
            builtInKeyboardView.changeKeyboard(this.$keyboard);
            builtInKeyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener(this) {
                final /* synthetic */ XSession$showScreenKeyboardWithoutTextInputField$1 this$0;

                public void onKey(int i, int[] iArr) {
                    Intrinsics.checkParameterIsNotNull(iArr, "p2");
                }

                public void onText(CharSequence charSequence) {
                    Intrinsics.checkParameterIsNotNull(charSequence, "p1");
                }

                public void swipeDown() {
                }

                public void swipeLeft() {
                }

                public void swipeRight() {
                }

                public void swipeUp() {
                }

                {
                    this.this$0 = r1;
                }

                public void onPress(int i) {
                    if (i != 4 && i >= 0) {
                        Keyboard keyboard = builtInKeyboardView.getKeyboard();
                        Intrinsics.checkExpressionValueIsNotNull(keyboard, "builtinKeyboard.keyboard");
                        for (Keyboard.Key next : keyboard.getKeys()) {
                            if (next.sticky && i == next.codes[0]) {
                                return;
                            }
                        }
                        if (i > 100000) {
                            i -= 100000;
                            this.this$0.this$0.mActivity.onKeyDown(59, new KeyEvent(0, 59));
                        }
                        this.this$0.this$0.mActivity.onKeyDown(i, new KeyEvent(0, i));
                    }
                }

                public void onRelease(int i) {
                    boolean z;
                    if (i == 4) {
                        builtInKeyboardView.setOnKeyboardActionListener((KeyboardView.OnKeyboardActionListener) null);
                        this.this$0.this$0.showScreenKeyboardWithoutTextInputField(0);
                    } else if (i == -1) {
                        XSession.BuiltInKeyboardView builtInKeyboardView = builtInKeyboardView;
                        builtInKeyboardView.setShift(!builtInKeyboardView.getShift());
                        if (!builtInKeyboardView.getShift() || builtInKeyboardView.getAlt()) {
                            this.this$0.this$0.mActivity.onKeyUp(59, new KeyEvent(1, 59));
                        } else {
                            this.this$0.this$0.mActivity.onKeyDown(59, new KeyEvent(0, 59));
                        }
                        builtInKeyboardView.changeKeyboard(this.this$0.$keyboard);
                    } else if (i == -6) {
                        XSession.BuiltInKeyboardView builtInKeyboardView2 = builtInKeyboardView;
                        builtInKeyboardView2.setAlt(!builtInKeyboardView2.getAlt());
                        if (builtInKeyboardView.getAlt()) {
                            this.this$0.this$0.mActivity.onKeyUp(59, new KeyEvent(1, 59));
                        } else {
                            builtInKeyboardView.setShift(false);
                        }
                        builtInKeyboardView.changeKeyboard(this.this$0.$keyboard);
                    } else if (i >= 0) {
                        Keyboard keyboard = builtInKeyboardView.getKeyboard();
                        Intrinsics.checkExpressionValueIsNotNull(keyboard, "builtinKeyboard.keyboard");
                        for (Keyboard.Key next : keyboard.getKeys()) {
                            if (next.sticky && i == next.codes[0]) {
                                if (next.on) {
                                    builtInKeyboardView.getStickyKeys().add(Integer.valueOf(i));
                                    this.this$0.this$0.mActivity.onKeyDown(i, new KeyEvent(0, i));
                                    return;
                                }
                                builtInKeyboardView.getStickyKeys().remove(Integer.valueOf(i));
                                this.this$0.this$0.mActivity.onKeyUp(i, new KeyEvent(1, i));
                                return;
                            }
                        }
                        if (i > 100000) {
                            i -= 100000;
                            z = true;
                        } else {
                            z = false;
                        }
                        this.this$0.this$0.mActivity.onKeyUp(i, new KeyEvent(1, i));
                        if (z) {
                            this.this$0.this$0.mActivity.onKeyUp(59, new KeyEvent(1, 59));
                            builtInKeyboardView.getStickyKeys().remove(59);
                            Keyboard keyboard2 = builtInKeyboardView.getKeyboard();
                            Intrinsics.checkExpressionValueIsNotNull(keyboard2, "builtinKeyboard.keyboard");
                            for (Keyboard.Key next2 : keyboard2.getKeys()) {
                                if (next2.sticky && next2.codes[0] == 59 && next2.on) {
                                    next2.on = false;
                                    builtInKeyboardView.invalidateAllKeys();
                                }
                            }
                        }
                    }
                }
            });
            this.this$0.getMSessionData().setScreenKeyboard(builtInKeyboardView);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
            FrameLayout videoLayout = this.this$0.getMSessionData().getVideoLayout();
            if (videoLayout == null) {
                Intrinsics.throwNpe();
            }
            videoLayout.addView(this.this$0.getMSessionData().getScreenKeyboard(), layoutParams);
        }
    }
}
