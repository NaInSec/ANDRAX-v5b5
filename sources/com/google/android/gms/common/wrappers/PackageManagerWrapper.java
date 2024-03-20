package com.google.android.gms.common.wrappers;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import com.google.android.gms.common.util.PlatformVersion;

public class PackageManagerWrapper {
    private final Context zzhv;

    public PackageManagerWrapper(Context context) {
        this.zzhv = context;
    }

    public ApplicationInfo getApplicationInfo(String str, int i) throws PackageManager.NameNotFoundException {
        return this.zzhv.getPackageManager().getApplicationInfo(str, i);
    }

    public PackageInfo getPackageInfo(String str, int i) throws PackageManager.NameNotFoundException {
        return this.zzhv.getPackageManager().getPackageInfo(str, i);
    }

    public final PackageInfo zza(String str, int i, int i2) throws PackageManager.NameNotFoundException {
        return this.zzhv.getPackageManager().getPackageInfo(str, 64);
    }

    public final String[] getPackagesForUid(int i) {
        return this.zzhv.getPackageManager().getPackagesForUid(i);
    }

    public final boolean zzb(int i, String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            try {
                ((AppOpsManager) this.zzhv.getSystemService("appops")).checkPackage(i, str);
                return true;
            } catch (SecurityException unused) {
                return false;
            }
        } else {
            String[] packagesForUid = this.zzhv.getPackageManager().getPackagesForUid(i);
            if (!(str == null || packagesForUid == null)) {
                for (String equals : packagesForUid) {
                    if (str.equals(equals)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public int checkCallingOrSelfPermission(String str) {
        return this.zzhv.checkCallingOrSelfPermission(str);
    }

    public int checkPermission(String str, String str2) {
        return this.zzhv.getPackageManager().checkPermission(str, str2);
    }

    public CharSequence getApplicationLabel(String str) throws PackageManager.NameNotFoundException {
        return this.zzhv.getPackageManager().getApplicationLabel(this.zzhv.getPackageManager().getApplicationInfo(str, 0));
    }

    public boolean isCallerInstantApp() {
        String nameForUid;
        if (Binder.getCallingUid() == Process.myUid()) {
            return InstantApps.isInstantApp(this.zzhv);
        }
        if (!PlatformVersion.isAtLeastO() || (nameForUid = this.zzhv.getPackageManager().getNameForUid(Binder.getCallingUid())) == null) {
            return false;
        }
        return this.zzhv.getPackageManager().isInstantApp(nameForUid);
    }
}
