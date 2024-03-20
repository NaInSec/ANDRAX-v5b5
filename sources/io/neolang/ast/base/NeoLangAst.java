package io.neolang.ast.base;

import io.neolang.ast.visitor.VisitorFactory;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lio/neolang/ast/base/NeoLangAst;", "", "()V", "visit", "Lio/neolang/ast/visitor/VisitorFactory;", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangAst.kt */
public class NeoLangAst {
    public final VisitorFactory visit() {
        return new VisitorFactory(this);
    }
}
