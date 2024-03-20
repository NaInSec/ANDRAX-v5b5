package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;

public final class zabw {
    public final RegisterListenerMethod<Api.AnyClient, ?> zajw;
    public final UnregisterListenerMethod<Api.AnyClient, ?> zajx;

    public zabw(RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod, UnregisterListenerMethod<Api.AnyClient, ?> unregisterListenerMethod) {
        this.zajw = registerListenerMethod;
        this.zajx = unregisterListenerMethod;
    }
}
