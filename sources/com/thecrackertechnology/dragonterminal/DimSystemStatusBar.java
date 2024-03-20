package com.thecrackertechnology.dragonterminal;

import android.view.View;

/* compiled from: MainActivity */
abstract class DimSystemStatusBar {
    public abstract void dim(View view);

    DimSystemStatusBar() {
    }

    public static DimSystemStatusBar get() {
        return DimSystemStatusBarHoneycomb.Holder.sInstance;
    }

    /* compiled from: MainActivity */
    private static class DimSystemStatusBarHoneycomb extends DimSystemStatusBar {

        /* compiled from: MainActivity */
        private static class Holder {
            /* access modifiers changed from: private */
            public static final DimSystemStatusBarHoneycomb sInstance = new DimSystemStatusBarHoneycomb();

            private Holder() {
            }
        }

        private DimSystemStatusBarHoneycomb() {
        }

        public void dim(View view) {
            if (Globals.ImmersiveMode) {
                view.setSystemUiVisibility(4102);
            } else {
                view.setSystemUiVisibility(1);
            }
        }
    }
}
