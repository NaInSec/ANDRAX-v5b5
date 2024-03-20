package com.thecrackertechnology.dragonterminal.ui.term;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import de.mrapp.android.tabswitcher.TabSwitcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00040\u00042\u000e\u0010\u0005\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Landroid/support/v4/view/WindowInsetsCompat;", "kotlin.jvm.PlatformType", "<anonymous parameter 0>", "Landroid/view/View;", "insets", "onApplyWindowInsets"}, k = 3, mv = {1, 1, 15})
/* compiled from: NeoTermActivity.kt */
final class NeoTermActivity$createWindowInsetsListener$1 implements OnApplyWindowInsetsListener {
    final /* synthetic */ NeoTermActivity this$0;

    NeoTermActivity$createWindowInsetsListener$1(NeoTermActivity neoTermActivity) {
        this.this$0 = neoTermActivity;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        TabSwitcher tabSwitcher = this.this$0.getTabSwitcher();
        Intrinsics.checkExpressionValueIsNotNull(windowInsetsCompat, "insets");
        tabSwitcher.setPadding(windowInsetsCompat.getSystemWindowInsetLeft(), windowInsetsCompat.getSystemWindowInsetTop(), windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
        return windowInsetsCompat;
    }
}
