package com.thecrackertechnology.dragonterminal.ui.term;

import com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab;
import com.thecrackertechnology.dragonterminal.utils.FullScreenHelper;
import de.mrapp.android.tabswitcher.Tab;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/thecrackertechnology/dragonterminal/ui/term/NeoTermActivity$onCreate$1", "Lcom/thecrackertechnology/dragonterminal/utils/FullScreenHelper$KeyBoardListener;", "onKeyboardChange", "", "isShow", "", "keyboardHeight", "", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoTermActivity.kt */
public final class NeoTermActivity$onCreate$1 implements FullScreenHelper.KeyBoardListener {
    final /* synthetic */ NeoTermActivity this$0;

    NeoTermActivity$onCreate$1(NeoTermActivity neoTermActivity) {
        this.this$0 = neoTermActivity;
    }

    public void onKeyboardChange(boolean z, int i) {
        if (this.this$0.getTabSwitcher().getSelectedTab() instanceof TermTab) {
            Tab selectedTab = this.this$0.getTabSwitcher().getSelectedTab();
            if (selectedTab != null) {
                this.this$0.toggleToolbar(((TermTab) selectedTab).getToolbar(), !z);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab");
        }
    }
}
