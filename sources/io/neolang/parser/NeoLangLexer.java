package io.neolang.parser;

import io.neolang.ast.NeoLangEOFToken;
import io.neolang.ast.NeoLangToken;
import io.neolang.ast.NeoLangTokenType;
import io.neolang.ast.NeoLangTokenValue;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0014\u001a\u00020\rH\u0002J\b\u0010\u0015\u001a\u00020\rH\u0002J\b\u0010\u0016\u001a\u00020\rH\u0002J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0018H\u0002J\u0013\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u001cH\u0000¢\u0006\u0002\b\u001dJ\u0012\u0010\u001e\u001a\u00020\u00182\b\b\u0002\u0010\u001f\u001a\u00020\u0018H\u0002J\u0017\u0010 \u001a\u00020!2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0000¢\u0006\u0002\b\"J\f\u0010#\u001a\u00020\u0018*\u00020\u0004H\u0002J\f\u0010$\u001a\u00020\u0018*\u00020\u0004H\u0002J\f\u0010%\u001a\u00020\u0006*\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8BX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lio/neolang/parser/NeoLangLexer;", "", "()V", "currentChar", "", "currentPosition", "", "lineNumber", "nextToken", "Lio/neolang/ast/NeoLangToken;", "getNextToken", "()Lio/neolang/ast/NeoLangToken;", "programCode", "", "getNextBinaryNumber", "", "numberValue", "getNextDecimalNumber", "getNextHexNumber", "getNextOctalNumber", "getNextTokenAsId", "getNextTokenAsNumber", "getNextTokenAsString", "isIdentifier", "", "tokenChar", "isFirstChar", "lex", "", "lex$NeoLang", "moveToNextChar", "eofThrow", "setInputSource", "", "setInputSource$NeoLang", "isHexNumber", "isNumber", "toNumber", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangLexer.kt */
public final class NeoLangLexer {
    private char currentChar = ' ';
    private int currentPosition;
    private int lineNumber;
    private String programCode;

    private final boolean isNumber(char c) {
        return '0' <= c && '9' >= c;
    }

    public final void setInputSource$NeoLang(String str) {
        this.programCode = str;
    }

