package org.acra.builder;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import org.acra.ACRA;
import org.acra.log.ACRALog;

public final class LastActivityManager {
    /* access modifiers changed from: private */
    public WeakReference<Activity> lastActivityCreated = new WeakReference<>((Object) null);

    public LastActivityManager(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
                if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "onActivityCreated " + activity.getClass());
                }
                WeakReference unused = LastActivityManager.this.lastActivityCreated = new WeakReference(activity);
            }

            public void onActivityStarted(Activity activity) {
                if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "onActivityStarted " + activity.getClass());
                }
            }

            public void onActivityResumed(Activity activity) {
                if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "onActivityResumed " + activity.getClass());
                }
            }

            public void onActivityPaused(Activity activity) {
                if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "onActivityPaused " + activity.getClass());
                }
            }

            public void onActivityStopped(Activity activity) {
                if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "onActivityStopped " + activity.getClass());
                }
                synchronized (this) {
                    notify();
                }
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "onActivitySaveInstanceState " + activity.getClass());
                }
            }

            public void onActivityDestroyed(Activity activity) {
                if (ACRA.DEV_LOGGING) {
                    ACRALog aCRALog = ACRA.log;
                    String str = ACRA.LOG_TAG;
                    aCRALog.d(str, "onActivityDestroyed " + activity.getClass());
                }
            }
        });
    }

    public Activity getLastActivity() {
        return (Activity) this.lastActivityCreated.get();
    }

    public void clearLastActivity() {
        this.lastActivityCreated.clear();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0008 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void waitForActivityStop(int r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            long r0 = (long) r3
            r2.wait(r0)     // Catch:{ InterruptedException -> 0x0008 }
            goto L_0x0008
        L_0x0006:
            r3 = move-exception
            goto L_0x000a
        L_0x0008:
            monitor-exit(r2)     // Catch:{ all -> 0x0006 }
            return
        L_0x000a:
            monitor-exit(r2)     // Catch:{ all -> 0x0006 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.acra.builder.LastActivityManager.waitForActivityStop(int):void");
    }
}
