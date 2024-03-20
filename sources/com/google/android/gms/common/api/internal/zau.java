package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

final class zau implements zabt {
    private final /* synthetic */ zas zaep;

    private zau(zas zas) {
        this.zaep = zas;
    }

    public final void zab(Bundle bundle) {
        this.zaep.zaen.lock();
        try {
            this.zaep.zaa(bundle);
            ConnectionResult unused = this.zaep.zaek = ConnectionResult.RESULT_SUCCESS;
            this.zaep.zax();
        } finally {
            this.zaep.zaen.unlock();
        }
    }

    public final void zac(ConnectionResult connectionResult) {
        this.zaep.zaen.lock();
        try {
            ConnectionResult unused = this.zaep.zaek = connectionResult;
            this.zaep.zax();
        } finally {
            this.zaep.zaen.unlock();
        }
    }

    public final void zab(int i, boolean z) {
        this.zaep.zaen.lock();
        try {
            if (!this.zaep.zaem && this.zaep.zael != null) {
                if (this.zaep.zael.isSuccess()) {
                    boolean unused = this.zaep.zaem = true;
                    this.zaep.zaef.onConnectionSuspended(i);
                    this.zaep.zaen.unlock();
                    return;
                }
            }
            boolean unused2 = this.zaep.zaem = false;
            this.zaep.zaa(i, z);
        } finally {
            this.zaep.zaen.unlock();
        }
    }

    /* synthetic */ zau(zas zas, zat zat) {
        this(zas);
    }
}
