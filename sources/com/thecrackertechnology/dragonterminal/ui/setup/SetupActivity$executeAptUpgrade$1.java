package com.thecrackertechnology.dragonterminal.ui.setup;

import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.frontend.floating.TerminalDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "exitStatus", "", "dialog", "Lcom/thecrackertechnology/dragonterminal/frontend/floating/TerminalDialog;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: SetupActivity.kt */
final class SetupActivity$executeAptUpgrade$1 extends Lambda implements Function2<Integer, TerminalDialog, Unit> {
    final /* synthetic */ SetupActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SetupActivity$executeAptUpgrade$1(SetupActivity setupActivity) {
        super(2);
        this.this$0 = setupActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), (TerminalDialog) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, TerminalDialog terminalDialog) {
        Intrinsics.checkParameterIsNotNull(terminalDialog, "dialog");
        if (i == 0) {
            terminalDialog.dismiss();
            this.this$0.finish();
            return;
        }
        terminalDialog.setTitle(this.this$0.getString(R.string.error));
    }
}
