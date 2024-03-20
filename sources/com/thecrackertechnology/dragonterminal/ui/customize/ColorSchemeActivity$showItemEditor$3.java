package com.thecrackertechnology.dragonterminal.ui.customize;

import android.content.DialogInterface;
import com.thecrackertechnology.dragonterminal.backend.TerminalColors;
import com.thecrackertechnology.dragonterminal.ui.customize.model.ColorItem;
import es.dmoral.coloromatic.ColorOMaticDialog;
import es.dmoral.coloromatic.IndicatorMode;
import es.dmoral.coloromatic.OnColorSelectedListener;
import es.dmoral.coloromatic.colormode.ColorMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: ColorSchemeActivity.kt */
final class ColorSchemeActivity$showItemEditor$3 implements DialogInterface.OnClickListener {
    final /* synthetic */ Function1 $applyColor;
    final /* synthetic */ ColorItem $model;
    final /* synthetic */ ColorSchemeActivity this$0;

    ColorSchemeActivity$showItemEditor$3(ColorSchemeActivity colorSchemeActivity, ColorItem colorItem, Function1 function1) {
        this.this$0 = colorSchemeActivity;
        this.$model = colorItem;
        this.$applyColor = function1;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        new ColorOMaticDialog.Builder().initialColor(TerminalColors.parse(this.$model.getColorValue())).colorMode(ColorMode.RGB).indicatorMode(IndicatorMode.HEX).onColorSelected(new OnColorSelectedListener(this) {
            final /* synthetic */ ColorSchemeActivity$showItemEditor$3 this$0;

            {
                this.this$0 = r1;
            }

            public final void onColorSelected(int i) {
                Function1 function1 = this.this$0.$applyColor;
                StringBuilder sb = new StringBuilder();
                sb.append('#');
                String hexString = Integer.toHexString(i);
                Intrinsics.checkExpressionValueIsNotNull(hexString, "Integer.toHexString(newColor)");
                if (hexString != null) {
                    String substring = hexString.substring(2);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                    sb.append(substring);
                    function1.invoke(sb.toString());
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }).showColorIndicator(true).create().show(this.this$0.getSupportFragmentManager(), "ColorOMaticDialog");
    }
}
