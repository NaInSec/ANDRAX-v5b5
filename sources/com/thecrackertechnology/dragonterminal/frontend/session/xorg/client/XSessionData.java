package com.thecrackertechnology.dragonterminal.frontend.session.xorg.client;

import android.view.View;
import android.widget.FrameLayout;
import com.thecrackertechnology.dragonterminal.NeoAudioThread;
import com.thecrackertechnology.dragonterminal.NeoGLView;
import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R \u0010)\u001a\b\u0012\u0004\u0012\u00020+0*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u00100\u001a\u0004\u0018\u000101X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105¨\u00066"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/client/XSessionData;", "", "()V", "audioThread", "Lcom/thecrackertechnology/dragonterminal/NeoAudioThread;", "getAudioThread", "()Lcom/thecrackertechnology/dragonterminal/NeoAudioThread;", "setAudioThread", "(Lcom/thecrackertechnology/dragonterminal/NeoAudioThread;)V", "client", "Lcom/thecrackertechnology/dragonterminal/xorg/NeoXorgViewClient;", "getClient", "()Lcom/thecrackertechnology/dragonterminal/xorg/NeoXorgViewClient;", "setClient", "(Lcom/thecrackertechnology/dragonterminal/xorg/NeoXorgViewClient;)V", "glView", "Lcom/thecrackertechnology/dragonterminal/NeoGLView;", "getGlView", "()Lcom/thecrackertechnology/dragonterminal/NeoGLView;", "setGlView", "(Lcom/thecrackertechnology/dragonterminal/NeoGLView;)V", "isPaused", "", "()Z", "setPaused", "(Z)V", "keyboardWithoutTextInputShown", "getKeyboardWithoutTextInputShown", "setKeyboardWithoutTextInputShown", "screenKeyboard", "Landroid/view/View;", "getScreenKeyboard", "()Landroid/view/View;", "setScreenKeyboard", "(Landroid/view/View;)V", "screenKeyboardHintMessage", "", "getScreenKeyboardHintMessage", "()Ljava/lang/String;", "setScreenKeyboardHintMessage", "(Ljava/lang/String;)V", "textInput", "Ljava/util/LinkedList;", "", "getTextInput", "()Ljava/util/LinkedList;", "setTextInput", "(Ljava/util/LinkedList;)V", "videoLayout", "Landroid/widget/FrameLayout;", "getVideoLayout", "()Landroid/widget/FrameLayout;", "setVideoLayout", "(Landroid/widget/FrameLayout;)V", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: XSessionData.kt */
public final class XSessionData {
    private NeoAudioThread audioThread;
    private NeoXorgViewClient client;
    private NeoGLView glView;
    private boolean isPaused;
    private boolean keyboardWithoutTextInputShown;
    private View screenKeyboard;
    private String screenKeyboardHintMessage;
    private LinkedList<Integer> textInput = new LinkedList<>();
    private FrameLayout videoLayout;

    public final FrameLayout getVideoLayout() {
        return this.videoLayout;
    }

    public final void setVideoLayout(FrameLayout frameLayout) {
        this.videoLayout = frameLayout;
    }

    public final NeoAudioThread getAudioThread() {
        return this.audioThread;
    }

    public final void setAudioThread(NeoAudioThread neoAudioThread) {
        this.audioThread = neoAudioThread;
    }

    public final View getScreenKeyboard() {
        return this.screenKeyboard;
    }

    public final void setScreenKeyboard(View view) {
        this.screenKeyboard = view;
    }

    public final NeoGLView getGlView() {
        return this.glView;
    }

    public final void setGlView(NeoGLView neoGLView) {
        this.glView = neoGLView;
    }

    public final boolean isPaused() {
        return this.isPaused;
    }

    public final void setPaused(boolean z) {
        this.isPaused = z;
    }

    public final NeoXorgViewClient getClient() {
        return this.client;
    }

    public final void setClient(NeoXorgViewClient neoXorgViewClient) {
        this.client = neoXorgViewClient;
    }

    public final boolean getKeyboardWithoutTextInputShown() {
        return this.keyboardWithoutTextInputShown;
    }

    public final void setKeyboardWithoutTextInputShown(boolean z) {
        this.keyboardWithoutTextInputShown = z;
    }

    public final String getScreenKeyboardHintMessage() {
        return this.screenKeyboardHintMessage;
    }

    public final void setScreenKeyboardHintMessage(String str) {
        this.screenKeyboardHintMessage = str;
    }

    public final LinkedList<Integer> getTextInput() {
        return this.textInput;
    }

    public final void setTextInput(LinkedList<Integer> linkedList) {
        Intrinsics.checkParameterIsNotNull(linkedList, "<set-?>");
        this.textInput = linkedList;
    }
}
