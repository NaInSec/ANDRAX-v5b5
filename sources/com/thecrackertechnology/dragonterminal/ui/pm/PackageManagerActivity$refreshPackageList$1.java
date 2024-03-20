package com.thecrackertechnology.dragonterminal.ui.pm;

import android.widget.Toast;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.component.pm.NeoPackageInfo;
import com.thecrackertechnology.dragonterminal.component.pm.PackageComponent;
import com.thecrackertechnology.dragonterminal.component.pm.SourceHelper;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.ui.pm.model.PackageModel;
import java.io.File;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: PackageManagerActivity.kt */
final class PackageManagerActivity$refreshPackageList$1 implements Runnable {
    final /* synthetic */ PackageManagerActivity this$0;

    PackageManagerActivity$refreshPackageList$1(PackageManagerActivity packageManagerActivity) {
        this.this$0 = packageManagerActivity;
    }

    public final void run() {
        PackageComponent packageComponent = (PackageComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, PackageComponent.class, false, 2, (Object) null);
        List<File> detectSourceFiles = SourceHelper.INSTANCE.detectSourceFiles();
        packageComponent.clearPackages();
        for (File reloadPackages : detectSourceFiles) {
            packageComponent.reloadPackages(reloadPackages, false);
        }
        Collection<NeoPackageInfo> values = packageComponent.getPackages().values();
        Intrinsics.checkExpressionValueIsNotNull(values, "pm.packages.values");
        Collection models = this.this$0.getModels();
        for (NeoPackageInfo neoPackageInfo : values) {
            Intrinsics.checkExpressionValueIsNotNull(neoPackageInfo, "it");
            models.add(new PackageModel(neoPackageInfo));
        }
        this.this$0.runOnUiThread(new Runnable(this) {
            final /* synthetic */ PackageManagerActivity$refreshPackageList$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                this.this$0.this$0.getAdapter().edit().replaceAll(this.this$0.this$0.getModels()).commit();
                if (this.this$0.this$0.getModels().isEmpty()) {
                    Toast.makeText(this.this$0.this$0, R.string.package_list_empty, 0).show();
                    this.this$0.this$0.changeSource();
                }
            }
        });
    }
}
