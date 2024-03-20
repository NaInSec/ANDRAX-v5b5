package com.thecrackertechnology.dragonterminal.ui.settings;

import android.content.DialogInterface;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.frontend.floating.TerminalDialog;
import com.thecrackertechnology.dragonterminal.utils.PackageUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: GeneralSettingsActivity.kt */
final class GeneralSettingsActivity$requestInstallShell$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ String $shellName;
    final /* synthetic */ GeneralSettingsActivity this$0;

    GeneralSettingsActivity$requestInstallShell$1(GeneralSettingsActivity generalSettingsActivity, String str) {
        this.this$0 = generalSettingsActivity;
        this.$shellName = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        PackageUtils.INSTANCE.apt(this.this$0, "install", new String[]{"-y", this.$shellName}, new Function2<Integer, TerminalDialog, Unit>(this) {
            final /* synthetic */ GeneralSettingsActivity$requestInstallShell$1 this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke(((Number) obj).intValue(), (TerminalDialog) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, TerminalDialog terminalDialog) {
                Intrinsics.checkParameterIsNotNull(terminalDialog, "dialog");
                if (i == 0) {
                    terminalDialog.dismiss();
                    this.this$0.this$0.postChangeShell(this.this$0.$shellName);
                    return;
                }
                terminalDialog.setTitle(this.this$0.this$0.getString(R.string.error));
            }
        });
    }
}
