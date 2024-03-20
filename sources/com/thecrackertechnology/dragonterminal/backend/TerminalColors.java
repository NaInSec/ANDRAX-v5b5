package com.thecrackertechnology.dragonterminal.backend;

public final class TerminalColors {
    public static final TerminalColorScheme COLOR_SCHEME = new TerminalColorScheme();
    public final int[] mCurrentColors = new int[259];

    public TerminalColors() {
        reset();
    }

    public void reset(int i) {
        this.mCurrentColors[i] = COLOR_SCHEME.mDefaultColors[i];
    }

    public void reset() {
        reset(COLOR_SCHEME);
    }

    public void reset(TerminalColorScheme terminalColorScheme) {
        System.arraycopy(terminalColorScheme.mDefaultColors, 0, this.mCurrentColors, 0, 259);
    }

    public static int parse(String str) {
        int i;
        try {
            int i2 = 1;
            if (str.charAt(0) == '#') {
                i = 1;
                i2 = 0;
            } else if (!str.startsWith("rgb:")) {
                return Integer.parseInt(str);
            } else {
                i = 4;
            }
            int length = (str.length() - i) - (i2 * 2);
            if (length % 3 != 0) {
                return 0;
            }
            int i3 = length / 3;
            double pow = 255.0d / (Math.pow(2.0d, (double) (i3 * 4)) - 1.0d);
            String substring = str.substring(i, i + i3);
            int i4 = i2 + i3;
            int i5 = i + i4;
            String substring2 = str.substring(i5, i5 + i3);
            int i6 = i5 + i4;
            return ((int) (((double) Integer.parseInt(str.substring(i6, i3 + i6), 16)) * pow)) | -16777216 | (((int) (((double) Integer.parseInt(substring, 16)) * pow)) << 16) | (((int) (((double) Integer.parseInt(substring2, 16)) * pow)) << 8);
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
            return 0;
        }
    }

    public void tryParseColor(int i, String str) {
        int parse = parse(str);
        if (parse != 0) {
            this.mCurrentColors[i] = parse;
        }
    }
}
