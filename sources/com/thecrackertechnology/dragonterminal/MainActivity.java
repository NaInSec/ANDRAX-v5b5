package com.thecrackertechnology.dragonterminal;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannedString;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;
import com.thecrackertechnology.dragonterminal.xorg.R;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

public class MainActivity extends Activity implements NeoXorgViewClient {
    static final int ADVERTISEMENT_POSITION_BOTTOM = -1;
    static final int ADVERTISEMENT_POSITION_CENTER = -2;
    static final int ADVERTISEMENT_POSITION_RIGHT = -1;
    public static boolean ApplicationLibraryLoaded = false;
    public static MainActivity instance = null;
    static boolean keyboardWithoutTextInputShown = false;
    /* access modifiers changed from: private */
    public static AudioThread mAudioThread;
    /* access modifiers changed from: private */
    public int[][] TextInputKeyboardList = {new int[]{0, R.xml.qwerty, R.xml.c64, R.xml.amiga, R.xml.atari800}, new int[]{0, R.xml.qwerty_shift, R.xml.c64, R.xml.amiga_shift, R.xml.atari800}, new int[]{0, R.xml.qwerty_alt, R.xml.c64, R.xml.amiga_alt, R.xml.atari800}, new int[]{0, R.xml.qwerty_alt_shift, R.xml.c64, R.xml.amiga_alt_shift, R.xml.atari800}};
    /* access modifiers changed from: private */
    public Button _btn = null;
    /* access modifiers changed from: private */
    public InputMethodManager _inputManager = null;
    boolean _isPaused = false;
    private LinearLayout _layout = null;
    private LinearLayout _layout2 = null;
    /* access modifiers changed from: private */
    public View _screenKeyboard = null;
    /* access modifiers changed from: private */
    public String _screenKeyboardHintMessage = null;
    /* access modifiers changed from: private */
    public TextView _tv = null;
    FrameLayout _videoLayout = null;
    public ProgressDialog loadingDialog = null;
    NeoGLView mGLView = null;
    private boolean sdlInited = false;
    public LinkedList<Integer> textInput = new LinkedList<>();
    public boolean writeExternalStoragePermissionDialogAnswered = false;

