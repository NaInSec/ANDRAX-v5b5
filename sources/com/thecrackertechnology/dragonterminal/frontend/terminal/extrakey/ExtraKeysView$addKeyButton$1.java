package com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey;

import android.view.View;
import android.widget.Button;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.button.IExtraButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: ExtraKeysView.kt */
final class ExtraKeysView$addKeyButton$1 implements View.OnClickListener {
    final /* synthetic */ IExtraButton $extraButton;
    final /* synthetic */ Button $outerButton;
    final /* synthetic */ ExtraKeysView this$0;

    ExtraKeysView$addKeyButton$1(ExtraKeysView extraKeysView, Button button, IExtraButton iExtraButton) {
        this.this$0 = extraKeysView;
        this.$outerButton = button;
        this.$extraButton = iExtraButton;
    }

    public final void onClick(View view) {
        this.$outerButton.performHapticFeedback(3);
        View rootView = this.this$0.getRootView();
        IExtraButton iExtraButton = this.$extraButton;
        Intrinsics.checkExpressionValueIsNotNull(rootView, "root");
        iExtraButton.onClick(rootView);
    }
}
