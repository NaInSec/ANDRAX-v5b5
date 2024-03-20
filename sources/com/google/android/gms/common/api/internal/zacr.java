package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.zac;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

final class zacr implements IBinder.DeathRecipient, zacs {
    private final WeakReference<BasePendingResult<?>> zalb;
    private final WeakReference<zac> zalc;
    private final WeakReference<IBinder> zald;

    private zacr(BasePendingResult<?> basePendingResult, zac zac, IBinder iBinder) {
        this.zalc = new WeakReference<>(zac);
        this.zalb = new WeakReference<>(basePendingResult);
        this.zald = new WeakReference<>(iBinder);
    }

    public final void zac(BasePendingResult<?> basePendingResult) {
        zaby();
    }

    public final void binderDied() {
        zaby();
    }

    private final void zaby() {
        BasePendingResult basePendingResult = (BasePendingResult) this.zalb.get();
        zac zac = (zac) this.zalc.get();
        if (!(zac == null || basePendingResult == null)) {
            zac.remove(basePendingResult.zam().intValue());
        }
        IBinder iBinder = (IBinder) this.zald.get();
        if (iBinder != null) {
            try {
                iBinder.unlinkToDeath(this, 0);
            } catch (NoSuchElementException unused) {
            }
        }
    }

    /* synthetic */ zacr(BasePendingResult basePendingResult, zac zac, IBinder iBinder, zacq zacq) {
        this(basePendingResult, (zac) null, iBinder);
    }
}
