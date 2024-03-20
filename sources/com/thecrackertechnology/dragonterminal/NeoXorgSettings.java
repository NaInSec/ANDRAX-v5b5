package com.thecrackertechnology.dragonterminal;

import com.thecrackertechnology.dragonterminal.xorg.NeoXorgViewClient;

public class NeoXorgSettings {
    public static void init(NeoXorgViewClient neoXorgViewClient) {
        Settings.Load(neoXorgViewClient);
    }
}
