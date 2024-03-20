package com.thecrackertechnology.dragonterminal.component.codegen.interfaces;

import com.thecrackertechnology.dragonterminal.component.codegen.CodeGenParameter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0006H&¨\u0006\n"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenerator;", "", "parameter", "Lcom/thecrackertechnology/dragonterminal/component/codegen/CodeGenParameter;", "(Lcom/thecrackertechnology/dragonterminal/component/codegen/CodeGenParameter;)V", "generateCode", "", "codeGenObject", "Lcom/thecrackertechnology/dragonterminal/component/codegen/interfaces/CodeGenObject;", "getGeneratorName", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CodeGenerator.kt */
public abstract class CodeGenerator {
    public abstract String generateCode(CodeGenObject codeGenObject);

    public abstract String getGeneratorName();

    public CodeGenerator(CodeGenParameter codeGenParameter) {
        Intrinsics.checkParameterIsNotNull(codeGenParameter, "parameter");
    }
}
