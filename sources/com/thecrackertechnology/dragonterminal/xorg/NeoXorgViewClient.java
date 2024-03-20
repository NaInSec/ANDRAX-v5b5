package com.thecrackertechnology.dragonterminal.xorg;

import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import com.thecrackertechnology.dragonterminal.NeoGLView;

public interface NeoXorgViewClient {
    Context getContext();

    NeoGLView getGLView();

    Window getWindow();

    WindowManager getWindowManager();

    void hideScreenKeyboard();

    void initScreenOrientation();

    boolean isKeyboardWithoutTextInputShown();

    boolean isPaused();

    boolean isRunningOnOUYA();

    boolean isScreenKeyboardShown();

    void runOnUiThread(Runnable runnable);

    void setScreenKeyboardHintMessage(String str);

    void setSystemMousePointerVisible(int i);

    void showScreenKeyboard(String str);

    void showScreenKeyboardWithoutTextInputField(int i);

    void updateScreenOrientation();
}
