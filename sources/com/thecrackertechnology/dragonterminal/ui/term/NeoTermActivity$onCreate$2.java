package com.thecrackertechnology.dragonterminal.ui.term;

import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: NeoTermActivity.kt */
final class NeoTermActivity$onCreate$2 implements Runnable {
    final /* synthetic */ NeoTermActivity this$0;

    NeoTermActivity$onCreate$2(NeoTermActivity neoTermActivity) {
        this.this$0 = neoTermActivity;
    }

    public final void run() {
        if (this.this$0.getTabSwitcher().getCount() > 0) {
            NeoPreference neoPreference = NeoPreference.INSTANCE;
            StringBuilder sb = new StringBuilder();
            File filesDir = AndraxApp.Companion.get().getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
            sb.append(filesDir.getAbsolutePath());
            sb.append("/bin/andraxshell.sh");
            neoPreference.setLoginShellName(sb.toString());
        }
    }
}
