package io.neolang.ast.node;

import io.neolang.ast.base.NeoLangBaseNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lio/neolang/ast/node/NeoLangProgramNode;", "Lio/neolang/ast/base/NeoLangBaseNode;", "groups", "", "Lio/neolang/ast/node/NeoLangGroupNode;", "(Ljava/util/List;)V", "getGroups", "()Ljava/util/List;", "toString", "", "Companion", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangProgramNode.kt */
public final class NeoLangProgramNode extends NeoLangBaseNode {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<NeoLangGroupNode> groups;

    public NeoLangProgramNode(List<NeoLangGroupNode> list) {
        Intrinsics.checkParameterIsNotNull(list, "groups");
        this.groups = list;
    }

    public final List<NeoLangGroupNode> getGroups() {
        return this.groups;
    }

    public String toString() {
        return "NeoLangProgramNode { groups: " + this.groups + " }";
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lio/neolang/ast/node/NeoLangProgramNode$Companion;", "", "()V", "emptyNode", "Lio/neolang/ast/node/NeoLangProgramNode;", "NeoLang"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoLangProgramNode.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NeoLangProgramNode emptyNode() {
            return new NeoLangProgramNode(CollectionsKt.emptyList());
        }
    }
}
