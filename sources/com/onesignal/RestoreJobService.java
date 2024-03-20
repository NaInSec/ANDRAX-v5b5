package com.onesignal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.onesignal.NotificationExtenderService;

public class RestoreJobService extends JobIntentService {
    static final int RESTORE_SERVICE_JOB_ID = 2071862122;

    public /* bridge */ /* synthetic */ boolean isStopped() {
        return super.isStopped();
    }

    public /* bridge */ /* synthetic */ IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    public /* bridge */ /* synthetic */ boolean onStopCurrentWork() {
        return super.onStopCurrentWork();
    }

    public /* bridge */ /* synthetic */ void setInterruptIfStopped(boolean z) {
        super.setInterruptIfStopped(z);
    }

    /* access modifiers changed from: protected */
    public final void onHandleWork(Intent intent) {
        Bundle extras;
        if (intent != null && (extras = intent.getExtras()) != null) {
            NotificationBundleProcessor.ProcessFromGCMIntentService(getApplicationContext(), new BundleCompatBundle(extras), (NotificationExtenderService.OverrideSettings) null);
        }
    }
}
