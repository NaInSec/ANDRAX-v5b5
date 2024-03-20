package io.neolang.ast;

import io.neolang.runtime.type.NeoLangValue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lio/neolang/ast/NeoLangTokenValue;", "", "value", "Lio/neolang/runtime/type/NeoLangValue;", "(Lio/neolang/runtime/type/NeoLangValue;)V", "getValue", "()Lio/neolang/runtime/type/NeoLangValue;", "toString", "", "Companion", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangTokenValue.kt */
public final class NeoLangTokenValue {
    /* access modifiers changed from: private */
    public static final NeoLangTokenValue ARRAY_END = new NeoLangTokenValue(new NeoLangValue("]"));
    /* access modifiers changed from: private */
    public static final NeoLangTokenValue ARRAY_START = new NeoLangTokenValue(new NeoLangValue("["));
    /* access modifiers changed from: private */
    public static final NeoLangTokenValue BRACKET_END = new NeoLangTokenValue(new NeoLangValue("}"));
    /* access modifiers changed from: private */
    public static final NeoLangTokenValue BRACKET_START = new NeoLangTokenValue(new NeoLangValue("{"));
    /* access modifiers changed from: private */
    public static final NeoLangTokenValue COLON = new NeoLangTokenValue(new NeoLangValue(":"));
    /* access modifiers changed from: private */
    public static final NeoLangTokenValue COMMA = new NeoLangTokenValue(new NeoLangValue(","));
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final NeoLangTokenValue EOF = new NeoLangTokenValue(new NeoLangValue("<EOF>"));
    /* access modifiers changed from: private */
    public static final NeoLangTokenValue QUOTE = new NeoLangTokenValue(new NeoLangValue("\""));
    private final NeoLangValue value;

    public NeoLangTokenValue(NeoLangValue neoLangValue) {
        Intrinsics.checkParameterIsNotNull(neoLangValue, "value");
        this.value = neoLangValue;
    }

    public final NeoLangValue getValue() {
        return this.value;
    }

    public String toString() {
        return this.value.asString();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006¨\u0006\u0018"}, d2 = {"Lio/neolang/ast/NeoLangTokenValue$Companion;", "", "()V", "ARRAY_END", "Lio/neolang/ast/NeoLangTokenValue;", "getARRAY_END", "()Lio/neolang/ast/NeoLangTokenValue;", "ARRAY_START", "getARRAY_START", "BRACKET_END", "getBRACKET_END", "BRACKET_START", "getBRACKET_START", "COLON", "getCOLON", "COMMA", "getCOMMA", "EOF", "getEOF", "QUOTE", "getQUOTE", "wrap", "tokenText", "", "NeoLang"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoLangTokenValue.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NeoLangTokenValue getCOLON() {
            return NeoLangTokenValue.COLON;
        }

        public final NeoLangTokenValue getCOMMA() {
            return NeoLangTokenValue.COMMA;
        }

        public final NeoLangTokenValue getQUOTE() {
            return NeoLangTokenValue.QUOTE;
        }

        public final NeoLangTokenValue getEOF() {
            return NeoLangTokenValue.EOF;
        }

        public final NeoLangTokenValue getBRACKET_START() {
            return NeoLangTokenValue.BRACKET_START;
        }

        public final NeoLangTokenValue getBRACKET_END() {
            return NeoLangTokenValue.BRACKET_END;
        }

        public final NeoLangTokenValue getARRAY_START() {
            return NeoLangTokenValue.ARRAY_START;
        }

        public final NeoLangTokenValue getARRAY_END() {
            return NeoLangTokenValue.ARRAY_END;
        }

        public final NeoLangTokenValue wrap(String str) {
            Intrinsics.checkParameterIsNotNull(str, "tokenText");
            Companion companion = this;
            if (Intrinsics.areEqual((Object) str, (Object) companion.getCOLON().getValue().asString())) {
                return companion.getCOLON();
            }
            if (Intrinsics.areEqual((Object) str, (Object) companion.getCOMMA().getValue().asString())) {
                return companion.getCOMMA();
            }
            if (Intrinsics.areEqual((Object) str, (Object) companion.getQUOTE().getValue().asString())) {
                return companion.getQUOTE();
            }
            if (Intrinsics.areEqual((Object) str, (Object) companion.getBRACKET_START().getValue().asString())) {
                return companion.getBRACKET_START();
            }
            if (Intrinsics.areEqual((Object) str, (Object) companion.getBRACKET_END().getValue().asString())) {
                return companion.getBRACKET_END();
            }
            if (Intrinsics.areEqual((Object) str, (Object) companion.getARRAY_START().getValue().asString())) {
                return companion.getARRAY_START();
            }
            if (Intrinsics.areEqual((Object) str, (Object) companion.getARRAY_END().getValue().asString())) {
                return companion.getARRAY_END();
            }
            return new NeoLangTokenValue(new NeoLangValue(str));
        }
    }
}
