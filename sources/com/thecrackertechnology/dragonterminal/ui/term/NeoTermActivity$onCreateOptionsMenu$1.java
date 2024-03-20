package com.thecrackertechnology.dragonterminal.ui.term;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab;
import de.mrapp.android.tabswitcher.Tab;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: NeoTermActivity.kt */
final class NeoTermActivity$onCreateOptionsMenu$1 implements View.OnClickListener {
    final /* synthetic */ NeoTermActivity this$0;

    NeoTermActivity$onCreateOptionsMenu$1(NeoTermActivity neoTermActivity) {
        this.this$0 = neoTermActivity;
    }

    public final void onClick(View view) {
        if (!this.this$0.getTabSwitcher().isSwitcherShown()) {
            Object systemService = this.this$0.getSystemService("input_method");
            if (systemService != null) {
                if (((InputMethodManager) systemService).isActive() && (this.this$0.getTabSwitcher().getSelectedTab() instanceof TermTab)) {
                    Tab selectedTab = this.this$0.getTabSwitcher().getSelectedTab();
                    if (selectedTab != null) {
                        ((TermTab) selectedTab).requireHideIme();
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab");
                    }
                }
                this.this$0.toggleSwitcher(true, true);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
        this.this$0.toggleSwitcher(false, true);
    }
}
