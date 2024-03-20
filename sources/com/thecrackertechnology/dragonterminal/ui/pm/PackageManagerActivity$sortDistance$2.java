package com.thecrackertechnology.dragonterminal.ui.pm;

import com.thecrackertechnology.dragonterminal.ui.pm.model.PackageModel;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001 \u0005*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00030\u00032&\u0010\u0006\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001 \u0005*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "l", "Lkotlin/Pair;", "Lcom/thecrackertechnology/dragonterminal/ui/pm/model/PackageModel;", "kotlin.jvm.PlatformType", "r", "compare"}, k = 3, mv = {1, 1, 15})
/* compiled from: PackageManagerActivity.kt */
final class PackageManagerActivity$sortDistance$2<T> implements Comparator<Pair<? extends PackageModel, ? extends Integer>> {
    public static final PackageManagerActivity$sortDistance$2 INSTANCE = new PackageManagerActivity$sortDistance$2();

    PackageManagerActivity$sortDistance$2() {
    }

    public final int compare(Pair<PackageModel, Integer> pair, Pair<PackageModel, Integer> pair2) {
        return Intrinsics.compare(pair2.getSecond().intValue(), pair.getSecond().intValue());
    }
}
