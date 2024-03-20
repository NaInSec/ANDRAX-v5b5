package com.onesignal;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import java.util.List;

class OneSignalChromeTab {
    OneSignalChromeTab() {
    }

    private static boolean hasChromeTabLibrary() {
        Class<CustomTabsServiceConnection> cls = CustomTabsServiceConnection.class;
        return true;
    }

    protected static boolean open(String str, boolean z) {
        if (!hasChromeTabLibrary()) {
            return false;
        }
        return CustomTabsClient.bindCustomTabsService(OneSignal.appContext, "com.android.chrome", new OneSignalCustomTabsServiceConnection(str, z));
    }

    private static class OneSignalCustomTabsServiceConnection extends CustomTabsServiceConnection {
        private boolean openActivity;
        private String url;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        OneSignalCustomTabsServiceConnection(String str, boolean z) {
            this.url = str;
            this.openActivity = z;
        }

        public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
            if (customTabsClient != null) {
                customTabsClient.warmup(0);
                CustomTabsSession newSession = customTabsClient.newSession((CustomTabsCallback) null);
                if (newSession != null) {
                    Uri parse = Uri.parse(this.url);
                    newSession.mayLaunchUrl(parse, (Bundle) null, (List<Bundle>) null);
                    if (this.openActivity) {
                        CustomTabsIntent build = new CustomTabsIntent.Builder(newSession).build();
                        build.intent.setData(parse);
                        build.intent.addFlags(268435456);
                        if (Build.VERSION.SDK_INT >= 16) {
                            OneSignal.appContext.startActivity(build.intent, build.startAnimationBundle);
                        } else {
                            OneSignal.appContext.startActivity(build.intent);
                        }
                    }
                }
            }
        }
    }
}
