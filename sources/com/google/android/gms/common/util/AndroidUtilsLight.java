package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.common.wrappers.Wrappers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AndroidUtilsLight {
    private static volatile int zzgd = -1;

    public static byte[] getPackageCertificateHashBytes(Context context, String str) throws PackageManager.NameNotFoundException {
        MessageDigest zzi;
        PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
        if (packageInfo.signatures == null || packageInfo.signatures.length != 1 || (zzi = zzi("SHA1")) == null) {
            return null;
        }
        return zzi.digest(packageInfo.signatures[0].toByteArray());
    }

    public static MessageDigest zzi(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    public static Context getDeviceProtectedStorageContext(Context context) {
        return (!PlatformVersion.isAtLeastN() || context.isDeviceProtectedStorage()) ? context : context.createDeviceProtectedStorageContext();
    }
}
