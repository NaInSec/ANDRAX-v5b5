package com.thecrackertechnology.dragonterminal.component.userscript;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/userscript/UserScript;", "", "scriptFile", "Ljava/io/File;", "(Ljava/io/File;)V", "getScriptFile", "()Ljava/io/File;", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: UserScript.kt */
public final class UserScript {
    private final File scriptFile;

    public UserScript(File file) {
        Intrinsics.checkParameterIsNotNull(file, "scriptFile");
        this.scriptFile = file;
    }

    public final File getScriptFile() {
        return this.scriptFile;
    }
}
