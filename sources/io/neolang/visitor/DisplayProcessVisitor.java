package io.neolang.visitor;

import io.neolang.ast.visitor.IVisitorCallbackAdapter;
import io.neolang.runtime.context.NeoLangContext;
import io.neolang.runtime.type.NeoLangValue;
import java.util.Map;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\bH\u0016J\b\u0010\r\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lio/neolang/visitor/DisplayProcessVisitor;", "Lio/neolang/ast/visitor/IVisitorCallbackAdapter;", "()V", "contextStack", "Ljava/util/Stack;", "Lio/neolang/runtime/context/NeoLangContext;", "getCurrentContext", "onEnterContext", "", "contextName", "", "onExitContext", "onFinish", "onStart", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: DisplayProcessVisitor.kt */
public final class DisplayProcessVisitor extends IVisitorCallbackAdapter {
    private final Stack<NeoLangContext> contextStack = new Stack<>();

    public void onStart() {
        System.out.println(">>> Start");
        onEnterContext("global");
    }

    public void onFinish() {
        while (!this.contextStack.isEmpty()) {
            onExitContext();
        }
        System.out.println(">>> Finish");
    }

    public void onEnterContext(String str) {
        Intrinsics.checkParameterIsNotNull(str, "contextName");
        this.contextStack.push(new NeoLangContext(str));
        System.out.println(">>> Entering Context `" + str + '\'');
    }

    public void onExitContext() {
        NeoLangContext pop = this.contextStack.pop();
        System.out.println(">>> Exiting & Dumping Context " + pop.getContextName());
        for (Map.Entry entry : pop.getAttributes().entrySet()) {
            System.out.println("     > [" + ((String) entry.getKey()) + "]: " + ((NeoLangValue) entry.getValue()).asString());
        }
    }

    public NeoLangContext getCurrentContext() {
        NeoLangContext peek = this.contextStack.peek();
        Intrinsics.checkExpressionValueIsNotNull(peek, "contextStack.peek()");
        return peek;
    }
}
