package com.thecrackertechnology.dragonterminal.ui.customize;

import android.content.DialogInterface;
import android.widget.EditText;
import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: ColorSchemeActivity.kt */
final class ColorSchemeActivity$applyColorScheme$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ NeoColorScheme $colorScheme;
    final /* synthetic */ EditText $edit;
    final /* synthetic */ boolean $finishAfter;
    final /* synthetic */ ColorSchemeActivity this$0;

    ColorSchemeActivity$applyColorScheme$1(ColorSchemeActivity colorSchemeActivity, NeoColorScheme neoColorScheme, EditText editText, boolean z) {
        this.this$0 = colorSchemeActivity;
        this.$colorScheme = neoColorScheme;
        this.$edit = editText;
        this.$finishAfter = z;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        NeoColorScheme neoColorScheme = this.$colorScheme;
        EditText editText = this.$edit;
        Intrinsics.checkExpressionValueIsNotNull(editText, "edit");
        neoColorScheme.setColorName(editText.getText().toString());
        this.this$0.applyColorScheme(this.$colorScheme, this.$finishAfter);
    }
}
