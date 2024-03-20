package com.thecrackertechnology.dragonterminal.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/utils/NetworkUtils;", "", "()V", "getNetworkType", "", "context", "Landroid/content/Context;", "isNetworkAvailable", "", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NetworkUtils.kt */
public final class NetworkUtils {
    public static final NetworkUtils INSTANCE = new NetworkUtils();

    private NetworkUtils() {
    }

    public final boolean isNetworkAvailable(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return getNetworkType(context) != null;
    }

    private final String getNetworkType(Context context) {
        String str = null;
        Object systemService = context.getSystemService("connectivity");
        if (systemService != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
            if (connectivityManager == null) {
                return null;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return str;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() != 0) {
                return str;
            }
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return "2G";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    break;
                case 13:
                    return "4G";
                default:
                    String subtypeName = activeNetworkInfo.getSubtypeName();
                    if (!StringsKt.equals(subtypeName, "TD-SCDMA", true) && !StringsKt.equals(subtypeName, "WCDMA", true) && !StringsKt.equals(subtypeName, "CDMA2000", true)) {
                        return subtypeName;
                    }
            }
            return "3G";
        }
        throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
    }
}
