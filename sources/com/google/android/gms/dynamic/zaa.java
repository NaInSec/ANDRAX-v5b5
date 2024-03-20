package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;

final class zaa implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zari;

    zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zari = deferredLifecycleHelper;
    }

    public final void onDelegateCreated(T t) {
        LifecycleDelegate unused = this.zari.zare = t;
        Iterator it = this.zari.zarg.iterator();
        while (it.hasNext()) {
            ((DeferredLifecycleHelper.zaa) it.next()).zaa(this.zari.zare);
        }
        this.zari.zarg.clear();
        Bundle unused2 = this.zari.zarf = null;
    }
}
