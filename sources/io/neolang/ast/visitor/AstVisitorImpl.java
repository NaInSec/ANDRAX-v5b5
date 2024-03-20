package io.neolang.ast.visitor;

import io.neolang.ast.base.NeoLangAst;
import io.neolang.ast.base.NeoLangBaseNode;
import io.neolang.ast.node.NeoLangArrayNode;
import io.neolang.ast.node.NeoLangAttributeNode;
import io.neolang.ast.node.NeoLangBlockNode;
import io.neolang.ast.node.NeoLangGroupNode;
import io.neolang.ast.node.NeoLangNumberNode;
import io.neolang.ast.node.NeoLangProgramNode;
import io.neolang.ast.node.NeoLangStringNode;
import io.neolang.runtime.type.NeoLangValue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u0012\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u0016\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u0018\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u001a\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u001c"}, d2 = {"Lio/neolang/ast/visitor/AstVisitorImpl;", "", "()V", "definePrimaryData", "", "name", "", "value", "Lio/neolang/runtime/type/NeoLangValue;", "visitorCallback", "Lio/neolang/ast/visitor/IVisitorCallback;", "visitArray", "ast", "Lio/neolang/ast/node/NeoLangArrayNode;", "visitArrayElementBlock", "Lio/neolang/ast/node/NeoLangBlockNode;", "index", "", "visitAttribute", "Lio/neolang/ast/node/NeoLangAttributeNode;", "visitBlock", "blockName", "visitGroup", "Lio/neolang/ast/node/NeoLangGroupNode;", "visitProgram", "Lio/neolang/ast/node/NeoLangProgramNode;", "visitStartAst", "Lio/neolang/ast/base/NeoLangAst;", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: AstVisitorImpl.kt */
public final class AstVisitorImpl {
    public static final AstVisitorImpl INSTANCE = new AstVisitorImpl();

    private AstVisitorImpl() {
    }

    public final void visitProgram(NeoLangProgramNode neoLangProgramNode, IVisitorCallback iVisitorCallback) {
        Intrinsics.checkParameterIsNotNull(neoLangProgramNode, "ast");
        Intrinsics.checkParameterIsNotNull(iVisitorCallback, "visitorCallback");
        iVisitorCallback.onStart();
        for (NeoLangGroupNode visitGroup : neoLangProgramNode.getGroups()) {
            INSTANCE.visitGroup(visitGroup, iVisitorCallback);
        }
        iVisitorCallback.onFinish();
    }

    public final void visitGroup(NeoLangGroupNode neoLangGroupNode, IVisitorCallback iVisitorCallback) {
        Intrinsics.checkParameterIsNotNull(neoLangGroupNode, "ast");
        Intrinsics.checkParameterIsNotNull(iVisitorCallback, "visitorCallback");
        for (NeoLangAttributeNode visitAttribute : neoLangGroupNode.getAttributes()) {
            INSTANCE.visitAttribute(visitAttribute, iVisitorCallback);
        }
    }

    public final void visitAttribute(NeoLangAttributeNode neoLangAttributeNode, IVisitorCallback iVisitorCallback) {
        Intrinsics.checkParameterIsNotNull(neoLangAttributeNode, "ast");
        Intrinsics.checkParameterIsNotNull(iVisitorCallback, "visitorCallback");
        visitBlock(neoLangAttributeNode.getBlockNode(), neoLangAttributeNode.getStringNode().eval().asString(), iVisitorCallback);
    }

    public final void visitArray(NeoLangArrayNode neoLangArrayNode, IVisitorCallback iVisitorCallback) {
        Intrinsics.checkParameterIsNotNull(neoLangArrayNode, "ast");
        Intrinsics.checkParameterIsNotNull(iVisitorCallback, "visitorCallback");
        iVisitorCallback.onEnterContext(neoLangArrayNode.getArrayNameNode().eval().asString());
        for (NeoLangArrayNode.Companion.ArrayElement arrayElement : neoLangArrayNode.getElements()) {
            INSTANCE.visitArrayElementBlock(arrayElement.getBlock(), arrayElement.getIndex(), iVisitorCallback);
        }
        iVisitorCallback.onExitContext();
    }

    public final void visitArrayElementBlock(NeoLangBlockNode neoLangBlockNode, int i, IVisitorCallback iVisitorCallback) {
        Intrinsics.checkParameterIsNotNull(neoLangBlockNode, "ast");
        Intrinsics.checkParameterIsNotNull(iVisitorCallback, "visitorCallback");
        NeoLangBaseNode ast = neoLangBlockNode.getAst();
        if (ast instanceof NeoLangGroupNode) {
            iVisitorCallback.onEnterContext(String.valueOf(i));
            INSTANCE.visitGroup((NeoLangGroupNode) ast, iVisitorCallback);
            iVisitorCallback.onExitContext();
        } else if (ast instanceof NeoLangStringNode) {
            definePrimaryData(String.valueOf(i), ((NeoLangStringNode) ast).eval(), iVisitorCallback);
        } else if (ast instanceof NeoLangNumberNode) {
            definePrimaryData(String.valueOf(i), ((NeoLangNumberNode) ast).eval(), iVisitorCallback);
        }
    }

    public final void visitBlock(NeoLangBlockNode neoLangBlockNode, String str, IVisitorCallback iVisitorCallback) {
        Intrinsics.checkParameterIsNotNull(neoLangBlockNode, "ast");
        Intrinsics.checkParameterIsNotNull(str, "blockName");
        Intrinsics.checkParameterIsNotNull(iVisitorCallback, "visitorCallback");
        NeoLangBaseNode ast = neoLangBlockNode.getAst();
        if (ast instanceof NeoLangGroupNode) {
            iVisitorCallback.onEnterContext(str);
            INSTANCE.visitGroup((NeoLangGroupNode) ast, iVisitorCallback);
            iVisitorCallback.onExitContext();
        } else if (ast instanceof NeoLangArrayNode) {
            INSTANCE.visitArray((NeoLangArrayNode) ast, iVisitorCallback);
        } else if (ast instanceof NeoLangStringNode) {
            definePrimaryData(str, ((NeoLangStringNode) ast).eval(), iVisitorCallback);
        } else if (ast instanceof NeoLangNumberNode) {
            definePrimaryData(str, ((NeoLangNumberNode) ast).eval(), iVisitorCallback);
        }
    }

    private final void definePrimaryData(String str, NeoLangValue neoLangValue, IVisitorCallback iVisitorCallback) {
        iVisitorCallback.getCurrentContext().defineAttribute(str, neoLangValue);
    }

    public final void visitStartAst(NeoLangAst neoLangAst, IVisitorCallback iVisitorCallback) {
        Intrinsics.checkParameterIsNotNull(neoLangAst, "ast");
        Intrinsics.checkParameterIsNotNull(iVisitorCallback, "visitorCallback");
        if (neoLangAst instanceof NeoLangProgramNode) {
            INSTANCE.visitProgram((NeoLangProgramNode) neoLangAst, iVisitorCallback);
        } else if (neoLangAst instanceof NeoLangGroupNode) {
            INSTANCE.visitGroup((NeoLangGroupNode) neoLangAst, iVisitorCallback);
        } else if (neoLangAst instanceof NeoLangArrayNode) {
            INSTANCE.visitArray((NeoLangArrayNode) neoLangAst, iVisitorCallback);
        }
    }
}
