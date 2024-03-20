package com.thecrackertechnology.dragonterminal.ui.pm;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.ui.pm.adapter.PackageAdapter;
import com.thecrackertechnology.dragonterminal.ui.pm.model.PackageModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/thecrackertechnology/dragonterminal/ui/pm/PackageManagerActivity$onCreate$1", "Lcom/thecrackertechnology/dragonterminal/ui/pm/adapter/PackageAdapter$Listener;", "onModelClicked", "", "model", "Lcom/thecrackertechnology/dragonterminal/ui/pm/model/PackageModel;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PackageManagerActivity.kt */
public final class PackageManagerActivity$onCreate$1 implements PackageAdapter.Listener {
    final /* synthetic */ PackageManagerActivity this$0;

    PackageManagerActivity$onCreate$1(PackageManagerActivity packageManagerActivity) {
        this.this$0 = packageManagerActivity;
    }

    public void onModelClicked(PackageModel packageModel) {
        Intrinsics.checkParameterIsNotNull(packageModel, "model");
        new AlertDialog.Builder(this.this$0).setTitle((CharSequence) packageModel.getPackageInfo().getPackageName()).setMessage((CharSequence) packageModel.getPackageDetails(this.this$0)).setPositiveButton((int) R.string.install, (DialogInterface.OnClickListener) new PackageManagerActivity$onCreate$1$onModelClicked$1(this, packageModel)).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).show();
    }
}
