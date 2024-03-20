package com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey;

import android.view.View;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.ControlButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/ExtraKeysView$EXPAND_BUTTONS$1", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/button/ControlButton;", "onClick", "", "view", "Landroid/view/View;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ExtraKeysView.kt */
public final class ExtraKeysView$EXPAND_BUTTONS$1 extends ControlButton {
    final /* synthetic */ ExtraKeysView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExtraKeysView$EXPAND_BUTTONS$1(ExtraKeysView extraKeysView, String str) {
        super(str);
        this.this$0 = extraKeysView;
    }

    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        ExtraKeysView.expandButtonPanel$default(this.this$0, (Boolean) null, 1, (Object) null);
    }
}
