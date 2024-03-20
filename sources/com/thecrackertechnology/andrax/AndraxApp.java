package com.thecrackertechnology.andrax;

import android.app.AlertDialog;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;
import com.onesignal.OneSignalDbContract;
import com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference;
import com.thecrackertechnology.dragonterminal.ui.bonus.BonusActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.acra.ACRA;
import org.acra.BuildConfig;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraDialog;
import org.acra.annotation.AcraMailSender;

@AcraMailSender(mailTo = "weidsom@thecrackertechnology.com")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0006\u0010\u0007\u001a\u00020\u0004J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ&\u0010\f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000fJ&\u0010\f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006J\b\u0010\u0013\u001a\u00020\u0004H\u0016J\u0006\u0010\u0014\u001a\u00020\u0004J\u0010\u0010\u0015\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¨\u0006\u001a"}, d2 = {"Lcom/thecrackertechnology/andrax/AndraxApp;", "Landroid/app/Application;", "()V", "attachBaseContext", "", "base", "Landroid/content/Context;", "checkcoreversion", "easterEgg", "context", "message", "", "errorDialog", "", "dismissCallback", "Lkotlin/Function0;", "isRooted", "", "c", "onCreate", "openHelpLink", "setPermissions", "path", "Ljava/io/File;", "CheckVersion", "Companion", "app_release"}, k = 1, mv = {1, 1, 15})
@AcraCore(buildConfigClass = BuildConfig.class)
@AcraDialog(resCommentPrompt = 2131755067, resIcon = 2131230824, resText = 2131755068, resTheme = 2131820551, resTitle = 2131755066)
/* compiled from: AndraxApp.kt */
public final class AndraxApp extends Application {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static AndraxApp app;

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00dd A[SYNTHETIC, Splitter:B:32:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x02dd A[SYNTHETIC, Splitter:B:44:0x02dd] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0319  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0380  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x040b  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0506  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x056d  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x05f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate() {
        /*
            r19 = this;
            r1 = r19
            java.lang.String r2 = "su -c /system/bin/dumpsys deviceidle whitelist +com.thecrackertechnology.andrax"
            java.lang.String r3 = "/bin/busybox mount -o remount,exec,suid,dev,rw /data"
            java.lang.String r4 = "su -c "
            java.lang.String r5 = "arm/static/bin"
            java.lang.String r6 = "all/scripts"
            java.lang.String r7 = "su -c rm -rf "
            java.lang.String r8 = "issafepts2"
            java.lang.String r9 = "filesDir"
            java.lang.String r10 = "/scripts"
            java.lang.String r11 = "/bin"
            java.lang.String r12 = "this.filesDir"
            super.onCreate()
            r13 = r1
            android.content.Context r13 = (android.content.Context) r13
            r1.isRooted(r13)
            r0 = 0
            r14 = r0
            java.io.OutputStream r14 = (java.io.OutputStream) r14
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            r16 = r14
            java.lang.ProcessBuilder r0 = new java.lang.ProcessBuilder     // Catch:{ Exception -> 0x030f, all -> 0x0308 }
            java.lang.String r17 = "su"
            java.lang.String[] r14 = new java.lang.String[]{r17}     // Catch:{ Exception -> 0x030f, all -> 0x0308 }
            r0.<init>(r14)     // Catch:{ Exception -> 0x030f, all -> 0x0308 }
            java.io.File r14 = new java.io.File     // Catch:{ Exception -> 0x030f, all -> 0x0308 }
            r17 = r7
            android.content.pm.ApplicationInfo r7 = r19.getApplicationInfo()     // Catch:{ Exception -> 0x0303, all -> 0x02fe }
            java.lang.String r7 = r7.dataDir     // Catch:{ Exception -> 0x0303, all -> 0x02fe }
            r14.<init>(r7)     // Catch:{ Exception -> 0x0303, all -> 0x02fe }
            r0.directory(r14)     // Catch:{ Exception -> 0x0303, all -> 0x02fe }
            r7 = 1
            r0.redirectErrorStream(r7)     // Catch:{ Exception -> 0x0303, all -> 0x02fe }
            java.lang.Process r7 = r0.start()     // Catch:{ Exception -> 0x0303, all -> 0x02fe }
            java.lang.String r0 = "pb.start()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r0)     // Catch:{ Exception -> 0x0303, all -> 0x02fe }
            java.io.OutputStream r14 = r7.getOutputStream()     // Catch:{ Exception -> 0x0303, all -> 0x02fe }
            java.io.InputStream r0 = r7.getInputStream()     // Catch:{ Exception -> 0x02f9, all -> 0x02f3 }
            r18 = r5
            java.lang.String r5 = "process.inputStream"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r5)     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            r0.<init>()     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            java.lang.String r5 = "PATH="
            r0.append(r5)     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            java.io.File r5 = r19.getFilesDir()     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r12)     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            java.lang.String r5 = r5.getAbsolutePath()     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            r0.append(r5)     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            java.lang.String r5 = "/bin:$PATH"
            r0.append(r5)     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            r5 = 0
            r15.add(r5, r0)     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            java.lang.String r0 = "exit 0"
            r15.add(r0)     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            r0 = 0
            r5 = r0
            java.io.DataOutputStream r5 = (java.io.DataOutputStream) r5     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            r16 = r5
            java.io.DataOutputStream r5 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x00d5, all -> 0x00cc }
            r5.<init>(r14)     // Catch:{ IOException -> 0x00d5, all -> 0x00cc }
            java.util.Iterator r0 = r15.iterator()     // Catch:{ IOException -> 0x00ca }
        L_0x009c:
            boolean r15 = r0.hasNext()     // Catch:{ IOException -> 0x00ca }
            if (r15 == 0) goto L_0x00c1
            java.lang.Object r15 = r0.next()     // Catch:{ IOException -> 0x00ca }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ IOException -> 0x00ca }
            r16 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ca }
            r0.<init>()     // Catch:{ IOException -> 0x00ca }
            r0.append(r15)     // Catch:{ IOException -> 0x00ca }
            java.lang.String r15 = "\n"
            r0.append(r15)     // Catch:{ IOException -> 0x00ca }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x00ca }
            r5.writeBytes(r0)     // Catch:{ IOException -> 0x00ca }
            r0 = r16
            goto L_0x009c
        L_0x00c1:
            r5.flush()     // Catch:{ IOException -> 0x00ca }
            r5.close()     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
        L_0x00c7:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            goto L_0x00e1
        L_0x00ca:
            r0 = move-exception
            goto L_0x00d8
        L_0x00cc:
            r0 = move-exception
            r5 = r16
        L_0x00cf:
            r15 = r17
            r7 = r18
            goto L_0x02db
        L_0x00d5:
            r0 = move-exception
            r5 = r16
        L_0x00d8:
            r0.printStackTrace()     // Catch:{ all -> 0x02d8 }
            if (r5 == 0) goto L_0x00e1
            r5.close()     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            goto L_0x00c7
        L_0x00e1:
            int r0 = r7.waitFor()     // Catch:{ Exception -> 0x02ed, all -> 0x02e6 }
            if (r14 == 0) goto L_0x00ec
            r14.close()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x00ec:
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r4)
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r12)
            java.lang.String r4 = r4.getAbsolutePath()
            r5.append(r4)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            java.lang.Process r0 = r0.exec(r3)
            r0.waitFor()
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
            java.lang.Process r0 = r0.exec(r2)
            r0.waitFor()
            r0 = r1
            com.thecrackertechnology.andrax.AndraxApp r0 = (com.thecrackertechnology.andrax.AndraxApp) r0
            app = r0
            com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference r0 = com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference.INSTANCE
            r0.init(r13)
            com.thecrackertechnology.dragonterminal.component.NeoInitializer r0 = com.thecrackertechnology.dragonterminal.component.NeoInitializer.INSTANCE
            r0.init(r13)
            com.onesignal.OneSignal$Builder r0 = com.onesignal.OneSignal.startInit(r13)
            com.onesignal.OneSignal$OSInFocusDisplayOption r2 = com.onesignal.OneSignal.OSInFocusDisplayOption.Notification
            com.onesignal.OneSignal$Builder r0 = r0.inFocusDisplaying(r2)
            r2 = 0
            com.onesignal.OneSignal$Builder r0 = r0.unsubscribeWhenNotificationsAreDisabled(r2)
            r0.init()
            java.lang.String r0 = r19.getPackageName()
            android.content.SharedPreferences r0 = r1.getSharedPreferences(r0, r2)
            boolean r3 = r0.getBoolean(r8, r2)
            if (r3 == 0) goto L_0x01db
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.io.File r3 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r9)
            java.lang.String r3 = r3.getAbsolutePath()
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            r0.mkdirs()
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r9)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            r2.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r3 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r12)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r10)
            java.lang.String r4 = r4.toString()
            r3.extractAssetsDir(r13, r6, r4)
            r1.setPermissions(r0)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r0 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r12)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            r7 = r18
            r0.extractAssetsDir(r13, r7, r3)
            r1.setPermissions(r2)
            r2 = 1
            goto L_0x02c2
        L_0x01db:
            r7 = r18
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r15 = r17
            r3.append(r15)
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r12)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            java.lang.Process r2 = r2.exec(r3)
            r2.waitFor()
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r15)
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r12)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            java.lang.Process r2 = r2.exec(r3)
            r2.waitFor()
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r9)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            r2.mkdirs()
            java.io.File r3 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r9)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            r3.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r4 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.io.File r9 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r12)
            java.lang.String r9 = r9.getAbsolutePath()
            r5.append(r9)
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            r4.extractAssetsDir(r13, r6, r5)
            r1.setPermissions(r2)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r2 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r12)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r2.extractAssetsDir(r13, r7, r4)
            r1.setPermissions(r3)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r2 = 1
            r0.putBoolean(r8, r2)
            r0.commit()
        L_0x02c2:
            com.thecrackertechnology.andrax.AndraxApp$CheckVersion r0 = new com.thecrackertechnology.andrax.AndraxApp$CheckVersion
            r0.<init>()
            java.lang.String[] r2 = new java.lang.String[r2]
            r3 = 2131755071(0x7f10003f, float:1.914101E38)
            java.lang.String r3 = r1.getString(r3)
            r4 = 0
            r2[r4] = r3
            r0.execute(r2)
            goto L_0x0502
        L_0x02d8:
            r0 = move-exception
            goto L_0x00cf
        L_0x02db:
            if (r5 == 0) goto L_0x02e5
            r5.close()     // Catch:{ Exception -> 0x02e3 }
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x02e3 }
            goto L_0x02e5
        L_0x02e3:
            r0 = move-exception
            goto L_0x0314
        L_0x02e5:
            throw r0     // Catch:{ Exception -> 0x02e3 }
        L_0x02e6:
            r0 = move-exception
            r15 = r17
            r7 = r18
            goto L_0x0504
        L_0x02ed:
            r0 = move-exception
            r15 = r17
            r7 = r18
            goto L_0x0314
        L_0x02f3:
            r0 = move-exception
            r7 = r5
            r15 = r17
            goto L_0x0504
        L_0x02f9:
            r0 = move-exception
            r7 = r5
            r15 = r17
            goto L_0x0314
        L_0x02fe:
            r0 = move-exception
            r7 = r5
            r15 = r17
            goto L_0x030b
        L_0x0303:
            r0 = move-exception
            r7 = r5
            r15 = r17
            goto L_0x0312
        L_0x0308:
            r0 = move-exception
            r15 = r7
            r7 = r5
        L_0x030b:
            r14 = r16
            goto L_0x0504
        L_0x030f:
            r0 = move-exception
            r15 = r7
            r7 = r5
        L_0x0312:
            r14 = r16
        L_0x0314:
            r0.printStackTrace()     // Catch:{ all -> 0x0503 }
            if (r14 == 0) goto L_0x031e
            r14.close()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x031e:
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r4)
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r12)
            java.lang.String r4 = r4.getAbsolutePath()
            r5.append(r4)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            java.lang.Process r0 = r0.exec(r3)
            r0.waitFor()
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
            java.lang.Process r0 = r0.exec(r2)
            r0.waitFor()
            r0 = r1
            com.thecrackertechnology.andrax.AndraxApp r0 = (com.thecrackertechnology.andrax.AndraxApp) r0
            app = r0
            com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference r0 = com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference.INSTANCE
            r0.init(r13)
            com.thecrackertechnology.dragonterminal.component.NeoInitializer r0 = com.thecrackertechnology.dragonterminal.component.NeoInitializer.INSTANCE
            r0.init(r13)
            com.onesignal.OneSignal$Builder r0 = com.onesignal.OneSignal.startInit(r13)
            com.onesignal.OneSignal$OSInFocusDisplayOption r2 = com.onesignal.OneSignal.OSInFocusDisplayOption.Notification
            com.onesignal.OneSignal$Builder r0 = r0.inFocusDisplaying(r2)
            r2 = 0
            com.onesignal.OneSignal$Builder r0 = r0.unsubscribeWhenNotificationsAreDisabled(r2)
            r0.init()
            java.lang.String r0 = r19.getPackageName()
            android.content.SharedPreferences r0 = r1.getSharedPreferences(r0, r2)
            boolean r3 = r0.getBoolean(r8, r2)
            if (r3 == 0) goto L_0x040b
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.io.File r3 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r9)
            java.lang.String r3 = r3.getAbsolutePath()
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            r0.mkdirs()
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r9)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            r2.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r3 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r12)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r10)
            java.lang.String r4 = r4.toString()
            r3.extractAssetsDir(r13, r6, r4)
            r1.setPermissions(r0)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r0 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r12)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            r0.extractAssetsDir(r13, r7, r3)
            r1.setPermissions(r2)
            r2 = 1
            goto L_0x04ee
        L_0x040b:
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r15)
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r12)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            java.lang.Process r2 = r2.exec(r3)
            r2.waitFor()
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r15)
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r12)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            java.lang.Process r2 = r2.exec(r3)
            r2.waitFor()
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r9)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            r2.mkdirs()
            java.io.File r3 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r9)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            r3.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r4 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.io.File r9 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r12)
            java.lang.String r9 = r9.getAbsolutePath()
            r5.append(r9)
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            r4.extractAssetsDir(r13, r6, r5)
            r1.setPermissions(r2)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r2 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r12)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r2.extractAssetsDir(r13, r7, r4)
            r1.setPermissions(r3)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r2 = 1
            r0.putBoolean(r8, r2)
            r0.commit()
        L_0x04ee:
            com.thecrackertechnology.andrax.AndraxApp$CheckVersion r0 = new com.thecrackertechnology.andrax.AndraxApp$CheckVersion
            r0.<init>()
            java.lang.String[] r2 = new java.lang.String[r2]
            r3 = 2131755071(0x7f10003f, float:1.914101E38)
            java.lang.String r3 = r1.getString(r3)
            r4 = 0
            r2[r4] = r3
            r0.execute(r2)
        L_0x0502:
            return
        L_0x0503:
            r0 = move-exception
        L_0x0504:
            if (r14 == 0) goto L_0x050b
            r14.close()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
        L_0x050b:
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r4)
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r12)
            java.lang.String r4 = r4.getAbsolutePath()
            r14.append(r4)
            r14.append(r3)
            java.lang.String r3 = r14.toString()
            java.lang.Process r3 = r5.exec(r3)
            r3.waitFor()
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()
            java.lang.Process r2 = r3.exec(r2)
            r2.waitFor()
            r2 = r1
            com.thecrackertechnology.andrax.AndraxApp r2 = (com.thecrackertechnology.andrax.AndraxApp) r2
            app = r2
            com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference r2 = com.thecrackertechnology.dragonterminal.frontend.config.NeoPreference.INSTANCE
            r2.init(r13)
            com.thecrackertechnology.dragonterminal.component.NeoInitializer r2 = com.thecrackertechnology.dragonterminal.component.NeoInitializer.INSTANCE
            r2.init(r13)
            com.onesignal.OneSignal$Builder r2 = com.onesignal.OneSignal.startInit(r13)
            com.onesignal.OneSignal$OSInFocusDisplayOption r3 = com.onesignal.OneSignal.OSInFocusDisplayOption.Notification
            com.onesignal.OneSignal$Builder r2 = r2.inFocusDisplaying(r3)
            r3 = 0
            com.onesignal.OneSignal$Builder r2 = r2.unsubscribeWhenNotificationsAreDisabled(r3)
            r2.init()
            java.lang.String r2 = r19.getPackageName()
            android.content.SharedPreferences r2 = r1.getSharedPreferences(r2, r3)
            boolean r4 = r2.getBoolean(r8, r3)
            if (r4 == 0) goto L_0x05f8
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.io.File r4 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r9)
            java.lang.String r4 = r4.getAbsolutePath()
            r3.append(r4)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            r2.mkdirs()
            java.io.File r3 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r9)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            r3.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r4 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.io.File r8 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r12)
            java.lang.String r8 = r8.getAbsolutePath()
            r5.append(r8)
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            r4.extractAssetsDir(r13, r6, r5)
            r1.setPermissions(r2)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r2 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r12)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r2.extractAssetsDir(r13, r7, r4)
            r1.setPermissions(r3)
            r3 = 1
            goto L_0x06db
        L_0x05f8:
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r15)
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r12)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            java.lang.Process r3 = r3.exec(r4)
            r3.waitFor()
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r15)
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r12)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r10)
            java.lang.String r4 = r4.toString()
            java.lang.Process r3 = r3.exec(r4)
            r3.waitFor()
            java.io.File r3 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.io.File r5 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r9)
            java.lang.String r5 = r5.getAbsolutePath()
            r4.append(r5)
            r4.append(r10)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            r3.mkdirs()
            java.io.File r4 = new java.io.File
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.io.File r14 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r14, r9)
            java.lang.String r9 = r14.getAbsolutePath()
            r5.append(r9)
            r5.append(r11)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            r4.mkdirs()
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r5 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.io.File r14 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r14, r12)
            java.lang.String r14 = r14.getAbsolutePath()
            r9.append(r14)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r5.extractAssetsDir(r13, r6, r9)
            r1.setPermissions(r3)
            com.thecrackertechnology.dragonterminal.utils.AssetsUtils r3 = com.thecrackertechnology.dragonterminal.utils.AssetsUtils.INSTANCE
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.io.File r6 = r19.getFilesDir()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r12)
            java.lang.String r6 = r6.getAbsolutePath()
            r5.append(r6)
            r5.append(r11)
            java.lang.String r5 = r5.toString()
            r3.extractAssetsDir(r13, r7, r5)
            r1.setPermissions(r4)
            android.content.SharedPreferences$Editor r2 = r2.edit()
            r3 = 1
            r2.putBoolean(r8, r3)
            r2.commit()
        L_0x06db:
            com.thecrackertechnology.andrax.AndraxApp$CheckVersion r2 = new com.thecrackertechnology.andrax.AndraxApp$CheckVersion
            r2.<init>()
            java.lang.String[] r3 = new java.lang.String[r3]
            r4 = 2131755071(0x7f10003f, float:1.914101E38)
            java.lang.String r4 = r1.getString(r4)
            r5 = 0
            r3[r5] = r4
            r2.execute(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.andrax.AndraxApp.onCreate():void");
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "base");
        super.attachBaseContext(context);
        ACRA.init(this);
    }

    public final void errorDialog(Context context, int i, Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String string = getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(message)");
        errorDialog(context, string, function0);
    }

    public final void errorDialog(Context context, String str, Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(str, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        new AlertDialog.Builder(context).setTitle(R.string.error).setMessage(str).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.show_help, new AndraxApp$errorDialog$1(this)).setOnDismissListener(new AndraxApp$errorDialog$2(function0)).show();
    }

    public final void openHelpLink() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://neoterm.gitbooks.io/neoterm-wiki/content/"));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public final void easterEgg(Context context, String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(str, OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
        int loadInt = NeoPreference.INSTANCE.loadInt(NeoPreference.KEY_HAPPY_EGG, 0) + 1;
        NeoPreference.INSTANCE.store(NeoPreference.KEY_HAPPY_EGG, (Object) Integer.valueOf(loadInt));
        if (loadInt == 4) {
            Toast makeText = Toast.makeText(this, str, 1);
            makeText.setGravity(17, 0, 0);
            makeText.show();
        } else if (loadInt > 8) {
            NeoPreference.INSTANCE.store(NeoPreference.KEY_HAPPY_EGG, (Object) 0);
            context.startActivity(new Intent(context, BonusActivity.class));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/thecrackertechnology/andrax/AndraxApp$Companion;", "", "()V", "app", "Lcom/thecrackertechnology/andrax/AndraxApp;", "get", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: AndraxApp.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AndraxApp get() {
            AndraxApp access$getApp$cp = AndraxApp.app;
            if (access$getApp$cp == null) {
                Intrinsics.throwNpe();
            }
            return access$getApp$cp;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J#\u0010\n\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\f\"\u00020\u0002H\u0014¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u0014J!\u0010\u0011\u001a\u00020\u000f2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\f\"\u00020\u0002H\u0014¢\u0006\u0002\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/thecrackertechnology/andrax/AndraxApp$CheckVersion;", "Landroid/os/AsyncTask;", "", "()V", "VersionFromServer", "", "getVersionFromServer", "()I", "setVersionFromServer", "(I)V", "doInBackground", "fileUrl", "", "([Ljava/lang/String;)Ljava/lang/String;", "onPostExecute", "", "result", "onProgressUpdate", "progress", "([Ljava/lang/String;)V", "app_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: AndraxApp.kt */
    public static final class CheckVersion extends AsyncTask<String, String, String> {
        private int VersionFromServer;

        /* access modifiers changed from: protected */
        public void onProgressUpdate(String... strArr) {
            Intrinsics.checkParameterIsNotNull(strArr, NotificationCompat.CATEGORY_PROGRESS);
        }

        public final int getVersionFromServer() {
            return this.VersionFromServer;
        }

        public final void setVersionFromServer(int i) {
            this.VersionFromServer = i;
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
            Intrinsics.checkParameterIsNotNull(strArr, "fileUrl");
            try {
                URLConnection openConnection = new URL(strArr[0]).openConnection();
                Intrinsics.checkExpressionValueIsNotNull(openConnection, "urlConnection");
                openConnection.setConnectTimeout(1000);
                openConnection.setReadTimeout(1000);
                openConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Android ANDRAX; Mobile; rv:03) Gecko/67.0 Firefox/67.0");
                openConnection.connect();
                String readLine = new BufferedReader(new InputStreamReader(openConnection.getInputStream())).readLine();
                Intrinsics.checkExpressionValueIsNotNull(readLine, "bufferedReader.readLine()");
                this.VersionFromServer = 0;
                try {
                    this.VersionFromServer = Integer.parseInt(new Regex("\\s+").replace((CharSequence) readLine, ""));
                    return null;
                } catch (NumberFormatException unused) {
                    return null;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            if (this.VersionFromServer > Integer.parseInt(AndraxAppKt.getVersiondefault())) {
                int i = Build.VERSION.SDK_INT;
                if (Build.VERSION.SDK_INT >= 26) {
                    new NotificationChannel("NEWVERSION", "New Version of ANDRAX", 4);
                }
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://andrax.thecrackertechnology.com"));
                intent.addFlags(268435456);
                PendingIntent activity = PendingIntent.getActivity(AndraxApp.Companion.get(), 0, intent, 0);
                Intrinsics.checkExpressionValueIsNotNull(activity, "PendingIntent.getActivit…0, notificationIntent, 0)");
                NotificationCompat.Builder color = new NotificationCompat.Builder(AndraxApp.Companion.get(), "NEWVERSION").setSmallIcon(R.drawable.andraxicon_svg).setChannelId("NEWVERSION").setContentTitle("NEW VERSION").setContentText("ANDRAX has a new version, DOWNLOAD NOW!").setStyle(new NotificationCompat.BigTextStyle().bigText("ANDRAX has a new version, DOWNLOAD NOW!\nFor new tools, bug fixes and a lot of improvements")).setPriority(1).setAutoCancel(false).setVibrate(new long[]{1000, 1000, 1000}).setOngoing(true).setContentIntent(activity).setColor((int) 4278190080L);
                NotificationManagerCompat from = NotificationManagerCompat.from(AndraxApp.Companion.get());
                Intrinsics.checkExpressionValueIsNotNull(from, "NotificationManagerCompat.from(AndraxApp.get())");
                from.notify(9988, color.build());
                return;
            }
            NotificationManagerCompat from2 = NotificationManagerCompat.from(AndraxApp.Companion.get());
            Intrinsics.checkExpressionValueIsNotNull(from2, "NotificationManagerCompat.from(AndraxApp.get())");
            from2.cancel(9988);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00fa A[Catch:{ Exception -> 0x0158, all -> 0x014c }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0101 A[Catch:{ Exception -> 0x0158, all -> 0x014c }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0122 A[Catch:{ Exception -> 0x014a, all -> 0x0146 }, LOOP:2: B:44:0x0119->B:47:0x0122, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0139 A[EDGE_INSN: B:70:0x0139->B:48:0x0139 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:71:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setPermissions(java.io.File r12) {
        /*
            r11 = this;
            java.lang.String r0 = "this.filesDir"
            if (r12 != 0) goto L_0x0005
            return
        L_0x0005:
            boolean r1 = r12.exists()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0037
            r12.setReadable(r2, r3)
            r12.setExecutable(r2, r3)
            java.io.File[] r12 = r12.listFiles()
            if (r12 == 0) goto L_0x0036
            int r1 = r12.length
            r4 = 0
        L_0x001b:
            if (r4 >= r1) goto L_0x0037
            r5 = r12[r4]
            java.lang.String r6 = "f"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            boolean r6 = r5.isDirectory()
            if (r6 == 0) goto L_0x002d
            r11.setPermissions(r5)
        L_0x002d:
            r5.setReadable(r2, r3)
            r5.setExecutable(r2, r3)
            int r4 = r4 + 1
            goto L_0x001b
        L_0x0036:
            return
        L_0x0037:
            r12 = 0
            r1 = r12
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1
            r4 = r12
            java.io.OutputStream r4 = (java.io.OutputStream) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.lang.ProcessBuilder r6 = new java.lang.ProcessBuilder     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r7 = "su"
            java.lang.String[] r7 = new java.lang.String[]{r7}     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.io.File r7 = new java.io.File     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            android.content.pm.ApplicationInfo r8 = r11.getApplicationInfo()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r8 = r8.dataDir     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r7.<init>(r8)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r6.directory(r7)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r6.redirectErrorStream(r3)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.Process r6 = r6.start()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r7 = "pb.start()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.io.OutputStream r4 = r6.getOutputStream()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.io.InputStream r7 = r6.getInputStream()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r8 = "process.inputStream"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r8.<init>()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r9 = "chmod -R 777 "
            r8.append(r9)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.io.File r9 = r11.getFilesDir()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r0)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r9 = r9.getAbsolutePath()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r8.append(r9)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r5.add(r3, r8)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r8.<init>()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r9 = "rm -rf"
            r8.append(r9)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.io.File r9 = r11.getFilesDir()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r0)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r0 = r9.getAbsolutePath()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r8.append(r0)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r0 = "/bin/su"
            r8.append(r0)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r0 = r8.toString()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r5.add(r2, r0)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.lang.String r0 = "exit 0"
            r5.add(r0)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.io.DataOutputStream r12 = (java.io.DataOutputStream) r12     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.io.DataOutputStream r0 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x00fe, all -> 0x00f4 }
            r0.<init>(r4)     // Catch:{ IOException -> 0x00fe, all -> 0x00f4 }
            java.util.Iterator r12 = r5.iterator()     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
        L_0x00c8:
            boolean r2 = r12.hasNext()     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
            if (r2 == 0) goto L_0x00e9
            java.lang.Object r2 = r12.next()     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
            r5.<init>()     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
            r5.append(r2)     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
            java.lang.String r2 = "\n"
            r5.append(r2)     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
            java.lang.String r2 = r5.toString()     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
            r0.writeBytes(r2)     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
            goto L_0x00c8
        L_0x00e9:
            r0.flush()     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
        L_0x00ec:
            r0.close()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            goto L_0x0102
        L_0x00f0:
            r12 = move-exception
            goto L_0x00f8
        L_0x00f2:
            goto L_0x00ff
        L_0x00f4:
            r0 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
        L_0x00f8:
            if (r0 == 0) goto L_0x00fd
            r0.close()     // Catch:{ Exception -> 0x0158, all -> 0x014c }
        L_0x00fd:
            throw r12     // Catch:{ Exception -> 0x0158, all -> 0x014c }
        L_0x00fe:
            r0 = r12
        L_0x00ff:
            if (r0 == 0) goto L_0x0102
            goto L_0x00ec
        L_0x0102:
            java.io.BufferedReader r12 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r0.<init>(r7)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            java.io.Reader r0 = (java.io.Reader) r0     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            r12.<init>(r0)     // Catch:{ Exception -> 0x0158, all -> 0x014c }
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r0.<init>()     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r1 = 1024(0x400, float:1.435E-42)
            char[] r1 = new char[r1]     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            java.lang.String r2 = ""
        L_0x0119:
            int r5 = r12.read(r1)     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r0.element = r5     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r7 = -1
            if (r5 == r7) goto L_0x0139
            int r5 = r0.element     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            java.lang.String r7 = new java.lang.String     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r7.<init>(r1, r3, r5)     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r5.<init>()     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r5.append(r2)     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r5.append(r7)     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            java.lang.String r2 = r5.toString()     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            goto L_0x0119
        L_0x0139:
            int r0 = r6.waitFor()     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r12.close()
            if (r4 == 0) goto L_0x0161
        L_0x0142:
            r4.close()
            goto L_0x0161
        L_0x0146:
            r0 = move-exception
            r1 = r12
            r12 = r0
            goto L_0x014d
        L_0x014a:
            r1 = r12
            goto L_0x0159
        L_0x014c:
            r12 = move-exception
        L_0x014d:
            if (r1 == 0) goto L_0x0152
            r1.close()
        L_0x0152:
            if (r4 == 0) goto L_0x0157
            r4.close()
        L_0x0157:
            throw r12
        L_0x0158:
        L_0x0159:
            if (r1 == 0) goto L_0x015e
            r1.close()
        L_0x015e:
            if (r4 == 0) goto L_0x0161
            goto L_0x0142
        L_0x0161:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.andrax.AndraxApp.setPermissions(java.io.File):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        if (r4 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0082, code lost:
        if (r0 != null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0084, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0095, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0097, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x009b, code lost:
        if (r1 != null) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x009d, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00a0, code lost:
        if (r0 != null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00a3, code lost:
        if (r2 != false) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00a5, code lost:
        r7 = new android.content.Intent(r6, com.thecrackertechnology.andrax.RootIt.class);
        r7.addFlags(268435456);
        startActivity(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00b7, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00b8, code lost:
        if (r1 != null) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00ba, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00bd, code lost:
        if (r0 != null) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00bf, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00c2, code lost:
        throw r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0076 A[SYNTHETIC, Splitter:B:39:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x008a A[SYNTHETIC, Splitter:B:49:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0091 A[Catch:{ IOException -> 0x0097, all -> 0x0095 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isRooted(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = "c"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            r7 = 0
            r0 = r7
            java.io.OutputStream r0 = (java.io.OutputStream) r0
            r1 = r7
            java.io.InputStream r1 = (java.io.InputStream) r1
            r2 = 0
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0097 }
            java.lang.String r4 = "su"
            java.lang.Process r3 = r3.exec(r4)     // Catch:{ IOException -> 0x0097 }
            java.io.OutputStream r0 = r3.getOutputStream()     // Catch:{ IOException -> 0x0097 }
            java.io.InputStream r1 = r3.getInputStream()     // Catch:{ IOException -> 0x0097 }
            r3 = r7
            java.io.DataOutputStream r3 = (java.io.DataOutputStream) r3     // Catch:{ IOException -> 0x0097 }
            java.io.DataOutputStream r4 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            r4.<init>(r0)     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            java.lang.String r3 = "ls /data\n"
            r4.writeBytes(r3)     // Catch:{ IOException -> 0x0038 }
            java.lang.String r3 = "exit\n"
            r4.writeBytes(r3)     // Catch:{ IOException -> 0x0038 }
            r4.flush()     // Catch:{ IOException -> 0x0038 }
        L_0x0034:
            r4.close()     // Catch:{ IOException -> 0x0097 }
            goto L_0x0047
        L_0x0038:
            r3 = move-exception
            goto L_0x0041
        L_0x003a:
            r7 = move-exception
            r4 = r3
            goto L_0x008f
        L_0x003d:
            r4 = move-exception
            r5 = r4
            r4 = r3
            r3 = r5
        L_0x0041:
            r3.printStackTrace()     // Catch:{ all -> 0x008e }
            if (r4 == 0) goto L_0x0047
            goto L_0x0034
        L_0x0047:
            java.io.BufferedReader r7 = (java.io.BufferedReader) r7     // Catch:{ IOException -> 0x0097 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x006f }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x006f }
            r4.<init>(r1)     // Catch:{ IOException -> 0x006f }
            java.io.Reader r4 = (java.io.Reader) r4     // Catch:{ IOException -> 0x006f }
            r3.<init>(r4)     // Catch:{ IOException -> 0x006f }
            r7 = 0
        L_0x0056:
            java.lang.String r4 = r3.readLine()     // Catch:{ IOException -> 0x0068, all -> 0x0063 }
            if (r4 == 0) goto L_0x005f
            int r7 = r7 + 1
            goto L_0x0056
        L_0x005f:
            r3.close()     // Catch:{ IOException -> 0x0097 }
            goto L_0x007a
        L_0x0063:
            r7 = move-exception
            r5 = r3
            r3 = r7
            r7 = r5
            goto L_0x0088
        L_0x0068:
            r4 = move-exception
            r5 = r3
            r3 = r7
            r7 = r5
            goto L_0x0071
        L_0x006d:
            r3 = move-exception
            goto L_0x0088
        L_0x006f:
            r4 = move-exception
            r3 = 0
        L_0x0071:
            r4.printStackTrace()     // Catch:{ all -> 0x006d }
            if (r7 == 0) goto L_0x0079
            r7.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0079:
            r7 = r3
        L_0x007a:
            if (r7 <= 0) goto L_0x007d
            r2 = 1
        L_0x007d:
            if (r1 == 0) goto L_0x0082
            r1.close()
        L_0x0082:
            if (r0 == 0) goto L_0x00a3
        L_0x0084:
            r0.close()
            goto L_0x00a3
        L_0x0088:
            if (r7 == 0) goto L_0x008d
            r7.close()     // Catch:{ IOException -> 0x0097 }
        L_0x008d:
            throw r3     // Catch:{ IOException -> 0x0097 }
        L_0x008e:
            r7 = move-exception
        L_0x008f:
            if (r4 == 0) goto L_0x0094
            r4.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0094:
            throw r7     // Catch:{ IOException -> 0x0097 }
        L_0x0095:
            r7 = move-exception
            goto L_0x00b8
        L_0x0097:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x0095 }
            if (r1 == 0) goto L_0x00a0
            r1.close()
        L_0x00a0:
            if (r0 == 0) goto L_0x00a3
            goto L_0x0084
        L_0x00a3:
            if (r2 != 0) goto L_0x00b7
            android.content.Intent r7 = new android.content.Intent
            r0 = r6
            android.content.Context r0 = (android.content.Context) r0
            java.lang.Class<com.thecrackertechnology.andrax.RootIt> r1 = com.thecrackertechnology.andrax.RootIt.class
            r7.<init>(r0, r1)
            r0 = 268435456(0x10000000, float:2.5243549E-29)
            r7.addFlags(r0)
            r6.startActivity(r7)
        L_0x00b7:
            return r2
        L_0x00b8:
            if (r1 == 0) goto L_0x00bd
            r1.close()
        L_0x00bd:
            if (r0 == 0) goto L_0x00c2
            r0.close()
        L_0x00c2:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.andrax.AndraxApp.isRooted(android.content.Context):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a5 A[Catch:{ Exception -> 0x012c, all -> 0x0129 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ac A[Catch:{ Exception -> 0x012c, all -> 0x0129 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00cc A[Catch:{ Exception -> 0x011b, all -> 0x0119 }, LOOP:1: B:30:0x00c3->B:33:0x00cc, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00e3 A[EDGE_INSN: B:73:0x00e3->B:34:0x00e3 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:74:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void checkcoreversion() {
        /*
            r13 = this;
            java.lang.String r0 = "\\s+"
            java.lang.String r1 = ""
            r2 = 0
            r3 = r2
            java.io.BufferedReader r3 = (java.io.BufferedReader) r3
            r4 = r2
            java.io.OutputStream r4 = (java.io.OutputStream) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r6 = 268435456(0x10000000, float:2.5243549E-29)
            java.lang.ProcessBuilder r7 = new java.lang.ProcessBuilder     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.String r8 = "su"
            java.lang.String[] r8 = new java.lang.String[]{r8}     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            r7.<init>(r8)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.io.File r8 = new java.io.File     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            android.content.pm.ApplicationInfo r9 = r13.getApplicationInfo()     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.String r9 = r9.dataDir     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            r7.directory(r8)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            r8 = 0
            r7.redirectErrorStream(r8)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.Process r7 = r7.start()     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.String r9 = "pb.start()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r9)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.io.OutputStream r4 = r7.getOutputStream()     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.io.InputStream r9 = r7.getInputStream()     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.String r10 = "process.inputStream"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r10)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            r10.<init>()     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.String r11 = "cat "
            r10.append(r11)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            android.content.pm.ApplicationInfo r11 = r13.getApplicationInfo()     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.String r11 = r11.dataDir     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            r10.append(r11)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.String r11 = "/tmpsystem/version"
            r10.append(r11)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            r5.add(r8, r10)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.lang.String r10 = "exit 0"
            r5.add(r10)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.io.DataOutputStream r2 = (java.io.DataOutputStream) r2     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.io.DataOutputStream r10 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x00a9, all -> 0x00a0 }
            r10.<init>(r4)     // Catch:{ IOException -> 0x00a9, all -> 0x00a0 }
            java.util.Iterator r2 = r5.iterator()     // Catch:{ IOException -> 0x009e, all -> 0x009c }
        L_0x0074:
            boolean r5 = r2.hasNext()     // Catch:{ IOException -> 0x009e, all -> 0x009c }
            if (r5 == 0) goto L_0x0095
            java.lang.Object r5 = r2.next()     // Catch:{ IOException -> 0x009e, all -> 0x009c }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x009e, all -> 0x009c }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x009e, all -> 0x009c }
            r11.<init>()     // Catch:{ IOException -> 0x009e, all -> 0x009c }
            r11.append(r5)     // Catch:{ IOException -> 0x009e, all -> 0x009c }
            java.lang.String r5 = "\n"
            r11.append(r5)     // Catch:{ IOException -> 0x009e, all -> 0x009c }
            java.lang.String r5 = r11.toString()     // Catch:{ IOException -> 0x009e, all -> 0x009c }
            r10.writeBytes(r5)     // Catch:{ IOException -> 0x009e, all -> 0x009c }
            goto L_0x0074
        L_0x0095:
            r10.flush()     // Catch:{ IOException -> 0x009e, all -> 0x009c }
        L_0x0098:
            r10.close()     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            goto L_0x00ad
        L_0x009c:
            r2 = move-exception
            goto L_0x00a3
        L_0x009e:
            goto L_0x00aa
        L_0x00a0:
            r5 = move-exception
            r10 = r2
            r2 = r5
        L_0x00a3:
            if (r10 == 0) goto L_0x00a8
            r10.close()     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
        L_0x00a8:
            throw r2     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
        L_0x00a9:
            r10 = r2
        L_0x00aa:
            if (r10 == 0) goto L_0x00ad
            goto L_0x0098
        L_0x00ad:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            r5.<init>(r9)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            java.io.Reader r5 = (java.io.Reader) r5     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x012c, all -> 0x0129 }
            kotlin.jvm.internal.Ref$IntRef r3 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ Exception -> 0x0123, all -> 0x011d }
            r3.<init>()     // Catch:{ Exception -> 0x0123, all -> 0x011d }
            r5 = 1024(0x400, float:1.435E-42)
            char[] r5 = new char[r5]     // Catch:{ Exception -> 0x0123, all -> 0x011d }
            r9 = r1
        L_0x00c3:
            int r10 = r2.read(r5)     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            r3.element = r10     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            r11 = -1
            if (r10 == r11) goto L_0x00e3
            int r10 = r3.element     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            java.lang.String r11 = new java.lang.String     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            r11.<init>(r5, r8, r10)     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            r10.<init>()     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            r10.append(r9)     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            r10.append(r11)     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            java.lang.String r9 = r10.toString()     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            goto L_0x00c3
        L_0x00e3:
            int r3 = r7.waitFor()     // Catch:{ Exception -> 0x011b, all -> 0x0119 }
            r2.close()
            if (r4 == 0) goto L_0x00ef
            r4.close()
        L_0x00ef:
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            kotlin.text.Regex r2 = new kotlin.text.Regex
            r2.<init>((java.lang.String) r0)
            java.lang.String r0 = r2.replace((java.lang.CharSequence) r9, (java.lang.String) r1)
            int r0 = java.lang.Integer.parseInt(r0)
            java.lang.String r1 = com.thecrackertechnology.andrax.AndraxAppKt.getVersiondefault()
            int r1 = java.lang.Integer.parseInt(r1)
            if (r0 == r1) goto L_0x015f
            android.content.Intent r0 = new android.content.Intent
            r1 = r13
            android.content.Context r1 = (android.content.Context) r1
            java.lang.Class<com.thecrackertechnology.andrax.CheckCoreVersion> r2 = com.thecrackertechnology.andrax.CheckCoreVersion.class
            r0.<init>(r1, r2)
        L_0x0112:
            r0.addFlags(r6)
            r13.startActivity(r0)
            goto L_0x015f
        L_0x0119:
            r3 = move-exception
            goto L_0x011f
        L_0x011b:
            r3 = move-exception
            goto L_0x0125
        L_0x011d:
            r3 = move-exception
            r9 = r1
        L_0x011f:
            r12 = r3
            r3 = r2
            r2 = r12
            goto L_0x0161
        L_0x0123:
            r3 = move-exception
            r9 = r1
        L_0x0125:
            r12 = r3
            r3 = r2
            r2 = r12
            goto L_0x012e
        L_0x0129:
            r2 = move-exception
            r9 = r1
            goto L_0x0161
        L_0x012c:
            r2 = move-exception
            r9 = r1
        L_0x012e:
            r2.printStackTrace()     // Catch:{ all -> 0x0160 }
            if (r3 == 0) goto L_0x0136
            r3.close()
        L_0x0136:
            if (r4 == 0) goto L_0x013b
            r4.close()
        L_0x013b:
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            kotlin.text.Regex r2 = new kotlin.text.Regex
            r2.<init>((java.lang.String) r0)
            java.lang.String r0 = r2.replace((java.lang.CharSequence) r9, (java.lang.String) r1)
            int r0 = java.lang.Integer.parseInt(r0)
            java.lang.String r1 = com.thecrackertechnology.andrax.AndraxAppKt.getVersiondefault()
            int r1 = java.lang.Integer.parseInt(r1)
            if (r0 == r1) goto L_0x015f
            android.content.Intent r0 = new android.content.Intent
            r1 = r13
            android.content.Context r1 = (android.content.Context) r1
            java.lang.Class<com.thecrackertechnology.andrax.CheckCoreVersion> r2 = com.thecrackertechnology.andrax.CheckCoreVersion.class
            r0.<init>(r1, r2)
            goto L_0x0112
        L_0x015f:
            return
        L_0x0160:
            r2 = move-exception
        L_0x0161:
            if (r3 == 0) goto L_0x0166
            r3.close()
        L_0x0166:
            if (r4 == 0) goto L_0x016b
            r4.close()
        L_0x016b:
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            kotlin.text.Regex r3 = new kotlin.text.Regex
            r3.<init>((java.lang.String) r0)
            java.lang.String r0 = r3.replace((java.lang.CharSequence) r9, (java.lang.String) r1)
            int r0 = java.lang.Integer.parseInt(r0)
            java.lang.String r1 = com.thecrackertechnology.andrax.AndraxAppKt.getVersiondefault()
            int r1 = java.lang.Integer.parseInt(r1)
            if (r0 == r1) goto L_0x0194
            android.content.Intent r0 = new android.content.Intent
            r1 = r13
            android.content.Context r1 = (android.content.Context) r1
            java.lang.Class<com.thecrackertechnology.andrax.CheckCoreVersion> r3 = com.thecrackertechnology.andrax.CheckCoreVersion.class
            r0.<init>(r1, r3)
            r0.addFlags(r6)
            r13.startActivity(r0)
        L_0x0194:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.thecrackertechnology.andrax.AndraxApp.checkcoreversion():void");
    }
}
