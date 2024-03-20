package io.neolang.runtime.type;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lio/neolang/runtime/type/NeoLangValue;", "", "rawValue", "(Ljava/lang/Object;)V", "asNumber", "", "asString", "", "isValid", "", "Companion", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangValue.kt */
public final class NeoLangValue {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final NeoLangValue UNDEFINED = new NeoLangValue("<undefined>");
    private final Object rawValue;

    public NeoLangValue(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "rawValue");
        this.rawValue = obj;
    }

    public final String asString() {
        return this.rawValue.toString();
    }

    public final double asNumber() {
        Object obj = this.rawValue;
        if (obj instanceof Object[]) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Throwable unused) {
            return 0.0d;
        }
    }

    public final boolean isValid() {
        return !Intrinsics.areEqual((Object) this, (Object) UNDEFINED);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/neolang/runtime/type/NeoLangValue$Companion;", "", "()V", "UNDEFINED", "Lio/neolang/runtime/type/NeoLangValue;", "getUNDEFINED", "()Lio/neolang/runtime/type/NeoLangValue;", "NeoLang"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoLangValue.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NeoLangValue getUNDEFINED() {
            return NeoLangValue.UNDEFINED;
        }
    }
}
