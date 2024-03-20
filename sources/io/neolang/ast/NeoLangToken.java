package io.neolang.ast;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lio/neolang/ast/NeoLangToken;", "", "tokenType", "Lio/neolang/ast/NeoLangTokenType;", "tokenValue", "Lio/neolang/ast/NeoLangTokenValue;", "(Lio/neolang/ast/NeoLangTokenType;Lio/neolang/ast/NeoLangTokenValue;)V", "lineNumber", "", "getLineNumber", "()I", "setLineNumber", "(I)V", "getTokenType", "()Lio/neolang/ast/NeoLangTokenType;", "getTokenValue", "()Lio/neolang/ast/NeoLangTokenValue;", "toString", "", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangToken.kt */
public class NeoLangToken {
    private int lineNumber;
    private final NeoLangTokenType tokenType;
    private final NeoLangTokenValue tokenValue;

    public NeoLangToken(NeoLangTokenType neoLangTokenType, NeoLangTokenValue neoLangTokenValue) {
        Intrinsics.checkParameterIsNotNull(neoLangTokenType, "tokenType");
        Intrinsics.checkParameterIsNotNull(neoLangTokenValue, "tokenValue");
        this.tokenType = neoLangTokenType;
        this.tokenValue = neoLangTokenValue;
    }

    public final NeoLangTokenType getTokenType() {
        return this.tokenType;
    }

    public final NeoLangTokenValue getTokenValue() {
        return this.tokenValue;
    }

    public final int getLineNumber() {
        return this.lineNumber;
    }

    public final void setLineNumber(int i) {
        this.lineNumber = i;
    }

    public String toString() {
        return "Token { tokenType: " + this.tokenType + ", tokenValue: " + this.tokenValue + " };";
    }
}
