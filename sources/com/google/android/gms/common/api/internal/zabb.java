package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zal;

final class zabb extends zal {
    private final /* synthetic */ zaaw zahg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zabb(zaaw zaaw, Looper looper) {
        super(looper);
        this.zahg = zaaw;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.zahg.zaav();
        } else if (i != 2) {
            int i2 = message.what;
            StringBuilder sb = new StringBuilder(31);
            sb.append("Unknown message id: ");
            sb.append(i2);
            Log.w("GoogleApiClientImpl", sb.toString());
        } else {
            this.zahg.resume();
        }
    }
}
