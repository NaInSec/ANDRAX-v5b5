package com.thecrackertechnology.dragonterminal.component.colorscheme;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/component/colorscheme/DefaultColorScheme;", "Lcom/thecrackertechnology/dragonterminal/component/colorscheme/NeoColorScheme;", "()V", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: DefaultColorScheme.kt */
public final class DefaultColorScheme extends NeoColorScheme {
    public static final DefaultColorScheme INSTANCE;

    static {
        DefaultColorScheme defaultColorScheme = new DefaultColorScheme();
        INSTANCE = defaultColorScheme;
        defaultColorScheme.setColorName("Default");
        defaultColorScheme.setForegroundColor("#00ff00");
        defaultColorScheme.setBackgroundColor("#000000");
        defaultColorScheme.setCursorColor("#00A000");
    }

    private DefaultColorScheme() {
    }
}
