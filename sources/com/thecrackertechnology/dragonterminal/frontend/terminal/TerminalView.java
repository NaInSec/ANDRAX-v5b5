package com.thecrackertechnology.dragonterminal.frontend.terminal;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Scroller;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.KeyHandler;
import com.thecrackertechnology.dragonterminal.backend.TerminalBuffer;
import com.thecrackertechnology.dragonterminal.backend.TerminalEmulator;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.frontend.completion.listener.OnAutoCompleteListener;
import com.thecrackertechnology.dragonterminal.frontend.terminal.GestureAndScaleRecognizer;
import org.apache.commons.lang3.StringUtils;

public final class TerminalView extends View {
    private static final boolean LOG_KEY_EVENTS = false;
    private boolean mAccessibilityEnabled;
    private ActionMode mActionMode;
    TerminalViewClient mClient;
    int mCombiningAccent;
    TerminalEmulator mEmulator;
    private boolean mEnableWordBasedIme = false;
    GestureAndScaleRecognizer mGestureRecognizer;
    boolean mInitialTextSelection;
    boolean mIsDraggingLeftSelection;
    boolean mIsSelectingText = false;
    private BitmapDrawable mLeftSelectionHandle;
    private int mMouseScrollStartX = -1;
    private int mMouseScrollStartY = -1;
    private long mMouseStartDownTime = -1;
    TerminalRenderer mRenderer;
    private BitmapDrawable mRightSelectionHandle;
    float mScaleFactor = 1.0f;
    float mScrollRemainder;
    Scroller mScroller;
    int mSelX1 = -1;
    int mSelX2 = -1;
    int mSelY1 = -1;
    int mSelY2 = -1;
    float mSelectionDownX;
    float mSelectionDownY;
    TerminalSession mTermSession;
    int mTextSize;
    int mTopRow;
    /* access modifiers changed from: private */
    public OnAutoCompleteListener onAutoCompleteListener;

    public boolean isOpaque() {
        return true;
    }

    public boolean onCheckIsTextEditor() {
        return true;
    }

    public TerminalView(Context context) {
        super(context);
        commonInit(context);
    }

