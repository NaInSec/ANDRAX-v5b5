package io.neolang.ast.node;

import io.neolang.ast.base.NeoLangBaseNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lio/neolang/ast/node/NeoLangArrayNode;", "Lio/neolang/ast/base/NeoLangBaseNode;", "arrayNameNode", "Lio/neolang/ast/node/NeoLangStringNode;", "elements", "", "Lio/neolang/ast/node/NeoLangArrayNode$Companion$ArrayElement;", "(Lio/neolang/ast/node/NeoLangStringNode;[Lio/neolang/ast/node/NeoLangArrayNode$Companion$ArrayElement;)V", "getArrayNameNode", "()Lio/neolang/ast/node/NeoLangStringNode;", "getElements", "()[Lio/neolang/ast/node/NeoLangArrayNode$Companion$ArrayElement;", "[Lio/neolang/ast/node/NeoLangArrayNode$Companion$ArrayElement;", "Companion", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangArrayNode.kt */
public final class NeoLangArrayNode extends NeoLangBaseNode {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final NeoLangStringNode arrayNameNode;
    private final Companion.ArrayElement[] elements;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lio/neolang/ast/node/NeoLangArrayNode$Companion;", "", "()V", "ArrayElement", "NeoLang"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoLangArrayNode.kt */
    public static final class Companion {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/neolang/ast/node/NeoLangArrayNode$Companion$ArrayElement;", "", "index", "", "block", "Lio/neolang/ast/node/NeoLangBlockNode;", "(ILio/neolang/ast/node/NeoLangBlockNode;)V", "getBlock", "()Lio/neolang/ast/node/NeoLangBlockNode;", "getIndex", "()I", "NeoLang"}, k = 1, mv = {1, 1, 15})
        /* compiled from: NeoLangArrayNode.kt */
        public static final class ArrayElement {
            private final NeoLangBlockNode block;
            private final int index;

            public ArrayElement(int i, NeoLangBlockNode neoLangBlockNode) {
                Intrinsics.checkParameterIsNotNull(neoLangBlockNode, "block");
                this.index = i;
                this.block = neoLangBlockNode;
            }

            public final NeoLangBlockNode getBlock() {
                return this.block;
            }

            public final int getIndex() {
                return this.index;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public NeoLangArrayNode(NeoLangStringNode neoLangStringNode, Companion.ArrayElement[] arrayElementArr) {
        Intrinsics.checkParameterIsNotNull(neoLangStringNode, "arrayNameNode");
        Intrinsics.checkParameterIsNotNull(arrayElementArr, "elements");
        this.arrayNameNode = neoLangStringNode;
        this.elements = arrayElementArr;
    }

    public final NeoLangStringNode getArrayNameNode() {
        return this.arrayNameNode;
    }

    public final Companion.ArrayElement[] getElements() {
        return this.elements;
    }
}
