package com.thecrackertechnology.dragonterminal;

import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;

public class NeoAudioThread extends AudioThread {
    public /* bridge */ /* synthetic */ int deinitAudio() {
        return super.deinitAudio();
    }

    public /* bridge */ /* synthetic */ int fillBuffer() {
        return super.fillBuffer();
    }

    public /* bridge */ /* synthetic */ byte[] getBuffer() {
        return super.getBuffer();
    }

    public /* bridge */ /* synthetic */ int initAudio(int i, int i2, int i3, int i4) {
        return super.initAudio(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ int initAudioThread() {
        return super.initAudioThread();
    }

    public /* bridge */ /* synthetic */ int pauseAudioPlayback() {
        return super.pauseAudioPlayback();
    }

    public /* bridge */ /* synthetic */ int resumeAudioPlayback() {
        return super.resumeAudioPlayback();
    }

    public NeoAudioThread(NeoXorgViewClient neoXorgViewClient) {
        super(neoXorgViewClient);
    }
}
