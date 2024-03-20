package com.thecrackertechnology.dragonterminal.component.codegen.generators;

import com.thecrackertechnology.dragonterminal.component.codegen.CodeGenParameter;
import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenObject;
import com.thecrackertechnology.dragonterminal.component.codegen.interfaces.CodeGenerator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/codegen/generators/NeoProfileGenerator;", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenerator;", "parameter", "Lcom/thecrackertechnology/dragonterminal/component/codegen/CodeGenParameter;", "(Lcom/thecrackertechnology/dragonterminal/component/codegen/CodeGenParameter;)V", "generateCode", "", "codeGenObject", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenObject;", "getGeneratorName", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoProfileGenerator.kt */
public final class NeoProfileGenerator extends CodeGenerator {
    public String generateCode(CodeGenObject codeGenObject) {
        Intrinsics.checkParameterIsNotNull(codeGenObject, "codeGenObject");
        return "";
    }

    public String getGeneratorName() {
        return "NeoProfile-Generator";
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NeoProfileGenerator(CodeGenParameter codeGenParameter) {
        super(codeGenParameter);
        Intrinsics.checkParameterIsNotNull(codeGenParameter, "parameter");
    }
}
