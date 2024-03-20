package com.thecrackertechnology.dragonterminal;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

public abstract class Clipboard {
    public abstract String get(Context context);

    public abstract void set(Context context, String str);

    public abstract void setListener(Context context, Runnable runnable);

    public static Clipboard get() {
        if (Build.VERSION.SDK_INT >= 11) {
            return NewerClipboard.Holder.Instance;
        }
        return OlderClipboard.Holder.Instance;
    }

    private static class NewerClipboard extends Clipboard {
        private NewerClipboard() {
        }

        private static class Holder {
            /* access modifiers changed from: private */
            public static final NewerClipboard Instance = new NewerClipboard();

            private Holder() {
            }
        }

        public void set(Context context, String str) {
            try {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
                if (clipboardManager != null) {
                    clipboardManager.setText(str);
                }
            } catch (Exception e) {
                Log.i("SDL", "setClipboardText() exception: " + e.toString());
            }
        }

        public String get(Context context) {
            try {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
                if (clipboardManager == null || clipboardManager.getText() == null) {
                    return "";
                }
                return clipboardManager.getText().toString();
            } catch (Exception e) {
                Log.i("SDL", "getClipboardText() exception: " + e.toString());
                return "";
            }
        }

        public void setListener(Context context, final Runnable runnable) {
            ((ClipboardManager) context.getSystemService("clipboard")).addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
                public void onPrimaryClipChanged() {
                    runnable.run();
                }
            });
        }
    }

    private static class OlderClipboard extends Clipboard {
        private OlderClipboard() {
        }

        private static class Holder {
            /* access modifiers changed from: private */
            public static final OlderClipboard Instance = new OlderClipboard();

            private Holder() {
            }
        }

        public void set(Context context, String str) {
            try {
                android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) context.getSystemService("clipboard");
                if (clipboardManager != null) {
                    clipboardManager.setText(str);
                }
            } catch (Exception e) {
                Log.i("SDL", "setClipboardText() exception: " + e.toString());
            }
        }

        public String get(Context context) {
            try {
                android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) context.getSystemService("clipboard");
                if (clipboardManager == null || clipboardManager.getText() == null) {
                    return "";
                }
                return clipboardManager.getText().toString();
            } catch (Exception e) {
                Log.i("SDL", "getClipboardText() exception: " + e.toString());
                return "";
            }
        }

        public void setListener(Context context, Runnable runnable) {
            Log.i("SDL", "Cannot set clipboard listener on Android 2.3 or older");
        }
    }
}
