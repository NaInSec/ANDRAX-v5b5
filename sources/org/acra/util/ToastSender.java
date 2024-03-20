package org.acra.util;

import android.content.Context;
import android.widget.Toast;
import org.acra.ACRA;

public final class ToastSender {
    private ToastSender() {
    }

    public static void sendToast(Context context, String str, int i) {
        try {
            Toast.makeText(context, str, i).show();
        } catch (RuntimeException e) {
            ACRA.log.w(ACRA.LOG_TAG, "Could not send crash Toast", e);
        }
    }
}
