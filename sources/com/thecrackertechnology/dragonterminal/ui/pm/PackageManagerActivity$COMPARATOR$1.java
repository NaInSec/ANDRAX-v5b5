package com.thecrackertechnology.dragonterminal.ui.pm;

import com.thecrackertechnology.dragonterminal.ui.pm.model.PackageModel;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "a", "Lcom/thecrackertechnology/dragonterminal/ui/pm/model/PackageModel;", "kotlin.jvm.PlatformType", "b", "compare"}, k = 3, mv = {1, 1, 15})
/* compiled from: PackageManagerActivity.kt */
final class PackageManagerActivity$COMPARATOR$1<T> implements Comparator<M> {
    public static final PackageManagerActivity$COMPARATOR$1 INSTANCE = new PackageManagerActivity$COMPARATOR$1();

    PackageManagerActivity$COMPARATOR$1() {
    }

    public final int compare(PackageModel packageModel, PackageModel packageModel2) {
        String packageName = packageModel.getPackageInfo().getPackageName();
        if (packageName == null) {
            Intrinsics.throwNpe();
        }
        String packageName2 = packageModel2.getPackageInfo().getPackageName();
        if (packageName2 == null) {
            Intrinsics.throwNpe();
        }
        return packageName.compareTo(packageName2);
    }
}
