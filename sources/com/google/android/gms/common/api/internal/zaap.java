package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

final class zaap extends zabf {
    private final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks zagn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zaap(zaan zaan, zabd zabd, BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zabd);
        this.zagn = connectionProgressReportCallbacks;
    }

    public final void zaan() {
        this.zagn.onReportServiceBinding(new ConnectionResult(16, (PendingIntent) null));
    }
}
