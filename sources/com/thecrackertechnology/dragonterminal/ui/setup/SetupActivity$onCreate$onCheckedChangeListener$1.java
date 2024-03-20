package com.thecrackertechnology.dragonterminal.ui.setup;

import android.widget.CompoundButton;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "button", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "checked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 15})
/* compiled from: SetupActivity.kt */
final class SetupActivity$onCreate$onCheckedChangeListener$1 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ TextView $tipText;
    final /* synthetic */ SetupActivity this$0;

    SetupActivity$onCreate$onCheckedChangeListener$1(SetupActivity setupActivity, TextView textView) {
        this.this$0 = setupActivity;
        this.$tipText = textView;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            Intrinsics.checkExpressionValueIsNotNull(compoundButton, "button");
            int indexOf = ArraysKt.indexOf((T[]) this.this$0.hintMapping, Integer.valueOf(compoundButton.getId()));
            if (indexOf >= 0 && indexOf % 2 == 0) {
                this.$tipText.setText(this.this$0.hintMapping[indexOf + 1].intValue());
            }
        }
    }
}
