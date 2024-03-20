package com.thecrackertechnology.dragonterminal.ui.customize;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: BaseCustomizeActivity.kt */
final class BaseCustomizeActivity$initCustomizationComponent$1 implements Runnable {
    final /* synthetic */ BaseCustomizeActivity this$0;

    BaseCustomizeActivity$initCustomizationComponent$1(BaseCustomizeActivity baseCustomizeActivity) {
        this.this$0 = baseCustomizeActivity;
    }

    public final void run() {
        this.this$0.getTerminalView().attachSession(this.this$0.getSession());
    }
}
