package com.thecrackertechnology.dragonterminal.utils;

import android.content.Context;
import com.thecrackertechnology.dragonterminal.frontend.floating.TerminalDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JE\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\n2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\f¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/PackageUtils;", "", "()V", "apt", "", "context", "Landroid/content/Context;", "subCommand", "", "extraArgs", "", "callback", "Lkotlin/Function2;", "", "Lcom/thecrackertechnology/dragonterminal/frontend/floating/TerminalDialog;", "(Landroid/content/Context;Ljava/lang/String;[Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PackageUtils.kt */
public final class PackageUtils {
    public static final PackageUtils INSTANCE = new PackageUtils();

    private PackageUtils() {
    }

    public final void apt(Context context, String str, String[] strArr, Function2<? super Integer, ? super TerminalDialog, Unit> function2) {
        String[] strArr2;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(str, "subCommand");
        Intrinsics.checkParameterIsNotNull(function2, "callback");
        if (strArr != null) {
            SpreadBuilder spreadBuilder = new SpreadBuilder(3);
            spreadBuilder.add("");
            spreadBuilder.add(str);
            spreadBuilder.addSpread(strArr);
            strArr2 = (String[]) spreadBuilder.toArray(new String[spreadBuilder.size()]);
        } else {
            strArr2 = new String[]{"", str};
        }
        TerminalDialog execute = new TerminalDialog(context).onFinish(new PackageUtils$apt$1(function2)).imeEnabled(true).execute("", strArr2);
        execute.show("apt " + str);
    }
}
