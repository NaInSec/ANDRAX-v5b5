package org.acra.sender;

import org.acra.config.DefaultRetryPolicy;
import org.acra.util.InstanceCreator;

/* renamed from: org.acra.sender.-$$Lambda$b56CD9vHz9YzUv87HD1wOykEnVg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$b56CD9vHz9YzUv87HD1wOykEnVg implements InstanceCreator.Fallback {
    public static final /* synthetic */ $$Lambda$b56CD9vHz9YzUv87HD1wOykEnVg INSTANCE = new $$Lambda$b56CD9vHz9YzUv87HD1wOykEnVg();

    private /* synthetic */ $$Lambda$b56CD9vHz9YzUv87HD1wOykEnVg() {
    }

    public final Object get() {
        return new DefaultRetryPolicy();
    }
}
