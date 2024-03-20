package com.thecrackertechnology.dragonterminal.backend;

public final class TerminalBuffer {
    private int mActiveTranscriptRows = 0;
    int mColumns;
    TerminalRow[] mLines;
    private int mScreenFirstRow = 0;
    int mScreenRows;
    int mTotalRows;

    public TerminalBuffer(int i, int i2, int i3) {
        this.mColumns = i;
        this.mTotalRows = i2;
        this.mScreenRows = i3;
        this.mLines = new TerminalRow[i2];
        blockSet(0, 0, i, i3, 32, TextStyle.NORMAL);
    }

    public String getTranscriptText() {
        return getSelectedText(0, -getActiveTranscriptRows(), this.mColumns, this.mScreenRows).trim();
    }

    public String getSelectedText(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        StringBuilder sb = new StringBuilder();
        int i7 = this.mColumns;
        int i8 = i2;
        int i9 = i8 < (-getActiveTranscriptRows()) ? -getActiveTranscriptRows() : i8;
        int i10 = this.mScreenRows;
        int i11 = i4;
        int i12 = i11 >= i10 ? i10 - 1 : i11;
        int i13 = i9;
        while (i13 <= i12) {
            int i14 = i13 == i9 ? i : 0;
            if (i13 != i12 || (i5 = i3 + 1) > i7) {
                i5 = i7;
            }
            TerminalRow terminalRow = this.mLines[externalToInternalRow(i13)];
            int findStartOfColumn = terminalRow.findStartOfColumn(i14);
            int findStartOfColumn2 = i5 < this.mColumns ? terminalRow.findStartOfColumn(i5) : terminalRow.getSpaceUsed();
            if (findStartOfColumn2 == findStartOfColumn) {
                findStartOfColumn2 = terminalRow.findStartOfColumn(i5 + 1);
            }
            char[] cArr = terminalRow.mText;
            boolean lineWrap = getLineWrap(i13);
            if (!lineWrap || i5 != i7) {
                i6 = -1;
                for (int i15 = findStartOfColumn; i15 < findStartOfColumn2; i15++) {
                    if (cArr[i15] != ' ') {
                        i6 = i15;
                    }
                }
            } else {
                i6 = findStartOfColumn2 - 1;
            }
            if (i6 != -1) {
                sb.append(cArr, findStartOfColumn, (i6 - findStartOfColumn) + 1);
            }
            if (!lineWrap && i13 < i12 && i13 < this.mScreenRows - 1) {
                sb.append(10);
            }
            i13++;
        }
        return sb.toString();
    }

    public int getActiveTranscriptRows() {
        return this.mActiveTranscriptRows;
    }

    public int getActiveRows() {
        return this.mActiveTranscriptRows + this.mScreenRows;
    }

    public int externalToInternalRow(int i) {
        if (i < (-this.mActiveTranscriptRows) || i > this.mScreenRows) {
            throw new IllegalArgumentException("extRow=" + i + ", mScreenRows=" + this.mScreenRows + ", mActiveTranscriptRows=" + this.mActiveTranscriptRows);
        }
        int i2 = this.mScreenFirstRow + i;
        int i3 = this.mTotalRows;
        return i2 < 0 ? i3 + i2 : i2 % i3;
    }

    public void setLineWrap(int i) {
        this.mLines[externalToInternalRow(i)].mLineWrap = true;
    }

    public boolean getLineWrap(int i) {
        return this.mLines[externalToInternalRow(i)].mLineWrap;
    }

