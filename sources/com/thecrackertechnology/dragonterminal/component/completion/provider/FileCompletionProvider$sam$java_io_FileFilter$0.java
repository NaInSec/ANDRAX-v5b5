package com.thecrackertechnology.dragonterminal.component.completion.provider;

import java.io.File;
import java.io.FileFilter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
/* compiled from: FileCompletionProvider.kt */
final class FileCompletionProvider$sam$java_io_FileFilter$0 implements FileFilter {
    private final /* synthetic */ Function1 function;

    FileCompletionProvider$sam$java_io_FileFilter$0(Function1 function1) {
        this.function = function1;
    }

    public final /* synthetic */ boolean accept(File file) {
        Object invoke = this.function.invoke(file);
        Intrinsics.checkExpressionValueIsNotNull(invoke, "invoke(...)");
        return ((Boolean) invoke).booleanValue();
    }
}
