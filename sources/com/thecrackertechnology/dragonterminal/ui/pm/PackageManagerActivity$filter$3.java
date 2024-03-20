package com.thecrackertechnology.dragonterminal.ui.pm;

import com.thecrackertechnology.dragonterminal.component.pm.NeoPackageInfo;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/thecrackertechnology/dragonterminal/component/pm/NeoPackageInfo;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: PackageManagerActivity.kt */
final class PackageManagerActivity$filter$3 extends Lambda implements Function1<NeoPackageInfo, String> {
    public static final PackageManagerActivity$filter$3 INSTANCE = new PackageManagerActivity$filter$3();

    PackageManagerActivity$filter$3() {
        super(1);
    }

    public final String invoke(NeoPackageInfo neoPackageInfo) {
        Intrinsics.checkParameterIsNotNull(neoPackageInfo, "it");
        String description = neoPackageInfo.getDescription();
        if (description == null) {
            Intrinsics.throwNpe();
        }
        return description;
    }
}
