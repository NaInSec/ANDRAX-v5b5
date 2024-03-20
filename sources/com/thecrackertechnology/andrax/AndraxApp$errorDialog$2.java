package com.thecrackertechnology.andrax;

import android.content.DialogInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "onDismiss"}, k = 3, mv = {1, 1, 15})
/* compiled from: AndraxApp.kt */
final class AndraxApp$errorDialog$2 implements DialogInterface.OnDismissListener {
    final /* synthetic */ Function0 $dismissCallback;

    AndraxApp$errorDialog$2(Function0 function0) {
        this.$dismissCallback = function0;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        Function0 function0 = this.$dismissCallback;
        if (function0 != null) {
            Unit unit = (Unit) function0.invoke();
        }
    }
}
