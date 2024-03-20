package com.onesignal;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import com.onesignal.OneSignal;
import java.util.HashMap;
import java.util.Set;

class OneSignalPrefs {
    static final String PREFS_EXISTING_PURCHASES = "ExistingPurchases";
    public static final String PREFS_GT_APP_ID = "GT_APP_ID";
    public static final String PREFS_GT_DO_NOT_SHOW_MISSING_GPS = "GT_DO_NOT_SHOW_MISSING_GPS";
    public static final String PREFS_GT_FIREBASE_TRACKING_ENABLED = "GT_FIREBASE_TRACKING_ENABLED";
    public static final String PREFS_GT_PLAYER_ID = "GT_PLAYER_ID";
    public static final String PREFS_GT_REGISTRATION_ID = "GT_REGISTRATION_ID";
    public static final String PREFS_GT_SOUND_ENABLED = "GT_SOUND_ENABLED";
    public static final String PREFS_GT_UNSENT_ACTIVE_TIME = "GT_UNSENT_ACTIVE_TIME";
    public static final String PREFS_GT_VIBRATE_ENABLED = "GT_VIBRATE_ENABLED";
    public static final String PREFS_ONESIGNAL = OneSignal.class.getSimpleName();
    public static final String PREFS_ONESIGNAL_ACCEPTED_NOTIFICATION_LAST = "ONESIGNAL_ACCEPTED_NOTIFICATION_LAST";
    public static final String PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST = "PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST";
    public static final String PREFS_ONESIGNAL_EMAIL_ID_LAST = "PREFS_ONESIGNAL_EMAIL_ID_LAST";
    public static final String PREFS_ONESIGNAL_PERMISSION_ACCEPTED_LAST = "ONESIGNAL_PERMISSION_ACCEPTED_LAST";
    public static final String PREFS_ONESIGNAL_PLAYER_ID_LAST = "ONESIGNAL_PLAYER_ID_LAST";
    public static final String PREFS_ONESIGNAL_PUSH_TOKEN_LAST = "ONESIGNAL_PUSH_TOKEN_LAST";
    public static final String PREFS_ONESIGNAL_SUBSCRIPTION = "ONESIGNAL_SUBSCRIPTION";
    public static final String PREFS_ONESIGNAL_SUBSCRIPTION_LAST = "ONESIGNAL_SUBSCRIPTION_LAST";
    public static final String PREFS_ONESIGNAL_SYNCED_SUBSCRIPTION = "ONESIGNAL_SYNCED_SUBSCRIPTION";
    public static final String PREFS_ONESIGNAL_USERSTATE_DEPENDVALYES_ = "ONESIGNAL_USERSTATE_DEPENDVALYES_";
    public static final String PREFS_ONESIGNAL_USERSTATE_SYNCVALYES_ = "ONESIGNAL_USERSTATE_SYNCVALYES_";
    public static final String PREFS_ONESIGNAL_USER_PROVIDED_CONSENT = "ONESIGNAL_USER_PROVIDED_CONSENT";
    public static final String PREFS_OS_ATTRIBUTED_INFLUENCES = "PREFS_OS_ATTRIBUTED_INFLUENCES";
    public static final String PREFS_OS_CACHED_IAMS = "PREFS_OS_CACHED_IAMS";
    public static final String PREFS_OS_CLEAR_GROUP_SUMMARY_CLICK = "OS_CLEAR_GROUP_SUMMARY_CLICK";
    public static final String PREFS_OS_CLICKED_CLICK_IDS_IAMS = "PREFS_OS_CLICKED_CLICK_IDS_IAMS";
    public static final String PREFS_OS_DISMISSED_IAMS = "PREFS_OS_DISPLAYED_IAMS";
    public static final String PREFS_OS_EMAIL_ID = "OS_EMAIL_ID";
    public static final String PREFS_OS_ETAG_PREFIX = "PREFS_OS_ETAG_PREFIX_";
    public static final String PREFS_OS_FILTER_OTHER_GCM_RECEIVERS = "OS_FILTER_OTHER_GCM_RECEIVERS";
    public static final String PREFS_OS_HTTP_CACHE_PREFIX = "PREFS_OS_HTTP_CACHE_PREFIX_";
    public static final String PREFS_OS_IMPRESSIONED_IAMS = "PREFS_OS_IMPRESSIONED_IAMS";
    public static final String PREFS_OS_LAST_LOCATION_TIME = "OS_LAST_LOCATION_TIME";
    public static final String PREFS_OS_LAST_SESSION_TIME = "OS_LAST_SESSION_TIME";
    static final String PREFS_OS_OUTCOMES_V2 = "PREFS_OS_OUTCOMES_V2";
    public static final String PREFS_OS_RECEIVE_RECEIPTS_ENABLED = "PREFS_OS_RECEIVE_RECEIPTS_ENABLED";
    public static final String PREFS_OS_RESTORE_TTL_FILTER = "OS_RESTORE_TTL_FILTER";
    public static final String PREFS_OS_UNSENT_ATTRIBUTED_ACTIVE_TIME = "OS_UNSENT_ATTRIBUTED_ACTIVE_TIME";
    public static final String PREFS_PLAYER_PURCHASES = "GTPlayerPurchases";
    static final String PREFS_PURCHASE_TOKENS = "purchaseTokens";
    public static final String PREFS_TRIGGERS = "OneSignalTriggers";
    public static WritePrefHandlerThread prefsHandler;
    static HashMap<String, HashMap<String, Object>> prefsToApply;

