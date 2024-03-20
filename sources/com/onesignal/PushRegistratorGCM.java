package com.onesignal;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.stats.CodePackage;

class PushRegistratorGCM extends PushRegistratorAbstractGoogle {
    /* access modifiers changed from: package-private */
    public String getProviderName() {
        return CodePackage.GCM;
    }

    PushRegistratorGCM() {
    }

    /* access modifiers changed from: package-private */
    public String getToken(String str) throws Throwable {
        return GoogleCloudMessaging.getInstance(OneSignal.appContext).register(new String[]{str});
    }
}
