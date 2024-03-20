package com.thecrackertechnology.dragonterminal.backend;

import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Stack;
import kotlin.jvm.internal.ByteCompanionObject;
import org.apache.commons.lang3.StringUtils;

public final class TerminalEmulator {
    public static final int CURSOR_STYLE_BAR = 2;
    public static final int CURSOR_STYLE_BLOCK = 0;
    public static final int CURSOR_STYLE_UNDERLINE = 1;
    private static final int DECSET_BIT_APPLICATION_CURSOR_KEYS = 1;
    private static final int DECSET_BIT_APPLICATION_KEYPAD = 32;
    private static final int DECSET_BIT_AUTOWRAP = 8;
    private static final int DECSET_BIT_BRACKETED_PASTE_MODE = 1024;
    private static final int DECSET_BIT_LEFTRIGHT_MARGIN_MODE = 2048;
    private static final int DECSET_BIT_MOUSE_PROTOCOL_SGR = 512;
    private static final int DECSET_BIT_MOUSE_TRACKING_BUTTON_EVENT = 128;
    private static final int DECSET_BIT_MOUSE_TRACKING_PRESS_RELEASE = 64;
    private static final int DECSET_BIT_ORIGIN_MODE = 4;
    private static final int DECSET_BIT_RECTANGULAR_CHANGEATTRIBUTE = 4096;
    private static final int DECSET_BIT_REVERSE_VIDEO = 2;
    private static final int DECSET_BIT_SEND_FOCUS_EVENTS = 256;
    private static final int DECSET_BIT_SHOWING_CURSOR = 16;
    private static final int ESC = 1;
    private static final int ESC_CSI = 6;
    private static final int ESC_CSI_ARGS_ASTERIX = 16;
    private static final int ESC_CSI_ARGS_SPACE = 15;
    private static final int ESC_CSI_BIGGERTHAN = 12;
    private static final int ESC_CSI_DOLLAR = 8;
    private static final int ESC_CSI_DOUBLE_QUOTE = 17;
    private static final int ESC_CSI_EXCLAMATION = 19;
    private static final int ESC_CSI_QUESTIONMARK = 7;
    private static final int ESC_CSI_QUESTIONMARK_ARG_DOLLAR = 14;
    private static final int ESC_CSI_SINGLE_QUOTE = 18;
    private static final int ESC_NONE = 0;
    private static final int ESC_OSC = 10;
    private static final int ESC_OSC_ESC = 11;
    private static final int ESC_P = 13;
    private static final int ESC_PERCENT = 9;
    private static final int ESC_POUND = 2;
    private static final int ESC_SELECT_LEFT_PAREN = 3;
    private static final int ESC_SELECT_RIGHT_PAREN = 4;
    private static final boolean LOG_ESCAPE_SEQUENCES = false;
    private static final int MAX_ESCAPE_PARAMETERS = 16;
    private static final int MAX_OSC_STRING_LENGTH = 8192;
    public static final int MOUSE_LEFT_BUTTON = 0;
    public static final int MOUSE_LEFT_BUTTON_MOVED = 32;
    public static final int MOUSE_WHEELDOWN_BUTTON = 65;
    public static final int MOUSE_WHEELUP_BUTTON = 64;
    public static final int UNICODE_REPLACEMENT_CHAR = 65533;
    private boolean mAboutToAutoWrap;
    final TerminalBuffer mAltBuffer;
    private int mArgIndex;
    private final int[] mArgs = new int[16];
    int mBackColor;
    private int mBottomMargin;
    public final TerminalColors mColors = new TerminalColors();
    public int mColumns;
    private boolean mContinueSequence;
    private int mCurrentDecSetFlags;
    private int mCursorCol;
    private int mCursorRow;
    private int mCursorStyle = 0;
    private int mEffect;
    private int mEscapeState;
    int mForeColor;
    private boolean mInsertMode;
    private int mLastEmittedCodePoint = -1;
    private int mLeftMargin;
    private final TerminalBuffer mMainBuffer;
    private final StringBuilder mOSCOrDeviceControlArgs = new StringBuilder();
    private int mRightMargin;
    public int mRows;
    private int mSavedDecSetFlags;
    private final SavedScreenState mSavedStateAlt = new SavedScreenState();
    private final SavedScreenState mSavedStateMain = new SavedScreenState();
    private TerminalBuffer mScreen;
    private int mScrollCounter = 0;
    private final TerminalOutput mSession;
    private boolean[] mTabStop;
    private String mTitle;
    private final Stack<String> mTitleStack = new Stack<>();
    private int mTopMargin;
    private boolean mUseLineDrawingG0;
    private boolean mUseLineDrawingG1;
    private boolean mUseLineDrawingUsesG0 = true;
    private byte mUtf8Index;
    private final byte[] mUtf8InputBuffer = new byte[4];
    private byte mUtf8ToFollow;

    private void logError(String str) {
    }

