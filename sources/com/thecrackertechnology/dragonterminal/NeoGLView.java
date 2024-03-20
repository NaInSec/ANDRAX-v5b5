package com.thecrackertechnology.dragonterminal;

import android.view.KeyEvent;
import android.view.MotionEvent;
import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;

public class NeoGLView extends DemoGLSurfaceView {
    public /* bridge */ /* synthetic */ void exitApp() {
        super.exitApp();
    }

    public /* bridge */ /* synthetic */ boolean isPaused() {
        return super.isPaused();
    }

    public /* bridge */ /* synthetic */ void limitEventRate(MotionEvent motionEvent) {
        super.limitEventRate(motionEvent);
    }

    public /* bridge */ /* synthetic */ boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return super.onGenericMotionEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public /* bridge */ /* synthetic */ boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return super.onKeyMultiple(i, i2, keyEvent);
    }

    public /* bridge */ /* synthetic */ boolean onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    public /* bridge */ /* synthetic */ void onPause() {
        super.onPause();
    }

    public /* bridge */ /* synthetic */ void onResume() {
        super.onResume();
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public NeoGLView(NeoXorgViewClient neoXorgViewClient) {
        super(neoXorgViewClient);
    }

    public void callNativeScreenKeyboardShown(int i) {
        nativeScreenKeyboardShown(i);
    }

    public void callNativeScreenVisibleRect(int i, int i2, int i3, int i4) {
        nativeScreenVisibleRect(i, i2, i3, i4);
    }
}
