package com.thecrackertechnology.dragonterminal.component.font;

import java.io.File;
import java.io.FileFilter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "pathname", "Ljava/io/File;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: FontComponent.kt */
final class FontComponent$reloadFonts$1 implements FileFilter {
    public static final FontComponent$reloadFonts$1 INSTANCE = new FontComponent$reloadFonts$1();

    FontComponent$reloadFonts$1() {
    }

    public final boolean accept(File file) {
        Intrinsics.checkExpressionValueIsNotNull(file, "pathname");
        String name = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "pathname.name");
        return StringsKt.endsWith$default(name, ".ttf", false, 2, (Object) null);
    }
}
