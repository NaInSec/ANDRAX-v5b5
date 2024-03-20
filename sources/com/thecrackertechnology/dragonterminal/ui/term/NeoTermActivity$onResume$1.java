package com.thecrackertechnology.dragonterminal.ui.term;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.ui.term.tab.TermTab;
import com.thecrackertechnology.dragonterminal.ui.term.tab.XSessionTab;
import de.mrapp.android.tabswitcher.Animation;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.TabSwitcherListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ\"\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J(\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J(\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u0016"}, d2 = {"com/thecrackertechnology/dragonterminal/ui/term/NeoTermActivity$onResume$1", "Lde/mrapp/android/tabswitcher/TabSwitcherListener;", "onAllTabsRemoved", "", "tabSwitcher", "Lde/mrapp/android/tabswitcher/TabSwitcher;", "tabs", "", "Lde/mrapp/android/tabswitcher/Tab;", "animation", "Lde/mrapp/android/tabswitcher/Animation;", "(Lde/mrapp/android/tabswitcher/TabSwitcher;[Lde/mrapp/android/tabswitcher/Tab;Lde/mrapp/android/tabswitcher/Animation;)V", "onSelectionChanged", "selectedTabIndex", "", "selectedTab", "onSwitcherHidden", "onSwitcherShown", "onTabAdded", "index", "tab", "onTabRemoved", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoTermActivity.kt */
public final class NeoTermActivity$onResume$1 implements TabSwitcherListener {
    final /* synthetic */ NeoTermActivity this$0;

    public void onAllTabsRemoved(TabSwitcher tabSwitcher, Tab[] tabArr, Animation animation) {
        Intrinsics.checkParameterIsNotNull(tabSwitcher, "tabSwitcher");
        Intrinsics.checkParameterIsNotNull(tabArr, "tabs");
        Intrinsics.checkParameterIsNotNull(animation, "animation");
    }

    NeoTermActivity$onResume$1(NeoTermActivity neoTermActivity) {
        this.this$0 = neoTermActivity;
    }

    public void onSwitcherShown(TabSwitcher tabSwitcher) {
        Intrinsics.checkParameterIsNotNull(tabSwitcher, "tabSwitcher");
        this.this$0.getToolbar().setNavigationIcon((int) R.drawable.ic_add_box_white_24dp);
        this.this$0.getToolbar().setNavigationOnClickListener(this.this$0.getAddSessionListener());
        this.this$0.getToolbar().setBackgroundResource(17170445);
        this.this$0.getToolbar().animate().alpha(0.0f).setDuration(300).withEndAction(new NeoTermActivity$onResume$1$onSwitcherShown$1(this)).start();
    }

    public void onSwitcherHidden(TabSwitcher tabSwitcher) {
        Intrinsics.checkParameterIsNotNull(tabSwitcher, "tabSwitcher");
        this.this$0.getToolbar().setNavigationIcon((Drawable) null);
        this.this$0.getToolbar().setNavigationOnClickListener((View.OnClickListener) null);
        this.this$0.getToolbar().setBackgroundResource(R.color.black_fuck);
    }

    public void onSelectionChanged(TabSwitcher tabSwitcher, int i, Tab tab) {
        Intrinsics.checkParameterIsNotNull(tabSwitcher, "tabSwitcher");
        if (tab instanceof TermTab) {
            TermTab termTab = (TermTab) tab;
            if (termTab.getTermData().getTermSession() != null) {
                NeoPreference neoPreference = NeoPreference.INSTANCE;
                TerminalSession termSession = termTab.getTermData().getTermSession();
                if (termSession == null) {
                    Intrinsics.throwNpe();
                }
                neoPreference.storeCurrentSession(termSession);
            }
        }
    }

    public void onTabAdded(TabSwitcher tabSwitcher, int i, Tab tab, Animation animation) {
        Intrinsics.checkParameterIsNotNull(tabSwitcher, "tabSwitcher");
        Intrinsics.checkParameterIsNotNull(tab, "tab");
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        this.this$0.update_colors();
    }

    public void onTabRemoved(TabSwitcher tabSwitcher, int i, Tab tab, Animation animation) {
        Intrinsics.checkParameterIsNotNull(tabSwitcher, "tabSwitcher");
        Intrinsics.checkParameterIsNotNull(tab, "tab");
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        if (tab instanceof TermTab) {
            SessionRemover.INSTANCE.removeSession(this.this$0.termService, (TermTab) tab);
        } else if (tab instanceof XSessionTab) {
            SessionRemover.INSTANCE.removeXSession(this.this$0.termService, (XSessionTab) tab);
        }
    }
}
