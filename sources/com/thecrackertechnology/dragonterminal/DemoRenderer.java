package com.thecrackertechnology.dragonterminal;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.View;
import com.thecrackertechnology.dragonterminal.GLSurfaceView_SDL;
import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: Video */
class DemoRenderer extends GLSurfaceView_SDL.Renderer {
    public static final boolean mRatelimitTouchEvents = true;
    public AccelerometerReader accelerometer = null;
    /* access modifiers changed from: private */
    public NeoXorgViewClient mClient = null;
    private EGL10 mEgl = null;
    private EGLContext mEglContext = null;
    private EGLDisplay mEglDisplay = null;
    private EGLSurface mEglSurface = null;
    private boolean mFirstTimeStart = true;
    private GL10 mGl = null;
    private boolean mGlContextLost = false;
    public boolean mGlSurfaceCreated = false;
    public int mHeight = 0;
    int mLastPendingResize = 0;
    int mOrientationFrameHackyCounter = 0;
    public boolean mPaused = false;
    public int mWidth = 0;

    private int PowerOf2(int i) {
        int i2 = 1;
        while (i2 < i) {
            i2 <<= 1;
        }
        return i2;
    }

    public static native void nativeClipboardChanged();

    private native void nativeDone();

    private native void nativeGlContextLost();

    private native void nativeInit(String str, String str2, int i, int i2);

    private native void nativeInitJavaCallbacks();

    private native void nativeResize(int i, int i2, int i3);

    public static native void nativeTextInput(int i, int i2);

    public static native void nativeTextInputFinished();

    public boolean cloudLoad(String str, String str2, String str3) {
        return false;
    }

    public boolean cloudSave(String str, String str2, String str3, String str4, String str5, long j) {
        return false;
    }

    public void getAdvertisementParams(int[] iArr) {
    }

    public native void nativeGlContextLostAsyncEvent();

    public native void nativeGlContextRecreated();

    public void requestNewAdvertisement() {
    }

    public void restartMyself(String str) {
    }

    public void setAdvertisementPosition(int i, int i2) {
    }

    public void setAdvertisementVisible(int i) {
    }

    public DemoRenderer(NeoXorgViewClient neoXorgViewClient) {
        this.mClient = neoXorgViewClient;
        Clipboard.get().setListener(this.mClient.getContext(), new Runnable() {
            public void run() {
                DemoRenderer.nativeClipboardChanged();
            }
        });
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        Log.i("SDL", "libSDL: DemoRenderer.onSurfaceCreated(): paused " + this.mPaused + " mFirstTimeStart " + this.mFirstTimeStart);
        this.mGlSurfaceCreated = true;
        this.mGl = gl10;
        if (!this.mPaused && !this.mFirstTimeStart) {
            nativeGlContextRecreated();
        }
        this.mFirstTimeStart = false;
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        Log.i("SDL", "libSDL: DemoRenderer.onSurfaceChanged(): paused " + this.mPaused + " mFirstTimeStart " + this.mFirstTimeStart + " w " + i + " h " + i2);
        if (i < i2 && Globals.HorizontalOrientation) {
            int i3 = i2;
            i2 = i;
            i = i3;
        }
        this.mWidth = i - (i % 2);
        this.mHeight = i2 - (i2 % 2);
        this.mGl = gl10;
        nativeResize(this.mWidth, this.mHeight, Globals.KeepAspectRatio ? 1 : 0);
    }

