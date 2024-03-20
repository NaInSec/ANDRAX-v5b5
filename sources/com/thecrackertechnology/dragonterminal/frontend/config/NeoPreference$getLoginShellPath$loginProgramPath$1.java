package com.thecrackertechnology.dragonterminal.frontend.config;

import com.thecrackertechnology.andrax.AndraxApp;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: NeoPreference.kt */
final class NeoPreference$getLoginShellPath$loginProgramPath$1 extends Lambda implements Function0<String> {
    public static final NeoPreference$getLoginShellPath$loginProgramPath$1 INSTANCE = new NeoPreference$getLoginShellPath$loginProgramPath$1();

    NeoPreference$getLoginShellPath$loginProgramPath$1() {
        super(0);
    }

    public final String invoke() {
        NeoPreference neoPreference = NeoPreference.INSTANCE;
        StringBuilder sb = new StringBuilder();
        File filesDir = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "AndraxApp.get().filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/bin/");
        sb.append(DefaultValues.INSTANCE.getLoginShell());
        neoPreference.setLoginShellName(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        File filesDir2 = AndraxApp.Companion.get().getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir2, "AndraxApp.get().filesDir");
        sb2.append(filesDir2.getAbsolutePath());
        sb2.append("/bin/");
        sb2.append(DefaultValues.INSTANCE.getLoginShell());
        return sb2.toString();
    }
}
