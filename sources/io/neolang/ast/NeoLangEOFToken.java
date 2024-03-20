package io.neolang.ast;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/neolang/ast/NeoLangEOFToken;", "Lio/neolang/ast/NeoLangToken;", "()V", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangEOFToken.kt */
public final class NeoLangEOFToken extends NeoLangToken {
    public NeoLangEOFToken() {
        super(NeoLangTokenType.EOF, NeoLangTokenValue.Companion.getEOF());
    }
}
