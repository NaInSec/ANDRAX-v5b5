package com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.combine;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/combine/CombinedSequence;", "", "()V", "keys", "", "", "getKeys", "()Ljava/util/List;", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: CombinedSequence.kt */
public final class CombinedSequence {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<String> keys;

    private CombinedSequence() {
        this.keys = new ArrayList();
    }

    public /* synthetic */ CombinedSequence(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final List<String> getKeys() {
        return this.keys;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/combine/CombinedSequence$Companion;", "", "()V", "solveString", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/extrakey/combine/CombinedSequence;", "keyString", "", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: CombinedSequence.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CombinedSequence solveString(String str) {
            Intrinsics.checkParameterIsNotNull(str, "keyString");
            CombinedSequence combinedSequence = new CombinedSequence((DefaultConstructorMarker) null);
            for (String str2 : StringsKt.split$default((CharSequence) str, new char[]{' '}, false, 0, 6, (Object) null)) {
                CharSequence charSequence = str2;
                if (StringsKt.startsWith$default(charSequence, (char) Typography.less, false, 2, (Object) null) && StringsKt.endsWith$default(charSequence, (char) Typography.greater, false, 2, (Object) null)) {
                    int length = str2.length() - 1;
                    if (str2 != null) {
                        str2 = str2.substring(1, length);
                        Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                }
                combinedSequence.getKeys().add(str2);
            }
            return combinedSequence;
        }
    }
}
