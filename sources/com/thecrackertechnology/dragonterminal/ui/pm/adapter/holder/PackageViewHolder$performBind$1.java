package com.thecrackertechnology.dragonterminal.ui.pm.adapter.holder;

import android.view.View;
import com.thecrackertechnology.dragonterminal.ui.pm.model.PackageModel;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: PackageViewHolder.kt */
final class PackageViewHolder$performBind$1 implements View.OnClickListener {
    final /* synthetic */ PackageModel $item;
    final /* synthetic */ PackageViewHolder this$0;

    PackageViewHolder$performBind$1(PackageViewHolder packageViewHolder, PackageModel packageModel) {
        this.this$0 = packageViewHolder;
        this.$item = packageModel;
    }

    public final void onClick(View view) {
        this.this$0.listener.onModelClicked(this.$item);
    }
}
