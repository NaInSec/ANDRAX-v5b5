package com.thecrackertechnology.dragonterminal.backend;

import java.nio.charset.StandardCharsets;

public abstract class TerminalOutput {
    public abstract void clipboardText(String str);

    public abstract void onBell();

    public abstract void onColorsChanged();

    public abstract void titleChanged(String str, String str2);

    public abstract void write(byte[] bArr, int i, int i2);

    public final void write(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        write(bytes, 0, bytes.length);
    }
}
