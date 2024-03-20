package com.thecrackertechnology.dragonterminal.component.completion.provider;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/completion/provider/ProgramCompletionProvider;", "Lcom/thecrackertechnology/dragonterminal/component/completion/provider/FileCompletionProvider;", "()V", "providerName", "", "getProviderName", "()Ljava/lang/String;", "generateDesc", "file", "Ljava/io/File;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ProgramCompletionProvider.kt */
public final class ProgramCompletionProvider extends FileCompletionProvider {
    public String getProviderName() {
        return "NeoTermProvider.ProgramCompletionProvider";
    }

    public String generateDesc(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return file.canExecute() ? "<Program>" : super.generateDesc(file);
    }
}
