package com.afollestad.materialdialogs;

import android.support.v4.view.GravityCompat;

public enum GravityEnum {
    START,
    CENTER,
    END;
    
    private static final boolean HAS_RTL = false;

    /* renamed from: com.afollestad.materialdialogs.GravityEnum$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$afollestad$materialdialogs$GravityEnum = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.afollestad.materialdialogs.GravityEnum[] r0 = com.afollestad.materialdialogs.GravityEnum.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$afollestad$materialdialogs$GravityEnum = r0
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$GravityEnum     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.afollestad.materialdialogs.GravityEnum r1 = com.afollestad.materialdialogs.GravityEnum.START     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$GravityEnum     // Catch:{ NoSuchFieldError -> 0x001f }
                com.afollestad.materialdialogs.GravityEnum r1 = com.afollestad.materialdialogs.GravityEnum.CENTER     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$GravityEnum     // Catch:{ NoSuchFieldError -> 0x002a }
                com.afollestad.materialdialogs.GravityEnum r1 = com.afollestad.materialdialogs.GravityEnum.END     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.GravityEnum.AnonymousClass1.<clinit>():void");
        }
    }

    public int getGravityInt() {
        int i = AnonymousClass1.$SwitchMap$com$afollestad$materialdialogs$GravityEnum[ordinal()];
        if (i != 1) {
            if (i == 2) {
                return 1;
            }
            if (i != 3) {
                throw new IllegalStateException("Invalid gravity constant");
            } else if (HAS_RTL) {
                return GravityCompat.END;
            } else {
                return 5;
            }
        } else if (HAS_RTL) {
            return GravityCompat.START;
        } else {
            return 3;
        }
    }

    public int getTextAlignment() {
        int i = AnonymousClass1.$SwitchMap$com$afollestad$materialdialogs$GravityEnum[ordinal()];
        if (i != 2) {
            return i != 3 ? 5 : 6;
        }
        return 4;
    }
}
