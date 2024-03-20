package com.thecrackertechnology.dragonterminal.services;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import com.onesignal.OneSignalDbContract;
import com.thecrackertechnology.andrax.R;
import com.thecrackertechnology.dragonterminal.backend.EmulatorDebug;
import com.thecrackertechnology.dragonterminal.backend.TerminalSession;
import com.thecrackertechnology.dragonterminal.bridge.SessionId;
import com.thecrackertechnology.dragonterminal.frontend.logging.NLog;
import com.thecrackertechnology.dragonterminal.frontend.session.shell.ShellParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XParameter;
import com.thecrackertechnology.dragonterminal.frontend.session.xorg.XSession;
import com.thecrackertechnology.dragonterminal.ui.term.NeoTermActivity;
import com.thecrackertechnology.dragonterminal.utils.TerminalUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\u0018\u0000 32\u00020\u0001:\u000234B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0018H\u0002J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020#J\u0012\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0018H\u0016J\b\u0010)\u001a\u00020\u0018H\u0016J \u0010*\u001a\u00020+2\u0006\u0010&\u001a\u00020'2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020+H\u0016J\b\u0010.\u001a\u00020\u0018H\u0002J\u000e\u0010/\u001a\u00020+2\u0006\u00100\u001a\u00020\u0005J\u000e\u00101\u001a\u00020+2\u0006\u00100\u001a\u00020\u000eJ\b\u00102\u001a\u00020\u0018H\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0018\u00010\bR\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0018\u00010\u000bR\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0004j\b\u0012\u0004\u0012\u00020\u000e`\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00128F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014¨\u00065"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/services/NeoTermService;", "Landroid/app/Service;", "()V", "mTerminalSessions", "Ljava/util/ArrayList;", "Lcom/thecrackertechnology/dragonterminal/backend/TerminalSession;", "Lkotlin/collections/ArrayList;", "mWakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "mWifiLock", "Landroid/net/wifi/WifiManager$WifiLock;", "Landroid/net/wifi/WifiManager;", "mXSessions", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XSession;", "serviceBinder", "Lcom/thecrackertechnology/dragonterminal/services/NeoTermService$NeoTermBinder;", "sessions", "", "getSessions", "()Ljava/util/List;", "xSessions", "getXSessions", "acquireLock", "", "createNotification", "Landroid/app/Notification;", "createNotificationChannel", "createOrFindSession", "parameter", "Lcom/thecrackertechnology/dragonterminal/frontend/session/shell/ShellParameter;", "createTermSession", "createXSession", "activity", "Landroid/app/Activity;", "Lcom/thecrackertechnology/dragonterminal/frontend/session/xorg/XParameter;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "releaseLock", "removeTermSession", "sessionToRemove", "removeXSession", "updateNotification", "Companion", "NeoTermBinder", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoTermService.kt */
public final class NeoTermService extends Service {
    /* access modifiers changed from: private */
    public static final String ACTION_ACQUIRE_LOCK = ACTION_ACQUIRE_LOCK;
    /* access modifiers changed from: private */
    public static final String ACTION_RELEASE_LOCK = ACTION_RELEASE_LOCK;
    /* access modifiers changed from: private */
    public static final String ACTION_SERVICE_STOP = ACTION_SERVICE_STOP;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String DEFAULT_CHANNEL_ID = DEFAULT_CHANNEL_ID;
    private static final int NOTIFICATION_ID = NOTIFICATION_ID;
    private final ArrayList<TerminalSession> mTerminalSessions = new ArrayList<>();
    private PowerManager.WakeLock mWakeLock;
    private WifiManager.WifiLock mWifiLock;
    private final ArrayList<XSession> mXSessions = new ArrayList<>();
    private final NeoTermBinder serviceBinder = new NeoTermBinder();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0002¨\u0006\b"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/services/NeoTermService$NeoTermBinder;", "Landroid/os/Binder;", "(Lcom/thecrackertechnology/dragonterminal/services/NeoTermService;)V", "service", "Lcom/thecrackertechnology/dragonterminal/services/NeoTermService;", "getService", "()Lcom/thecrackertechnology/dragonterminal/services/NeoTermService;", "setService", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoTermService.kt */
    public final class NeoTermBinder extends Binder {
        private NeoTermService service;

