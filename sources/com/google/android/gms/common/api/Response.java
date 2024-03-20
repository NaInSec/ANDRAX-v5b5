package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

public class Response<T extends Result> {
    private T zzao;

    public Response() {
    }

    protected Response(T t) {
        this.zzao = t;
    }

    /* access modifiers changed from: protected */
    public T getResult() {
        return this.zzao;
    }

    public void setResult(T t) {
        this.zzao = t;
    }
}
