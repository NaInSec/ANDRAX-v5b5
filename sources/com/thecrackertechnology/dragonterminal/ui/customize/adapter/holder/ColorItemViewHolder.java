package com.thecrackertechnology.dragonterminal.ui.customize.adapter.holder;

import android.view.View;
import android.widget.TextView;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.TerminalColors;
import com.thecrackertechnology.dragonterminal.ui.customize.adapter.ColorItemAdapter;
import com.thecrackertechnology.dragonterminal.ui.customize.model.ColorItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0014R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/holder/ColorItemViewHolder;", "Lcom/github/wrdlbrnft/sortedlistadapter/SortedListAdapter$ViewHolder;", "Lcom/thecrackertechnology/dragonterminal/ui/customize/model/ColorItem;", "rootView", "Landroid/view/View;", "listener", "Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/ColorItemAdapter$Listener;", "(Landroid/view/View;Lcom/thecrackertechnology/dragonterminal/ui/customize/adapter/ColorItemAdapter$Listener;)V", "colorItemDesc", "Landroid/widget/TextView;", "colorItemName", "colorView", "performBind", "", "item", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ColorItemViewHolder.kt */
public final class ColorItemViewHolder extends SortedListAdapter.ViewHolder<ColorItem> {
    private final TextView colorItemDesc;
    private final TextView colorItemName;
    private final View colorView;
    /* access modifiers changed from: private */
    public final ColorItemAdapter.Listener listener;
    private final View rootView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ColorItemViewHolder(View view, ColorItemAdapter.Listener listener2) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "rootView");
        Intrinsics.checkParameterIsNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.rootView = view;
        this.listener = listener2;
        View findViewById = this.rootView.findViewById(R.id.color_item_name);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "rootView.findViewById<Te…ew>(R.id.color_item_name)");
        this.colorItemName = (TextView) findViewById;
        View findViewById2 = this.rootView.findViewById(R.id.color_item_description);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "rootView.findViewById<Te…d.color_item_description)");
        this.colorItemDesc = (TextView) findViewById2;
        View findViewById3 = this.rootView.findViewById(R.id.color_item_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "rootView.findViewById<View>(R.id.color_item_view)");
        this.colorView = findViewById3;
    }

    /* access modifiers changed from: protected */
    public void performBind(ColorItem colorItem) {
        Intrinsics.checkParameterIsNotNull(colorItem, "item");
        this.rootView.setOnClickListener(new ColorItemViewHolder$performBind$1(this, colorItem));
        this.colorItemName.setText(colorItem.getColorName());
        this.colorItemDesc.setText(colorItem.getColorValue());
        if (colorItem.getColorValue().length() > 0) {
            int parse = TerminalColors.parse(colorItem.getColorValue());
            this.colorView.setBackgroundColor(parse);
            this.colorItemDesc.setTextColor(parse);
        }
    }
}
