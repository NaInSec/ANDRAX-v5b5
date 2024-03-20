package com.thecrackertechnology.dragonterminal.ui.customize.adapter.holder;

import android.view.View;
import com.thecrackertechnology.dragonterminal.ui.customize.model.ColorItem;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: ColorItemViewHolder.kt */
final class ColorItemViewHolder$performBind$1 implements View.OnClickListener {
    final /* synthetic */ ColorItem $item;
    final /* synthetic */ ColorItemViewHolder this$0;

    ColorItemViewHolder$performBind$1(ColorItemViewHolder colorItemViewHolder, ColorItem colorItem) {
        this.this$0 = colorItemViewHolder;
        this.$item = colorItem;
    }

    public final void onClick(View view) {
        this.this$0.listener.onModelClicked(this.$item);
    }
}