    static int mapDecSetBitToInternalBit(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 25) {
            return 16;
        }
        if (i == 66) {
            return 32;
        }
        if (i == 69) {
            return 2048;
        }
        if (i == 1000) {
            return 64;
        }
        if (i == 1002) {
            return 128;
        }
        if (i == 1004) {
            return 256;
        }
        if (i == 1006) {
            return 512;
        }
        if (i == 2004) {
            return 1024;
        }
        if (i == 5) {
            return 2;
        }
        if (i != 6) {
            return i != 7 ? -1 : 8;
        }
        return 4;
    }

    private boolean isDecsetInternalBitSet(int i) {
        return (i & this.mCurrentDecSetFlags) != 0;
    }

    private void setDecsetinternalBit(int i, boolean z) {
        if (z) {
            if (i == 64) {
                setDecsetinternalBit(128, false);
            } else if (i == 128) {
                setDecsetinternalBit(64, false);
            }
        }
        if (z) {
            this.mCurrentDecSetFlags = i | this.mCurrentDecSetFlags;
            return;
        }
        this.mCurrentDecSetFlags = (~i) & this.mCurrentDecSetFlags;
    }

    public TerminalEmulator(TerminalOutput terminalOutput, int i, int i2, int i3) {
        this.mSession = terminalOutput;
        TerminalBuffer terminalBuffer = new TerminalBuffer(i, i3, i2);
        this.mMainBuffer = terminalBuffer;
        this.mScreen = terminalBuffer;
        this.mAltBuffer = new TerminalBuffer(i, i2, i2);
        this.mRows = i2;
        this.mColumns = i;
        this.mTabStop = new boolean[this.mColumns];
        reset();
    }

    public TerminalBuffer getScreen() {
        return this.mScreen;
    }

    public boolean isAlternateBufferActive() {
        return this.mScreen == this.mAltBuffer;
    }

    public void sendMouseEvent(int i, int i2, int i3, boolean z) {
        if (i2 < 1) {
            i2 = 1;
        }
        int i4 = this.mColumns;
        if (i2 > i4) {
            i2 = i4;
        }
        if (i3 < 1) {
            i3 = 1;
        }
        int i5 = this.mRows;
        if (i3 > i5) {
            i3 = i5;
        }
        if (i != 32 || isDecsetInternalBitSet(128)) {
            char c = 'M';
            if (isDecsetInternalBitSet(512)) {
                TerminalOutput terminalOutput = this.mSession;
                StringBuilder sb = new StringBuilder();
                sb.append("\u001b[<%d;%d;%d");
                if (!z) {
                    c = 'm';
                }
                sb.append(c);
                terminalOutput.write(String.format(sb.toString(), new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)}));
                return;
            }
            if (!z) {
                i = 3;
            }
            if (!(i2 > 223 || i3 > 223)) {
                byte[] bArr = {27, 91, 77, (byte) (i + 32), (byte) (i2 + 32), (byte) (i3 + 32)};
                this.mSession.write(bArr, 0, bArr.length);
            }
        }
    }

    public void resize(int i, int i2) {
        if (this.mRows != i2 || this.mColumns != i) {
            if (i < 2 || i2 < 2) {
                throw new IllegalArgumentException("rows=" + i2 + ", columns=" + i);
            }
            if (this.mRows != i2) {
                this.mRows = i2;
                this.mTopMargin = 0;
                this.mBottomMargin = this.mRows;
            }
            int i3 = this.mColumns;
            if (i3 != i) {
                this.mColumns = i;
                boolean[] zArr = this.mTabStop;
                this.mTabStop = new boolean[this.mColumns];
                setDefaultTabStops();
                System.arraycopy(zArr, 0, this.mTabStop, 0, Math.min(i3, i));
                this.mLeftMargin = 0;
                this.mRightMargin = this.mColumns;
            }
            resizeScreen();
        }
    }

    private void resizeScreen() {
        int[] iArr = {this.mCursorCol, this.mCursorRow};
        this.mScreen.resize(this.mColumns, this.mRows, this.mScreen == this.mAltBuffer ? this.mRows : this.mMainBuffer.mTotalRows, iArr, getStyle(), isAlternateBufferActive());
        this.mCursorCol = iArr[0];
        this.mCursorRow = iArr[1];
    }

    public int getCursorRow() {
        return this.mCursorRow;
    }

    public int getCursorCol() {
        return this.mCursorCol;
    }

    public int getCursorStyle() {
        return this.mCursorStyle;
    }

    public boolean isReverseVideo() {
        return isDecsetInternalBitSet(2);
    }

    public boolean isShowingCursor() {
        return isDecsetInternalBitSet(16);
    }

    public boolean isKeypadApplicationMode() {
        return isDecsetInternalBitSet(32);
    }

    public boolean isCursorKeysApplicationMode() {
        return isDecsetInternalBitSet(1);
    }

    public boolean isMouseTrackingActive() {
        return isDecsetInternalBitSet(64) || isDecsetInternalBitSet(128);
    }

    private void setDefaultTabStops() {
        int i = 0;
        while (i < this.mColumns) {
            this.mTabStop[i] = (i & 7) == 0 && i != 0;
            i++;
        }
    }

    public void append(byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            processByte(bArr[i2]);
        }
    }

    private void processByte(byte b) {
        byte b2;
        byte b3 = this.mUtf8ToFollow;
        if (b3 > 0) {
            if ((b & 192) == 128) {
                byte[] bArr = this.mUtf8InputBuffer;
                byte b4 = this.mUtf8Index;
                this.mUtf8Index = (byte) (b4 + 1);
                bArr[b4] = b;
                byte b5 = (byte) (b3 - 1);
                this.mUtf8ToFollow = b5;
                if (b5 == 0) {
                    byte b6 = this.mUtf8Index;
                    byte b7 = ((byte) (b6 == 2 ? 31 : b6 == 3 ? 15 : 7)) & this.mUtf8InputBuffer[0];
                    int i = 1;
                    while (true) {
                        b2 = this.mUtf8Index;
                        if (i >= b2) {
                            break;
                        }
                        b7 = (b7 << 6) | (this.mUtf8InputBuffer[i] & 63);
                        i++;
                    }
                    if ((b7 <= Byte.MAX_VALUE && b2 > 1) || ((b7 < 2047 && this.mUtf8Index > 2) || (b7 < 65535 && this.mUtf8Index > 3))) {
                        b7 = 65533;
                    }
                    this.mUtf8ToFollow = 0;
                    this.mUtf8Index = 0;
                    if (b7 < 128 || b7 > 159) {
                        int type = Character.getType(b7);
                        if (type == 0 || type == 19) {
                            b7 = 65533;
                        }
                        processCodePoint(b7);
                        return;
                    }
                    return;
                }
                return;
            }
            this.mUtf8ToFollow = 0;
            this.mUtf8Index = 0;
            emitCodePoint(UNICODE_REPLACEMENT_CHAR);
            processByte(b);
        } else if ((b & ByteCompanionObject.MIN_VALUE) == 0) {
            processCodePoint(b);
        } else {
            if ((b & 224) == 192) {
                this.mUtf8ToFollow = 1;
            } else if ((b & 240) == 224) {
                this.mUtf8ToFollow = 2;
            } else if ((b & 248) == 240) {
                this.mUtf8ToFollow = 3;
            } else {
                processCodePoint(UNICODE_REPLACEMENT_CHAR);
                return;
            }
            byte[] bArr2 = this.mUtf8InputBuffer;
            byte b8 = this.mUtf8Index;
            this.mUtf8Index = (byte) (b8 + 1);
            bArr2[b8] = b;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0350, code lost:
        if (r1 == false) goto L_0x032f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0117, code lost:
        if (isDecsetInternalBitSet(r2) != false) goto L_0x0119;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x011b, code lost:
        r2 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0139, code lost:
        if (r0.mScreen == r0.mAltBuffer) goto L_0x0119;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processCodePoint(int r29) {
        /*
            r28 = this;
            r0 = r28
            r1 = r29
            if (r1 == 0) goto L_0x040a
            r2 = 24
            r3 = 0
            if (r1 == r2) goto L_0x03ff
            r4 = 26
            if (r1 == r4) goto L_0x03ff
            r4 = 10
            r5 = 27
            if (r1 == r5) goto L_0x03ee
            r6 = 1
            switch(r1) {
                case 7: goto L_0x03e0;
                case 8: goto L_0x03bc;
                case 9: goto L_0x03b5;
                case 10: goto L_0x03b1;
                case 11: goto L_0x03b1;
                case 12: goto L_0x03b1;
                case 13: goto L_0x03ab;
                case 14: goto L_0x03a8;
                case 15: goto L_0x03a4;
                default: goto L_0x0019;
            }
        L_0x0019:
            r0.mContinueSequence = r3
            int r4 = r0.mEscapeState
            r7 = 4096(0x1000, float:5.74E-42)
            r8 = 126(0x7e, float:1.77E-43)
            r9 = 113(0x71, float:1.58E-43)
            r10 = 112(0x70, float:1.57E-43)
            r11 = 48
            r12 = 120(0x78, float:1.68E-43)
            r13 = 116(0x74, float:1.63E-43)
            r14 = 32
            r15 = 2
            switch(r4) {
                case 0: goto L_0x0397;
                case 1: goto L_0x0393;
                case 2: goto L_0x038f;
                case 3: goto L_0x0388;
                case 4: goto L_0x0381;
                case 5: goto L_0x0031;
                case 6: goto L_0x037d;
                case 7: goto L_0x0379;
                case 8: goto L_0x0172;
                case 9: goto L_0x039c;
                case 10: goto L_0x016d;
                case 11: goto L_0x0168;
                case 12: goto L_0x0163;
                case 13: goto L_0x015e;
                case 14: goto L_0x00f9;
                case 15: goto L_0x00d7;
                case 16: goto L_0x00bf;
                case 17: goto L_0x0098;
                case 18: goto L_0x0042;
                case 19: goto L_0x0036;
                default: goto L_0x0031;
            }
        L_0x0031:
            r28.unknownSequence(r29)
            goto L_0x039c
        L_0x0036:
            if (r1 != r10) goto L_0x003d
            r28.reset()
            goto L_0x039c
        L_0x003d:
            r28.unknownSequence(r29)
            goto L_0x039c
        L_0x0042:
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 != r2) goto L_0x006b
            int r1 = r0.mRightMargin
            int r2 = r0.mCursorCol
            int r1 = r1 - r2
            int r2 = r0.getArg0(r6)
            int r2 = java.lang.Math.min(r2, r1)
            int r7 = r1 - r2
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r4 = r0.mScreen
            int r5 = r0.mCursorCol
            r6 = 0
            int r8 = r0.mRows
            int r9 = r5 + r2
            r10 = 0
            r4.blockCopy(r5, r6, r7, r8, r9, r10)
            int r1 = r0.mCursorCol
            int r4 = r0.mRows
            r0.blockClear(r1, r3, r2, r4)
            goto L_0x039c
        L_0x006b:
            if (r1 != r8) goto L_0x0093
            int r1 = r0.mRightMargin
            int r2 = r0.mCursorCol
            int r1 = r1 - r2
            int r2 = r0.getArg0(r6)
            int r2 = java.lang.Math.min(r2, r1)
            int r1 = r1 - r2
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r4 = r0.mScreen
            int r9 = r0.mCursorCol
            int r5 = r9 + r2
            r6 = 0
            int r8 = r0.mRows
            r10 = 0
            r7 = r1
            r4.blockCopy(r5, r6, r7, r8, r9, r10)
            int r4 = r0.mCursorRow
            int r4 = r4 + r1
            int r1 = r0.mRows
            r0.blockClear(r4, r3, r2, r1)
            goto L_0x039c
        L_0x0093:
            r28.unknownSequence(r29)
            goto L_0x039c
        L_0x0098:
            if (r1 != r9) goto L_0x00ba
            int r2 = r0.getArg0(r3)
            if (r2 == 0) goto L_0x00b2
            if (r2 != r15) goto L_0x00a3
            goto L_0x00b2
        L_0x00a3:
            if (r2 != r6) goto L_0x00ad
            int r1 = r0.mEffect
            r1 = r1 | 128(0x80, float:1.794E-43)
            r0.mEffect = r1
            goto L_0x039c
        L_0x00ad:
            r28.unknownSequence(r29)
            goto L_0x039c
        L_0x00b2:
            int r1 = r0.mEffect
            r1 = r1 & -129(0xffffffffffffff7f, float:NaN)
            r0.mEffect = r1
            goto L_0x039c
        L_0x00ba:
            r28.unknownSequence(r29)
            goto L_0x039c
        L_0x00bf:
            int r2 = r0.getArg0(r3)
            if (r1 != r12) goto L_0x00d2
            if (r2 < 0) goto L_0x00d2
            if (r2 > r15) goto L_0x00d2
            if (r2 != r15) goto L_0x00cc
            goto L_0x00cd
        L_0x00cc:
            r6 = 0
        L_0x00cd:
            r0.setDecsetinternalBit(r7, r6)
            goto L_0x039c
        L_0x00d2:
            r28.unknownSequence(r29)
            goto L_0x039c
        L_0x00d7:
            int r2 = r0.getArg0(r3)
            if (r1 == r9) goto L_0x00e8
            if (r1 == r13) goto L_0x039c
            r2 = 117(0x75, float:1.64E-43)
            if (r1 == r2) goto L_0x039c
            r28.unknownSequence(r29)
            goto L_0x039c
        L_0x00e8:
            switch(r2) {
                case 0: goto L_0x00f5;
                case 1: goto L_0x00f5;
                case 2: goto L_0x00f5;
                case 3: goto L_0x00f1;
                case 4: goto L_0x00f1;
                case 5: goto L_0x00ed;
                case 6: goto L_0x00ed;
                default: goto L_0x00eb;
            }
        L_0x00eb:
            goto L_0x039c
        L_0x00ed:
            r0.mCursorStyle = r15
            goto L_0x039c
        L_0x00f1:
            r0.mCursorStyle = r6
            goto L_0x039c
        L_0x00f5:
            r0.mCursorStyle = r3
            goto L_0x039c
        L_0x00f9:
            if (r1 != r10) goto L_0x0159
            int r1 = r0.getArg0(r3)
            r2 = 47
            if (r1 == r2) goto L_0x0135
            r2 = 1047(0x417, float:1.467E-42)
            if (r1 == r2) goto L_0x0135
            r2 = 1049(0x419, float:1.47E-42)
            if (r1 != r2) goto L_0x010c
            goto L_0x0135
        L_0x010c:
            int r2 = mapDecSetBitToInternalBit(r1)
            r4 = -1
            if (r2 != r4) goto L_0x011d
            boolean r2 = r0.isDecsetInternalBitSet(r2)
            if (r2 == 0) goto L_0x011b
        L_0x0119:
            r2 = 1
            goto L_0x013c
        L_0x011b:
            r2 = 2
            goto L_0x013c
        L_0x011d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Got DECRQM for unrecognized private DEC mode="
            r2.append(r4)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            java.lang.String r4 = "NeoTerm-Emulator"
            android.util.Log.e(r4, r2)
            r2 = 0
            goto L_0x013c
        L_0x0135:
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r2 = r0.mScreen
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r4 = r0.mAltBuffer
            if (r2 != r4) goto L_0x011b
            goto L_0x0119
        L_0x013c:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r4 = r0.mSession
            java.util.Locale r5 = java.util.Locale.US
            java.lang.Object[] r7 = new java.lang.Object[r15]
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r7[r3] = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r7[r6] = r1
            java.lang.String r1 = "\u001b[?%d;%d$y"
            java.lang.String r1 = java.lang.String.format(r5, r1, r7)
            r4.write(r1)
            goto L_0x039c
        L_0x0159:
            r28.unknownSequence(r29)
            goto L_0x039c
        L_0x015e:
            r28.doDeviceControl(r29)
            goto L_0x039c
        L_0x0163:
            r28.doCsiBiggerThan(r29)
            goto L_0x039c
        L_0x0168:
            r28.doOscEsc(r29)
            goto L_0x039c
        L_0x016d:
            r28.doOsc(r29)
            goto L_0x039c
        L_0x0172:
            r4 = 4
            boolean r9 = r0.isDecsetInternalBitSet(r4)
            if (r9 == 0) goto L_0x017c
            int r10 = r0.mTopMargin
            goto L_0x017d
        L_0x017c:
            r10 = 0
        L_0x017d:
            if (r9 == 0) goto L_0x0182
            int r11 = r0.mBottomMargin
            goto L_0x0184
        L_0x0182:
            int r11 = r0.mRows
        L_0x0184:
            if (r9 == 0) goto L_0x0189
            int r7 = r0.mLeftMargin
            goto L_0x018a
        L_0x0189:
            r7 = 0
        L_0x018a:
            if (r9 == 0) goto L_0x018f
            int r9 = r0.mRightMargin
            goto L_0x0191
        L_0x018f:
            int r9 = r0.mColumns
        L_0x0191:
            r2 = 114(0x72, float:1.6E-43)
            if (r1 == r2) goto L_0x02b2
            if (r1 == r13) goto L_0x02b2
            r2 = 118(0x76, float:1.65E-43)
            if (r1 == r2) goto L_0x023e
            if (r1 == r12) goto L_0x01aa
            r2 = 122(0x7a, float:1.71E-43)
            if (r1 == r2) goto L_0x01aa
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L_0x01aa
            r28.unknownSequence(r29)
            goto L_0x039c
        L_0x01aa:
            if (r1 == r12) goto L_0x01ae
            r2 = 1
            goto L_0x01af
        L_0x01ae:
            r2 = 0
        L_0x01af:
            r4 = 123(0x7b, float:1.72E-43)
            if (r1 != r4) goto L_0x01b5
            r1 = 1
            goto L_0x01b6
        L_0x01b5:
            r1 = 0
        L_0x01b6:
            if (r2 == 0) goto L_0x01bc
            if (r1 == 0) goto L_0x01bc
            r4 = 1
            goto L_0x01bd
        L_0x01bc:
            r4 = 0
        L_0x01bd:
            if (r2 == 0) goto L_0x01c3
            r2 = 32
            r5 = 0
            goto L_0x01c9
        L_0x01c3:
            r2 = -1
            int r2 = r0.getArg(r3, r2, r6)
            r5 = 1
        L_0x01c9:
            if (r2 < r14) goto L_0x01cd
            if (r2 <= r8) goto L_0x01d5
        L_0x01cd:
            r8 = 160(0xa0, float:2.24E-43)
            if (r2 < r8) goto L_0x039c
            r8 = 255(0xff, float:3.57E-43)
            if (r2 > r8) goto L_0x039c
        L_0x01d5:
            int r8 = r5 + 1
            int r5 = r0.getArg(r5, r6, r6)
            int r5 = r5 + r10
            int r12 = r11 + 1
            int r5 = java.lang.Math.min(r5, r12)
            int r12 = r8 + 1
            int r8 = r0.getArg(r8, r6, r6)
            int r8 = r8 + r7
            int r13 = r9 + 1
            int r8 = java.lang.Math.min(r8, r13)
            int r13 = r12 + 1
            int r14 = r0.mRows
            int r12 = r0.getArg(r12, r14, r6)
            int r12 = r12 + r10
            int r10 = java.lang.Math.min(r12, r11)
            int r11 = r0.mColumns
            int r11 = r0.getArg(r13, r11, r6)
            int r11 = r11 + r7
            int r7 = java.lang.Math.min(r11, r9)
            long r11 = r28.getStyle()
            int r5 = r5 - r6
        L_0x020c:
            if (r5 >= r10) goto L_0x039c
            int r9 = r8 + -1
        L_0x0210:
            if (r9 >= r7) goto L_0x023b
            if (r1 == 0) goto L_0x0222
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r13 = r0.mScreen
            long r13 = r13.getStyleAt(r5, r9)
            int r13 = com.thecrackertechnology.dragonterminal.backend.TextStyle.decodeEffect(r13)
            r13 = r13 & 128(0x80, float:1.794E-43)
            if (r13 != 0) goto L_0x0238
        L_0x0222:
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r15 = r0.mScreen
            if (r4 == 0) goto L_0x022d
            long r13 = r15.getStyleAt(r5, r9)
            r19 = r13
            goto L_0x022f
        L_0x022d:
            r19 = r11
        L_0x022f:
            r16 = r9
            r17 = r5
            r18 = r2
            r15.setChar(r16, r17, r18, r19)
        L_0x0238:
            int r9 = r9 + 1
            goto L_0x0210
        L_0x023b:
            int r5 = r5 + 1
            goto L_0x020c
        L_0x023e:
            int r1 = r0.getArg(r3, r6, r6)
            int r1 = r1 - r6
            int r1 = r1 + r10
            int r2 = r0.mRows
            int r1 = java.lang.Math.min(r1, r2)
            int r2 = r0.getArg(r6, r6, r6)
            int r2 = r2 - r6
            int r2 = r2 + r7
            int r4 = r0.mColumns
            int r2 = java.lang.Math.min(r2, r4)
            int r4 = r0.mRows
            int r4 = r0.getArg(r15, r4, r6)
            int r4 = r4 + r10
            int r4 = java.lang.Math.max(r4, r1)
            int r5 = r0.mRows
            int r4 = java.lang.Math.min(r4, r5)
            r5 = 3
            int r8 = r0.mColumns
            int r5 = r0.getArg(r5, r8, r6)
            int r5 = r5 + r7
            int r5 = java.lang.Math.max(r5, r2)
            int r8 = r0.mColumns
            int r5 = java.lang.Math.min(r5, r8)
            r8 = 5
            int r8 = r0.getArg(r8, r6, r6)
            int r8 = r8 - r6
            int r8 = r8 + r10
            int r9 = r0.mRows
            int r22 = java.lang.Math.min(r8, r9)
            r8 = 6
            int r8 = r0.getArg(r8, r6, r6)
            int r8 = r8 - r6
            int r8 = r8 + r7
            int r6 = r0.mColumns
            int r21 = java.lang.Math.min(r8, r6)
            int r6 = r0.mRows
            int r6 = r6 - r22
            int r4 = r4 - r1
            int r20 = java.lang.Math.min(r6, r4)
            int r4 = r0.mColumns
            int r4 = r4 - r21
            int r5 = r5 - r2
            int r19 = java.lang.Math.min(r4, r5)
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r4 = r0.mScreen
            r16 = r4
            r17 = r2
            r18 = r1
            r16.blockCopy(r17, r18, r19, r20, r21, r22)
            goto L_0x039c
        L_0x02b2:
            if (r1 != r13) goto L_0x02b6
            r1 = 1
            goto L_0x02b7
        L_0x02b6:
            r1 = 0
        L_0x02b7:
            int r2 = r0.getArg(r3, r6, r6)
            int r2 = r2 - r6
            int r2 = java.lang.Math.min(r2, r11)
            int r2 = r2 + r10
            int r8 = r0.getArg(r6, r6, r6)
            int r8 = r8 - r6
            int r8 = java.lang.Math.min(r8, r9)
            int r8 = r8 + r7
            int r12 = r0.mRows
            int r12 = r0.getArg(r15, r12, r6)
            int r12 = r12 + r6
            int r11 = r11 - r6
            int r11 = java.lang.Math.min(r12, r11)
            int r10 = r10 + r11
            r11 = 3
            int r12 = r0.mColumns
            int r11 = r0.getArg(r11, r12, r6)
            int r11 = r11 + r6
            int r12 = r9 + -1
            int r11 = java.lang.Math.min(r11, r12)
            int r11 = r11 + r7
            int r12 = r0.mArgIndex
            if (r12 < r4) goto L_0x039c
            int[] r13 = r0.mArgs
            int r14 = r13.length
            if (r12 < r14) goto L_0x02f4
            int r12 = r13.length
            int r12 = r12 - r6
            r0.mArgIndex = r12
        L_0x02f4:
            r12 = 4
        L_0x02f5:
            int r13 = r0.mArgIndex
            if (r12 > r13) goto L_0x039c
            int r13 = r0.getArg(r12, r3, r3)
            if (r13 == 0) goto L_0x034a
            if (r13 == r6) goto L_0x0345
            if (r13 == r4) goto L_0x0340
            r14 = 5
            if (r13 == r14) goto L_0x0339
            r14 = 7
            if (r13 == r14) goto L_0x0332
            r14 = 22
            if (r13 == r14) goto L_0x032b
            if (r13 == r5) goto L_0x0324
            r14 = 24
            if (r13 == r14) goto L_0x0321
            r15 = 25
            if (r13 == r15) goto L_0x031c
            r18 = 0
        L_0x0319:
            r19 = 1
            goto L_0x0353
        L_0x031c:
            r13 = 8
            r18 = 8
            goto L_0x032f
        L_0x0321:
            r18 = 4
            goto L_0x032f
        L_0x0324:
            r14 = 24
            r13 = 16
            r18 = 16
            goto L_0x032f
        L_0x032b:
            r14 = 24
            r18 = 1
        L_0x032f:
            r19 = 0
            goto L_0x0353
        L_0x0332:
            r14 = 24
            r13 = 16
            r18 = 16
            goto L_0x0319
        L_0x0339:
            r14 = 24
            r13 = 8
            r18 = 8
            goto L_0x0319
        L_0x0340:
            r14 = 24
            r18 = 4
            goto L_0x0319
        L_0x0345:
            r14 = 24
            r18 = 1
            goto L_0x0319
        L_0x034a:
            r14 = 24
            r13 = 29
            r18 = 29
            if (r1 != 0) goto L_0x0319
            goto L_0x032f
        L_0x0353:
            if (r1 == 0) goto L_0x035a
            if (r19 != 0) goto L_0x035a
            r15 = 4096(0x1000, float:5.74E-42)
            goto L_0x0375
        L_0x035a:
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r13 = r0.mScreen
            r15 = 4096(0x1000, float:5.74E-42)
            boolean r21 = r0.isDecsetInternalBitSet(r15)
            r17 = r13
            r20 = r1
            r22 = r7
            r23 = r9
            r24 = r2
            r25 = r8
            r26 = r10
            r27 = r11
            r17.setOrClearEffect(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
        L_0x0375:
            int r12 = r12 + 1
            goto L_0x02f5
        L_0x0379:
            r28.doCsiQuestionMark(r29)
            goto L_0x039c
        L_0x037d:
            r28.doCsi(r29)
            goto L_0x039c
        L_0x0381:
            if (r1 != r11) goto L_0x0384
            goto L_0x0385
        L_0x0384:
            r6 = 0
        L_0x0385:
            r0.mUseLineDrawingG1 = r6
            goto L_0x039c
        L_0x0388:
            if (r1 != r11) goto L_0x038b
            goto L_0x038c
        L_0x038b:
            r6 = 0
        L_0x038c:
            r0.mUseLineDrawingG0 = r6
            goto L_0x039c
        L_0x038f:
            r28.doEscPound(r29)
            goto L_0x039c
        L_0x0393:
            r28.doEsc(r29)
            goto L_0x039c
        L_0x0397:
            if (r1 < r14) goto L_0x039c
            r28.emitCodePoint(r29)
        L_0x039c:
            boolean r1 = r0.mContinueSequence
            if (r1 != 0) goto L_0x040a
            r0.mEscapeState = r3
            goto L_0x040a
        L_0x03a4:
            r0.mUseLineDrawingUsesG0 = r6
            goto L_0x040a
        L_0x03a8:
            r0.mUseLineDrawingUsesG0 = r3
            goto L_0x040a
        L_0x03ab:
            int r1 = r0.mLeftMargin
            r0.setCursorCol(r1)
            goto L_0x040a
        L_0x03b1:
            r28.doLinefeed()
            goto L_0x040a
        L_0x03b5:
            int r1 = r0.nextTabStop(r6)
            r0.mCursorCol = r1
            goto L_0x040a
        L_0x03bc:
            int r1 = r0.mLeftMargin
            int r2 = r0.mCursorCol
            if (r1 != r2) goto L_0x03db
            int r1 = r0.mCursorRow
            int r1 = r1 - r6
            if (r1 < 0) goto L_0x040a
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r2 = r0.mScreen
            boolean r2 = r2.getLineWrap(r1)
            if (r2 == 0) goto L_0x040a
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r2 = r0.mScreen
            r2.clearLineWrap(r1)
            int r2 = r0.mRightMargin
            int r2 = r2 - r6
            r0.setCursorRowCol(r1, r2)
            goto L_0x040a
        L_0x03db:
            int r2 = r2 - r6
            r0.setCursorCol(r2)
            goto L_0x040a
        L_0x03e0:
            int r2 = r0.mEscapeState
            if (r2 != r4) goto L_0x03e8
            r28.doOsc(r29)
            goto L_0x040a
        L_0x03e8:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r1 = r0.mSession
            r1.onBell()
            goto L_0x040a
        L_0x03ee:
            int r2 = r0.mEscapeState
            r3 = 13
            if (r2 != r3) goto L_0x03f5
            return
        L_0x03f5:
            if (r2 == r4) goto L_0x03fb
            r28.startEscapeSequence()
            goto L_0x040a
        L_0x03fb:
            r28.doOsc(r29)
            goto L_0x040a
        L_0x03ff:
            int r1 = r0.mEscapeState
            if (r1 == 0) goto L_0x040a
            r0.mEscapeState = r3
            r1 = 127(0x7f, float:1.78E-43)
            r0.emitCodePoint(r1)
        L_0x040a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalEmulator.processCodePoint(int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x011f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0190  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doDeviceControl(int r15) {
        /*
            r14 = this;
            r0 = 0
            r1 = 92
            if (r15 == r1) goto L_0x0025
            java.lang.StringBuilder r1 = r14.mOSCOrDeviceControlArgs
            int r1 = r1.length()
            r2 = 8192(0x2000, float:1.14794E-41)
            if (r1 <= r2) goto L_0x0019
            java.lang.StringBuilder r15 = r14.mOSCOrDeviceControlArgs
            r15.setLength(r0)
            r14.finishSequence()
            goto L_0x01f1
        L_0x0019:
            java.lang.StringBuilder r0 = r14.mOSCOrDeviceControlArgs
            r0.appendCodePoint(r15)
            int r15 = r14.mEscapeState
            r14.continueSequence(r15)
            goto L_0x01f1
        L_0x0025:
            java.lang.StringBuilder r15 = r14.mOSCOrDeviceControlArgs
            java.lang.String r15 = r15.toString()
            java.lang.String r1 = "$q"
            boolean r1 = r15.startsWith(r1)
            java.lang.String r2 = "'"
            java.lang.String r3 = "\u001b\\"
            if (r1 == 0) goto L_0x0075
            java.lang.String r0 = "$q\"p"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x005c
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r15 = r14.mSession
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "\u001bP1$r"
            r0.append(r1)
            java.lang.String r1 = "64;1\"p"
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            r15.write(r0)
            goto L_0x01ee
        L_0x005c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unrecognized DECRQSS string: '"
            r0.append(r1)
            r0.append(r15)
            r0.append(r2)
            java.lang.String r15 = r0.toString()
            r14.finishSequenceAndLogError(r15)
            goto L_0x01ee
        L_0x0075:
            java.lang.String r1 = "+q"
            boolean r1 = r15.startsWith(r1)
            if (r1 == 0) goto L_0x01ee
            r1 = 2
            java.lang.String r15 = r15.substring(r1)
            java.lang.String r4 = ";"
            java.lang.String[] r15 = r15.split(r4)
            int r4 = r15.length
            r5 = 0
        L_0x008a:
            if (r5 >= r4) goto L_0x01ee
            r6 = r15[r5]
            int r7 = r6.length()
            int r7 = r7 % r1
            java.lang.String r8 = "NeoTerm-Emulator"
            if (r7 != 0) goto L_0x01d6
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r9 = 0
        L_0x009d:
            int r10 = r6.length()
            if (r9 >= r10) goto L_0x00d6
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "0x"
            r10.append(r11)
            char r11 = r6.charAt(r9)
            r10.append(r11)
            java.lang.String r11 = ""
            r10.append(r11)
            int r11 = r9 + 1
            char r11 = r6.charAt(r11)
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            java.lang.Long r10 = java.lang.Long.decode(r10)
            long r10 = r10.longValue()
            int r11 = (int) r10
            char r10 = (char) r11
            r7.append(r10)
            int r9 = r9 + 2
            goto L_0x009d
        L_0x00d6:
            java.lang.String r7 = r7.toString()
            int r9 = r7.hashCode()
            r10 = -1354842768(0xffffffffaf3ebd70, float:-1.7347701E-10)
            r11 = 3
            r12 = -1
            r13 = 1
            if (r9 == r10) goto L_0x0112
            r10 = 2188(0x88c, float:3.066E-42)
            if (r9 == r10) goto L_0x0108
            r10 = 2682(0xa7a, float:3.758E-42)
            if (r9 == r10) goto L_0x00fe
            r10 = 3373707(0x337a8b, float:4.72757E-39)
            if (r9 == r10) goto L_0x00f4
            goto L_0x011c
        L_0x00f4:
            java.lang.String r9 = "name"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x011c
            r9 = 3
            goto L_0x011d
        L_0x00fe:
            java.lang.String r9 = "TN"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x011c
            r9 = 2
            goto L_0x011d
        L_0x0108:
            java.lang.String r9 = "Co"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x011c
            r9 = 0
            goto L_0x011d
        L_0x0112:
            java.lang.String r9 = "colors"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x011c
            r9 = 1
            goto L_0x011d
        L_0x011c:
            r9 = -1
        L_0x011d:
            if (r9 == 0) goto L_0x0137
            if (r9 == r13) goto L_0x0137
            if (r9 == r1) goto L_0x0134
            if (r9 == r11) goto L_0x0134
            boolean r9 = r14.isDecsetInternalBitSet(r13)
            r10 = 32
            boolean r10 = r14.isDecsetInternalBitSet(r10)
            java.lang.String r9 = com.thecrackertechnology.dragonterminal.backend.KeyHandler.getCodeFromTermcap(r7, r9, r10)
            goto L_0x0139
        L_0x0134:
            java.lang.String r9 = "xterm"
            goto L_0x0139
        L_0x0137:
            java.lang.String r9 = "256"
        L_0x0139:
            if (r9 != 0) goto L_0x0190
            int r9 = r7.hashCode()
            r10 = 1196(0x4ac, float:1.676E-42)
            if (r9 == r10) goto L_0x0152
            r10 = 1234(0x4d2, float:1.729E-42)
            if (r9 == r10) goto L_0x0148
            goto L_0x015b
        L_0x0148:
            java.lang.String r9 = "&8"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x015b
            r12 = 1
            goto L_0x015b
        L_0x0152:
            java.lang.String r9 = "%1"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x015b
            r12 = 0
        L_0x015b:
            if (r12 == 0) goto L_0x0176
            if (r12 == r13) goto L_0x0176
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Unhandled termcap/terminfo name: '"
            r9.append(r10)
            r9.append(r7)
            r9.append(r2)
            java.lang.String r7 = r9.toString()
            android.util.Log.w(r8, r7)
        L_0x0176:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r7 = r14.mSession
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "\u001bP0+r"
            r8.append(r9)
            r8.append(r6)
            r8.append(r3)
            java.lang.String r6 = r8.toString()
            r7.write(r6)
            goto L_0x01ea
        L_0x0190:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r8 = 0
        L_0x0196:
            int r10 = r9.length()
            if (r8 >= r10) goto L_0x01b4
            java.lang.Object[] r10 = new java.lang.Object[r13]
            char r11 = r9.charAt(r8)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r10[r0] = r11
            java.lang.String r11 = "%02X"
            java.lang.String r10 = java.lang.String.format(r11, r10)
            r7.append(r10)
            int r8 = r8 + 1
            goto L_0x0196
        L_0x01b4:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r8 = r14.mSession
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "\u001bP1+r"
            r9.append(r10)
            r9.append(r6)
            java.lang.String r6 = "="
            r9.append(r6)
            r9.append(r7)
            r9.append(r3)
            java.lang.String r6 = r9.toString()
            r8.write(r6)
            goto L_0x01ea
        L_0x01d6:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "Invalid device termcap/terminfo name of odd length: "
            r7.append(r9)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            android.util.Log.e(r8, r6)
        L_0x01ea:
            int r5 = r5 + 1
            goto L_0x008a
        L_0x01ee:
            r14.finishSequence()
        L_0x01f1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalEmulator.doDeviceControl(int):void");
    }

    private int nextTabStop(int i) {
        int i2 = this.mCursorCol;
        while (true) {
            i2++;
            if (i2 >= this.mColumns) {
                return this.mRightMargin - 1;
            }
            if (this.mTabStop[i2] && i - 1 == 0) {
                return Math.min(i2, this.mRightMargin);
            }
        }
    }

    private void doCsiQuestionMark(int i) {
        int i2;
        int i3;
        if (i != 36) {
            int i4 = 0;
            if (i == 104 || i == 108) {
                int i5 = this.mArgIndex;
                int[] iArr = this.mArgs;
                if (i5 >= iArr.length) {
                    this.mArgIndex = iArr.length - 1;
                }
                for (int i6 = 0; i6 <= this.mArgIndex; i6++) {
                    doDecSetOrReset(i == 104, this.mArgs[i6]);
                }
                return;
            }
            int i7 = -1;
            if (i != 110) {
                if (i == 74 || i == 75) {
                    this.mAboutToAutoWrap = false;
                    boolean z = i == 75;
                    int arg0 = getArg0(0);
                    if (arg0 == 0) {
                        i4 = this.mCursorCol;
                        i7 = this.mCursorRow;
                        i2 = this.mColumns;
                        if (z) {
                            i3 = i7 + 1;
                        } else {
                            i3 = this.mRows;
                        }
                    } else if (arg0 == 1) {
                        i7 = z ? this.mCursorRow : 0;
                        i2 = this.mCursorCol + 1;
                        i3 = 1 + this.mCursorRow;
                    } else if (arg0 != 2) {
                        unknownSequence(i);
                        i2 = -1;
                        i4 = -1;
                        i3 = -1;
                    } else {
                        i7 = z ? this.mCursorRow : 0;
                        i2 = this.mColumns;
                        i3 = z ? this.mCursorRow + 1 : this.mRows;
                    }
                    long style = getStyle();
                    while (i7 < i3) {
                        for (int i8 = i4; i8 < i2; i8++) {
                            if ((TextStyle.decodeEffect(this.mScreen.getStyleAt(i7, i8)) & 128) == 0) {
                                this.mScreen.setChar(i8, i7, 32, style);
                            }
                        }
                        i7++;
                    }
                } else if (i == 114 || i == 115) {
                    int i9 = this.mArgIndex;
                    int[] iArr2 = this.mArgs;
                    if (i9 >= iArr2.length) {
                        this.mArgIndex = iArr2.length - 1;
                    }
                    for (int i10 = 0; i10 <= this.mArgIndex; i10++) {
                        int i11 = this.mArgs[i10];
                        int mapDecSetBitToInternalBit = mapDecSetBitToInternalBit(i11);
                        if (mapDecSetBitToInternalBit == -1) {
                            Log.w(EmulatorDebug.LOG_TAG, "Ignoring request to save/recall decset bit=" + i11);
                        } else if (i == 115) {
                            this.mSavedDecSetFlags |= mapDecSetBitToInternalBit;
                        } else {
                            doDecSetOrReset((mapDecSetBitToInternalBit & this.mSavedDecSetFlags) != 0, i11);
                        }
                    }
                } else {
                    parseArg(i);
                }
            } else if (getArg0(-1) != 6) {
                finishSequence();
            } else {
                this.mSession.write(String.format(Locale.US, "\u001b[?%d;%d;1R", new Object[]{Integer.valueOf(this.mCursorRow + 1), Integer.valueOf(this.mCursorCol + 1)}));
            }
        } else {
            continueSequence(14);
        }
    }

    public void doDecSetOrReset(boolean z, int i) {
        int mapDecSetBitToInternalBit = mapDecSetBitToInternalBit(i);
        if (mapDecSetBitToInternalBit != -1) {
            setDecsetinternalBit(mapDecSetBitToInternalBit, z);
        }
        boolean z2 = false;
        switch (i) {
            case 1:
            case 12:
            case 25:
            case 40:
            case 45:
            case 66:
            case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW:
            case 1034:
            case 2004:
                return;
            case 47:
                break;
            case 69:
                if (!z) {
                    this.mLeftMargin = 0;
                    this.mRightMargin = this.mColumns;
                    return;
                }
                return;
            default:
                switch (i) {
                    case 3:
                        this.mTopMargin = 0;
                        this.mLeftMargin = 0;
                        this.mBottomMargin = this.mRows;
                        this.mRightMargin = this.mColumns;
                        setDecsetinternalBit(2048, false);
                        blockClear(0, 0, this.mColumns, this.mRows);
                        setCursorRowCol(0, 0);
                        return;
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                        return;
                    case 6:
                        if (z) {
                            setCursorPosition(0, 0);
                            return;
                        }
                        return;
                    default:
                        switch (i) {
                            case 1000:
                            case 1001:
                            case 1002:
                            case PointerIconCompat.TYPE_HELP:
                            case PointerIconCompat.TYPE_WAIT:
                            case 1005:
                            case PointerIconCompat.TYPE_CELL:
                                return;
                            default:
                                switch (i) {
                                    case 1047:
                                    case 1049:
                                        break;
                                    case 1048:
                                        if (z) {
                                            saveCursor();
                                            return;
                                        } else {
                                            restoreCursor();
                                            return;
                                        }
                                    default:
                                        unknownParameter(i);
                                        return;
                                }
                        }
                }
        }
        TerminalBuffer terminalBuffer = z ? this.mAltBuffer : this.mMainBuffer;
        if (terminalBuffer != this.mScreen) {
            if (!(terminalBuffer.mColumns == this.mColumns && terminalBuffer.mScreenRows == this.mRows)) {
                z2 = true;
            }
            if (z) {
                saveCursor();
            }
            this.mScreen = terminalBuffer;
            if (!z) {
                int i2 = this.mSavedStateMain.mSavedCursorCol;
                int i3 = this.mSavedStateMain.mSavedCursorRow;
                restoreCursor();
                if (z2) {
                    this.mCursorCol = i2;
                    this.mCursorRow = i3;
                }
            }
            if (z2) {
                resizeScreen();
            }
            if (terminalBuffer == this.mAltBuffer) {
                terminalBuffer.blockSet(0, 0, this.mColumns, this.mRows, 32, getStyle());
            }
        }
    }

    private void doCsiBiggerThan(int i) {
        if (i == 99) {
            this.mSession.write("\u001b[>41;320;0c");
        } else if (i != 109) {
            parseArg(i);
        } else {
            Log.e(EmulatorDebug.LOG_TAG, "(ignored) CSI > MODIFY RESOURCE: " + getArg0(-1) + " to " + getArg1(-1));
        }
    }

    private void startEscapeSequence() {
        this.mEscapeState = 1;
        this.mArgIndex = 0;
        Arrays.fill(this.mArgs, -1);
    }

    private void doLinefeed() {
        boolean z = this.mCursorRow >= this.mBottomMargin;
        int i = this.mCursorRow;
        int i2 = i + 1;
        if (!z) {
            if (i2 == this.mBottomMargin) {
                scrollDownOneLine();
                i2 = this.mBottomMargin - 1;
            }
            setCursorRow(i2);
        } else if (i != this.mRows - 1) {
            setCursorRow(i2);
        }
    }

    private void continueSequence(int i) {
        this.mEscapeState = i;
        this.mContinueSequence = true;
    }

    private void doEscPound(int i) {
        if (i != 56) {
            unknownSequence(i);
        } else {
            this.mScreen.blockSet(0, 0, this.mColumns, this.mRows, 69, getStyle());
        }
    }

    private void doEsc(int i) {
        int i2 = i;
        if (i2 == 35) {
            continueSequence(2);
        } else if (i2 == 48) {
        } else {
            if (i2 != 72) {
                int i3 = 0;
                if (i2 == 80) {
                    this.mOSCOrDeviceControlArgs.setLength(0);
                    continueSequence(13);
                } else if (i2 == 91) {
                    continueSequence(6);
                } else if (i2 == 93) {
                    this.mOSCOrDeviceControlArgs.setLength(0);
                    continueSequence(10);
                } else if (i2 == 99) {
                    reset();
                    blockClear(0, 0, this.mColumns, this.mRows);
                    setCursorPosition(0, 0);
                } else if (i2 == 40) {
                    continueSequence(3);
                } else if (i2 == 41) {
                    continueSequence(4);
                } else if (i2 == 61) {
                    setDecsetinternalBit(32, true);
                } else if (i2 == 62) {
                    setDecsetinternalBit(32, false);
                } else if (i2 == 77) {
                    int i4 = this.mCursorRow;
                    int i5 = this.mTopMargin;
                    if (i4 <= i5) {
                        this.mScreen.blockCopy(0, i5, this.mColumns, this.mBottomMargin - (i5 + 1), 0, i5 + 1);
                        blockClear(0, this.mTopMargin, this.mColumns);
                        return;
                    }
                    this.mCursorRow = i4 - 1;
                } else if (i2 != 78) {
                    switch (i2) {
                        case 54:
                            int i6 = this.mCursorCol;
                            int i7 = this.mLeftMargin;
                            if (i6 > i7) {
                                this.mCursorCol = i6 - 1;
                                return;
                            }
                            int i8 = this.mBottomMargin;
                            int i9 = this.mTopMargin;
                            int i10 = i8 - i9;
                            this.mScreen.blockCopy(i7, i9, (this.mRightMargin - i7) - 1, i10, i7 + 1, i9);
                            this.mScreen.blockSet(this.mLeftMargin, this.mTopMargin, 1, i10, 32, TextStyle.encode(this.mForeColor, this.mBackColor, 0));
                            return;
                        case 55:
                            saveCursor();
                            return;
                        case 56:
                            restoreCursor();
                            return;
                        case 57:
                            int i11 = this.mCursorCol;
                            int i12 = this.mRightMargin;
                            if (i11 < i12 - 1) {
                                this.mCursorCol = i11 + 1;
                                return;
                            }
                            int i13 = this.mBottomMargin;
                            int i14 = this.mTopMargin;
                            int i15 = i13 - i14;
                            TerminalBuffer terminalBuffer = this.mScreen;
                            int i16 = this.mLeftMargin;
                            terminalBuffer.blockCopy(i16 + 1, i14, (i12 - i16) - 1, i15, i16, i14);
                            this.mScreen.blockSet(this.mRightMargin - 1, this.mTopMargin, 1, i15, 32, TextStyle.encode(this.mForeColor, this.mBackColor, 0));
                            return;
                        default:
                            switch (i2) {
                                case 68:
                                    doLinefeed();
                                    return;
                                case 69:
                                    if (isDecsetInternalBitSet(4)) {
                                        i3 = this.mLeftMargin;
                                    }
                                    setCursorCol(i3);
                                    doLinefeed();
                                    return;
                                case 70:
                                    setCursorRowCol(0, this.mBottomMargin - 1);
                                    return;
                                default:
                                    unknownSequence(i);
                                    return;
                            }
                    }
                }
            } else {
                this.mTabStop[this.mCursorCol] = true;
            }
        }
    }

    private void saveCursor() {
        SavedScreenState savedScreenState = this.mScreen == this.mMainBuffer ? this.mSavedStateMain : this.mSavedStateAlt;
        savedScreenState.mSavedCursorRow = this.mCursorRow;
        savedScreenState.mSavedCursorCol = this.mCursorCol;
        savedScreenState.mSavedEffect = this.mEffect;
        savedScreenState.mSavedForeColor = this.mForeColor;
        savedScreenState.mSavedBackColor = this.mBackColor;
        savedScreenState.mSavedDecFlags = this.mCurrentDecSetFlags;
        savedScreenState.mUseLineDrawingG0 = this.mUseLineDrawingG0;
        savedScreenState.mUseLineDrawingG1 = this.mUseLineDrawingG1;
        savedScreenState.mUseLineDrawingUsesG0 = this.mUseLineDrawingUsesG0;
    }

    private void restoreCursor() {
        SavedScreenState savedScreenState = this.mScreen == this.mMainBuffer ? this.mSavedStateMain : this.mSavedStateAlt;
        setCursorRowCol(savedScreenState.mSavedCursorRow, savedScreenState.mSavedCursorCol);
        this.mEffect = savedScreenState.mSavedEffect;
        this.mForeColor = savedScreenState.mSavedForeColor;
        this.mBackColor = savedScreenState.mSavedBackColor;
        this.mCurrentDecSetFlags = (this.mCurrentDecSetFlags & -13) | (savedScreenState.mSavedDecFlags & 12);
        this.mUseLineDrawingG0 = savedScreenState.mUseLineDrawingG0;
        this.mUseLineDrawingG1 = savedScreenState.mUseLineDrawingG1;
        this.mUseLineDrawingUsesG0 = savedScreenState.mUseLineDrawingUsesG0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02ef, code lost:
        setCursorPosition(getArg1(1) - 1, getArg0(1) - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x033c, code lost:
        setCursorCol(java.lang.Math.min(r9.mRightMargin - 1, r9.mCursorCol + getArg0(1)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doCsi(int r10) {
        /*
            r9 = this;
            r0 = 36
            if (r10 == r0) goto L_0x046b
            r0 = 39
            if (r10 == r0) goto L_0x0465
            r0 = 42
            if (r10 == r0) goto L_0x045f
            r0 = 80
            r1 = 0
            r2 = 1
            if (r10 == r0) goto L_0x0438
            r0 = 88
            if (r10 == r0) goto L_0x0417
            r0 = 90
            if (r10 == r0) goto L_0x03f5
            r0 = 83
            if (r10 == r0) goto L_0x03e9
            r0 = 84
            if (r10 == r0) goto L_0x03bb
            switch(r10) {
                case 32: goto L_0x03b4;
                case 33: goto L_0x03ad;
                case 34: goto L_0x03a6;
                default: goto L_0x0025;
            }
        L_0x0025:
            r0 = 12
            r3 = 2
            switch(r10) {
                case 62: goto L_0x03a1;
                case 63: goto L_0x039b;
                case 64: goto L_0x0374;
                case 65: goto L_0x0362;
                case 66: goto L_0x034f;
                case 67: goto L_0x033c;
                case 68: goto L_0x032a;
                case 69: goto L_0x031e;
                case 70: goto L_0x0312;
                case 71: goto L_0x02fe;
                case 72: goto L_0x02ef;
                case 73: goto L_0x02e2;
                case 74: goto L_0x02a1;
                case 75: goto L_0x0274;
                case 76: goto L_0x024f;
                case 77: goto L_0x0227;
                default: goto L_0x002b;
            }
        L_0x002b:
            switch(r10) {
                case 96: goto L_0x021d;
                case 97: goto L_0x033c;
                case 98: goto L_0x0208;
                case 99: goto L_0x01f9;
                case 100: goto L_0x01e5;
                case 101: goto L_0x01d7;
                case 102: goto L_0x02ef;
                case 103: goto L_0x01b8;
                case 104: goto L_0x01b3;
                default: goto L_0x002e;
            }
        L_0x002e:
            switch(r10) {
                case 108: goto L_0x01ae;
                case 109: goto L_0x01a9;
                case 110: goto L_0x016c;
                default: goto L_0x0031;
            }
        L_0x0031:
            switch(r10) {
                case 114: goto L_0x0140;
                case 115: goto L_0x010b;
                case 116: goto L_0x003e;
                case 117: goto L_0x0039;
                default: goto L_0x0034;
            }
        L_0x0034:
            r9.parseArg(r10)
            goto L_0x0470
        L_0x0039:
            r9.restoreCursor()
            goto L_0x0470
        L_0x003e:
            int r10 = r9.getArg0(r1)
            r4 = 11
            if (r10 == r4) goto L_0x0102
            r4 = 13
            if (r10 == r4) goto L_0x00f9
            r4 = 14
            if (r10 == r4) goto L_0x00d4
            switch(r10) {
                case 18: goto L_0x00b3;
                case 19: goto L_0x0092;
                case 20: goto L_0x0089;
                case 21: goto L_0x0080;
                case 22: goto L_0x0068;
                case 23: goto L_0x0053;
                default: goto L_0x0051;
            }
        L_0x0051:
            goto L_0x0470
        L_0x0053:
            java.util.Stack<java.lang.String> r10 = r9.mTitleStack
            boolean r10 = r10.isEmpty()
            if (r10 != 0) goto L_0x0470
            java.util.Stack<java.lang.String> r10 = r9.mTitleStack
            java.lang.Object r10 = r10.pop()
            java.lang.String r10 = (java.lang.String) r10
            r9.setTitle(r10)
            goto L_0x0470
        L_0x0068:
            java.util.Stack<java.lang.String> r10 = r9.mTitleStack
            java.lang.String r0 = r9.mTitle
            r10.push(r0)
            java.util.Stack<java.lang.String> r10 = r9.mTitleStack
            int r10 = r10.size()
            r0 = 20
            if (r10 <= r0) goto L_0x0470
            java.util.Stack<java.lang.String> r10 = r9.mTitleStack
            r10.remove(r1)
            goto L_0x0470
        L_0x0080:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r10 = r9.mSession
            java.lang.String r0 = "\u001b]l\u001b\\"
            r10.write(r0)
            goto L_0x0470
        L_0x0089:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r10 = r9.mSession
            java.lang.String r0 = "\u001b]LIconLabel\u001b\\"
            r10.write(r0)
            goto L_0x0470
        L_0x0092:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r10 = r9.mSession
            java.util.Locale r0 = java.util.Locale.US
            java.lang.Object[] r3 = new java.lang.Object[r3]
            int r4 = r9.mRows
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            int r1 = r9.mColumns
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3[r2] = r1
            java.lang.String r1 = "\u001b[9;%d;%dt"
            java.lang.String r0 = java.lang.String.format(r0, r1, r3)
            r10.write(r0)
            goto L_0x0470
        L_0x00b3:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r10 = r9.mSession
            java.util.Locale r0 = java.util.Locale.US
            java.lang.Object[] r3 = new java.lang.Object[r3]
            int r4 = r9.mRows
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            int r1 = r9.mColumns
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3[r2] = r1
            java.lang.String r1 = "\u001b[8;%d;%dt"
            java.lang.String r0 = java.lang.String.format(r0, r1, r3)
            r10.write(r0)
            goto L_0x0470
        L_0x00d4:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r10 = r9.mSession
            java.util.Locale r4 = java.util.Locale.US
            java.lang.Object[] r3 = new java.lang.Object[r3]
            int r5 = r9.mRows
            int r5 = r5 * 12
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r3[r1] = r5
            int r1 = r9.mColumns
            int r1 = r1 * 12
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            r3[r2] = r0
            java.lang.String r0 = "\u001b[4;%d;%dt"
            java.lang.String r0 = java.lang.String.format(r4, r0, r3)
            r10.write(r0)
            goto L_0x0470
        L_0x00f9:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r10 = r9.mSession
            java.lang.String r0 = "\u001b[3;0;0t"
            r10.write(r0)
            goto L_0x0470
        L_0x0102:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r10 = r9.mSession
            java.lang.String r0 = "\u001b[1t"
            r10.write(r0)
            goto L_0x0470
        L_0x010b:
            r10 = 2048(0x800, float:2.87E-42)
            boolean r10 = r9.isDecsetInternalBitSet(r10)
            if (r10 == 0) goto L_0x013b
            int r10 = r9.getArg0(r2)
            int r10 = r10 - r2
            int r0 = r9.mColumns
            int r0 = r0 - r3
            int r10 = java.lang.Math.min(r10, r0)
            r9.mLeftMargin = r10
            int r10 = r9.mLeftMargin
            int r10 = r10 + r2
            int r0 = r9.mColumns
            int r0 = r9.getArg1(r0)
            int r2 = r9.mColumns
            int r0 = java.lang.Math.min(r0, r2)
            int r10 = java.lang.Math.max(r10, r0)
            r9.mRightMargin = r10
            r9.setCursorPosition(r1, r1)
            goto L_0x0470
        L_0x013b:
            r9.saveCursor()
            goto L_0x0470
        L_0x0140:
            int r10 = r9.getArg0(r2)
            int r10 = r10 - r2
            int r0 = r9.mRows
            int r0 = r0 - r3
            int r10 = java.lang.Math.min(r10, r0)
            int r10 = java.lang.Math.max(r1, r10)
            r9.mTopMargin = r10
            int r10 = r9.mTopMargin
            int r10 = r10 + r3
            int r0 = r9.mRows
            int r0 = r9.getArg1(r0)
            int r2 = r9.mRows
            int r0 = java.lang.Math.min(r0, r2)
            int r10 = java.lang.Math.max(r10, r0)
            r9.mBottomMargin = r10
            r9.setCursorPosition(r1, r1)
            goto L_0x0470
        L_0x016c:
            int r10 = r9.getArg0(r1)
            r0 = 5
            if (r10 == r0) goto L_0x019b
            r0 = 6
            if (r10 == r0) goto L_0x0178
            goto L_0x0470
        L_0x0178:
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r10 = r9.mSession
            java.util.Locale r0 = java.util.Locale.US
            java.lang.Object[] r3 = new java.lang.Object[r3]
            int r4 = r9.mCursorRow
            int r4 = r4 + r2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            int r1 = r9.mCursorCol
            int r1 = r1 + r2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3[r2] = r1
            java.lang.String r1 = "\u001b[%d;%dR"
            java.lang.String r0 = java.lang.String.format(r0, r1, r3)
            r10.write(r0)
            goto L_0x0470
        L_0x019b:
            r10 = 4
            byte[] r10 = new byte[r10]
            r10 = {27, 91, 48, 110} // fill-array
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r0 = r9.mSession
            int r2 = r10.length
            r0.write(r10, r1, r2)
            goto L_0x0470
        L_0x01a9:
            r9.selectGraphicRendition()
            goto L_0x0470
        L_0x01ae:
            r9.doSetMode(r1)
            goto L_0x0470
        L_0x01b3:
            r9.doSetMode(r2)
            goto L_0x0470
        L_0x01b8:
            int r10 = r9.getArg0(r1)
            if (r10 == 0) goto L_0x01cf
            r0 = 3
            if (r10 == r0) goto L_0x01c3
            goto L_0x0470
        L_0x01c3:
            r10 = 0
        L_0x01c4:
            int r0 = r9.mColumns
            if (r10 >= r0) goto L_0x0470
            boolean[] r0 = r9.mTabStop
            r0[r10] = r1
            int r10 = r10 + 1
            goto L_0x01c4
        L_0x01cf:
            boolean[] r10 = r9.mTabStop
            int r0 = r9.mCursorCol
            r10[r0] = r1
            goto L_0x0470
        L_0x01d7:
            int r10 = r9.mCursorCol
            int r0 = r9.mCursorRow
            int r1 = r9.getArg0(r2)
            int r0 = r0 + r1
            r9.setCursorPosition(r10, r0)
            goto L_0x0470
        L_0x01e5:
            int r10 = r9.getArg0(r2)
            int r10 = java.lang.Math.max(r2, r10)
            int r0 = r9.mRows
            int r10 = java.lang.Math.min(r10, r0)
            int r10 = r10 - r2
            r9.setCursorRow(r10)
            goto L_0x0470
        L_0x01f9:
            int r10 = r9.getArg0(r1)
            if (r10 != 0) goto L_0x0470
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r10 = r9.mSession
            java.lang.String r0 = "\u001b[?64;1;2;6;9;15;18;21;22c"
            r10.write(r0)
            goto L_0x0470
        L_0x0208:
            int r10 = r9.mLastEmittedCodePoint
            r0 = -1
            if (r10 != r0) goto L_0x020f
            goto L_0x0470
        L_0x020f:
            int r10 = r9.getArg0(r2)
        L_0x0213:
            if (r1 >= r10) goto L_0x0470
            int r0 = r9.mLastEmittedCodePoint
            r9.emitCodePoint(r0)
            int r1 = r1 + 1
            goto L_0x0213
        L_0x021d:
            int r10 = r9.getArg0(r2)
            int r10 = r10 - r2
            r9.setCursorColRespectingOriginMode(r10)
            goto L_0x0470
        L_0x0227:
            r9.mAboutToAutoWrap = r1
            int r10 = r9.mBottomMargin
            int r0 = r9.mCursorRow
            int r10 = r10 - r0
            int r0 = r9.getArg0(r2)
            int r0 = java.lang.Math.min(r0, r10)
            int r10 = r10 - r0
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r2 = r9.mScreen
            r3 = 0
            int r8 = r9.mCursorRow
            int r4 = r8 + r0
            int r5 = r9.mColumns
            r7 = 0
            r6 = r10
            r2.blockCopy(r3, r4, r5, r6, r7, r8)
            int r2 = r9.mCursorRow
            int r2 = r2 + r10
            int r10 = r9.mColumns
            r9.blockClear(r1, r2, r10, r0)
            goto L_0x0470
        L_0x024f:
            int r10 = r9.mBottomMargin
            int r0 = r9.mCursorRow
            int r10 = r10 - r0
            int r0 = r9.getArg0(r2)
            int r0 = java.lang.Math.min(r0, r10)
            int r6 = r10 - r0
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r2 = r9.mScreen
            r3 = 0
            int r4 = r9.mCursorRow
            int r5 = r9.mColumns
            r7 = 0
            int r8 = r4 + r0
            r2.blockCopy(r3, r4, r5, r6, r7, r8)
            int r10 = r9.mCursorRow
            int r2 = r9.mColumns
            r9.blockClear(r1, r10, r2, r0)
            goto L_0x0470
        L_0x0274:
            int r0 = r9.getArg0(r1)
            if (r0 == 0) goto L_0x0293
            if (r0 == r2) goto L_0x028a
            if (r0 == r3) goto L_0x0282
            r9.unknownSequence(r10)
            return
        L_0x0282:
            int r10 = r9.mCursorRow
            int r0 = r9.mColumns
            r9.blockClear(r1, r10, r0)
            goto L_0x029d
        L_0x028a:
            int r10 = r9.mCursorRow
            int r0 = r9.mCursorCol
            int r0 = r0 + r2
            r9.blockClear(r1, r10, r0)
            goto L_0x029d
        L_0x0293:
            int r10 = r9.mCursorCol
            int r0 = r9.mCursorRow
            int r2 = r9.mColumns
            int r2 = r2 - r10
            r9.blockClear(r10, r0, r2)
        L_0x029d:
            r9.mAboutToAutoWrap = r1
            goto L_0x0470
        L_0x02a1:
            int r0 = r9.getArg0(r1)
            if (r0 == 0) goto L_0x02c7
            if (r0 == r2) goto L_0x02b7
            if (r0 == r3) goto L_0x02af
            r9.unknownSequence(r10)
            return
        L_0x02af:
            int r10 = r9.mColumns
            int r0 = r9.mRows
            r9.blockClear(r1, r1, r10, r0)
            goto L_0x02de
        L_0x02b7:
            int r10 = r9.mColumns
            int r0 = r9.mCursorRow
            r9.blockClear(r1, r1, r10, r0)
            int r10 = r9.mCursorRow
            int r0 = r9.mCursorCol
            int r0 = r0 + r2
            r9.blockClear(r1, r10, r0)
            goto L_0x02de
        L_0x02c7:
            int r10 = r9.mCursorCol
            int r0 = r9.mCursorRow
            int r3 = r9.mColumns
            int r3 = r3 - r10
            r9.blockClear(r10, r0, r3)
            int r10 = r9.mCursorRow
            int r0 = r10 + 1
            int r3 = r9.mColumns
            int r4 = r9.mRows
            int r10 = r10 + r2
            int r4 = r4 - r10
            r9.blockClear(r1, r0, r3, r4)
        L_0x02de:
            r9.mAboutToAutoWrap = r1
            goto L_0x0470
        L_0x02e2:
            int r10 = r9.getArg0(r2)
            int r10 = r9.nextTabStop(r10)
            r9.setCursorCol(r10)
            goto L_0x0470
        L_0x02ef:
            int r10 = r9.getArg1(r2)
            int r10 = r10 - r2
            int r0 = r9.getArg0(r2)
            int r0 = r0 - r2
            r9.setCursorPosition(r10, r0)
            goto L_0x0470
        L_0x02fe:
            int r10 = r9.getArg0(r2)
            int r10 = java.lang.Math.max(r2, r10)
            int r0 = r9.mColumns
            int r10 = java.lang.Math.min(r10, r0)
            int r10 = r10 - r2
            r9.setCursorCol(r10)
            goto L_0x0470
        L_0x0312:
            int r10 = r9.mCursorRow
            int r0 = r9.getArg0(r2)
            int r10 = r10 - r0
            r9.setCursorPosition(r1, r10)
            goto L_0x0470
        L_0x031e:
            int r10 = r9.mCursorRow
            int r0 = r9.getArg0(r2)
            int r10 = r10 + r0
            r9.setCursorPosition(r1, r10)
            goto L_0x0470
        L_0x032a:
            int r10 = r9.mLeftMargin
            int r0 = r9.mCursorCol
            int r1 = r9.getArg0(r2)
            int r0 = r0 - r1
            int r10 = java.lang.Math.max(r10, r0)
            r9.setCursorCol(r10)
            goto L_0x0470
        L_0x033c:
            int r10 = r9.mRightMargin
            int r10 = r10 - r2
            int r0 = r9.mCursorCol
            int r1 = r9.getArg0(r2)
            int r0 = r0 + r1
            int r10 = java.lang.Math.min(r10, r0)
            r9.setCursorCol(r10)
            goto L_0x0470
        L_0x034f:
            int r10 = r9.mBottomMargin
            int r10 = r10 - r2
            int r0 = r9.mCursorRow
            int r1 = r9.getArg0(r2)
            int r0 = r0 + r1
            int r10 = java.lang.Math.min(r10, r0)
            r9.setCursorRow(r10)
            goto L_0x0470
        L_0x0362:
            int r10 = r9.mTopMargin
            int r0 = r9.mCursorRow
            int r1 = r9.getArg0(r2)
            int r0 = r0 - r1
            int r10 = java.lang.Math.max(r10, r0)
            r9.setCursorRow(r10)
            goto L_0x0470
        L_0x0374:
            r9.mAboutToAutoWrap = r1
            int r10 = r9.mColumns
            int r0 = r9.mCursorCol
            int r10 = r10 - r0
            int r0 = r9.getArg0(r2)
            int r0 = java.lang.Math.min(r0, r10)
            int r4 = r10 - r0
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r1 = r9.mScreen
            int r2 = r9.mCursorCol
            int r7 = r9.mCursorRow
            r5 = 1
            int r6 = r2 + r0
            r3 = r7
            r1.blockCopy(r2, r3, r4, r5, r6, r7)
            int r10 = r9.mCursorCol
            int r1 = r9.mCursorRow
            r9.blockClear(r10, r1, r0)
            goto L_0x0470
        L_0x039b:
            r10 = 7
            r9.continueSequence(r10)
            goto L_0x0470
        L_0x03a1:
            r9.continueSequence(r0)
            goto L_0x0470
        L_0x03a6:
            r10 = 17
            r9.continueSequence(r10)
            goto L_0x0470
        L_0x03ad:
            r10 = 19
            r9.continueSequence(r10)
            goto L_0x0470
        L_0x03b4:
            r10 = 15
            r9.continueSequence(r10)
            goto L_0x0470
        L_0x03bb:
            int r0 = r9.mArgIndex
            if (r0 != 0) goto L_0x03e4
            int r10 = r9.getArg0(r2)
            int r0 = r9.mBottomMargin
            int r2 = r9.mTopMargin
            int r0 = r0 - r2
            int r10 = java.lang.Math.min(r0, r10)
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r2 = r9.mScreen
            r3 = 0
            int r4 = r9.mTopMargin
            int r5 = r9.mColumns
            int r6 = r0 - r10
            r7 = 0
            int r8 = r4 + r10
            r2.blockCopy(r3, r4, r5, r6, r7, r8)
            int r0 = r9.mTopMargin
            int r2 = r9.mColumns
            r9.blockClear(r1, r0, r2, r10)
            goto L_0x0470
        L_0x03e4:
            r9.unimplementedSequence(r10)
            goto L_0x0470
        L_0x03e9:
            int r10 = r9.getArg0(r2)
        L_0x03ed:
            if (r1 >= r10) goto L_0x0470
            r9.scrollDownOneLine()
            int r1 = r1 + 1
            goto L_0x03ed
        L_0x03f5:
            int r10 = r9.getArg0(r2)
            int r0 = r9.mLeftMargin
            int r1 = r9.mCursorCol
            int r1 = r1 - r2
        L_0x03fe:
            if (r1 < 0) goto L_0x0414
            boolean[] r2 = r9.mTabStop
            boolean r2 = r2[r1]
            if (r2 == 0) goto L_0x0411
            int r10 = r10 + -1
            if (r10 != 0) goto L_0x0411
            int r10 = r9.mLeftMargin
            int r0 = java.lang.Math.max(r1, r10)
            goto L_0x0414
        L_0x0411:
            int r1 = r1 + -1
            goto L_0x03fe
        L_0x0414:
            r9.mCursorCol = r0
            goto L_0x0470
        L_0x0417:
            r9.mAboutToAutoWrap = r1
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r1 = r9.mScreen
            int r10 = r9.mCursorCol
            int r3 = r9.mCursorRow
            int r0 = r9.getArg0(r2)
            int r2 = r9.mColumns
            int r4 = r9.mCursorCol
            int r2 = r2 - r4
            int r4 = java.lang.Math.min(r0, r2)
            r5 = 1
            r6 = 32
            long r7 = r9.getStyle()
            r2 = r10
            r1.blockSet(r2, r3, r4, r5, r6, r7)
            goto L_0x0470
        L_0x0438:
            r9.mAboutToAutoWrap = r1
            int r10 = r9.mColumns
            int r0 = r9.mCursorCol
            int r10 = r10 - r0
            int r0 = r9.getArg0(r2)
            int r0 = java.lang.Math.min(r0, r10)
            int r10 = r10 - r0
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r1 = r9.mScreen
            int r6 = r9.mCursorCol
            int r2 = r6 + r0
            int r7 = r9.mCursorRow
            r5 = 1
            r3 = r7
            r4 = r10
            r1.blockCopy(r2, r3, r4, r5, r6, r7)
            int r1 = r9.mCursorCol
            int r1 = r1 + r10
            int r10 = r9.mCursorRow
            r9.blockClear(r1, r10, r0)
            goto L_0x0470
        L_0x045f:
            r10 = 16
            r9.continueSequence(r10)
            goto L_0x0470
        L_0x0465:
            r10 = 18
            r9.continueSequence(r10)
            goto L_0x0470
        L_0x046b:
            r10 = 8
            r9.continueSequence(r10)
        L_0x0470:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalEmulator.doCsi(int):void");
    }

    private void selectGraphicRendition() {
        int i = this.mArgIndex;
        int[] iArr = this.mArgs;
        if (i >= iArr.length) {
            this.mArgIndex = iArr.length - 1;
        }
        int i2 = 0;
        while (true) {
            int i3 = this.mArgIndex;
            if (i2 <= i3) {
                int i4 = this.mArgs[i2];
                if (i4 < 0) {
                    if (i3 > 0) {
                        i2++;
                    } else {
                        i4 = 0;
                    }
                }
                if (i4 == 0) {
                    this.mForeColor = 256;
                    this.mBackColor = 257;
                    this.mEffect = 0;
                } else if (i4 == 1) {
                    this.mEffect |= 1;
                } else if (i4 == 2) {
                    this.mEffect |= 256;
                } else if (i4 == 3) {
                    this.mEffect |= 2;
                } else if (i4 == 4) {
                    this.mEffect |= 4;
                } else if (i4 == 5) {
                    this.mEffect |= 8;
                } else if (i4 == 7) {
                    this.mEffect |= 16;
                } else if (i4 == 8) {
                    this.mEffect |= 32;
                } else if (i4 == 9) {
                    this.mEffect |= 64;
                } else if (!(i4 == 10 || i4 == 11)) {
                    if (i4 == 22) {
                        this.mEffect &= -258;
                    } else if (i4 == 23) {
                        this.mEffect &= -3;
                    } else if (i4 == 24) {
                        this.mEffect &= -5;
                    } else if (i4 == 25) {
                        this.mEffect &= -9;
                    } else if (i4 == 27) {
                        this.mEffect &= -17;
                    } else if (i4 == 28) {
                        this.mEffect &= -33;
                    } else if (i4 == 29) {
                        this.mEffect &= -65;
                    } else if (i4 >= 30 && i4 <= 37) {
                        this.mForeColor = i4 - 30;
                    } else if (i4 == 38 || i4 == 48) {
                        int i5 = i2 + 2;
                        int i6 = this.mArgIndex;
                        if (i5 <= i6) {
                            int[] iArr2 = this.mArgs;
                            int i7 = iArr2[i2 + 1];
                            if (i7 == 2) {
                                int i8 = i2 + 4;
                                if (i8 > i6) {
                                    Log.w(EmulatorDebug.LOG_TAG, "Too few CSI" + i4 + ";2 RGB arguments");
                                } else {
                                    int i9 = iArr2[i5];
                                    int i10 = iArr2[i2 + 3];
                                    int i11 = iArr2[i8];
                                    if (i9 < 0 || i10 < 0 || i11 < 0 || i9 > 255 || i10 > 255 || i11 > 255) {
                                        finishSequenceAndLogError("Invalid RGB: " + i9 + "," + i10 + "," + i11);
                                    } else {
                                        int i12 = (i10 << 8) | (i9 << 16) | ViewCompat.MEASURED_STATE_MASK | i11;
                                        if (i4 == 38) {
                                            this.mForeColor = i12;
                                        } else {
                                            this.mBackColor = i12;
                                        }
                                    }
                                    i2 = i8;
                                }
                            } else if (i7 == 5) {
                                int i13 = iArr2[i5];
                                if (i13 >= 0 && i13 < 259) {
                                    if (i4 == 38) {
                                        this.mForeColor = i13;
                                    } else {
                                        this.mBackColor = i13;
                                    }
                                }
                                i2 = i5;
                            } else {
                                finishSequenceAndLogError("Invalid ISO-8613-3 SGR first argument: " + i7);
                            }
                        }
                    } else if (i4 == 39) {
                        this.mForeColor = 256;
                    } else if (i4 >= 40 && i4 <= 47) {
                        this.mBackColor = i4 - 40;
                    } else if (i4 == 49) {
                        this.mBackColor = 257;
                    } else if (i4 >= 90 && i4 <= 97) {
                        this.mForeColor = (i4 - 90) + 8;
                    } else if (i4 >= 100 && i4 <= 107) {
                        this.mBackColor = (i4 - 100) + 8;
                    }
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private void doOsc(int i) {
        if (i == 7) {
            doOscSetTextParameters("\u0007");
        } else if (i != 27) {
            collectOSCArgs(i);
        } else {
            continueSequence(11);
        }
    }

    private void doOscEsc(int i) {
        if (i != 92) {
            collectOSCArgs(27);
            collectOSCArgs(i);
            continueSequence(10);
            return;
        }
        doOscSetTextParameters("\u001b\\");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0200, code lost:
        unknownSequence(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0203, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0206, code lost:
        if (r2 < 0) goto L_0x0209;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0209, code lost:
        if (r2 >= 0) goto L_0x021e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x020b, code lost:
        if (r11 < '0') goto L_0x021e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x020d, code lost:
        if (r11 > '9') goto L_0x021e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x020f, code lost:
        if (r6 >= 0) goto L_0x0213;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0211, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0213, code lost:
        r6 = r6 * 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0215, code lost:
        r6 = r6 + (r11 - '0');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0218, code lost:
        if (r7 == false) goto L_0x021b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x021b, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x021e, code lost:
        unknownSequence(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0221, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0222, code lost:
        setTitle(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        if (r6 == 0) goto L_0x0222;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
        if (r6 == 1) goto L_0x0222;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        if (r6 == 2) goto L_0x0222;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        r13 = 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        if (r6 == 4) goto L_0x01c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        if (r6 == 52) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        if (r6 == 104) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0055, code lost:
        if (r6 == 119) goto L_0x0225;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0057, code lost:
        switch(r6) {
            case 10: goto L_0x0072;
            case 11: goto L_0x0072;
            case 12: goto L_0x0072;
            default: goto L_0x005a;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005a, code lost:
        switch(r6) {
            case 110: goto L_0x0062;
            case 111: goto L_0x0062;
            case 112: goto L_0x0062;
            default: goto L_0x005d;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005d, code lost:
        unknownParameter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0062, code lost:
        r0.mColors.reset((r6 - 110) + 256);
        r0.mSession.onColorsChanged();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0072, code lost:
        r8 = (r6 - 10) + 256;
        r3 = 0;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x014e, code lost:
        if (r5.isEmpty() == false) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0150, code lost:
        r0.mColors.reset();
        r0.mSession.onColorsChanged();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x015c, code lost:
        r1 = 0;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0162, code lost:
        if (r1 != r5.length()) goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0164, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0166, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0167, code lost:
        if (r3 != false) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x016f, code lost:
        if (r5.charAt(r1) != ';') goto L_0x018a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r0.mColors.reset(java.lang.Integer.parseInt(r5.substring(r2, r1)));
        r0.mSession.onColorsChanged();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0183, code lost:
        if (r3 == false) goto L_0x0187;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0187, code lost:
        r1 = r1 + 1;
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        r0.mSession.clipboardText(new java.lang.String(android.util.Base64.decode(r5.substring(r5.indexOf(";") + 1), 0), java.nio.charset.StandardCharsets.UTF_8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01aa, code lost:
        android.util.Log.e(com.thecrackertechnology.dragonterminal.backend.EmulatorDebug.LOG_TAG, "OSC Manipulate selection, invalid string '" + r5 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01c5, code lost:
        r1 = 0;
        r2 = -1;
        r6 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01cd, code lost:
        if (r1 != r5.length()) goto L_0x01d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01cf, code lost:
        r7 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01d1, code lost:
        r7 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01d2, code lost:
        if (r7 == false) goto L_0x01d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01d4, code lost:
        r9 = ';';
        r11 = ';';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01d9, code lost:
        r11 = r5.charAt(r1);
        r9 = ';';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01df, code lost:
        if (r11 != r9) goto L_0x0204;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01e1, code lost:
        if (r2 >= 0) goto L_0x01e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01e3, code lost:
        r2 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01e8, code lost:
        if (r6 < 0) goto L_0x0200;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01ec, code lost:
        if (r6 <= 255) goto L_0x01ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01ef, code lost:
        r0.mColors.tryParseColor(r6, r5.substring(r2, r1));
        r0.mSession.onColorsChanged();
        r2 = -1;
        r6 = -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doOscSetTextParameters(java.lang.String r20) {
        /*
            r19 = this;
            r0 = r19
            java.lang.String r1 = "/"
            java.lang.String r2 = "%04x"
            r3 = -1
            r5 = 0
            r6 = -1
        L_0x0009:
            java.lang.StringBuilder r7 = r0.mOSCOrDeviceControlArgs
            int r7 = r7.length()
            r8 = 57
            java.lang.String r9 = ""
            r10 = 48
            r11 = 59
            r12 = 1
            if (r5 >= r7) goto L_0x003e
            java.lang.StringBuilder r7 = r0.mOSCOrDeviceControlArgs
            char r7 = r7.charAt(r5)
            if (r7 != r11) goto L_0x002a
            java.lang.StringBuilder r7 = r0.mOSCOrDeviceControlArgs
            int r5 = r5 + r12
            java.lang.String r5 = r7.substring(r5)
            goto L_0x003f
        L_0x002a:
            if (r7 < r10) goto L_0x003a
            if (r7 > r8) goto L_0x003a
            if (r6 >= 0) goto L_0x0032
            r6 = 0
            goto L_0x0034
        L_0x0032:
            int r6 = r6 * 10
        L_0x0034:
            int r7 = r7 + -48
            int r6 = r6 + r7
            int r5 = r5 + 1
            goto L_0x0009
        L_0x003a:
            r0.unknownSequence(r7)
            return
        L_0x003e:
            r5 = r9
        L_0x003f:
            if (r6 == 0) goto L_0x0222
            if (r6 == r12) goto L_0x0222
            r7 = 2
            if (r6 == r7) goto L_0x0222
            r7 = 4
            r13 = 255(0xff, float:3.57E-43)
            if (r6 == r7) goto L_0x01c5
            r3 = 52
            if (r6 == r3) goto L_0x018c
            r3 = 104(0x68, float:1.46E-43)
            if (r6 == r3) goto L_0x014a
            r3 = 119(0x77, float:1.67E-43)
            if (r6 == r3) goto L_0x0225
            switch(r6) {
                case 10: goto L_0x0072;
                case 11: goto L_0x0072;
                case 12: goto L_0x0072;
                default: goto L_0x005a;
            }
        L_0x005a:
            switch(r6) {
                case 110: goto L_0x0062;
                case 111: goto L_0x0062;
                case 112: goto L_0x0062;
                default: goto L_0x005d;
            }
        L_0x005d:
            r0.unknownParameter(r6)
            goto L_0x0225
        L_0x0062:
            com.thecrackertechnology.dragonterminal.backend.TerminalColors r1 = r0.mColors
            int r6 = r6 + -110
            int r6 = r6 + 256
            r1.reset((int) r6)
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r1 = r0.mSession
            r1.onColorsChanged()
            goto L_0x0225
        L_0x0072:
            int r3 = r6 + -10
            int r3 = r3 + 256
            r8 = r3
            r3 = 0
            r7 = 0
        L_0x0079:
            int r9 = r5.length()
            if (r3 != r9) goto L_0x0081
            r9 = 1
            goto L_0x0082
        L_0x0081:
            r9 = 0
        L_0x0082:
            if (r9 != 0) goto L_0x0091
            char r10 = r5.charAt(r3)
            if (r10 != r11) goto L_0x008b
            goto L_0x0091
        L_0x008b:
            r4 = r20
            r18 = r6
            goto L_0x0141
        L_0x0091:
            java.lang.String r10 = r5.substring(r7, r3)     // Catch:{ NumberFormatException -> 0x008b }
            java.lang.String r14 = "?"
            boolean r14 = r14.equals(r10)     // Catch:{ NumberFormatException -> 0x008b }
            if (r14 == 0) goto L_0x0120
            com.thecrackertechnology.dragonterminal.backend.TerminalColors r10 = r0.mColors     // Catch:{ NumberFormatException -> 0x008b }
            int[] r10 = r10.mCurrentColors     // Catch:{ NumberFormatException -> 0x008b }
            r10 = r10[r8]     // Catch:{ NumberFormatException -> 0x008b }
            r14 = 16711680(0xff0000, float:2.3418052E-38)
            r14 = r14 & r10
            int r14 = r14 >> 16
            r15 = 65535(0xffff, float:9.1834E-41)
            int r14 = r14 * r15
            int r14 = r14 / r13
            r16 = 65280(0xff00, float:9.1477E-41)
            r16 = r10 & r16
            int r16 = r16 >> 8
            int r11 = r16 * r15
            int r11 = r11 / r13
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r10 = r10 * r15
            int r10 = r10 / r13
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r15 = r0.mSession     // Catch:{ NumberFormatException -> 0x008b }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x008b }
            r13.<init>()     // Catch:{ NumberFormatException -> 0x008b }
            java.lang.String r4 = "\u001b]"
            r13.append(r4)     // Catch:{ NumberFormatException -> 0x008b }
            r13.append(r6)     // Catch:{ NumberFormatException -> 0x008b }
            java.lang.String r4 = ";rgb:"
            r13.append(r4)     // Catch:{ NumberFormatException -> 0x008b }
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ NumberFormatException -> 0x008b }
            r18 = r6
            java.lang.Object[] r6 = new java.lang.Object[r12]     // Catch:{ NumberFormatException -> 0x011d }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ NumberFormatException -> 0x011d }
            r17 = 0
            r6[r17] = r14     // Catch:{ NumberFormatException -> 0x011d }
            java.lang.String r4 = java.lang.String.format(r4, r2, r6)     // Catch:{ NumberFormatException -> 0x011d }
            r13.append(r4)     // Catch:{ NumberFormatException -> 0x011d }
            r13.append(r1)     // Catch:{ NumberFormatException -> 0x011d }
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ NumberFormatException -> 0x011d }
            java.lang.Object[] r6 = new java.lang.Object[r12]     // Catch:{ NumberFormatException -> 0x011d }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ NumberFormatException -> 0x011d }
            r14 = 0
            r6[r14] = r11     // Catch:{ NumberFormatException -> 0x011d }
            java.lang.String r4 = java.lang.String.format(r4, r2, r6)     // Catch:{ NumberFormatException -> 0x011d }
            r13.append(r4)     // Catch:{ NumberFormatException -> 0x011d }
            r13.append(r1)     // Catch:{ NumberFormatException -> 0x011d }
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ NumberFormatException -> 0x011d }
            java.lang.Object[] r6 = new java.lang.Object[r12]     // Catch:{ NumberFormatException -> 0x011d }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ NumberFormatException -> 0x011d }
            r11 = 0
            r6[r11] = r10     // Catch:{ NumberFormatException -> 0x011d }
            java.lang.String r4 = java.lang.String.format(r4, r2, r6)     // Catch:{ NumberFormatException -> 0x011d }
            r13.append(r4)     // Catch:{ NumberFormatException -> 0x011d }
            r4 = r20
            r13.append(r4)     // Catch:{ NumberFormatException -> 0x0141 }
            java.lang.String r6 = r13.toString()     // Catch:{ NumberFormatException -> 0x0141 }
            r15.write(r6)     // Catch:{ NumberFormatException -> 0x0141 }
            goto L_0x012e
        L_0x011d:
            r4 = r20
            goto L_0x0141
        L_0x0120:
            r4 = r20
            r18 = r6
            com.thecrackertechnology.dragonterminal.backend.TerminalColors r6 = r0.mColors     // Catch:{ NumberFormatException -> 0x0141 }
            r6.tryParseColor(r8, r10)     // Catch:{ NumberFormatException -> 0x0141 }
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r6 = r0.mSession     // Catch:{ NumberFormatException -> 0x0141 }
            r6.onColorsChanged()     // Catch:{ NumberFormatException -> 0x0141 }
        L_0x012e:
            int r8 = r8 + 1
            if (r9 != 0) goto L_0x0225
            r6 = 258(0x102, float:3.62E-43)
            if (r8 > r6) goto L_0x0225
            int r3 = r3 + 1
            int r6 = r5.length()     // Catch:{ NumberFormatException -> 0x0141 }
            if (r3 < r6) goto L_0x0140
            goto L_0x0225
        L_0x0140:
            r7 = r3
        L_0x0141:
            int r3 = r3 + r12
            r6 = r18
            r11 = 59
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x0079
        L_0x014a:
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x015c
            com.thecrackertechnology.dragonterminal.backend.TerminalColors r1 = r0.mColors
            r1.reset()
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r1 = r0.mSession
            r1.onColorsChanged()
            goto L_0x0225
        L_0x015c:
            r1 = 0
            r2 = 0
        L_0x015e:
            int r3 = r5.length()
            if (r1 != r3) goto L_0x0166
            r3 = 1
            goto L_0x0167
        L_0x0166:
            r3 = 0
        L_0x0167:
            if (r3 != 0) goto L_0x0171
            char r4 = r5.charAt(r1)
            r6 = 59
            if (r4 != r6) goto L_0x018a
        L_0x0171:
            java.lang.String r4 = r5.substring(r2, r1)     // Catch:{ NumberFormatException -> 0x018a }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ NumberFormatException -> 0x018a }
            com.thecrackertechnology.dragonterminal.backend.TerminalColors r6 = r0.mColors     // Catch:{ NumberFormatException -> 0x018a }
            r6.reset((int) r4)     // Catch:{ NumberFormatException -> 0x018a }
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r4 = r0.mSession     // Catch:{ NumberFormatException -> 0x018a }
            r4.onColorsChanged()     // Catch:{ NumberFormatException -> 0x018a }
            if (r3 == 0) goto L_0x0187
            goto L_0x0225
        L_0x0187:
            int r1 = r1 + 1
            r2 = r1
        L_0x018a:
            int r1 = r1 + r12
            goto L_0x015e
        L_0x018c:
            java.lang.String r1 = ";"
            int r1 = r5.indexOf(r1)
            int r1 = r1 + r12
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x01aa }
            java.lang.String r1 = r5.substring(r1)     // Catch:{ Exception -> 0x01aa }
            r4 = 0
            byte[] r1 = android.util.Base64.decode(r1, r4)     // Catch:{ Exception -> 0x01aa }
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ Exception -> 0x01aa }
            r2.<init>(r1, r3)     // Catch:{ Exception -> 0x01aa }
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r1 = r0.mSession     // Catch:{ Exception -> 0x01aa }
            r1.clipboardText(r2)     // Catch:{ Exception -> 0x01aa }
            goto L_0x0225
        L_0x01aa:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "OSC Manipulate selection, invalid string '"
            r1.append(r2)
            r1.append(r5)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "NeoTerm-Emulator"
            android.util.Log.e(r2, r1)
            goto L_0x0225
        L_0x01c5:
            r4 = 0
            r1 = 0
            r2 = -1
            r6 = -1
        L_0x01c9:
            int r7 = r5.length()
            if (r1 != r7) goto L_0x01d1
            r7 = 1
            goto L_0x01d2
        L_0x01d1:
            r7 = 0
        L_0x01d2:
            if (r7 == 0) goto L_0x01d9
            r9 = 59
            r11 = 59
            goto L_0x01df
        L_0x01d9:
            char r11 = r5.charAt(r1)
            r9 = 59
        L_0x01df:
            if (r11 != r9) goto L_0x0204
            if (r2 >= 0) goto L_0x01e8
            int r2 = r1 + 1
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x0218
        L_0x01e8:
            if (r6 < 0) goto L_0x0200
            r13 = 255(0xff, float:3.57E-43)
            if (r6 <= r13) goto L_0x01ef
            goto L_0x0200
        L_0x01ef:
            com.thecrackertechnology.dragonterminal.backend.TerminalColors r11 = r0.mColors
            java.lang.String r2 = r5.substring(r2, r1)
            r11.tryParseColor(r6, r2)
            com.thecrackertechnology.dragonterminal.backend.TerminalOutput r2 = r0.mSession
            r2.onColorsChanged()
            r2 = -1
            r6 = -1
            goto L_0x0218
        L_0x0200:
            r0.unknownSequence(r11)
            return
        L_0x0204:
            r13 = 255(0xff, float:3.57E-43)
            if (r2 < 0) goto L_0x0209
            goto L_0x0218
        L_0x0209:
            if (r2 >= 0) goto L_0x021e
            if (r11 < r10) goto L_0x021e
            if (r11 > r8) goto L_0x021e
            if (r6 >= 0) goto L_0x0213
            r6 = 0
            goto L_0x0215
        L_0x0213:
            int r6 = r6 * 10
        L_0x0215:
            int r11 = r11 + -48
            int r6 = r6 + r11
        L_0x0218:
            if (r7 == 0) goto L_0x021b
            goto L_0x0225
        L_0x021b:
            int r1 = r1 + 1
            goto L_0x01c9
        L_0x021e:
            r0.unknownSequence(r11)
            return
        L_0x0222:
            r0.setTitle(r5)
        L_0x0225:
            r19.finishSequence()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalEmulator.doOscSetTextParameters(java.lang.String):void");
    }

    private void blockClear(int i, int i2, int i3) {
        blockClear(i, i2, i3, 1);
    }

    private void blockClear(int i, int i2, int i3, int i4) {
        this.mScreen.blockSet(i, i2, i3, i4, 32, getStyle());
    }

    private long getStyle() {
        return TextStyle.encode(this.mForeColor, this.mBackColor, this.mEffect);
    }

    private void doSetMode(boolean z) {
        int arg0 = getArg0(0);
        if (arg0 == 4) {
            this.mInsertMode = z;
        } else if (arg0 == 20) {
            unknownParameter(arg0);
        } else if (arg0 != 34) {
            unknownParameter(arg0);
        }
    }

    private void setCursorPosition(int i, int i2) {
        boolean isDecsetInternalBitSet = isDecsetInternalBitSet(4);
        int i3 = 0;
        int i4 = isDecsetInternalBitSet ? this.mTopMargin : 0;
        int i5 = isDecsetInternalBitSet ? this.mBottomMargin : this.mRows;
        if (isDecsetInternalBitSet) {
            i3 = this.mLeftMargin;
        }
        setCursorRowCol(Math.max(i4, Math.min(i2 + i4, i5 - 1)), Math.max(i3, Math.min(i + i3, (isDecsetInternalBitSet ? this.mRightMargin : this.mColumns) - 1)));
    }

    private void scrollDownOneLine() {
        this.mScrollCounter++;
        if (this.mLeftMargin == 0 && this.mRightMargin == this.mColumns) {
            this.mScreen.scrollDownOneLine(this.mTopMargin, this.mBottomMargin, getStyle());
            return;
        }
        TerminalBuffer terminalBuffer = this.mScreen;
        int i = this.mLeftMargin;
        int i2 = this.mTopMargin;
        terminalBuffer.blockCopy(i, i2 + 1, this.mRightMargin - i, (this.mBottomMargin - i2) - 1, i, i2);
        TerminalBuffer terminalBuffer2 = this.mScreen;
        int i3 = this.mLeftMargin;
        terminalBuffer2.blockSet(i3, this.mBottomMargin - 1, this.mRightMargin - i3, 1, 32, (long) this.mEffect);
    }

    private void parseArg(int i) {
        if (i >= 48 && i <= 57) {
            int i2 = this.mArgIndex;
            int[] iArr = this.mArgs;
            if (i2 < iArr.length) {
                int i3 = iArr[i2];
                int i4 = i - 48;
                if (i3 >= 0) {
                    i4 += i3 * 10;
                }
                this.mArgs[this.mArgIndex] = i4;
            }
            continueSequence(this.mEscapeState);
        } else if (i == 59) {
            int i5 = this.mArgIndex;
            if (i5 < this.mArgs.length) {
                this.mArgIndex = i5 + 1;
            }
            continueSequence(this.mEscapeState);
        } else {
            unknownSequence(i);
        }
    }

    private int getArg0(int i) {
        return getArg(0, i, true);
    }

    private int getArg1(int i) {
        return getArg(1, i, true);
    }

    private int getArg(int i, int i2, boolean z) {
        int i3 = this.mArgs[i];
        return (i3 < 0 || (i3 == 0 && z)) ? i2 : i3;
    }

    private void collectOSCArgs(int i) {
        if (this.mOSCOrDeviceControlArgs.length() < 8192) {
            this.mOSCOrDeviceControlArgs.appendCodePoint(i);
            continueSequence(this.mEscapeState);
            return;
        }
        unknownSequence(i);
    }

    private void unimplementedSequence(int i) {
        logError("Unimplemented sequence char '" + ((char) i) + "' (U+" + String.format("%04x", new Object[]{Integer.valueOf(i)}) + ")");
        finishSequence();
    }

    private void unknownSequence(int i) {
        logError("Unknown sequence char '" + ((char) i) + "' (numeric value=" + i + ")");
        finishSequence();
    }

    private void unknownParameter(int i) {
        logError("Unknown parameter: " + i);
        finishSequence();
    }

    private void finishSequenceAndLogError(String str) {
        finishSequence();
    }

    private void finishSequence() {
        this.mEscapeState = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ea A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x010f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x015b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void emitCodePoint(int r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r0.mLastEmittedCodePoint = r1
            boolean r2 = r0.mUseLineDrawingUsesG0
            if (r2 == 0) goto L_0x000f
            boolean r2 = r0.mUseLineDrawingG0
            if (r2 == 0) goto L_0x00d0
            goto L_0x0013
        L_0x000f:
            boolean r2 = r0.mUseLineDrawingG1
            if (r2 == 0) goto L_0x00d0
        L_0x0013:
            r2 = 48
            if (r1 == r2) goto L_0x00cb
            switch(r1) {
                case 95: goto L_0x00c6;
                case 96: goto L_0x00c1;
                case 97: goto L_0x00bc;
                case 98: goto L_0x00b7;
                case 99: goto L_0x00b2;
                case 100: goto L_0x00ad;
                case 101: goto L_0x00a8;
                case 102: goto L_0x00a3;
                case 103: goto L_0x009e;
                case 104: goto L_0x0099;
                case 105: goto L_0x0094;
                case 106: goto L_0x008f;
                case 107: goto L_0x008a;
                case 108: goto L_0x0085;
                case 109: goto L_0x0080;
                case 110: goto L_0x007b;
                case 111: goto L_0x0076;
                case 112: goto L_0x0070;
                case 113: goto L_0x006a;
                case 114: goto L_0x0064;
                case 115: goto L_0x005e;
                case 116: goto L_0x0058;
                case 117: goto L_0x0052;
                case 118: goto L_0x004c;
                case 119: goto L_0x0046;
                case 120: goto L_0x0040;
                case 121: goto L_0x003a;
                case 122: goto L_0x0034;
                case 123: goto L_0x002e;
                case 124: goto L_0x0028;
                case 125: goto L_0x0022;
                case 126: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x00d0
        L_0x001c:
            r1 = 183(0xb7, float:2.56E-43)
            r5 = 183(0xb7, float:2.56E-43)
            goto L_0x00d1
        L_0x0022:
            r1 = 163(0xa3, float:2.28E-43)
            r5 = 163(0xa3, float:2.28E-43)
            goto L_0x00d1
        L_0x0028:
            r1 = 8800(0x2260, float:1.2331E-41)
            r5 = 8800(0x2260, float:1.2331E-41)
            goto L_0x00d1
        L_0x002e:
            r1 = 960(0x3c0, float:1.345E-42)
            r5 = 960(0x3c0, float:1.345E-42)
            goto L_0x00d1
        L_0x0034:
            r1 = 8805(0x2265, float:1.2338E-41)
            r5 = 8805(0x2265, float:1.2338E-41)
            goto L_0x00d1
        L_0x003a:
            r1 = 8804(0x2264, float:1.2337E-41)
            r5 = 8804(0x2264, float:1.2337E-41)
            goto L_0x00d1
        L_0x0040:
            r1 = 9474(0x2502, float:1.3276E-41)
            r5 = 9474(0x2502, float:1.3276E-41)
            goto L_0x00d1
        L_0x0046:
            r1 = 9516(0x252c, float:1.3335E-41)
            r5 = 9516(0x252c, float:1.3335E-41)
            goto L_0x00d1
        L_0x004c:
            r1 = 9524(0x2534, float:1.3346E-41)
            r5 = 9524(0x2534, float:1.3346E-41)
            goto L_0x00d1
        L_0x0052:
            r1 = 9508(0x2524, float:1.3324E-41)
            r5 = 9508(0x2524, float:1.3324E-41)
            goto L_0x00d1
        L_0x0058:
            r1 = 9500(0x251c, float:1.3312E-41)
            r5 = 9500(0x251c, float:1.3312E-41)
            goto L_0x00d1
        L_0x005e:
            r1 = 9149(0x23bd, float:1.282E-41)
            r5 = 9149(0x23bd, float:1.282E-41)
            goto L_0x00d1
        L_0x0064:
            r1 = 9148(0x23bc, float:1.2819E-41)
            r5 = 9148(0x23bc, float:1.2819E-41)
            goto L_0x00d1
        L_0x006a:
            r1 = 9472(0x2500, float:1.3273E-41)
            r5 = 9472(0x2500, float:1.3273E-41)
            goto L_0x00d1
        L_0x0070:
            r1 = 9147(0x23bb, float:1.2818E-41)
            r5 = 9147(0x23bb, float:1.2818E-41)
            goto L_0x00d1
        L_0x0076:
            r1 = 9146(0x23ba, float:1.2816E-41)
            r5 = 9146(0x23ba, float:1.2816E-41)
            goto L_0x00d1
        L_0x007b:
            r1 = 9532(0x253c, float:1.3357E-41)
            r5 = 9532(0x253c, float:1.3357E-41)
            goto L_0x00d1
        L_0x0080:
            r1 = 9492(0x2514, float:1.3301E-41)
            r5 = 9492(0x2514, float:1.3301E-41)
            goto L_0x00d1
        L_0x0085:
            r1 = 9484(0x250c, float:1.329E-41)
            r5 = 9484(0x250c, float:1.329E-41)
            goto L_0x00d1
        L_0x008a:
            r1 = 9488(0x2510, float:1.3296E-41)
            r5 = 9488(0x2510, float:1.3296E-41)
            goto L_0x00d1
        L_0x008f:
            r1 = 9496(0x2518, float:1.3307E-41)
            r5 = 9496(0x2518, float:1.3307E-41)
            goto L_0x00d1
        L_0x0094:
            r1 = 9227(0x240b, float:1.293E-41)
            r5 = 9227(0x240b, float:1.293E-41)
            goto L_0x00d1
        L_0x0099:
            r1 = 10
            r5 = 10
            goto L_0x00d1
        L_0x009e:
            r1 = 177(0xb1, float:2.48E-43)
            r5 = 177(0xb1, float:2.48E-43)
            goto L_0x00d1
        L_0x00a3:
            r1 = 176(0xb0, float:2.47E-43)
            r5 = 176(0xb0, float:2.47E-43)
            goto L_0x00d1
        L_0x00a8:
            r1 = 9226(0x240a, float:1.2928E-41)
            r5 = 9226(0x240a, float:1.2928E-41)
            goto L_0x00d1
        L_0x00ad:
            r1 = 13
            r5 = 13
            goto L_0x00d1
        L_0x00b2:
            r1 = 9228(0x240c, float:1.2931E-41)
            r5 = 9228(0x240c, float:1.2931E-41)
            goto L_0x00d1
        L_0x00b7:
            r1 = 9225(0x2409, float:1.2927E-41)
            r5 = 9225(0x2409, float:1.2927E-41)
            goto L_0x00d1
        L_0x00bc:
            r1 = 9618(0x2592, float:1.3478E-41)
            r5 = 9618(0x2592, float:1.3478E-41)
            goto L_0x00d1
        L_0x00c1:
            r1 = 9670(0x25c6, float:1.355E-41)
            r5 = 9670(0x25c6, float:1.355E-41)
            goto L_0x00d1
        L_0x00c6:
            r1 = 32
            r5 = 32
            goto L_0x00d1
        L_0x00cb:
            r1 = 9608(0x2588, float:1.3464E-41)
            r5 = 9608(0x2588, float:1.3464E-41)
            goto L_0x00d1
        L_0x00d0:
            r5 = r1
        L_0x00d1:
            r1 = 8
            boolean r1 = r0.isDecsetInternalBitSet(r1)
            int r8 = com.thecrackertechnology.dragonterminal.backend.WcWidth.width(r5)
            int r2 = r0.mCursorCol
            int r3 = r0.mRightMargin
            r9 = 1
            int r3 = r3 - r9
            r10 = 0
            if (r2 != r3) goto L_0x00e6
            r2 = 1
            goto L_0x00e7
        L_0x00e6:
            r2 = 0
        L_0x00e7:
            r3 = 2
            if (r1 == 0) goto L_0x010f
            if (r2 == 0) goto L_0x0114
            boolean r2 = r0.mAboutToAutoWrap
            if (r2 == 0) goto L_0x00f2
            if (r8 == r9) goto L_0x00f4
        L_0x00f2:
            if (r8 != r3) goto L_0x0114
        L_0x00f4:
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r2 = r0.mScreen
            int r3 = r0.mCursorRow
            r2.setLineWrap(r3)
            int r2 = r0.mLeftMargin
            r0.mCursorCol = r2
            int r2 = r0.mCursorRow
            int r3 = r2 + 1
            int r4 = r0.mBottomMargin
            if (r3 >= r4) goto L_0x010b
            int r2 = r2 + r9
            r0.mCursorRow = r2
            goto L_0x0114
        L_0x010b:
            r18.scrollDownOneLine()
            goto L_0x0114
        L_0x010f:
            if (r2 == 0) goto L_0x0114
            if (r8 != r3) goto L_0x0114
            return
        L_0x0114:
            boolean r2 = r0.mInsertMode
            if (r2 == 0) goto L_0x0131
            if (r8 <= 0) goto L_0x0131
            int r12 = r0.mCursorCol
            int r2 = r12 + r8
            int r3 = r0.mRightMargin
            if (r2 >= r3) goto L_0x0131
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r11 = r0.mScreen
            int r4 = r0.mCursorRow
            int r14 = r3 - r2
            r15 = 1
            r13 = r4
            r16 = r2
            r17 = r4
            r11.blockCopy(r12, r13, r14, r15, r16, r17)
        L_0x0131:
            if (r8 > 0) goto L_0x013d
            int r2 = r0.mCursorCol
            if (r2 <= 0) goto L_0x013d
            boolean r2 = r0.mAboutToAutoWrap
            if (r2 != 0) goto L_0x013d
            r2 = 1
            goto L_0x013e
        L_0x013d:
            r2 = 0
        L_0x013e:
            com.thecrackertechnology.dragonterminal.backend.TerminalBuffer r3 = r0.mScreen
            int r4 = r0.mCursorCol
            int r4 = r4 - r2
            int r6 = r0.mCursorRow
            long r11 = r18.getStyle()
            r2 = r3
            r3 = r4
            r4 = r6
            r6 = r11
            r2.setChar(r3, r4, r5, r6)
            if (r1 == 0) goto L_0x015e
            if (r8 <= 0) goto L_0x015e
            int r1 = r0.mCursorCol
            int r2 = r0.mRightMargin
            int r2 = r2 - r8
            if (r1 != r2) goto L_0x015c
            r10 = 1
        L_0x015c:
            r0.mAboutToAutoWrap = r10
        L_0x015e:
            int r1 = r0.mCursorCol
            int r1 = r1 + r8
            int r2 = r0.mRightMargin
            int r2 = r2 - r9
            int r1 = java.lang.Math.min(r1, r2)
            r0.mCursorCol = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.backend.TerminalEmulator.emitCodePoint(int):void");
    }

    private void setCursorRow(int i) {
        this.mCursorRow = i;
        this.mAboutToAutoWrap = false;
    }

    private void setCursorCol(int i) {
        this.mCursorCol = i;
        this.mAboutToAutoWrap = false;
    }

    private void setCursorColRespectingOriginMode(int i) {
        setCursorPosition(i, this.mCursorRow);
    }

    private void setCursorRowCol(int i, int i2) {
        this.mCursorRow = Math.max(0, Math.min(i, this.mRows - 1));
        this.mCursorCol = Math.max(0, Math.min(i2, this.mColumns - 1));
        this.mAboutToAutoWrap = false;
    }

    public int getScrollCounter() {
        return this.mScrollCounter;
    }

    public void clearScrollCounter() {
        this.mScrollCounter = 0;
    }

    public void reset() {
        this.mCursorStyle = 0;
        this.mArgIndex = 0;
        this.mContinueSequence = false;
        this.mEscapeState = 0;
        this.mInsertMode = false;
        this.mLeftMargin = 0;
        this.mTopMargin = 0;
        this.mBottomMargin = this.mRows;
        this.mRightMargin = this.mColumns;
        this.mAboutToAutoWrap = false;
        SavedScreenState savedScreenState = this.mSavedStateMain;
        SavedScreenState savedScreenState2 = this.mSavedStateAlt;
        savedScreenState2.mSavedForeColor = 256;
        savedScreenState.mSavedForeColor = 256;
        this.mForeColor = 256;
        savedScreenState2.mSavedBackColor = 257;
        savedScreenState.mSavedBackColor = 257;
        this.mBackColor = 257;
        setDefaultTabStops();
        this.mUseLineDrawingG1 = false;
        this.mUseLineDrawingG0 = false;
        this.mUseLineDrawingUsesG0 = true;
        SavedScreenState savedScreenState3 = this.mSavedStateMain;
        savedScreenState3.mSavedDecFlags = 0;
        savedScreenState3.mSavedEffect = 0;
        savedScreenState3.mSavedCursorCol = 0;
        savedScreenState3.mSavedCursorRow = 0;
        SavedScreenState savedScreenState4 = this.mSavedStateAlt;
        savedScreenState4.mSavedDecFlags = 0;
        savedScreenState4.mSavedEffect = 0;
        savedScreenState4.mSavedCursorCol = 0;
        savedScreenState4.mSavedCursorRow = 0;
        this.mCurrentDecSetFlags = 0;
        setDecsetinternalBit(8, true);
        setDecsetinternalBit(16, true);
        SavedScreenState savedScreenState5 = this.mSavedStateMain;
        SavedScreenState savedScreenState6 = this.mSavedStateAlt;
        int i = this.mCurrentDecSetFlags;
        savedScreenState6.mSavedDecFlags = i;
        savedScreenState5.mSavedDecFlags = i;
        this.mSavedDecSetFlags = i;
        this.mUtf8ToFollow = 0;
        this.mUtf8Index = 0;
        this.mColors.reset();
        this.mSession.onColorsChanged();
    }

    public void setColorScheme(TerminalColorScheme terminalColorScheme) {
        this.mColors.reset(terminalColorScheme);
        this.mSession.onColorsChanged();
    }

    public String getSelectedText(int i, int i2, int i3, int i4) {
        return this.mScreen.getSelectedText(i, i2, i3, i4);
    }

    public String getTitle() {
        return this.mTitle;
    }

    private void setTitle(String str) {
        String str2 = this.mTitle;
        this.mTitle = str;
        if (!Objects.equals(str2, str)) {
            this.mSession.titleChanged(str2, str);
        }
    }

    public void paste(String str) {
        String replaceAll = str.replaceAll("(\u001b|[-])", "").replaceAll("\r?\n", StringUtils.CR);
        boolean isDecsetInternalBitSet = isDecsetInternalBitSet(1024);
        if (isDecsetInternalBitSet) {
            this.mSession.write("\u001b[200~");
        }
        this.mSession.write(replaceAll);
        if (isDecsetInternalBitSet) {
            this.mSession.write("\u001b[201~");
        }
    }

    static final class SavedScreenState {
        int mSavedBackColor;
        int mSavedCursorCol;
        int mSavedCursorRow;
        int mSavedDecFlags;
        int mSavedEffect;
        int mSavedForeColor;
        boolean mUseLineDrawingG0;
        boolean mUseLineDrawingG1;
        boolean mUseLineDrawingUsesG0 = true;

        SavedScreenState() {
        }
    }

    public String toString() {
        return "TerminalEmulator[size=" + this.mScreen.mColumns + "x" + this.mScreen.mScreenRows + ", margins={" + this.mTopMargin + "," + this.mRightMargin + "," + this.mBottomMargin + "," + this.mLeftMargin + "}]";
    }
}
