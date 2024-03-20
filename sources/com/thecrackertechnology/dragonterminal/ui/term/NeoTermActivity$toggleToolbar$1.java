package com.thecrackertechnology.dragonterminal.ui.term;

import android.support.v7.widget.Toolbar;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: NeoTermActivity.kt */
final class NeoTermActivity$toggleToolbar$1 implements Runnable {
    final /* synthetic */ Toolbar $toolbar;

    NeoTermActivity$toggleToolbar$1(Toolbar toolbar) {
        this.$toolbar = toolbar;
    }

    public final void run() {
        this.$toolbar.setVisibility(8);
    }
}