    public void onWindowResize(final int i, final int i2) {
        if (!this.mClient.isRunningOnOUYA()) {
            Log.d("SDL", "libSDL: DemoRenderer.onWindowResize(): " + i + "x" + i2);
            this.mLastPendingResize = this.mLastPendingResize + 1;
            final int i3 = this.mLastPendingResize;
            this.mClient.getGLView().postDelayed(new Runnable() {
                public void run() {
                    if (i3 == DemoRenderer.this.mLastPendingResize) {
                        int i = i;
                        int i2 = i - (i % 2);
                        int i3 = i2;
                        int i4 = i3 - (i3 % 2);
                        View peekDecorView = DemoRenderer.this.mClient.getWindow().peekDecorView();
                        if (peekDecorView != null && Globals.ImmersiveMode) {
                            i2 = peekDecorView.getWidth() - (peekDecorView.getWidth() % 2);
                            i4 = peekDecorView.getHeight() - (peekDecorView.getHeight() % 2);
                        }
                        Display defaultDisplay = DemoRenderer.this.mClient.getWindowManager().getDefaultDisplay();
                        boolean z = true;
                        if (!(DemoRenderer.this.mWidth == 0 || DemoRenderer.this.mHeight == 0 || (DemoRenderer.this.mWidth == i2 && DemoRenderer.this.mHeight == i4))) {
                            Log.i("SDL", "libSDL: DemoRenderer.onWindowResize(): screen size changed from " + DemoRenderer.this.mWidth + "x" + DemoRenderer.this.mHeight + " to " + i2 + "x" + i4);
                            if (!Globals.SwVideoMode || (Math.abs(defaultDisplay.getWidth() - i2) <= defaultDisplay.getWidth() / 10 && Math.abs(defaultDisplay.getHeight() - i4) <= defaultDisplay.getHeight() / 10)) {
                                Log.i("SDL", "System button bar hidden - re-init video to avoid black bar at the top");
                                DemoRenderer.super.ResetVideoSurface();
                                DemoRenderer.super.onWindowResize(i2, i4);
                            } else {
                                Log.i("SDL", "Multiwindow detected - enabling screen orientation autodetection");
                                Globals.AutoDetectOrientation = true;
                                DemoRenderer.this.mClient.initScreenOrientation();
                                DemoRenderer.super.ResetVideoSurface();
                                DemoRenderer.super.onWindowResize(i2, i4);
                            }
                        }
                        if (DemoRenderer.this.mWidth == 0 && DemoRenderer.this.mHeight == 0) {
                            if ((i2 > i4) != (defaultDisplay.getWidth() > defaultDisplay.getHeight())) {
                                Log.i("SDL", "Multiwindow detected - app window size " + i2 + "x" + i4 + " but display dimensions are " + defaultDisplay.getWidth() + "x" + defaultDisplay.getHeight());
                                Globals.AutoDetectOrientation = true;
                            }
                        }
                        if (Globals.AutoDetectOrientation) {
                            if ((i2 > i4) != (DemoRenderer.this.mWidth > DemoRenderer.this.mHeight)) {
                                if (i2 <= i4) {
                                    z = false;
                                }
                                Globals.HorizontalOrientation = z;
                            }
                        }
                    }
                }
            }, 2000);
        }
    }

    public void onSurfaceDestroyed() {
        Log.i("SDL", "libSDL: DemoRenderer.onSurfaceDestroyed(): paused " + this.mPaused + " mFirstTimeStart " + this.mFirstTimeStart);
        this.mGlSurfaceCreated = false;
        this.mGlContextLost = true;
        nativeGlContextLost();
    }

    public void onDrawFrame(GL10 gl10) {
        this.mGl = gl10;
        SwapBuffers();
        nativeInitJavaCallbacks();
        this.mGlContextLost = false;
        Settings.Apply(this.mClient);
        Settings.nativeSetEnv("DISPLAY_RESOLUTION_WIDTH", String.valueOf(Math.max(this.mWidth, this.mHeight)));
        Settings.nativeSetEnv("DISPLAY_RESOLUTION_HEIGHT", String.valueOf(Math.min(this.mWidth, this.mHeight)));
        this.accelerometer = new NeoAccelerometerReader(this.mClient.getContext());
        int i = 1;
        if (Globals.MoveMouseWithGyroscope) {
            startAccelerometerGyroscope(1);
        }
        if (Globals.AudioBufferConfig >= 2) {
            Thread.currentThread().setPriority(3);
        }
        String str = Globals.CommandLine;
        String str2 = Globals.DataDir;
        if ((!Globals.SwVideoMode || !Globals.MultiThreadedVideo) && !Globals.CompatibilityHacksVideo) {
            i = 0;
        }
        nativeInit(str2, str, i, 0);
        System.exit(0);
    }

