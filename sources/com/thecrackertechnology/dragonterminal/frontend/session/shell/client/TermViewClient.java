package com.thecrackertechnology.dragonterminal.frontend.session.shell.client;

import android.content.Context;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import com.thecrackertechnology.andrax.AndraxApp;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.component.extrakey.ExtraKeyComponent;
import com.thecrackertechnology.dragonterminal.frontend.component.ComponentManager;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellTermSession;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalView;
import com.thecrackertechnology.dragonterminal.frontend.terminal.TerminalViewClient;
import com.thecrackertechnology.dragonterminal.frontend.terminal.extrakey.ExtraKeysView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\nH\u0002J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\nH\u0016J\"\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\nH\u0002J\"\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J$\u0010\"\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010#\u001a\u0004\u0018\u00010\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u001a\u0010$\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010#\u001a\u0004\u0018\u00010\u001bH\u0016J\u0012\u0010%\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010&H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(H\u0016J\u0012\u0010*\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010&H\u0016J\b\u0010+\u001a\u00020\nH\u0016J\b\u0010,\u001a\u00020\nH\u0016J\b\u0010-\u001a\u00020\u0013H\u0002J\b\u0010.\u001a\u00020\nH\u0016J\u001a\u0010/\u001a\u00020\u00132\b\u00100\u001a\u0004\u0018\u00010\b2\b\b\u0002\u00101\u001a\u00020\nJ\b\u00102\u001a\u00020\nH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u00063"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermViewClient;", "Lcom/thecrackertechnology/dragonterminal/frontend/terminal/TerminalViewClient;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "lastTitle", "", "mVirtualControlKeyDown", "", "mVirtualFnKeyDown", "termSessionData", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionData;", "getTermSessionData", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionData;", "setTermSessionData", "(Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/client/TermSessionData;)V", "changeFontSize", "", "increase", "copyModeChanged", "copyMode", "handleVirtualKeys", "keyCode", "", "event", "Landroid/view/KeyEvent;", "down", "onCodePoint", "codePoint", "ctrlDown", "session", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "onKeyDown", "e", "onKeyUp", "onLongPress", "Landroid/view/MotionEvent;", "onScale", "", "scale", "onSingleTapUp", "readAltKey", "readControlKey", "removeExtraKeys", "shouldBackButtonBeMappedToEscape", "updateExtraKeys", "title", "force", "updateExtraKeysVisibility", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TermViewClient.kt */
public final class TermViewClient implements TerminalViewClient {
    private final Context context;
    private String lastTitle = "";
    private boolean mVirtualControlKeyDown;
    private boolean mVirtualFnKeyDown;
    private TermSessionData termSessionData;

    public void copyModeChanged(boolean z) {
    }

    public boolean onLongPress(MotionEvent motionEvent) {
        return false;
    }

