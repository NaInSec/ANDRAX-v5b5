package io.neolang.ast.node;

import io.neolang.ast.base.NeoLangBaseNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lio/neolang/ast/node/NeoLangGroupNode;", "Lio/neolang/ast/base/NeoLangBaseNode;", "attributes", "", "Lio/neolang/ast/node/NeoLangAttributeNode;", "([Lio/neolang/ast/node/NeoLangAttributeNode;)V", "getAttributes", "()[Lio/neolang/ast/node/NeoLangAttributeNode;", "[Lio/neolang/ast/node/NeoLangAttributeNode;", "toString", "", "Companion", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangGroupNode.kt */
public final class NeoLangGroupNode extends NeoLangBaseNode {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final NeoLangAttributeNode[] attributes;

    public NeoLangGroupNode(NeoLangAttributeNode[] neoLangAttributeNodeArr) {
        Intrinsics.checkParameterIsNotNull(neoLangAttributeNodeArr, "attributes");
        this.attributes = neoLangAttributeNodeArr;
    }

    public final NeoLangAttributeNode[] getAttributes() {
        return this.attributes;
    }

    public String toString() {
        return "NeoLangGroupNode { attrs: " + this.attributes + " }";
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lio/neolang/ast/node/NeoLangGroupNode$Companion;", "", "()V", "emptyNode", "Lio/neolang/ast/node/NeoLangGroupNode;", "NeoLang"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoLangGroupNode.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NeoLangGroupNode emptyNode() {
            return new NeoLangGroupNode(new NeoLangAttributeNode[0]);
        }
    }
}
