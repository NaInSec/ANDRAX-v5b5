package io.neolang.ast.visitor;

import io.neolang.runtime.context.NeoLangContext;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0005H&J\b\u0010\t\u001a\u00020\u0005H&J\b\u0010\n\u001a\u00020\u0005H&¨\u0006\u000b"}, d2 = {"Lio/neolang/ast/visitor/IVisitorCallback;", "", "getCurrentContext", "Lio/neolang/runtime/context/NeoLangContext;", "onEnterContext", "", "contextName", "", "onExitContext", "onFinish", "onStart", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: IVisitorCallback.kt */
public interface IVisitorCallback {
    NeoLangContext getCurrentContext();

    void onEnterContext(String str);

    void onExitContext();

    void onFinish();

    void onStart();
}