    public TermViewClient(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    public final Context getContext() {
        return this.context;
    }

    public final TermSessionData getTermSessionData() {
        return this.termSessionData;
    }

    public final void setTermSessionData(TermSessionData termSessionData2) {
        this.termSessionData = termSessionData2;
    }

    public float onScale(float f) {
        if (f >= 0.9f && f <= 1.1f) {
            return f;
        }
        changeFontSize(f > 1.0f);
        return 1.0f;
    }

    public void onSingleTapUp(MotionEvent motionEvent) {
        TerminalView termView;
        TermSessionData termSessionData2 = this.termSessionData;
        if (termSessionData2 != null && (termView = termSessionData2.getTermView()) != null) {
            Object systemService = this.context.getSystemService("input_method");
            if (systemService != null) {
                ((InputMethodManager) systemService).showSoftInput(termView, 1);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
    }

    public boolean shouldBackButtonBeMappedToEscape() {
        TermSessionData termSessionData2 = this.termSessionData;
        ShellTermSession shellTermSession = (ShellTermSession) (termSessionData2 != null ? termSessionData2.getTermSession() : null);
        if (shellTermSession != null) {
            return shellTermSession.getShellProfile().getEnableBackKeyToEscape();
        }
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent, TerminalSession terminalSession) {
        char unicodeChar;
        if (handleVirtualKeys(i, keyEvent, true)) {
            return true;
        }
        TermSessionData termSessionData2 = this.termSessionData;
        TermUiPresenter termUI = termSessionData2 != null ? termSessionData2.getTermUI() : null;
        if (i != 4) {
            if (i != 66) {
                if (keyEvent != null && keyEvent.isCtrlPressed() && keyEvent.isShiftPressed()) {
                    char unicodeChar2 = (char) keyEvent.getUnicodeChar(0);
                    if (unicodeChar2 == '+') {
                        changeFontSize(true);
                    } else if (unicodeChar2 == '-') {
                        changeFontSize(false);
                    } else if (unicodeChar2 != 'f') {
                        if (unicodeChar2 != 'n') {
                            if (unicodeChar2 != 'v') {
                                if (unicodeChar2 != 'x') {
                                    if (unicodeChar2 == 'z' && termUI != null) {
                                        termUI.requireSwitchToPrevious();
                                    }
                                } else if (termUI != null) {
                                    termUI.requireSwitchToNext();
                                }
                            } else if (termUI != null) {
                                termUI.requirePaste();
                            }
                        } else if (termUI != null) {
                            termUI.requireCreateNew();
                        }
                    } else if (termUI != null) {
                        termUI.requireToggleFullScreen();
                    }
                    return true;
                } else if (keyEvent == null || !keyEvent.isAltPressed() || '1' > (unicodeChar = (char) keyEvent.getUnicodeChar(0)) || '9' < unicodeChar) {
                    return false;
                } else {
                    int i2 = unicodeChar - '0';
                    if (termUI != null) {
                        termUI.requireSwitchTo(i2);
                    }
                    return true;
                }
            } else if (keyEvent == null || keyEvent.getAction() != 0 || terminalSession == null || terminalSession.isRunning()) {
                return false;
            } else {
                if (termUI != null) {
                    termUI.requireClose();
                }
                return true;
            }
        } else if (keyEvent == null || keyEvent.getAction() != 0 || termUI == null) {
            return false;
        } else {
            return termUI.requireFinishAutoCompletion();
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return handleVirtualKeys(i, keyEvent, false);
    }

    public boolean readControlKey() {
        TermSessionData termSessionData2 = this.termSessionData;
        ExtraKeysView extraKeysView = termSessionData2 != null ? termSessionData2.getExtraKeysView() : null;
        return (extraKeysView != null && extraKeysView.readControlButton()) || this.mVirtualControlKeyDown;
    }

    public boolean readAltKey() {
        TermSessionData termSessionData2 = this.termSessionData;
        ExtraKeysView extraKeysView = termSessionData2 != null ? termSessionData2.getExtraKeysView() : null;
        return (extraKeysView != null && extraKeysView.readAltButton()) || this.mVirtualFnKeyDown;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        r7 = false;
        r8 = -1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a4 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onCodePoint(int r7, boolean r8, com.thecrackertechnology.dragonterminal.backend.TerminalSession r9) {
        /*
            r6 = this;
            boolean r8 = r6.mVirtualFnKeyDown
            r0 = 0
            if (r8 == 0) goto L_0x00ac
            int r8 = java.lang.Character.toLowerCase(r7)
            char r1 = (char) r8
            r2 = 46
            r3 = 124(0x7c, float:1.74E-43)
            r4 = 1
            r5 = -1
            if (r1 == r2) goto L_0x0083
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 == r2) goto L_0x007f
            r2 = 110(0x6e, float:1.54E-43)
            if (r1 == r2) goto L_0x007c
            r2 = 112(0x70, float:1.57E-43)
            if (r1 == r2) goto L_0x0079
            r2 = 97
            if (r1 == r2) goto L_0x0076
            r2 = 98
            if (r1 == r2) goto L_0x0074
            r2 = 104(0x68, float:1.46E-43)
            if (r1 == r2) goto L_0x0071
            r2 = 105(0x69, float:1.47E-43)
            if (r1 == r2) goto L_0x006e
            switch(r1) {
                case 48: goto L_0x006c;
                case 49: goto L_0x0067;
                case 50: goto L_0x0067;
                case 51: goto L_0x0067;
                case 52: goto L_0x0067;
                case 53: goto L_0x0067;
                case 54: goto L_0x0067;
                case 55: goto L_0x0067;
                case 56: goto L_0x0067;
                case 57: goto L_0x0067;
                default: goto L_0x0031;
            }
        L_0x0031:
            switch(r1) {
                case 100: goto L_0x0064;
                case 101: goto L_0x0061;
                case 102: goto L_0x0074;
                default: goto L_0x0034;
            }
        L_0x0034:
            switch(r1) {
                case 115: goto L_0x005e;
                case 116: goto L_0x005b;
                case 117: goto L_0x0058;
                case 118: goto L_0x003e;
                case 119: goto L_0x003b;
                case 120: goto L_0x0074;
                default: goto L_0x0037;
            }
        L_0x0037:
            r7 = 0
            r8 = -1
        L_0x0039:
            r3 = -1
            goto L_0x0087
        L_0x003b:
            r3 = 19
            goto L_0x006e
        L_0x003e:
            android.content.Context r7 = r6.context
            java.lang.String r8 = "audio"
            java.lang.Object r7 = r7.getSystemService(r8)
            if (r7 == 0) goto L_0x0050
            android.media.AudioManager r7 = (android.media.AudioManager) r7
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
            r7.adjustSuggestedStreamVolume(r0, r8, r4)
            goto L_0x0037
        L_0x0050:
            kotlin.TypeCastException r7 = new kotlin.TypeCastException
            java.lang.String r8 = "null cannot be cast to non-null type android.media.AudioManager"
            r7.<init>(r8)
            throw r7
        L_0x0058:
            r8 = 95
            goto L_0x0085
        L_0x005b:
            r3 = 61
            goto L_0x006e
        L_0x005e:
            r3 = 20
            goto L_0x006e
        L_0x0061:
            r8 = 27
            goto L_0x0085
        L_0x0064:
            r3 = 22
            goto L_0x006e
        L_0x0067:
            int r7 = r7 + -49
            int r3 = r7 + 131
            goto L_0x006e
        L_0x006c:
            r3 = 140(0x8c, float:1.96E-43)
        L_0x006e:
            r7 = 0
            r8 = -1
            goto L_0x0087
        L_0x0071:
            r8 = 126(0x7e, float:1.77E-43)
            goto L_0x0085
        L_0x0074:
            r7 = 1
            goto L_0x0039
        L_0x0076:
            r3 = 21
            goto L_0x006e
        L_0x0079:
            r3 = 92
            goto L_0x006e
        L_0x007c:
            r3 = 93
            goto L_0x006e
        L_0x007f:
            r7 = 0
            r8 = 124(0x7c, float:1.74E-43)
            goto L_0x0039
        L_0x0083:
            r8 = 28
        L_0x0085:
            r7 = 0
            goto L_0x0039
        L_0x0087:
            if (r3 == r5) goto L_0x00a4
            if (r9 == 0) goto L_0x00ab
            com.thecrackertechnology.dragonterminal.backend.TerminalEmulator r7 = r9.getEmulator()
            java.lang.String r8 = "term"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            boolean r8 = r7.isCursorKeysApplicationMode()
            boolean r7 = r7.isKeypadApplicationMode()
            java.lang.String r7 = com.thecrackertechnology.dragonterminal.backend.KeyHandler.getCode(r3, r0, r8, r7)
            r9.write(r7)
            goto L_0x00ab
        L_0x00a4:
            if (r8 == r5) goto L_0x00ab
            if (r9 == 0) goto L_0x00ab
            r9.writeCodePoint(r7, r8)
        L_0x00ab:
            return r4
        L_0x00ac:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.frontend.session.shell.client.TermViewClient.onCodePoint(int, boolean, com.thecrackertechnology.dragonterminal.backend.TerminalSession):boolean");
    }

    private final boolean handleVirtualKeys(int i, KeyEvent keyEvent, boolean z) {
        boolean z2 = false;
        if (keyEvent == null) {
            return false;
        }
        TermSessionData termSessionData2 = this.termSessionData;
        ShellTermSession shellTermSession = (ShellTermSession) (termSessionData2 != null ? termSessionData2.getTermSession() : null);
        if (shellTermSession != null) {
            boolean enableSpecialVolumeKeys = shellTermSession.getShellProfile().getEnableSpecialVolumeKeys();
            InputDevice device = keyEvent.getDevice();
            if (device != null && device.getKeyboardType() == 2) {
                return false;
            }
            if (i == 25) {
                if (z && enableSpecialVolumeKeys) {
                    z2 = true;
                }
                this.mVirtualControlKeyDown = z2;
                return true;
            } else if (i == 24) {
                if (z && enableSpecialVolumeKeys) {
                    z2 = true;
                }
                this.mVirtualFnKeyDown = z2;
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ void updateExtraKeys$default(TermViewClient termViewClient, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        termViewClient.updateExtraKeys(str, z);
    }

    public final void updateExtraKeys(String str, boolean z) {
        TermSessionData termSessionData2 = this.termSessionData;
        ExtraKeysView extraKeysView = termSessionData2 != null ? termSessionData2.getExtraKeysView() : null;
        if (extraKeysView != null && str != null) {
            if (!(str.length() == 0)) {
                if (((!Intrinsics.areEqual((Object) this.lastTitle, (Object) str)) || z) && updateExtraKeysVisibility()) {
                    removeExtraKeys();
                    ((ExtraKeyComponent) ComponentManager.getComponent$default(ComponentManager.INSTANCE, ExtraKeyComponent.class, false, 2, (Object) null)).showShortcutKeys(str, extraKeysView);
                    extraKeysView.updateButtons();
                    this.lastTitle = str;
                }
            }
        }
    }

    private final boolean updateExtraKeysVisibility() {
        ExtraKeysView extraKeysView;
        TermSessionData termSessionData2 = this.termSessionData;
        if (termSessionData2 == null || (extraKeysView = termSessionData2.getExtraKeysView()) == null) {
            return false;
        }
        TermSessionData termSessionData3 = this.termSessionData;
        ShellTermSession shellTermSession = (ShellTermSession) (termSessionData3 != null ? termSessionData3.getTermSession() : null);
        if (shellTermSession == null) {
            return false;
        }
        if (AndraxApp.Companion.get().getResources().getConfiguration().orientation == 2) {
            extraKeysView.setVisibility(8);
            return false;
        } else if (shellTermSession.getShellProfile().getEnableExtraKeys()) {
            extraKeysView.setVisibility(0);
            return true;
        } else {
            extraKeysView.setVisibility(8);
            return false;
        }
    }

    private final void removeExtraKeys() {
        TermSessionData termSessionData2 = this.termSessionData;
        ExtraKeysView extraKeysView = termSessionData2 != null ? termSessionData2.getExtraKeysView() : null;
        if (extraKeysView != null) {
            extraKeysView.clearUserKeys();
        }
    }

    private final void changeFontSize(boolean z) {
        TermSessionData termSessionData2 = this.termSessionData;
        TerminalView termView = termSessionData2 != null ? termSessionData2.getTermView() : null;
        if (termView != null) {
            int validateFontSize = NeoPreference.INSTANCE.validateFontSize(termView.getTextSize() + ((z ? 1 : -1) * 1));
            termView.setTextSize(validateFontSize);
            NeoPreference.INSTANCE.store(NeoPreference.KEY_FONT_SIZE, (Object) Integer.valueOf(validateFontSize));
        }
    }
}
