package io.neolang.visitor;

import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import io.neolang.ast.visitor.IVisitorCallback;
import io.neolang.runtime.context.NeoLangContext;
import io.neolang.runtime.type.NeoLangArray;
import io.neolang.runtime.type.NeoLangValue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\fJ!\u0010\r\u001a\u00020\u000e2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000f\u001a\u00020\n¢\u0006\u0002\u0010\u0010J#\u0010\u0011\u001a\u0004\u0018\u00010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0014\u001a\u00020\n¢\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0004H\u0016J\u0006\u0010\u0019\u001a\u00020\u0004J#\u0010\u001a\u001a\u0004\u0018\u00010\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0014\u001a\u00020\n¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\nH\u0016J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\b\u0010 \u001a\u00020\u001dH\u0016J\b\u0010!\u001a\u00020\u001dH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lio/neolang/visitor/ConfigVisitor;", "Lio/neolang/ast/visitor/IVisitorCallback;", "()V", "currentContext", "Lio/neolang/runtime/context/NeoLangContext;", "rootContext", "getArray", "Lio/neolang/runtime/type/NeoLangArray;", "contextPath", "", "", "arrayName", "([Ljava/lang/String;Ljava/lang/String;)Lio/neolang/runtime/type/NeoLangArray;", "getAttribute", "Lio/neolang/runtime/type/NeoLangValue;", "attrName", "([Ljava/lang/String;Ljava/lang/String;)Lio/neolang/runtime/type/NeoLangValue;", "getBooleanValue", "", "path", "name", "([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean;", "getContext", "([Ljava/lang/String;)Lio/neolang/runtime/context/NeoLangContext;", "getCurrentContext", "getRootContext", "getStringValue", "([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "onEnterContext", "", "contextName", "onExitContext", "onFinish", "onStart", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: ConfigVisitor.kt */
public final class ConfigVisitor implements IVisitorCallback {
    private NeoLangContext currentContext;
    private NeoLangContext rootContext;

    public final NeoLangContext getRootContext() {
        NeoLangContext neoLangContext = this.rootContext;
        if (neoLangContext == null) {
            Intrinsics.throwNpe();
        }
        return neoLangContext;
    }

    public final NeoLangContext getContext(String[] strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "contextPath");
        NeoLangContext currentContext2 = getCurrentContext();
        for (String child : strArr) {
            currentContext2 = currentContext2.getChild(child);
        }
        return currentContext2;
    }

    public final NeoLangValue getAttribute(String[] strArr, String str) {
        Intrinsics.checkParameterIsNotNull(strArr, "contextPath");
        Intrinsics.checkParameterIsNotNull(str, "attrName");
        return getContext(strArr).getAttribute(str);
    }

    public final NeoLangArray getArray(String[] strArr, String str) {
        Intrinsics.checkParameterIsNotNull(strArr, "contextPath");
        Intrinsics.checkParameterIsNotNull(str, "arrayName");
        return NeoLangArray.Companion.createFromContext(getContext(strArr).getChild(str));
    }

    public final String getStringValue(String[] strArr, String str) {
        Intrinsics.checkParameterIsNotNull(strArr, "path");
        Intrinsics.checkParameterIsNotNull(str, NeoColorScheme.COLOR_META_NAME);
        NeoLangValue attribute = getAttribute(strArr, str);
        if (attribute.isValid()) {
            return attribute.asString();
        }
        return null;
    }

    public final Boolean getBooleanValue(String[] strArr, String str) {
        Intrinsics.checkParameterIsNotNull(strArr, "path");
        Intrinsics.checkParameterIsNotNull(str, NeoColorScheme.COLOR_META_NAME);
        NeoLangValue attribute = getAttribute(strArr, str);
        if (attribute.isValid()) {
            return Boolean.valueOf(Intrinsics.areEqual((Object) attribute.asString(), (Object) "true"));
        }
        return null;
    }

    public void onStart() {
        this.currentContext = new NeoLangContext("global");
        this.rootContext = this.currentContext;
    }

    public void onFinish() {
        NeoLangContext neoLangContext = this.currentContext;
        while (neoLangContext != null && neoLangContext.getParent() != null) {
            neoLangContext = neoLangContext.getParent();
        }
        this.currentContext = neoLangContext;
    }

    public void onEnterContext(String str) {
        Intrinsics.checkParameterIsNotNull(str, "contextName");
        NeoLangContext neoLangContext = new NeoLangContext(str);
        neoLangContext.setParent(this.currentContext);
        NeoLangContext neoLangContext2 = this.currentContext;
        if (neoLangContext2 == null) {
            Intrinsics.throwNpe();
        }
        neoLangContext2.getChildren().add(neoLangContext);
        this.currentContext = neoLangContext;
    }

    public void onExitContext() {
        NeoLangContext neoLangContext = this.currentContext;
        if ((neoLangContext != null ? neoLangContext.getParent() : null) != null) {
            this.currentContext = neoLangContext.getParent();
        }
    }

    public NeoLangContext getCurrentContext() {
        NeoLangContext neoLangContext = this.currentContext;
        if (neoLangContext == null) {
            Intrinsics.throwNpe();
        }
        return neoLangContext;
    }
}
