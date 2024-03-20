package com.onesignal;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.ViewTreeObserver;
import com.onesignal.OSSystemConditionController;
import com.onesignal.OneSignal;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ActivityLifecycleHandler {
    static Activity curActivity;
    static FocusHandlerThread focusHandlerThread = new FocusHandlerThread();
    static boolean nextResumeIsFirstActivity;
    private static Map<String, ActivityAvailableListener> sActivityAvailableListeners = new ConcurrentHashMap();
    private static Map<String, KeyboardListener> sKeyboardListeners = new ConcurrentHashMap();
    private static Map<String, OSSystemConditionController.OSSystemConditionObserver> sSystemConditionObservers = new ConcurrentHashMap();

    static void onActivityCreated(Activity activity) {
    }

    static void onActivityStarted(Activity activity) {
    }

    ActivityLifecycleHandler() {
    }

    static abstract class ActivityAvailableListener {
        /* access modifiers changed from: package-private */
        public void available(Activity activity) {
        }

        /* access modifiers changed from: package-private */
        public void stopped(WeakReference<Activity> weakReference) {
        }

        ActivityAvailableListener() {
        }
    }

    static void setSystemConditionObserver(String str, OSSystemConditionController.OSSystemConditionObserver oSSystemConditionObserver) {
        Activity activity = curActivity;
        if (activity != null) {
            ViewTreeObserver viewTreeObserver = activity.getWindow().getDecorView().getViewTreeObserver();
            KeyboardListener keyboardListener = new KeyboardListener(oSSystemConditionObserver, str);
            viewTreeObserver.addOnGlobalLayoutListener(keyboardListener);
            sKeyboardListeners.put(str, keyboardListener);
        }
        sSystemConditionObservers.put(str, oSSystemConditionObserver);
    }

    static void setActivityAvailableListener(String str, ActivityAvailableListener activityAvailableListener) {
        sActivityAvailableListeners.put(str, activityAvailableListener);
        Activity activity = curActivity;
        if (activity != null) {
            activityAvailableListener.available(activity);
        }
    }

    static void removeSystemConditionObserver(String str) {
        sKeyboardListeners.remove(str);
        sSystemConditionObservers.remove(str);
    }

    static void removeActivityAvailableListener(String str) {
        sActivityAvailableListeners.remove(str);
    }

    private static void setCurActivity(Activity activity) {
        curActivity = activity;
        for (Map.Entry<String, ActivityAvailableListener> value : sActivityAvailableListeners.entrySet()) {
            ((ActivityAvailableListener) value.getValue()).available(curActivity);
        }
        try {
            ViewTreeObserver viewTreeObserver = curActivity.getWindow().getDecorView().getViewTreeObserver();
            for (Map.Entry next : sSystemConditionObservers.entrySet()) {
                KeyboardListener keyboardListener = new KeyboardListener((OSSystemConditionController.OSSystemConditionObserver) next.getValue(), (String) next.getKey());
                viewTreeObserver.addOnGlobalLayoutListener(keyboardListener);
                sKeyboardListeners.put(next.getKey(), keyboardListener);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    static void onConfigurationChanged(Configuration configuration) {
        Activity activity = curActivity;
        if (activity != null && OSUtils.hasConfigChangeFlag(activity, 128)) {
            logOrientationChange(configuration.orientation);
            onOrientationChanged();
        }
    }

    static void onActivityResumed(Activity activity) {
        setCurActivity(activity);
        logCurActivity();
        handleFocus();
    }

    static void onActivityPaused(Activity activity) {
        if (activity == curActivity) {
            curActivity = null;
            handleLostFocus();
        }
        logCurActivity();
    }

    static void onActivityStopped(Activity activity) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "onActivityStopped: " + activity);
        if (activity == curActivity) {
            curActivity = null;
            handleLostFocus();
        }
        for (Map.Entry<String, ActivityAvailableListener> value : sActivityAvailableListeners.entrySet()) {
            ((ActivityAvailableListener) value.getValue()).stopped(new WeakReference(activity));
        }
        logCurActivity();
    }

    static void onActivityDestroyed(Activity activity) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "onActivityDestroyed: " + activity);
        sKeyboardListeners.clear();
        if (activity == curActivity) {
            curActivity = null;
            handleLostFocus();
        }
        logCurActivity();
    }

    private static void logCurActivity() {
        String str;
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        StringBuilder sb = new StringBuilder();
        sb.append("curActivity is NOW: ");
        if (curActivity != null) {
            str = "" + curActivity.getClass().getName() + ":" + curActivity;
        } else {
            str = "null";
        }
        sb.append(str);
        OneSignal.Log(log_level, sb.toString());
    }

    private static void logOrientationChange(int i) {
        if (i == 2) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.onesignalLog(log_level, "Configuration Orientation Change: LANDSCAPE (" + i + ")");
        } else if (i == 1) {
            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.onesignalLog(log_level2, "Configuration Orientation Change: PORTRAIT (" + i + ")");
        }
    }

    private static void onOrientationChanged() {
        handleLostFocus();
        for (Map.Entry<String, ActivityAvailableListener> value : sActivityAvailableListeners.entrySet()) {
            ((ActivityAvailableListener) value.getValue()).stopped(new WeakReference(curActivity));
        }
        for (Map.Entry<String, ActivityAvailableListener> value2 : sActivityAvailableListeners.entrySet()) {
            ((ActivityAvailableListener) value2.getValue()).available(curActivity);
        }
        ViewTreeObserver viewTreeObserver = curActivity.getWindow().getDecorView().getViewTreeObserver();
        for (Map.Entry next : sSystemConditionObservers.entrySet()) {
            KeyboardListener keyboardListener = new KeyboardListener((OSSystemConditionController.OSSystemConditionObserver) next.getValue(), (String) next.getKey());
            viewTreeObserver.addOnGlobalLayoutListener(keyboardListener);
            sKeyboardListeners.put(next.getKey(), keyboardListener);
        }
        handleFocus();
    }

    private static void handleLostFocus() {
        focusHandlerThread.runRunnable(new AppFocusRunnable());
    }

    private static void handleFocus() {
        if (focusHandlerThread.hasBackgrounded() || nextResumeIsFirstActivity) {
            nextResumeIsFirstActivity = false;
            focusHandlerThread.resetBackgroundState();
            OneSignal.onAppFocus();
            return;
        }
        focusHandlerThread.stopScheduledRunnable();
    }

    static class FocusHandlerThread extends HandlerThread {
        private AppFocusRunnable appFocusRunnable;
        private Handler mHandler = new Handler(getLooper());

        FocusHandlerThread() {
            super("FocusHandlerThread");
            start();
        }

        /* access modifiers changed from: package-private */
        public Looper getHandlerLooper() {
            return this.mHandler.getLooper();
        }

        /* access modifiers changed from: package-private */
        public void resetBackgroundState() {
            AppFocusRunnable appFocusRunnable2 = this.appFocusRunnable;
            if (appFocusRunnable2 != null) {
                boolean unused = appFocusRunnable2.backgrounded = false;
            }
        }

        /* access modifiers changed from: package-private */
        public void stopScheduledRunnable() {
            this.mHandler.removeCallbacksAndMessages((Object) null);
        }

        /* access modifiers changed from: package-private */
        public void runRunnable(AppFocusRunnable appFocusRunnable2) {
            AppFocusRunnable appFocusRunnable3 = this.appFocusRunnable;
            if (appFocusRunnable3 == null || !appFocusRunnable3.backgrounded || this.appFocusRunnable.completed) {
                this.appFocusRunnable = appFocusRunnable2;
                this.mHandler.removeCallbacksAndMessages((Object) null);
                this.mHandler.postDelayed(appFocusRunnable2, 2000);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean hasBackgrounded() {
            AppFocusRunnable appFocusRunnable2 = this.appFocusRunnable;
            return appFocusRunnable2 != null && appFocusRunnable2.backgrounded;
        }
    }

    private static class AppFocusRunnable implements Runnable {
        /* access modifiers changed from: private */
        public boolean backgrounded;
        /* access modifiers changed from: private */
        public boolean completed;

        private AppFocusRunnable() {
        }

        public void run() {
            if (ActivityLifecycleHandler.curActivity == null) {
                this.backgrounded = true;
                OneSignal.onAppLostFocus();
                this.completed = true;
            }
        }
    }

    private static class KeyboardListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private final String key;
        private final OSSystemConditionController.OSSystemConditionObserver observer;

        private KeyboardListener(OSSystemConditionController.OSSystemConditionObserver oSSystemConditionObserver, String str) {
            this.observer = oSSystemConditionObserver;
            this.key = str;
        }

        public void onGlobalLayout() {
            if (!OSViewUtils.isKeyboardUp(new WeakReference(ActivityLifecycleHandler.curActivity))) {
                if (ActivityLifecycleHandler.curActivity != null) {
                    ViewTreeObserver viewTreeObserver = ActivityLifecycleHandler.curActivity.getWindow().getDecorView().getViewTreeObserver();
                    if (Build.VERSION.SDK_INT < 16) {
                        viewTreeObserver.removeGlobalOnLayoutListener(this);
                    } else {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    }
                }
                ActivityLifecycleHandler.removeSystemConditionObserver(this.key);
                this.observer.systemConditionChanged();
            }
        }
    }
}
