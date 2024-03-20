package com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0006\u0010\u001d\u001a\u00020\u0005J\u0017\u0010\u001e\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010 R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006!"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/StatedControlButton;", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/ControlButton;", "text", "", "initState", "", "(Ljava/lang/String;Z)V", "getInitState", "()Z", "setInitState", "(Z)V", "toggleButton", "Landroid/widget/ToggleButton;", "getToggleButton", "()Landroid/widget/ToggleButton;", "setToggleButton", "(Landroid/widget/ToggleButton;)V", "makeButton", "Landroid/widget/Button;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "onClick", "", "view", "Landroid/view/View;", "readState", "setStatus", "status", "(Ljava/lang/Boolean;)V", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: StatedControlButton.kt */
public class StatedControlButton extends ControlButton {
    private boolean initState;
    private ToggleButton toggleButton;

    public StatedControlButton(String str) {
        this(str, false, 2, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StatedControlButton(String str, boolean z) {
        super(str);
        Intrinsics.checkParameterIsNotNull(str, "text");
        this.initState = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ StatedControlButton(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z);
    }

    public final boolean getInitState() {
        return this.initState;
    }

    public final void setInitState(boolean z) {
        this.initState = z;
    }

    public final ToggleButton getToggleButton() {
        return this.toggleButton;
    }

    public final void setToggleButton(ToggleButton toggleButton2) {
        this.toggleButton = toggleButton2;
    }

    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        ToggleButton toggleButton2 = this.toggleButton;
        setStatus(toggleButton2 != null ? Boolean.valueOf(toggleButton2.isChecked()) : null);
    }

    public Button makeButton(Context context, AttributeSet attributeSet, int i) {
        ToggleButton toggleButton2 = new ToggleButton(context, (AttributeSet) null, 16843567);
        toggleButton2.setClickable(true);
        if (this.initState) {
            toggleButton2.setChecked(true);
            toggleButton2.setTextColor(IExtraButton.Companion.getSELECTED_TEXT_COLOR());
        }
        this.toggleButton = toggleButton2;
        return toggleButton2;
    }

    private final void setStatus(Boolean bool) {
        int i;
        ToggleButton toggleButton2 = this.toggleButton;
        if (toggleButton2 != null && bool != null) {
            toggleButton2.setChecked(bool.booleanValue());
            if (bool.booleanValue()) {
                i = IExtraButton.Companion.getSELECTED_TEXT_COLOR();
            } else {
                i = IExtraButton.Companion.getNORMAL_TEXT_COLOR();
            }
            toggleButton2.setTextColor(i);
        }
    }

    public final boolean readState() {
        ToggleButton toggleButton2 = this.toggleButton;
        if (toggleButton2 == null) {
            return false;
        }
        if (toggleButton2.isPressed()) {
            return true;
        }
        boolean isChecked = toggleButton2.isChecked();
        if (isChecked) {
            toggleButton2.setChecked(false);
            toggleButton2.setTextColor(IExtraButton.Companion.getNORMAL_TEXT_COLOR());
        }
        return isChecked;
    }
}
