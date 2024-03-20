package es.dmoral.coloromatic.colormode;

import es.dmoral.coloromatic.colormode.mode.ARGB;
import es.dmoral.coloromatic.colormode.mode.AbstractColorMode;
import es.dmoral.coloromatic.colormode.mode.HSV;
import es.dmoral.coloromatic.colormode.mode.RGB;

public enum ColorMode {
    RGB,
    HSV,
    ARGB;

    /* renamed from: es.dmoral.coloromatic.colormode.ColorMode$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$es$dmoral$coloromatic$colormode$ColorMode = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                es.dmoral.coloromatic.colormode.ColorMode[] r0 = es.dmoral.coloromatic.colormode.ColorMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$es$dmoral$coloromatic$colormode$ColorMode = r0
                int[] r0 = $SwitchMap$es$dmoral$coloromatic$colormode$ColorMode     // Catch:{ NoSuchFieldError -> 0x0014 }
                es.dmoral.coloromatic.colormode.ColorMode r1 = es.dmoral.coloromatic.colormode.ColorMode.RGB     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$es$dmoral$coloromatic$colormode$ColorMode     // Catch:{ NoSuchFieldError -> 0x001f }
                es.dmoral.coloromatic.colormode.ColorMode r1 = es.dmoral.coloromatic.colormode.ColorMode.HSV     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$es$dmoral$coloromatic$colormode$ColorMode     // Catch:{ NoSuchFieldError -> 0x002a }
                es.dmoral.coloromatic.colormode.ColorMode r1 = es.dmoral.coloromatic.colormode.ColorMode.ARGB     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: es.dmoral.coloromatic.colormode.ColorMode.AnonymousClass1.<clinit>():void");
        }
    }

    public AbstractColorMode getColorMode() {
        int i = AnonymousClass1.$SwitchMap$es$dmoral$coloromatic$colormode$ColorMode[ordinal()];
        if (i == 1) {
            return new RGB();
        }
        if (i == 2) {
            return new HSV();
        }
        if (i != 3) {
            return new RGB();
        }
        return new ARGB();
    }
}
