package com.thecrackertechnology.dragonterminal.component.codegen;

import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenObject;
import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenerator;
import com.thecrackertechnology.dragonterminal.frontend.component.NeoComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016¨\u0006\u000b"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/codegen/CodeGenComponent;", "Lcom/thecrackertechnology/dragonterminal/frontend/component/NeoComponent;", "()V", "newGenerator", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenerator;", "codeObject", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenObject;", "onServiceDestroy", "", "onServiceInit", "onServiceObtained", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CodeGenComponent.kt */
public final class CodeGenComponent implements NeoComponent {
    public void onServiceDestroy() {
    }

    public void onServiceInit() {
    }

    public void onServiceObtained() {
    }

    public final CodeGenerator newGenerator(CodeGenObject codeGenObject) {
        Intrinsics.checkParameterIsNotNull(codeGenObject, "codeObject");
        return codeGenObject.getCodeGenerator(new CodeGenParameter());
    }
}