    public Context getContext() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        instance = this;
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        if (Globals.InhibitSuspend) {
            getWindow().setFlags(128, 128);
        }
        Log.i("SDL", "libSDL: Creating startup screen");
        this._layout = new LinearLayout(this);
        this._layout.setOrientation(1);
        this._layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this._layout2 = new LinearLayout(this);
        this._layout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.loadingDialog = new ProgressDialog(this);
        this.loadingDialog.setMessage(getString(R.string.accessing_network));
        Semaphore semaphore = new Semaphore(0);
        if (Globals.StartupMenuButtonTimeout > 0) {
            this._btn = new Button(this);
            this._btn.setEnabled(false);
            this._btn.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            this._btn.setText(getResources().getString(R.string.device_change_cfg));
            this._btn.setOnClickListener(new View.OnClickListener(this, semaphore) {
                public MainActivity p;
                final /* synthetic */ Semaphore val$loadedLibraries;

                {
                    this.val$loadedLibraries = r3;
                    this.p = r2;
                }

                public void onClick(View view) {
                    MainActivity.this.setUpStatusLabel();
                    Log.i("SDL", "libSDL: User clicked change phone config button");
                    this.val$loadedLibraries.acquireUninterruptibly();
                    MainActivity.this.setScreenOrientation();
                    SettingsMenu.showConfig(this.p, false);
                }
            });
            this._layout2.addView(this._btn);
        }
        this._layout.addView(this._layout2);
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        try {
            imageView.setImageDrawable(Drawable.createFromStream(getAssets().open("logo.png"), "logo.png"));
        } catch (Exception unused) {
            imageView.setImageResource(R.drawable.publisherlogo);
        }
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this._layout.addView(imageView);
        this._videoLayout = new FrameLayout(this);
        this._videoLayout.addView(this._layout);
        setContentView(this._videoLayout);
        this._videoLayout.setFocusable(true);
        this._videoLayout.setFocusableInTouchMode(true);
        this._videoLayout.requestFocus();
        new Thread(new Runnable(this, semaphore) {
            MainActivity p;
            final /* synthetic */ Semaphore val$loadedLibraries;

            {
                this.val$loadedLibraries = r3;
                this.p = r2;
            }

            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException unused) {
                }
                MainActivity mainActivity = this.p;
                if (MainActivity.mAudioThread == null) {
                    Log.i("SDL", "libSDL: Loading libraries");
                    this.p.LoadLibraries();
                    AudioThread unused2 = MainActivity.mAudioThread = new AudioThread(this.p);
                    Log.i("SDL", "libSDL: Loading settings");
                    final Semaphore semaphore = new Semaphore(0);
                    AnonymousClass1Callback2 r2 = new Runnable() {
                        public MainActivity Parent;

                        public void run() {
                            Settings.Load(this.Parent);
                            MainActivity.this.setScreenOrientation();
                            semaphore.release();
                            AnonymousClass1Callback.this.val$loadedLibraries.release();
                            if (MainActivity.this._btn != null) {
                                MainActivity.this._btn.setEnabled(true);
                                MainActivity.this._btn.setFocusable(true);
                                MainActivity.this._btn.setFocusableInTouchMode(true);
                                MainActivity.this._btn.requestFocus();
                            }
                        }
                    };
                    MainActivity mainActivity2 = this.p;
                    r2.Parent = mainActivity2;
                    mainActivity2.runOnUiThread(r2);
                    semaphore.acquireUninterruptibly();
                    if (!Globals.CompatibilityHacksStaticInit) {
                        MainActivity.LoadApplicationLibrary(this.p);
                    }
                }
                if (!Settings.settingsChanged) {
                    if (Globals.StartupMenuButtonTimeout > 0) {
                        Log.i("SDL", "libSDL: " + String.valueOf(Globals.StartupMenuButtonTimeout) + "-msec timeout in startup screen");
                        try {
                            Thread.sleep((long) Globals.StartupMenuButtonTimeout);
                        } catch (InterruptedException unused3) {
                        }
                    }
                    if (Settings.settingsChanged) {
                    }
                }
            }
        }).start();
    }

    public void setUpStatusLabel() {
        Button button = this._btn;
        if (button != null) {
            this._layout2.removeView(button);
            this._btn = null;
        }
        if (this._tv == null) {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            this._tv = new TextView(this);
            this._tv.setMaxLines(2);
            this._tv.setMinLines(2);
            this._tv.setText(R.string.init);
            int i = (int) (((double) width) * 0.1d);
            this._tv.setPadding(i, (int) (((double) height) * 0.1d), i, 0);
            this._layout2.addView(this._tv);
        }
    }

    public void initSDL() {
        setScreenOrientation();
        updateScreenOrientation();
        DimSystemStatusBar.get().dim(this._videoLayout);
        new Thread(new Runnable() {
            public void run() {
                if (Globals.AutoDetectOrientation) {
                    Globals.HorizontalOrientation = MainActivity.this.isCurrentOrientationHorizontal();
                }
                while (true) {
                    if (MainActivity.this.isCurrentOrientationHorizontal() != Globals.HorizontalOrientation || ((KeyguardManager) MainActivity.this.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("libSDL: Waiting for screen orientation to change to ");
                        sb.append(Globals.HorizontalOrientation ? "landscape" : "portrait");
                        sb.append(", and for disabling lockscreen mode");
                        Log.d("SDL", sb.toString());
                        try {
                            Thread.sleep(500);
                        } catch (Exception unused) {
                        }
                        if (MainActivity.this._isPaused) {
                            Log.i("SDL", "libSDL: Application paused, cancelling SDL initialization until it will be brought to foreground");
                            return;
                        }
                        DimSystemStatusBar.get().dim(MainActivity.this._videoLayout);
                    } else {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                DisplayMetrics displayMetrics = new DisplayMetrics();
                                MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                                if (Globals.ImmersiveMode && !(MainActivity.this._videoLayout.getHeight() == displayMetrics.widthPixels && MainActivity.this._videoLayout.getWidth() == displayMetrics.heightPixels)) {
                                    DimSystemStatusBar.get().dim(MainActivity.this._videoLayout);
                                    try {
                                        Thread.sleep(300);
                                    } catch (Exception unused) {
                                    }
                                }
                                MainActivity.this.initSDLInternal();
                            }
                        });
                        return;
                    }
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void initSDLInternal() {
        if (!this.sdlInited) {
            Log.i("SDL", "libSDL: Initializing video and SDL application");
            this.sdlInited = true;
            DimSystemStatusBar.get().dim(this._videoLayout);
            this._videoLayout.removeView(this._layout);
            this._layout = null;
            this._layout2 = null;
            this._btn = null;
            this._tv = null;
            this._inputManager = (InputMethodManager) getSystemService("input_method");
            this._videoLayout = new FrameLayout(this);
            SetLayerType.get().setLayerType(this._videoLayout);
            setContentView(this._videoLayout);
            this.mGLView = new NeoGLView(this);
            SetLayerType.get().setLayerType(this.mGLView);
            if (!isRunningOnOUYA() || !Globals.TvBorders) {
                this._videoLayout.addView(this.mGLView, new FrameLayout.LayoutParams(-1, -1));
            } else {
                RelativeLayout relativeLayout = new RelativeLayout(this);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getResources().getDimensionPixelOffset(R.dimen.screen_border_horizontal), -1);
                layoutParams.addRule(9, -1);
                layoutParams.addRule(10, -1);
                ImageView imageView = new ImageView(this);
                imageView.setId(R.id.left);
                imageView.setImageResource(R.drawable.tv_border_left);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                relativeLayout.addView(imageView, layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(getResources().getDimensionPixelOffset(R.dimen.screen_border_horizontal), -1);
                layoutParams2.addRule(11, -1);
                layoutParams2.addRule(10, -1);
                ImageView imageView2 = new ImageView(this);
                imageView2.setId(R.id.right);
                imageView2.setImageResource(R.drawable.tv_border_left);
                imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView2.setScaleX(-1.0f);
                relativeLayout.addView(imageView2, layoutParams2);
                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.screen_border_vertical));
                layoutParams3.addRule(10, -1);
                layoutParams3.addRule(1, imageView.getId());
                layoutParams3.addRule(0, imageView2.getId());
                ImageView imageView3 = new ImageView(this);
                imageView3.setId(R.id.top);
                imageView3.setImageResource(R.drawable.tv_border_top);
                imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
                relativeLayout.addView(imageView3, layoutParams3);
                RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.screen_border_vertical));
                layoutParams4.addRule(12, -1);
                layoutParams4.addRule(1, imageView.getId());
                layoutParams4.addRule(0, imageView2.getId());
                ImageView imageView4 = new ImageView(this);
                imageView4.setId(R.id.bottom);
                imageView4.setImageResource(R.drawable.tv_border_top);
                imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView4.setScaleY(-1.0f);
                relativeLayout.addView(imageView4, layoutParams4);
                RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams5.addRule(1, imageView.getId());
                layoutParams5.addRule(0, imageView2.getId());
                layoutParams5.addRule(3, imageView3.getId());
                layoutParams5.addRule(2, imageView4.getId());
                this.mGLView.setLayoutParams(layoutParams5);
                relativeLayout.addView(this.mGLView);
                this._videoLayout.addView(relativeLayout, new FrameLayout.LayoutParams(-1, -1));
            }
            this.mGLView.setFocusableInTouchMode(true);
            this.mGLView.setFocusable(true);
            this.mGLView.requestFocus();
            if (Globals.HideSystemMousePointer && Build.VERSION.SDK_INT >= 24) {
                this.mGLView.setPointerIcon(PointerIcon.getSystemIcon(this, 0));
            }
            DimSystemStatusBar.get().dim(this._videoLayout);
            Rect rect = new Rect();
            this._videoLayout.getWindowVisibleDisplayFrame(rect);
            NeoGLView neoGLView = this.mGLView;
            NeoGLView.nativeScreenVisibleRect(rect.left, rect.top, rect.right, rect.bottom);
            this._videoLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    final Rect rect = new Rect();
                    MainActivity.this._videoLayout.getWindowVisibleDisplayFrame(rect);
                    final int height = MainActivity.this._videoLayout.getRootView().getHeight() - MainActivity.this._videoLayout.getHeight();
                    final int width = MainActivity.this._videoLayout.getRootView().getWidth() - MainActivity.this._videoLayout.getWidth();
                    Log.v("SDL", "Main window visible region changed: " + rect.left + ":" + rect.top + ":" + rect.width() + ":" + rect.height());
                    MainActivity.this._videoLayout.postDelayed(new Runnable() {
                        public void run() {
                            DimSystemStatusBar.get().dim(MainActivity.this._videoLayout);
                            NeoGLView neoGLView = MainActivity.this.mGLView;
                            NeoGLView.nativeScreenVisibleRect(rect.left + width, rect.top + height, rect.width(), rect.height());
                        }
                    }, 300);
                    MainActivity.this._videoLayout.postDelayed(new Runnable() {
                        public void run() {
                            DimSystemStatusBar.get().dim(MainActivity.this._videoLayout);
                            NeoGLView neoGLView = MainActivity.this.mGLView;
                            NeoGLView.nativeScreenVisibleRect(rect.left + width, rect.top + height, rect.width(), rect.height());
                        }
                    }, 600);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this._isPaused = true;
        NeoGLView neoGLView = this.mGLView;
        if (neoGLView != null) {
            neoGLView.onPause();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mGLView != null) {
            DimSystemStatusBar.get().dim(this._videoLayout);
            this.mGLView.onResume();
        }
        this._isPaused = false;
        Intent intent = new Intent("com.nvidia.intent.action.ENABLE_STYLUS");
        intent.putExtra("package", getPackageName());
        sendBroadcast(intent);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Log.i("SDL", "libSDL: onWindowFocusChanged: " + z + " - sending onPause/onResume");
        if (!z) {
            onPause();
        } else {
            onResume();
        }
    }

    public boolean isPaused() {
        return this._isPaused;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        NeoGLView neoGLView = this.mGLView;
        if (neoGLView != null) {
            neoGLView.exitApp();
        }
        super.onDestroy();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException unused) {
        }
        System.exit(0);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public boolean isKeyboardWithoutTextInputShown() {
        return keyboardWithoutTextInputShown;
    }

    public void showScreenKeyboardWithoutTextInputField(final int i) {
        if (!isKeyboardWithoutTextInputShown()) {
            keyboardWithoutTextInputShown = true;
            runOnUiThread(new Runnable() {
                public void run() {
                    if (i == 0) {
                        MainActivity.this._inputManager.toggleSoftInput(2, 0);
                        MainActivity.this._inputManager.showSoftInput(MainActivity.this.mGLView, 2);
                        MainActivity.this.getWindow().setSoftInputMode(4);
                    } else if (MainActivity.this._screenKeyboard == null) {
                        final AnonymousClass1BuiltInKeyboardView r0 = new KeyboardView(MainActivity.this, (AttributeSet) null) {
                            public boolean alt = false;
                            public boolean shift = false;
                            public TreeSet<Integer> stickyKeys = new TreeSet<>();

                            public boolean onKeyDown(int i, KeyEvent keyEvent) {
                                return false;
                            }

                            public boolean onKeyUp(int i, KeyEvent keyEvent) {
                                return false;
                            }

                            public boolean dispatchTouchEvent(MotionEvent motionEvent) {
                                if (motionEvent.getY() < ((float) getTop())) {
                                    return false;
                                }
                                if (motionEvent.getAction() == 0 || motionEvent.getAction() == 1 || motionEvent.getAction() == 2) {
                                    return super.dispatchTouchEvent(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getX(), motionEvent.getY() - ((float) getTop()), motionEvent.getMetaState()));
                                }
                                return false;
                            }

                            public void ChangeKeyboard() {
                                boolean z = this.shift;
                                setKeyboard(new Keyboard(MainActivity.this, MainActivity.this.TextInputKeyboardList[(z ? 1 : 0) + (this.alt ? 2 : 0)][i]));
                                setPreviewEnabled(false);
                                setProximityCorrectionEnabled(false);
                                for (Keyboard.Key next : getKeyboard().getKeys()) {
                                    if (this.stickyKeys.contains(Integer.valueOf(next.codes[0]))) {
                                        next.on = true;
                                        invalidateAllKeys();
                                    }
                                }
                            }
                        };
                        r0.setAlpha(0.7f);
                        r0.ChangeKeyboard();
                        r0.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
                            public void onKey(int i, int[] iArr) {
                            }

                            public void onText(CharSequence charSequence) {
                            }

                            public void swipeDown() {
                            }

                            public void swipeLeft() {
                            }

                            public void swipeRight() {
                            }

                            public void swipeUp() {
                            }

                            public void onPress(int i) {
                                if (i != 4 && i >= 0) {
                                    for (Keyboard.Key next : r0.getKeyboard().getKeys()) {
                                        if (next.sticky && i == next.codes[0]) {
                                            return;
                                        }
                                    }
                                    if (i > 100000) {
                                        i -= 100000;
                                        MainActivity.this.onKeyDown(59, new KeyEvent(0, 59));
                                    }
                                    MainActivity.this.onKeyDown(i, new KeyEvent(0, i));
                                }
                            }

                            public void onRelease(int i) {
                                boolean z;
                                if (i == 4) {
                                    r0.setOnKeyboardActionListener((KeyboardView.OnKeyboardActionListener) null);
                                    MainActivity.this.showScreenKeyboardWithoutTextInputField(0);
                                } else if (i == -1) {
                                    AnonymousClass1BuiltInKeyboardView r7 = r0;
                                    r7.shift = !r7.shift;
                                    if (!r0.shift || r0.alt) {
                                        MainActivity.this.onKeyUp(59, new KeyEvent(1, 59));
                                    } else {
                                        MainActivity.this.onKeyDown(59, new KeyEvent(0, 59));
                                    }
                                    r0.ChangeKeyboard();
                                } else if (i == -6) {
                                    AnonymousClass1BuiltInKeyboardView r72 = r0;
                                    r72.alt = !r72.alt;
                                    if (r0.alt) {
                                        MainActivity.this.onKeyUp(59, new KeyEvent(1, 59));
                                    } else {
                                        r0.shift = false;
                                    }
                                    r0.ChangeKeyboard();
                                } else if (i >= 0) {
                                    for (Keyboard.Key next : r0.getKeyboard().getKeys()) {
                                        if (next.sticky && i == next.codes[0]) {
                                            if (next.on) {
                                                r0.stickyKeys.add(Integer.valueOf(i));
                                                MainActivity.this.onKeyDown(i, new KeyEvent(0, i));
                                                return;
                                            }
                                            r0.stickyKeys.remove(Integer.valueOf(i));
                                            MainActivity.this.onKeyUp(i, new KeyEvent(1, i));
                                            return;
                                        }
                                    }
                                    if (i > 100000) {
                                        i -= 100000;
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    MainActivity.this.onKeyUp(i, new KeyEvent(1, i));
                                    if (z) {
                                        MainActivity.this.onKeyUp(59, new KeyEvent(1, 59));
                                        r0.stickyKeys.remove(59);
                                        for (Keyboard.Key next2 : r0.getKeyboard().getKeys()) {
                                            if (next2.sticky && next2.codes[0] == 59 && next2.on) {
                                                next2.on = false;
                                                r0.invalidateAllKeys();
                                            }
                                        }
                                    }
                                }
                            }
                        });
                        View unused = MainActivity.this._screenKeyboard = r0;
                        MainActivity.this._videoLayout.addView(MainActivity.this._screenKeyboard, new FrameLayout.LayoutParams(-1, -2, 80));
                    }
                }
            });
        } else {
            keyboardWithoutTextInputShown = false;
            runOnUiThread(new Runnable() {
                public void run() {
                    if (MainActivity.this._screenKeyboard != null) {
                        MainActivity.this._videoLayout.removeView(MainActivity.this._screenKeyboard);
                        View unused = MainActivity.this._screenKeyboard = null;
                    }
                    MainActivity.this.getWindow().setSoftInputMode(2);
                    MainActivity.this._inputManager.hideSoftInputFromWindow(MainActivity.this.mGLView.getWindowToken(), 0);
                    DimSystemStatusBar.get().dim(MainActivity.this._videoLayout);
                }
            });
        }
        NeoGLView neoGLView = this.mGLView;
        NeoGLView.nativeScreenKeyboardShown(keyboardWithoutTextInputShown ? 1 : 0);
    }

    public void showScreenKeyboard(String str) {
        if (Globals.CompatibilityHacksTextInputEmulatesHwKeyboard) {
            showScreenKeyboardWithoutTextInputField(Globals.TextInputKeyboard);
        } else if (this._screenKeyboard == null) {
            final EditText editText = new EditText(this, (AttributeSet) null, Build.VERSION.SDK_INT >= 21 ? 16974360 : 16973900);
            String str2 = this._screenKeyboardHintMessage;
            if (str2 == null) {
                str2 = getString(R.string.text_edit_click_here);
            }
            editText.setHint(str2);
            editText.setText(str);
            editText.setSelection(editText.getText().length());
            editText.setOnKeyListener(new View.OnKeyListener(this) {
                MainActivity _parent;

                {
                    this._parent = r2;
                }

                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() != 1) {
                        return false;
                    }
                    if (i != 66 && i != 4 && i != 82 && i != 96 && i != 97 && i != 99 && i != 100 && i != 188 && i != 189 && i != 190 && i != 191) {
                        return false;
                    }
                    this._parent.hideScreenKeyboard();
                    return true;
                }
            });
            editText.setBackgroundColor(getResources().getColor(17170435));
            editText.setTextColor(getResources().getColor(17170447));
            if (isRunningOnOUYA() && Globals.TvBorders) {
                editText.setPadding(100, 100, 100, 100);
            }
            this._screenKeyboard = editText;
            this._videoLayout.addView(this._screenKeyboard);
            editText.setInputType(1);
            editText.setFocusableInTouchMode(true);
            editText.setFocusable(true);
            editText.postDelayed(new Runnable() {
                public void run() {
                    editText.requestFocus();
                    editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0f, 0.0f, 0));
                    editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0f, 0.0f, 0));
                    editText.postDelayed(new Runnable() {
                        public void run() {
                            editText.requestFocus();
                            editText.setSelection(editText.getText().length());
                        }
                    }, 100);
                }
            }, 300);
        }
    }

    public void hideScreenKeyboard() {
        if (keyboardWithoutTextInputShown) {
            showScreenKeyboardWithoutTextInputField(Globals.TextInputKeyboard);
        }
        View view = this._screenKeyboard;
        if (view != null && (view instanceof EditText)) {
            synchronized (this.textInput) {
                String obj = ((EditText) this._screenKeyboard).getText().toString();
                for (int i = 0; i < obj.length(); i++) {
                    DemoRenderer.nativeTextInput(obj.charAt(i), obj.codePointAt(i));
                }
            }
            DemoRenderer.nativeTextInputFinished();
            this._inputManager.hideSoftInputFromWindow(this._screenKeyboard.getWindowToken(), 0);
            this._videoLayout.removeView(this._screenKeyboard);
            this._screenKeyboard = null;
            this.mGLView.setFocusableInTouchMode(true);
            this.mGLView.setFocusable(true);
            this.mGLView.requestFocus();
            DimSystemStatusBar.get().dim(this._videoLayout);
            this._videoLayout.postDelayed(new Runnable() {
                public void run() {
                    DimSystemStatusBar.get().dim(MainActivity.this._videoLayout);
                }
            }, 500);
        }
    }

    public boolean isScreenKeyboardShown() {
        return this._screenKeyboard != null;
    }

    public void setScreenKeyboardHintMessage(String str) {
        this._screenKeyboardHintMessage = str;
        runOnUiThread(new Runnable() {
            public void run() {
                if (MainActivity.this._screenKeyboard != null && (MainActivity.this._screenKeyboard instanceof EditText)) {
                    String access$600 = MainActivity.this._screenKeyboardHintMessage;
                    EditText editText = (EditText) MainActivity.this._screenKeyboard;
                    if (access$600 == null) {
                        access$600 = MainActivity.this.getString(R.string.text_edit_click_here);
                    }
                    editText.setHint(access$600);
                }
            }
        });
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateScreenOrientation();
    }

    public void updateScreenOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        AccelerometerReader.gyro.invertedOrientation = rotation == 2 || rotation == 3;
    }

    public void initScreenOrientation() {
        setScreenOrientation();
    }

    public void setText(String str) {
        AnonymousClass2Callback r0 = new Runnable() {
            MainActivity Parent;
            public SpannedString text;

            public void run() {
                this.Parent.setUpStatusLabel();
                if (this.Parent._tv != null) {
                    this.Parent._tv.setText(this.text);
                }
            }
        };
        r0.text = new SpannedString(str);
        r0.Parent = this;
        runOnUiThread(r0);
    }

    public void onNewIntent(Intent intent) {
        Log.i("SDL", "onNewIntent(): " + intent.toString());
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public void LoadLibraries() {
        try {
            if (Globals.NeedGles3) {
                System.loadLibrary("GLESv3");
                Log.i("SDL", "libSDL: loaded GLESv3 lib");
            } else if (Globals.NeedGles2) {
                System.loadLibrary("GLESv2");
                Log.i("SDL", "libSDL: loaded GLESv2 lib");
            }
        } catch (UnsatisfiedLinkError unused) {
            Log.i("SDL", "libSDL: Cannot load GLESv3 or GLESv2 lib");
        }
        try {
            for (String str : Globals.XLIBS) {
                String str2 = Globals.XLIB_DIR + str;
                Log.i("SDL", "libSDL: loading lib " + str2);
                try {
                    System.load(str2);
                } catch (UnsatisfiedLinkError e) {
                    Log.i("SDL", "libSDL: error loading lib " + str2 + ", reason: " + e.getLocalizedMessage());
                }
            }
        } catch (UnsatisfiedLinkError unused2) {
        }
    }

    public static void LoadApplicationLibrary(Context context) {
        Settings.nativeChdir(Globals.DataDir);
        try {
            for (String str : Globals.XAPP_LIBS) {
                String str2 = Globals.XLIB_DIR + str;
                Log.i("SDL", "libSDL: loading lib " + str2);
                try {
                    System.load(str2);
                } catch (UnsatisfiedLinkError e) {
                    Log.i("SDL", "libSDL: error loading lib " + str2 + ", reason: " + e.getLocalizedMessage());
                }
            }
        } catch (UnsatisfiedLinkError unused) {
        }
        Log.v("SDL", "libSDL: loaded all libraries");
        ApplicationLibraryLoaded = true;
    }

    public int getApplicationVersion() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("SDL", "libSDL: Cannot get the version of our own package: " + e);
            return 0;
        }
    }

    public boolean isRunningOnOUYA() {
        try {
            getPackageManager().getPackageInfo("tv.ouya", 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            if (((UiModeManager) getSystemService("uimode")).getCurrentModeType() == 4 || Globals.OuyaEmulation) {
                return true;
            }
            return false;
        }
    }

    public NeoGLView getGLView() {
        return this.mGLView;
    }

    public boolean isCurrentOrientationHorizontal() {
        View peekDecorView;
        if (!Globals.AutoDetectOrientation || (peekDecorView = getWindow().peekDecorView()) == null) {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            if (defaultDisplay.getWidth() >= defaultDisplay.getHeight()) {
                return true;
            }
            return false;
        } else if (peekDecorView.getWidth() >= peekDecorView.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void setScreenOrientation() {
        Globals.AutoDetectOrientation = true;
        if (Globals.AutoDetectOrientation) {
            setRequestedOrientation(13);
        } else {
            setRequestedOrientation(Globals.HorizontalOrientation ? 6 : 7);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        String str;
        if (strArr.length == 0 || iArr.length == 0) {
            Log.i("SDL", "libSDL: Permission request dialog was aborted");
            return;
        }
        String str2 = "GRANTED";
        if ("android.permission.RECORD_AUDIO".equals(strArr[0])) {
            StringBuilder sb = new StringBuilder();
            sb.append("libSDL: Record audio permission: ");
            if (iArr[0] == 0) {
                str = str2;
            } else {
                str = "DENIED";
            }
            sb.append(str);
            Log.i("SDL", sb.toString());
        }
        if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(strArr[0])) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("libSDL: Write external storage permission: ");
            if (iArr[0] != 0) {
                str2 = "DENIED";
            }
            sb2.append(str2);
            Log.i("SDL", sb2.toString());
            this.writeExternalStoragePermissionDialogAnswered = true;
        }
    }

    public void setSystemMousePointerVisible(int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.mGLView.setPointerIcon(PointerIcon.getSystemIcon(this, i == 0 ? 0 : 1000));
        }
    }

    public FrameLayout getVideoLayout() {
        return this._videoLayout;
    }
}
