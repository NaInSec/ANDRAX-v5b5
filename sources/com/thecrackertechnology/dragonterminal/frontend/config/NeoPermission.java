package com.thecrackertechnology.dragonterminal.frontend.config;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0002J\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/thecrackertechnology/dragonterminal/frontend/config/NeoPermission;", "", "()V", "REQUEST_APP_PERMISSION", "", "doRequestPermission", "", "context", "Landroid/app/Activity;", "requestCode", "initAppPermission", "app_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: NeoPermission.kt */
public final class NeoPermission {
    public static final NeoPermission INSTANCE = new NeoPermission();
    public static final int REQUEST_APP_PERMISSION = 10086;

    private NeoPermission() {
    }

    public final void initAppPermission(Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "context");
        if (Build.VERSION.SDK_INT >= 23) {
            Context context = activity;
            if (ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
                return;
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.READ_EXTERNAL_STORAGE")) {
                new AlertDialog.Builder(context).setMessage("需要存储权限来访问存储设备上的文件").setPositiveButton(17039370, new NeoPermission$initAppPermission$1(activity, i)).show();
            } else {
                doRequestPermission(activity, i);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void doRequestPermission(Activity activity, int i) {
        try {
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, i);
        } catch (ActivityNotFoundException unused) {
        }
    }
}
