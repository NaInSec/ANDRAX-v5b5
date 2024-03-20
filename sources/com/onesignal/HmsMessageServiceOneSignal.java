package com.onesignal;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

public class HmsMessageServiceOneSignal extends HmsMessageService {
    /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.onesignal.HmsMessageServiceOneSignal] */
    public void onNewToken(String str) {
        OneSignalHmsEventBridge.onNewToken(this, str);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.onesignal.HmsMessageServiceOneSignal] */
    public void onMessageReceived(RemoteMessage remoteMessage) {
        OneSignalHmsEventBridge.onMessageReceived(this, remoteMessage);
    }
}
