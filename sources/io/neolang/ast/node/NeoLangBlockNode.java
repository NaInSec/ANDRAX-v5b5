package io.neolang.ast.node;

import io.neolang.ast.base.NeoLangBaseNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lio/neolang/ast/node/NeoLangBlockNode;", "Lio/neolang/ast/node/NeoLangAstBasedNode;", "blockElement", "Lio/neolang/ast/base/NeoLangBaseNode;", "(Lio/neolang/ast/base/NeoLangBaseNode;)V", "Companion", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangBlockNode.kt */
public final class NeoLangBlockNode extends NeoLangAstBasedNode {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lio/neolang/ast/node/NeoLangBlockNode$Companion;", "", "()V", "emptyNode", "Lio/neolang/ast/node/NeoLangBlockNode;", "NeoLang"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoLangBlockNode.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NeoLangBlockNode emptyNode() {
            return new NeoLangBlockNode(new NeoLangDummyNode());
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NeoLangBlockNode(NeoLangBaseNode neoLangBaseNode) {
        super(neoLangBaseNode);
        Intrinsics.checkParameterIsNotNull(neoLangBaseNode, "blockElement");
    }
}
