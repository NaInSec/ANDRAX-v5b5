package com.thecrackertechnology.dragonterminal.backend;

import java.util.Arrays;

public final class TerminalRow {
    private static final float SPARE_CAPACITY_FACTOR = 1.5f;
    private final int mColumns;
    boolean mHasNonOneWidthOrSurrogateChars;
    boolean mLineWrap;
    private short mSpaceUsed;
    final long[] mStyle;
    public char[] mText;

    public TerminalRow(int i, long j) {
        this.mColumns = i;
        this.mText = new char[((int) (((float) i) * SPARE_CAPACITY_FACTOR))];
        this.mStyle = new long[i];
        clear(j);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: char} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void copyInterval(com.thecrackertechnology.dragonterminal.backend.TerminalRow r10, int r11, int r12, int r13) {
        /*
            r9 = this;
            boolean r0 = r9.mHasNonOneWidthOrSurrogateChars
            boolean r1 = r10.mHasNonOneWidthOrSurrogateChars
            r0 = r0 | r1
            r9.mHasNonOneWidthOrSurrogateChars = r0
            int r0 = r10.findStartOfColumn(r11)
            int r12 = r10.findStartOfColumn(r12)
            r1 = 0
            r2 = 1
            if (r11 <= 0) goto L_0x001d
            int r3 = r11 + -1
            boolean r3 = r10.wideDisplayCharacterStartingAt(r3)
            if (r3 == 0) goto L_0x001d
            r3 = 1
            goto L_0x001e
        L_0x001d:
            r3 = 0
        L_0x001e:
            char[] r4 = r10.mText
            if (r9 != r10) goto L_0x0027
            int r5 = r4.length
            char[] r4 = java.util.Arrays.copyOf(r4, r5)
        L_0x0027:
            r5 = r11
            r11 = 0
        L_0x0029:
            if (r0 >= r12) goto L_0x0052
            char r6 = r4[r0]
            boolean r7 = java.lang.Character.isHighSurrogate(r6)
            if (r7 == 0) goto L_0x003b
            int r0 = r0 + 1
            char r7 = r4[r0]
            int r6 = java.lang.Character.toCodePoint(r6, r7)
        L_0x003b:
            if (r3 == 0) goto L_0x0040
            r6 = 32
            r3 = 0
        L_0x0040:
            int r7 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r6)
            if (r7 <= 0) goto L_0x0049
            int r13 = r13 + r11
            int r5 = r5 + r11
            r11 = r7
        L_0x0049:
            long r7 = r10.getStyle(r5)
            r9.setChar(r13, r6, r7)
            int r0 = r0 + r2
            goto L_0x0029
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalRow.copyInterval(com.thecrackertechnology.dragonterminal.backend.TerminalRow, int, int, int):void");
    }

    public int getSpaceUsed() {
        return this.mSpaceUsed;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findStartOfColumn(int r7) {
        /*
            r6 = this;
            int r0 = r6.mColumns
            if (r7 != r0) goto L_0x0009
            int r7 = r6.getSpaceUsed()
            return r7
        L_0x0009:
            r0 = 0
            r1 = 0
        L_0x000b:
            char[] r2 = r6.mText
            int r3 = r0 + 1
            char r2 = r2[r0]
            boolean r4 = java.lang.Character.isHighSurrogate(r2)
            if (r4 == 0) goto L_0x0022
            char[] r4 = r6.mText
            int r5 = r3 + 1
            char r3 = r4[r3]
            int r2 = java.lang.Character.toCodePoint(r2, r3)
            r3 = r5
        L_0x0022:
            int r2 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r2)
            if (r2 <= 0) goto L_0x005f
            int r1 = r1 + r2
            if (r1 != r7) goto L_0x005c
        L_0x002b:
            short r7 = r6.mSpaceUsed
            if (r3 >= r7) goto L_0x005b
            char[] r7 = r6.mText
            char r7 = r7[r3]
            boolean r7 = java.lang.Character.isHighSurrogate(r7)
            if (r7 == 0) goto L_0x004e
            char[] r7 = r6.mText
            char r0 = r7[r3]
            int r1 = r3 + 1
            char r7 = r7[r1]
            int r7 = java.lang.Character.toCodePoint(r0, r7)
            int r7 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r7)
            if (r7 > 0) goto L_0x005b
            int r3 = r3 + 2
            goto L_0x002b
        L_0x004e:
            char[] r7 = r6.mText
            char r7 = r7[r3]
            int r7 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r7)
            if (r7 > 0) goto L_0x005b
            int r3 = r3 + 1
            goto L_0x002b
        L_0x005b:
            return r3
        L_0x005c:
            if (r1 <= r7) goto L_0x005f
            return r0
        L_0x005f:
            r0 = r3
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalRow.findStartOfColumn(int):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean wideDisplayCharacterStartingAt(int r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            r2 = 0
        L_0x0003:
            short r3 = r6.mSpaceUsed
            if (r1 >= r3) goto L_0x0031
            char[] r3 = r6.mText
            int r4 = r1 + 1
            char r1 = r3[r1]
            boolean r3 = java.lang.Character.isHighSurrogate(r1)
            if (r3 == 0) goto L_0x001e
            char[] r3 = r6.mText
            int r5 = r4 + 1
            char r3 = r3[r4]
            int r1 = java.lang.Character.toCodePoint(r1, r3)
            r4 = r5
        L_0x001e:
            int r1 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r1)
            if (r1 <= 0) goto L_0x002f
            if (r2 != r7) goto L_0x002b
            r3 = 2
            if (r1 != r3) goto L_0x002b
            r7 = 1
            return r7
        L_0x002b:
            int r2 = r2 + r1
            if (r2 <= r7) goto L_0x002f
            return r0
        L_0x002f:
            r1 = r4
            goto L_0x0003
        L_0x0031:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalRow.wideDisplayCharacterStartingAt(int):boolean");
    }

    public void clear(long j) {
        Arrays.fill(this.mText, ' ');
        Arrays.fill(this.mStyle, j);
        this.mSpaceUsed = (short) this.mColumns;
        this.mHasNonOneWidthOrSurrogateChars = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setChar(int r17, int r18, long r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            r2 = r19
            long[] r4 = r0.mStyle
            r4[r17] = r2
            int r4 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r18)
            boolean r5 = r0.mHasNonOneWidthOrSurrogateChars
            r6 = 1
            if (r5 != 0) goto L_0x0022
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r1 >= r5) goto L_0x0020
            if (r4 == r6) goto L_0x001a
            goto L_0x0020
        L_0x001a:
            char[] r2 = r0.mText
            char r1 = (char) r1
            r2[r17] = r1
            return
        L_0x0020:
            r0.mHasNonOneWidthOrSurrogateChars = r6
        L_0x0022:
            r5 = 0
            if (r4 > 0) goto L_0x0027
            r7 = 1
            goto L_0x0028
        L_0x0027:
            r7 = 0
        L_0x0028:
            if (r17 <= 0) goto L_0x0034
            int r8 = r17 + -1
            boolean r8 = r0.wideDisplayCharacterStartingAt(r8)
            if (r8 == 0) goto L_0x0034
            r8 = 1
            goto L_0x0035
        L_0x0034:
            r8 = 0
        L_0x0035:
            r9 = 32
            r10 = 2
            if (r7 == 0) goto L_0x003f
            if (r8 == 0) goto L_0x005a
            int r2 = r17 + -1
            goto L_0x005c
        L_0x003f:
            if (r8 == 0) goto L_0x0046
            int r8 = r17 + -1
            r0.setChar(r8, r9, r2)
        L_0x0046:
            if (r4 != r10) goto L_0x0052
            int r8 = r17 + 1
            boolean r8 = r0.wideDisplayCharacterStartingAt(r8)
            if (r8 == 0) goto L_0x0052
            r8 = 1
            goto L_0x0053
        L_0x0052:
            r8 = 0
        L_0x0053:
            if (r8 == 0) goto L_0x005a
            int r8 = r17 + 1
            r0.setChar(r8, r9, r2)
        L_0x005a:
            r2 = r17
        L_0x005c:
            char[] r3 = r0.mText
            int r8 = r0.findStartOfColumn(r2)
            int r11 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r3, r8)
            int r12 = r2 + r11
            int r13 = r0.mColumns
            if (r12 >= r13) goto L_0x0071
            int r12 = r0.findStartOfColumn(r12)
            goto L_0x0073
        L_0x0071:
            short r12 = r0.mSpaceUsed
        L_0x0073:
            int r12 = r12 - r8
            int r13 = java.lang.Character.charCount(r18)
            if (r7 == 0) goto L_0x007b
            int r13 = r13 + r12
        L_0x007b:
            int r14 = r8 + r12
            int r15 = r8 + r13
            int r13 = r13 - r12
            if (r13 <= 0) goto L_0x009e
            short r9 = r0.mSpaceUsed
            int r6 = r9 - r14
            int r9 = r9 + r13
            int r10 = r3.length
            if (r9 <= r10) goto L_0x009a
            int r9 = r3.length
            int r10 = r0.mColumns
            int r9 = r9 + r10
            char[] r9 = new char[r9]
            java.lang.System.arraycopy(r3, r5, r9, r5, r14)
            java.lang.System.arraycopy(r3, r14, r9, r15, r6)
            r0.mText = r9
            r3 = r9
            goto L_0x00a6
        L_0x009a:
            java.lang.System.arraycopy(r3, r14, r3, r15, r6)
            goto L_0x00a6
        L_0x009e:
            if (r13 >= 0) goto L_0x00a6
            short r6 = r0.mSpaceUsed
            int r6 = r6 - r14
            java.lang.System.arraycopy(r3, r14, r3, r15, r6)
        L_0x00a6:
            short r6 = r0.mSpaceUsed
            int r6 = r6 + r13
            short r6 = (short) r6
            r0.mSpaceUsed = r6
            if (r7 == 0) goto L_0x00af
            goto L_0x00b0
        L_0x00af:
            r12 = 0
        L_0x00b0:
            int r8 = r8 + r12
            java.lang.Character.toChars(r1, r3, r8)
            r1 = 2
            if (r11 != r1) goto L_0x00e8
            r1 = 1
            if (r4 != r1) goto L_0x00e8
            short r1 = r0.mSpaceUsed
            int r2 = r1 + 1
            int r4 = r3.length
            if (r2 <= r4) goto L_0x00d5
            int r1 = r3.length
            int r2 = r0.mColumns
            int r1 = r1 + r2
            char[] r1 = new char[r1]
            java.lang.System.arraycopy(r3, r5, r1, r5, r15)
            int r2 = r15 + 1
            short r4 = r0.mSpaceUsed
            int r4 = r4 - r15
            java.lang.System.arraycopy(r3, r15, r1, r2, r4)
            r0.mText = r1
            goto L_0x00dc
        L_0x00d5:
            int r2 = r15 + 1
            int r1 = r1 - r15
            java.lang.System.arraycopy(r3, r15, r3, r2, r1)
            r1 = r3
        L_0x00dc:
            r2 = 32
            r1[r15] = r2
            short r1 = r0.mSpaceUsed
            r5 = 1
            int r1 = r1 + r5
            short r1 = (short) r1
            r0.mSpaceUsed = r1
            goto L_0x011f
        L_0x00e8:
            r5 = 1
            if (r11 != r5) goto L_0x011f
            r1 = 2
            if (r4 != r1) goto L_0x011f
            int r4 = r0.mColumns
            int r6 = r4 + -1
            if (r2 == r6) goto L_0x0117
            int r4 = r4 - r1
            if (r2 != r4) goto L_0x00fb
            short r1 = (short) r15
            r0.mSpaceUsed = r1
            goto L_0x011f
        L_0x00fb:
            char[] r2 = r0.mText
            char r2 = r2[r15]
            boolean r2 = java.lang.Character.isHighSurrogate(r2)
            if (r2 == 0) goto L_0x0106
            goto L_0x0107
        L_0x0106:
            r1 = 1
        L_0x0107:
            int r1 = r1 + r15
            int r2 = r1 - r15
            short r4 = r0.mSpaceUsed
            int r4 = r4 - r1
            java.lang.System.arraycopy(r3, r1, r3, r15, r4)
            short r1 = r0.mSpaceUsed
            int r1 = r1 - r2
            short r1 = (short) r1
            r0.mSpaceUsed = r1
            goto L_0x011f
        L_0x0117:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Cannot put wide character in last column"
            r1.<init>(r2)
            throw r1
        L_0x011f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalRow.setChar(int, int, long):void");
    }

    /* access modifiers changed from: package-private */
    public boolean isBlank() {
        int spaceUsed = getSpaceUsed();
        for (int i = 0; i < spaceUsed; i++) {
            if (this.mText[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    public final long getStyle(int i) {
        return this.mStyle[i];
    }
}
