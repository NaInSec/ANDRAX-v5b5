package com.thecrackertechnology.dragonterminal.frontend.session.xorg;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.Globals;
import com.thecrackertechnology.dragonterminal.NeoAccelerometerReader;
import com.thecrackertechnology.dragonterminal.NeoAudioThread;
import com.thecrackertechnology.dragonterminal.NeoGLView;
import com.thecrackertechnology.dragonterminal.NeoRenderer;
import com.thecrackertechnology.dragonterminal.NeoTextInput;
import com.thecrackertechnology.dragonterminal.NeoXorgSettings;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.client.XSessionData;
import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;
import java.util.HashMap;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002./B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u001aH\u0016J\u0006\u0010\u001e\u001a\u00020\u0017J\u0006\u0010\u001f\u001a\u00020\u0017J\u0006\u0010 \u001a\u00020\u0017J\u0012\u0010!\u001a\u00020\u00172\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010$\u001a\u00020\u00172\b\u0010%\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(H\u0016J\u0012\u0010)\u001a\u00020\u00172\b\u0010*\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020(H\u0016J\b\u0010-\u001a\u00020\u0017H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u00060"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession;", "Lcom/thecrackertechnology/dragonterminal/xorg/NeoXorgViewClient;", "mActivity", "Landroid/app/Activity;", "mSessionData", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/client/XSessionData;", "(Landroid/app/Activity;Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/client/XSessionData;)V", "getMSessionData", "()Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/client/XSessionData;", "mSessionName", "", "getMSessionName", "()Ljava/lang/String;", "setMSessionName", "(Ljava/lang/String;)V", "getContext", "getGLView", "Lcom/thecrackertechnology/dragonterminal/NeoGLView;", "getWindow", "Landroid/view/Window;", "getWindowManager", "Landroid/view/WindowManager;", "hideScreenKeyboard", "", "initScreenOrientation", "isKeyboardWithoutTextInputShown", "", "isPaused", "isRunningOnOUYA", "isScreenKeyboardShown", "onDestroy", "onPause", "onResume", "runOnUiThread", "runnable", "Ljava/lang/Runnable;", "setScreenKeyboardHintMessage", "hideMessage", "setSystemMousePointerVisible", "visible", "", "showScreenKeyboard", "oldText", "showScreenKeyboardWithoutTextInputField", "keyboard", "updateScreenOrientation", "BuiltInKeyboardView", "SimpleKeyListener", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: XSession.kt */
public final class XSession implements NeoXorgViewClient {
    /* access modifiers changed from: private */
    public final Activity mActivity;
    private final XSessionData mSessionData;
    private String mSessionName = "";

    public XSession(Activity activity, XSessionData xSessionData) {
        Intrinsics.checkParameterIsNotNull(activity, "mActivity");
        Intrinsics.checkParameterIsNotNull(xSessionData, "mSessionData");
        this.mActivity = activity;
        this.mSessionData = xSessionData;
        if (Globals.InhibitSuspend) {
            getWindow().setFlags(128, 128);
        }
        NeoXorgViewClient neoXorgViewClient = this;
        this.mSessionData.setClient(neoXorgViewClient);
        NeoXorgSettings.init(neoXorgViewClient);
        if (this.mSessionData.getAudioThread() == null) {
            this.mSessionData.setAudioThread(new NeoAudioThread(neoXorgViewClient));
        }
    }

    public final XSessionData getMSessionData() {
        return this.mSessionData;
    }

    public final String getMSessionName() {
        return this.mSessionName;
    }

    public final void setMSessionName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mSessionName = str;
    }

    public final void onPause() {
        NeoGLView glView;
        this.mSessionData.setPaused(true);
        if (this.mSessionData.getGlView() != null && (glView = this.mSessionData.getGlView()) != null) {
            glView.onPause();
        }
    }

    public final void onDestroy() {
        NeoGLView glView;
        if (this.mSessionData.getGlView() != null && (glView = this.mSessionData.getGlView()) != null) {
            glView.exitApp();
        }
    }

    public final void onResume() {
        NeoGLView glView;
        if (!(this.mSessionData.getGlView() == null || (glView = this.mSessionData.getGlView()) == null)) {
            glView.onResume();
        }
        this.mSessionData.setPaused(false);
    }

    public Activity getContext() {
        return this.mActivity;
    }

    public boolean isKeyboardWithoutTextInputShown() {
        return this.mSessionData.getKeyboardWithoutTextInputShown();
    }

    public boolean isPaused() {
        return this.mSessionData.isPaused();
    }

    public void runOnUiThread(Runnable runnable) {
        this.mActivity.runOnUiThread(runnable);
    }

    public NeoGLView getGLView() {
        return this.mSessionData.getGlView();
    }

    public Window getWindow() {
        Window window = this.mActivity.getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        return window;
    }

    public WindowManager getWindowManager() {
        WindowManager windowManager = this.mActivity.getWindowManager();
        if (windowManager == null) {
            Intrinsics.throwNpe();
        }
        return windowManager;
    }

    public void showScreenKeyboardWithoutTextInputField(int i) {
        Object systemService = this.mActivity.getSystemService("input_method");
        if (systemService != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            if (!isKeyboardWithoutTextInputShown()) {
                this.mSessionData.setKeyboardWithoutTextInputShown(true);
                runOnUiThread(new XSession$showScreenKeyboardWithoutTextInputField$1(this, i, inputMethodManager));
            } else {
                this.mSessionData.setKeyboardWithoutTextInputShown(false);
                runOnUiThread(new XSession$showScreenKeyboardWithoutTextInputField$2(this, inputMethodManager));
            }
            NeoGLView gLView = getGLView();
            if (gLView == null) {
                Intrinsics.throwNpe();
            }
            gLView.callNativeScreenKeyboardShown(isKeyboardWithoutTextInputShown() ? 1 : 0);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
    }

    public void setScreenKeyboardHintMessage(String str) {
        this.mSessionData.setScreenKeyboardHintMessage(str);
        if (this.mSessionData.getScreenKeyboard() instanceof EditText) {
            runOnUiThread(new XSession$setScreenKeyboardHintMessage$1(this, str));
        }
    }

    public boolean isScreenKeyboardShown() {
        return this.mSessionData.getScreenKeyboard() != null;
    }

    public void showScreenKeyboard(String str) {
        if (Globals.CompatibilityHacksTextInputEmulatesHwKeyboard) {
            showScreenKeyboardWithoutTextInputField(Globals.TextInputKeyboard);
        } else if (this.mSessionData.getScreenKeyboard() == null) {
            EditText editText = new EditText(this.mActivity, (AttributeSet) null, Build.VERSION.SDK_INT >= 21 ? 16974360 : 16973900);
            String screenKeyboardHintMessage = this.mSessionData.getScreenKeyboardHintMessage();
            if (screenKeyboardHintMessage == null) {
                screenKeyboardHintMessage = this.mActivity.getString(R.string.text_edit_click_here);
            }
            editText.setHint(screenKeyboardHintMessage);
            editText.setText(str);
            editText.setSelection(editText.getText().length());
            editText.setOnKeyListener(new SimpleKeyListener(this));
            editText.setBackgroundColor(this.mActivity.getResources().getColor(17170435));
            editText.setTextColor(this.mActivity.getResources().getColor(17170447));
            if (isRunningOnOUYA() && Globals.TvBorders) {
                editText.setPadding(100, 100, 100, 100);
            }
            this.mSessionData.setScreenKeyboard(editText);
            FrameLayout videoLayout = this.mSessionData.getVideoLayout();
            if (videoLayout == null) {
                Intrinsics.throwNpe();
            }
            videoLayout.addView(this.mSessionData.getScreenKeyboard());
            editText.setInputType(1);
            editText.setFocusableInTouchMode(true);
            editText.setFocusable(true);
            editText.postDelayed(new XSession$showScreenKeyboard$1(editText), 300);
        }
    }

    public void hideScreenKeyboard() {
        Object systemService = this.mActivity.getSystemService("input_method");
        if (systemService != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            if (isKeyboardWithoutTextInputShown()) {
                showScreenKeyboardWithoutTextInputField(Globals.TextInputKeyboard);
            }
            if (this.mSessionData.getScreenKeyboard() != null && (this.mSessionData.getScreenKeyboard() instanceof EditText)) {
                synchronized (this.mSessionData.getTextInput()) {
                    View screenKeyboard = this.mSessionData.getScreenKeyboard();
                    if (screenKeyboard != null) {
                        String obj = ((EditText) screenKeyboard).getText().toString();
                        int length = obj.length();
                        int i = 0;
                        while (i < length) {
                            char charAt = obj.charAt(i);
                            if (obj != null) {
                                NeoRenderer.callNativeTextInput(charAt, obj.codePointAt(i));
                                i++;
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
                    }
                }
                NeoRenderer.callNativeTextInputFinished();
                View screenKeyboard2 = this.mSessionData.getScreenKeyboard();
                if (screenKeyboard2 == null) {
                    Intrinsics.throwNpe();
                }
                inputMethodManager.hideSoftInputFromWindow(screenKeyboard2.getWindowToken(), 0);
                FrameLayout videoLayout = this.mSessionData.getVideoLayout();
                if (videoLayout == null) {
                    Intrinsics.throwNpe();
                }
                videoLayout.removeView(this.mSessionData.getScreenKeyboard());
                this.mSessionData.setScreenKeyboard((View) null);
                NeoGLView gLView = getGLView();
                if (gLView == null) {
                    Intrinsics.throwNpe();
                }
                gLView.setFocusableInTouchMode(true);
                NeoGLView gLView2 = getGLView();
                if (gLView2 == null) {
                    Intrinsics.throwNpe();
                }
                gLView2.setFocusable(true);
                NeoGLView gLView3 = getGLView();
                if (gLView3 == null) {
                    Intrinsics.throwNpe();
                }
                gLView3.requestFocus();
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
    }

    public void updateScreenOrientation() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Intrinsics.checkExpressionValueIsNotNull(defaultDisplay, "windowManager.defaultDisplay");
        int rotation = defaultDisplay.getRotation();
        NeoAccelerometerReader.setGyroInvertedOrientation(rotation == 2 || rotation == 3);
    }

    public void initScreenOrientation() {
        Globals.AutoDetectOrientation = true;
        if (Globals.AutoDetectOrientation) {
            this.mActivity.setRequestedOrientation(13);
        } else {
            this.mActivity.setRequestedOrientation(Globals.HorizontalOrientation ? 6 : 7);
        }
    }

    public boolean isRunningOnOUYA() {
        try {
            this.mActivity.getPackageManager().getPackageInfo("tv.ouya", 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            UiModeManager uiModeManager = (UiModeManager) this.mActivity.getSystemService("uimode");
            if ((uiModeManager == null || uiModeManager.getCurrentModeType() != 4) && !Globals.OuyaEmulation) {
                return false;
            }
            return true;
        }
    }

    public void setSystemMousePointerVisible(int i) {
        NeoGLView gLView;
        if (Build.VERSION.SDK_INT >= 24 && (gLView = getGLView()) != null) {
            gLView.setPointerIcon(PointerIcon.getSystemIcon(this.mActivity, i == 0 ? 0 : 1000));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession$SimpleKeyListener;", "Landroid/view/View$OnKeyListener;", "client", "Lcom/thecrackertechnology/dragonterminal/xorg/NeoXorgViewClient;", "(Lcom/thecrackertechnology/dragonterminal/xorg/NeoXorgViewClient;)V", "getClient", "()Lcom/thecrackertechnology/dragonterminal/xorg/NeoXorgViewClient;", "setClient", "onKey", "", "v", "Landroid/view/View;", "keyCode", "", "event", "Landroid/view/KeyEvent;", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: XSession.kt */
    public static final class SimpleKeyListener implements View.OnKeyListener {
        private NeoXorgViewClient client;

        public SimpleKeyListener(NeoXorgViewClient neoXorgViewClient) {
            Intrinsics.checkParameterIsNotNull(neoXorgViewClient, "client");
            this.client = neoXorgViewClient;
        }

        public final NeoXorgViewClient getClient() {
            return this.client;
        }

        public final void setClient(NeoXorgViewClient neoXorgViewClient) {
            Intrinsics.checkParameterIsNotNull(neoXorgViewClient, "<set-?>");
            this.client = neoXorgViewClient;
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            Intrinsics.checkParameterIsNotNull(view, "v");
            Intrinsics.checkParameterIsNotNull(keyEvent, NotificationCompat.CATEGORY_EVENT);
            if (keyEvent.getAction() != 1) {
                return false;
            }
            if (i != 66 && i != 4 && i != 82 && i != 96 && i != 97 && i != 99 && i != 100 && i != 188 && i != 189 && i != 190 && i != 191) {
                return false;
            }
            this.client.hideScreenKeyboard();
            return true;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0012J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010!\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession$BuiltInKeyboardView;", "Landroid/inputmethodservice/KeyboardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "alt", "", "getAlt", "()Z", "setAlt", "(Z)V", "shift", "getShift", "setShift", "stickyKeys", "Ljava/util/TreeSet;", "", "getStickyKeys", "()Ljava/util/TreeSet;", "setStickyKeys", "(Ljava/util/TreeSet;)V", "changeKeyboard", "", "keyboardIndex", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "onKeyDown", "key", "event", "Landroid/view/KeyEvent;", "onKeyUp", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: XSession.kt */
    public static final class BuiltInKeyboardView extends KeyboardView {
        private HashMap _$_findViewCache;
        private boolean alt;
        private boolean shift;
        private TreeSet<Integer> stickyKeys = new TreeSet<>();

        public void _$_clearFindViewByIdCache() {
            HashMap hashMap = this._$_findViewCache;
            if (hashMap != null) {
                hashMap.clear();
            }
        }

        public View _$_findCachedViewById(int i) {
            if (this._$_findViewCache == null) {
                this._$_findViewCache = new HashMap();
            }
            View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
            if (view != null) {
                return view;
            }
            View findViewById = findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }

        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            Intrinsics.checkParameterIsNotNull(keyEvent, NotificationCompat.CATEGORY_EVENT);
            return false;
        }

        public boolean onKeyUp(int i, KeyEvent keyEvent) {
            Intrinsics.checkParameterIsNotNull(keyEvent, NotificationCompat.CATEGORY_EVENT);
            return false;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BuiltInKeyboardView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            Intrinsics.checkParameterIsNotNull(context, "context");
        }

        public final boolean getShift() {
            return this.shift;
        }

        public final void setShift(boolean z) {
            this.shift = z;
        }

        public final boolean getAlt() {
            return this.alt;
        }

        public final void setAlt(boolean z) {
            this.alt = z;
        }

        public final TreeSet<Integer> getStickyKeys() {
            return this.stickyKeys;
        }

        public final void setStickyKeys(TreeSet<Integer> treeSet) {
            Intrinsics.checkParameterIsNotNull(treeSet, "<set-?>");
            this.stickyKeys = treeSet;
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            Intrinsics.checkParameterIsNotNull(motionEvent, "ev");
            if (motionEvent.getY() < ((float) getTop())) {
                return false;
            }
            if (motionEvent.getAction() == 0 || motionEvent.getAction() == 1 || motionEvent.getAction() == 2) {
                return super.dispatchTouchEvent(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getX(), motionEvent.getY() - ((float) getTop()), motionEvent.getMetaState()));
            }
            return false;
        }

        public final void changeKeyboard(int i) {
            boolean z = this.shift;
            Keyboard keyboard = new Keyboard(getContext(), NeoTextInput.TextInputKeyboardList[(z ? 1 : 0) + (this.alt ? 2 : 0)][i]);
            setPreviewEnabled(false);
            setProximityCorrectionEnabled(false);
            for (Keyboard.Key next : keyboard.getKeys()) {
                if (this.stickyKeys.contains(Integer.valueOf(next.codes[0]))) {
                    next.on = true;
                    invalidateAllKeys();
                }
            }
        }
    }
}
