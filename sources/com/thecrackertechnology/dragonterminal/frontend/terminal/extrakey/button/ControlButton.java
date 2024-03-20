package com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/ControlButton;", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/TextButton;", "text", "", "(Ljava/lang/String;)V", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ControlButton.kt */
public class ControlButton extends TextButton {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ControlButton(String str) {
        super(str, false);
        Intrinsics.checkParameterIsNotNull(str, "text");
    }
}
