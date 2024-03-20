package com.thecrackertechnology.dragonterminal.frontend.component.helper;

import java.io.File;
import java.io.FileFilter;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002 \u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "T", "Lcom/thecrackertechnology/dragonterminal/frontend/component/helper/ConfigFileBasedObject;", "it", "Ljava/io/File;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: ConfigFileBasedComponent.kt */
final class ConfigFileBasedComponent$Companion$NEOLANG_FILTER$1 implements FileFilter {
    public static final ConfigFileBasedComponent$Companion$NEOLANG_FILTER$1 INSTANCE = new ConfigFileBasedComponent$Companion$NEOLANG_FILTER$1();

    ConfigFileBasedComponent$Companion$NEOLANG_FILTER$1() {
    }

    public final boolean accept(File file) {
        Intrinsics.checkExpressionValueIsNotNull(file, "it");
        return Intrinsics.areEqual((Object) FilesKt.getExtension(file), (Object) "nl");
    }
}