    OneSignalPrefs() {
    }

    static {
        initializePool();
    }

    public static class WritePrefHandlerThread extends HandlerThread {
        private static final int WRITE_CALL_DELAY_TO_BUFFER_MS = 200;
        private long lastSyncTime = 0;
        private Handler mHandler;
        private boolean threadStartCalled;

        WritePrefHandlerThread(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        public void onLooperPrepared() {
            super.onLooperPrepared();
            this.mHandler = new Handler(getLooper());
            scheduleFlushToDisk();
        }

        /* access modifiers changed from: private */
        public synchronized void startDelayedWrite() {
            if (OneSignal.appContext != null) {
                startThread();
                scheduleFlushToDisk();
            }
        }

        private void startThread() {
            if (!this.threadStartCalled) {
                start();
                this.threadStartCalled = true;
            }
        }

        private synchronized void scheduleFlushToDisk() {
            if (this.mHandler != null) {
                this.mHandler.removeCallbacksAndMessages((Object) null);
                if (this.lastSyncTime == 0) {
                    this.lastSyncTime = System.currentTimeMillis();
                }
                AnonymousClass1 r2 = new Runnable() {
                    public void run() {
                        WritePrefHandlerThread.this.flushBufferToDisk();
                    }
                };
                this.mHandler.postDelayed(r2, (this.lastSyncTime - System.currentTimeMillis()) + 200);
            }
        }

        /* access modifiers changed from: private */
        public void flushBufferToDisk() {
            for (String next : OneSignalPrefs.prefsToApply.keySet()) {
                SharedPreferences.Editor edit = OneSignalPrefs.getSharedPrefsByName(next).edit();
                HashMap hashMap = OneSignalPrefs.prefsToApply.get(next);
                synchronized (hashMap) {
                    for (String str : hashMap.keySet()) {
                        Object obj = hashMap.get(str);
                        if (obj instanceof String) {
                            edit.putString(str, (String) obj);
                        } else if (obj instanceof Boolean) {
                            edit.putBoolean(str, ((Boolean) obj).booleanValue());
                        } else if (obj instanceof Integer) {
                            edit.putInt(str, ((Integer) obj).intValue());
                        } else if (obj instanceof Long) {
                            edit.putLong(str, ((Long) obj).longValue());
                        } else if (obj instanceof Set) {
                            edit.putStringSet(str, (Set) obj);
                        }
                    }
                    hashMap.clear();
                }
                edit.apply();
            }
            this.lastSyncTime = System.currentTimeMillis();
        }
    }

    public static void initializePool() {
        prefsToApply = new HashMap<>();
        prefsToApply.put(PREFS_ONESIGNAL, new HashMap());
        prefsToApply.put(PREFS_PLAYER_PURCHASES, new HashMap());
        prefsToApply.put(PREFS_TRIGGERS, new HashMap());
        prefsHandler = new WritePrefHandlerThread("OSH_WritePrefs");
    }

    public static void startDelayedWrite() {
        prefsHandler.startDelayedWrite();
    }

    public static void saveString(String str, String str2, String str3) {
        save(str, str2, str3);
    }

    public static void saveStringSet(String str, String str2, Set<String> set) {
        save(str, str2, set);
    }

    public static void saveBool(String str, String str2, boolean z) {
        save(str, str2, Boolean.valueOf(z));
    }

    public static void saveInt(String str, String str2, int i) {
        save(str, str2, Integer.valueOf(i));
    }

    public static void saveLong(String str, String str2, long j) {
        save(str, str2, Long.valueOf(j));
    }

    public static void saveObject(String str, String str2, Object obj) {
        save(str, str2, obj);
    }

    private static void save(String str, String str2, Object obj) {
        HashMap hashMap = prefsToApply.get(str);
        synchronized (hashMap) {
            hashMap.put(str2, obj);
        }
        startDelayedWrite();
    }

    static String getString(String str, String str2, String str3) {
        return (String) get(str, str2, String.class, str3);
    }

    static boolean getBool(String str, String str2, boolean z) {
        return ((Boolean) get(str, str2, Boolean.class, Boolean.valueOf(z))).booleanValue();
    }

