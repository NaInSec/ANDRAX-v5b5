package io.neolang.ast.visitor;

import io.neolang.ast.base.NeoLangAst;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u0005¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lio/neolang/ast/visitor/AstVisitor;", "", "ast", "Lio/neolang/ast/base/NeoLangAst;", "visitorCallback", "Lio/neolang/ast/visitor/IVisitorCallback;", "(Lio/neolang/ast/base/NeoLangAst;Lio/neolang/ast/visitor/IVisitorCallback;)V", "getCallback", "T", "()Lio/neolang/ast/visitor/IVisitorCallback;", "start", "", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: AstVisitor.kt */
public final class AstVisitor {
    private final NeoLangAst ast;
    private final IVisitorCallback visitorCallback;

    public AstVisitor(NeoLangAst neoLangAst, IVisitorCallback iVisitorCallback) {
        Intrinsics.checkParameterIsNotNull(neoLangAst, "ast");
        Intrinsics.checkParameterIsNotNull(iVisitorCallback, "visitorCallback");
        this.ast = neoLangAst;
        this.visitorCallback = iVisitorCallback;
    }

    public final void start() {
        AstVisitorImpl.INSTANCE.visitStartAst(this.ast, this.visitorCallback);
    }

    public final <T extends IVisitorCallback> T getCallback() {
        T t = this.visitorCallback;
        if (t != null) {
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type T");
    }
}
