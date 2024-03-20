package com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.combine.CombinedSequence;
import de.mrapp.android.util.view.ExpandableGridView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\nH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/IExtraButton;", "Landroid/view/View$OnClickListener;", "()V", "buttonKeys", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/combine/CombinedSequence;", "getButtonKeys", "()Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/combine/CombinedSequence;", "setButtonKeys", "(Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/combine/CombinedSequence;)V", "displayText", "", "getDisplayText", "()Ljava/lang/String;", "setDisplayText", "(Ljava/lang/String;)V", "makeButton", "Landroid/widget/Button;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "onClick", "", "view", "Landroid/view/View;", "toString", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: IExtraButton.kt */
public abstract class IExtraButton implements View.OnClickListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String KEY_ALT = KEY_ALT;
    /* access modifiers changed from: private */
    public static final String KEY_ARROW_DOWN = KEY_ARROW_DOWN;
    /* access modifiers changed from: private */
    public static final String KEY_ARROW_DOWN_TEXT = KEY_ARROW_DOWN_TEXT;
    /* access modifiers changed from: private */
    public static final String KEY_ARROW_LEFT = KEY_ARROW_LEFT;
    /* access modifiers changed from: private */
    public static final String KEY_ARROW_LEFT_TEXT = KEY_ARROW_LEFT_TEXT;
    /* access modifiers changed from: private */
    public static final String KEY_ARROW_RIGHT = KEY_ARROW_RIGHT;
    /* access modifiers changed from: private */
    public static final String KEY_ARROW_RIGHT_TEXT = KEY_ARROW_RIGHT_TEXT;
    /* access modifiers changed from: private */
    public static final String KEY_ARROW_UP = KEY_ARROW_UP;
    /* access modifiers changed from: private */
    public static final String KEY_ARROW_UP_TEXT = KEY_ARROW_UP_TEXT;
    /* access modifiers changed from: private */
    public static final String KEY_CTRL = KEY_CTRL;
    /* access modifiers changed from: private */
    public static final String KEY_END = KEY_END;
    /* access modifiers changed from: private */
    public static final String KEY_ESC = KEY_ESC;
    /* access modifiers changed from: private */
    public static final String KEY_HOME = KEY_HOME;
    /* access modifiers changed from: private */
    public static final String KEY_PAGE_DOWN = KEY_PAGE_DOWN;
    /* access modifiers changed from: private */
    public static final String KEY_PAGE_UP = KEY_PAGE_UP;
    /* access modifiers changed from: private */
    public static final String KEY_SHOW_ALL_BUTTONS = KEY_SHOW_ALL_BUTTONS;
    /* access modifiers changed from: private */
    public static final String KEY_TAB = KEY_TAB;
    /* access modifiers changed from: private */
    public static final String KEY_TOGGLE_IME = KEY_TOGGLE_IME;
    /* access modifiers changed from: private */
    public static int NORMAL_TEXT_COLOR = ((int) ExpandableGridView.PACKED_POSITION_VALUE_NULL);
    /* access modifiers changed from: private */
    public static int SELECTED_TEXT_COLOR = ((int) 4294901760L);
    private CombinedSequence buttonKeys;
    private String displayText;

    public abstract Button makeButton(Context context, AttributeSet attributeSet, int i);

    public abstract void onClick(View view);

    public final CombinedSequence getButtonKeys() {
        return this.buttonKeys;
    }

    public final void setButtonKeys(CombinedSequence combinedSequence) {
        this.buttonKeys = combinedSequence;
    }

    public final String getDisplayText() {
        return this.displayText;
    }

    public final void setDisplayText(String str) {
        this.displayText = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" { display: ");
        sb.append(this.displayText);
        sb.append(", code: ");
        CombinedSequence combinedSequence = this.buttonKeys;
        sb.append(combinedSequence != null ? combinedSequence.getKeys() : null);
        sb.append(" }");
        return sb.toString();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b%\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0014\u0010#\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0014\u0010%\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0014\u0010'\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u001a\u0010)\u001a\u00020*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u00020*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010,\"\u0004\b1\u0010.¨\u00067"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/IExtraButton$Companion;", "", "()V", "KEY_ALT", "", "getKEY_ALT", "()Ljava/lang/String;", "KEY_ARROW_DOWN", "getKEY_ARROW_DOWN", "KEY_ARROW_DOWN_TEXT", "getKEY_ARROW_DOWN_TEXT", "KEY_ARROW_LEFT", "getKEY_ARROW_LEFT", "KEY_ARROW_LEFT_TEXT", "getKEY_ARROW_LEFT_TEXT", "KEY_ARROW_RIGHT", "getKEY_ARROW_RIGHT", "KEY_ARROW_RIGHT_TEXT", "getKEY_ARROW_RIGHT_TEXT", "KEY_ARROW_UP", "getKEY_ARROW_UP", "KEY_ARROW_UP_TEXT", "getKEY_ARROW_UP_TEXT", "KEY_CTRL", "getKEY_CTRL", "KEY_END", "getKEY_END", "KEY_ESC", "getKEY_ESC", "KEY_HOME", "getKEY_HOME", "KEY_PAGE_DOWN", "getKEY_PAGE_DOWN", "KEY_PAGE_UP", "getKEY_PAGE_UP", "KEY_SHOW_ALL_BUTTONS", "getKEY_SHOW_ALL_BUTTONS", "KEY_TAB", "getKEY_TAB", "KEY_TOGGLE_IME", "getKEY_TOGGLE_IME", "NORMAL_TEXT_COLOR", "", "getNORMAL_TEXT_COLOR", "()I", "setNORMAL_TEXT_COLOR", "(I)V", "SELECTED_TEXT_COLOR", "getSELECTED_TEXT_COLOR", "setSELECTED_TEXT_COLOR", "sendKey", "", "view", "Landroid/view/View;", "keyName", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: IExtraButton.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getKEY_ESC() {
            return IExtraButton.KEY_ESC;
        }

        public final String getKEY_TAB() {
            return IExtraButton.KEY_TAB;
        }

        public final String getKEY_CTRL() {
            return IExtraButton.KEY_CTRL;
        }

        public final String getKEY_ALT() {
            return IExtraButton.KEY_ALT;
        }

        public final String getKEY_PAGE_UP() {
            return IExtraButton.KEY_PAGE_UP;
        }

        public final String getKEY_PAGE_DOWN() {
            return IExtraButton.KEY_PAGE_DOWN;
        }

        public final String getKEY_HOME() {
            return IExtraButton.KEY_HOME;
        }

        public final String getKEY_END() {
            return IExtraButton.KEY_END;
        }

        public final String getKEY_ARROW_UP_TEXT() {
            return IExtraButton.KEY_ARROW_UP_TEXT;
        }

        public final String getKEY_ARROW_DOWN_TEXT() {
            return IExtraButton.KEY_ARROW_DOWN_TEXT;
        }

        public final String getKEY_ARROW_LEFT_TEXT() {
            return IExtraButton.KEY_ARROW_LEFT_TEXT;
        }

        public final String getKEY_ARROW_RIGHT_TEXT() {
            return IExtraButton.KEY_ARROW_RIGHT_TEXT;
        }

        public final String getKEY_SHOW_ALL_BUTTONS() {
            return IExtraButton.KEY_SHOW_ALL_BUTTONS;
        }

        public final String getKEY_TOGGLE_IME() {
            return IExtraButton.KEY_TOGGLE_IME;
        }

        public final String getKEY_ARROW_UP() {
            return IExtraButton.KEY_ARROW_UP;
        }

        public final String getKEY_ARROW_DOWN() {
            return IExtraButton.KEY_ARROW_DOWN;
        }

        public final String getKEY_ARROW_LEFT() {
            return IExtraButton.KEY_ARROW_LEFT;
        }

        public final String getKEY_ARROW_RIGHT() {
            return IExtraButton.KEY_ARROW_RIGHT;
        }

        public final int getNORMAL_TEXT_COLOR() {
            return IExtraButton.NORMAL_TEXT_COLOR;
        }

        public final void setNORMAL_TEXT_COLOR(int i) {
            IExtraButton.NORMAL_TEXT_COLOR = i;
        }

        public final int getSELECTED_TEXT_COLOR() {
            return IExtraButton.SELECTED_TEXT_COLOR;
        }

        public final void setSELECTED_TEXT_COLOR(int i) {
            IExtraButton.SELECTED_TEXT_COLOR = i;
        }

        public final void sendKey(View view, String str) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(str, "keyName");
            Companion companion = this;
            int i = 20;
            String str2 = "";
            if (Intrinsics.areEqual((Object) str, (Object) companion.getKEY_ESC())) {
                i = 111;
            } else if (Intrinsics.areEqual((Object) str, (Object) companion.getKEY_TAB())) {
                i = 61;
            } else {
                if (!Intrinsics.areEqual((Object) str, (Object) companion.getKEY_ARROW_UP())) {
                    if (!Intrinsics.areEqual((Object) str, (Object) companion.getKEY_ARROW_LEFT())) {
                        if (!Intrinsics.areEqual((Object) str, (Object) companion.getKEY_ARROW_RIGHT())) {
                            if (!Intrinsics.areEqual((Object) str, (Object) companion.getKEY_ARROW_DOWN())) {
                                if (!Intrinsics.areEqual((Object) str, (Object) companion.getKEY_ARROW_UP_TEXT())) {
                                    if (!Intrinsics.areEqual((Object) str, (Object) companion.getKEY_ARROW_LEFT_TEXT())) {
                                        if (!Intrinsics.areEqual((Object) str, (Object) companion.getKEY_ARROW_RIGHT_TEXT())) {
                                            if (!Intrinsics.areEqual((Object) str, (Object) companion.getKEY_ARROW_DOWN_TEXT())) {
                                                if (Intrinsics.areEqual((Object) str, (Object) companion.getKEY_PAGE_UP())) {
                                                    i = 92;
                                                } else if (Intrinsics.areEqual((Object) str, (Object) companion.getKEY_PAGE_DOWN())) {
                                                    i = 93;
                                                } else if (Intrinsics.areEqual((Object) str, (Object) companion.getKEY_HOME())) {
                                                    i = 122;
                                                } else if (Intrinsics.areEqual((Object) str, (Object) companion.getKEY_END())) {
                                                    i = SDL_1_3_Keycodes.SDLK_CUT;
                                                } else {
                                                    if (Intrinsics.areEqual((Object) str, (Object) "―")) {
                                                        str = "-";
                                                    }
                                                    str2 = str;
                                                    i = 0;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        i = 22;
                    }
                    i = 21;
                }
                i = 19;
            }
            boolean z = true;
            if (i > 0) {
                view.dispatchKeyEvent(new KeyEvent(0, i));
                view.dispatchKeyEvent(new KeyEvent(1, i));
                return;
            }
            if (str2.length() <= 0) {
                z = false;
            }
            if (z) {
                TerminalView terminalView = (TerminalView) view.findViewById(R.id.terminal_view);
                Intrinsics.checkExpressionValueIsNotNull(terminalView, "terminalView");
                TerminalSession currentSession = terminalView.getCurrentSession();
                if (currentSession != null) {
                    currentSession.write(str2);
                }
            }
        }
    }
}