        public NeoTermBinder() {
            this.service = NeoTermService.this;
        }

        public final NeoTermService getService() {
            return this.service;
        }

        public final void setService(NeoTermService neoTermService) {
            Intrinsics.checkParameterIsNotNull(neoTermService, "<set-?>");
            this.service = neoTermService;
        }
    }

    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, createNotification());
    }

    public IBinder onBind(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        return this.serviceBinder;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        String action = intent.getAction();
        if (Intrinsics.areEqual((Object) action, (Object) ACTION_SERVICE_STOP)) {
            int size = this.mTerminalSessions.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.mTerminalSessions.get(i3).finishIfRunning();
            }
            stopSelf();
            return 2;
        } else if (Intrinsics.areEqual((Object) action, (Object) ACTION_ACQUIRE_LOCK)) {
            acquireLock();
            return 2;
        } else if (!Intrinsics.areEqual((Object) action, (Object) ACTION_RELEASE_LOCK)) {
            return 2;
        } else {
            releaseLock();
            return 2;
        }
    }

    public void onDestroy() {
        stopForeground(true);
        int size = this.mTerminalSessions.size();
        for (int i = 0; i < size; i++) {
            this.mTerminalSessions.get(i).finishIfRunning();
        }
        this.mTerminalSessions.clear();
    }

    public final List<TerminalSession> getSessions() {
        return this.mTerminalSessions;
    }

    public final List<XSession> getXSessions() {
        return this.mXSessions;
    }

    public final TerminalSession createTermSession(ShellParameter shellParameter) {
        Intrinsics.checkParameterIsNotNull(shellParameter, "parameter");
        TerminalSession createOrFindSession = createOrFindSession(shellParameter);
        updateNotification();
        return createOrFindSession;
    }

    public final int removeTermSession(TerminalSession terminalSession) {
        Intrinsics.checkParameterIsNotNull(terminalSession, "sessionToRemove");
        int indexOf = this.mTerminalSessions.indexOf(terminalSession);
        if (indexOf >= 0) {
            this.mTerminalSessions.remove(indexOf);
            updateNotification();
        }
        return indexOf;
    }

    public final XSession createXSession(Activity activity, XParameter xParameter) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(xParameter, "parameter");
        XSession createSession = TerminalUtils.INSTANCE.createSession(activity, xParameter);
        this.mXSessions.add(createSession);
        updateNotification();
        return createSession;
    }

    public final int removeXSession(XSession xSession) {
        Intrinsics.checkParameterIsNotNull(xSession, "sessionToRemove");
        int indexOf = this.mXSessions.indexOf(xSession);
        if (indexOf >= 0) {
            this.mXSessions.remove(indexOf);
            updateNotification();
        }
        return indexOf;
    }

    private final TerminalSession createOrFindSession(ShellParameter shellParameter) {
        Object obj;
        if (shellParameter.willCreateNewSession()) {
            NLog.INSTANCE.d("createOrFindSession: creating new session", new Object[0]);
            TerminalSession createSession = TerminalUtils.INSTANCE.createSession((Context) this, shellParameter);
            this.mTerminalSessions.add(createSession);
            return createSession;
        }
        SessionId sessionId = shellParameter.getSessionId();
        if (sessionId == null) {
            Intrinsics.throwNpe();
        }
        NLog nLog = NLog.INSTANCE;
        nLog.d("createOrFindSession: find session by id " + sessionId, new Object[0]);
        Iterator it = this.mTerminalSessions.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((TerminalSession) obj).mHandle, (Object) sessionId.getSessionId())) {
                break;
            }
        }
        TerminalSession terminalSession = (TerminalSession) obj;
        if (terminalSession != null) {
            terminalSession.write(Intrinsics.stringPlus(shellParameter.getInitialCommand(), StringUtils.LF));
            return terminalSession;
        }
        throw new IllegalArgumentException("cannot find session by given id");
    }

    private final void updateNotification() {
        Object systemService = getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME);
        if (systemService != null) {
            ((NotificationManager) systemService).notify(NOTIFICATION_ID, createNotification());
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
    }

    private final Notification createNotification() {
        Class<NeoTermService> cls = NeoTermService.class;
        Context context = this;
        Intent intent = new Intent(context, NeoTermActivity.class);
        intent.addFlags(268435456);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 0);
        int size = this.mTerminalSessions.size();
        this.mXSessions.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Dragon Terminal is Hacking with ");
        int i = 1;
        sb.append(getString(R.string.service_status_text, new Object[]{Integer.valueOf(size)}));
        String sb2 = sb.toString();
        boolean z = this.mWakeLock != null;
        if (z) {
            sb2 = sb2 + StringUtils.SPACE + getString(R.string.service_lock_acquired);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID);
        builder.setContentTitle("Dragon Terminal");
        CharSequence charSequence = sb2;
        builder.setContentText(charSequence);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(charSequence));
        builder.setSmallIcon(R.drawable.ic_terminal_running);
        builder.setContentIntent(activity);
        builder.setOngoing(true);
        builder.setShowWhen(false);
        builder.setColor((int) 4278190080L);
        if (!z) {
            i = -1;
        }
        builder.setPriority(i);
        builder.addAction(17301533, getString(R.string.exit), PendingIntent.getService(context, 0, new Intent(context, cls).setAction(ACTION_SERVICE_STOP), 0));
        builder.addAction(z ? 17301535 : 17301551, getString(z ? R.string.service_release_lock : R.string.service_acquire_lock), PendingIntent.getService(context, 0, new Intent(context, cls).setAction(z ? ACTION_RELEASE_LOCK : ACTION_ACQUIRE_LOCK), 0));
        Notification build = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "builder.build()");
        return build;
    }

    private final void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(DEFAULT_CHANNEL_ID, "Dragon Terminal", 4);
            notificationChannel.setDescription("Dragon Terminal notifications");
            Object systemService = getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME);
            if (systemService != null) {
                ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
        }
    }

    private final void acquireLock() {
        if (this.mWakeLock == null) {
            Object systemService = getSystemService("power");
            if (systemService != null) {
                this.mWakeLock = ((PowerManager) systemService).newWakeLock(1, "NeoTerm-Emulator:");
                PowerManager.WakeLock wakeLock = this.mWakeLock;
                if (wakeLock == null) {
                    Intrinsics.throwNpe();
                }
                wakeLock.acquire();
                Object systemService2 = getApplicationContext().getSystemService("wifi");
                if (systemService2 != null) {
                    this.mWifiLock = ((WifiManager) systemService2).createWifiLock(3, EmulatorDebug.LOG_TAG);
                    WifiManager.WifiLock wifiLock = this.mWifiLock;
                    if (wifiLock == null) {
                        Intrinsics.throwNpe();
                    }
                    wifiLock.acquire();
                    updateNotification();
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
            }
            throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
        }
    }

    private final void releaseLock() {
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            if (wakeLock == null) {
                Intrinsics.throwNpe();
            }
            wakeLock.release();
            this.mWakeLock = null;
            WifiManager.WifiLock wifiLock = this.mWifiLock;
            if (wifiLock == null) {
                Intrinsics.throwNpe();
            }
            wifiLock.release();
            this.mWifiLock = null;
            updateNotification();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u000e\u0010\r\u001a\u00020\u000eXD¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/services/NeoTermService$Companion;", "", "()V", "ACTION_ACQUIRE_LOCK", "", "getACTION_ACQUIRE_LOCK", "()Ljava/lang/String;", "ACTION_RELEASE_LOCK", "getACTION_RELEASE_LOCK", "ACTION_SERVICE_STOP", "getACTION_SERVICE_STOP", "DEFAULT_CHANNEL_ID", "getDEFAULT_CHANNEL_ID", "NOTIFICATION_ID", "", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: NeoTermService.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getACTION_SERVICE_STOP() {
            return NeoTermService.ACTION_SERVICE_STOP;
        }

        public final String getACTION_ACQUIRE_LOCK() {
            return NeoTermService.ACTION_ACQUIRE_LOCK;
        }

        public final String getACTION_RELEASE_LOCK() {
            return NeoTermService.ACTION_RELEASE_LOCK;
        }

        public final String getDEFAULT_CHANNEL_ID() {
            return NeoTermService.DEFAULT_CHANNEL_ID;
        }
    }
}
