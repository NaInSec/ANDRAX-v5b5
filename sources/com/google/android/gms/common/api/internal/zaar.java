package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zaj;
import java.lang.ref.WeakReference;

final class zaar extends zac {
    private final WeakReference<zaak> zagj;

    zaar(zaak zaak) {
        this.zagj = new WeakReference<>(zaak);
    }

    public final void zab(zaj zaj) {
        zaak zaak = (zaak) this.zagj.get();
        if (zaak != null) {
            zaak.zafs.zaa((zabf) new zaas(this, zaak, zaak, zaj));
        }
    }
}
