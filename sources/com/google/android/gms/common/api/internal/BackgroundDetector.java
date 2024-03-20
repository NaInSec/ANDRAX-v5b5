package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final BackgroundDetector zzas = new BackgroundDetector();
    private final AtomicBoolean zzat = new AtomicBoolean();
    private final AtomicBoolean zzau = new AtomicBoolean();
    private final ArrayList<BackgroundStateChangeListener> zzav = new ArrayList<>();
    private boolean zzaw = false;

    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean z);
    }

    private BackgroundDetector() {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public static BackgroundDetector getInstance() {
        return zzas;
    }

    public static void initialize(Application application) {
        synchronized (zzas) {
            if (!zzas.zzaw) {
                application.registerActivityLifecycleCallbacks(zzas);
                application.registerComponentCallbacks(zzas);
                zzas.zzaw = true;
            }
        }
    }

    public final boolean readCurrentStateIfPossible(boolean z) {
        if (!this.zzau.get()) {
            if (!PlatformVersion.isAtLeastJellyBean()) {
                return z;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.zzau.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.zzat.set(true);
            }
        }
        return isInBackground();
    }

    public final boolean isInBackground() {
        return this.zzat.get();
    }

    public final void addListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        synchronized (zzas) {
            this.zzav.add(backgroundStateChangeListener);
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.zzat.compareAndSet(true, false);
        this.zzau.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.zzat.compareAndSet(true, false);
        this.zzau.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    public final void onTrimMemory(int i) {
        if (i == 20 && this.zzat.compareAndSet(false, true)) {
            this.zzau.set(true);
            onBackgroundStateChanged(true);
        }
    }

    private final void onBackgroundStateChanged(boolean z) {
        synchronized (zzas) {
            ArrayList arrayList = this.zzav;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((BackgroundStateChangeListener) obj).onBackgroundStateChanged(z);
            }
        }
    }
}
