package io.neolang.ast.visitor;

import io.neolang.ast.base.NeoLangAst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/neolang/ast/visitor/VisitorFactory;", "", "ast", "Lio/neolang/ast/base/NeoLangAst;", "(Lio/neolang/ast/base/NeoLangAst;)V", "getVisitor", "Lio/neolang/ast/visitor/AstVisitor;", "callbackInterface", "Ljava/lang/Class;", "Lio/neolang/ast/visitor/IVisitorCallback;", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: VisitorFactory.kt */
public final class VisitorFactory {
    private final NeoLangAst ast;

    public VisitorFactory(NeoLangAst neoLangAst) {
        Intrinsics.checkParameterIsNotNull(neoLangAst, "ast");
        this.ast = neoLangAst;
    }

    public final AstVisitor getVisitor(Class<? extends IVisitorCallback> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "callbackInterface");
        try {
            NeoLangAst neoLangAst = this.ast;
            Object newInstance = cls.newInstance();
            Intrinsics.checkExpressionValueIsNotNull(newInstance, "callbackInterface.newInstance()");
            return new AstVisitor(neoLangAst, (IVisitorCallback) newInstance);
        } catch (Exception unused) {
            return null;
        }
    }
}
