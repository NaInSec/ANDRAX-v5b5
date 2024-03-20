package com.google.android.gms.common.api.internal;

abstract class zaau implements Runnable {
    private final /* synthetic */ zaak zagi;

    private zaau(zaak zaak) {
        this.zagi = zaak;
    }

    /* access modifiers changed from: protected */
    public abstract void zaan();

    public void run() {
        this.zagi.zaen.lock();
        try {
            if (!Thread.interrupted()) {
                zaan();
                this.zagi.zaen.unlock();
            }
        } catch (RuntimeException e) {
            this.zagi.zafs.zab(e);
        } finally {
            this.zagi.zaen.unlock();
        }
    }

    /* synthetic */ zaau(zaak zaak, zaal zaal) {
        this(zaak);
    }
}
