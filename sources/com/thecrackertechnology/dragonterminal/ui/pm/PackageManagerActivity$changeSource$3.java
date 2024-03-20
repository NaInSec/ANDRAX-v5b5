package com.thecrackertechnology.dragonterminal.ui.pm;

import android.content.DialogInterface;
import com.thecrackertechnology.dragonterminal.component.pm.Source;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "which", "", "isChecked", "", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: PackageManagerActivity.kt */
final class PackageManagerActivity$changeSource$3 implements DialogInterface.OnMultiChoiceClickListener {
    final /* synthetic */ List $sourceList;

    PackageManagerActivity$changeSource$3(List list) {
        this.$sourceList = list;
    }

    public final void onClick(DialogInterface dialogInterface, int i, boolean z) {
        ((Source) this.$sourceList.get(i)).enabled = z;
    }
}