    public TerminalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        commonInit(context);
    }

    private void commonInit(Context context) {
        this.mGestureRecognizer = new GestureAndScaleRecognizer(context, new GestureAndScaleRecognizer.Listener() {
            private float doubleTapX;
            private float doubleTapY;
            private boolean draggedAfterDoubleTap;
            private boolean scrolledWithFinger;

            public boolean onDoubleTap(MotionEvent motionEvent) {
                return true;
            }

            public boolean onDown(float f, float f2) {
                return false;
            }

            public boolean onUp(MotionEvent motionEvent) {
                TerminalView terminalView = TerminalView.this;
                terminalView.mScrollRemainder = 0.0f;
                if (terminalView.mEmulator == null || !TerminalView.this.mEmulator.isMouseTrackingActive() || TerminalView.this.mIsSelectingText || this.scrolledWithFinger) {
                    this.scrolledWithFinger = false;
                    return false;
                }
                TerminalView.this.sendMouseEventCode(motionEvent, 0, true);
                TerminalView.this.sendMouseEventCode(motionEvent, 0, false);
                return true;
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (TerminalView.this.mEmulator == null) {
                    return true;
                }
                if (TerminalView.this.mIsSelectingText) {
                    TerminalView.this.toggleSelectingText((MotionEvent) null);
                    return true;
                }
                TerminalView.this.requestFocus();
                if (TerminalView.this.mEmulator.isMouseTrackingActive() || motionEvent.isFromSource(8194)) {
                    return false;
                }
                TerminalView.this.mClient.onSingleTapUp(motionEvent);
                return true;
            }

            public boolean onScroll(MotionEvent motionEvent, float f, float f2) {
                if (TerminalView.this.mEmulator != null && !TerminalView.this.mIsSelectingText) {
                    if (!TerminalView.this.mEmulator.isMouseTrackingActive() || !motionEvent.isFromSource(8194)) {
                        this.scrolledWithFinger = true;
                        float f3 = f2 + TerminalView.this.mScrollRemainder;
                        int i = (int) (f3 / ((float) TerminalView.this.mRenderer.mFontLineSpacing));
                        TerminalView terminalView = TerminalView.this;
                        terminalView.mScrollRemainder = f3 - ((float) (terminalView.mRenderer.mFontLineSpacing * i));
                        TerminalView.this.doScroll(motionEvent, i);
                    } else {
                        TerminalView.this.sendMouseEventCode(motionEvent, 32, true);
                    }
                }
                return true;
            }

            public boolean onScale(float f, float f2, float f3) {
                if (TerminalView.this.mEmulator != null && !TerminalView.this.mIsSelectingText) {
                    TerminalView.this.mScaleFactor *= f3;
                    TerminalView terminalView = TerminalView.this;
                    terminalView.mScaleFactor = terminalView.mClient.onScale(TerminalView.this.mScaleFactor);
                }
                return true;
            }

            public boolean onFling(final MotionEvent motionEvent, float f, float f2) {
                if (TerminalView.this.mEmulator == null || TerminalView.this.mIsSelectingText || !TerminalView.this.mScroller.isFinished()) {
                    return true;
                }
                final boolean isMouseTrackingActive = TerminalView.this.mEmulator.isMouseTrackingActive();
                if (isMouseTrackingActive) {
                    TerminalView.this.mScroller.fling(0, 0, 0, -((int) (f2 * 0.25f)), 0, 0, (-TerminalView.this.mEmulator.mRows) / 2, TerminalView.this.mEmulator.mRows / 2);
                } else {
                    TerminalView.this.mScroller.fling(0, TerminalView.this.mTopRow, 0, -((int) (f2 * 0.25f)), 0, 0, -TerminalView.this.mEmulator.getScreen().getActiveTranscriptRows(), 0);
                }
                TerminalView.this.post(new Runnable() {
                    private int mLastY = 0;

                    public void run() {
                        if (isMouseTrackingActive != TerminalView.this.mEmulator.isMouseTrackingActive()) {
                            TerminalView.this.mScroller.abortAnimation();
                        } else if (!TerminalView.this.mScroller.isFinished()) {
                            boolean computeScrollOffset = TerminalView.this.mScroller.computeScrollOffset();
                            int currY = TerminalView.this.mScroller.getCurrY();
                            TerminalView.this.doScroll(motionEvent, currY - (isMouseTrackingActive ? this.mLastY : TerminalView.this.mTopRow));
                            this.mLastY = currY;
                            if (computeScrollOffset) {
                                TerminalView.this.post(this);
                            }
                        }
                    }
                });
                return true;
            }

            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                if (TerminalView.this.mEmulator.isMouseTrackingActive() && !motionEvent.isFromSource(8194)) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        this.doubleTapX = motionEvent.getX();
                        this.doubleTapY = motionEvent.getY();
                        this.draggedAfterDoubleTap = false;
                        TerminalView.this.sendMouseEventCode(motionEvent, 0, true);
                    } else if (action == 1) {
                        if (!this.draggedAfterDoubleTap) {
                            TerminalView.this.sendMouseEventCode(motionEvent, 0, false);
                            TerminalView.this.sendMouseEventCode(motionEvent, 0, true);
                        }
                        TerminalView.this.sendMouseEventCode(motionEvent, 0, false);
                    } else if (action == 2 && (Math.abs(motionEvent.getX() - this.doubleTapX) >= TerminalView.this.mRenderer.mFontWidth || Math.abs(motionEvent.getY() - this.doubleTapY) >= ((float) TerminalView.this.mRenderer.mFontLineSpacing))) {
                        this.doubleTapX = motionEvent.getX();
                        this.doubleTapY = motionEvent.getY();
                        this.draggedAfterDoubleTap = true;
                        TerminalView.this.sendMouseEventCode(motionEvent, 32, true);
                    }
                }
                return true;
            }

            public void onLongPress(MotionEvent motionEvent) {
                if (!TerminalView.this.mGestureRecognizer.isInProgress() && !TerminalView.this.mClient.onLongPress(motionEvent) && !TerminalView.this.mIsSelectingText) {
                    TerminalView.this.performHapticFeedback(0);
                    TerminalView.this.toggleSelectingText(motionEvent);
                }
            }
        });
        this.mScroller = new Scroller(context);
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        this.mAccessibilityEnabled = accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public void setTerminalViewClient(TerminalViewClient terminalViewClient) {
        this.mClient = terminalViewClient;
    }

    public void setOnKeyListener(View.OnKeyListener onKeyListener) {
        if (onKeyListener instanceof TerminalViewClient) {
            setTerminalViewClient((TerminalViewClient) onKeyListener);
        }
    }

    public boolean attachSession(TerminalSession terminalSession) {
        if (terminalSession == this.mTermSession) {
            return false;
        }
        this.mTopRow = 0;
        this.mTermSession = terminalSession;
        this.mEmulator = null;
        this.mCombiningAccent = 0;
        updateSize();
        setVerticalScrollBarEnabled(true);
        return true;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        if (this.mEnableWordBasedIme) {
            editorInfo.inputType = 1;
        } else {
            editorInfo.inputType = 0;
        }
        editorInfo.imeOptions = 33554432;
        return new BaseInputConnection(this, true) {
            public boolean finishComposingText() {
                super.finishComposingText();
                sendTextToTerminal(getEditable());
                getEditable().clear();
                return true;
            }

            public boolean commitText(CharSequence charSequence, int i) {
                super.commitText(charSequence, i);
                if (TerminalView.this.mEmulator == null) {
                    return true;
                }
                Editable editable = getEditable();
                sendTextToTerminal(editable);
                if (TerminalView.this.onAutoCompleteListener != null) {
                    TerminalView.this.onAutoCompleteListener.onCompletionRequired(editable.toString());
                }
                editable.clear();
                return true;
            }

            public boolean deleteSurroundingText(int i, int i2) {
                KeyEvent keyEvent = new KeyEvent(0, 67);
                for (int i3 = 0; i3 < i; i3++) {
                    sendKeyEvent(keyEvent);
                }
                return super.deleteSurroundingText(i, i2);
            }

            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: char} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: char} */
            /* access modifiers changed from: package-private */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void sendTextToTerminal(java.lang.CharSequence r8) {
                /*
                    r7 = this;
                    int r0 = r8.length()
                    r1 = 0
                    r2 = 0
                L_0x0006:
                    if (r2 >= r0) goto L_0x004c
                    char r3 = r8.charAt(r2)
                    boolean r4 = java.lang.Character.isHighSurrogate(r3)
                    if (r4 == 0) goto L_0x0022
                    int r2 = r2 + 1
                    if (r2 >= r0) goto L_0x001f
                    char r4 = r8.charAt(r2)
                    int r3 = java.lang.Character.toCodePoint(r3, r4)
                    goto L_0x0022
                L_0x001f:
                    r3 = 65533(0xfffd, float:9.1831E-41)
                L_0x0022:
                    r4 = 31
                    r5 = 1
                    if (r3 > r4) goto L_0x0044
                    r4 = 27
                    if (r3 == r4) goto L_0x0044
                    r4 = 10
                    if (r3 != r4) goto L_0x0031
                    r3 = 13
                L_0x0031:
                    switch(r3) {
                        case 28: goto L_0x0041;
                        case 29: goto L_0x003e;
                        case 30: goto L_0x003b;
                        case 31: goto L_0x0038;
                        default: goto L_0x0034;
                    }
                L_0x0034:
                    int r3 = r3 + 96
                L_0x0036:
                    r4 = 1
                    goto L_0x0045
                L_0x0038:
                    r3 = 95
                    goto L_0x0036
                L_0x003b:
                    r3 = 94
                    goto L_0x0036
                L_0x003e:
                    r3 = 93
                    goto L_0x0036
                L_0x0041:
                    r3 = 92
                    goto L_0x0036
                L_0x0044:
                    r4 = 0
                L_0x0045:
                    com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView r6 = com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView.this
                    r6.inputCodePoint(r3, r4, r1)
                    int r2 = r2 + r5
                    goto L_0x0006
                L_0x004c:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView.AnonymousClass2.sendTextToTerminal(java.lang.CharSequence):void");
            }
        };
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollRange() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return 1;
        }
        return terminalEmulator.getScreen().getActiveRows();
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollExtent() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return 1;
        }
        return terminalEmulator.mRows;
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollOffset() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return 1;
        }
        return (terminalEmulator.getScreen().getActiveRows() + this.mTopRow) - this.mEmulator.mRows;
    }

    public void onScreenUpdated() {
        int i;
        if (this.mEmulator != null) {
            boolean z = true;
            boolean z2 = this.mTopRow != 0;
            if (this.mIsSelectingText || z2) {
                int activeTranscriptRows = this.mEmulator.getScreen().getActiveTranscriptRows();
                int scrollCounter = this.mEmulator.getScrollCounter();
                int i2 = this.mTopRow;
                if ((-i2) + scrollCounter > activeTranscriptRows) {
                    if (this.mIsSelectingText) {
                        toggleSelectingText((MotionEvent) null);
                    }
                    z = false;
                } else {
                    this.mTopRow = i2 - scrollCounter;
                    this.mSelY1 -= scrollCounter;
                    this.mSelY2 -= scrollCounter;
                }
                if (z2) {
                    awakenScrollBars();
                }
            } else {
                z = false;
            }
            if (!z && (i = this.mTopRow) != 0) {
                if (i < -3) {
                    awakenScrollBars();
                }
                this.mTopRow = 0;
            }
            this.mEmulator.clearScrollCounter();
            invalidate();
            String selectedText = this.mEmulator.getScreen().getSelectedText(0, this.mTopRow, this.mEmulator.mColumns, this.mTopRow + this.mEmulator.mRows);
            if (this.mAccessibilityEnabled) {
                setContentDescription(selectedText);
            }
        }
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public void setTextSize(int i) {
        this.mTextSize = i;
        TerminalRenderer terminalRenderer = this.mRenderer;
        this.mRenderer = new TerminalRenderer(i, terminalRenderer == null ? Typeface.MONOSPACE : terminalRenderer.mTypeface);
        updateSize();
    }

    public void setTypeface(Typeface typeface) {
        this.mRenderer = new TerminalRenderer(this.mRenderer.mTextSize, typeface);
        updateSize();
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void sendMouseEventCode(MotionEvent motionEvent, int i, boolean z) {
        int x = ((int) (motionEvent.getX() / this.mRenderer.mFontWidth)) + 1;
        int y = ((int) ((motionEvent.getY() - ((float) this.mRenderer.mFontLineSpacingAndAscent)) / ((float) this.mRenderer.mFontLineSpacing))) + 1;
        if (z && (i == 65 || i == 64)) {
            if (this.mMouseStartDownTime == motionEvent.getDownTime()) {
                x = this.mMouseScrollStartX;
                y = this.mMouseScrollStartY;
            } else {
                this.mMouseStartDownTime = motionEvent.getDownTime();
                this.mMouseScrollStartX = x;
                this.mMouseScrollStartY = y;
            }
        }
        this.mEmulator.sendMouseEvent(i, x, y, z);
    }

    /* access modifiers changed from: package-private */
    public void doScroll(MotionEvent motionEvent, int i) {
        boolean z = i < 0;
        int abs = Math.abs(i);
        for (int i2 = 0; i2 < abs; i2++) {
            if (this.mEmulator.isMouseTrackingActive()) {
                sendMouseEventCode(motionEvent, z ? 64 : 65, true);
            } else if (this.mEmulator.isAlternateBufferActive()) {
                handleKeyCode(z ? 19 : 20, 0);
            } else {
                this.mTopRow = Math.min(0, Math.max(-this.mEmulator.getScreen().getActiveTranscriptRows(), this.mTopRow + (z ? -1 : 1)));
                if (!awakenScrollBars()) {
                    invalidate();
                }
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.mEmulator == null || !motionEvent.isFromSource(8194) || motionEvent.getAction() != 8) {
            return false;
        }
        if (motionEvent.getAxisValue(9) > 0.0f) {
            z = true;
        }
        doScroll(motionEvent, z ? -3 : 3);
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mEmulator == null) {
            return true;
        }
        int action = motionEvent.getAction();
        boolean z = false;
        if (this.mIsSelectingText) {
            int y = ((int) (motionEvent.getY() / ((float) this.mRenderer.mFontLineSpacing))) + this.mTopRow;
            int x = (int) (motionEvent.getX() / this.mRenderer.mFontWidth);
            if (action == 0) {
                if (Math.abs(x - this.mSelX1) + Math.abs(y - this.mSelY1) <= Math.abs(x - this.mSelX2) + Math.abs(y - this.mSelY2)) {
                    z = true;
                }
                this.mIsDraggingLeftSelection = z;
                this.mSelectionDownX = motionEvent.getX();
                this.mSelectionDownY = motionEvent.getY();
            } else if (action == 1) {
                this.mInitialTextSelection = false;
            } else if (action == 2 && !this.mInitialTextSelection) {
                float x2 = motionEvent.getX() - this.mSelectionDownX;
                float y2 = motionEvent.getY() - this.mSelectionDownY;
                int ceil = (int) Math.ceil((double) (x2 / this.mRenderer.mFontWidth));
                int ceil2 = (int) Math.ceil((double) (y2 / ((float) this.mRenderer.mFontLineSpacing)));
                this.mSelectionDownX += ((float) ceil) * this.mRenderer.mFontWidth;
                this.mSelectionDownY += (float) (this.mRenderer.mFontLineSpacing * ceil2);
                if (this.mIsDraggingLeftSelection) {
                    this.mSelX1 += ceil;
                    this.mSelY1 += ceil2;
                } else {
                    this.mSelX2 += ceil;
                    this.mSelY2 += ceil2;
                }
                this.mSelX1 = Math.min(this.mEmulator.mColumns, Math.max(0, this.mSelX1));
                this.mSelX2 = Math.min(this.mEmulator.mColumns, Math.max(0, this.mSelX2));
                if ((this.mSelY1 == this.mSelY2 && this.mSelX1 > this.mSelX2) || this.mSelY1 > this.mSelY2) {
                    this.mIsDraggingLeftSelection = !this.mIsDraggingLeftSelection;
                    int i = this.mSelX1;
                    int i2 = this.mSelY1;
                    this.mSelX1 = this.mSelX2;
                    this.mSelY1 = this.mSelY2;
                    this.mSelX2 = i;
                    this.mSelY2 = i2;
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    this.mActionMode.invalidateContentRect();
                }
                invalidate();
            }
            this.mGestureRecognizer.onTouchEvent(motionEvent);
            return true;
        }
        if (motionEvent.isFromSource(8194)) {
            if (motionEvent.isButtonPressed(2)) {
                if (action == 0) {
                    showContextMenu();
                }
                return true;
            } else if (motionEvent.isButtonPressed(4)) {
                pasteFromClipboard();
            } else if (this.mEmulator.isMouseTrackingActive()) {
                int action2 = motionEvent.getAction();
                if (action2 == 0 || action2 == 1) {
                    sendMouseEventCode(motionEvent, 0, motionEvent.getAction() == 0);
                } else if (action2 == 2) {
                    sendMouseEventCode(motionEvent, 32, true);
                }
                return true;
            }
        }
        this.mGestureRecognizer.onTouchEvent(motionEvent);
        return true;
    }

    public void pasteFromClipboard() {
        ClipData primaryClip;
        ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
        if (clipboardManager != null && (primaryClip = clipboardManager.getPrimaryClip()) != null) {
            CharSequence coerceToText = primaryClip.getItemAt(0).coerceToText(getContext());
            if (!TextUtils.isEmpty(coerceToText)) {
                this.mEmulator.paste(coerceToText.toString());
            }
        }
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.mIsSelectingText) {
                toggleSelectingText((MotionEvent) null);
                return true;
            } else if (this.mClient.shouldBackButtonBeMappedToEscape()) {
                int action = keyEvent.getAction();
                if (action == 0) {
                    return onKeyDown(i, keyEvent);
                }
                if (action == 1) {
                    return onKeyUp(i, keyEvent);
                }
            }
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        char unicodeChar;
        if (this.mEmulator == null) {
            return true;
        }
        if (this.mClient.onKeyDown(i, keyEvent, this.mTermSession)) {
            invalidate();
            return true;
        } else if (keyEvent.isSystem() && (!this.mClient.shouldBackButtonBeMappedToEscape() || i != 4)) {
            return super.onKeyDown(i, keyEvent);
        } else {
            if (keyEvent.getAction() == 2 && i == 0) {
                this.mTermSession.write(keyEvent.getCharacters());
                return true;
            }
            int metaState = keyEvent.getMetaState();
            boolean isCtrlPressed = keyEvent.isCtrlPressed();
            boolean z = (metaState & 16) != 0;
            boolean z2 = (metaState & 32) != 0;
            int i2 = isCtrlPressed ? KeyHandler.KEYMOD_CTRL : 0;
            if (keyEvent.isAltPressed()) {
                i2 |= Integer.MIN_VALUE;
            }
            if (keyEvent.isShiftPressed()) {
                i2 |= KeyHandler.KEYMOD_SHIFT;
            }
            if (!keyEvent.isFunctionPressed() && handleKeyCode(i, i2)) {
                return true;
            }
            int i3 = 28672;
            if (!z2) {
                i3 = 28690;
            }
            int unicodeChar2 = keyEvent.getUnicodeChar((~i3) & keyEvent.getMetaState());
            if (unicodeChar2 == 0) {
                return false;
            }
            int i4 = this.mCombiningAccent;
            if ((unicodeChar2 & Integer.MIN_VALUE) != 0) {
                if (i4 != 0) {
                    inputCodePoint(i4, isCtrlPressed, z);
                }
                this.mCombiningAccent = unicodeChar2 & Integer.MAX_VALUE;
            } else {
                if (i4 != 0) {
                    int deadChar = KeyCharacterMap.getDeadChar(i4, unicodeChar2);
                    if (deadChar > 0) {
                        unicodeChar2 = deadChar;
                    }
                    this.mCombiningAccent = 0;
                }
                inputCodePoint(unicodeChar2, isCtrlPressed, z);
            }
            if (this.mCombiningAccent != i4) {
                invalidate();
            }
            if (!(this.onAutoCompleteListener == null || !keyEvent.isPrintingKey() || (unicodeChar = (char) keyEvent.getUnicodeChar(metaState)) == 8)) {
                this.onAutoCompleteListener.onCompletionRequired(new String(new char[]{unicodeChar}));
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void inputCodePoint(int i, boolean z, boolean z2) {
        int i2;
        if (this.mTermSession != null) {
            boolean z3 = z || this.mClient.readControlKey();
            boolean z4 = z2 || this.mClient.readAltKey();
            if (!this.mClient.onCodePoint(i, z3, this.mTermSession)) {
                int i3 = 94;
                if (z3) {
                    if (i >= 97 && i <= 122) {
                        i2 = i - 97;
                    } else if (i >= 65 && i <= 90) {
                        i2 = i - 65;
                    } else if (i == 32 || i == 50) {
                        i = 0;
                    } else if (i == 91 || i == 51) {
                        i = 27;
                    } else if (i == 92 || i == 52) {
                        i = 28;
                    } else if (i == 93 || i == 53) {
                        i = 29;
                    } else if (i == 94 || i == 54) {
                        i = 30;
                    } else if (i == 95 || i == 55 || i == 47) {
                        i = 31;
                    } else if (i == 56) {
                        i = 127;
                    }
                    i = i2 + 1;
                }
                if (i > -1) {
                    if (i != 710) {
                        i3 = i != 715 ? i != 732 ? i : SDL_1_3_Keycodes.SDLK_FIND : 96;
                    }
                    this.mTermSession.writeCodePoint(z4, i3);
                    scrollToBottomIfNeeded();
                }
            }
        }
    }

    public boolean handleKeyCode(int i, int i2) {
        TerminalEmulator emulator = this.mTermSession.getEmulator();
        String code = KeyHandler.getCode(i, i2, emulator.isCursorKeysApplicationMode(), emulator.isKeypadApplicationMode());
        if (code == null) {
            return false;
        }
        this.mTermSession.write(code);
        scrollToBottomIfNeeded();
        OnAutoCompleteListener onAutoCompleteListener2 = this.onAutoCompleteListener;
        if (onAutoCompleteListener2 == null) {
            return true;
        }
        onAutoCompleteListener2.onKeyCode(i, i2);
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mEmulator == null) {
            return true;
        }
        if (this.mClient.onKeyUp(i, keyEvent)) {
            invalidate();
            return true;
        } else if (keyEvent.isSystem()) {
            return super.onKeyUp(i, keyEvent);
        } else {
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void scrollToBottomIfNeeded() {
        if (this.mTopRow != 0) {
            this.mTopRow = 0;
            this.mEmulator.clearScrollCounter();
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        updateSize();
    }

    public void updateSize() {
        int width = getWidth();
        int height = getHeight();
        if (width != 0 && height != 0 && this.mTermSession != null) {
            int max = Math.max(4, (int) (((float) width) / this.mRenderer.mFontWidth));
            int max2 = Math.max(4, (height - this.mRenderer.mFontLineSpacingAndAscent) / this.mRenderer.mFontLineSpacing);
            TerminalEmulator terminalEmulator = this.mEmulator;
            if (terminalEmulator == null || max != terminalEmulator.mColumns || max2 != this.mEmulator.mRows) {
                this.mTermSession.updateSize(max, max2);
                this.mEmulator = this.mTermSession.getEmulator();
                this.mTopRow = 0;
                scrollTo(0, 0);
                invalidate();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
            return;
        }
        this.mRenderer.render(terminalEmulator, canvas, this.mTopRow, this.mSelY1, this.mSelY2, this.mSelX1, this.mSelX2);
        if (this.mIsSelectingText) {
            int intrinsicWidth = this.mLeftSelectionHandle.getIntrinsicWidth();
            int i = intrinsicWidth / 4;
            int round = Math.round(((float) this.mSelX1) * this.mRenderer.mFontWidth) + i;
            int i2 = (((this.mSelY1 + 1) - this.mTopRow) * this.mRenderer.mFontLineSpacing) + this.mRenderer.mFontLineSpacingAndAscent;
            BitmapDrawable bitmapDrawable = this.mLeftSelectionHandle;
            bitmapDrawable.setBounds(round - intrinsicWidth, i2, round, bitmapDrawable.getIntrinsicHeight() + i2);
            this.mLeftSelectionHandle.draw(canvas);
            int round2 = Math.round(((float) (this.mSelX2 + 1)) * this.mRenderer.mFontWidth) - i;
            int i3 = (((this.mSelY2 + 1) - this.mTopRow) * this.mRenderer.mFontLineSpacing) + this.mRenderer.mFontLineSpacingAndAscent;
            BitmapDrawable bitmapDrawable2 = this.mRightSelectionHandle;
            bitmapDrawable2.setBounds(round2, i3, intrinsicWidth + round2, bitmapDrawable2.getIntrinsicHeight() + i3);
            this.mRightSelectionHandle.draw(canvas);
        }
    }

    public void toggleSelectingText(MotionEvent motionEvent) {
        this.mIsSelectingText = !this.mIsSelectingText;
        this.mClient.copyModeChanged(this.mIsSelectingText);
        if (this.mIsSelectingText) {
            if (this.mLeftSelectionHandle == null) {
                this.mLeftSelectionHandle = (BitmapDrawable) getContext().getDrawable(R.drawable.text_select_handle_left_material);
                this.mRightSelectionHandle = (BitmapDrawable) getContext().getDrawable(R.drawable.text_select_handle_right_material);
            }
            int x = (int) (motionEvent.getX() / this.mRenderer.mFontWidth);
            int y = ((int) ((motionEvent.getY() + ((float) (motionEvent.isFromSource(8194) ? 0 : -40))) / ((float) this.mRenderer.mFontLineSpacing))) + this.mTopRow;
            this.mSelX2 = x;
            this.mSelX1 = x;
            this.mSelY2 = y;
            this.mSelY1 = y;
            TerminalBuffer screen = this.mEmulator.getScreen();
            int i = this.mSelX1;
            int i2 = this.mSelY1;
            if (!StringUtils.SPACE.equals(screen.getSelectedText(i, i2, i, i2))) {
                while (true) {
                    int i3 = this.mSelX1;
                    if (i3 <= 0) {
                        break;
                    }
                    int i4 = this.mSelY1;
                    if ("".equals(screen.getSelectedText(i3 - 1, i4, i3 - 1, i4))) {
                        break;
                    }
                    this.mSelX1--;
                }
                while (this.mSelX2 < this.mEmulator.mColumns - 1) {
                    int i5 = this.mSelX2;
                    int i6 = this.mSelY1;
                    if ("".equals(screen.getSelectedText(i5 + 1, i6, i5 + 1, i6))) {
                        break;
                    }
                    this.mSelX2++;
                }
            }
            this.mInitialTextSelection = true;
            this.mIsDraggingLeftSelection = true;
            this.mSelectionDownX = motionEvent.getX();
            this.mSelectionDownY = motionEvent.getY();
            final AnonymousClass3 r7 = new ActionMode.Callback() {
                public void onDestroyActionMode(ActionMode actionMode) {
                }

                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    menu.add(0, 1, 0, R.string.copy_text).setShowAsAction(6);
                    menu.add(0, 2, 0, R.string.paste_text).setEnabled(((ClipboardManager) TerminalView.this.getContext().getSystemService("clipboard")).hasPrimaryClip()).setShowAsAction(6);
                    return true;
                }

                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    if (!TerminalView.this.mIsSelectingText) {
                        return true;
                    }
                    int itemId = menuItem.getItemId();
                    if (itemId == 1) {
                        TerminalView.this.mTermSession.clipboardText(TerminalView.this.mEmulator.getSelectedText(TerminalView.this.mSelX1, TerminalView.this.mSelY1, TerminalView.this.mSelX2, TerminalView.this.mSelY2).trim());
                    } else if (itemId == 2) {
                        TerminalView.this.pasteFromClipboard();
                    } else if (itemId == 3) {
                        TerminalView.this.showContextMenu();
                    }
                    TerminalView.this.toggleSelectingText((MotionEvent) null);
                    return true;
                }
            };
            if (Build.VERSION.SDK_INT >= 23) {
                this.mActionMode = startActionMode(new ActionMode.Callback2() {
                    public void onDestroyActionMode(ActionMode actionMode) {
                    }

                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        return r7.onCreateActionMode(actionMode, menu);
                    }

                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        return r7.onActionItemClicked(actionMode, menuItem);
                    }

                    public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
                        int round = Math.round(((float) TerminalView.this.mSelX1) * TerminalView.this.mRenderer.mFontWidth);
                        int round2 = Math.round(((float) TerminalView.this.mSelX2) * TerminalView.this.mRenderer.mFontWidth);
                        rect.set(Math.min(round, round2), Math.round((float) ((TerminalView.this.mSelY1 - TerminalView.this.mTopRow) * TerminalView.this.mRenderer.mFontLineSpacing)), Math.max(round, round2), Math.round((float) (((TerminalView.this.mSelY2 + 1) - TerminalView.this.mTopRow) * TerminalView.this.mRenderer.mFontLineSpacing)));
                    }
                }, 1);
            } else {
                this.mActionMode = startActionMode(r7);
            }
            invalidate();
            return;
        }
        this.mActionMode.finish();
        this.mSelY2 = -1;
        this.mSelX2 = -1;
        this.mSelY1 = -1;
        this.mSelX1 = -1;
        invalidate();
    }

    public TerminalSession getCurrentSession() {
        return this.mTermSession;
    }

    public OnAutoCompleteListener getOnAutoCompleteListener() {
        return this.onAutoCompleteListener;
    }

    public void setOnAutoCompleteListener(OnAutoCompleteListener onAutoCompleteListener2) {
        this.onAutoCompleteListener = onAutoCompleteListener2;
    }

    public int getCursorAbsoluteX() {
        return (int) this.mRenderer.getCursorX();
    }

    public int getCursorAbsoluteY() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        return (int) (this.mRenderer.getCursorY() + ((float) iArr[1]));
    }

    public void setEnableWordBasedIme(boolean z) {
        this.mEnableWordBasedIme = z;
    }
}
