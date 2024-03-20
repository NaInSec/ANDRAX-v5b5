package io.neolang.ast.visitor;

import io.neolang.runtime.context.NeoLangContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016¨\u0006\f"}, d2 = {"Lio/neolang/ast/visitor/IVisitorCallbackAdapter;", "Lio/neolang/ast/visitor/IVisitorCallback;", "()V", "getCurrentContext", "Lio/neolang/runtime/context/NeoLangContext;", "onEnterContext", "", "contextName", "", "onExitContext", "onFinish", "onStart", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: IVisitorCallbackAdapter.kt */
public class IVisitorCallbackAdapter implements IVisitorCallback {
    public void onEnterContext(String str) {
        Intrinsics.checkParameterIsNotNull(str, "contextName");
    }

    public void onExitContext() {
    }

    public void onFinish() {
    }

    public void onStart() {
    }

    public NeoLangContext getCurrentContext() {
        throw new RuntimeException("getCurrentContext() not supported in this IVisitorCallback!");
    }
}
