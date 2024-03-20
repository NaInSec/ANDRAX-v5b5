package io.neolang.ast.node;

import io.neolang.ast.base.NeoLangBaseNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lio/neolang/ast/node/NeoLangAstBasedNode;", "Lio/neolang/ast/base/NeoLangBaseNode;", "ast", "(Lio/neolang/ast/base/NeoLangBaseNode;)V", "getAst", "()Lio/neolang/ast/base/NeoLangBaseNode;", "toString", "", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangAstBasedNode.kt */
public class NeoLangAstBasedNode extends NeoLangBaseNode {
    private final NeoLangBaseNode ast;

    public NeoLangAstBasedNode(NeoLangBaseNode neoLangBaseNode) {
        Intrinsics.checkParameterIsNotNull(neoLangBaseNode, "ast");
        this.ast = neoLangBaseNode;
    }

    public final NeoLangBaseNode getAst() {
        return this.ast;
    }

    public String toString() {
        return getClass().getSimpleName() + " { ast: " + this.ast + " }";
    }
}
