package com.thecrackertechnology.dragonterminal.component.pm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0001\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/pm/Architecture;", "", "(Ljava/lang/String;I)V", "ALL", "ARM", "AARCH64", "X86", "X86_64", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: Architecture.kt */
public enum Architecture {
    ALL,
    ARM,
    AARCH64,
    X86,
    X86_64;
    
    public static final Companion Companion = null;

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/pm/Architecture$Companion;", "", "()V", "parse", "Lcom/thecrackertechnology/dragonterminal/component/pm/Architecture;", "arch", "", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: Architecture.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Architecture parse(String str) {
            Intrinsics.checkParameterIsNotNull(str, "arch");
            switch (str.hashCode()) {
                case -1221096139:
                    if (str.equals("aarch64")) {
                        return Architecture.AARCH64;
                    }
                    break;
                case -806050265:
                    if (str.equals("x86_64")) {
                        return Architecture.X86_64;
                    }
                    break;
                case 96860:
                    if (str.equals("arm")) {
                        return Architecture.ARM;
                    }
                    break;
                case 117110:
                    if (str.equals("x86")) {
                        return Architecture.X86;
                    }
                    break;
            }
            return Architecture.ALL;
        }
    }
}