    public void clearLineWrap(int i) {
        this.mLines[externalToInternalRow(i)].mLineWrap = false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v35, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v36, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01f6  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0211  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x01e1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0149  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void resize(int r31, int r32, int r33, int[] r34, long r35, boolean r37) {
        /*
            r30 = this;
            r6 = r30
            r0 = r31
            r1 = r32
            r2 = r33
            r7 = r35
            int r3 = r6.mColumns
            r9 = 1
            r10 = 0
            if (r0 != r3) goto L_0x008c
            int r3 = r6.mTotalRows
            if (r1 > r3) goto L_0x008c
            int r0 = r6.mScreenRows
            int r3 = r0 - r1
            if (r3 <= 0) goto L_0x003e
            if (r3 >= r0) goto L_0x003e
            int r0 = r0 - r9
        L_0x001d:
            if (r0 <= 0) goto L_0x0062
            r4 = r34[r9]
            if (r4 < r0) goto L_0x0024
            goto L_0x0062
        L_0x0024:
            int r4 = r6.externalToInternalRow(r0)
            com.thecrackertechnology.dragonterminal.backend.TerminalRow[] r5 = r6.mLines
            r7 = r5[r4]
            if (r7 == 0) goto L_0x0036
            r4 = r5[r4]
            boolean r4 = r4.isBlank()
            if (r4 == 0) goto L_0x003b
        L_0x0036:
            int r3 = r3 + -1
            if (r3 != 0) goto L_0x003b
            goto L_0x0062
        L_0x003b:
            int r0 = r0 + -1
            goto L_0x001d
        L_0x003e:
            if (r3 >= 0) goto L_0x0062
            int r0 = r6.mActiveTranscriptRows
            int r0 = -r0
            int r0 = java.lang.Math.max(r3, r0)
            if (r3 == r0) goto L_0x0062
            r4 = 0
        L_0x004a:
            int r5 = r0 - r3
            if (r4 >= r5) goto L_0x0061
            int r5 = r6.mScreenFirstRow
            int r11 = r6.mScreenRows
            int r5 = r5 + r11
            int r5 = r5 + r4
            int r11 = r6.mTotalRows
            int r5 = r5 % r11
            com.thecrackertechnology.dragonterminal.backend.TerminalRow r5 = r6.allocateFullLineIfNecessary(r5)
            r5.clear(r7)
            int r4 = r4 + 1
            goto L_0x004a
        L_0x0061:
            r3 = r0
        L_0x0062:
            int r0 = r6.mScreenFirstRow
            int r0 = r0 + r3
            r6.mScreenFirstRow = r0
            int r0 = r6.mScreenFirstRow
            int r4 = r6.mTotalRows
            if (r0 >= 0) goto L_0x006f
            int r0 = r0 + r4
            goto L_0x0070
        L_0x006f:
            int r0 = r0 % r4
        L_0x0070:
            r6.mScreenFirstRow = r0
            r6.mTotalRows = r2
            if (r37 == 0) goto L_0x0078
            r0 = 0
            goto L_0x007f
        L_0x0078:
            int r0 = r6.mActiveTranscriptRows
            int r0 = r0 + r3
            int r0 = java.lang.Math.max(r10, r0)
        L_0x007f:
            r6.mActiveTranscriptRows = r0
            r0 = r34[r9]
            int r0 = r0 - r3
            r34[r9] = r0
            r6.mScreenRows = r1
            r1 = 1
            r2 = 0
            goto L_0x0239
        L_0x008c:
            com.thecrackertechnology.dragonterminal.backend.TerminalRow[] r11 = r6.mLines
            com.thecrackertechnology.dragonterminal.backend.TerminalRow[] r3 = new com.thecrackertechnology.dragonterminal.backend.TerminalRow[r2]
            r6.mLines = r3
            r3 = 0
        L_0x0093:
            if (r3 >= r2) goto L_0x00a1
            com.thecrackertechnology.dragonterminal.backend.TerminalRow[] r4 = r6.mLines
            com.thecrackertechnology.dragonterminal.backend.TerminalRow r5 = new com.thecrackertechnology.dragonterminal.backend.TerminalRow
            r5.<init>(r0, r7)
            r4[r3] = r5
            int r3 = r3 + 1
            goto L_0x0093
        L_0x00a1:
            int r3 = r6.mActiveTranscriptRows
            int r12 = r6.mScreenFirstRow
            int r13 = r6.mScreenRows
            int r14 = r6.mTotalRows
            r6.mTotalRows = r2
            r6.mScreenRows = r1
            r6.mScreenFirstRow = r10
            r6.mActiveTranscriptRows = r10
            r6.mColumns = r0
            r15 = r34[r9]
            r4 = r34[r10]
            int r0 = -r3
            r1 = -1
            r5 = r0
            r0 = -1
            r2 = 0
            r3 = 0
            r16 = 0
            r17 = 0
        L_0x00c1:
            if (r5 >= r13) goto L_0x0233
            int r18 = r12 + r5
            if (r18 >= 0) goto L_0x00ca
            int r18 = r14 + r18
            goto L_0x00cc
        L_0x00ca:
            int r18 = r18 % r14
        L_0x00cc:
            r9 = r11[r18]
            if (r5 != r15) goto L_0x00d3
            r18 = 1
            goto L_0x00d5
        L_0x00d3:
            r18 = 0
        L_0x00d5:
            if (r9 == 0) goto L_0x021c
            if (r2 != 0) goto L_0x00db
            if (r18 != 0) goto L_0x00e3
        L_0x00db:
            boolean r19 = r9.isBlank()
            if (r19 == 0) goto L_0x00e3
            goto L_0x021c
        L_0x00e3:
            r31 = r2
            if (r3 <= 0) goto L_0x010d
            r2 = r16
        L_0x00e9:
            if (r10 >= r3) goto L_0x0105
            r32 = r4
            int r4 = r6.mScreenRows
            r33 = r5
            int r5 = r4 + -1
            if (r2 != r5) goto L_0x00fa
            r5 = 0
            r6.scrollDownOneLine(r5, r4, r7)
            goto L_0x00fc
        L_0x00fa:
            int r2 = r2 + 1
        L_0x00fc:
            int r10 = r10 + 1
            r4 = r32
            r5 = r33
            r17 = 0
            goto L_0x00e9
        L_0x0105:
            r32 = r4
            r33 = r5
            r16 = r2
            r10 = 0
            goto L_0x0112
        L_0x010d:
            r32 = r4
            r33 = r5
            r10 = r3
        L_0x0112:
            if (r18 != 0) goto L_0x012e
            boolean r2 = r9.mLineWrap
            if (r2 == 0) goto L_0x0119
            goto L_0x012e
        L_0x0119:
            r2 = 0
            r3 = 0
        L_0x011b:
            int r4 = r9.getSpaceUsed()
            if (r2 >= r4) goto L_0x0138
            char[] r4 = r9.mText
            char r4 = r4[r2]
            r5 = 32
            if (r4 == r5) goto L_0x012b
            int r3 = r2 + 1
        L_0x012b:
            int r2 = r2 + 1
            goto L_0x011b
        L_0x012e:
            int r3 = r9.getSpaceUsed()
            if (r18 == 0) goto L_0x0138
            r4 = r3
            r18 = 1
            goto L_0x013b
        L_0x0138:
            r4 = r3
            r18 = 0
        L_0x013b:
            r2 = 0
            r20 = r0
            r21 = r1
            r1 = r16
            r0 = 0
            r5 = 0
            r16 = r31
        L_0x0147:
            if (r0 >= r4) goto L_0x01e1
            r22 = r2
            char[] r2 = r9.mText
            char r2 = r2[r0]
            boolean r3 = java.lang.Character.isHighSurrogate(r2)
            if (r3 == 0) goto L_0x015f
            char[] r3 = r9.mText
            int r0 = r0 + 1
            char r3 = r3[r0]
            int r2 = java.lang.Character.toCodePoint(r2, r3)
        L_0x015f:
            r24 = r0
            r3 = r2
            int r25 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r3)
            if (r25 <= 0) goto L_0x016c
            long r22 = r9.getStyle(r5)
        L_0x016c:
            int r0 = r17 + r25
            int r2 = r6.mColumns
            if (r0 <= r2) goto L_0x018d
            r6.setLineWrap(r1)
            int r0 = r6.mScreenRows
            r2 = 1
            int r0 = r0 - r2
            if (r1 != r0) goto L_0x0186
            if (r16 == 0) goto L_0x017f
            int r20 = r20 + -1
        L_0x017f:
            int r0 = r6.mScreenRows
            r2 = 0
            r6.scrollDownOneLine(r2, r0, r7)
            goto L_0x0188
        L_0x0186:
            int r1 = r1 + 1
        L_0x0188:
            r26 = r20
            r17 = 0
            goto L_0x018f
        L_0x018d:
            r26 = r20
        L_0x018f:
            r20 = r1
            if (r25 > 0) goto L_0x0197
            if (r17 <= 0) goto L_0x0197
            r0 = 1
            goto L_0x0198
        L_0x0197:
            r0 = 0
        L_0x0198:
            int r1 = r17 - r0
            r0 = r30
            r2 = r20
            r29 = r4
            r37 = r10
            r27 = r11
            r28 = r12
            r10 = r32
            r11 = r33
            r12 = r5
            r4 = r22
            r0.setChar(r1, r2, r3, r4)
            if (r25 <= 0) goto L_0x01c8
            if (r15 != r11) goto L_0x01bc
            if (r10 != r12) goto L_0x01bc
            r21 = r17
            r26 = r20
            r16 = 1
        L_0x01bc:
            int r5 = r12 + r25
            int r17 = r17 + r25
            if (r18 == 0) goto L_0x01c9
            if (r16 == 0) goto L_0x01c9
            r1 = r20
            r0 = 1
            goto L_0x01ee
        L_0x01c8:
            r5 = r12
        L_0x01c9:
            r0 = 1
            int r1 = r24 + 1
            r0 = r1
            r32 = r10
            r33 = r11
            r1 = r20
            r2 = r22
            r20 = r26
            r11 = r27
            r12 = r28
            r4 = r29
            r10 = r37
            goto L_0x0147
        L_0x01e1:
            r37 = r10
            r27 = r11
            r28 = r12
            r0 = 1
            r10 = r32
            r11 = r33
            r26 = r20
        L_0x01ee:
            int r2 = r13 + -1
            if (r11 == r2) goto L_0x0211
            boolean r2 = r9.mLineWrap
            if (r2 != 0) goto L_0x0211
            int r2 = r6.mScreenRows
            int r2 = r2 - r0
            if (r1 != r2) goto L_0x0206
            if (r16 == 0) goto L_0x01ff
            int r26 = r26 + -1
        L_0x01ff:
            int r0 = r6.mScreenRows
            r2 = 0
            r6.scrollDownOneLine(r2, r0, r7)
            goto L_0x0208
        L_0x0206:
            int r1 = r1 + 1
        L_0x0208:
            r3 = r37
            r2 = r16
            r0 = r26
            r17 = 0
            goto L_0x0217
        L_0x0211:
            r3 = r37
            r2 = r16
            r0 = r26
        L_0x0217:
            r16 = r1
            r1 = r21
            goto L_0x0228
        L_0x021c:
            r31 = r2
            r10 = r4
            r27 = r11
            r28 = r12
            r11 = r5
            int r3 = r3 + 1
            r2 = r31
        L_0x0228:
            int r5 = r11 + 1
            r4 = r10
            r11 = r27
            r12 = r28
            r9 = 1
            r10 = 0
            goto L_0x00c1
        L_0x0233:
            r2 = 0
            r34[r2] = r1
            r1 = 1
            r34[r1] = r0
        L_0x0239:
            r0 = r34[r2]
            if (r0 < 0) goto L_0x0241
            r0 = r34[r1]
            if (r0 >= 0) goto L_0x0245
        L_0x0241:
            r34[r1] = r2
            r34[r2] = r2
        L_0x0245:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalBuffer.resize(int, int, int, int[], long, boolean):void");
    }

    private void blockCopyLinesDown(int i, int i2) {
        if (i2 != 0) {
            int i3 = this.mTotalRows;
            int i4 = i2 - 1;
            TerminalRow terminalRow = this.mLines[((i + i4) + 1) % i3];
            while (i4 >= 0) {
                TerminalRow[] terminalRowArr = this.mLines;
                int i5 = i + i4;
                terminalRowArr[(i5 + 1) % i3] = terminalRowArr[i5 % i3];
                i4--;
            }
            this.mLines[i % i3] = terminalRow;
        }
    }

    public void scrollDownOneLine(int i, int i2, long j) {
        int i3 = i2 - 1;
        if (i > i3 || i < 0 || i2 > this.mScreenRows) {
            throw new IllegalArgumentException("topMargin=" + i + ", bottomMargin=" + i2 + ", mScreenRows=" + this.mScreenRows);
        }
        blockCopyLinesDown(this.mScreenFirstRow, i);
        blockCopyLinesDown(externalToInternalRow(i2), this.mScreenRows - i2);
        int i4 = this.mTotalRows;
        this.mScreenFirstRow = (this.mScreenFirstRow + 1) % i4;
        int i5 = this.mActiveTranscriptRows;
        if (i5 < i4 - this.mScreenRows) {
            this.mActiveTranscriptRows = i5 + 1;
        }
        int externalToInternalRow = externalToInternalRow(i3);
        TerminalRow[] terminalRowArr = this.mLines;
        if (terminalRowArr[externalToInternalRow] == null) {
            terminalRowArr[externalToInternalRow] = new TerminalRow(this.mColumns, j);
        } else {
            terminalRowArr[externalToInternalRow].clear(j);
        }
    }

    public void blockCopy(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int i9;
        if (i3 != 0) {
            if (i < 0 || (i7 = i + i3) > (i8 = this.mColumns) || i2 < 0 || i2 + i4 > (i9 = this.mScreenRows) || i5 < 0 || i3 + i5 > i8 || i6 < 0 || i6 + i4 > i9) {
                throw new IllegalArgumentException();
            }
            boolean z = i2 > i6;
            for (int i10 = 0; i10 < i4; i10++) {
                int i11 = z ? i10 : i4 - (i10 + 1);
                allocateFullLineIfNecessary(externalToInternalRow(i11 + i6)).copyInterval(allocateFullLineIfNecessary(externalToInternalRow(i2 + i11)), i, i7, i5);
            }
        }
    }

    public void blockSet(int i, int i2, int i3, int i4, int i5, long j) {
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        if (i6 < 0 || i6 + i8 > this.mColumns || i7 < 0 || i7 + i9 > this.mScreenRows) {
            throw new IllegalArgumentException("Illegal arguments! blockSet(" + i + ", " + i7 + ", " + i8 + ", " + i9 + ", " + i5 + ", " + this.mColumns + ", " + this.mScreenRows + ")");
        }
        for (int i10 = 0; i10 < i9; i10++) {
            for (int i11 = 0; i11 < i8; i11++) {
                setChar(i6 + i11, i7 + i10, i5, j);
            }
        }
    }

    public TerminalRow allocateFullLineIfNecessary(int i) {
        TerminalRow[] terminalRowArr = this.mLines;
        if (terminalRowArr[i] != null) {
            return terminalRowArr[i];
        }
        TerminalRow terminalRow = new TerminalRow(this.mColumns, 0);
        terminalRowArr[i] = terminalRow;
        return terminalRow;
    }

    public void setChar(int i, int i2, int i3, long j) {
        if (i2 >= this.mScreenRows || i >= this.mColumns) {
            throw new IllegalArgumentException("row=" + i2 + ", column=" + i + ", mScreenRows=" + this.mScreenRows + ", mColumns=" + this.mColumns);
        }
        allocateFullLineIfNecessary(externalToInternalRow(i2)).setChar(i, i3, j);
    }

    public long getStyleAt(int i, int i2) {
        return allocateFullLineIfNecessary(externalToInternalRow(i)).getStyle(i2);
    }

    public void setOrClearEffect(int i, boolean z, boolean z2, boolean z3, int i2, int i3, int i4, int i5, int i6, int i7) {
        int i8 = i;
        int i9 = i6;
        int i10 = i4;
        while (i10 < i9) {
            TerminalRow terminalRow = this.mLines[externalToInternalRow(i10)];
            int i11 = (z3 || i10 + 1 == i9) ? i7 : i3;
            for (int i12 = (z3 || i10 == i4) ? i5 : i2; i12 < i11; i12++) {
                long style = terminalRow.getStyle(i12);
                int decodeForeColor = TextStyle.decodeForeColor(style);
                int decodeBackColor = TextStyle.decodeBackColor(style);
                int decodeEffect = TextStyle.decodeEffect(style);
                terminalRow.mStyle[i12] = TextStyle.encode(decodeForeColor, decodeBackColor, z2 ? ((~decodeEffect) & i8) | ((~i8) & decodeEffect) : z ? decodeEffect | i8 : decodeEffect & (~i8));
            }
            i10++;
        }
    }
}
