package com.thecrackertechnology.dragonterminal.component.completion.provider;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "pathname", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: FileCompletionProvider.kt */
final class FileCompletionProvider$provideCandidates$1 extends Lambda implements Function1<File, Boolean> {
    final /* synthetic */ String $partName;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileCompletionProvider$provideCandidates$1(String str) {
        super(1);
        this.$partName = str;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((File) obj));
    }

    public final boolean invoke(File file) {
        Intrinsics.checkParameterIsNotNull(file, "pathname");
        String name = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "pathname.name");
        String str = this.$partName;
        Intrinsics.checkExpressionValueIsNotNull(str, "partName");
        return StringsKt.startsWith$default(name, str, false, 2, (Object) null);
    }
}
