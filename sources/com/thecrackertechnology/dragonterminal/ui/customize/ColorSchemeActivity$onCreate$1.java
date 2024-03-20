package com.thecrackertechnology.dragonterminal.ui.customize;

import com.thecrackertechnology.dragonterminal.ui.customize.adapter.ColorItemAdapter;
import com.thecrackertechnology.dragonterminal.ui.customize.model.ColorItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/thecrackertechnology/dragonterminal/ui/customize/ColorSchemeActivity$onCreate$1", "Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/ColorItemAdapter$Listener;", "onModelClicked", "", "model", "Lcom/thecrackertechnology/dragonterminal/ui/customize/model/ColorItem;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ColorSchemeActivity.kt */
public final class ColorSchemeActivity$onCreate$1 implements ColorItemAdapter.Listener {
    final /* synthetic */ ColorSchemeActivity this$0;

    ColorSchemeActivity$onCreate$1(ColorSchemeActivity colorSchemeActivity) {
        this.this$0 = colorSchemeActivity;
    }

    public void onModelClicked(ColorItem colorItem) {
        Intrinsics.checkParameterIsNotNull(colorItem, "model");
        this.this$0.showItemEditor(colorItem);
    }
}
