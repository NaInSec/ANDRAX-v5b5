package com.google.firebase.iid;

final class zzx implements InstanceIdResult {
    private final String zzbp;
    private final String zzbq;

    zzx(String str, String str2) {
        this.zzbp = str;
        this.zzbq = str2;
    }

    public final String getId() {
        return this.zzbp;
    }

    public final String getToken() {
        return this.zzbq;
    }
}