    public int swapBuffers() {
        if (super.SwapBuffers() || !Globals.NonBlockingSwapBuffers) {
            if (this.mGlContextLost) {
                this.mGlContextLost = false;
                Settings.SetupTouchscreenKeyboardGraphics(this.mClient.getContext());
                super.SwapBuffers();
            }
            synchronized (this) {
                notify();
            }
            if (this.mClient.isScreenKeyboardShown() && !this.mClient.isKeyboardWithoutTextInputShown()) {
                try {
                    Thread.sleep(50);
                } catch (Exception unused) {
                }
            }
            this.mOrientationFrameHackyCounter++;
            if (this.mOrientationFrameHackyCounter > 100) {
                this.mOrientationFrameHackyCounter = 0;
                this.mClient.updateScreenOrientation();
            }
            return 1;
        }
        synchronized (this) {
            notify();
        }
        return 0;
    }

    public void showScreenKeyboardWithoutTextInputField() {
        this.mClient.showScreenKeyboardWithoutTextInputField(Globals.TextInputKeyboard);
    }

    public void showInternalScreenKeyboard(int i) {
        this.mClient.showScreenKeyboardWithoutTextInputField(i);
    }

    public void showScreenKeyboard(String str, int i) {
        AnonymousClass1Callback r3 = new Runnable() {
            public NeoXorgViewClient client;
            public String oldText;

            public void run() {
                this.client.showScreenKeyboard(this.oldText);
            }
        };
        NeoXorgViewClient neoXorgViewClient = this.mClient;
        r3.client = neoXorgViewClient;
        r3.oldText = str;
        neoXorgViewClient.runOnUiThread(r3);
    }

    public void hideScreenKeyboard() {
        AnonymousClass2Callback r0 = new Runnable() {
            public NeoXorgViewClient client;

            public void run() {
                this.client.hideScreenKeyboard();
            }
        };
        NeoXorgViewClient neoXorgViewClient = this.mClient;
        r0.client = neoXorgViewClient;
        neoXorgViewClient.runOnUiThread(r0);
    }

    public int isScreenKeyboardShown() {
        return this.mClient.isScreenKeyboardShown() ? 1 : 0;
    }

    public void setScreenKeyboardHintMessage(String str) {
        this.mClient.setScreenKeyboardHintMessage(str);
    }

    public void startAccelerometerGyroscope(int i) {
        this.accelerometer.openedBySDL = i != 0;
        if (!this.accelerometer.openedBySDL || this.mPaused) {
            this.accelerometer.stop();
        } else {
            this.accelerometer.start();
        }
    }

    public String getClipboardText() {
        return Clipboard.get().get(this.mClient.getContext());
    }

    public void setClipboardText(String str) {
        Clipboard.get().set(this.mClient.getContext(), str);
    }

    public void exitApp() {
        nativeDone();
    }

    public void openExternalApp(String str, String str2, String str3) {
        try {
            Intent intent = new Intent();
            if (str3 != null && str3.length() > 0) {
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str3));
            }
            if (str != null && str2 != null && str.length() > 0 && str2.length() > 0) {
                intent.setClassName(str, str2);
            }
            this.mClient.getContext().startActivity(intent);
        } catch (Exception e) {
            Log.i("SDL", "libSDL: cannot start external app: " + e.toString());
        }
    }

    public void setSystemMousePointerVisible(int i) {
        this.mClient.setSystemMousePointerVisible(i);
    }

    public void setConfigOptionFromSDL(int i, int i2) {
        Settings.setConfigOptionFromSDL(i, i2);
    }
}
