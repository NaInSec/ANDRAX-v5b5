package com.thecrackertechnology.dragonterminal.frontend.floating;

import android.content.DialogInterface;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "onCancel"}, k = 3, mv = {1, 1, 15})
/* compiled from: TerminalDialog.kt */
final class TerminalDialog$execute$1 implements DialogInterface.OnCancelListener {
    final /* synthetic */ TerminalDialog this$0;

    TerminalDialog$execute$1(TerminalDialog terminalDialog) {
        this.this$0 = terminalDialog;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        TerminalSession access$getTerminalSession$p = this.this$0.terminalSession;
        if (access$getTerminalSession$p != null) {
            access$getTerminalSession$p.finishIfRunning();
        }
        DialogInterface.OnCancelListener access$getCancelListener$p = this.this$0.cancelListener;
        if (access$getCancelListener$p != null) {
            access$getCancelListener$p.onCancel(dialogInterface);
        }
    }
}
