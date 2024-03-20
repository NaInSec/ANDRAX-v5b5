package com.onesignal;

import android.content.Context;
import com.huawei.hms.common.ApiException;
import com.onesignal.OneSignal;
import com.onesignal.PushRegistrator;

class PushRegistratorHMS implements PushRegistrator {
    static final String HMS_CLIENT_APP_ID = "client/app_id";
    private static final int NEW_TOKEN_TIMEOUT_MS = 30000;
    private static boolean callbackSuccessful;
    private static PushRegistrator.RegisteredHandler registeredHandler;

    PushRegistratorHMS() {
    }

    static void fireCallback(String str) {
        PushRegistrator.RegisteredHandler registeredHandler2 = registeredHandler;
        if (registeredHandler2 != null) {
            callbackSuccessful = true;
            registeredHandler2.complete(str, 1);
        }
    }

    public void registerForPush(final Context context, String str, final PushRegistrator.RegisteredHandler registeredHandler2) {
        registeredHandler = registeredHandler2;
        new Thread(new Runnable() {
            public void run() {
                try {
                    PushRegistratorHMS.this.getHMSTokenTask(context, registeredHandler2);
                } catch (ApiException e) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "HMS ApiException getting Huawei push token!", e);
                    registeredHandler2.complete((String) null, e.getStatusCode() == 907135000 ? -26 : -27);
                }
            }
        }, "OS_HMS_GET_TOKEN").start();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0048, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void getHMSTokenTask(android.content.Context r4, com.onesignal.PushRegistrator.RegisteredHandler r5) throws com.huawei.hms.common.ApiException {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = com.onesignal.OSUtils.hasAllHMSLibrariesForPushKit()     // Catch:{ all -> 0x0049 }
            if (r0 != 0) goto L_0x000f
            r4 = 0
            r0 = -28
            r5.complete(r4, r0)     // Catch:{ all -> 0x0049 }
            monitor-exit(r3)
            return
        L_0x000f:
            com.huawei.agconnect.config.AGConnectServicesConfig r0 = com.huawei.agconnect.config.AGConnectServicesConfig.fromContext(r4)     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = "client/app_id"
            java.lang.String r0 = r0.getString(r1)     // Catch:{ all -> 0x0049 }
            com.huawei.hms.aaid.HmsInstanceId r4 = com.huawei.hms.aaid.HmsInstanceId.getInstance(r4)     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = "HCM"
            java.lang.String r4 = r4.getToken(r0, r1)     // Catch:{ all -> 0x0049 }
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0049 }
            if (r0 != 0) goto L_0x0044
            com.onesignal.OneSignal$LOG_LEVEL r0 = com.onesignal.OneSignal.LOG_LEVEL.INFO     // Catch:{ all -> 0x0049 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r1.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "Device registered for HMS, push token = "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            r1.append(r4)     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0049 }
            com.onesignal.OneSignal.Log(r0, r1)     // Catch:{ all -> 0x0049 }
            r0 = 1
            r5.complete(r4, r0)     // Catch:{ all -> 0x0049 }
            goto L_0x0047
        L_0x0044:
            r3.waitForOnNewPushTokenEvent(r5)     // Catch:{ all -> 0x0049 }
        L_0x0047:
            monitor-exit(r3)
            return
        L_0x0049:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.PushRegistratorHMS.getHMSTokenTask(android.content.Context, com.onesignal.PushRegistrator$RegisteredHandler):void");
    }

    private static void doTimeOutWait() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void waitForOnNewPushTokenEvent(PushRegistrator.RegisteredHandler registeredHandler2) {
        doTimeOutWait();
        if (!callbackSuccessful) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "HmsMessageServiceOneSignal.onNewToken timed out.");
            registeredHandler2.complete((String) null, -25);
        }
    }
}
