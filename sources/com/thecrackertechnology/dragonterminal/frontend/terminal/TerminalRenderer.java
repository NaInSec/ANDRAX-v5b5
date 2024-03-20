package com.thecrackertechnology.dragonterminal.frontend.terminal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import com.thecrackertechnology.dragonterminal.backend.TextStyle;
import org.apache.commons.lang3.StringUtils;

final class TerminalRenderer {
    private final float[] asciiMeasures = new float[127];
    private final int mFontAscent;
    final int mFontLineSpacing;
    final int mFontLineSpacingAndAscent;
    final float mFontWidth;
    private final Paint mTextPaint = new Paint();
    final int mTextSize;
    final Typeface mTypeface;
    protected float savedLastDrawnLineX;
    protected float savedLastDrawnLineY;

    public TerminalRenderer(int i, Typeface typeface) {
        this.mTextSize = i;
        this.mTypeface = typeface;
        this.mTextPaint.setTypeface(typeface);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextSize((float) i);
        this.mFontLineSpacing = (int) Math.ceil((double) this.mTextPaint.getFontSpacing());
        this.mFontAscent = (int) Math.ceil((double) this.mTextPaint.ascent());
        this.mFontLineSpacingAndAscent = this.mFontLineSpacing + this.mFontAscent;
        this.mFontWidth = this.mTextPaint.measureText("X");
        StringBuilder sb = new StringBuilder(StringUtils.SPACE);
        for (int i2 = 0; i2 < this.asciiMeasures.length; i2++) {
            sb.setCharAt(0, (char) i2);
            this.asciiMeasures[i2] = this.mTextPaint.measureText(sb, 0, 1);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: char} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01b2 A[EDGE_INSN: B:85:0x01b2->B:73:0x01b2 ?: BREAK  
    EDGE_INSN: B:84:0x01b2->B:73:0x01b2 ?: BREAK  , LOOP:1: B:22:0x0089->B:73:0x01b2, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void render(com.thecrackertechnology.dragonterminal.backend.TerminalEmulator r48, android.graphics.Canvas r49, int r50, int r51, int r52, int r53, int r54) {
        /*
            r47 = this;
            r15 = r47
            r14 = r48
            r12 = r51
            r13 = r52
            boolean r16 = r48.isReverseVideo()
            int r0 = r14.mRows
            int r11 = r50 + r0
            int r10 = r14.mColumns
            int r17 = r48.getCursorCol()
            int r9 = r48.getCursorRow()
            boolean r18 = r48.isShowingCursor()
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r8 = r48.getScreen()
            com.thecrackertechnology.dragonterminal.backend.TerminalColors r0 = r14.mColors
            int[] r7 = r0.mCurrentColors
            int r19 = r48.getCursorStyle()
            if (r16 == 0) goto L_0x0038
            r0 = 256(0x100, float:3.59E-43)
            r0 = r7[r0]
            android.graphics.PorterDuff$Mode r1 = android.graphics.PorterDuff.Mode.SRC
            r6 = r49
            r6.drawColor(r0, r1)
            goto L_0x003a
        L_0x0038:
            r6 = r49
        L_0x003a:
            int r0 = r15.mFontLineSpacingAndAscent
            float r0 = (float) r0
            r5 = r50
        L_0x003f:
            if (r5 >= r11) goto L_0x0225
            int r1 = r15.mFontLineSpacing
            float r1 = (float) r1
            float r20 = r0 + r1
            r0 = -1
            if (r5 != r9) goto L_0x004e
            if (r18 == 0) goto L_0x004e
            r4 = r17
            goto L_0x004f
        L_0x004e:
            r4 = -1
        L_0x004f:
            if (r5 < r12) goto L_0x0062
            if (r5 > r13) goto L_0x0062
            if (r5 != r12) goto L_0x0058
            r1 = r53
            goto L_0x0059
        L_0x0058:
            r1 = -1
        L_0x0059:
            if (r5 != r13) goto L_0x005e
            r2 = r54
            goto L_0x0060
        L_0x005e:
            int r2 = r14.mColumns
        L_0x0060:
            r3 = r1
            goto L_0x0064
        L_0x0062:
            r2 = -1
            r3 = -1
        L_0x0064:
            int r1 = r8.externalToInternalRow(r5)
            com.thecrackertechnology.dragonterminal.backend.TerminalRow r1 = r8.allocateFullLineIfNecessary(r1)
            char[] r12 = r1.mText
            int r13 = r1.getSpaceUsed()
            r21 = 0
            r23 = 0
            r24 = 0
            r25 = r5
            r50 = r13
            r27 = r21
            r0 = 0
            r5 = 0
            r13 = 0
            r21 = -1
            r22 = 0
            r26 = 0
            r29 = 0
        L_0x0089:
            r30 = 258(0x102, float:3.62E-43)
            if (r0 >= r10) goto L_0x01d6
            char r6 = r12[r13]
            boolean r31 = java.lang.Character.isHighSurrogate(r6)
            r32 = r11
            r33 = 1
            if (r31 == 0) goto L_0x009c
            r34 = 2
            goto L_0x009e
        L_0x009c:
            r34 = 1
        L_0x009e:
            if (r31 == 0) goto L_0x00a8
            int r31 = r13 + 1
            char r11 = r12[r31]
            int r6 = java.lang.Character.toCodePoint(r6, r11)
        L_0x00a8:
            int r11 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r6)
            if (r0 < r3) goto L_0x00b0
            if (r0 <= r2) goto L_0x00be
        L_0x00b0:
            if (r4 == r0) goto L_0x00be
            r31 = r10
            r10 = 2
            if (r11 != r10) goto L_0x00bc
            int r10 = r0 + 1
            if (r4 != r10) goto L_0x00bc
            goto L_0x00c0
        L_0x00bc:
            r10 = 0
            goto L_0x00c1
        L_0x00be:
            r31 = r10
        L_0x00c0:
            r10 = 1
        L_0x00c1:
            long r36 = r1.getStyle(r0)
            r38 = r1
            float[] r1 = r15.asciiMeasures
            r39 = r2
            int r2 = r1.length
            if (r6 >= r2) goto L_0x00d3
            r1 = r1[r6]
            r6 = r34
            goto L_0x00db
        L_0x00d3:
            android.graphics.Paint r1 = r15.mTextPaint
            r6 = r34
            float r1 = r1.measureText(r12, r13, r6)
        L_0x00db:
            r34 = r1
            float r1 = r15.mFontWidth
            float r1 = r34 / r1
            float r2 = (float) r11
            float r1 = r1 - r2
            float r1 = java.lang.Math.abs(r1)
            double r1 = (double) r1
            r40 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            int r42 = (r1 > r40 ? 1 : (r1 == r40 ? 0 : -1))
            if (r42 <= 0) goto L_0x00f4
            r40 = 1
            goto L_0x00f6
        L_0x00f4:
            r40 = 0
        L_0x00f6:
            int r1 = (r36 > r27 ? 1 : (r36 == r27 ? 0 : -1))
            if (r1 != 0) goto L_0x0121
            if (r10 != r5) goto L_0x0121
            if (r40 != 0) goto L_0x0121
            if (r29 == 0) goto L_0x0101
            goto L_0x0121
        L_0x0101:
            r15 = r50
            r30 = r0
            r43 = r3
            r44 = r4
            r45 = r8
            r41 = r9
            r42 = r12
            r46 = r13
            r1 = r21
            r0 = r26
            r40 = r29
            r35 = 2
            r21 = r6
            r29 = r7
            r26 = r11
            goto L_0x0193
        L_0x0121:
            if (r0 != 0) goto L_0x013e
            r15 = r50
            r30 = r0
            r43 = r3
            r44 = r4
            r21 = r6
            r29 = r7
            r45 = r8
            r41 = r9
            r22 = r10
            r26 = r11
            r42 = r12
            r46 = r13
            r35 = 2
            goto L_0x018a
        L_0x013e:
            int r29 = r0 - r21
            int r41 = r13 - r22
            if (r5 == 0) goto L_0x014f
            com.thecrackertechnology.dragonterminal.backend.TerminalColors r1 = r14.mColors
            int[] r1 = r1.mCurrentColors
            r1 = r1[r30]
            r30 = r0
            r42 = r1
            goto L_0x0153
        L_0x014f:
            r30 = r0
            r42 = 0
        L_0x0153:
            r0 = r47
            r1 = r49
            r2 = r12
            r43 = r3
            r3 = r7
            r44 = r4
            r4 = r20
            r5 = r21
            r21 = r6
            r6 = r29
            r29 = r7
            r7 = r22
            r45 = r8
            r8 = r41
            r41 = r9
            r9 = r26
            r22 = r10
            r26 = 2
            r10 = r42
            r26 = r11
            r35 = 2
            r11 = r19
            r15 = r50
            r42 = r12
            r46 = r13
            r12 = r27
            r14 = r16
            r0.drawTextRun(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r14)
        L_0x018a:
            r5 = r22
            r1 = r30
            r27 = r36
            r22 = r46
            r0 = 0
        L_0x0193:
            float r0 = r0 + r34
            int r2 = r30 + r26
            int r13 = r46 + r21
        L_0x0199:
            r3 = r42
            if (r13 >= r15) goto L_0x01b2
            int r4 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r3, r13)
            if (r4 > 0) goto L_0x01b2
            char r4 = r3[r13]
            boolean r4 = java.lang.Character.isHighSurrogate(r4)
            if (r4 == 0) goto L_0x01ad
            r4 = 2
            goto L_0x01ae
        L_0x01ad:
            r4 = 1
        L_0x01ae:
            int r13 = r13 + r4
            r42 = r3
            goto L_0x0199
        L_0x01b2:
            r14 = r48
            r6 = r49
            r26 = r0
            r21 = r1
            r0 = r2
            r12 = r3
            r50 = r15
            r7 = r29
            r10 = r31
            r11 = r32
            r1 = r38
            r2 = r39
            r29 = r40
            r9 = r41
            r3 = r43
            r4 = r44
            r8 = r45
            r15 = r47
            goto L_0x0089
        L_0x01d6:
            r29 = r7
            r45 = r8
            r41 = r9
            r31 = r10
            r32 = r11
            r3 = r12
            r46 = r13
            int r6 = r31 - r21
            int r8 = r46 - r22
            r15 = r48
            if (r5 == 0) goto L_0x01f3
            com.thecrackertechnology.dragonterminal.backend.TerminalColors r0 = r15.mColors
            int[] r0 = r0.mCurrentColors
            r0 = r0[r30]
            r10 = r0
            goto L_0x01f4
        L_0x01f3:
            r10 = 0
        L_0x01f4:
            r0 = r47
            r1 = r49
            r2 = r3
            r3 = r29
            r4 = r20
            r5 = r21
            r7 = r22
            r9 = r26
            r11 = r19
            r12 = r27
            r14 = r16
            r0.drawTextRun(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r14)
            int r5 = r25 + 1
            r6 = r49
            r12 = r51
            r13 = r52
            r14 = r15
            r0 = r20
            r7 = r29
            r10 = r31
            r11 = r32
            r9 = r41
            r8 = r45
            r15 = r47
            goto L_0x003f
        L_0x0225:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalRenderer.render(com.thecrackertechnology.dragonterminal.backend.TerminalEmulator, android.graphics.Canvas, int, int, int, int, int):void");
    }

    private void drawTextRun(Canvas canvas, char[] cArr, int[] iArr, float f, int i, int i2, int i3, int i4, float f2, int i5, int i6, long j, boolean z) {
        int i7;
        boolean z2;
        float f3;
        float f4;
        boolean z3;
        int i8;
        float f5;
        int i9;
        float f6 = f;
        int i10 = i5;
        int i11 = i6;
        int decodeForeColor = TextStyle.decodeForeColor(j);
        int decodeEffect = TextStyle.decodeEffect(j);
        int decodeBackColor = TextStyle.decodeBackColor(j);
        boolean z4 = (decodeEffect & 9) != 0;
        boolean z5 = (decodeEffect & 4) != 0;
        boolean z6 = (decodeEffect & 2) != 0;
        boolean z7 = (decodeEffect & 64) != 0;
        boolean z8 = (decodeEffect & 256) != 0;
        if ((decodeForeColor & ViewCompat.MEASURED_STATE_MASK) != -16777216) {
            if (z4 && decodeForeColor >= 0 && decodeForeColor < 8) {
                decodeForeColor += 8;
            }
            decodeForeColor = iArr[decodeForeColor];
        }
        if ((decodeBackColor & ViewCompat.MEASURED_STATE_MASK) != -16777216) {
            decodeBackColor = iArr[decodeBackColor];
        }
        if (z ^ ((decodeEffect & 16) != 0)) {
            i7 = decodeBackColor;
            decodeBackColor = decodeForeColor;
        } else {
            i7 = decodeForeColor;
        }
        float f7 = this.mFontWidth;
        float f8 = ((float) i) * f7;
        float f9 = (float) i2;
        float f10 = f8 + (f9 * f7);
        float f11 = f2 / f7;
        boolean z9 = z4;
        if (((double) Math.abs(f11 - f9)) > 0.01d) {
            canvas.save();
            canvas.scale(f9 / f11, 1.0f);
            float f12 = f11 / f9;
            f3 = f8 * f12;
            f4 = f10 * f12;
            z2 = true;
        } else {
            Canvas canvas2 = canvas;
            f3 = f8;
            f4 = f10;
            z2 = false;
        }
        if (decodeBackColor != iArr[257]) {
            this.mTextPaint.setColor(decodeBackColor);
            float f13 = (f6 - ((float) this.mFontLineSpacingAndAscent)) + ((float) this.mFontAscent);
            i9 = i7;
            Paint paint = this.mTextPaint;
            z3 = z7;
            i8 = ViewCompat.MEASURED_STATE_MASK;
            f5 = f4;
            canvas.drawRect(f3, f13, f4, f, paint);
        } else {
            i9 = i7;
            z3 = z7;
            i8 = ViewCompat.MEASURED_STATE_MASK;
            f5 = f4;
        }
        if (i10 != 0) {
            this.mTextPaint.setColor(i10);
            float f14 = (float) (this.mFontLineSpacingAndAscent - this.mFontAscent);
            if (i11 == 1) {
                f14 = (float) (((double) f14) / 4.0d);
            } else if (i11 == 2) {
                f5 = (float) (((double) f5) - (((double) ((f5 - f3) * 3.0f)) / 4.0d));
            }
            canvas.drawRect(f3, f6 - f14, f5, f, this.mTextPaint);
            this.savedLastDrawnLineX = f3;
            this.savedLastDrawnLineY = f6;
        }
        if ((decodeEffect & 32) == 0) {
            int i12 = z8 ? (((((i9 >> 16) & 255) * 2) / 3) << 16) + i8 + (((((i9 >> 8) & 255) * 2) / 3) << 8) + (((i9 & 255) * 2) / 3) : i9;
            this.mTextPaint.setFakeBoldText(z9);
            this.mTextPaint.setUnderlineText(z5);
            this.mTextPaint.setTextSkewX(z6 ? -0.35f : 0.0f);
            this.mTextPaint.setStrikeThruText(z3);
            this.mTextPaint.setColor(i12);
            canvas.drawText(cArr, i3, i4, f3, f6 - ((float) this.mFontLineSpacingAndAscent), this.mTextPaint);
        }
        if (z2) {
            canvas.restore();
        }
    }

    /* access modifiers changed from: package-private */
    public float getCursorX() {
        return this.savedLastDrawnLineX;
    }

    /* access modifiers changed from: package-private */
    public float getCursorY() {
        return this.savedLastDrawnLineY;
    }
}
