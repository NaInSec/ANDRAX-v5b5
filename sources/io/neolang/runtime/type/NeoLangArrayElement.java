package io.neolang.runtime.type;

import com.thecrackertechnology.dragonterminal.component.extrakey.NeoExtraKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lio/neolang/runtime/type/NeoLangArrayElement;", "", "()V", "eval", "Lio/neolang/runtime/type/NeoLangValue;", "key", "", "isBlock", "", "NeoLang"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoLangArrayElement.kt */
public class NeoLangArrayElement {
    public boolean isBlock() {
        return false;
    }

    public NeoLangValue eval() {
        return NeoLangValue.Companion.getUNDEFINED();
    }

    public NeoLangValue eval(String str) {
        Intrinsics.checkParameterIsNotNull(str, NeoExtraKey.EKS_META_KEY);
        return NeoLangValue.Companion.getUNDEFINED();
    }
}