    public final List<NeoLangToken> lex$NeoLang() {
        String str = this.programCode;
        if (str == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        this.currentPosition = 0;
        this.lineNumber = 1;
        if (str.length() > 0) {
            z = true;
        }
        if (z) {
            this.currentChar = str.charAt(this.currentPosition);
            while (this.currentPosition < str.length()) {
                NeoLangToken nextToken = getNextToken();
                if (nextToken instanceof NeoLangEOFToken) {
                    break;
                }
                arrayList.add(nextToken);
            }
        }
        return arrayList;
    }

    static /* synthetic */ boolean moveToNextChar$default(NeoLangLexer neoLangLexer, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return neoLangLexer.moveToNextChar(z);
    }

    private final boolean moveToNextChar(boolean z) {
        String str = this.programCode;
        if (str == null) {
            return false;
        }
        this.currentPosition++;
        if (this.currentPosition < str.length()) {
            this.currentChar = str.charAt(this.currentPosition);
            return true;
        } else if (!z) {
            return false;
        } else {
            throw new InvalidTokenException("Unexpected EOF near `" + this.currentChar + "' in line " + this.lineNumber);
        }
    }

    private final NeoLangToken getNextToken() {
        NeoLangToken neoLangToken;
        String str = this.programCode;
        if (str == null) {
            return new NeoLangEOFToken();
        }
        do {
            char c = this.currentChar;
            if (c == ' ' || c == 9 || c == 10 || c == 13) {
                if (this.currentChar == 10) {
                    this.lineNumber++;
                    int i = this.lineNumber;
                }
            } else if (this.currentPosition >= str.length()) {
                return new NeoLangEOFToken();
            } else {
                NeoLangTokenValue wrap = NeoLangTokenValue.Companion.wrap(String.valueOf(this.currentChar));
                if (Intrinsics.areEqual((Object) wrap, (Object) NeoLangTokenValue.Companion.getCOLON())) {
                    moveToNextChar(true);
                    neoLangToken = new NeoLangToken(NeoLangTokenType.COLON, wrap);
                } else if (Intrinsics.areEqual((Object) wrap, (Object) NeoLangTokenValue.Companion.getBRACKET_START())) {
                    moveToNextChar(true);
                    neoLangToken = new NeoLangToken(NeoLangTokenType.BRACKET_START, wrap);
                } else if (Intrinsics.areEqual((Object) wrap, (Object) NeoLangTokenValue.Companion.getBRACKET_END())) {
                    moveToNextChar$default(this, false, 1, (Object) null);
                    neoLangToken = new NeoLangToken(NeoLangTokenType.BRACKET_END, wrap);
                } else if (Intrinsics.areEqual((Object) wrap, (Object) NeoLangTokenValue.Companion.getARRAY_START())) {
                    moveToNextChar$default(this, false, 1, (Object) null);
                    neoLangToken = new NeoLangToken(NeoLangTokenType.ARRAY_START, wrap);
                } else if (Intrinsics.areEqual((Object) wrap, (Object) NeoLangTokenValue.Companion.getARRAY_END())) {
                    moveToNextChar$default(this, false, 1, (Object) null);
                    neoLangToken = new NeoLangToken(NeoLangTokenType.ARRAY_END, wrap);
                } else if (Intrinsics.areEqual((Object) wrap, (Object) NeoLangTokenValue.Companion.getCOMMA())) {
                    moveToNextChar(true);
                    neoLangToken = new NeoLangToken(NeoLangTokenType.COMMA, wrap);
                } else if (Intrinsics.areEqual((Object) wrap, (Object) NeoLangTokenValue.Companion.getQUOTE())) {
                    neoLangToken = new NeoLangToken(NeoLangTokenType.STRING, NeoLangTokenValue.Companion.wrap(getNextTokenAsString()));
                } else if (isNumber(this.currentChar)) {
                    neoLangToken = new NeoLangToken(NeoLangTokenType.NUMBER, NeoLangTokenValue.Companion.wrap(getNextTokenAsNumber()));
                } else if (isIdentifier(this.currentChar, true)) {
                    neoLangToken = new NeoLangToken(NeoLangTokenType.ID, NeoLangTokenValue.Companion.wrap(getNextTokenAsId()));
                } else {
                    throw new InvalidTokenException("Unexpected character near line " + this.lineNumber + ": " + this.currentChar);
                }
                neoLangToken.setLineNumber(this.lineNumber);
                return neoLangToken;
            }
        } while (moveToNextChar$default(this, false, 1, (Object) null));
        return new NeoLangEOFToken();
    }

    private final String getNextTokenAsString() {
        moveToNextChar(true);
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        while (z && this.currentChar != NeoLangTokenValue.Companion.getQUOTE().getValue().asString().charAt(0)) {
            sb.append(this.currentChar);
            z = moveToNextChar$default(this, false, 1, (Object) null);
        }
        moveToNextChar$default(this, false, 1, (Object) null);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }

    private final String getNextTokenAsNumber() {
        double d;
        double d2 = (double) (this.currentChar - '0');
        if (d2 > ((double) 0)) {
            d = getNextDecimalNumber(d2);
        } else if (!moveToNextChar$default(this, false, 1, (Object) null)) {
            return String.valueOf(d2);
        } else {
            char c = this.currentChar;
            if (c == 'x' || c == 'X') {
                d = getNextHexNumber(d2);
            } else if (c == 'b' || c == 'B') {
                d = getNextBinaryNumber(d2);
            } else {
                d = getNextOctalNumber(d2);
            }
        }
        return String.valueOf(d);
    }

    private final double getNextOctalNumber(double d) {
        double d2 = d;
        boolean z = true;
        while (z) {
            char c = this.currentChar;
            if ('0' > c || '7' < c) {
                break;
            }
            d2 = (d2 * ((double) 8)) + ((double) toNumber(c));
            z = moveToNextChar$default(this, false, 1, (Object) null);
        }
        return d2;
    }

    private final double getNextBinaryNumber(double d) {
        boolean moveToNextChar$default = moveToNextChar$default(this, false, 1, (Object) null);
        while (moveToNextChar$default) {
            char c = this.currentChar;
            if ('0' > c || '1' < c) {
                break;
            }
            d += (((double) 2) * d) + ((double) toNumber(c));
            moveToNextChar$default = moveToNextChar$default(this, false, 1, (Object) null);
        }
        return d;
    }

    private final double getNextHexNumber(double d) {
        boolean moveToNextChar$default = moveToNextChar$default(this, false, 1, (Object) null);
        while (moveToNextChar$default && isHexNumber(this.currentChar)) {
            d *= (double) 16;
            char c = this.currentChar;
            moveToNextChar$default = moveToNextChar$default(this, false, 1, (Object) null);
        }
        return d;
    }

    private final double getNextDecimalNumber(double d) {
        boolean moveToNextChar$default = moveToNextChar$default(this, false, 1, (Object) null);
        double d2 = 0.0d;
        double d3 = d;
        boolean z = false;
        int i = 1;
        while (moveToNextChar$default) {
            if (!isNumber(this.currentChar)) {
                if (this.currentChar != '.') {
                    break;
                }
                moveToNextChar$default = moveToNextChar$default(this, false, 1, (Object) null);
                z = true;
            } else {
                if (z) {
                    d2 = (d2 * ((double) 10)) + ((double) toNumber(this.currentChar));
                    i *= 10;
                } else {
                    d3 = (d3 * ((double) 10)) + ((double) toNumber(this.currentChar));
                }
                moveToNextChar$default = moveToNextChar$default(this, false, 1, (Object) null);
            }
        }
        return d3 + (d2 / ((double) i));
    }

    private final String getNextTokenAsId() {
        StringBuilder sb = new StringBuilder();
        while (isIdentifier(this.currentChar, false)) {
            sb.append(this.currentChar);
            if (!moveToNextChar$default(this, false, 1, (Object) null)) {
                break;
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    private final boolean isIdentifier(char c, boolean z) {
        boolean z2 = ('a' <= c && 'z' >= c) || ('A' <= c && 'Z' >= c) || StringsKt.contains$default((CharSequence) "_-#$", c, false, 2, (Object) null);
        if (z) {
            return z2;
        }
        if (!z2) {
            return '0' <= c && '9' >= c;
        }
        return true;
    }

    private final int toNumber(char c) {
        if (isNumber(c)) {
            return c - '0';
        }
        return 0;
    }

    private final boolean isHexNumber(char c) {
        return isNumber(c) || ('a' <= c && 'f' >= c) || ('A' <= c && 'F' >= c);
    }
}