    static int getInt(String str, String str2, int i) {
        return ((Integer) get(str, str2, Integer.class, Integer.valueOf(i))).intValue();
    }

    static long getLong(String str, String str2, long j) {
        return ((Long) get(str, str2, Long.class, Long.valueOf(j))).longValue();
    }

    public static Set<String> getStringSet(String str, String str2, Set<String> set) {
        return (Set) get(str, str2, Set.class, set);
    }

    static Object getObject(String str, String str2, Object obj) {
        return get(str, str2, Object.class, obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        r3 = getSharedPrefsByName(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        if (r3 == null) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0039, code lost:
        if (r5.equals(java.lang.String.class) == false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0041, code lost:
        return r3.getString(r4, (java.lang.String) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
        if (r5.equals(java.lang.Boolean.class) == false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0058, code lost:
        return java.lang.Boolean.valueOf(r3.getBoolean(r4, ((java.lang.Boolean) r6).booleanValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005f, code lost:
        if (r5.equals(java.lang.Integer.class) == false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006f, code lost:
        return java.lang.Integer.valueOf(r3.getInt(r4, ((java.lang.Integer) r6).intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0076, code lost:
        if (r5.equals(java.lang.Long.class) == false) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0086, code lost:
        return java.lang.Long.valueOf(r3.getLong(r4, ((java.lang.Long) r6).longValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008d, code lost:
        if (r5.equals(java.util.Set.class) == false) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
        return r3.getStringSet(r4, (java.util.Set) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009c, code lost:
        if (r5.equals(java.lang.Object.class) == false) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a6, code lost:
        return java.lang.Boolean.valueOf(r3.contains(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a7, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a9, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object get(java.lang.String r3, java.lang.String r4, java.lang.Class r5, java.lang.Object r6) {
        /*
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, java.lang.Object>> r0 = prefsToApply
            java.lang.Object r0 = r0.get(r3)
            java.util.HashMap r0 = (java.util.HashMap) r0
            monitor-enter(r0)
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            boolean r1 = r5.equals(r1)     // Catch:{ all -> 0x00ac }
            if (r1 == 0) goto L_0x001e
            boolean r1 = r0.containsKey(r4)     // Catch:{ all -> 0x00ac }
            if (r1 == 0) goto L_0x001e
            r3 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x00ac }
            monitor-exit(r0)     // Catch:{ all -> 0x00ac }
            return r3
        L_0x001e:
            java.lang.Object r1 = r0.get(r4)     // Catch:{ all -> 0x00ac }
            if (r1 != 0) goto L_0x00aa
            boolean r2 = r0.containsKey(r4)     // Catch:{ all -> 0x00ac }
            if (r2 == 0) goto L_0x002c
            goto L_0x00aa
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x00ac }
            android.content.SharedPreferences r3 = getSharedPrefsByName(r3)
            if (r3 == 0) goto L_0x00a9
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0042
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r3 = r3.getString(r4, r6)
            return r3
        L_0x0042:
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0059
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r5 = r6.booleanValue()
            boolean r3 = r3.getBoolean(r4, r5)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            return r3
        L_0x0059:
            java.lang.Class<java.lang.Integer> r0 = java.lang.Integer.class
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0070
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r5 = r6.intValue()
            int r3 = r3.getInt(r4, r5)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            return r3
        L_0x0070:
            java.lang.Class<java.lang.Long> r0 = java.lang.Long.class
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0087
            java.lang.Long r6 = (java.lang.Long) r6
            long r5 = r6.longValue()
            long r3 = r3.getLong(r4, r5)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            return r3
        L_0x0087:
            java.lang.Class<java.util.Set> r0 = java.util.Set.class
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0096
            java.util.Set r6 = (java.util.Set) r6
            java.util.Set r3 = r3.getStringSet(r4, r6)
            return r3
        L_0x0096:
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x00a7
            boolean r3 = r3.contains(r4)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            return r3
        L_0x00a7:
            r3 = 0
            return r3
        L_0x00a9:
            return r6
        L_0x00aa:
            monitor-exit(r0)     // Catch:{ all -> 0x00ac }
            return r1
        L_0x00ac:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ac }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OneSignalPrefs.get(java.lang.String, java.lang.String, java.lang.Class, java.lang.Object):java.lang.Object");
    }

    static synchronized SharedPreferences getSharedPrefsByName(String str) {
        synchronized (OneSignalPrefs.class) {
            if (OneSignal.appContext == null) {
                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "OneSignal.appContext null, could not read " + str + " from getSharedPreferences.", new Throwable());
                return null;
            }
            SharedPreferences sharedPreferences = OneSignal.appContext.getSharedPreferences(str, 0);
            return sharedPreferences;
        }
    }
}
