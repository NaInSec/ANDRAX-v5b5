package com.thecrackertechnology.dragonterminal.ui.pm.adapter.holder;

import android.view.View;
import android.widget.TextView;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.ui.pm.adapter.PackageAdapter;
import com.thecrackertechnology.dragonterminal.ui.pm.model.PackageModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/holder/PackageViewHolder;", "Lcom/github/wrdlbrnft/sortedlistadapter/SortedListAdapter$ViewHolder;", "Lcom/thecrackertechnology/dragonterminal/ui/pm/model/PackageModel;", "rootView", "Landroid/view/View;", "listener", "Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/PackageAdapter$Listener;", "(Landroid/view/View;Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/PackageAdapter$Listener;)V", "packageDescView", "Landroid/widget/TextView;", "packageNameView", "performBind", "", "item", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PackageViewHolder.kt */
public final class PackageViewHolder extends SortedListAdapter.ViewHolder<PackageModel> {
    /* access modifiers changed from: private */
    public final PackageAdapter.Listener listener;
    private final TextView packageDescView;
    private final TextView packageNameView;
    private final View rootView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PackageViewHolder(View view, PackageAdapter.Listener listener2) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "rootView");
        Intrinsics.checkParameterIsNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.rootView = view;
        this.listener = listener2;
        View findViewById = this.rootView.findViewById(R.id.package_item_name);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "rootView.findViewById(R.id.package_item_name)");
        this.packageNameView = (TextView) findViewById;
        View findViewById2 = this.rootView.findViewById(R.id.package_item_desc);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "rootView.findViewById(R.id.package_item_desc)");
        this.packageDescView = (TextView) findViewById2;
    }

    /* access modifiers changed from: protected */
    public void performBind(PackageModel packageModel) {
        Intrinsics.checkParameterIsNotNull(packageModel, "item");
        this.rootView.setOnClickListener(new PackageViewHolder$performBind$1(this, packageModel));
        this.packageNameView.setText(packageModel.getPackageInfo().getPackageName());
        this.packageDescView.setText(packageModel.getPackageInfo().getDescription());
    }
}
