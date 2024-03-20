package org.acra.util;

import android.app.Activity;
import android.content.Context;
import android.os.Process;
import org.acra.ACRA;
import org.acra.builder.LastActivityManager;
import org.acra.config.CoreConfiguration;
import org.acra.log.ACRALog;

public final class ProcessFinisher {
    private final CoreConfiguration config;
    private final Context context;
    private final LastActivityManager lastActivityManager;

    public ProcessFinisher(Context context2, CoreConfiguration coreConfiguration, LastActivityManager lastActivityManager2) {
        this.context = context2;
        this.config = coreConfiguration;
        this.lastActivityManager = lastActivityManager2;
    }

    public void endApplication() {
        stopServices();
        killProcessAndExit();
    }

    public void finishLastActivity(Thread thread) {
        Activity lastActivity = this.lastActivityManager.getLastActivity();
        if (lastActivity != null) {
            boolean z = thread == lastActivity.getMainLooper().getThread();
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Finishing the last Activity prior to killing the Process");
            }
            $$Lambda$ProcessFinisher$XnjEXj1db0EzhCdHKgkGX9jhpB8 r1 = new Runnable(lastActivity) {
                private final /* synthetic */ Activity f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    ProcessFinisher.lambda$finishLastActivity$0(this.f$0);
                }
            };
            if (z) {
                r1.run();
            } else {
                lastActivity.runOnUiThread(r1);
            }
            if (!z) {
                this.lastActivityManager.waitForActivityStop(100);
            }
            this.lastActivityManager.clearLastActivity();
        }
    }

    static /* synthetic */ void lambda$finishLastActivity$0(Activity activity) {
        activity.finish();
        if (ACRA.DEV_LOGGING) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.d(str, "Finished " + activity.getClass());
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:10|11|12|13|(2:15|24)(1:23)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x004f */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0053 A[Catch:{ ServiceNotReachedException -> 0x0077 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x001d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void stopServices() {
        /*
            r7 = this;
            org.acra.config.CoreConfiguration r0 = r7.config
            boolean r0 = r0.stopServicesOnCrash()
            if (r0 == 0) goto L_0x0081
            android.content.Context r0 = r7.context     // Catch:{ ServiceNotReachedException -> 0x0077 }
            android.app.ActivityManager r0 = org.acra.util.SystemServices.getActivityManager(r0)     // Catch:{ ServiceNotReachedException -> 0x0077 }
            r1 = 2147483647(0x7fffffff, float:NaN)
            java.util.List r0 = r0.getRunningServices(r1)     // Catch:{ ServiceNotReachedException -> 0x0077 }
            int r1 = android.os.Process.myPid()     // Catch:{ ServiceNotReachedException -> 0x0077 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ ServiceNotReachedException -> 0x0077 }
        L_0x001d:
            boolean r2 = r0.hasNext()     // Catch:{ ServiceNotReachedException -> 0x0077 }
            if (r2 == 0) goto L_0x0081
            java.lang.Object r2 = r0.next()     // Catch:{ ServiceNotReachedException -> 0x0077 }
            android.app.ActivityManager$RunningServiceInfo r2 = (android.app.ActivityManager.RunningServiceInfo) r2     // Catch:{ ServiceNotReachedException -> 0x0077 }
            int r3 = r2.pid     // Catch:{ ServiceNotReachedException -> 0x0077 }
            if (r3 != r1) goto L_0x001d
            java.lang.Class<org.acra.sender.SenderService> r3 = org.acra.sender.SenderService.class
            java.lang.String r3 = r3.getName()     // Catch:{ ServiceNotReachedException -> 0x0077 }
            android.content.ComponentName r4 = r2.service     // Catch:{ ServiceNotReachedException -> 0x0077 }
            java.lang.String r4 = r4.getClassName()     // Catch:{ ServiceNotReachedException -> 0x0077 }
            boolean r3 = r3.equals(r4)     // Catch:{ ServiceNotReachedException -> 0x0077 }
            if (r3 != 0) goto L_0x001d
            android.content.Intent r3 = new android.content.Intent     // Catch:{ SecurityException -> 0x004f }
            r3.<init>()     // Catch:{ SecurityException -> 0x004f }
            android.content.ComponentName r4 = r2.service     // Catch:{ SecurityException -> 0x004f }
            r3.setComponent(r4)     // Catch:{ SecurityException -> 0x004f }
            android.content.Context r4 = r7.context     // Catch:{ SecurityException -> 0x004f }
            r4.stopService(r3)     // Catch:{ SecurityException -> 0x004f }
            goto L_0x001d
        L_0x004f:
            boolean r3 = org.acra.ACRA.DEV_LOGGING     // Catch:{ ServiceNotReachedException -> 0x0077 }
            if (r3 == 0) goto L_0x001d
            org.acra.log.ACRALog r3 = org.acra.ACRA.log     // Catch:{ ServiceNotReachedException -> 0x0077 }
            java.lang.String r4 = org.acra.ACRA.LOG_TAG     // Catch:{ ServiceNotReachedException -> 0x0077 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ ServiceNotReachedException -> 0x0077 }
            r5.<init>()     // Catch:{ ServiceNotReachedException -> 0x0077 }
            java.lang.String r6 = "Unable to stop Service "
            r5.append(r6)     // Catch:{ ServiceNotReachedException -> 0x0077 }
            android.content.ComponentName r2 = r2.service     // Catch:{ ServiceNotReachedException -> 0x0077 }
            java.lang.String r2 = r2.getClassName()     // Catch:{ ServiceNotReachedException -> 0x0077 }
            r5.append(r2)     // Catch:{ ServiceNotReachedException -> 0x0077 }
            java.lang.String r2 = ". Permission denied"
            r5.append(r2)     // Catch:{ ServiceNotReachedException -> 0x0077 }
            java.lang.String r2 = r5.toString()     // Catch:{ ServiceNotReachedException -> 0x0077 }
            r3.d(r4, r2)     // Catch:{ ServiceNotReachedException -> 0x0077 }
            goto L_0x001d
        L_0x0077:
            r0 = move-exception
            org.acra.log.ACRALog r1 = org.acra.ACRA.log
            java.lang.String r2 = org.acra.ACRA.LOG_TAG
            java.lang.String r3 = "Unable to stop services"
            r1.e(r2, r3, r0)
        L_0x0081:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.util.ProcessFinisher.stopServices():void");
    }

    private void killProcessAndExit() {
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}
