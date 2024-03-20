package com.thecrackertechnology.dragonterminal;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;

/* compiled from: Video */
class DemoGLSurfaceView extends GLSurfaceView_SDL {
    NeoXorgViewClient mClient;
    DemoRenderer mRenderer;

    public static native void nativeGamepadAnalogJoystickInput(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i);

    public static native void nativeHardwareMouseDetected(int i);

    public static native int nativeKey(int i, int i2, int i3, int i4);

    public static native void nativeMotionEvent(int i, int i2, int i3, int i4, int i5, int i6);

    public static native void nativeMouseButtonsPressed(int i, int i2);

    public static native void nativeMouseWheel(int i, int i2);

    public static native void nativeScreenKeyboardShown(int i);

    public static native void nativeScreenVisibleRect(int i, int i2, int i3, int i4);

    public DemoGLSurfaceView(NeoXorgViewClient neoXorgViewClient) {
        super(neoXorgViewClient.getContext());
        this.mClient = neoXorgViewClient;
        setEGLConfigChooser(Globals.VideoDepthBpp, Globals.NeedDepthBuffer, Globals.NeedStencilBuffer, Globals.NeedGles2, Globals.NeedGles3);
        this.mRenderer = new DemoRenderer(neoXorgViewClient);
        setRenderer(this.mRenderer);
        DifferentTouchInput.registerInputManagerCallbacks(neoXorgViewClient.getContext());
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if ((keyEvent.getSource() & 8194) == 8194) {
                nativeMouseButtonsPressed(2, 1);
                return true;
            } else if (this.mClient.isKeyboardWithoutTextInputShown()) {
                return true;
            }
        }
        if (nativeKey(i, 1, keyEvent.getUnicodeChar(), DifferentTouchInput.processGamepadDeviceId(keyEvent.getDevice())) == 0) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if ((keyEvent.getSource() & 8194) == 8194) {
                nativeMouseButtonsPressed(2, 0);
                return true;
            } else if (this.mClient.isKeyboardWithoutTextInputShown()) {
                this.mClient.showScreenKeyboardWithoutTextInputField(0);
                return true;
            }
        }
        if (nativeKey(i, 0, keyEvent.getUnicodeChar(), DifferentTouchInput.processGamepadDeviceId(keyEvent.getDevice())) == 0) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        if (keyEvent.getCharacters() != null) {
            for (int i3 = 0; i3 < keyEvent.getCharacters().length(); i3++) {
                nativeKey(keyEvent.getKeyCode(), 1, keyEvent.getCharacters().codePointAt(i3), 0);
                nativeKey(keyEvent.getKeyCode(), 0, keyEvent.getCharacters().codePointAt(i3), 0);
            }
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getX() != 0.0f) {
            motionEvent.offsetLocation(-getX(), -getY());
        }
        DifferentTouchInput.touchInput.process(motionEvent);
        limitEventRate(motionEvent);
        return true;
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        DifferentTouchInput.touchInput.processGenericEvent(motionEvent);
        limitEventRate(motionEvent);
        return true;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void limitEventRate(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 2
            if (r0 == r1) goto L_0x000e
            int r4 = r4.getAction()
            r0 = 7
            if (r4 != r0) goto L_0x0026
        L_0x000e:
            com.thecrackertechnology.dragonterminal.DemoRenderer r4 = r3.mRenderer
            monitor-enter(r4)
            com.thecrackertechnology.dragonterminal.DemoRenderer r0 = r3.mRenderer     // Catch:{ InterruptedException -> 0x001b }
            r1 = 300(0x12c, double:1.48E-321)
            r0.wait(r1)     // Catch:{ InterruptedException -> 0x001b }
            goto L_0x0025
        L_0x0019:
            r0 = move-exception
            goto L_0x0027
        L_0x001b:
            java.lang.String r0 = "SDL"
            java.lang.String r1 = "DemoGLSurfaceView::limitEventRate(): Who dared to interrupt my slumber?"
            android.util.Log.v(r0, r1)     // Catch:{ all -> 0x0019 }
            java.lang.Thread.interrupted()     // Catch:{ all -> 0x0019 }
        L_0x0025:
            monitor-exit(r4)     // Catch:{ all -> 0x0019 }
        L_0x0026:
            return
        L_0x0027:
            monitor-exit(r4)     // Catch:{ all -> 0x0019 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.dragonterminal.DemoGLSurfaceView.limitEventRate(android.view.MotionEvent):void");
    }

    public void exitApp() {
        this.mRenderer.exitApp();
    }

    public void onPause() {
        StringBuilder sb = new StringBuilder();
        sb.append("libSDL: DemoGLSurfaceView.onPause(): mRenderer.mGlSurfaceCreated ");
        sb.append(this.mRenderer.mGlSurfaceCreated);
        sb.append(" mRenderer.mPaused ");
        sb.append(this.mRenderer.mPaused);
        sb.append(this.mRenderer.mPaused ? " - not doing anything" : "");
        Log.i("SDL", sb.toString());
        if (!this.mRenderer.mPaused) {
            this.mRenderer.mPaused = true;
            super.onPause();
            this.mRenderer.nativeGlContextLostAsyncEvent();
            if (this.mRenderer.accelerometer != null) {
                this.mRenderer.accelerometer.stop();
            }
        }
    }

    public boolean isPaused() {
        return this.mRenderer.mPaused;
    }

    public void onResume() {
        StringBuilder sb = new StringBuilder();
        sb.append("libSDL: DemoGLSurfaceView.onResume(): mRenderer.mGlSurfaceCreated ");
        sb.append(this.mRenderer.mGlSurfaceCreated);
        sb.append(" mRenderer.mPaused ");
        sb.append(this.mRenderer.mPaused);
        sb.append(!this.mRenderer.mPaused ? " - not doing anything" : "");
        Log.i("SDL", sb.toString());
        if (this.mRenderer.mPaused) {
            this.mRenderer.mPaused = false;
            super.onResume();
            if ((this.mRenderer.mGlSurfaceCreated && !this.mRenderer.mPaused) || Globals.NonBlockingSwapBuffers) {
                this.mRenderer.nativeGlContextRecreated();
            }
            if (this.mRenderer.accelerometer != null && this.mRenderer.accelerometer.openedBySDL) {
                this.mRenderer.accelerometer.start();
            }
        }
    }
}
