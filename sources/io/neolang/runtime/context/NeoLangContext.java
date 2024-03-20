package io.neolang.runtime.context;

import io.neolang.runtime.type.NeoLangValue;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u000e\n\u0002\u0010$\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0007J\u000e\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0003J\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0018J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0000X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001b"}, d2 = {"Lio/neolang/runtime/context/NeoLangContext;", "", "contextName", "", "(Ljava/lang/String;)V", "attributes", "", "Lio/neolang/runtime/type/NeoLangValue;", "children", "", "getChildren", "()Ljava/util/List;", "getContextName", "()Ljava/lang/String;", "parent", "getParent", "()Lio/neolang/runtime/context/NeoLangContext;", "setParent", "(Lio/neolang/runtime/context/NeoLangContext;)V", "defineAttribute", "attributeName", "attributeValue", "getAttribute", "getAttributes", "", "getChild", "Companion", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangContext.kt */
public final class NeoLangContext {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final NeoLangContext emptyContext = new NeoLangContext("<Context-Empty>");
    private final Map<String, NeoLangValue> attributes = new LinkedHashMap();
    private final List<NeoLangContext> children = new ArrayList();
    private final String contextName;
    private NeoLangContext parent;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lio/neolang/runtime/context/NeoLangContext$Companion;", "", "()V", "emptyContext", "Lio/neolang/runtime/context/NeoLangContext;", "NeoLang"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoLangContext.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public NeoLangContext(String str) {
        Intrinsics.checkParameterIsNotNull(str, "contextName");
        this.contextName = str;
    }

    public final String getContextName() {
        return this.contextName;
    }

    public final List<NeoLangContext> getChildren() {
        return this.children;
    }

    public final NeoLangContext getParent() {
        return this.parent;
    }

    public final void setParent(NeoLangContext neoLangContext) {
        this.parent = neoLangContext;
    }

    public final NeoLangContext defineAttribute(String str, NeoLangValue neoLangValue) {
        Intrinsics.checkParameterIsNotNull(str, "attributeName");
        Intrinsics.checkParameterIsNotNull(neoLangValue, "attributeValue");
        this.attributes.put(str, neoLangValue);
        return this;
    }

    public final NeoLangValue getAttribute(String str) {
        Intrinsics.checkParameterIsNotNull(str, "attributeName");
        NeoLangValue neoLangValue = this.attributes.get(str);
        if (neoLangValue == null) {
            NeoLangContext neoLangContext = this.parent;
            neoLangValue = neoLangContext != null ? neoLangContext.getAttribute(str) : null;
        }
        return neoLangValue != null ? neoLangValue : NeoLangValue.Companion.getUNDEFINED();
    }

    public final NeoLangContext getChild(String str) {
        Intrinsics.checkParameterIsNotNull(str, "contextName");
        NeoLangContext neoLangContext = null;
        for (NeoLangContext neoLangContext2 : this.children) {
            if (Intrinsics.areEqual((Object) neoLangContext2.contextName, (Object) str)) {
                neoLangContext = neoLangContext2;
            }
        }
        return neoLangContext != null ? neoLangContext : emptyContext;
    }

    public final Map<String, NeoLangValue> getAttributes() {
        return this.attributes;
    }
}
