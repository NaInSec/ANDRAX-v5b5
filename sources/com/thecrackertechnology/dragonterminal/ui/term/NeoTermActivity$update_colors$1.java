package com.thecrackertechnology.dragonterminal.ui.term;

import com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab;
import de.mrapp.android.tabswitcher.Tab;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: NeoTermActivity.kt */
final class NeoTermActivity$update_colors$1 implements Runnable {
    final /* synthetic */ NeoTermActivity this$0;

    NeoTermActivity$update_colors$1(NeoTermActivity neoTermActivity) {
        this.this$0 = neoTermActivity;
    }

    public final void run() {
        if (this.this$0.getTabSwitcher().getCount() > 0) {
            Tab selectedTab = this.this$0.getTabSwitcher().getSelectedTab();
            if (selectedTab instanceof TermTab) {
                ((TermTab) selectedTab).updateColorScheme();
            }
        }
    }
}
