package io.neolang.ast.node;

import io.neolang.ast.base.NeoLangBaseNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lio/neolang/ast/node/NeoLangAttributeNode;", "Lio/neolang/ast/base/NeoLangBaseNode;", "stringNode", "Lio/neolang/ast/node/NeoLangStringNode;", "blockNode", "Lio/neolang/ast/node/NeoLangBlockNode;", "(Lio/neolang/ast/node/NeoLangStringNode;Lio/neolang/ast/node/NeoLangBlockNode;)V", "getBlockNode", "()Lio/neolang/ast/node/NeoLangBlockNode;", "getStringNode", "()Lio/neolang/ast/node/NeoLangStringNode;", "toString", "", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangAttributeNode.kt */
public final class NeoLangAttributeNode extends NeoLangBaseNode {
    private final NeoLangBlockNode blockNode;
    private final NeoLangStringNode stringNode;

    public NeoLangAttributeNode(NeoLangStringNode neoLangStringNode, NeoLangBlockNode neoLangBlockNode) {
        Intrinsics.checkParameterIsNotNull(neoLangStringNode, "stringNode");
        Intrinsics.checkParameterIsNotNull(neoLangBlockNode, "blockNode");
        this.stringNode = neoLangStringNode;
        this.blockNode = neoLangBlockNode;
    }

    public final NeoLangBlockNode getBlockNode() {
        return this.blockNode;
    }

    public final NeoLangStringNode getStringNode() {
        return this.stringNode;
    }

    public String toString() {
        return "NeoLangAttributeNode { stringNode: " + this.stringNode + ", block: " + this.blockNode + " }";
    }
}
