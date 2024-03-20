package com.google.android.gms.common.wrappers;

import android.content.Context;

public class Wrappers {
    private static Wrappers zzhx = new Wrappers();
    private PackageManagerWrapper zzhw = null;

    private final synchronized PackageManagerWrapper zzi(Context context) {
        if (this.zzhw == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.zzhw = new PackageManagerWrapper(context);
        }
        return this.zzhw;
    }

    public static PackageManagerWrapper packageManager(Context context) {
        return zzhx.zzi(context);
    }
}
