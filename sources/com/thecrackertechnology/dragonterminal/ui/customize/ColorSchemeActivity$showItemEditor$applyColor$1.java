package com.thecrackertechnology.dragonterminal.ui.customize;

import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import com.thecrackertechnology.dragonterminal.ui.customize.model.ColorItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "newColor", "", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: ColorSchemeActivity.kt */
final class ColorSchemeActivity$showItemEditor$applyColor$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ ColorItem $model;
    final /* synthetic */ ColorSchemeActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ColorSchemeActivity$showItemEditor$applyColor$1(ColorSchemeActivity colorSchemeActivity, ColorItem colorItem) {
        super(1);
        this.this$0 = colorSchemeActivity;
        this.$model = colorItem;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(String str) {
        Intrinsics.checkParameterIsNotNull(str, "newColor");
        this.$model.setColorValue(str);
        this.this$0.getAdapter().notifyItemChanged(this.this$0.getAdapter().getColorList().indexOf(this.$model));
        ColorSchemeActivity.access$getEditingColorScheme$p(this.this$0).setColor(this.$model.getColorType(), this.$model.getColorValue());
        this.this$0.colorSchemeComponent.applyColorScheme(this.this$0.getTerminalView(), (ExtraKeysView) null, ColorSchemeActivity.access$getEditingColorScheme$p(this.this$0));
        this.this$0.setChanged(true);
    }
}
