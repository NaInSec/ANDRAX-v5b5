package io.neolang.ast.node;

import io.neolang.ast.NeoLangToken;
import io.neolang.ast.base.NeoLangBaseNode;
import io.neolang.runtime.type.NeoLangValue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lio/neolang/ast/node/NeoLangTokenBasedNode;", "Lio/neolang/ast/base/NeoLangBaseNode;", "token", "Lio/neolang/ast/NeoLangToken;", "(Lio/neolang/ast/NeoLangToken;)V", "getToken", "()Lio/neolang/ast/NeoLangToken;", "eval", "Lio/neolang/runtime/type/NeoLangValue;", "toString", "", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangTokenBasedNode.kt */
public class NeoLangTokenBasedNode extends NeoLangBaseNode {
    private final NeoLangToken token;

    public NeoLangTokenBasedNode(NeoLangToken neoLangToken) {
        Intrinsics.checkParameterIsNotNull(neoLangToken, "token");
        this.token = neoLangToken;
    }

    public final NeoLangToken getToken() {
        return this.token;
    }

    public String toString() {
        return getClass().getSimpleName() + " { token: " + this.token + " }";
    }

    public final NeoLangValue eval() {
        return this.token.getTokenValue().getValue();
    }
}
