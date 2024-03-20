package com.thecrackertechnology.dragonterminal.ui.pm.model;

import android.content.Context;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.component.pm.NeoPackageInfo;
import com.thecrackertechnology.dragonterminal.utils.FileUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u001b\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000e\u001a\u0002H\rH\u0016¢\u0006\u0002\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\f\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000e\u001a\u0002H\rH\u0016¢\u0006\u0002\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/ui/pm/model/PackageModel;", "Lcom/github/wrdlbrnft/sortedlistadapter/SortedListAdapter$ViewModel;", "packageInfo", "Lcom/thecrackertechnology/dragonterminal/component/pm/NeoPackageInfo;", "(Lcom/thecrackertechnology/dragonterminal/component/pm/NeoPackageInfo;)V", "getPackageInfo", "()Lcom/thecrackertechnology/dragonterminal/component/pm/NeoPackageInfo;", "getPackageDetails", "", "context", "Landroid/content/Context;", "isContentTheSameAs", "", "T", "t", "(Ljava/lang/Object;)Z", "isSameModelAs", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PackageModel.kt */
public final class PackageModel implements SortedListAdapter.ViewModel {
    private final NeoPackageInfo packageInfo;

    public PackageModel(NeoPackageInfo neoPackageInfo) {
        Intrinsics.checkParameterIsNotNull(neoPackageInfo, "packageInfo");
        this.packageInfo = neoPackageInfo;
    }

    public final NeoPackageInfo getPackageInfo() {
        return this.packageInfo;
    }

    public <T> boolean isSameModelAs(T t) {
        if (t instanceof PackageModel) {
            return Intrinsics.areEqual((Object) ((PackageModel) t).packageInfo.getPackageName(), (Object) this.packageInfo.getPackageName());
        }
        return false;
    }

    public <T> boolean isContentTheSameAs(T t) {
        return isSameModelAs(t);
    }

    public final String getPackageDetails(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String string = context.getString(R.string.package_details, new Object[]{this.packageInfo.getPackageName(), this.packageInfo.getVersion(), this.packageInfo.getDependenciesString(), FileUtils.INSTANCE.formatSizeInKB(this.packageInfo.getInstalledSizeInBytes()), this.packageInfo.getDescription(), this.packageInfo.getHomePage()});
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.stri…on, packageInfo.homePage)");
        return string;
    }
}
