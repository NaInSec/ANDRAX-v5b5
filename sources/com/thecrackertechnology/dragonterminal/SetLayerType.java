package com.thecrackertechnology.dragonterminal;

import android.graphics.Paint;
import android.view.View;

/* compiled from: MainActivity */
abstract class SetLayerType {
    public abstract void setLayerType(View view);

    SetLayerType() {
    }

    public static SetLayerType get() {
        return SetLayerTypeHoneycomb.Holder.sInstance;
    }

    /* compiled from: MainActivity */
    private static class SetLayerTypeHoneycomb extends SetLayerType {

        /* compiled from: MainActivity */
        private static class Holder {
            /* access modifiers changed from: private */
            public static final SetLayerTypeHoneycomb sInstance = new SetLayerTypeHoneycomb();

            private Holder() {
            }
        }

        private SetLayerTypeHoneycomb() {
        }

        public void setLayerType(View view) {
            view.setLayerType(0, (Paint) null);
        }
    }
}
