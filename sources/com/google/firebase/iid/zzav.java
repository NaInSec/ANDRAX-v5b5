package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.thecrackertechnology.dragonterminal.backend.KeyHandler;
import java.util.ArrayDeque;
import java.util.Queue;

public final class zzav {
    private static zzav zzcx;
    private final SimpleArrayMap<String, String> zzcy = new SimpleArrayMap<>();
    private Boolean zzcz = null;
    final Queue<Intent> zzda = new ArrayDeque();
    private final Queue<Intent> zzdb = new ArrayDeque();

    public static synchronized zzav zzai() {
        zzav zzav;
        synchronized (zzav.class) {
            if (zzcx == null) {
                zzcx = new zzav();
            }
            zzav = zzcx;
        }
        return zzav;
    }

    private zzav() {
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getBroadcast(context, i, zza(context, "com.google.firebase.MESSAGING_EVENT", intent), KeyHandler.KEYMOD_CTRL);
    }

    public static void zzb(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.INSTANCE_ID_EVENT", intent));
    }

    public static void zzc(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.MESSAGING_EVENT", intent));
    }

    private static Intent zza(Context context, String str, Intent intent) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return intent2;
    }

    public final Intent zzaj() {
        return this.zzdb.poll();
    }

    public final int zzb(Context context, String str, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Starting service: ".concat(valueOf) : new String("Starting service: "));
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -842411455) {
            if (hashCode == 41532704 && str.equals("com.google.firebase.MESSAGING_EVENT")) {
                c = 1;
            }
        } else if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
            c = 0;
        }
        if (c == 0) {
            this.zzda.offer(intent);
        } else if (c != 1) {
            String valueOf2 = String.valueOf(str);
            Log.w("FirebaseInstanceId", valueOf2.length() != 0 ? "Unknown service action: ".concat(valueOf2) : new String("Unknown service action: "));
            return 500;
        } else {
            this.zzdb.offer(intent);
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return zzd(context, intent2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00db A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f2 A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f7 A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0104 A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x010e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzd(android.content.Context r6, android.content.Intent r7) {
        /*
            r5 = this;
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.lang.String> r0 = r5.zzcy
            monitor-enter(r0)
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.lang.String> r1 = r5.zzcy     // Catch:{ all -> 0x0143 }
            java.lang.String r2 = r7.getAction()     // Catch:{ all -> 0x0143 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0143 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0143 }
            monitor-exit(r0)     // Catch:{ all -> 0x0143 }
            r0 = 0
            if (r1 != 0) goto L_0x00ab
            android.content.pm.PackageManager r1 = r6.getPackageManager()
            android.content.pm.ResolveInfo r1 = r1.resolveService(r7, r0)
            if (r1 == 0) goto L_0x00a3
            android.content.pm.ServiceInfo r2 = r1.serviceInfo
            if (r2 != 0) goto L_0x0023
            goto L_0x00a3
        L_0x0023:
            android.content.pm.ServiceInfo r1 = r1.serviceInfo
            java.lang.String r2 = r6.getPackageName()
            java.lang.String r3 = r1.packageName
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x006d
            java.lang.String r2 = r1.name
            if (r2 != 0) goto L_0x0036
            goto L_0x006d
        L_0x0036:
            java.lang.String r1 = r1.name
            java.lang.String r2 = "."
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L_0x005c
            java.lang.String r2 = r6.getPackageName()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r3 = r1.length()
            if (r3 == 0) goto L_0x0057
            java.lang.String r1 = r2.concat(r1)
            goto L_0x005c
        L_0x0057:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
        L_0x005c:
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.lang.String> r2 = r5.zzcy
            monitor-enter(r2)
            android.support.v4.util.SimpleArrayMap<java.lang.String, java.lang.String> r3 = r5.zzcy     // Catch:{ all -> 0x006a }
            java.lang.String r4 = r7.getAction()     // Catch:{ all -> 0x006a }
            r3.put(r4, r1)     // Catch:{ all -> 0x006a }
            monitor-exit(r2)     // Catch:{ all -> 0x006a }
            goto L_0x00ab
        L_0x006a:
            r6 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x006a }
            throw r6
        L_0x006d:
            java.lang.String r2 = r1.packageName
            java.lang.String r1 = r1.name
            java.lang.String r3 = java.lang.String.valueOf(r2)
            int r3 = r3.length()
            int r3 = r3 + 94
            java.lang.String r4 = java.lang.String.valueOf(r1)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Error resolving target intent service, skipping classname enforcement. Resolved service was: "
            r4.append(r3)
            r4.append(r2)
            java.lang.String r2 = "/"
            r4.append(r2)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            java.lang.String r2 = "FirebaseInstanceId"
            android.util.Log.e(r2, r1)
            goto L_0x00d7
        L_0x00a3:
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = "Failed to resolve target intent service, skipping classname enforcement"
            android.util.Log.e(r1, r2)
            goto L_0x00d7
        L_0x00ab:
            r2 = 3
            java.lang.String r3 = "FirebaseInstanceId"
            boolean r2 = android.util.Log.isLoggable(r3, r2)
            if (r2 == 0) goto L_0x00d0
            java.lang.String r2 = "Restricting intent to a specific service: "
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r4 = r3.length()
            if (r4 == 0) goto L_0x00c5
            java.lang.String r2 = r2.concat(r3)
            goto L_0x00cb
        L_0x00c5:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r2)
            r2 = r3
        L_0x00cb:
            java.lang.String r3 = "FirebaseInstanceId"
            android.util.Log.d(r3, r2)
        L_0x00d0:
            java.lang.String r2 = r6.getPackageName()
            r7.setClassName(r2, r1)
        L_0x00d7:
            java.lang.Boolean r1 = r5.zzcz     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            if (r1 != 0) goto L_0x00ea
            java.lang.String r1 = "android.permission.WAKE_LOCK"
            int r1 = r6.checkCallingOrSelfPermission(r1)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            if (r1 != 0) goto L_0x00e4
            r0 = 1
        L_0x00e4:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            r5.zzcz = r0     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
        L_0x00ea:
            java.lang.Boolean r0 = r5.zzcz     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            boolean r0 = r0.booleanValue()     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            if (r0 == 0) goto L_0x00f7
            android.content.ComponentName r6 = android.support.v4.content.WakefulBroadcastReceiver.startWakefulService(r6, r7)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            goto L_0x0102
        L_0x00f7:
            android.content.ComponentName r6 = r6.startService(r7)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            java.lang.String r7 = "FirebaseInstanceId"
            java.lang.String r0 = "Missing wake lock permission, service start may be delayed"
            android.util.Log.d(r7, r0)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
        L_0x0102:
            if (r6 != 0) goto L_0x010e
            java.lang.String r6 = "FirebaseInstanceId"
            java.lang.String r7 = "Error while delivering the message: ServiceIntent not found."
            android.util.Log.e(r6, r7)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            r6 = 404(0x194, float:5.66E-43)
            return r6
        L_0x010e:
            r6 = -1
            return r6
        L_0x0110:
            r6 = move-exception
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r7 = java.lang.String.valueOf(r6)
            int r7 = r7.length()
            int r7 = r7 + 45
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r7)
            java.lang.String r7 = "Failed to start service while in background: "
            r0.append(r7)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.lang.String r7 = "FirebaseInstanceId"
            android.util.Log.e(r7, r6)
            r6 = 402(0x192, float:5.63E-43)
            return r6
        L_0x0138:
            r6 = move-exception
            java.lang.String r7 = "FirebaseInstanceId"
            java.lang.String r0 = "Error while delivering the message to the serviceIntent"
            android.util.Log.e(r7, r0, r6)
            r6 = 401(0x191, float:5.62E-43)
            return r6
        L_0x0143:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0143 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzav.zzd(android.content.Context, android.content.Intent):int");
    }
}
